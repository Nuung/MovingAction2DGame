package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

// main 쓰레드는 여기 있음, static 임!
public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 6691247796639148462L;
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9; // 높이는 넓이에 상대적으로 비례하도록 지정 1대 9/12 비율
	
	// thread 처리의 기본 멤버 변수
	private Thread thread;
	private boolean running = false; // thread 프로세싱의 처리위한 논리변수
	
	private Random r; // 난수 생성 object, enemy의 moving 액션을 난수화 하기 위해서 사용
	
	// 모든 게임 오브젝트, 링크드 리스트 -> 티킹, 랜더링
	private Handler handler; // 우린 그냥 Handler 클래스의 tick, render 메소드만 한번 호출하면, 모든 오브젝트에 대해 명령 하달됨
	private HUD hud;
	
	// 생성자
	public Game() { 
		// 생성자 내부 오브젝트 이니셜라이징 순서 중요함
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler)); // 키 액션 리스너 this object(Canvas)에 등록
		
		new Window(WIDTH, HEIGHT, "GAME", this);
		
		hud = new HUD();
		r = new Random();
		
		handler.addObject(new Player(WIDTH/2 - 32, HEIGHT/2 - 32, ID.Player, handler)); // player object 추가, 위치는 정 가운데
		
		for(int i = 0; i < 5; i++) { // 여러 에너미 생성 예제
			handler.addObject(new BasicEnemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.Enemy, handler)); // 기본 Red색 Enemy object 추가
		}

		
	} // Game의 최고 이니셜 라이징

	public synchronized void start() {
		thread = new Thread(this); // 쓰레드 object에 game object 전달
		thread.start();
		running = true; // 실행중 -> 반복
	} // start -> 게임 진행을 돌아가게 하는 최상위 프로세스 개념
	
	public synchronized void stop() {
		// 쓰레딩 처리에 예외처리는 기본
		try{
			thread.join();
			running = false;
		} catch(Exception e) {
			e.printStackTrace();
		} // try ~ catch
	} // start -> 게임 진행을 돌아가게 하는 최상위 프로세스 개념

	@Override // in 쓰레드의 런에이블 인터페이스의 유일한 메소드
	public void run() {
		this.requestFocus(); // os에게 현재 창을 포커스해라는 명령
		
		// 초당 프레임 설정을 위한 기본적인 로직
		// 2D 쓰레딩, 프로세싱 에선 기본적으로 아래와 같은 로직을 사용한다
		// tick 은 게임적 요소 이니셜라이징과 이벤트 처리
		// render 는 그에 따른 이미지적 처리, 렌더링 (프레임에 맞게) 
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while(running) {
			// 현재타임과 과거 지나간 타임을 계산해서 계속해서
			// 과하지도, 부족하지도 않은 적당한 (컴터에 왠만하면 맞게)
			// 프레임을 유도하기 위한 로직
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta --;
			} // inner while
			
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("Fps : " + frames);
				frames = 0;
			} // frame check printing if
		} // while (running)
		stop(); // running 이 false 면 이쪽으로 넘어옴
	} // run()
	
	private void tick() {
		handler.tick();
		hud.tick();
	} // tick()
	
	private void render() {
		// 렌더링에는 기본적으로 모니터 출력의 이미지 버퍼 전략을 사용함
		// 컨버스 (awt)는 swing과 다르게 버퍼전략을 수동적으로 선언해 줘야함
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) { // 이니셜 라이징
			this.createBufferStrategy(3); // 이미지 버퍼 공간을 3개 사용한다. 
			// 이 역시 랜더링의 불문율 같은 이유이며, 4는 과하고 2는 끊기는 느낌때문에 3이 적당하다고 한다.
			return;
		}
		
		// 그래픽적 요소 랜더링을 위한 g, 그 아래는 Graphics class의 기본메소드
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		// 순서 중요합니다~
		hud.render(g); // 얘는 정적인 object가 아니라 player에 종속되는 class라는 개념으로, handler가 제어하지 않는다
		handler.render(g);

		
		g.dispose();
		bs.show();
	} // render()
	
	// object들이 Frame ( = room)을 벗어나지 못하게! 가두기 메소드
	public static int clamp(int var, int min, int max) {
		// 로직은 간단하다, var에 좌표값이 들어오고 가질 수 있는 최소 좌표값과 최대 좌표값을 넣어준다
		// 아래 조건에 맞게, var 값을 return으로 조져줘서 알아서 못 벗어나게 만들어 준다 
		if(var >= max) // 현 좌표가 최대값 넘으면
			return var = max; // 최대값으로만 계속 유지
		else if(var <= min) { // 최소값보다 작으면 --> 이하 동문
			return var = min;
		} else {
			return var;
		} // if ~ else if ~ else
		
	} // clamp()
	
	public static void main(String args[]) {
		new Game(); // this object의 생성자를 호출 
	} // MAIN
	
} // Game Class

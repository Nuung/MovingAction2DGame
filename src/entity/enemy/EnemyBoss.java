package entity.enemy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import display.Trail;
import entity.GameObject;
import main.Game;
import main.objecttype.Handler;
import main.objecttype.ID;

// GameObject가 abstract 임을 진가를 보이는 Class!
public class EnemyBoss extends GameObject {

	private Handler handler;
	Random rn = new Random();
	// 타이머는 보스에게 특정 움직임의 패턴을 주기위함임
	private int timer = 80; // 첫번째 액션
	private int timer2 = 50; // 두번째 액션
	private int speedtimer = 0; // 두번째 액션 뒤 스피트
	private int timer3 = 1500;
	
	public EnemyBoss(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		 valX = 0; valY = 2;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		this.x += valX;
		this.y += valY;
		
		// timer라는 변수를 추가해서 boss가 위에서 아래로 등장하는 효과를 넣을 수 있다.
		if(timer <= 0 && timer3 > 0) valY = 0;
		else timer --; // 즉 타이머가 0될때까지 ticking (움직임) 되다가 0보다 작거나 같은 순간에 '0' -> 멈추게됨 
		
		if(timer <= 0) timer2--; // timer가 0될때까지 멈춰있는 효과
		if(timer2 <= 0 && timer3 > 0) { // 즉 아래로 등장하고 이제 좌, 우로만 움직임
			if(valX == 0) valX = 2;
			// 총알 나가는 액션! ticking 되면서 Bullet이 object로 추가되는 거
			int spawn = rn.nextInt(10);
			if(spawn == 0) handler.addObject(new EnemyBossBullet(x + 48, y + 48, ID.Enemy, handler));
			if(valX > 0) {
				speedtimer++;
				if (speedtimer > 100) {
					valX += 1; // 총알 쏘면서 좌우 이동속도 상향 / -> 참고로 시간 지날수록 당연히 점점 더 빨라짐 
					speedtimer = 0; 
				} // inner if
			} else if(valX < 0) {
				speedtimer++;
				if (speedtimer > 100) {
					valX -= 1; // 방향 바꼈을때도 고려해서
					speedtimer = 0;
				} // inner if
			} // else if
			timer3--;
		} // if(timer2 <= 0)
		if(timer3 <= 0) {
			valY = 20;
		}
		
		
		// 좌 우 만 움직이니까 x축에 벽에 대한 벗어남 (즉 벽에 충돌) 액션만 추가
		if(x <= 0 || x >= Game.WIDTH - 96 ) valX = -valX;
		if(timer <= 0) {
			if(y <= 0 || y >= Game.HEIGHT - 96 ) valY = -valY;
		}
		
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.RED, 96, 96, 0.088888f, handler)); // 꼬리
	} // tick()

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.RED);
		g.fillRect(x, y, 96, 96);
	} // render()

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x, y, 96, 96);
	} // getBounds()

}

package main;

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
public class MenuParticle extends GameObject {

	private Handler handler;
	private Random rn = new Random();
	
	// effect BALL
	private int red = rn.nextInt(255);
	private int green = rn.nextInt(255);
	private int blue = rn.nextInt(255);
	private Color col;

	public MenuParticle(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		// menu effect! totaly random speed in x, y
		valX = (rn.nextInt(5 - -5) + -7);
		valY = (rn.nextInt(5 - -5) + -7);
		if(valX == 0) valX = 6;
		if(valY == 0) valX = 6;
		
		// Color를 멤버변수 R G B 값을 이용해서 이펙트 효과를 주자.
		col = new Color(red, green, blue); 
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		this.x += valX;
		this.y += valY;
		
		// 프레임 크기 생각! --> 값이 커지면 부딫힌거임! --> 방향 바꿔주기
		// 0 일땐, 상위, 왼쪽 frame경계, Rectange의 윗면, 왼쪽 면 이 부딫힘 (Rectangle의 0, 0이 어딘지 생각해보기)
		// 하지만 하위, 오른쪽 frame 경계는 Rectange의 사이즈만큼 간 포인트 ( width, height 좌표지점에 )에 부딫힌다 그래서 32를 빼줌
		if(y <= 0 || y >= Game.HEIGHT - 30 ) valY = -valY;
		if(x <= 0 || x >= Game.WIDTH - 20 ) valX = -valX; // 실제 프레임의 크기가 정한 완벽한 정수값으로 안떨어짐,, 그래서 애매한 숫자를 사용
		
		handler.addObject(new Trail(x, y, ID.Trail, col, 16, 16, 0.02f, handler));
	} // tick()

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(col);
		g.fillRect(x, y, 16, 16);
	} // render()

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x, y, 16, 16);
	} // getBounds()

}

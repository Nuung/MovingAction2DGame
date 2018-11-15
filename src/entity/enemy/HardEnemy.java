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
public class HardEnemy extends GameObject {

	private Handler handler;
	private Random rn;
	
	public HardEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		rn = new Random();
		valX = 6; 
		valY = rn.nextInt(10);
		if(valY < 6) valY = 6;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		this.x += valX;
		this.y += valY;
		

		// 프레임 크기 생각! --> 값이 커지면 부딫힌거임! --> 방향 바꿔주기
		// 0 일땐, 상위, 왼쪽 frame경계, Rectange의 윗면, 왼쪽 면 이 부딫힘 (Rectangle의 0, 0이 어딘지 생각해보기)
		// 하지만 하위, 오른쪽 frame 경계는 Rectange의 사이즈만큼 간 포인트 ( width, height 좌표지점에 )에 부딫힌다 그래서 32를 빼줌
//		if(y <= 0 || y >= Game.HEIGHT - 30 ) valY = -valY;
//		if(x <= 0 || x >= Game.WIDTH - 20 ) valX = -valX; // 실제 프레임의 크기가 정한 완벽한 정수값으로 안떨어짐,, 그래서 애매한 숫자를 사용
		
		// Hard Enemy에선 조금 특이하게 bound 될 때 (벽에 부딪힐때 마다 속도를 달리 주자
		if(y <= 0 || y >= Game.HEIGHT - 32 ) {
			if(valY < 0) valY = -(rn.nextInt(2- -7)+1)*-1;
			else valY = (rn.nextInt(2- -7)+1)*-1;
		}
		if(x <= 0 || x >= Game.WIDTH - 24 ) {
			if(valX < 0) valX = -(rn.nextInt(2- -7)+1)*-1;
			else valX = (rn.nextInt(2- -7)+1)*-1;
		}
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.YELLOW, 24, 24, 0.02f, handler));
	} // tick()

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, 24, 24);
	} // render()

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x, y, 24, 24);
	} // getBounds()

}

package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import display.HUD;
import display.Trail;
import display.assets.Assets;
import main.Game;
import main.objecttype.Handler;
import main.objecttype.ID;

public class Player extends GameObject {
	
	Random r = new Random();
	Handler handler;
	
	// 생성자
	public Player(int x, int y, ID id, Handler handler) {
		// super class의 member val --> player 최초 세팅
		// super's member's val 변수는 Game에서 Speed에 입각한 개념
		super(x, y, id); // 위치, 열거형 값 ( Know what kind of object )
		this.handler = handler;
	}

	@Override
	public void tick() {
		// moving action 일어남!
		x += valX;
		y += valY;
		
		// frame 창 벗어나지 못하게
		x = Game.clamp(x, 0, Game.WIDTH - 37);
		y = Game.clamp(y, 0, Game.HEIGHT - 60);
		
//		handler.addObject(new Trail(x, y, ID.Trail, Color.WHITE, 32, 32, 0.08f, handler));
		// 충돌 테스팅 --> with Rectangle!!
		collision();
	} // tick()

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
//		g.setColor(Color.white);
//		g.fillRect(x, y, 32, 32);
		g.drawImage(Assets.testPlayer, x, y, 32, 32, null);
	} // render()

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x, y, 32, 32);
	} // getBounds()
	
	public void collision() {
		
		for(int i = 0; i < handler.object.size(); i++) {
			// 일단 handler가 가지고 있는 object를 다 돈다, 이새끼가 'Enemy' 일때 충돌을 감지하는 거여~
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Enemy || tempObject.getId() == ID.FastEnemy || 
					tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.EnemyBoss) { 
				//  intersect는 서로 간섭하는지, 우리가 따로 투명 사각형 (getBounds 메소드를 통해 돌아오는 Rectangle obejct)
				// 좌표값끼리 점과 점 사이 거리 구해주는 공식을 써서 메소드화 시킬 필요 없이, Rectangle class에
				// 기본 메소드에 '간섭'에 대한, 여러 점과 점, 거리와 거리를 테스팅 해주는 좋은 메소드가 있다
				if(getBounds().intersects(tempObject.getBounds())) { // 간섭되면 true, 아니면 false가 리턴된다
					// collision code
					HUD.HEALTH -= 2; 
				} // inner if
			} // if
		} // for
		
	} // collision()
	
}

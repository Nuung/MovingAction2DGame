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

// Enemy Boss의 총알이 될 object!
public class EnemyBossBullet extends GameObject {

	private Handler handler;
	Random rn = new Random();
	
	public EnemyBossBullet(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		// 총알이 존나 좌 우 막나가게 
		valX = (rn.nextInt(5 - -5) + -5); 
		valY = 5;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		this.x += valX;
		this.y += valY;
		
//		if(y <= 0 || y >= Game.HEIGHT - 30 ) valY = -valY;
//		if(x <= 0 || x >= Game.WIDTH - 20 ) valX = -valX; // 실제 프레임의 크기가 정한 완벽한 정수값으로 안떨어짐,, 그래서 애매한 숫자를 사용
		// 벗어나면 그냥 object에서 삭제 -> 총알 역할이니까 임시 오브젝트!
		if(y >= Game.HEIGHT) handler.removeObject(this);
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.RED, 16, 16, 0.02f, handler));
	} // tick()

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.RED);
		g.fillRect(x, y, 16, 16);
	} // render()

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x, y, 16, 16);
	} // getBounds()

}

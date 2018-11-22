package entity.item;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import display.HUD;
import display.Trail;
import entity.GameObject;
import entity.Player;
import main.Game;
import main.objecttype.Handler;
import main.objecttype.ID;

//GameObject가 abstract 임을 진가를 보이는 Class!
public class Shield extends GameObject {

	private Handler handler;
	private Player player;
	private int rotateCounter = 0;
	
	public Shield(int x, int y, ID id, Handler handler, Player player) {
		super(player.getPlayerX() - 4, player.getPlayerY() - 4, id);
		this.handler = handler;
		this.player = player;
		valY = 2;
	}

	@Override
	public void tick() {
		rotateCounter++;
		
		if(rotateCounter >= 4) {
			rotate(300, 300, this.x, this.y, 10);
			rotateCounter = 0;
		} // if
		
//		handler.addObject(new Trail(x, y, ID.Trail, Color.MAGENTA, 10, 10, 0.18f, handler));
		collision();
	} // tick()

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.MAGENTA);
		g.fillRect(x, y, 10, 10);
	} // render()

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x, y, 10, 10);
	} // getBounds()

	public void collision() {
		
		for(int i = 0; i < handler.object.size(); i++) {
			// 일단 handler가 가지고 있는 object를 다 돈다, 이 'Enemy' 일때 충돌을 감지하는 거여~
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Enemy || tempObject.getId() == ID.FastEnemy || 
					tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.EnemyBoss
					|| tempObject.getId() == ID.HardEnemy ) { 
				// Player Class의 collision을 참조
				if(getBounds().intersects(tempObject.getBounds())) { // 간섭되면 true, 아니면 false가 리턴된다
					// collision code
					handler.removeObject(tempObject);
				} // inner if
			} // if
		} // for
		
	} // collision()
	
	public void rotate(int centerX, int centerY, int oldX, int oldY, int deg){
		
		double dDegree = Math.toRadians(deg);
		double cosd = Math.cos(dDegree);
		double sind = Math.sin(dDegree);

		// (150,150)좌표를 중심으d로 회전
		double newX =  (oldX - centerX) * cosd - (oldY - centerX) * sind + centerX;                  
		double newY =  (oldX - centerY) * sind + (oldY - centerY) * cosd + centerY;
		
		this.x = (int) newX;
		this.y = (int) newY;
	 }
	
}

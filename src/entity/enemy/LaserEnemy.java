package entity.enemy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import display.Trail;
import entity.GameObject;
import main.Game;
import main.objecttype.Handler;
import main.objecttype.ID;

// GameObject가 abstract 임을 진가를 보이는 Class!
public class LaserEnemy extends GameObject {

	private Handler handler;
	private int valHSize;
	private boolean isHorizontal;
	
	// Laser Event Action!
	private boolean actionBool = true;
	private int actionCount1, actionCount2, actionCount3;
	
	public LaserEnemy(int x, int y, ID id, Handler handler, boolean isHorizontal) {
		super(x, y, id); // 0는 무조건 0이어야 함
		this.handler = handler;
		this.valHSize = 0;
		this.isHorizontal = isHorizontal;
		
		// for event action
		this.actionCount1 = 0;
		this.actionCount2 = 0;
		this.actionCount3 = 0;
	}

	@Override
	public void tick() {
	} // tick()

	@Override
	public void render(Graphics g) {
		
		// action Count가 곧 타이머의 기능, 그렇게 타이머 마다 액션을 넣어줌
		if(actionCount1 < 1000) {
			if(actionCount2 >= 10) {
				g.setColor(Color.RED);
				if(isHorizontal) g.fillRect(x, y, Game.WIDTH, 6); 
				else g.fillRect(x, y, 8, Game.HEIGHT);
				actionCount2 = 0;
			}
			actionCount1++;
			actionCount2++;
		} // if
		else if (actionCount1 >= 1000 && actionCount3 < 150){
			valHSize = 14; 

			g.setColor(Color.RED);
			if(isHorizontal) g.fillRect(x, y, Game.WIDTH, valHSize); 
			else g.fillRect(x, y, valHSize, Game.HEIGHT);
			
			actionCount3++;
		} // else if
		else if(actionCount3 >= 150){
			handler.removeObject(this);
		}
		
	} // render()

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		if(isHorizontal) return new Rectangle(x, y, Game.WIDTH, valHSize);
		else return new Rectangle(x, y, valHSize, Game.HEIGHT);
	} // getBounds()

}

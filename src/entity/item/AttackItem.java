package entity.item;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import display.Trail;
import entity.GameObject;
import main.objecttype.Handler;
import main.objecttype.ID;

// GameObject가 abstract 임을 진가를 보이는 Class!
public class AttackItem extends GameObject {

	private Handler handler;
	
	public AttackItem(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		valY = 5;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		this.y -= valY;
		handler.addObject(new Trail(x, y, ID.Trail, Color.WHITE, 8, 8, 0.21f, handler));
	} // tick()

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.WHITE);
		g.fillRect(x, y, 8, 8);
	} // render()

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x, y, 8, 8);
	} // getBounds()

}

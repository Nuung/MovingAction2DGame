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
public class TsunamiEnemy extends GameObject {

	private Handler handler;
	private Color inColor;
	
	public TsunamiEnemy(int x, int y, ID id, Handler handler, Color inColor, Boolean isLeft) {
		super(x, y, id);
		this.handler = handler;
		this.inColor = inColor;
		
		// isLeft == true -> 왼쪽에서 오른쪽으로 , 반대일 경우 오른쪽에서 왼쪽
		if(isLeft) valX = 5;
		else valX = -5;
		valY = 0;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		this.x += valX;
		
		handler.addObject(new Trail(x, y, ID.Trail, this.inColor, 16, 16, 0.049f, handler));
	} // tick()

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(this.inColor);
		g.fillRect(x, y, 16, 16);
	} // render()

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x, y, 16, 16);
	} // getBounds()

}

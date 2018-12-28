package entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import main.objecttype.ID;

public class StateDoor extends GameObject {

	// x, y member는 위치하는 좌표값 / 크기 값 필요 / render에서 fillRect하면 됨!
	
	public StateDoor(int x, int y, ID id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

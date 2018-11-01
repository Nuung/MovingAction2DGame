package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Player extends GameObject {
	
	Random r = new Random();
	
	// 생성자
	public Player(int x, int y, ID id) {
		// super class의 member val --> player 최초 세팅
		// super's member's val 변수는 Game에서 Speed에 입각한 개념
		super(x, y, id); // 위치, 열거형 값 ( Know what kind of object )		
	}

	@Override
	public void tick() {
		// moving action 일어남!
		x += valX;
		y += valY;
		
		// frame 창 벗어나지 못하게
		x = Game.clamp(x, 0, Game.WIDTH - 37);
		y = Game.clamp(y, 0, Game.HEIGHT - 60);
	} // tick()

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.white);
		g.fillRect(x, y, 32, 32);
	} // render()
	
	
	
}

package main.status;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import display.HUD;
import main.Game;
import main.objecttype.Handler;

public class Shop extends MouseAdapter {

	private Handler handler;
	private HUD hud;
	
	// for Cost of item! (box) -> coin is same with score!, 초기 가격임
	private int Box1 = 10; // max health 
	private int Box2 = 10; // speed
	private int Box3 = 10; // healing health
	private int Box4 = 10; // attack damage
	
	public Shop(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", 0, 48));
		g.drawString("Shop", Game.WIDTH/2 - 100, 50);
	
		// in Box1 --> ' HEALTH '
		g.setFont(new Font("arial", 0, 12));
		g.drawString("Upgrade Health", 110, 120);
		g.drawString("Cost : " + Box1, 110, 140);
		g.drawRect(99, 100, 100, 80);
		
		// in Box2 --> ' SPEED '
		g.setFont(new Font("arial", 0, 12));
		g.drawString("Upgrade Speed", 260, 120);
		g.drawString("Cost : " + Box2, 260, 140);
		g.drawRect(249, 100, 100, 80);
		
		// in Box3 --> ' Refill HEALTH '
		g.setFont(new Font("arial", 0, 12));
		g.drawString("Refill Health", 410, 120);
		g.drawString("Cost : " + Box3, 410, 140);
		g.drawRect(400, 100, 100, 80);
		
		// in Box4 --> '  Attack Damage '
		g.setFont(new Font("arial", 0, 12));
		g.drawString("Attack Damage", 110, 240);
		g.drawString("Cost : " + Box4, 110, 280);
		g.drawRect(99, 215, 100, 80);
		
		g.drawString("Score : " + hud.getScore(),  Game.WIDTH/2 - 50, 300);
		g.drawString("Press 'Space' to go back!",  Game.WIDTH/2 - 50, 330);
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		// for Box1 --> ' HEALTH '
		if(mx >= 100 && mx <= 200) {
			if(my >= 100 && my <= 180) {
				if(hud.getScore() >= Box1) { // Box1 보다 큰 스코어 있으면
					hud.setScore(hud.getScore() - Box1);
					Box1 += 10; // 구매할때마다 당근 가격 오름
					// hud의 bounds 변수로 HP를 제어함
					hud.bounds += 20;
					hud.HEALTH = (100 + (hud.bounds/2));
				} // most inner if
			}
		} // if
		
		// for Box2 --> ' SPEED '
		if(mx >= 250 && mx <= 350) {
			if(my >= 100 && my <= 180) {
				if(hud.getScore() >= Box2) { // Box2 보다 큰 스코어 있으면
					hud.setScore(hud.getScore() - Box2);
					Box2 += 10; // 구매할때마다 당근 가격 오름
					// KeyInput의 불리언 변수값의 int값으로 speed제어
					handler.spd++; // 그 spd값을 모든 object를 제어하는 handler를 통해 다시 제어
				} // most inner if
			}
		} // if

		// for Box3 --> ' Refill HEALTH '
		if(mx >= 400 && mx <= 500) {
			if(my >= 100 && my <= 180) {
				if(hud.getScore() >= Box3) { // Box3 보다 큰 스코어 있으면
					hud.setScore(hud.getScore() - Box3);
					Box3 += 10; // 구매할때마다 당근 가격 오름
					hud.HEALTH = (100 + (hud.bounds/2));
				} // most inner if
			}
		} // if
		
		// for Box4 --> ' Attack Damage '
		if(mx >= 99 && mx <= 199) {
			if(my >= 215 && my <= 315) {
				if(hud.getScore() >= Box4) { // Box3 보다 큰 스코어 있으면
					hud.setScore(hud.getScore() - Box4);
					Box4 += 10; // 구매할때마다 당근 가격 오름
					handler.attackDamage += 2;
					System.out.println("");
				} // most inner if
			}
		} // if
		
	} // mousePressed()
	
} // Shop Class

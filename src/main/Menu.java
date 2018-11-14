package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import display.HUD;
import entity.Player;
import entity.enemy.BasicEnemy;
import main.Game.STATE;
import main.objecttype.Handler;
import main.objecttype.ID;

public class Menu extends MouseAdapter{

	private Handler handler;
	private HUD hud; // for score
	private Random r = new Random();
	private int centerYPos;
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
		this.centerYPos = Game.HEIGHT / 2;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(Game.gameState == STATE.Menu) {
			// 'PLAY' 가 적힌 Rectangle 구역
			if(moveOver(mx, my, 210, 150, 200, 64)) {
				Game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2 - 32, Game.HEIGHT/2 - 32, ID.Player, handler)); // player object 추가, 위치는 정 가운데
				handler.clearEnemys(); // Player 제외한 메뉴 이펙트로 넣은 새끼들 모두 제거
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.Enemy, handler)); // 기본 Red색 Enemy object 추가
			} // inner if
			
			// 'HELP' 가 적힌 Rectangle 구역
			if(moveOver(mx, my, 210, 250, 200, 64)) {
				Game.gameState = STATE.Help;
				System.out.println("HELP");
			} // inner if
			
			// 'QUIT' 이 적힌 Rectangle 구역
			if(moveOver(mx, my, 210, 350, 200, 64)) {
				System.exit(1);
			} // inner if
		} // Menu if
		
		// BackButton for help / 'BACK' 이 적힌 Rectangle 구역
		if(Game.gameState == STATE.Help) {
			if(moveOver(mx, my, 210, 350, 200, 64)) {
				Game.gameState = STATE.Menu;
				return;
			} // inner if
		} // Help if
		
		// TrayAgain Button for End / 'Try Again' 이 적힌 Rectangle 구역
		if(Game.gameState == STATE.End) {
			if(moveOver(mx, my, 210, 350, 200, 64)) {
				Game.gameState = STATE.Game;
				hud.setLevel(1); hud.setScore(0);
				handler.addObject(new Player(Game.WIDTH/2 - 32, Game.HEIGHT/2 - 32, ID.Player, handler)); // player object 추가, 위치는 정 가운데
				handler.clearEnemys(); // Player 제외한 메뉴 이펙트로 넣은 새끼들 모두 제거
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.Enemy, handler)); // 기본 Red색 Enemy object 추가
			} // inner if
		} // Help if
		
	} // mousePressed()
	
	public void mouseReleased(MouseEvent e) {
	}
	
	// mx, my값이 우리가 지정한 특정 지역의 위 아래 범위 내라면 -> 버튼 처럼 액션을 준다
	private boolean moveOver(int mx, int my, int x, int y, int width, int height) {
		// 파라미터 x y 는 어떤 object의 시작 좌표점이겠고, width와 height는 그 object의 크기를 지정해준다
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true; // 그 공간 제대로 눌렀음!
			} else { 
				return false; 
			} // inner if ~ else
		} else { 
			return false; 
		} // if ~ else
	}
	
	public void tick() {
		
	}  //  tick()
	
	public void render(Graphics g) {
		if(Game.gameState == STATE.Menu) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			// About menu String and Button shape Rectangle
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("MENU", 240, 90);
			
			g.setFont(fnt2);
			g.setColor(Color.WHITE);
			g.drawRect(210, 150, 200, 64);
			g.drawString("PLAY", 270, 190);
			
			g.drawRect(210, 250, 200, 64);	
			g.drawString("HELP", 270, 290);

			g.drawRect(210, 350, 200, 64);
			g.drawString("QUIT", 270, 390);
		} // Menu State에서 호출 if
		else if(Game.gameState == STATE.Help) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 15);
			
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("HELP", 240, 90);
			
			g.setFont(fnt3);
			g.drawString("Use WASD keys to move playerd and dodge enemies. Made By Nuung", 65, centerYPos);
			
			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);	
			g.drawString("BACK", 270, 390);
		} // Help State에서 호출 if
		else if(Game.gameState == STATE.End) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 15);
			
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("GAME OVER", 170, 90);
			
			g.setFont(fnt3);
			g.drawString("You lost with a score of : " + hud.getScore(), 200, centerYPos);
			
			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);	
			g.drawString("Try Again", 245, 390);
		} // End State에서 호출 if
		else if(Game.gameState == STATE.HyeonSooOne) {
			Font fnt = new Font("arial", 1, 50);
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("GAME OVER", 170, 90);
		} // HyeonSooOne State에서 호출 if
		else if(Game.gameState == STATE.HyeonSooTwo) {
			
		} //HyeonSooTwo State에서 호출 if
		
	} // render()
} // Menu Class

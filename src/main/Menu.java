package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import entity.Player;
import entity.enemy.BasicEnemy;
import main.Game.STATE;
import main.objecttype.Handler;
import main.objecttype.ID;

public class Menu extends MouseAdapter{

	// 기본적으로 가지고 있어야할 object, 프로젝트 내부상으로 최상의 Class object들
	private Game game;
	private Handler handler;
	private Random r = new Random();
	private int centerXPos, centerYPos;
	
	public Menu(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
		this.centerXPos = this.game.WIDTH / 2;
		this.centerYPos = this.game.HEIGHT / 2;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState == STATE.Menu) {
			// 'PLAY' 가 적힌 Rectangle 구역
			if(moveOver(mx, my, 210, 150, 200, 64)) {
				game.gameState = STATE.Game;
				handler.addObject(new Player(game.WIDTH/2 - 32, game.HEIGHT/2 - 32, ID.Player, handler)); // player object 추가, 위치는 정 가운데
				handler.addObject(new BasicEnemy(r.nextInt(game.WIDTH), r.nextInt(game.HEIGHT), ID.Enemy, handler)); // 기본 Red색 Enemy object 추가
			} // inner if
			
			// 'HELP' 가 적힌 Rectangle 구역
			if(moveOver(mx, my, 210, 250, 200, 64)) {
				game.gameState = STATE.Help;
				System.out.println("HELP");
			} // inner if
			
			// 'QUIT' 이 적힌 Rectangle 구역
			if(moveOver(mx, my, 210, 350, 200, 64)) {
				System.exit(1);
			} // inner if
		} // Menu if
		
		// BackButton for help / 'BACK' 이 적힌 Rectangle 구역
		if(game.gameState == STATE.Help) {
			if(moveOver(mx, my, 210, 350, 200, 64)) {
				game.gameState = STATE.Menu;
				return;
			} // inner if
		} // Help if
		
	} // mousePressed()
	
	public void mouseReleased(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();		
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
		if(game.gameState == STATE.Menu) {
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
		else if(game.gameState == STATE.Help) {
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
				
	} // render()
} // Menu Class

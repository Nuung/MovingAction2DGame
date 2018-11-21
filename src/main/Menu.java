package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Random;

import javax.swing.JOptionPane;

import database.DBConnection;
import display.HUD;
import display.Window;
import display.assets.Assets;
import entity.Player;
import entity.enemy.BasicEnemy;
import entity.enemy.HardEnemy;
import main.Game.STATE;
import main.objecttype.Handler;
import main.objecttype.ID;

public class Menu extends MouseAdapter{

	private Game game;
	private Handler handler;
	private HUD hud; // for score
	private Random r = new Random();
	private int centerYPos;
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
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
				Game.gameState = STATE.Select;
				return;
			} // inner if
			
			// 'Leaderboard' 가 적힌 Rectangle 구역
			if(moveOver(mx, my, 210, 250, 200, 64)) {
				Game.gameState = STATE.Leaderboard;
			} // inner if
			
			// 'QUIT' 이 적힌 Rectangle 구역
			if(moveOver(mx, my, 210, 350, 200, 64)) {
				System.exit(1);
			} // inner if
		} // Menu if
		
		if(Game.gameState == STATE.Select) {
			// 'Normal' 적힌 Rectangle 구역
			if(moveOver(mx, my, 210, 150, 200, 64)) {
				Game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2 - 32, Game.HEIGHT/2 - 32, ID.Player, handler)); // player object 추가, 위치는 정 가운데
				handler.clearEnemys(); // Player 제외한 메뉴 이펙트로 넣은 새끼들 모두 제거
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.Enemy, handler)); // 기본 Red색 Enemy object 추가
				game.diff = 1;
			} // inner if
			
			// 'Hard' 가 적힌 Rectangle 구역
			if(moveOver(mx, my, 210, 250, 200, 64)) {
				Game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2 - 32, Game.HEIGHT/2 - 32, ID.Player, handler)); // player object 추가, 위치는 정 가운데
				handler.clearEnemys(); // Player 제외한 메뉴 이펙트로 넣은 새끼들 모두 제거
				handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.HardEnemy, handler)); // 기본 Red색 Enemy object 추가
				handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.HardEnemy, handler)); // 기본 Red색 Enemy object 추가
				game.diff = 2;
			} // inner if
			
			// 'Back' 이 적힌 Rectangle 구역
			if(moveOver(mx, my, 210, 350, 200, 64)) {
				Game.gameState = STATE.Menu;
				return;
			} // inner if
		} // Menu if
		
		// BackButton for Leaderboard / 'BACK' 이 적힌 Rectangle 구역
		if(Game.gameState == STATE.Leaderboard) {
			if(moveOver(mx, my, 210, 350, 200, 64)) {
				Game.gameState = STATE.Menu;
				return;
			} // inner if
		} // Help if
		
		// TrayAgain Button for End / 'Try Again' 이 적힌 Rectangle 구역
		if(Game.gameState == STATE.End) {
			
			if(moveOver(mx, my, 120, 350, 166, 64)) { // Try Again
				Game.gameState = STATE.Menu;
				hud.setLevel(1); hud.setScore(0);
			} // inner if
			
			if(moveOver(mx, my, 286, 350, 166, 64)) { // Up load the score to DB
				DBConnection dbScore = new DBConnection();
				try {
					dbScore.InsertScore(hud.getScore());
				} catch(Exception e1) {
					System.out.println(" DB Inserting error (in Menu Class) : " + e1.getMessage());
				} // try - catch
				JOptionPane.showMessageDialog(null, "스코어가 정상적으로 업로드 됐습니다.");
				Game.DBsetting(); // 새로운 Score값이 추가됐으니 리더보드 값이 변경됨 -> DBsetting 다시
			} // inner if
		} // Help if
		
	} // mousePressed()
	
	public void mouseReleased(MouseEvent e) {
		
	} // mouseReleased()
	
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
			g.drawString("Dodge the", 127, 90);
			g.setColor(Color.RED);
			g.drawString("DOT", 390, 90);
			
			g.setFont(fnt2);
			g.setColor(Color.WHITE);
			g.drawRect(210, 150, 200, 64);
			g.drawString("PLAY", 270, 190);
			
			g.drawRect(210, 250, 200, 64);	
			g.drawString("Leaderboard", 220, 292);

			g.drawRect(210, 350, 200, 64);
			g.drawString("QUIT", 270, 390);
		} // Menu State에서 호출 if
		else if(Game.gameState == STATE.Leaderboard) {			
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 15);
			
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("Leaderboard", 160, 90);
			
			g.setFont(fnt3);
			g.drawString("Use WASD keys to move playerd and dodge enemies. Made By Nuung", 65, centerYPos-120);
			
			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);	
			g.drawString("BACK", 270, 390);
			
			// icon rendering
			g.drawImage(Assets.leaderButton, 100, 130, 80, 80, null);
			
			g.drawRect(210, 140, 340, 64);	
			g.drawString(Game.leaderBoard.get(0), 220, 182);
			g.drawRect(210, 204, 340, 64);	
			g.drawString(Game.leaderBoard.get(1), 220, 247);
			g.drawRect(210, 268, 340, 64);	
			g.drawString(Game.leaderBoard.get(2), 220, 310);
			
			g.setFont(fnt3);
			g.drawString("My Best Score", 80, 240);
			g.drawString(": "+Game.mybestBoard, 80, 260);
			
//			Graphics2D g2d = (Graphics2D) g;
//			g2d.translate(170, 0); // If needed 
//			g2d.rotate(1); // Rotate the image by 1 radian 
//			g2d.rotate(180.0/3.14);  // to rotate by 1 degree 
//			g2d.drawImage(Assets.leaderButton, 130, 0, 80, 80, null);
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
			g.drawRect(120, 350, 166, 64);	
			g.drawString("Try Again", 135, 390);
			
			g.setFont(fnt2);
			g.drawRect(286, 350, 245, 64);	
			g.drawString("Save the Score", 301, 390);
		} // End State에서 호출 if
		if(Game.gameState == STATE.Select) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			// About menu String and Button shape Rectangle
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("Select Difficulty", 120, 90);
			
			g.setFont(fnt2);
			g.setColor(Color.WHITE);
			g.drawRect(210, 150, 200, 64);
			g.drawString("Normal", 270, 190);
			
			g.drawRect(210, 250, 200, 64);	
			g.drawString("Hard", 270, 290);

			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 270, 390);
		} // Menu State에서 호출 if
	} // render()
} // Menu Class

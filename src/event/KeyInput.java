package event;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import entity.GameObject;
import entity.Player;
import entity.item.AttackItem;
import main.Game;
import main.Game.STATE;
import main.objecttype.Handler;
import main.objecttype.ID;

// key 입력을 통해 object의 무빙을 제어하는 Class
public class KeyInput extends KeyAdapter {
	
	// handler가 모든 object를 노드, 링크드 리스트 형태로 제어한다는 점 잊지말자
	private Handler handler;
	private static Player PlayerObject;
	private boolean[] keyDown = new boolean[4]; // key입력이 처먹어들어가는 경우때문에 일반 입력에서 배열형 논리로 바꿈
	
	// for detact game state!
	private Game game;
	
	// 생성자
	public  KeyInput(Handler handler, Game game) {
		this.game = game;
		this.handler = handler;  // 추후 동작을 위해 handler 객체 가지고 있기
		for(int i = 0; i < 4; i ++) {
			this.keyDown[i] = false; 
		} // for
	}
	
	public void keyPressed(KeyEvent e) { // key 눌렀을때
		int key = e.getKeyCode(); // 특정 키값 입력받기 
		
		// Key events for player 
		// handler.object.size는 링크드 리스트의 토탈 사이즈 (개수가) 됨
		// 즉, 모든 object 개수만큼 액션 지정(event 추가) 돌린다는 의미임
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);				
			if(tempObject.getId() == ID.Player) {
				// KeyEvent Class는 키보드의 특정 키 값을 변수형식으로 가지고 있음
				if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) { tempObject.setValY(-handler.spd); keyDown[0] = true; }
				if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) { tempObject.setValY(handler.spd);  keyDown[1] = true; }
				if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) { tempObject.setValX(-handler.spd); keyDown[2] = true; }
				if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) { tempObject.setValX(handler.spd); keyDown[3] = true; }
				KeyInput.PlayerObject = (Player) tempObject;
			} // if
		} // for
		
		// for stop button ( 'p' 키 )
		if(key == KeyEvent.VK_P) { // 설계는 간단하다 p 누를때마다 static 변수의 논리값이 바뀐다
			if(Game.gameState == STATE.Game) {
				if(Game.paused) Game.paused = false;
				else Game.paused = true;
			} // inner if
		} // if
		
		// for Attack button ( 'k' 키 )
		if(key == KeyEvent.VK_K) {
			if(Game.gameState == STATE.Game) {
				// 정교한 좌표값 조정 위해 정수값 빼줌
				handler.addObject(new AttackItem(PlayerObject.getPlayerX() + 17, PlayerObject.getPlayerY() + 10, ID.AttackItem, handler));
			} // inner if
		} // if
		
		if(key == KeyEvent.VK_ESCAPE) System.exit(1); // ESC 키 누르면 걍 게임꺼지게 
		
		// for entering to shop (State)
		if(key == KeyEvent.VK_SPACE) {
			if(Game.gameState == STATE.Game) Game.gameState = STATE.Shop;
			else if(Game.gameState == STATE.Shop) Game.gameState = STATE.Game;
		}
		
	} // keyPressed()
	
	public void keyReleased(KeyEvent e) { // key 땠을때
		int key = e.getKeyCode(); // 특정 키값 입력받기 
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Player) {
				// Key events for player
				// 키를 때면 움직임을 멈춰야함! -> 특정 땐 키 값에 대한 그 방향의 변수만 바꿔줘야 부드러운 움직임 완성
				if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) keyDown[0] = false; 
				if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) keyDown[1] = false; 
				if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) keyDown[2] = false; 
				if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) keyDown[3] = false; 
				
				// 배열형으로 바뀌면서 달라진 로직 --> 같은 방향 벡터의 조작은 둘 다 false여만 변화를 죽여라
				if(!keyDown[0] && !keyDown[1]) tempObject.setValY(0); // vertical movement
				if(!keyDown[2] && !keyDown[3]) tempObject.setValX(0); // horizantal movement
			} // if
		} // for
		
	} // keyReleased()
	
}

package event;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import entity.GameObject;
import main.objecttype.Handler;
import main.objecttype.ID;

// key 입력을 통해 object의 무빙을 제어하는 Class
public class KeyInput extends KeyAdapter {
	
	// handler가 모든 object를 노드, 링크드 리스트 형태로 제어한다는 점 잊지말자
	private Handler handler;
	private boolean[] keyDown = new boolean[4]; // key입력이 처먹어들어가는 경우때문에 일반 입력에서 배열형 논리로 바꿈
	
	// 생성자
	public  KeyInput(Handler handler) {
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
				if(key == KeyEvent.VK_W) { tempObject.setValY(-5); keyDown[0] = true; }
				if(key == KeyEvent.VK_S) { tempObject.setValY(5);  keyDown[1] = true; }
				if(key == KeyEvent.VK_A) { tempObject.setValX(-5); keyDown[2] = true; }
				if(key == KeyEvent.VK_D) { tempObject.setValX(5); keyDown[3] = true; }
			} // if
		} // for
		
		if(key == KeyEvent.VK_ESCAPE) System.exit(1); // ESC 키 누르면 걍 게임꺼지게 
		
	} // keyPressed()
	
	public void keyReleased(KeyEvent e) { // key 땠을때
		int key = e.getKeyCode(); // 특정 키값 입력받기 
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Player) {
				// Key events for player
				// 키를 때면 움직임을 멈춰야함! -> 특정 땐 키 값에 대한 그 방향의 변수만 바꿔줘야 부드러운 움직임 완성
				if(key == KeyEvent.VK_W) keyDown[0] = false; 
				if(key == KeyEvent.VK_S) keyDown[1] = false; 
				if(key == KeyEvent.VK_A) keyDown[2] = false; 
				if(key == KeyEvent.VK_D) keyDown[3] = false; 
				
				// 배열형으로 바뀌면서 달라진 로직 --> 같은 방향 벡터의 조작은 둘 다 false여만 변화를 죽여라
				if(!keyDown[0] && !keyDown[1]) tempObject.setValY(0); // vertical movement
				if(!keyDown[2] && !keyDown[3]) tempObject.setValX(0); // horizantal movement
			} // if
		} // for
		
	} // keyReleased()
	
}

package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// key 입력을 통해 object의 무빙을 제어하는 Class
public class KeyInput extends KeyAdapter {
	
	// handler가 모든 object를 노드, 링크드 리스트 형태로 제어한다는 점 잊지말자
	private Handler handler;
	
	public  KeyInput(Handler handler) {
		this.handler = handler;  // 추후 동작을 위해 handler 객체 가지고 있기
	}
	
	public void keyPressed(KeyEvent e) { // key 눌렀을때
		int key = e.getKeyCode(); // 특정 키값 입력받기 
		System.out.println(key);
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Player) {
				// Key events for player 1
				
				
			} // if
		} // for
	}
	
	public void keyReleased(KeyEvent e) { // key 땠을때
		
	}
	
}

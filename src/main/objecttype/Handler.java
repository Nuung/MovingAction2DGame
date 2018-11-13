package main.objecttype;

import java.awt.Graphics;
import java.util.LinkedList;

import entity.GameObject;
import entity.Player;
import main.Game;

// handler 는 object들을 계속해서 최신상태로 유지해 주는 역할을 한다.
// java 특성상, 객체 지향에서, 핵심 객체들을 상호작용하게 도와주는 Class
public class Handler {
	
	// 우리는 GameObject (abstract) class로 여러 형태 object를 찍어낸다!
	// 그 모든 object를 list, 더 나아가 Head와 Tail을 가지는 LinkedList 형태로 구현
	// 각 Game object 로 만들어진 palyer, enemy, coin etc 들은 노드로 저장된다
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	// 노드형태로 저장된 모든 GameObject에 대한 메소드 접근 구현
	// 쓰레딩이 된다면, 모든 게임 오브젝트가 다 알아서 ticking, rendering 되도록 구현하기 위해 이런 구조 사용
	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.tick(); // 재귀가 아니라 GameObject의 tick 메소드로 간다는 점
			// abstract 니까 찍어낸 하위 class의 재정의(override)된 tick로 간다는걸 알아야 한다
		}
	} // tick()
	
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.render(g); // 재귀가 아니라 GameObject의 tick 메소드로 간다는 점
			// abstract 니까 찍어낸 하위 class의 재정의(override)된 render로 간다는걸 알아야 한다
		} // for
	} // render()
	
	// 존재하는 모든 enemy type의 object들 없애기 -> 보스 등장 고조 위해 / 또 여러가지 초기화 화면 or 상태 유도
	public void clearEnemys() {
		// 노드들 다 검색위해 for
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			// player 오브젝트 만나면 일단 다 비우고 다시 플레이어 만들어주깅
			if(tempObject.getId() == ID.Player) {
				object.clear();
				if(Game.gameState != Game.STATE.End) // 끝난 상태가 아닐때만 player 추가 위해서
					addObject(new Player((int)tempObject.getX(), (int)tempObject.getY(), ID.Player, this));
			} // if
		} // for
	} // clearEnemys()
	
	// 링크드 리스트에 특정 GameObject object를 추가하기
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	// 링크드 리스트에 특정 GameObject object를 삭제하기
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
}

package main;

import java.awt.Graphics;
import java.util.LinkedList;

// handler 는 object들을 계속해서 최신상태로 유지해 주는 역할을 한다.
// java 특성상, 객체 지향에서, 핵심 객체들을 상호작용하게 도와주는 Class
public class Handler {
	
	// 우리는 GameObject (abstract) class로 여러 형태 object를 찍어낸다!
	// 그 모든 object를 list, 더 나아가 Head와 Tail을 가지는 LinkedList 형태로 구현
	// 각 Game object 로 만들어진 palyer, enemy, coin etc 들은 노드로 저장된다
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
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
		}
	} // render()
	
	// 링크드 리스트에 특정 GameObject object를 추가하기
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	// 링크드 리스트에 특정 GameObject object를 삭제하기
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
}

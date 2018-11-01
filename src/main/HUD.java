package main;

import java.awt.Color;
import java.awt.Graphics;

// obejct의 상태에 대한 정보를 가지고, 그 정보를 ticking, redering 해주는 class
// ex) LV, SCORE, HEALTH, etc
public class HUD {
	
	public static int HEALTH = 100;
	
	public void tick() {
		HEALTH -= 1;
		HEALTH = Game.clamp(HEALTH, 0, 100); // 많이 사용함. 틀 (room) 벗어나지 못하게! --> 여기선 HP 창
	} // tick()
	
	public void render(Graphics g) {
		
		// HP 창은 아래와 같은 3개의 도형 object 3겹 -> 제어해줄건 HEALTH 와 관련된 것만 해주면댐
		g.setColor(Color.GRAY);
		g.fillRect(15, 15, 200, 32);
		g.setColor(Color.GREEN);
		g.fillRect(15, 15, HEALTH * 2, 32);
		g.setColor(Color.WHITE);
		g.drawRect(15, 15, 200, 32);
	} // render()
	
}

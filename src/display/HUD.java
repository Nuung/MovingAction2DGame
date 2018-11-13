package display;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JOptionPane;

import database.DBConnection;
import main.Game;

// obejct의 상태에 대한 정보를 가지고, 그 정보를 ticking, redering 해주는 class
// ex) LV, SCORE, HEALTH, etc
public class HUD {
	
	public static int HEALTH = 100;
	//R G B 8bits (1byes)의 최대값 2^8 - 1 --> 요놈 값 조종해서 다른 색으로 랜더링 편하게 가능
	private int greenValue = 255; 
	
	// spawn위해 score, level system 쓰기
	private int score = 0;
	private int level = 1;
	
	public void tick() {
		HEALTH = Game.clamp(HEALTH, 0, 100); // 많이 사용함. 틀 (room) 벗어나지 못하게! --> 여기선 HP 창
		greenValue = Game.clamp(greenValue, 0, 255); // 위와 목적 동일, 범위 벗어나지 못하게 --> 가볍게 에러 방지 가능
		
		// 아래와 같이 하면 greenValue값은 헬스값에 비례해서 낮아지고
		// 결국 Health 막대기는 Red에 가까워 진다. --> 색깔 이펙트의 기본 로직
		greenValue = HEALTH * 2;
		score = score + 1;
		
		/* test line in DB
		if(HEALTH <= 0) {
			int result = JOptionPane.showConfirmDialog(null, "You Are DEAD", "Select", JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
				DBConnection dbScore = new DBConnection();
				dbScore.InsertScore(this.score);
				System.exit(0);
			} // inner if
			else {
				System.exit(0);
			}
		} // if
		*/
		
	} // tick()
	
	public void render(Graphics g) {
		
		// HP 창은 아래와 같은 3개의 도형 object 3겹 -> 제어해줄건 HEALTH 와 관련된 것만 해주면댐
		g.setColor(Color.GRAY);
		g.fillRect(15, 15, 200, 32);
		g.setColor(new Color(75, greenValue, 0));
		g.fillRect(15, 15, HEALTH * 2, 32);
		g.setColor(Color.WHITE);
		g.drawRect(15, 15, 200, 32);
		
		// for up level --> score / --> spawn
		g.drawString("Score: "+ score, 10, 64);
		g.drawString("Level: "+ level, 10, 80);
	} // render()
	
	// Getter and Setter
	public void score(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
}

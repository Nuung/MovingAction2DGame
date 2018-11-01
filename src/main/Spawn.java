package main;

import java.util.Random;

// 특정 원하는 object를 Spawn (일정 텀또는 조건으로 스폰닝, object를 생성한다는 의미)  
// 조건이라면 'score --> level system' 을 통해서, 상위로 간다면, enemy를 spawn한다는 액션이 된다
public class Spawn {

	private Handler handler;
	private HUD hud;
	private Random r = new Random(); // spawn할때 위치 랜덤 위해
	
	// Score, lv 탐색 syste 위한 meber val
	private int scoreKeep = 0;
	
	public Spawn(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	} // 생성자
	
	public void tick() {
		scoreKeep++;
		
		if(scoreKeep >= 100) { // 점수가 1천점 넘으면
			this.scoreKeep = 0; // 점수 초기화 ( 임시 저장 변수 )
			// 현재 level 에서 +1 한 레벨로 
			hud.setLevel(hud.getLevel() + 1);
			// level에 따라 handler에 적 오브젝트 추가하자!
			if(hud.getLevel() == 2) {
				// - 50은 생성될때 Frame밖에서 생성되는 일 없도록 하기 위해 ( 안정빵 수치 )
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));
			} else if(hud.getLevel() == 3) {
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
			} else if(hud.getLevel() == 4) {
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
			} else if(hud.getLevel() == 5) {
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
			} else if(hud.getLevel() == 6) {
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
			}
			
			// inner if ~ else
		} // if
	} // tick()
	
}

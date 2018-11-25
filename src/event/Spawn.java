package event;

import java.util.Random;

import display.HUD;
import entity.enemy.BasicEnemy;
import entity.enemy.EnemyBoss;
import entity.enemy.FastEnemy;
import entity.enemy.HardEnemy;
import entity.enemy.LaserEnemy;
import entity.enemy.SmartEnemy;
import entity.item.Shield;
import main.Game;
import main.objecttype.Handler;
import main.objecttype.ID;

// 특정 원하는 object를 Spawn (일정 텀또는 조건으로 스폰닝, object를 생성한다는 의미)  
// 조건이라면 'score --> level system' 을 통해서, 상위로 간다면, enemy를 spawn한다는 액션이 된다
public class Spawn {

	private Game game;
	private Handler handler;
	private HUD hud;
	private Random r = new Random(); // spawn할때 위치 랜덤 위해
	private static final boolean horizontal = true, vertical = false; // horizontal 이 가로, vertical이 세로
	
	// Score, lv 탐색 syste 위한 meber val
	private int scoreKeep = 0;
	
	public Spawn(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	} // 생성자
	
	public void tick() {
		scoreKeep++;
		
		if(scoreKeep >= 250) { // 점수가 1천점 넘으면
			this.scoreKeep = 0; // 점수 초기화 ( 임시 저장 변수 )
			// 현재 level 에서 +1 한 레벨로 
			hud.setLevel(hud.getLevel() + 1);
			
			if(hud.getLevel() >= 15) {
				hud.setLevel(1);
			}
			
			// 게임 난이도에 따른 진행이 다른 spawn
			if(game.diff == 1) {
				inNormal(hud.getLevel());
			} 
			else if(game.diff == 2) {
				inHard(hud.getLevel());
			}			
			
		} // if
	} // tick()
	
	public void inNormal(int level) {
		// level에 따라 handler에 적 오브젝트 추가하자!
		if(level == 2) {
//			handler.itemSetting(); // item Setting / like shield .. -> handler class
			// - 50은 생성될때 Frame밖에서 생성되는 일 없도록 하기 위해 ( 안정빵 수치 )
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));
			makingLaser(horizontal); 	makingLaser(vertical); makingLaser(horizontal); makingLaser(vertical);
		} else if(level == 3) {
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));
		} else if(level == 4) {
			handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
		} else if(level == 5) {
			handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
		} else if(level == 6) {
			handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
			handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
		}else if(level == 7) {
			makingLaser(horizontal); 	makingLaser(vertical); makingLaser(horizontal); makingLaser(vertical);
		}else if(level == 10) {
			handler.clearEnemys();
			handler.addObject(new EnemyBoss((Game.WIDTH / 2) - 48, -120, ID.EnemyBoss, handler));
		} else if(level == 14) {
			makingLaser(horizontal); 	makingLaser(vertical); makingLaser(horizontal); makingLaser(vertical);
		}
	} // inNormal()
	
	public void inHard(int level) {
		// level에 따라 handler에 적 오브젝트 추가하자!
		if(level == 2) {
//			handler.itemSetting(); // item Setting / like shield .. -> handler class
			// - 50은 생성될때 Frame밖에서 생성되는 일 없도록 하기 위해 ( 안정빵 수치 )
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));
			makingLaser(horizontal); 	makingLaser(vertical); makingLaser(horizontal); makingLaser(vertical);
		} else if(level == 3) {
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));
		} else if(level == 4) {
			handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
			handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
		} else if(level == 5) {
			handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
		} else if(level == 6) {
			handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
			handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
		} else if(level == 7) {
			handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.HardEnemy, handler));
			makingLaser(horizontal); 	makingLaser(vertical); makingLaser(horizontal); makingLaser(vertical);
		} else if(level == 10) {
			handler.clearEnemys();
			handler.addObject(new EnemyBoss((Game.WIDTH / 2) - 48, -120, ID.EnemyBoss, handler));
			handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler));
		} else if(level == 14) {
			makingLaser(horizontal); 	makingLaser(vertical); makingLaser(horizontal); makingLaser(vertical);
		}
	} // inHard
	
	public void makingLaser(boolean isHorizontal) {
		if(isHorizontal) handler.addObject(new LaserEnemy(0, r.nextInt(Game.HEIGHT - 50), ID.LaserEnemy, handler, isHorizontal));
		else handler.addObject(new LaserEnemy(r.nextInt(Game.HEIGHT - 50), 0, ID.LaserEnemy, handler, isHorizontal));		
	} // makingLaser()
	
}

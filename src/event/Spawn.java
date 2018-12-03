package event;

import java.awt.Color;
import java.util.Random;

import display.HUD;
import entity.enemy.BasicEnemy;
import entity.enemy.EnemyBoss;
import entity.enemy.FastEnemy;
import entity.enemy.HardEnemy;
import entity.enemy.LaserEnemy;
import entity.enemy.SmartEnemy;
import entity.enemy.TsunamiEnemy;
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
			
			if(hud.getLevel() >= 16) {
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
			TsunamiEnemyEvent(true);
			makingLaser(horizontal); 	makingLaser(vertical); makingLaser(horizontal); makingLaser(vertical);
		} else if(level == 3) {
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));
			TsunamiEnemyEvent(false);
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
			TsunamiEnemyEvent(true);
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));
			makingLaser(horizontal); 	makingLaser(vertical); makingLaser(horizontal); makingLaser(vertical);
		} else if(level == 3) {
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler));
			TsunamiEnemyEvent(false);
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
	
	public void TsunamiEnemyEvent(Boolean isLeft) {
		// 여파인 Cyan 색의 파도들을 일정한 Blue파도 보다 더 왼쪽에 배치시키지만, 일정 사이 랜덤 값 왼쪽으로
		Random rnO = new Random();
		Random rnT = new Random();
		int makeNull = rnT.nextInt((15 - 0) +1);
		
		if(isLeft) { // 왼쪽에서 오른쪽으로 갈때
			for(int i = 0; i < 15; i ++) {
				// 피할수 있는 공간 만들어주기
				if(makeNull == i || makeNull == i +1 || makeNull == i +2 ) continue;
				
				handler.addObject(new TsunamiEnemy(128, 32*i, ID.TsunamiEnemy, handler, Color.BLUE, isLeft));
				handler.addObject(new TsunamiEnemy(rnO.nextInt((64 - 0) + 1), 32*i + 16, ID.TsunamiEnemy, handler, Color.CYAN, isLeft));
			} // for	
		}
		else { // 오른쪽에서 왼쪽으로 갈때
			for(int i = 0; i < 15; i ++) {
				// 피할수 있는 공간 만들어주기
				if(makeNull == i || makeNull == i +1 || makeNull == i +2 ) continue;
				
				handler.addObject(new TsunamiEnemy(Game.WIDTH - 128, 32*i, ID.TsunamiEnemy, handler, Color.BLUE, isLeft));
				handler.addObject(new TsunamiEnemy(Game.WIDTH - rnO.nextInt((64 - 0) + 1), 32*i + 16, ID.TsunamiEnemy, handler, Color.CYAN, isLeft));
			} // for	
		}

	} // TsunamiEnemyEvent()
	
	public void makingLaser(boolean isHorizontal) {
		if(isHorizontal) handler.addObject(new LaserEnemy(0, r.nextInt(Game.HEIGHT - 50), ID.LaserEnemy, handler, isHorizontal));
		else handler.addObject(new LaserEnemy(r.nextInt(Game.HEIGHT - 50), 0, ID.LaserEnemy, handler, isHorizontal));		
	} // makingLaser()
	
}

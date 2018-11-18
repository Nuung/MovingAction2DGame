package display.assets;

import java.awt.image.BufferedImage;

public class Assets {
	
	// 한 타일의 좌 우 크기
	private static final int width = 64, height = 64;
	
	// Tile, map Static entities
	public static BufferedImage leaderButton; 
	public static BufferedImage backG[];
	public static BufferedImage[] player_up, player_down, player_left, player_right;
	
	// Going to load everything in our game 
	public static void init() {
		backG = new BufferedImage[10];
		// Moving2DTileGame\res\textures 의 절대 경로 잊지 말기~
		SpriteSheet playersheet = new SpriteSheet(ImageLoader.loadImage("/textures/gaja.png"));
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/starbackground.png"));
		SpriteSheet leaderboard = new SpriteSheet(ImageLoader.loadImage("/textures/leaderboard.png"));
		
		leaderButton = leaderboard.crop(0, 0, 1600, 1600);
		
		//─────────────────────Player Animation Set!─────────────────────//
		player_up = new BufferedImage[4];
		player_left = new BufferedImage[4];
		player_down = new BufferedImage[4];
		player_right = new BufferedImage[4];
		
		for(int i=0; i<4; i++) {
			player_down[i] = playersheet.crop(i*width, 0, width, height);
			player_left[i] = playersheet.crop(i*width, width, width, height);
			player_right[i] = playersheet.crop(i*width, width*2, width, height);
			player_up[i] = playersheet.crop(i*width, width*3, width, height);
		}
		//────────────────────────────────────────────────────────────────//
		
		//──────────────────────BackGround Tile Set!──────────────────────//
		for(int i = 0; i < backG.length; i++) {
			backG[i] = sheet.crop(32*i, 20*i, width, height);
		}
		//─────────────────────────────────────────────────────────────────//
		
	} // init()
}

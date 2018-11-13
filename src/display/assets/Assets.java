package display.assets;

import java.awt.image.BufferedImage;

public class Assets {
	
	// 한 타일의 좌 우 크기
	private static final int width = 64, height = 64;
	
	public static BufferedImage testPlayer; // Tile, map Static entities
	public static BufferedImage backG[];
	
	// Going to load everything in our game 
	public static void init() {
		backG = new BufferedImage[10];
		// Moving2DTileGame\res\textures 의 절대 경로 잊지 말기~
		SpriteSheet sheetplayer = new SpriteSheet(ImageLoader.loadImage("/textures/gaja.png"));
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/starbackground.png"));
		
		/*
		player_up = new BufferedImage[9];
		player_left = new BufferedImage[9];
		player_down = new BufferedImage[9];
		player_right = new BufferedImage[9];
		*/
		
		testPlayer = sheetplayer.crop(width*0, height*3, width, height);
		for(int i = 0; i < backG.length; i++) {
			backG[i] = sheet.crop(32*i, 20*i, width, height);
		} // for - backG
		
	} // init()
}

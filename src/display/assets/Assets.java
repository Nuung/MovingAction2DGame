package display.assets;

import java.awt.image.BufferedImage;

public class Assets {
	
	// 한 타일의 좌 우 크기
	private static final int width = 64, height = 64;
	
	public static BufferedImage testPlayer, dirt, grass, stone, tree, rock; // Tile, map Static entities
	
	// Going to load everything in our game 
	public static void init() {
		
		// Moving2DTileGame\res\textures 의 절대 경로 잊지 말기~
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/test.png"));
		
		/*
		player_up = new BufferedImage[9];
		player_left = new BufferedImage[9];
		player_down = new BufferedImage[9];
		player_right = new BufferedImage[9];
		*/
		
		testPlayer = sheet.crop(width*1, height*0, width, height);
		dirt = sheet.crop(width*2, height*2, width, height);
//		grass = sheet.crop(width, height*2, width, height);
//		stone = sheet.crop(width * 5, height*3, width, height);
	}
}

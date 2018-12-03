import java.io.File;

import main.Game;

public class Launcher {
//	
//	public void init() { 
//		System.out.println("init!");
//    }
//	
//	 public void start() {
//		 System.out.println("Start!");
//			new Game(); // this object의 생성자를 호출 
//			Game.DBsetting();
//	 }
//	 
//	 public void stop() {
//		 System.out.println("Stop!");
//	 }
//	 public void paint(Graphics g) {
//		 setBackground(Color.lightGray);
//		 g.drawString("Hello Applet.", 50, 50);
//	}
//	 
	private static File backGroundMusic; // BGM Music file!
	
	public static void main(String args[]) {
		backGroundMusic = new File("res/music/Bgm.wav"); // BGM 
		event.PlaySound.PlaySoundFile(backGroundMusic); // BGM Setting
		
		new Game(); // this object의 생성자를 호출 
		Game.DBsetting();
	} // MAIN
}

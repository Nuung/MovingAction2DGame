package display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import main.Game;

public class Window extends Canvas {

	/**
	 https://docs.oracle.com/javase/6/docs/api/java/io/Serializable.html
	 JFrame 생성, Game Class의 game 오브젝트 넣고 윈도우 창 생성하는 역할
	 **/
	private static final long serialVersionUID = -4810618286807932601L;
	public static JFrame frame;
	
	public Window(int width, int height, String title, Game game) {
		frame = new JFrame(title);
		
		// 프레임 크기 조절
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null); // 프레임이 열리는 위치가 상대적인 위치가 안되도록
		frame.add(game);
		frame.setVisible(true);
		frame.pack();
		game.start();
		
	}
	
}

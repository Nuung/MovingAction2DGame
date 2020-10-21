package display;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import display.components.PlaceholderJPasswordField;
import display.components.PlaceholderJTextField;

public class LoginWindow extends JFrame {
	
	// member val
	private final int width = 300, height = 300;
	private JPanel p1;
	private PlaceholderJTextField jtext;
	private PlaceholderJPasswordField jpass;

	public LoginWindow() {
		
		// setting main panel
		this.p1 = new JPanel();
		this.p1 = this.panelSetting(p1);
		
		// Login Frame 기본 설정들
		this.setPreferredSize(new Dimension(width, height)); // Size setting
		this.setMaximumSize(new Dimension(width, height));
		this.setMinimumSize(new Dimension(width, height));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit setting
		this.setResizable(false); // no resize
		this.setLocationRelativeTo(null); // 프레임이 열리는 위치가 상대적인 위치가 안되도록
		this.add(this.p1);
		this.setVisible(true); // showing
	}
	
	private JPanel panelSetting(JPanel inpanel) {
		inpanel.setLayout(new GridLayout(3, 1));
		this.jtext = new PlaceholderJTextField();
		this.jpass = new PlaceholderJPasswordField();
		
		// inner JPanel Setting
		JPanel tempP = new JPanel();
		JButton login = new JButton("LOGIN");
		JButton signup = new JButton("SIGN UP");
		tempP.setLayout(new GridLayout(1, 2));
		tempP.add(login); 
		tempP.add(signup);
		
		// Input JPanel setting
		inpanel.add(jtext);
		inpanel.add(jpass);
		inpanel.add(tempP);
		return inpanel;
	}
	
	public static void main(String args[]) {
		new LoginWindow();
	}
	
}

package display;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import entity.GameObject;
import main.objecttype.Handler;
import main.objecttype.ID;

// 잔향 남기는 이펙트 object 추가하기! 모든object에 대해 해당하는 Trail obejct를 또 추가하면 됨
/*
 This class는 alpha와 DST 같은 영상 처리 기법이 들어간다. --> 이미지를 트래이싱하며 랜더링의 변조가 들어가고,
 그로 인해서 잔향이 남게 해주는 효과를 주는 것  AlphaComposite 라는 Class (liabrary에 존재함)알아야 함
 */
public class Trail extends GameObject {

	// member val
	private float alpha = 1;
	private Handler handler;
	private Color color; // object의 색깔 지정
	private int width, height; // object의 상하 좌우 크기 지정
	private float life;
	
	// life = 0.001 ~ 0.1
	
	public Trail(int x, int y, ID id, Color color, int width, int height, float life, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.color = color;
		this.width = width;
		this.height = height;
		this.life = life;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// alpha가 생명보다 크며, ( life는 고정값이 되어 부리는겨, 실질적 object life 저장 변수는 alpha여 )
		if(alpha > life) {
			// 알파의 값을 라이프에서 마이너스 한 값으로
			alpha -= life - 0.0001f;
		} else handler.removeObject(this); // life보다 alpha가 작으면, object의 피가 닳아서 없어진것임! remove object!
	} // tick()

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2d = (Graphics2D) g;
		
		// 외형적 구조 구현 / 생성자에서 초기화된 멤버변수 사용함	
		g2d.setComposite(makeTransparnet(alpha));
		g.setColor(color);
		g.fillRect(x, y, width, height);
		g2d.setComposite(makeTransparnet(1)); // after this, be going to a solid 1 alpha
	} // render()

	// object tracing 액션의 핵심부분
	// https://docs.oracle.com/javase/8/docs/api/java/awt/AlphaComposite.html 참조
	private AlphaComposite makeTransparnet(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return (AlphaComposite.getInstance(type, alpha));
	}
	
	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	} // getBounds()
	
} // Trail Class

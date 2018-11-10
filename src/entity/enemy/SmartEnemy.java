package entity.enemy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import display.Trail;
import entity.GameObject;
import main.Game;
import main.objecttype.Handler;
import main.objecttype.ID;

// GameObject가 abstract 임을 진가를 보이는 Class!
public class SmartEnemy extends GameObject {

	private Handler handler;
	private GameObject player;
	
	public SmartEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		// handler가 game 엔티티들에 대한 object를 들고 있다는 점을 명시해라
		// for문을 돌면서 player (ID값이 식별자니까) object를 찾는다. --> 멤버 변수로써 가져온다 
		// 참고로 player object는 '유일'하기 때문에 가능한 루프다
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
		} // for
		
		// 우리는 가져온 player object를 계속 따라가게 만들 것이다.
		
		valX = 4; valY = 4;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		this.x += valX;
		this.y += valY;
		
		// moving tracing (제작자와는 other way !)
		// this object (smartEnemy) 의 좌표값을 기준으로, player와 '방향'만 검색
		// 그 '방향'에 따라서만 서로 부호화 하고, 멈추면 멈추고~
		if(this.x>player.getX()) this.valX=-1;
		else if(this.x<player.getX()) this.valX=1;
		else if(this.x==player.getX()) this.valX=0;
		
		if(this.y>player.getY()) this.valY=-1;
		else if(this.y<player.getY()) this.valY=1;
		else if(this.y==player.getY()) this.valY=0;
		
		/* 아래는 병신 제작자가 int -> float 의 대 전쟁과 대 혼동을 일으킨 코드 그냥 위에 꺼로 넣는게 그냥 안심
		// -8 의 상수값이 얼마나 빠르게 따라 잡는지 결정하는 speed값이 된다
		float diffX = x - player.getX() - 8;
		float diffY = y - player.getY() - 8;
		// 점과 점 사이의 거리 구하는 공식임 : 루트 ( x1 - x2 ) ^ 2 + ( y1 - y2 ) ^ 2
		float distance = (float) Math.sqrt((x - player.getX())*(x - player.getX()) + (y - player.getY())*(y - player.getY()));
		
		valX = (int) ((-1.0/distance) * diffX);
		valY = (int) ((-1.0/distance) * diffY);
		*/
		
		// 프레임 크기 생각! --> 값이 커지면 부딫힌거임! --> 방향 바꿔주기
		// 0 일땐, 상위, 왼쪽 frame경계, Rectange의 윗면, 왼쪽 면 이 부딫힘 (Rectangle의 0, 0이 어딘지 생각해보기)
		// 하지만 하위, 오른쪽 frame 경계는 Rectange의 사이즈만큼 간 포인트 ( width, height 좌표지점에 )에 부딫힌다 그래서 32를 빼줌
		if(y <= 0 || y >= Game.HEIGHT - 30 ) valY = -valY;
		if(x <= 0 || x >= Game.WIDTH - 20 ) valX = -valX; // 실제 프레임의 크기가 정한 완벽한 정수값으로 안떨어짐,, 그래서 애매한 숫자를 사용
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.GREEN, 16, 16, 0.02f, handler));
	} // tick()

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.GREEN);
		g.fillRect(x, y, 16, 16);
	} // render()

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x, y, 16, 16);
	} // getBounds()

}

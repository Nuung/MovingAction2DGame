package main;

import java.awt.Graphics;

// 추상화 class -> 찍어내기만 하는 틀! / player / enemy / coin / etc... object 찍는 틀
public abstract class GameObject {
	
	// how does the game know if the object is a player or an enemy or coin ?! 
	// --> ID class ( class X, 멤버변수가 암묵적으로 차례대로 int 값을 가지지만 이름은 변수형태인 열거형 구조체 )
	// class의 보안성 (특히 유저자체를 다루는 class라) 위해 protected 접근 제어
	protected int x, y;
	protected ID id;
	protected int valX, valY; // object가 움직인다 -> x y 를 val x, y 값이랑 계속 바꿔준다 -> moving action
	
	// 생성자
	public GameObject(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;	
	}
	
	// 각 찍어내지는 object는 기본적으로 아래 메소드를 가져야 한다
	public abstract void tick();
	public abstract void render(Graphics g);

	// Getter and Setter
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}
	
	public int getValX() {
		return valX;
	}

	public void setValX(int valX) {
		this.valX = valX;
	}

	public int getValY() {
		return valY;
	}

	public void setValY(int valY) {
		this.valY = valY;
	}
	
}

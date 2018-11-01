package main;

// how does the game know if the object is a player or an enemy or coin ?! 
// --> ID class ( class X, 멤버변수가 암묵적으로 차례대로 int 값을 가지지만 이름은 변수형태인 열거형 구조체 )
public enum ID {
	
	// 각 object에게 특정 int 값을 준다
	Player(), // player는 플레이어
	Enemy(), // 기본적인 적군 지정
	FastEnemy(), // 빠른 적군
	SmartEnemy(), // AI
	Trail(); // tracing, 따라가는 잔향 이펙터
	
	
}

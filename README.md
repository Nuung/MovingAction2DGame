<div align = "center">
    <img src="https://img.shields.io/badge/license-MIT-green" />
    <img src="https://img.shields.io/badge/Java%20version-%3E%3D%20SE%2011-blue" />
</div>

# MovingAction2DGame

- 2018 세종 창의학습 공동체 창의 마루상(1등상) / Making 2D tile game with java that has moving action ◎ / Basic of 2D game rendering and ticking (processing)
- <h3>Making 2D tile game with java that has moving action ◎</br>
  Basic of 2D game rendering and ticking (processing)</h3>
- [Release Check!](https://github.com/Nuung/MovingAction2DGame/releases)

###

## In Game Photo

<div align = "center">
    <img src="https://github.com/Nuung/MovingAction2DGame/blob/master/res/textures/ingame_img1.png" width="50%" />
    <img src="https://github.com/Nuung/MovingAction2DGame/blob/master/res/textures/ingame_img2.png" width="50%" />
    <img src="https://github.com/Nuung/MovingAction2DGame/blob/master/res/textures/ingame_img3.png" width="50%" />
    <img src="https://github.com/Nuung/MovingAction2DGame/blob/master/res/textures/ingame_img4.png" width="50%" />
    <img src="https://github.com/Nuung/MovingAction2DGame/blob/master/res/textures/ingame_img5.png" width="50%" />
    <img src="https://github.com/Nuung/MovingAction2DGame/blob/master/res/textures/ingame_img6.png" width="50%" />
    <img src="https://github.com/Nuung/MovingAction2DGame/blob/master/res/textures/ingame_img7.png" width="50%" />
    <img src="https://github.com/Nuung/MovingAction2DGame/blob/master/res/textures/ingame_img8.png" width="50%" />
    <img src="https://github.com/Nuung/MovingAction2DGame/blob/master/res/textures/ingame_img9.png" width="50%" />
    <img src="https://github.com/Nuung/MovingAction2DGame/blob/master/res/textures/ingame_img10.png" width="50%" />
</div>


## Getting Started
```bash
git clone https://github.com/Nuung/MovingAction2DGame.git

IDE에서 import -> Launcher.java -> Run Java Application
```
- Java Build Path 만 아래와 같이 mysql 추가 해주면 스코어 까지 정상 작동 가능합니다.
- <img src="https://github.com/Nuung/MovingAction2DGame/blob/master/res/textures/readme_img2.png" />
- 추가 안해도 실행은 되지만 스코어는 이용할 수 없습니다. 기본적으로 localhost를 사용합니다. 그렇기 때문에 가볍게 Database와 table만 아래와 같이 만들어 줍니다.
  ~~~~sql
  CREATE DATABASE IF NOT EXISTS javadb CHARACTER SET utf8 COLLATE utf8_general_ci;
  DROP TABLE IF EXISTS gamescore;
  use javadb;
  CREATE TABLE gamescore (
    id  	   	 INTEGER NOT NULL PRIMARY KEY, 
    name 		 VARCHAR(20),
    score  	 INTEGER NOT NULL
  );
  select * from gamescore;
  insert into gamescore value (1, "HyeonWoo", 1300);
  ~~~~
- 세부 사항은 package database -> DBConfigue.java 에서 확인 가능합니다!

### Control

```
Move : ←↑↓→ or WASD
PAUSE : P
ATTACK : K
SHOP : SPACE BAR
```


## 프로젝트 설명 및 세부 정보


### PROLOG
- 추억의 [곰플레이어 닷지 게임](https://prolite.tistory.com/1388)을 조금 다른 방식으로, java만 사용해서 구현해 보고 싶었습니다. 
- Thread를 통한 FPS ~ 랜더링, 추상클래스와 인터페이스, Swing 컴포넌트들의 조합과 그 조합에 event를 추가하면서 클래스 구조를 설계하는 것이 핵심이고 학습의 메인입니다. 


### UML
- 우선 가장 상위, static main method는 아래 이미지와 같이 위치 합니다.
- <img src="https://github.com/Nuung/MovingAction2DGame/blob/master/res/textures/readme_img1.png" width="40%" />
- 전체 UML은 아래 이미지와 같습니다! 처음 보면 굉장히 복잡해 보이지만 단순합니다.
- <img src="https://github.com/Nuung/MovingAction2DGame/blob/master/DodgeTheDot_UML/DodgeTheDot_UML.png" />
- 핵심 적인 부분을 몇가지 짚어보면,
  - 싱글톤 처럼 Handler Class의 Object, **Vector<GameObject>** 에 다른 class object들이 add 된다! 그러면서 개별 object target대상으로 tick() method가 일어난다.
  - 그 Entity들은 모두 GameObject Type 이다! GameObject Class는 **abstract class** 다! 
  - Game Class에서는 Display를 핵심적으로 object를 다루고 **STATE**를 통해서 GameObject를 어떻게 할지 결정이 된다! 즉, Display state를 가지고 보여주는 랜더링만 바뀌는 것이다. 


### More Information
<details>
    <summary>창의학습공동체 프로그램 참여 결과물</summary>
    
- JAVA를 공부하는 소모임을 직접 만들어서 Doge the Dot이라는 이름으로 캐릭터를 조작해 사방에서 날라오는 여러 장애물을 피하는 게임을 만들었습니다. 
- Canvas와 JFrame 을 이용해 렌더링했고, 렌더링을 묶어주는 최상위 클래스에서 Runnable 인터페이스 상속으로 스레드를 만들어서 초당 60프레임으로 게임을 구현했습니다. 
- 기본적인 클래스의 구조는 검색을 통한 클론코딩으로 시작하고 조원들끼리 모여 여러 아이디어를 추가했습니다. 렌더링이 필요한 object들을 위해 추상클래스로 GameObject 클래스를 만들어서 상속받은 뒤 구현했습니다. 
- object 특성마다 **다른 추가 구현**이 필요했기 때문에 인터페이스가 아닌 **추상클래스** 로 하였으며, 적군 object의 경우 **무조건 구현**해야 하는 ‘플레이어 쪽으로 움직이기’, ‘충돌 상자’ 등을 위해 EnemyGroup 이라는 **인터페이스**를 통하여 상속받게 했습니다. 여기서 추상 클래스와 인터페이스의 차이점과 존재 이유를 알 수 있었습니다. 
- Canvas위에 플레이어 조작에 따라 달라지는 움직임을 렌더링하기 위한 이벤트 추가, 그리고 특정 메뉴 클릭할 때 새롭게 그려지고 다른 화면을 렌더링해야 할 때, 렌더링을 묶어주는 최상위 클래스에서 state를 나누어서 다른 화면을 지금 프레임에 다시 그리는 방법을 이용했습니다. 그리고 렌더링이 필요한 모든 object를 핸들러라는 클래스를 만들어서 리스트 자료구조에 모아두고 state에 따라 다르게 다시 그렸습니다. 핸들러 클래스는 public이지만 포함하는 object들은 private이라서 접근 제어로 볼 수 있는 보안의 기초를 알 수 있었습니다. 또, 이 과정들 속에서 클래스라는 틀을 만들고 객체를 찍어내고 관리하면서 객체지향이 무엇인지 알 수 있었습니다.
- 이렇게 조원들과 함께 열심히 노력한 만큼, 마지막 성과 보고회에서 성장한 모습을 많이 보일 수 있어서 대상이라는 좋은 성과를 이룰 수 있었습니다. 
    
</details> 


package snake_1_1;

import java.util.ArrayList;

class Snake
{
	boolean isRun;  //是否在运动
	ArrayList<Node> body;  //蛇体
	Node food;   //食物
	int derection; //方向
	int score;  // 分数
	int status;  // 状态
	int speed;  // 速度
	public static final int SLOW = 500;
	public static final int MID = 300;
	public static final int FAST = 100;
	public static final int RUNNING = 1;
	public static final int PAUSED = 2;
	public static final int GAMEOVER = 3;
	public static final int LEFT = 1;
	public static final int UP = 2;
	public static final int RIGHT = 3;
	public static final int DOWN = 4;
	
	public Snake()
	{
		speed = Snake.SLOW;
		score = 0;
		isRun = false;
		status = Snake.PAUSED;
		derection = Snake.RIGHT;
		body = new ArrayList<Node>();
		body.add(new Node(60,20));
		body.add(new Node(40,20));
		body.add(new Node(20,20));
		makeFood();
	}
	
	//判断食物是否被蛇吃掉
	//如果食物在蛇运行方向的正前方， 并且 与蛇头接触 ，则被吃掉
	private boolean isEaten()
	{
		Node head = body.get(0);
		if (derection == Snake.RIGHT && (head.x + Node.W) == food.x
				&& head.y == food.y)
			return true;
		else if(derection == Snake.LEFT && (head.x - Node.W) == food.x 
				&& head.y == food.y)
			return true;
		else if(derection == Snake.UP && (head.y - Node.H) == food.y 
				&& head.x == food.x)
			return true;
		else if(derection == Snake.DOWN && (head.y + Node.H) == food.y 
				&& head.x == food.x)
			return true;
		else
			return false;			
	}
	
	//是否碰撞
	public boolean isCollsion() 
	{
		Node head = body.get(0);
		
		//碰壁
		if(derection == Snake.RIGHT && head.x == 280)
			return true;
		else if(derection == Snake.UP && head.y == 0)
			return true;
		else if(derection == Snake.LEFT && head.x == 0)
			return true;
		else if(derection == Snake.DOWN && head.y == 380)
			return true;
		
		//蛇头碰到身体
		Node temp = null;
		int i;
		for(i =3; i < body.size(); i++)
		{
			temp = body.get(i);
			if (temp.x == head.x && temp.y == head.y)
				return true;
		}
		if(i < body.size())
			return true;
		return false;
	}
	
	//在随机的地方产生食物
	public void makeFood()
	{
		boolean isInBody = true;
		int ax = 0, ay = 0;
		int X = 0, Y = 0;
		while(isInBody)
		{
			isInBody = false;
			ax = (int)(Math.random() * 15);
			ay = (int)(Math.random() * 20);
			X = ax*Node.W;
			Y = ax*Node.H;
			for(Node temp :body)
			{
				if(X== temp.x && Y == temp.y)
					isInBody = true;
			}
		}
		food = new Node( X, Y); 
	}
	
	//改变运行方向
	public void changeDerection(int newDer)
	{
		if(derection % 2 !=  newDer % 2)      //如果与原来方向相同或相反， 则无法改变
		{
			derection = newDer;
		}
	}
	
	public void move()
	{
		if(isEaten())    // 如果食物被吃掉
		{
			body.add(0,food);  // 把食物当成蛇头成为新的蛇体
			score += 10;
			makeFood();
		}
		else if(isCollsion())
		{
			isRun = false;
			status = Snake.GAMEOVER;	//结束
		}
		else if(isRun)	//正常运行 （ 不吃食物，不碰壁， 不碰到蛇身
		{
			Node head = body.get(0);
			int X = head.x;
			int Y = head.y;
			switch(derection)
			{
			case RIGHT:
				X += Node.W;
				break;
			case LEFT:
				X -= Node.W;
				break;
			case UP:
				Y -= Node.H;
				break;
			case DOWN:
				Y += Node.H;
				break;
			}
			body.add(0,new Node(X,Y));
			body.remove(body.size()-1);   //去掉蛇尾
		}
	}
}


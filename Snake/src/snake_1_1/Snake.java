package snake_1_1;

import java.util.ArrayList;

class Snake
{
	boolean isRun;  //�Ƿ����˶�
	ArrayList<Node> body;  //����
	Node food;   //ʳ��
	int derection; //����
	int score;  // ����
	int status;  // ״̬
	int speed;  // �ٶ�
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
	
	//�ж�ʳ���Ƿ��߳Ե�
	//���ʳ���������з������ǰ���� ���� ����ͷ�Ӵ� ���򱻳Ե�
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
	
	//�Ƿ���ײ
	public boolean isCollsion() 
	{
		Node head = body.get(0);
		
		//����
		if(derection == Snake.RIGHT && head.x == 280)
			return true;
		else if(derection == Snake.UP && head.y == 0)
			return true;
		else if(derection == Snake.LEFT && head.x == 0)
			return true;
		else if(derection == Snake.DOWN && head.y == 380)
			return true;
		
		//��ͷ��������
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
	
	//������ĵط�����ʳ��
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
	
	//�ı����з���
	public void changeDerection(int newDer)
	{
		if(derection % 2 !=  newDer % 2)      //�����ԭ��������ͬ���෴�� ���޷��ı�
		{
			derection = newDer;
		}
	}
	
	public void move()
	{
		if(isEaten())    // ���ʳ�ﱻ�Ե�
		{
			body.add(0,food);  // ��ʳ�ﵱ����ͷ��Ϊ�µ�����
			score += 10;
			makeFood();
		}
		else if(isCollsion())
		{
			isRun = false;
			status = Snake.GAMEOVER;	//����
		}
		else if(isRun)	//�������� �� ����ʳ������ڣ� ����������
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
			body.remove(body.size()-1);   //ȥ����β
		}
	}
}


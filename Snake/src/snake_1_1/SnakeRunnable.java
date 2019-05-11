package snake_1_1;

import javax.swing.JComponent;

//蛇运动以及记录分数的线程
class SnakeRunnable implements Runnable
{
	private Snake snake;
	private JComponent component;
	int i = 0;
	
	public SnakeRunnable(Snake snake, JComponent component)
	{
		this.snake = snake;
		this.component = component;
	}
	public void run()
	{
		while(true)
		{
			try {
				snake.move();
				component.repaint();
				Thread.sleep(snake.speed);
			}catch(Exception e) {}
		}
	}
}

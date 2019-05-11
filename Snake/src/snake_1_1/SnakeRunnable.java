package snake_1_1;

import javax.swing.JComponent;

//���˶��Լ���¼�������߳�
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

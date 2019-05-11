package snake_1_1;

import javax.swing.JLabel;

//记录状态的线程
class StatusRunnable implements Runnable
{
	private JLabel statusLabel;
	private JLabel scoreLabel;
	private Snake snake;
	
	public StatusRunnable(Snake snake, JLabel statusLabel, JLabel scoreLabel)
	{
		this.statusLabel = statusLabel;
		this.scoreLabel = scoreLabel;
		this.snake = snake;
	}
	
	public void run()
	{
		String sta = "";
		String spe = "";
		
		while(true)
		{
			switch(snake.status)
			{
			case Snake.RUNNING:
				sta = "running";
				break;
			case Snake.PAUSED:
				sta = "Paused";
				break;
			case Snake.GAMEOVER:
				sta = "GameOver";
				break;
			}
			statusLabel.setText(sta);
			scoreLabel.setText("" + snake.score);
			try {
				Thread.sleep(100);
			}catch(Exception e) {}	 		
		}
	}
}
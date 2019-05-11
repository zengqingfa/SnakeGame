package snake_1_1;

import java.awt.Color;
import java.awt.Component;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class SnakeFrame extends JFrame
{
	private JLabel statusLabel;
	private JLabel speedLabel;
	private JLabel scoreLabel;
	private JPanel snakePanel;
	private Snake snake;
	private JMenuBar bar;		//菜单栏
	private JMenu gameMenu;		
	private JMenu helpMenu;
	private JMenu speedMenu;
	private JMenuItem newItem;
	private JMenuItem pauseItem;
	private JMenuItem beginItem;
	private JMenuItem aboutItem;		//没用上
	private JMenuItem slowItem;
	private JMenuItem midItem;
	private JMenuItem fastItem;
	
	public SnakeFrame()
	{
		init();
		ActionListener actionListener = event ->
		{
			if(event.getSource() == pauseItem)
				snake.isRun = false;
			else if(event.getSource() == beginItem)
				snake.isRun = true;
			else if(event.getSource() == newItem)
				newGame();
			//菜单控制运行速度
			else if(event.getSource() == slowItem)
			{
				snake.speed = Snake.SLOW;
				speedLabel.setText("slow");
			}
			else if(event.getSource() == midItem)
			{
				snake.speed = Snake.MID;
				speedLabel.setText("mid");
			}
			else if(event.getSource() == fastItem)
			{
				snake.speed = Snake.FAST;
				speedLabel.setText("fast");
			}
		};
		
		pauseItem.addActionListener(actionListener);
		beginItem.addActionListener(actionListener);
		newItem.addActionListener(actionListener);
		slowItem.addActionListener(actionListener);
		midItem.addActionListener(actionListener);
		fastItem.addActionListener(actionListener);
		aboutItem.addActionListener(actionListener);
		
		addKeyListener(new KeyAdapter()
			{
				public void keyPressed(KeyEvent event)
				{
					switch(event.getKeyCode())
					{
					//方向键改变蛇运行的方向
					case KeyEvent.VK_DOWN:
						snake.changeDerection(Snake.DOWN);
						break;
					case KeyEvent.VK_UP:
						snake.changeDerection(Snake.UP);
						break;
					case KeyEvent.VK_LEFT:
						snake.changeDerection(Snake.LEFT);
						break;
					case KeyEvent.VK_RIGHT:
						snake.changeDerection(Snake.RIGHT);
						break;
					case KeyEvent.VK_SPACE:
						if(snake.isRun == true)
						{
							snake.isRun = false;
							snake.status = Snake.PAUSED;
							break;
						}
						else
						{
							snake.isRun = true;
							snake.status = Snake.RUNNING;
							break;
						}
					}
				}
			});
		
	}
	private void init()
	{
		snake = new Snake();
		setSize(380,460);
		setLayout(null);			//清空布局管理器
		this.setResizable(false);
		
		bar = new JMenuBar();
		
		gameMenu = new JMenu("Game");		
		gameMenu.add((newItem = new JMenuItem("New Game")));
		gameMenu.add((pauseItem = new JMenuItem("Pause")));
		gameMenu.add((beginItem = new JMenuItem("continue")));
		
		helpMenu = new JMenu("help");		
		helpMenu.add((aboutItem = new JMenuItem("about")));
		
		speedMenu = new JMenu("speed");
		speedMenu.add(( slowItem = new JMenuItem("slow")));
		speedMenu.add(( midItem  = new JMenuItem("mid")));
		speedMenu.add(( fastItem = new JMenuItem("fast")));
		
		bar.add(gameMenu);
		bar.add(helpMenu);
		bar.add(speedMenu);
		setJMenuBar(bar);
		
		statusLabel = new JLabel();
		speedLabel = new JLabel();
		scoreLabel = new JLabel();
		snakePanel = new JPanel();
		
		snakePanel.setBounds(0, 0, 300, 400);	   // 从左上角(0,0)开始 ,大小 300 * 400	
		snakePanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		add(snakePanel);
		statusLabel.setBounds(310,25,60,20);
		add(statusLabel);
		speedLabel.setBounds(310, 75, 60, 20);
		add(speedLabel);
		scoreLabel.setBounds(310, 125, 60, 20);
		add(scoreLabel);
		
		JLabel temp = new JLabel("状态");
		temp.setBounds(310, 5, 60, 20);
		add(temp);
		temp = new JLabel("速度");
		temp.setBounds(310,55,60,20);
		add(temp);
		temp = new JLabel("分数");
		temp.setBounds(310, 105, 60, 20);
		add(temp);			
	}
	private void newGame()
	{
		this.remove(snakePanel);
		this.remove(statusLabel);
		this.remove(scoreLabel);
		speedLabel.setText("slow");
		statusLabel = new JLabel();
		scoreLabel = new JLabel();
		snakePanel = new SnakePanel(snake);
		snakePanel.setBounds(0,0,300,400);
		snakePanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));  //加个边框
		
		Runnable r1 = new SnakeRunnable(snake,snakePanel);
		Runnable r2 = new StatusRunnable(snake,statusLabel,scoreLabel);
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start();
		t2.start();
		add(snakePanel);
		statusLabel.setBounds(310,25,60,20);
		add(statusLabel);
		scoreLabel.setBounds(310,125,60,20);
		add(scoreLabel);
	}
}

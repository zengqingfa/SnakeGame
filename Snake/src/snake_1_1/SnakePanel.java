package snake_1_1;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

//»­°å
public class SnakePanel extends JPanel
{
	Snake snake;
	public SnakePanel(Snake snake)
	{
		this.snake = snake;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for(Node node : snake.body)
		{
			g.setColor(Color.BLACK);
			g.fillRect(node.x, node.y, Node.W,Node.H);
		}
		Node food = snake.food;
		g.setColor(Color.RED);
		g.fillRect(food.x,food.y,Node.W, Node.H);
	}
}

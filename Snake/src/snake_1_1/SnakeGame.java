package snake_1_1;

import java.awt.EventQueue;
import javax.swing.JFrame;
/**
 * 
 * @version 1.1 2019-5-11
 * @author ClancyZeng
 *
 */

public class SnakeGame
{
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(()->
		{
			SnakeFrame frame = new SnakeFrame();
			frame.setTitle("Ã∞≥‘…ﬂ");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}








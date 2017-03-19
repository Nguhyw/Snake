package com.nguhyw.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.nguhyw.entities.Food;
import com.nguhyw.entities.Ground;
import com.nguhyw.entities.Snake;
import com.nguhyw.util.Global;

@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	
	private Snake snake;
	private Food  food;
	private Ground ground;

	public void display(Snake snake, Food food, Ground ground){
		System.out.println("gamepanel's display");
		
		this.snake = snake;
		this.food = food;
		this.ground = ground;
		
		this.repaint();
		
	}

	/* （非 Javadoc）
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		// TODO 自动生成的方法存根
		//重新显示
		
		g.setColor(new Color(Global.BACKGROUND_COLOR));
		g.fill3DRect(0, 0, Global.CELL_SIZE * Global.WIDTH, Global.CELL_SIZE * Global.HEIGHT, true);
		if((snake != null) && (food != null) && (ground != null)){
			
			snake.drawMe(g);
			food.DrawMe(g);
			ground.drawMe(g);
			
		}
		
	}
	
	
	
	
}

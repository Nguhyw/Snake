package com.nguhyw.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import com.nguhyw.controller.Controller;
import com.nguhyw.entities.Food;
import com.nguhyw.entities.Ground;
import com.nguhyw.entities.Snake;
import com.nguhyw.util.Global;
import com.nguhyw.view.GamePanel;
import com.nguhyw.view.OptionPanel;

public class Game{
	private OptionPanel option;
	private JFrame frame;
	private Snake snake;
	private Food food;
	private Ground ground;
	private GamePanel gamepanel;
	private Controller controller;
	
	public static void main(String[] args) {
		new Game();
	}
	
	public Game() {
		snake = new Snake();
		food = new Food();
		ground = new Ground();
		gamepanel = new GamePanel();
		frame = new JFrame();
		
		controller = new Controller(snake, food, ground, gamepanel);
		option = new OptionPanel(ground, frame, controller);
		
		controller.setOption(option);	//将OptionPanel的引用传递给controller，以便在controller中控制速度和分数的显示。
		
		gamepanel.setSize(Global.CELL_SIZE*Global.WIDTH, Global.CELL_SIZE*Global.HEIGHT);
		gamepanel.setFocusable(true);
		gamepanel.addKeyListener(controller);
		
		
		frame.setTitle("Nguhyw版贪吃蛇");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setSize(Global.CELL_SIZE*Global.WIDTH + 15, Global.CELL_SIZE*Global.HEIGHT + 40+79);
		frame.add(gamepanel,BorderLayout.CENTER);
		frame.setFocusable(true);
		snake.addSnakeListener(controller);
		frame.addKeyListener(controller);
		frame.add(option,BorderLayout.SOUTH);
		option.addKeyListener(controller);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();  
		frame.setLocation((dim.width - frame.getWidth())/2, (dim.height - frame.getHeight())/2);
		
		frame.setVisible(true);

	}


}

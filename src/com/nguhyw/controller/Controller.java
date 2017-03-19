package com.nguhyw.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import com.nguhyw.entities.Food;
import com.nguhyw.entities.Ground;
import com.nguhyw.entities.Snake;
import com.nguhyw.listener.SnakeListener;
import com.nguhyw.view.GamePanel;
import com.nguhyw.view.OptionPanel;

public class Controller extends KeyAdapter implements SnakeListener {

	private Snake snake;
	private Food food;
	private Ground ground;
	private GamePanel gamepanel;
	private OptionPanel option;

	public Controller(Snake snake, Food food, Ground ground, GamePanel gamepanel) {
		super();
		this.snake = snake;
		this.food = food;
		this.ground = ground;
		this.gamepanel = gamepanel;
	}
	
	public void setOption(OptionPanel option){
		this.option =  option;
	}

	/* （非 Javadoc）
	 * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO 自动生成的方法存根
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			snake.changeDirection(Snake.UP);
			break;
		case KeyEvent.VK_DOWN:
			snake.changeDirection(Snake.DOWN);
			break;
		case KeyEvent.VK_LEFT:
			snake.changeDirection(Snake.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			snake.changeDirection(Snake.RIGHT);
			break;
		default:
			break;
		}
	}


	@Override
	public void SnakeMove(Snake snake) {
		// TODO 自动生成的方法存根
		
		if(food.isSnakeEatFood(snake)){	//判断是否吃到食物
			snake.eatFood();	//执行蛇吃到食物的函数
			food.newFood(ground.getPosition(snake));	//产生一个新的食物
			snake.setScorl(snake.getScorl()+1);	//蛇的分数加1
		}
		if(snake.isEatBody()||ground.isSnakeEatRock(snake)){	//判断蛇是否吃到石头或自己
			gameOver();
		}
		option.getJLabSpeed().setText(550-snake.getSpeed()+"km/h");	//显示速度
		option.getJLabScorl().setText(snake.getScorl()+" ");		//显示分数
		gamepanel.display(snake, food, ground);						//更新蛇，食物，石头的位置
	}
	
	/*
	 * 开始新的游戏
	 */
	public void newGame(){
		
		food.newFood(ground.getPosition(snake));
		snake.newGame();
		
	}
	/*
	 * 开始或暂停游戏
	 */
	public boolean continueGame(){
		if(snake.getLife()){
			System.out.println("snake die");
			snake.setLife(false);
			return false;
		}
		else{
			System.out.println("snake life");
			snake.setLife(true);
			return true;
		}
	}
	
	/*
	 * 游戏结束计算分数和弹出提示框
	 */
	public void gameOver(){
		int scorl = snake.getBody().size();
		snake.Stop();
		System.out.println("Game Over!");
		JOptionPane.showMessageDialog(gamepanel, "SB游戏结束了!\n你的得分是："+scorl+"分","友情提示",JOptionPane.WARNING_MESSAGE);
	}
	
	
}

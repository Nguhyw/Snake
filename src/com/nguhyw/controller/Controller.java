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

	/* ���� Javadoc��
	 * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO �Զ����ɵķ������
		
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
		// TODO �Զ����ɵķ������
		
		if(food.isSnakeEatFood(snake)){	//�ж��Ƿ�Ե�ʳ��
			snake.eatFood();	//ִ���߳Ե�ʳ��ĺ���
			food.newFood(ground.getPosition(snake));	//����һ���µ�ʳ��
			snake.setScorl(snake.getScorl()+1);	//�ߵķ�����1
		}
		if(snake.isEatBody()||ground.isSnakeEatRock(snake)){	//�ж����Ƿ�Ե�ʯͷ���Լ�
			gameOver();
		}
		option.getJLabSpeed().setText(550-snake.getSpeed()+"km/h");	//��ʾ�ٶ�
		option.getJLabScorl().setText(snake.getScorl()+" ");		//��ʾ����
		gamepanel.display(snake, food, ground);						//�����ߣ�ʳ�ʯͷ��λ��
	}
	
	/*
	 * ��ʼ�µ���Ϸ
	 */
	public void newGame(){
		
		food.newFood(ground.getPosition(snake));
		snake.newGame();
		
	}
	/*
	 * ��ʼ����ͣ��Ϸ
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
	 * ��Ϸ������������͵�����ʾ��
	 */
	public void gameOver(){
		int scorl = snake.getBody().size();
		snake.Stop();
		System.out.println("Game Over!");
		JOptionPane.showMessageDialog(gamepanel, "SB��Ϸ������!\n��ĵ÷��ǣ�"+scorl+"��","������ʾ",JOptionPane.WARNING_MESSAGE);
	}
	
	
}

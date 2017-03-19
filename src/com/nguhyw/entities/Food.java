package com.nguhyw.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import com.nguhyw.util.Global;

public class Food {
	
	private Point point;
	
	public Food() {
		// TODO �Զ����ɵĹ��캯�����
		point = new Point();
	}
	
	/*
	 * �ж����Ƿ�Ե���ʳ��
	 */
	public boolean isSnakeEatFood(Snake snake){
		System.out.println("food's snake is eat food");
		
		Point p = snake.getHead();
		if(p.equals(point)){
			return true;
		}
		
		return false;
	}
	
	/*
	 * ����һ���µ�ʳ��
	 */
	public void newFood(Point point){
		this.point = point;
	}
	
	/*
	 * ��ʾʳ���λ��
	 */
	public void DrawMe(Graphics g){
		System.out.println("Food's draw me");
		
		g.setColor(new Color(Global.FOOD_COLOR));
		g.fill3DRect(point.x * Global.CELL_SIZE, point.y * Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
	}
}

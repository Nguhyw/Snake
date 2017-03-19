package com.nguhyw.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import com.nguhyw.util.Global;

public class Food {
	
	private Point point;
	
	public Food() {
		// TODO 自动生成的构造函数存根
		point = new Point();
	}
	
	/*
	 * 判断蛇是否吃到了食物
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
	 * 生成一个新的食物
	 */
	public void newFood(Point point){
		this.point = point;
	}
	
	/*
	 * 显示食物的位置
	 */
	public void DrawMe(Graphics g){
		System.out.println("Food's draw me");
		
		g.setColor(new Color(Global.FOOD_COLOR));
		g.fill3DRect(point.x * Global.CELL_SIZE, point.y * Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
	}
}

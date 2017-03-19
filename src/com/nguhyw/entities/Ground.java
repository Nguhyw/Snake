package com.nguhyw.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;
import java.util.Random;

import com.nguhyw.util.Global;

public class Ground {
	
	private int GroundMapNumber;
	private int[][] rock = new int[Global.WIDTH][Global.HEIGHT];
	
	public Ground() {
		// TODO 自动生成的构造函数存根
		//给游戏的边界都加上石头
		GroundMapNumber = 1;
		GroundMap();
		GroundMapNumber++;
	}
	
	/*
	 * 生成地图,总共有三个地图.
	 */
	public void GroundMap(){
		for(int i=0;i<Global.WIDTH;i++)
		{
			for(int j=0;j<Global.HEIGHT;j++)
			{
				rock[i][j] = 0;
			}
		}
		switch(GroundMapNumber){
		case 1:
			for(int x=0; x < Global.WIDTH; x++){
				rock[x][0] = 1;
				rock[x][Global.HEIGHT-1] = 1;
			}
			for(int y=0; y<Global.HEIGHT; y++){
				rock[0][y] = 1;
				rock[Global.WIDTH-1][y] = 1;
			}
			break;
		case 2:
			for(int x= Global.HEIGHT/4; x < (Global.HEIGHT*3)/4; x++){
				rock[Global.WIDTH/3][x] = 1;
				rock[(Global.WIDTH*2)/3][x] = 1;
			}
			break;
		case 3:
			for(int x= Global.WIDTH/4; x < (Global.WIDTH*3)/4; x++){
				rock[x][Global.HEIGHT/3] = 1;
				rock[x][(Global.HEIGHT*2)/3] = 1;
			}
			break;
		}
		
		
	}
	
	//获取食物的位置，要求不能和石头还有蛇身重叠
	public Point getPosition(Snake snake ){
		
		int[][] tempRock = new int[Global.WIDTH][Global.HEIGHT];
		LinkedList<Point> body;
		int x = 0;
		int y = 0;
		
		//将石头的位置复制到temprock中
		for(int i=0;i<Global.WIDTH;i++)
		{
			for(int j=0; j<Global.HEIGHT; j++)
			{
				tempRock[i][j] = rock[i][j];
			}
		}
		//获取蛇身的位置，依次将蛇身所在的位置，转化为石头的位置。这样就可以避免蛇身或者石头上出现食物的情况。
		body = snake.getBody();
		for (Point p : body) {
			tempRock[p.x][p.y] = 1;
		}
		//随机生成食物的位置，直到不和石头的位置重叠为止
		do{
			Random rm = new Random();
		
			x = rm.nextInt(Global.WIDTH);
			y = rm.nextInt(Global.HEIGHT);
		}while(tempRock[x][y] == 1);

		return new Point(x,y);
	}
	
	//判断蛇是否迟到了石头，如果迟到了石头就终止游戏
	public boolean isSnakeEatRock(Snake snake){
		System.out.println("ground's sanke is eat rock");
		
		for(int x=0; x < Global.WIDTH; x++){
			for(int y=0; y<Global.HEIGHT; y++){
				if((rock[x][y] == 1) && (snake.getHead().equals(new Point(x,y)))){
					return true;
				}
			}
		}
		
		return false;
	}
	
	
	//显示背景，将石头画出来
	public void drawMe(Graphics g){
		System.out.println("ground's draw me");
		g.setColor(new Color(Global.GROUND_COLOR));
		for(int x=0; x < Global.WIDTH; x++){
			for(int y=0; y<Global.HEIGHT; y++){
				if(rock[x][y] == 1)
					g.fill3DRect(x * Global.CELL_SIZE, y* Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
			}
		}
	}
	
	/*
	 * 切换地图
	 */
	public int switchGround(){
		GroundMap();
		GroundMapNumber++;
		if(GroundMapNumber>3)
		{
			GroundMapNumber = 1;
		}
		return GroundMapNumber;
	}
}

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
		// TODO �Զ����ɵĹ��캯�����
		//����Ϸ�ı߽綼����ʯͷ
		GroundMapNumber = 1;
		GroundMap();
		GroundMapNumber++;
	}
	
	/*
	 * ���ɵ�ͼ,�ܹ���������ͼ.
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
	
	//��ȡʳ���λ�ã�Ҫ���ܺ�ʯͷ���������ص�
	public Point getPosition(Snake snake ){
		
		int[][] tempRock = new int[Global.WIDTH][Global.HEIGHT];
		LinkedList<Point> body;
		int x = 0;
		int y = 0;
		
		//��ʯͷ��λ�ø��Ƶ�temprock��
		for(int i=0;i<Global.WIDTH;i++)
		{
			for(int j=0; j<Global.HEIGHT; j++)
			{
				tempRock[i][j] = rock[i][j];
			}
		}
		//��ȡ�����λ�ã����ν��������ڵ�λ�ã�ת��Ϊʯͷ��λ�á������Ϳ��Ա����������ʯͷ�ϳ���ʳ��������
		body = snake.getBody();
		for (Point p : body) {
			tempRock[p.x][p.y] = 1;
		}
		//�������ʳ���λ�ã�ֱ������ʯͷ��λ���ص�Ϊֹ
		do{
			Random rm = new Random();
		
			x = rm.nextInt(Global.WIDTH);
			y = rm.nextInt(Global.HEIGHT);
		}while(tempRock[x][y] == 1);

		return new Point(x,y);
	}
	
	//�ж����Ƿ�ٵ���ʯͷ������ٵ���ʯͷ����ֹ��Ϸ
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
	
	
	//��ʾ��������ʯͷ������
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
	 * �л���ͼ
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

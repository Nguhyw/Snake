package com.nguhyw.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import com.nguhyw.listener.SnakeListener;
import com.nguhyw.util.Global;

public class Snake {
	
	public static final int UP = -1;	//方向上
	public static final int DOWN = 1;	//方向下
	public static final int LEFT = -2;	//方向左
	public static final int RIGHT = 2;	//方向右
	
	private int OldDirection ,Direction;	//旧的方向和新的方向，通过判断新旧方向可以判断出当前操作的有效性。
	private boolean Life;					//蛇的生命，通过他可以控制游戏的暂停和开始
	private boolean ThreadIsStart = false;	//记录是否已经开启了驱动蛇移动的线程
	private int Scorl;						//分数，即蛇身的长度
	private Point LastTail = new Point();	//保存蛇尾
	
	private LinkedList<Point> body = new LinkedList<Point>();	//保存蛇身体所在的坐标
	private Set<SnakeListener> listeners = new HashSet<SnakeListener>();	//保存所有已注册的监听器
	
	public int getScorl() {
		return Scorl;
	}
	
	public void setScorl(int Scorl){
		if(Scorl<10000)
			this.Scorl = Scorl;
		System.out.println("外星人来了");
	}
	
	public boolean getLife() {
		return Life;
	}

	public void setLife(boolean Life) {
		this.Life = Life;
	}
	
	public LinkedList<Point> getBody() {
		
		return body;
	}
	
	public void setBody(LinkedList<Point> body) {
		this.body = body;
	}

	public Snake() {
		// TODO 自动生成的构造函数存根
	}
	
	public void init(){
		
		int x = Global.WIDTH/2;
		int y = Global.HEIGHT/2;
		
		Scorl = 0;
		while(!body.isEmpty()){
			body.removeFirst();
		}
		
		for(int i = 0; i<3; i++){
			Scorl++;
			body.addLast(new Point(x-i,y));
		}
		
		OldDirection = RIGHT;
		Direction = RIGHT;
		Life = false;
		
	}
	
	/*
	 * 蛇移动的方法
	 */
	public void move(){
		System.out.println("snake move");
		
		if(OldDirection + Direction != 0){
			OldDirection = Direction;
		}
		
		//1. 去尾
		LastTail = body.removeLast();
		
		int x = body.getFirst().x;
		int y = body.getFirst().y;
		
		switch(OldDirection){
		case UP:
			y--;
			if(y<0){
				y = Global.HEIGHT - 1;
			}
			break;
		case DOWN:
			y++;
			if(y >= Global.HEIGHT){
				y = 0;
			}
			break;
		case LEFT:
			x--;
			if(x<0){
				x = Global.WIDTH -1 ;
			}
			break;
		case RIGHT:
			x++;
			if(x >= Global.WIDTH ){
				x =  0;
			}
			break;
		}
		
		Point p = new Point(x, y);
		
		//2. 加头
		body.addFirst(p);
	}
	
	/*
	 * 蛇改变移动方向
	 */
	public void changeDirection(int Direction){
		System.out.println("sanke change direction");
		
		this.Direction = Direction;
	}
	
	/*
	 * 蛇吃到食物，身体加长
	 */
	public void eatFood(){
		System.out.println("sanke eat food");
		body.addLast(LastTail);		
	}
	
	/*
	 * 判断蛇是否吃到了自己
	 */
	public boolean isEatBody(){
		
		for (int i=1;i< body.size();i++) {
			if(body.getFirst().equals(body.get(i))){
				return true;
			}
		}
		
		return false;
	}
	
	/*
	 * 显示蛇身
	 */
	public void drawMe(Graphics g){
		System.out.println("snake draw me");
		g.setColor(new Color(Global.SNAKE_COLOR));
		for (Point point : body) {
			g.fill3DRect(point.x * Global.CELL_SIZE , point.y * Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
		}
		
	}
	
	/*
	 * 此处通过线程驱动蛇移动，然后历遍监听器的函数然后执行。
	 */
	private class SnakeDriver extends Thread{

		/* （非 Javadoc）
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			// TODO 自动生成的方法存根
			while(true){
				while(Life)
				{
					move();
					for (SnakeListener l : listeners) {
						l.SnakeMove(Snake.this);
					}
					try {
						sleep(getSpeed());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}	
				try {
					sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	/*
	 * 蛇开始移动
	 */
	public void Start(){
		if(!ThreadIsStart){
			ThreadIsStart = true;
			new Thread(new SnakeDriver()).start();
		}
		Life = true;
	}
	
	/*
	 * 蛇暂停移动
	 */
	public void Stop(){
		Life = false;
	}
	
	/*
	 * 注册监听器
	 */
	public void addSnakeListener(SnakeListener l){
		if(l!=null)
			listeners.add(l);
	}
	
	/*
	 * 获取蛇的头部
	 */
	public Point getHead(){
		
		return body.getFirst();
	}
	
	/*
	 * 获取蛇的速度
	 */
	public int getSpeed(){
		int length = body.size();
		int speed = 500;
		if(length < 10)
		{
			speed = 500;
		}
		else if(length < 20){
			speed = 300;
		}
		else if(length < 30){
			speed = 100;
		}
		else if(length < 40){
			speed = 50;
		}
		
		return speed;
	}
	
	/*
	 * 开启新的游戏
	 */
	public void newGame(){
		init();
		Start();
	}
}

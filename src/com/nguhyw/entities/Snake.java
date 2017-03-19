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
	
	public static final int UP = -1;	//������
	public static final int DOWN = 1;	//������
	public static final int LEFT = -2;	//������
	public static final int RIGHT = 2;	//������
	
	private int OldDirection ,Direction;	//�ɵķ�����µķ���ͨ���ж��¾ɷ�������жϳ���ǰ��������Ч�ԡ�
	private boolean Life;					//�ߵ�������ͨ�������Կ�����Ϸ����ͣ�Ϳ�ʼ
	private boolean ThreadIsStart = false;	//��¼�Ƿ��Ѿ��������������ƶ����߳�
	private int Scorl;						//������������ĳ���
	private Point LastTail = new Point();	//������β
	
	private LinkedList<Point> body = new LinkedList<Point>();	//�������������ڵ�����
	private Set<SnakeListener> listeners = new HashSet<SnakeListener>();	//����������ע��ļ�����
	
	public int getScorl() {
		return Scorl;
	}
	
	public void setScorl(int Scorl){
		if(Scorl<10000)
			this.Scorl = Scorl;
		System.out.println("����������");
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
		// TODO �Զ����ɵĹ��캯�����
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
	 * ���ƶ��ķ���
	 */
	public void move(){
		System.out.println("snake move");
		
		if(OldDirection + Direction != 0){
			OldDirection = Direction;
		}
		
		//1. ȥβ
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
		
		//2. ��ͷ
		body.addFirst(p);
	}
	
	/*
	 * �߸ı��ƶ�����
	 */
	public void changeDirection(int Direction){
		System.out.println("sanke change direction");
		
		this.Direction = Direction;
	}
	
	/*
	 * �߳Ե�ʳ�����ӳ�
	 */
	public void eatFood(){
		System.out.println("sanke eat food");
		body.addLast(LastTail);		
	}
	
	/*
	 * �ж����Ƿ�Ե����Լ�
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
	 * ��ʾ����
	 */
	public void drawMe(Graphics g){
		System.out.println("snake draw me");
		g.setColor(new Color(Global.SNAKE_COLOR));
		for (Point point : body) {
			g.fill3DRect(point.x * Global.CELL_SIZE , point.y * Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
		}
		
	}
	
	/*
	 * �˴�ͨ���߳��������ƶ���Ȼ������������ĺ���Ȼ��ִ�С�
	 */
	private class SnakeDriver extends Thread{

		/* ���� Javadoc��
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			// TODO �Զ����ɵķ������
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
	 * �߿�ʼ�ƶ�
	 */
	public void Start(){
		if(!ThreadIsStart){
			ThreadIsStart = true;
			new Thread(new SnakeDriver()).start();
		}
		Life = true;
	}
	
	/*
	 * ����ͣ�ƶ�
	 */
	public void Stop(){
		Life = false;
	}
	
	/*
	 * ע�������
	 */
	public void addSnakeListener(SnakeListener l){
		if(l!=null)
			listeners.add(l);
	}
	
	/*
	 * ��ȡ�ߵ�ͷ��
	 */
	public Point getHead(){
		
		return body.getFirst();
	}
	
	/*
	 * ��ȡ�ߵ��ٶ�
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
	 * �����µ���Ϸ
	 */
	public void newGame(){
		init();
		Start();
	}
}

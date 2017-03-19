package com.nguhyw.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import com.nguhyw.controller.Controller;
import com.nguhyw.entities.Ground;
import com.nguhyw.util.Global;

//VS4E -- DO NOT REMOVE THIS LINE!
public class OptionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JFrame frame;
	private Controller controller;
	private Ground ground;
	
	private JButton jBtnSuspend;
	private JLabel  jLabel0;
	private JLabel  jLabScorl;
	private JLabel  jLabel2;
	private JLabel  jLabSpeed;
	private JButton jBtnStart;
	private JButton jBtnGround;

	public OptionPanel(){
		initComponents();
	}
	public OptionPanel(Ground ground, JFrame frame,Controller controller){
		this.frame = frame;
		this.controller = controller;
		this.ground = ground;
		initComponents();
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO 自动生成的方法存根
		g.setColor(new Color(0xF0FFFF));
		g.fill3DRect(0, 0, this.getWidth(), this.getHeight(), true);
	}
	
	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJLabel0(), new Constraints(new Leading(19, 10, 10), new Leading(14, 10, 10)));
		add(getJLabScorl(), new Constraints(new Leading(57, 196, 10, 10), new Leading(14, 12, 12)));
		add(getJLabel2(), new Constraints(new Leading(19, 12, 12), new Leading(38, 12, 12)));
		add(getJLabSpeed(), new Constraints(new Leading(57, 138, 12, 12), new Leading(38, 12, 12)));
		add(getJBtnSuspend(), new Constraints(new Leading(477, 86, 12, 12), new Leading(42, 10, 10)));
		add(getJBtnStart(), new Constraints(new Leading(477, 12, 12), new Leading(12, 12, 12)));
		add(getJBtnGround(), new Constraints(new Leading(384, 87, 12, 12), new Leading(14, 12, 12)));
		setSize(Global.CELL_SIZE*Global.WIDTH, 80);
		setFocusableFlase();
	}
	private JButton getJBtnGround() {
		if (jBtnGround == null) {
			jBtnGround = new JButton();
			jBtnGround.setText("地图1");
			jBtnGround.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					jBtnGroundActionActionPerformed(event);
				}
			});
		}
		return jBtnGround;
	}
	private JButton getJBtnStart() {
		if (jBtnStart == null) {
			jBtnStart = new JButton();
			jBtnStart.setText("开始游戏");
			jBtnStart.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					jBtnStartActionActionPerformed(event);
				}
			});
		}
		return jBtnStart;
	}

	public JLabel getJLabSpeed() {
		if (jLabSpeed == null) {
			jLabSpeed = new JLabel();
			jLabSpeed.setText("0");
		}
		return jLabSpeed;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("速度：");
		}
		return jLabel2;
	}

	public JLabel getJLabScorl() {
		if (jLabScorl == null) {
			jLabScorl = new JLabel();
			jLabScorl.setText("0");
		}
		return jLabScorl;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("分数：");
		}
		return jLabel0;
	}


	private JButton getJBtnSuspend() {
		if (jBtnSuspend == null) {
			jBtnSuspend = new JButton();
			jBtnSuspend.setText("暂停");
			jBtnSuspend.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent event) {
					jBtnSuspendActionActionPerformed(event);
				}
			});
		}
		return jBtnSuspend;
	}

	private void jBtnStartActionActionPerformed(ActionEvent event) {
		setFocusableFlase();
		controller.newGame();
		System.out.println("开始游戏");
	}

	private void jBtnSuspendActionActionPerformed(ActionEvent event) {
		setFocusableFlase();
		if(controller.continueGame()){
			jBtnSuspend.setText("暂停");
		}else{
			jBtnSuspend.setText("继续");
		}
	}

	private void setFocusableFlase(){
		jBtnSuspend.setFocusable(false);
		jLabel0.setFocusable(false);
		jLabScorl.setFocusable(false);
		jLabel2.setFocusable(false);
		jLabSpeed.setFocusable(false);
		jBtnStart.setFocusable(false);
		jBtnGround.setFocusable(false);
		this.setFocusable(false);
		frame.setFocusable(true);
	}
	private void jBtnGroundActionActionPerformed(ActionEvent event) {
		setFocusableFlase();
		
		int result = ground.switchGround();
		if(result>=2 && result <= 3){
			result = result-1;
			jBtnGround.setText("地图"+result);
		}
		else{
			jBtnGround.setText("地图3");
		}

	}


}

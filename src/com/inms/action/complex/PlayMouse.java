package com.inms.action.complex;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class PlayMouse extends JFrame implements Runnable{

	private JLabel back;
	private JLabel[] mouse;
	private ImageIcon imgMouse;
	private JLabel jtf;
	public  PlayMouse() {
		this.setResizable(false);
		this.getContentPane().setLayout(null);
		this.setTitle("我的打地鼠游戏");
		this.setBounds(300, 100, 600, 438);
		
		this.setVisible(true);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		PlayMouse ttMouse = new PlayMouse();
	}
	
}
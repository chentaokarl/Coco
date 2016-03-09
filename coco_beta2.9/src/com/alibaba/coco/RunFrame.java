package com.alibaba.coco;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class RunFrame {
	JFrame run = new JFrame("CoCo2010");//顶层容器窗口
	JPanel m = new JPanel();//画布、容器
	JLabel jname = new JLabel();
	JPanel friends = new JPanel();
	JMenuBar flist = new JMenuBar() ;
	JMenuBar flist1 = new JMenuBar();
	JButton qunliao = new JButton("群聊");
	JMenu list = new JMenu(">在线好友列表");
	JMenu friendslist = new JMenu(">您的个人好友列表");
	/**
	 * RunFrame:used to new a running frame
	 * Made by TaoChen
	 * Time:2010-04
	 */
	
	//构建一个登陆界面
	public RunFrame(String name) {
		// TODO Auto-generated constructor stub
		Toolkit tk = m.getToolkit();
		flist.setLayout(new BorderLayout());
		flist1.setLayout(new BorderLayout());
		friends.setLayout(new GridLayout(3,0));
		jname.setText(name);
		m.setLayout(null);
		
		//添加列表监听,可有可无
		list.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(list.getText().equals(">在线好友列表")){
					list.setText("V在线好友列表");
					}else{
						list.setText(">在线好友列表");
					}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		//添加列表监听,可有可无
        friendslist.addMouseListener(new MouseListener(){

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(friendslist.getText().equals(">您的个人好友列表")){
				friendslist.setText("V您的个人好友列表");
				}else{
					friendslist.setText(">您的个人好友列表");
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
        	
        });

        flist1.add(friendslist);
		flist.add(list);
		friends.add(flist);
		friends.add(flist1);
		friends.add(qunliao);
		jname.setBounds(75, 0, 200, 30);
		m.add(jname);
		flist.setBounds(0, 30, 200, 25);
		m.add(flist);
		flist1.setBounds(0, 55, 200, 25);
		m.add(flist1);
		qunliao.setBounds(0, 80, 200, 25);
		m.add(qunliao);
		
		run.add(m);
		run.setSize(200, 500);
		run.setLocationRelativeTo(null);
		run.setIconImage(tk.getImage("image\\4.jpg"));
		run.setDefaultCloseOperation(run.EXIT_ON_CLOSE);
		run.setVisible(true);
	}
	public static void main(String[] args) {
		RunFrame rf = new RunFrame("ddd");
	}
}

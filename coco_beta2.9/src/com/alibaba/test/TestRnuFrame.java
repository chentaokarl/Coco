package com.alibaba.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class TestRnuFrame {
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
	 * TestRunFrame:used to new a running frame
	 * Made by TaoChen
	 * Time:2010-04
	 */
	public TestRnuFrame(String name) {
		// TODO Auto-generated constructor stub
		Toolkit tk = m.getToolkit();
		flist.setLayout(new BorderLayout());
		flist1.setLayout(new BorderLayout());
		friends.setLayout(new GridLayout(3,0));
		jname.setText(name);
		m.setLayout(null);
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
		run.setIconImage(tk.getImage("E:\\4.jpg"));
		run.setDefaultCloseOperation(run.EXIT_ON_CLOSE);
		run.setVisible(true);
	}
	
public static void main(String[] args) {
	TestRnuFrame mtrf = new TestRnuFrame("dddd");
}
}

package com.alibaba.coco;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class LoginJFrame {
   JFrame loginjframe = new JFrame("Cocologin");//顶层容器窗口
   JPanel jp = new JPanel();//画布、容器
   JPanel jp1 = new JPanel();//画布、容器
   JPanel jp2 = new JPanel();//画布、容器
   JPanel jp3 = new JPanel();//画布、容器
   JPanel jp4 = new JPanel();//画布、容器
   JPanel jp5 = new JPanel();//画布、容器
   JPanel jp6 = new JPanel();//画布、容器
   JPanel jp7 = new JPanel();//画布、容器
   JPanel jp8 = new JPanel();//画布、容器
   JLabel warnword = new JLabel("");//用于显示警告信息
   JLabel cocoIcon = new JLabel();//用于显示登录界面的图像
   JLabel name = new JLabel("帐号: ");//用于"账号:"的显示
   JLabel psw = new JLabel("密码: ");//用于"密码:"的显示
   JLabel welc = new JLabel("欢迎登陆！");//用于"欢迎登陆！"的显示
   JButton register = new JButton("注册");//用于注册新用户
   JButton login = new JButton("登陆");//构建一个登陆按钮
   JButton cancle = new JButton("取消");//构建一个取消按钮
   JButton yes = new JButton("确认");//用于警告框的确认
   JDialog warn = new JDialog(loginjframe,"警告",true);//警告框
   JTextField inputname = new JTextField(10);//登录名输入框
   JPasswordField inputpsw  = new JPasswordField(10);//密码输入框
   
   /**
    * LoginJFrame:used to new a login frame
    * Made by TaoChen
    * Time:2010-04
    */
   
   //构造器:构建一个登陆界面
   public LoginJFrame() {
	// TODO Auto-generated constructor stub
	   Toolkit tk  = jp.getToolkit();
	   cocoIcon.setIcon(new ImageIcon("F:\\10.gif"));
	   warn.setLayout(new GridLayout(2,1));
	   warn.setLocationRelativeTo(null);
	   jp7.setBackground(Color.pink);
	   jp4.setLayout(new BorderLayout(4,4));
	   jp5.setLayout(new BorderLayout(4,4));
	   jp6.setLayout(new BorderLayout(4,4));
	   loginjframe.setLayout(new BorderLayout(4,4));
	   jp8.add(yes);
	   warn.add(warnword);
	   warn.add(jp8);
	   warn.pack();
	   jp.add(cocoIcon);
       jp1.add(name);
       jp1.add(inputname);
       jp1.add(register);
       jp2.add(psw);
       jp2.add(inputpsw);
       jp7.add(welc);
       jp6.add(jp,BorderLayout.CENTER);
       jp6.add(jp7,BorderLayout.SOUTH);
       jp5.add(jp6,BorderLayout.CENTER);
       jp5.add(jp4,BorderLayout.SOUTH);
       jp4.add(jp1,BorderLayout.NORTH);
       jp4.add(jp2,BorderLayout.CENTER);
     
       jp3.add(login);
       jp3.add(cancle);
	   loginjframe.add(jp5,BorderLayout.CENTER);
	   loginjframe.add(jp3,BorderLayout.SOUTH);
	   loginjframe.pack();
	   loginjframe.setSize(350,250);
       loginjframe.setLocationRelativeTo(null);	 
       loginjframe.setIconImage(tk.getImage("E:\\myproject\\image\\4.jpg"));
	   loginjframe.setDefaultCloseOperation(loginjframe.EXIT_ON_CLOSE);
	   loginjframe.setVisible(true);
}
   public static void main(String[] args) {
	LoginJFrame ljf = new LoginJFrame();
}
}

package com.alibaba.coco;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import com.alibaba.message.Message;
import com.alibaba.user.Register;




public class CocoLogin {
	JDialog warn;//用于接收警告框
	JButton yes;//用于警告框的确认
	JButton register;//用于接收注册链接
	Register res;//用于new一个注册界面
	JButton login;//用于接收登陆按钮
	JButton cancle;//用于接收取消按钮
	LoginJFrame ljf;//用于构建登陆界面
	JLabel warnword;//用于显示警告框信息
	JTextField inputname;//用于接收登录名输入框
	JPasswordField inputpsw;//用于接收密码输入框
	Message msg ;//用于接收来自服务器的登录信息
	
    /**
     * CocoLogin:used to login system
     * Made by TaoChen
     * Time:2010-04
     */
	
	public CocoLogin() {
		// TODO Auto-generated constructor stub
		ljf = new LoginJFrame();//构造一个登陆界面
		this.yes = ljf.yes;//接收接收LoginJFrame中的yes按钮
		this.warn = ljf.warn;//接收LoginJFrame中的警告对话框
		this.login = ljf.login;//接收LoginJFrame中的登录按钮
		this.cancle = ljf.cancle;//接收LoginJFrame中的取消按钮
		this.warnword = ljf.warnword;//接收LoginJFrame中的警告显示
		this.register = ljf.register;//接收LoginJFrame中的注册label
		this.inputpsw = ljf.inputpsw;//接收LoginJFrame中的密码输入框
		this.inputname = ljf.inputname;//接收LoginJFrame中的登入名输入框
		
		//为yes添加监听
		yes.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				warn.setVisible(false);
			}
			
		});
		
		//为注册按钮添加监听
		register.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				  res = new Register();
			}
			
		});
		
		//为登陆按钮添加监听,使其连接登陆系统
		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(inputname.getText().equals("")||inputpsw.getText().equals("")){
					warnword.setText("请输入帐号和密码");
					warn.pack();
					warn.setVisible(true);
				}else{
				Client client = new Client(inputname.getText(),inputpsw.getText());
				if(client.connect()){
				Running run = new Running(client, inputname.getText());
				ljf.loginjframe.setVisible(false);
				}else{
					msg = client.msg;
					warnword.setText(msg.getMsg());
					warn.pack();
					warn.setVisible(true);
				}
				}
				}
		});
		
	    //为取消按钮添加监听,使其触发后退出程序
		cancle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
	}

	//整个登陆程序入口
	public static void main(String[] args) {
		CocoLogin col = new CocoLogin();
	}
}

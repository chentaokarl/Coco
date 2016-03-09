package com.alibaba.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import com.alibaba.coco.Client;
import com.alibaba.message.ListMessage;
import com.alibaba.message.Message;

public class Register {
	JDialog warn;//用于接收警告框
	JButton giveup;//用于接收取消按钮
	JButton zhuce;//用于接收注册按钮
	JButton yes;//警告框的确定
	JTextField num;//用于接收帐号输入框
	JPasswordField psw;//用于接收密码输入框
	JLabel warnword;//用于显示警告框信息
	JLabel checkexit;//用于接收检测帐号是否可用Label
	RegisterJFrame rjf;//用于new一个注册界面
	JPasswordField checkpsw;//用于接收确认密码输入框
	Socket registerSocket;//用于与服务器进行注册沟通
	ObjectOutputStream registerOos;//用于向服务器发送消息的输出流
	ObjectInputStream registerOis;//用于接收服务器发送消息的输入流
	Message registerMsg;// 用于与服务传递的封装信息类
  
	/**
	 * Register:used to new a user
	 * Made by TaoChen
	 * Time:2010-04
	 */
	
	public Register() {
		// TODO Auto-generated constructor stub
		rjf = new RegisterJFrame();
		this.giveup = rjf.giveup;
		this.zhuce = rjf.zhuce;
		this.num = rjf.num;
		this.psw = rjf.psw;
		this.yes = rjf.yes;
		this.warn = rjf.warn;
		this.warnword = rjf.warnword;
		this.checkexit = rjf.checkexit;
		this.checkpsw  = rjf.checkpsw;
		registerMsg = new Message("","",new Date());
		
		try {
			
			registerSocket = new Socket("127.0.0.1", 9999);
			registerOos = new ObjectOutputStream(registerSocket.getOutputStream());
			registerOis = new ObjectInputStream(registerSocket.getInputStream());
			registerMsg.setRegister(true);
			registerOos.writeObject(registerMsg);
			
			//为检查是否存在添加监听
			
			checkexit.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					Message registerMsg1 = new Message("","",new Date());
					 registerMsg1.setRegister(true);
					if(num.getText().equals("")){
						checkexit.setText("请输入用户名");
					}else{
						
						try {
							registerMsg1.setName(num.getText());
							registerOos.writeObject(registerMsg1);
							registerMsg1 = (Message) registerOis.readObject();
							if(registerMsg1.getState()){
								checkexit.setText("可用");
							}else{
								checkexit.setText("用户已存在");
							}
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
					}
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
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});
			psw.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
					Message registerMsg1 = new Message("","",new Date());
					 registerMsg1.setRegister(true);
					if(num.getText().equals("")){
						checkexit.setText("请输入用户名");
					}else{
						
						try {
							registerMsg1.setName(num.getText());
							registerOos.writeObject(registerMsg1);
							registerMsg1 = (Message) registerOis.readObject();
							if(registerMsg1.getState()){
								checkexit.setText("可用");
							}else{
								checkexit.setText("用户已存在");
							}
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
					}
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
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});

			
			
			//为yes添加监听
			yes.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					warn.setVisible(false);
				}
				
			});
			
			//为注册按钮添加监听
			zhuce.addActionListener(new ActionListener(){
				Message registerMsg1 = new Message("","",new Date());
				@Override
				public void actionPerformed(ActionEvent e) {
					registerMsg1.setRegister(true);
					// TODO Auto-generated method stub
					if(num.getText().equals("")){
						warnword.setText("请输入帐号");
						warn.setVisible(true);
					}else if(psw.getText().equals("")){
						warnword.setText("请输入密码");
						warn.setVisible(true);
					}else if(!checkpsw.getText().equals(psw.getText())){
						warnword.setText("两次输入密码不一致");
						warn.pack();
						warn.setVisible(true);
					}else{
                      registerMsg1.setName(num.getText());
                      registerMsg1.setPsw(psw.getText());
						try {
							registerOos.writeObject(registerMsg1);
							registerMsg1 = (Message) registerOis.readObject();
						    if(registerMsg1.getState()){
						    	registerMsg1.setRegister(false);
						    	registerOos.writeObject(registerMsg1);
								warnword.setText("注册成功");
								warn.setVisible(true);
								rjf.reg.setVisible(false);
								registerOos.close();
								registerOis.close();
								registerSocket.close();
								}else{
									warnword.setText("用户已存在");
									warn.pack();
									warn.setVisible(true);
								}
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}  
				
					}
				}
				
			});
			
			//为取消按钮添加监听
			giveup.addActionListener(new ActionListener(){
				Message registerMsg1 = new Message("","",new Date());
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						registerMsg1.setRegister(false);
				    	registerOos.writeObject(registerMsg1);
						rjf.reg.setVisible(false);
						registerOos.close();
						registerOis.close();
						registerSocket.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			});
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		rjf.reg.addWindowListener(new WindowAdapter(){
			Message registerMsg1 = new Message("","",new Date());
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				try {
					registerMsg1.setRegister(false);
			    	registerOos.writeObject(registerMsg1);
					rjf.reg.setVisible(false);
					registerOos.close();
					registerOis.close();
					registerSocket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		

	}

    
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}

}

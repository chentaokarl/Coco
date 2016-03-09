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
	JDialog warn;//���ڽ��վ����
	JButton giveup;//���ڽ���ȡ����ť
	JButton zhuce;//���ڽ���ע�ᰴť
	JButton yes;//������ȷ��
	JTextField num;//���ڽ����ʺ������
	JPasswordField psw;//���ڽ������������
	JLabel warnword;//������ʾ�������Ϣ
	JLabel checkexit;//���ڽ��ռ���ʺ��Ƿ����Label
	RegisterJFrame rjf;//����newһ��ע�����
	JPasswordField checkpsw;//���ڽ���ȷ�����������
	Socket registerSocket;//���������������ע�ṵͨ
	ObjectOutputStream registerOos;//�����������������Ϣ�������
	ObjectInputStream registerOis;//���ڽ��շ�����������Ϣ��������
	Message registerMsg;// ��������񴫵ݵķ�װ��Ϣ��
  
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
			
			//Ϊ����Ƿ������Ӽ���
			
			checkexit.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					Message registerMsg1 = new Message("","",new Date());
					 registerMsg1.setRegister(true);
					if(num.getText().equals("")){
						checkexit.setText("�������û���");
					}else{
						
						try {
							registerMsg1.setName(num.getText());
							registerOos.writeObject(registerMsg1);
							registerMsg1 = (Message) registerOis.readObject();
							if(registerMsg1.getState()){
								checkexit.setText("����");
							}else{
								checkexit.setText("�û��Ѵ���");
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
						checkexit.setText("�������û���");
					}else{
						
						try {
							registerMsg1.setName(num.getText());
							registerOos.writeObject(registerMsg1);
							registerMsg1 = (Message) registerOis.readObject();
							if(registerMsg1.getState()){
								checkexit.setText("����");
							}else{
								checkexit.setText("�û��Ѵ���");
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

			
			
			//Ϊyes��Ӽ���
			yes.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					warn.setVisible(false);
				}
				
			});
			
			//Ϊע�ᰴť��Ӽ���
			zhuce.addActionListener(new ActionListener(){
				Message registerMsg1 = new Message("","",new Date());
				@Override
				public void actionPerformed(ActionEvent e) {
					registerMsg1.setRegister(true);
					// TODO Auto-generated method stub
					if(num.getText().equals("")){
						warnword.setText("�������ʺ�");
						warn.setVisible(true);
					}else if(psw.getText().equals("")){
						warnword.setText("����������");
						warn.setVisible(true);
					}else if(!checkpsw.getText().equals(psw.getText())){
						warnword.setText("�����������벻һ��");
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
								warnword.setText("ע��ɹ�");
								warn.setVisible(true);
								rjf.reg.setVisible(false);
								registerOos.close();
								registerOis.close();
								registerSocket.close();
								}else{
									warnword.setText("�û��Ѵ���");
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
			
			//Ϊȡ����ť��Ӽ���
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

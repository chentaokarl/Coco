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
	JDialog warn;//���ڽ��վ����
	JButton yes;//���ھ�����ȷ��
	JButton register;//���ڽ���ע������
	Register res;//����newһ��ע�����
	JButton login;//���ڽ��յ�½��ť
	JButton cancle;//���ڽ���ȡ����ť
	LoginJFrame ljf;//���ڹ�����½����
	JLabel warnword;//������ʾ�������Ϣ
	JTextField inputname;//���ڽ��յ�¼�������
	JPasswordField inputpsw;//���ڽ������������
	Message msg ;//���ڽ������Է������ĵ�¼��Ϣ
	
    /**
     * CocoLogin:used to login system
     * Made by TaoChen
     * Time:2010-04
     */
	
	public CocoLogin() {
		// TODO Auto-generated constructor stub
		ljf = new LoginJFrame();//����һ����½����
		this.yes = ljf.yes;//���ս���LoginJFrame�е�yes��ť
		this.warn = ljf.warn;//����LoginJFrame�еľ���Ի���
		this.login = ljf.login;//����LoginJFrame�еĵ�¼��ť
		this.cancle = ljf.cancle;//����LoginJFrame�е�ȡ����ť
		this.warnword = ljf.warnword;//����LoginJFrame�еľ�����ʾ
		this.register = ljf.register;//����LoginJFrame�е�ע��label
		this.inputpsw = ljf.inputpsw;//����LoginJFrame�е����������
		this.inputname = ljf.inputname;//����LoginJFrame�еĵ����������
		
		//Ϊyes��Ӽ���
		yes.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				warn.setVisible(false);
			}
			
		});
		
		//Ϊע�ᰴť��Ӽ���
		register.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				  res = new Register();
			}
			
		});
		
		//Ϊ��½��ť��Ӽ���,ʹ�����ӵ�½ϵͳ
		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(inputname.getText().equals("")||inputpsw.getText().equals("")){
					warnword.setText("�������ʺź�����");
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
		
	    //Ϊȡ����ť��Ӽ���,ʹ�䴥�����˳�����
		cancle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
	}

	//������½�������
	public static void main(String[] args) {
		CocoLogin col = new CocoLogin();
	}
}

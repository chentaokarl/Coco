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
   JFrame loginjframe = new JFrame("Cocologin");//������������
   JPanel jp = new JPanel();//����������
   JPanel jp1 = new JPanel();//����������
   JPanel jp2 = new JPanel();//����������
   JPanel jp3 = new JPanel();//����������
   JPanel jp4 = new JPanel();//����������
   JPanel jp5 = new JPanel();//����������
   JPanel jp6 = new JPanel();//����������
   JPanel jp7 = new JPanel();//����������
   JPanel jp8 = new JPanel();//����������
   JLabel warnword = new JLabel("");//������ʾ������Ϣ
   JLabel cocoIcon = new JLabel();//������ʾ��¼�����ͼ��
   JLabel name = new JLabel("�ʺ�: ");//����"�˺�:"����ʾ
   JLabel psw = new JLabel("����: ");//����"����:"����ʾ
   JLabel welc = new JLabel("��ӭ��½��");//����"��ӭ��½��"����ʾ
   JButton register = new JButton("ע��");//����ע�����û�
   JButton login = new JButton("��½");//����һ����½��ť
   JButton cancle = new JButton("ȡ��");//����һ��ȡ����ť
   JButton yes = new JButton("ȷ��");//���ھ�����ȷ��
   JDialog warn = new JDialog(loginjframe,"����",true);//�����
   JTextField inputname = new JTextField(10);//��¼�������
   JPasswordField inputpsw  = new JPasswordField(10);//���������
   
   /**
    * LoginJFrame:used to new a login frame
    * Made by TaoChen
    * Time:2010-04
    */
   
   //������:����һ����½����
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

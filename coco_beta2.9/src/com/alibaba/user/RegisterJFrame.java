package com.alibaba.user;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterJFrame {
	JFrame reg = new JFrame("��ӭע��Coco");//���㴰��
	JPanel main = new JPanel();//������������
	JPanel fu  = new JPanel();//����������
    JLabel resimage = new JLabel();//���ڷ���ע������ͼ��
    JTextField num = new JTextField();//�˺������
    JPasswordField psw = new JPasswordField();//���������
    JButton zhuce = new JButton("ע��");//����ȷ��ע��İ�ť
    JButton giveup = new JButton("ȡ��");//����ȡ��ע��İ�ť
    JPasswordField checkpsw = new JPasswordField();// ȷ�����������
    JLabel resnum = new JLabel("�ʺ�:");//������ʾ"�ʺţ�"
    JLabel respsw = new JLabel("����:");//������ʾ"���룺"
    JLabel rescheckpsw = new JLabel("ȷ������:");//������ʾ"ȷ������:"
    JLabel checkexit = new JLabel("����Ƿ����");//������ʾ"����Ƿ����"
    JDialog warn = new JDialog(reg,"����",true);//������ʾ������Ϣ
    JButton yes = new JButton("ȷ��");//���ھ�����ȷ��
    JLabel warnword = new JLabel("ddddd");//������ʾ������Ϣ
    
	/**
	 * RegisterJFrame:used to create a interface
	 * Made by TaoChen
	 * Time:2010-04
	 */
	
    //������:����ע�����
    public RegisterJFrame() {
		// TODO Auto-generated constructor stub
    	warn.setLayout(new GridLayout(2,1));
    	warn.setLocationRelativeTo(null);
    	fu.add(yes);
  	    warn.add(warnword);
  	    warn.add(fu);
  	    warn.pack();
    	main.setLayout(null);
    	resimage.setIcon(new ImageIcon("image//register.gif"));
    	resimage.setBounds(0, 0, 300, 50);
    	resnum.setBounds(10, 60, 40, 30);
    	num.setBounds(50, 60, 150, 30);
    	checkexit.setBounds(200, 60, 80, 30);
    	respsw.setBounds(10,100, 40, 30);
    	psw.setBounds(50, 100, 150, 30);
    	rescheckpsw.setBounds(10, 140, 60, 30);
    	checkpsw.setBounds(70, 140, 150, 30);
    	zhuce.setBounds(30, 180, 100, 30);
    	giveup.setBounds(150, 180, 100, 30);
    	main.add(resimage);
    	main.add(resnum);
    	main.add(num);
    	main.add(checkexit);
    	main.add(respsw);
    	main.add(psw);
    	main.add(rescheckpsw);
    	main.add(checkpsw);
    	main.add(zhuce);
    	main.add(giveup);
    	
        reg.add(main);
    	reg.setSize(300, 300);
    	reg.setLocationRelativeTo(null);
    	reg.setDefaultCloseOperation(reg.DISPOSE_ON_CLOSE);
    	reg.setVisible(true);
    	
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
   RegisterJFrame rjf = new RegisterJFrame();
	}

}

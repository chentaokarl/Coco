package com.alibaba.test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CocoJFrame1 {

	JFrame myFrame = new JFrame();// ���㴰��
	JPanel jp = new JPanel();// ����������
	JButton shakeButton = new JButton(new ImageIcon("E://myproject//image/shake.jpg"));//������ť
	JButton showOrnot = new JButton(new ImageIcon("E://myproject//image/showOrnot.jpg"));//�Ƿ���ʾ��
	JButton qqshow = new JButton(new ImageIcon("E://myproject//image/qqshow.jpg"));//��ʾ��
	JButton qqshowown = new JButton(new ImageIcon("E://myproject//image/qqshowown.jpg"));//��ʾ��
	JButton send = new JButton(new ImageIcon("E://myproject//image/send.jpg"));// ���Ͱ�ť,���ڴ��������߳�
	JTextArea input = new JTextArea(8, 16);// ��ʾ��Ϣ����
	JTextArea output = new JTextArea(4, 4);// ��ʾ�����͵���Ϣ
	JScrollPane sp = new JScrollPane(input);// ��ʾ��Ϣ�Ĺ�����
	JScrollPane sp1 = new JScrollPane(output);// ��ʾ��������Ϣ�Ĺ�����
	
	/**
	 * CocoJFrame:used to create a dialogue
	 * Made by TaoChen 
	 * Time:2010-04
	 */
	
	public CocoJFrame1(String s) {
		// TODO Auto-generated constructor stub
		Toolkit tk = jp.getToolkit();
		jp.setBackground(Color.orange);
		input.setEditable(false);
		input.setLineWrap(true);
		output.setLineWrap(true);
		input.setFont(new Font("����", Font.ITALIC, 18));
		jp.setLayout(null);
		sp.setBounds(0, 50, 380, 250);
		jp.add(sp);
		showOrnot.setBounds(380, 220, 10, 30);
		jp.add(showOrnot);
		qqshow.setBounds(390, 50, 150, 230);
		jp.add(qqshow);
		shakeButton.setBounds(0, 300, 25, 25);
		jp.add(shakeButton);
		sp1.setBounds(0, 325, 380, 90);
		jp.add(sp1);
		qqshowown.setBounds(390, 310, 150, 145);
		jp.add(qqshowown);
		send.setBounds(300, 420, 80, 23);
		jp.add(send);
		myFrame.add(jp);
		myFrame.pack();
		myFrame.setTitle(s);
		myFrame.setSize(540, 495);
		myFrame.setLocationRelativeTo(null);
		myFrame.setIconImage(tk.getImage("E://myproject//image//4.jpg"));
//		myFrame.setSize(395, 495);
		myFrame.setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
CocoJFrame1  cj = new CocoJFrame1("");
	}

}

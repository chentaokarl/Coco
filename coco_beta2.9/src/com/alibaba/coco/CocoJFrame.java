package com.alibaba.coco;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.TransferHandler;

public class CocoJFrame {
	JFrame myFrame = new JFrame();// 顶层窗口
	JPanel jp = new JPanel();// 画布、容器
	JButton shakeButton = new JButton(new ImageIcon("image/shake.jpg"));//抖动按钮
	JButton showOrnot = new JButton(new ImageIcon("image/showOrnot.jpg"));//是否显示秀
	JButton qqshow = new JButton(new ImageIcon("image/qqshow.jpg"));//显示秀
	JButton qqshowown = new JButton(new ImageIcon("image/qqshowown.jpg"));//显示秀
	JButton send = new JButton(new ImageIcon("image/send.jpg"));// 发送按钮,用于触发发送线程
	JTextArea input = new JTextArea(8, 16);// 显示信息窗口
	JTextArea output = new JTextArea(4, 4);// 显示将发送的信息
	JScrollPane sp = new JScrollPane(input);// 显示信息的滚动条
	JScrollPane sp1 = new JScrollPane(output);// 显示将发送信息的滚动条
	TransferHandler th = new TransferHandler("text");//Drag&Drop
	
	/**
	 * CocoJFrame:used to create a dialogue
	 * Made by TaoChen 
	 * Time:2010-04
	 */
	
	public CocoJFrame(String s) {
		// TODO Auto-generated constructor stub
		Toolkit tk = jp.getToolkit();
		jp.setBackground(Color.orange);
		input.setTransferHandler(th);
		input.setDragEnabled(true);
		output.setTransferHandler(th);
		output.setDragEnabled(true);
		input.setEditable(false);
		input.setLineWrap(true);
		output.setLineWrap(true);
		input.setFont(new Font("宋体", Font.ITALIC, 18));
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
		myFrame.setIconImage(tk.getImage("image//4.jpg"));
//		myFrame.setSize(395, 495);
		myFrame.setVisible(true);
	}
	public static void main(String[] args) {
		CocoJFrame cj = new CocoJFrame("");
	}
}

package com.alibaba.coco;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;

import com.alibaba.message.ListMessage;
import com.alibaba.message.Message;

public class Dialogue {
	Message msg;//用于接收封装消息类Message
	String name;//用于接收调用者的登录名
	JButton send;//用于触发发送线程
	JButton showOrnot;//用于接收CocoJFrame中的显示秀与否的按钮
	JButton shakeButton;//用于接收CocoJFrame中的抖动按钮
	CocoJFrame coco;//用于构造对话框
	boolean showFlag = true;//秀显示标志
	JTextArea input;//用于接收CocoJFrame中的消息显示框
	JTextArea output;//用于接收CocoJFrame中的消息输入框
	String cname = "";//用于接收消息发送的对象的登录名
	ObjectOutputStream oos;//用于接收Client中的ObjectOutputStream
	ObjectInputStream ois;//用于接收Client中的ObjectOutputStream
	Message smsg = new Message("", "", new Date());//用于发送的封装消息类
	SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");//格式化时间
	
	/**
	 * Dialogue:used to create a new Dialogue when needed
	 * Made by TaoChen
	 * Time:2010-04
	 */
	
    //构造器:接收传入的参数,添加相应监听
	public Dialogue(String str, String cname, ObjectOutputStream oosi,
			ObjectInputStream oisi) {
		// TODO Auto-generated constructor stub
		this.name = str;//接收传入的调用者的登录名
		this.cname = cname;//接收要进行对话的另一方的登录名
		this.oos = oosi;//接收传入的Client的ObjectOutputStream
		this.ois = oisi;//接收传入的Client的ObjectInputStream
		coco = new CocoJFrame("你:" + name + "--to-->" + "好友:" + cname);//构建有对话双方的对话框
		input = coco.input;//接收CocoJFrame的信息显示框
		output = coco.output;//接收CocoJFrame的信息发送框
		send = coco.send;//接收CocoJFrame的发送按钮
		showOrnot = coco.showOrnot;//接收CocoJFrame的显示秀与否按钮
		shakeButton = coco.shakeButton;//接收CocoJFrame的抖动按钮
		coco.myFrame.setDefaultCloseOperation(coco.myFrame.DISPOSE_ON_CLOSE);//设定窗口关闭时释放所占用的资源
		
		//为发送按钮添加监听,被点击后触发发送线程
		send.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SendMsg sendmsg = new SendMsg();
				sendmsg.start();
			}
		});
		
		showOrnot.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(showFlag){
				coco.myFrame.setSize(395, 495);
				showOrnot.setIcon(new ImageIcon("E://myproject//image//show.jpg"));
				showFlag = false;
				}else{
					coco.myFrame.setSize(540, 495);
					showOrnot.setIcon(new ImageIcon("E://myproject//image//showOrnot.jpg"));
					showFlag = true;
				}
			}
			
		});
		
		shakeButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				smsg.setShakeflag(true);
				System.out.println(smsg.isShakeflag());
				SendMsg sendmsg = new SendMsg();
				sendmsg.start();
				 ShakeThread shakethread = new ShakeThread();
				 shakethread.start();
			}
			
		});
	}
    
	//消息发送线程
	class SendMsg extends Thread {
         Message smsg1;
		public void run() {
			try {
				String str = output.getText();
				smsg1 = new Message(name, str, new Date());
				smsg1.setShakeflag(smsg.isShakeflag());
				ListMessage lmsg = new ListMessage();
				smsg1.setCname(cname);
				lmsg.setMsg(smsg1);
				oos.writeObject(lmsg);
				if(!smsg.isShakeflag()){
				input.append("你说:" + myFmt.format(new Date()) + "\n" + str + "\n");
				}
				input.setCaretPosition(input.getText().length()); 
				output.setText("");
				smsg.setShakeflag(false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	class ShakeThread extends Thread{
		public void run(){
			
			try {
				Point p = coco.myFrame.getLocationOnScreen();
				int x = (int) p.getX();
				int y = (int) p.getY();
				coco.myFrame.setLocation(x+5,y);
				sleep(200);
				coco.myFrame.setLocation(x+5, y+5);
				sleep(200);
				coco.myFrame.setLocation(x-5, y);
				sleep(200);
				coco.myFrame.setLocation(x, y+5);
				sleep(200);
				coco.myFrame.setLocation(x+5,y);
				sleep(00);
				coco.myFrame.setLocation(x+5, y+5);
				sleep(200);
				coco.myFrame.setLocation(x, y+5);
				sleep(200);
				coco.myFrame.setLocation(x+5,y);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

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
	Message msg;//���ڽ��շ�װ��Ϣ��Message
	String name;//���ڽ��յ����ߵĵ�¼��
	JButton send;//���ڴ��������߳�
	JButton showOrnot;//���ڽ���CocoJFrame�е���ʾ�����İ�ť
	JButton shakeButton;//���ڽ���CocoJFrame�еĶ�����ť
	CocoJFrame coco;//���ڹ���Ի���
	boolean showFlag = true;//����ʾ��־
	JTextArea input;//���ڽ���CocoJFrame�е���Ϣ��ʾ��
	JTextArea output;//���ڽ���CocoJFrame�е���Ϣ�����
	String cname = "";//���ڽ�����Ϣ���͵Ķ���ĵ�¼��
	ObjectOutputStream oos;//���ڽ���Client�е�ObjectOutputStream
	ObjectInputStream ois;//���ڽ���Client�е�ObjectOutputStream
	Message smsg = new Message("", "", new Date());//���ڷ��͵ķ�װ��Ϣ��
	SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");//��ʽ��ʱ��
	
	/**
	 * Dialogue:used to create a new Dialogue when needed
	 * Made by TaoChen
	 * Time:2010-04
	 */
	
    //������:���մ���Ĳ���,�����Ӧ����
	public Dialogue(String str, String cname, ObjectOutputStream oosi,
			ObjectInputStream oisi) {
		// TODO Auto-generated constructor stub
		this.name = str;//���մ���ĵ����ߵĵ�¼��
		this.cname = cname;//����Ҫ���жԻ�����һ���ĵ�¼��
		this.oos = oosi;//���մ����Client��ObjectOutputStream
		this.ois = oisi;//���մ����Client��ObjectInputStream
		coco = new CocoJFrame("��:" + name + "--to-->" + "����:" + cname);//�����жԻ�˫���ĶԻ���
		input = coco.input;//����CocoJFrame����Ϣ��ʾ��
		output = coco.output;//����CocoJFrame����Ϣ���Ϳ�
		send = coco.send;//����CocoJFrame�ķ��Ͱ�ť
		showOrnot = coco.showOrnot;//����CocoJFrame����ʾ�����ť
		shakeButton = coco.shakeButton;//����CocoJFrame�Ķ�����ť
		coco.myFrame.setDefaultCloseOperation(coco.myFrame.DISPOSE_ON_CLOSE);//�趨���ڹر�ʱ�ͷ���ռ�õ���Դ
		
		//Ϊ���Ͱ�ť��Ӽ���,������󴥷������߳�
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
    
	//��Ϣ�����߳�
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
				input.append("��˵:" + myFmt.format(new Date()) + "\n" + str + "\n");
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

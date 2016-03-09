package com.alibaba.coco;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import com.alibaba.message.Message;

public class Client {
	Socket s; // �������ӷ�����
	String name; // ���ڽ��մ�����û���¼��
	String psw;//���ڽ��մ�����û�����
	Message msg; // ���ڽ��շ�װ��Ϣ
	boolean flag;//���ڱ�־��½�Ƿ�ɹ�
	ObjectInputStream ois; // ���ڵõ� Socket s�е�getInputStream
	ObjectOutputStream oos; // ���ڵõ� Socket s�е�getOutputStream
	Message smsg = new Message("", "", new Date()); // ���ڷ�װ���͵���Ϣ
	
	/**
	 * Client:connect to  Server
	 * Made by TaoChen
	 * Time:2010-04
	 */

	//������:����һ��String�Ĳ���,����¼��
	public Client(String str,String psw) {
		// TODO Auto-generated constructor stub
		this.name = str;
		this.psw = psw;
	}

	// �������ӷ�����
	public boolean connect() {
		try {
			s = new Socket("127.0.0.1", 9999);// ���ӷ�����
			oos = new ObjectOutputStream(s.getOutputStream());// �õ�s��OutputStream
			ois = new ObjectInputStream(s.getInputStream());// �õ�s��InputStream
			smsg.setName(name);// ����¼����װ��������Ϣsmsg
			smsg.setPsw(psw);
			oos.writeObject(smsg);// ����װ�˵�¼����smsg����������,�ڷ�������ע���¼
			msg = (Message) ois.readObject();
			flag = msg.getState();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	public void sendMsg(Message sendMsg) throws Exception{
		oos.writeObject(sendMsg);
	}
	
	public void readMsg() throws Exception{
		msg = (Message) ois.readObject();
	}
}

package com.alibaba.coco;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import com.alibaba.message.Message;
import com.alibaba.user.AddUser;

public class ServerRegister extends Thread{
	Message mname; //���ڽ������Կͻ��˵ķ�װ��Ϣ��ͷ��͵ķ�װ��Ϣ��
	ObjectInputStream fois;//���ڽ���������
	ObjectOutputStream foos;//���ڽ��������
	
	public ServerRegister(ObjectOutputStream foos,ObjectInputStream fois,Message mname) {
		// TODO Auto-generated constructor stub
		this.foos = foos;
		this.fois = fois;
		this.mname = mname;
	}
	public void run(){
		try {
			System.out.println();
		AddUser add;
	while(mname.isRegister()){
	
			mname = (Message) fois.readObject();
			if(mname.isRegister()){
				add = new AddUser(mname.getName(),mname.getPsw());
				if(add.checkin()){
					if(mname.getPsw()!=null){
					add.readPro();
					}
					mname.setState(true);
					foos.writeObject(mname);
				}else{
					mname.setState(false);
					foos.writeObject(mname);
				}
				}
	}
	foos.close();
	fois.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	
	}
}

package com.alibaba.coco;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JTextArea;

import com.alibaba.message.ListMessage;
import com.alibaba.message.Message;
import com.alibaba.user.AddUser;
import com.alibaba.user.CheckIn;

public class Server {
	int count = 0;//用于计数(记下连接客户数)
	CheckIn ci ;//用于核查用户名和密码
	Socket sk;//用于接收来自客户端的Socket
	Socket ssend;//用于发送时得到list中的Socket
	ServerSocket ss;//用于建立服务器
	JButton send;//用于接收来自CocoJFrame界面类中的按钮,以便创建按钮的监听
	Message msg; //用于接收来自客户端的封装消息类和发送的封装消息类
	Message rsmsg;//用于中转客户聊天的信息的封装消息类
	CocoJFrame coco;//用于创建一个界服务器面(没有实际用处,可以删除)
	JTextArea input;//用于接收来自CocoJFrame中的input,用来显示接收到得信息
	JTextArea output;//用于接收来自CocoJFrame中的output,用来显示发送的信息
	ObjectOutputStream os;//用于发送时得到listo中的ObjectOutputStream
	String deletename = new String();//用于接收来自客户端的下线消息中的姓名,以便列表中的更新删除
	ListMessage lmsg = new ListMessage();//用于封装message和最新上线的客户姓名
	ArrayList list = new ArrayList();//用于存储已经连接的客户的Socket
	ArrayList listo = new ArrayList();//用于存储根据已经连接的客户的Socket的OutputStream构造的ObjectOutputStream
	ArrayList listi = new ArrayList();//用于存储根据已经连接的客户的Socket的InputStream构造的ObjectInputStream
	ArrayList listname = new ArrayList();//用于存储在线用户的姓名,以便发送给用户，让其及时更新
	AddUser add;//注册用户类
	SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");//格式化时间
   
	/**
	 * Server:used to accept link 
	 * Made by TaoChen
	 * Time:2010-04
	 */
	
	//构造器
	public Server() {
		// TODO Auto-generated constructor stub
		coco = new CocoJFrame("CoCo-Server");//构造一个界面,用于显示信息
		coco.myFrame.setDefaultCloseOperation(coco.myFrame.EXIT_ON_CLOSE);//设定界面窗口关闭时退出程序
		this.send = coco.send;//接收来自界面的send按钮
		input = coco.input;
		output = coco.output;
		send.addActionListener(new ActionListener() {//对send按钮添加发送监听

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SendMsg sendmsg = new SendMsg();
				sendmsg.start();//启动发送消息的线程
			}
		});
	}
	
    //程序入口,启动整个Server程序
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server server = new Server();
		server.startServer();

	}

	//开启服务器,等待客户端连接，并作出一些链接后的处理
	public void startServer() {
		try {
			ObjectInputStream fois;
			ObjectOutputStream foos;
			ss = new ServerSocket(9999);//构建服务
			msg = new Message("server", "", new Date());//对封装消息类初始化,避免空指针
			while (true) {//循环等待连接,实现多客户连接
				ssend = ss.accept();//接受客户端连接
				list.add(ssend);
				listi.add(new ObjectInputStream(ssend.getInputStream()));
				listo.add(new ObjectOutputStream(ssend.getOutputStream()));
				fois = (ObjectInputStream) listi.get(listi.size()-1);
				foos = (ObjectOutputStream) listo.get(listo.size()-1);
				Message mname = (Message) fois.readObject();
				if(mname.isRegister()){
					list.remove(list.size()-1);
					listi.remove(listi.size()-1);
					listo.remove(listo.size()-1);
					ServerRegister sr = new ServerRegister(foos,fois,mname);
					sr.start();
				}else{
				ci = new CheckIn(mname.getName(),mname.getPsw());
				ci.checkin();
				if(ci.isFlag()){
					if(ci.isFlag1()){
						int i = 0;
						for( ;i< listname.size();i++){
							String name = (String) listname.get(i);
							if(mname.getName().equals(name)){
								mname.setState(false);
								mname.setMsg("用户已登录");
								foos.writeObject(mname);
								fois.close();
								foos.close();
								list.remove(list.size()-1);
								listi.remove(listi.size()-1);
								listo.remove(listo.size()-1);
								break;
							}
						}
						if(i==listname.size()){
						listname.add(mname.getName());
						mname.setState(true);
						foos.writeObject(mname);
						lmsg.setMsg(mname);
						lmsg.setNamenext((String) listname.get(listname.size() - 1));
						SendMsg sendmsg = new SendMsg();
						sendmsg.start();
						ReadMsg read = new ReadMsg((ObjectInputStream) listi.get(listi.size()-1));
						read.start();
						}
					}else{
						msg.setState(false);
						msg.setMsg("密码错误！");
						foos.writeObject(msg);
						fois.close();
						foos.close();
						list.remove(list.size()-1);
						listi.remove(listi.size()-1);
						listo.remove(listo.size()-1);
						
					}
				}else{
					msg.setState(false);
					msg.setMsg("用户不存在，请注册");
					foos.writeObject(msg);
					fois.close();
					foos.close();
					list.remove(list.size()-1);
					listi.remove(listi.size()-1);
					listo.remove(listo.size()-1);
				}
				
			}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// System.out.println("socket closed");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    //消息发送线程,通过按钮启动发送
	class SendMsg extends Thread {
		Socket sk;
		ObjectOutputStream os;
		ListMessage lmsg = new ListMessage();

		public void run() {
			try {
				
				String str = output.getText();
				msg.setMsg(str);
				msg.setOnlist(listname);
				lmsg.setDeletename(deletename);
				lmsg.setMsg(msg);
				if (listname.size() != 0)
					lmsg.setNamenext((String) listname
									.get(listname.size() - 1));
				for (int i = 0; i < list.size(); i++) {
					sk = (Socket) list.get(i);
					os = (ObjectOutputStream) listo.get(i);
					os.writeObject(lmsg);
				}
				input.append("你说:" + myFmt.format(new Date()) + "\n" + str + "\n");
				input.setCaretPosition(input.getText().length()); 
				output.setText("");
				deletename = "";

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				// System.out.println("socket closed");
			}
		}
	}
   //接收消息线程,处于常开状态
	class ReadMsg extends Thread {
		ObjectInputStream ois;

		public ReadMsg(ObjectInputStream ois) {
			// TODO Auto-generated constructor stub
			this.ois = ois;
		}

		public void run() {
			try {

				while (true) {
					lmsg = (ListMessage) ois.readObject();
					if (lmsg.getMsg().getState()) {
						msg = lmsg.getMsg();
						msg.setOnlist(listname);
						rsmsg = msg;
						RSendMsg rsend = new RSendMsg();
						rsend.start();
					} else {
						for (int i = 0; i < listname.size(); i++) {
							if (((String) listname.get(i)).equals(lmsg.getMsg()
									.getName())) {
								((InputStream) listi.get(i)).close();
								((OutputStream) listo.get(i)).close();
								((Socket) list.get(i)).close();
								listi.remove(i);
								listo.remove(i);
								list.remove(i);
								listname.remove(i);

							}

						}
					}
				}
			} catch (IOException e) {
				if (!listname.isEmpty()) {
					System.out.println(listname.get(listname.size() - 1));
				
				deletename = lmsg.getMsg().getName();
				System.out.println(deletename);
				SendMsg sendmsg = new SendMsg();
				sendmsg.start();
				}

				// TODO Auto-generated catch block
				// e.printStackTrace();
				// System.out.println("socket closed");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
    //转发消息线程,实现客户间的聊天
	class RSendMsg extends Thread {
		ListMessage lmsg = new ListMessage();

		public void run() {
			try {
				rsmsg.setOnlist(listname);
				lmsg.setMsg(rsmsg);
				lmsg.setNamenext((String) listname.get(listname.size() - 1));
				for (int j = 0; j < listname.size(); j++) {
					if (rsmsg.getCname().equals("All")) {
						os = (ObjectOutputStream) listo.get(j);
						os.writeObject(lmsg);
					} else if (rsmsg.getCname()
							.equals((String) listname.get(j))) {
						os = (ObjectOutputStream) listo.get(j);
						os.writeObject(lmsg);
					}
				}
				input.append(rsmsg.toString());
				output.setText("");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

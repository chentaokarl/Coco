package com.alibaba.coco;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.alibaba.coco.Dialogue.ShakeThread;
import com.alibaba.message.ListMessage;
import com.alibaba.message.Message;

public class Running {
	JMenu list;// list菜单
	Message msg;// 用于接收封装消息类
	String name;// 用于接收登陆名
	Client client;// 用于接收已连接的Client实例
	JMenuItem jmi;// 菜单项
	JMenuBar flist;// 菜单条
	JLabel jl = new JLabel();// 用于将用户登录名显示在界面上
	String namenext = new String();// 用于接收下一个登陆的用户的登录名,更新在线列表
	JButton rqunliao = new JButton();// 用于触发群聊功能
	ListMessage lmsg = new ListMessage();// 封装信息类
	ArrayList listname = new ArrayList();// 用于存储在线用户列表
	ArrayList jmilist = new ArrayList();// 用于存储菜单项列表
	ArrayList dialoguelist = new ArrayList();// 用于存储已经构建的Dialogue

	/**
	 * Running:used to make system User-friendly 
	 * Made by TaoChen 
	 * Time:2010-04
	 */

	// 接收Client实例和用户登录名
	public Running(Client c, String str) {
		// TODO Auto-generated constructor stub
		this.name = str;// 接收传入的用户登录名
		RunFrame rf = new RunFrame(name);// 构建一个运行时界面
		flist = rf.flist;// 接收RunFrame中的菜单条
		list = rf.list;// 接收RunFrame中的菜单
		rqunliao = rf.qunliao;// 接收RunFrame中的群聊按钮
		client = c;// 接收传入的Client实例
		jl.setText(str);// 显示传入的用户登录名
		RefreshThread rt = new RefreshThread();
		rt.start();// 启动接收消息线程

		// 为群聊按钮添加监听,触发时构建一个群聊按钮
		rqunliao.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int i = 0;
				msg.setCname("All");
				for (; i < dialoguelist.size(); i++) {
					if ("All".equals(((Dialogue) dialoguelist.get(i)).cname)) {
						((Dialogue) dialoguelist.get(i)).input.append(msg
								.toString());
						((Dialogue) dialoguelist.get(i)).coco.myFrame
								.setVisible(true);
						break;
					}
				}
				if (i == dialoguelist.size()) {
					Dialogue d = new Dialogue(name, "All", client.oos,
							client.ois);
					dialoguelist.add(d);
				}
			}

		});

		// 设定窗口关闭时,关闭流和Socket,并推出程序
		rf.run.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				lmsg.getMsg().setName(name);
				lmsg.getMsg().setState(false);
				try {
					client.oos.writeObject(lmsg);
					client.ois.close();
					client.oos.close();
					client.s.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
				}

				System.exit(0);
			}

		});
	}

	// 接收消息线程
	class RefreshThread extends Thread {

		public void run() {
			try {// 初始已经登录的用户,将他们显示在列表中

				lmsg = (ListMessage) client.ois.readObject();
				msg = lmsg.getMsg();
				for (int i = 0; i < msg.getOnlist().size(); i++) {
					JMenuItem jmi = new JMenuItem((String) msg.getOnlist().get(
							i), new ImageIcon("image\\8.gif"));
					list.add(jmi);
					jmilist.add(jmi);
					((JMenuItem) jmilist.get(jmilist.size() - 1))
							.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									int i = 0;
									for (; i < dialoguelist.size(); i++) {
										if (e
												.getActionCommand()
												.equals(
														((Dialogue) dialoguelist
																.get(i)).cname)) {
											((Dialogue) dialoguelist.get(i)).coco.myFrame
													.setVisible(true);
											break;
										}
									}
									if (i == dialoguelist.size()) {
										Dialogue d = new Dialogue(name, e
												.getActionCommand(),
												client.oos, client.ois);
										dialoguelist.add(d);
									}
								}

							});

				}
				namenext = lmsg.getNamenext();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				// e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			while (true) {// 循环接收消息,并根据消息中包含的协议进行相应处理
				try {

					lmsg = (ListMessage) client.ois.readObject();
					msg = lmsg.getMsg();
					if (!lmsg.getDeletename().equals("")) {// 首先判断消息中是否包含用户下线信息
						for (int i = 0; i < jmilist.size(); i++) {// 包含则作出相应删除
							if (((JMenuItem) jmilist.get(i)).getText().equals(
									lmsg.getDeletename())) {
								if (i == (jmilist.size() - 1)) {
									namenext = ((JMenuItem) jmilist.get(i - 1))
											.getText();
									jmilist.remove(i);
									list.remove(i);
								} else {
									jmilist.remove(i);
									list.remove(i);
								}
							}

						}
					} else if (!namenext.equals(lmsg.getNamenext())) {// 接着判断是否包含用户上线消息
						jmi = new JMenuItem(lmsg.getNamenext(), new ImageIcon(// 包含则将其加入列表
								"image\\8.gif"));
						list.add(jmi);
						jmilist.add(jmi);
						jmi.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								int i = 0;
								for (; i < dialoguelist.size(); i++) {
									if (e.getActionCommand()
											.equals(
													((Dialogue) dialoguelist
															.get(i)).cname)) {
										((Dialogue) dialoguelist.get(i)).coco.myFrame
												.setVisible(true);
										break;
									}
								}
								if (i == dialoguelist.size()) {
									Dialogue d = new Dialogue(name, e
											.getActionCommand(), client.oos,
											client.ois);
									dialoguelist.add(d);
								}

							}

						});
						namenext = lmsg.getNamenext();
					} else if (msg.getName().equals(name)) {// 如果发送者的姓名是自己则不作任何处理

					} else if (msg.getCname().equals("All")) {// 判断是否为群聊
						int i = 0;
						for (; i < dialoguelist.size(); i++) {
							if (msg.getCname().equals(
									((Dialogue) dialoguelist.get(i)).cname)) {
								((Dialogue) dialoguelist.get(i)).input
										.append(msg.toString());
								((Dialogue) dialoguelist.get(i)).input
								.setCaretPosition(((Dialogue) dialoguelist
										.get(i)).input
										.getText().length());
								((Dialogue) dialoguelist.get(i)).coco.myFrame
										.setVisible(true);
								break;
							}
						}
						if (i == dialoguelist.size()) {
							Dialogue d = new Dialogue(name, msg.getCname(),
									client.oos, client.ois);
							d.input.append(msg.toString());
							d.input.setCaretPosition(d.input.getText().length());
							dialoguelist.add(d);
						}

					} else {// 接着根据发送者姓名判断是否已有相应对话框存在,是则显示对话,否则构建一个新的对话框
						int i = 0;
						for (; i < dialoguelist.size(); i++) {
							if (msg.getName().equals(
									((Dialogue) dialoguelist.get(i)).cname)) { 
								if(msg.isShakeflag()){
									   ShakeThread st =((Dialogue) dialoguelist.get(i)).new ShakeThread();
									    st.start();
									    msg.setShakeflag(false);
								}else{
								((Dialogue) dialoguelist.get(i)).input
										.append(msg.toString());
								}
								((Dialogue) dialoguelist.get(i)).input
								.setCaretPosition(((Dialogue) dialoguelist
										.get(i)).input
										.getText().length());
								((Dialogue) dialoguelist.get(i)).coco.myFrame
										.setVisible(true);
								break;
							}
						}
						if (i == dialoguelist.size()) {
							Dialogue d = new Dialogue(name, msg.getName(),
									client.oos, client.ois);
							if(msg.isShakeflag()){
								   ShakeThread st =d.new ShakeThread();
								    st.start();
								    msg.setShakeflag(false);
							}else{
							d.input.append(msg.toString());
							}
							d.input.setCaretPosition(d.input.getText().length());
							dialoguelist.add(d);
						}
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}


}
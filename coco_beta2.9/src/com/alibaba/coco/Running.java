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
	JMenu list;// list�˵�
	Message msg;// ���ڽ��շ�װ��Ϣ��
	String name;// ���ڽ��յ�½��
	Client client;// ���ڽ��������ӵ�Clientʵ��
	JMenuItem jmi;// �˵���
	JMenuBar flist;// �˵���
	JLabel jl = new JLabel();// ���ڽ��û���¼����ʾ�ڽ�����
	String namenext = new String();// ���ڽ�����һ����½���û��ĵ�¼��,���������б�
	JButton rqunliao = new JButton();// ���ڴ���Ⱥ�Ĺ���
	ListMessage lmsg = new ListMessage();// ��װ��Ϣ��
	ArrayList listname = new ArrayList();// ���ڴ洢�����û��б�
	ArrayList jmilist = new ArrayList();// ���ڴ洢�˵����б�
	ArrayList dialoguelist = new ArrayList();// ���ڴ洢�Ѿ�������Dialogue

	/**
	 * Running:used to make system User-friendly 
	 * Made by TaoChen 
	 * Time:2010-04
	 */

	// ����Clientʵ�����û���¼��
	public Running(Client c, String str) {
		// TODO Auto-generated constructor stub
		this.name = str;// ���մ�����û���¼��
		RunFrame rf = new RunFrame(name);// ����һ������ʱ����
		flist = rf.flist;// ����RunFrame�еĲ˵���
		list = rf.list;// ����RunFrame�еĲ˵�
		rqunliao = rf.qunliao;// ����RunFrame�е�Ⱥ�İ�ť
		client = c;// ���մ����Clientʵ��
		jl.setText(str);// ��ʾ������û���¼��
		RefreshThread rt = new RefreshThread();
		rt.start();// ����������Ϣ�߳�

		// ΪȺ�İ�ť��Ӽ���,����ʱ����һ��Ⱥ�İ�ť
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

		// �趨���ڹر�ʱ,�ر�����Socket,���Ƴ�����
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

	// ������Ϣ�߳�
	class RefreshThread extends Thread {

		public void run() {
			try {// ��ʼ�Ѿ���¼���û�,��������ʾ���б���

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
			while (true) {// ѭ��������Ϣ,��������Ϣ�а�����Э�������Ӧ����
				try {

					lmsg = (ListMessage) client.ois.readObject();
					msg = lmsg.getMsg();
					if (!lmsg.getDeletename().equals("")) {// �����ж���Ϣ���Ƿ�����û�������Ϣ
						for (int i = 0; i < jmilist.size(); i++) {// ������������Ӧɾ��
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
					} else if (!namenext.equals(lmsg.getNamenext())) {// �����ж��Ƿ�����û�������Ϣ
						jmi = new JMenuItem(lmsg.getNamenext(), new ImageIcon(// ������������б�
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
					} else if (msg.getName().equals(name)) {// ��������ߵ��������Լ������κδ���

					} else if (msg.getCname().equals("All")) {// �ж��Ƿ�ΪȺ��
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

					} else {// ���Ÿ��ݷ����������ж��Ƿ�������Ӧ�Ի������,������ʾ�Ի�,���򹹽�һ���µĶԻ���
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
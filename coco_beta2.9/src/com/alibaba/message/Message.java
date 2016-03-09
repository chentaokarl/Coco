package com.alibaba.message;

import java.io.Serializable;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Message implements Serializable {
	private Date date;//���ڹ��쵱ǰʱ����
	private String msg;//��¼��Ϣ
	private String name;//�����ߵ�¼��
	private String psw;//�����ߵ�½����
	private String cname;//�����ߵ�¼��
	private InetAddress ip;//������IP
	private boolean state = true;//��¼��½״̬
	private boolean shakeflag = false;//����Ƿ񶶶�����
	private boolean register = false;//��Ǹ���ϢΪע����Ϣ
	private ArrayList onlist = new ArrayList();//��¼�����û��б�
	
   /**
    * Message:used to packaging message
    * Made by TaoChen
    * Time:2010-04
    */

	//������:���մ������
	public Message(String name,String msg, Date date) {
		// TODO Auto-generated constructor stub
	    this.name = name;
		this.msg = msg;
		this.date = date;
	}

	public ArrayList getOnlist() {
		return onlist;
	}

	public void setOnlist(ArrayList onlist) {
		this.onlist = onlist;
	}

	public boolean getState() {
		return state;
	}

	public void  setState(boolean state) {
		this.state = state;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}



	public InetAddress getIp() {
		return ip;
	}

	public void setIp(InetAddress ip) {
		this.ip = ip;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public boolean isRegister() {
		return register;
	}

	public void setRegister(boolean register) {
		this.register = register;
	}

	public boolean isShakeflag() {
		return shakeflag;
	}

	public void setShakeflag(boolean shakeflag) {
		this.shakeflag = shakeflag;
	}
	public String toString() {
		SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
		return this.name + "˵:" + myFmt.format(this.date) + "\n" + msg + "\n";
	}
}

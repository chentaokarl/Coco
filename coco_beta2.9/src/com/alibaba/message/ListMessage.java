package com.alibaba.message;

import java.io.Serializable;


public class ListMessage implements Serializable{
    private Message msg;//��װ��װ��Ϣ��
    private String namenext;//��װ�µ�¼���û���¼��
    private String deletename = "";//��װ�����ߵ��û��ĵ�¼��
    
    /**
     * ListMessage:used to packaging Message
     * Made by TaoChen
     * Time:2010-04
     */
    
	public Message getMsg() {
		return msg;
	}
	public void setMsg(Message msg) {
		this.msg = msg;
	}
	public String getNamenext() {
		return namenext;
	}
	public void setNamenext(String namenext) {
		this.namenext = namenext;
	}
	public String getDeletename() {
		return deletename;
	}
	public void setDeletename(String deletename) {
		this.deletename = deletename;
	}
     
}

package com.alibaba.message;

import java.io.Serializable;


public class ListMessage implements Serializable{
    private Message msg;//封装封装消息类
    private String namenext;//封装新登录的用户登录名
    private String deletename = "";//封装新下线的用户的登录名
    
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

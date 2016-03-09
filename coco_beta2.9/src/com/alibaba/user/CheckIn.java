package com.alibaba.user;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Properties;

public class CheckIn {
	private String checkname;// 接收登陆的用户名
	private String checkpsw;// 用于接收登陆的密码
	private boolean flag = true;// 用于标识用户是否存在登陆
	private boolean flag1 = true;// 用于标识密码是否正确

	/**
	 * CheckIn:used to check user_name and password Made by TaoChen Time:2010-04
	 */

	public CheckIn(String name, String psw) {
		// TODO Auto-generated constructor stub
		this.checkname = name;
		this.checkpsw = psw;
	}

	public void checkin() {
		Properties p = new Properties();
		FileInputStream fis;
		String property = "";
		try {
			fis = new FileInputStream("E:/Projects/QQ/myproject/User.txt");
			p.load(fis);
			Enumeration p_names = p.propertyNames();
			for (int i = 0; p_names.hasMoreElements(); i++) {

				String p_name = (String) p_names.nextElement();
				if (p_name.equals(checkname)) {
					property = new String(p.getProperty(p_name).getBytes(
							"iso-8859-1"), "GBK");
				}
			}
			if (property.equals("")) {
				flag = false;

			} else if (property.equals(checkpsw)) {
				flag = true;
				flag = true;
			} else {
				flag1 = false;
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isFlag() {
		return flag;
	}

	public boolean isFlag1() {
		return flag1;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public void setFlag1(boolean flag1) {
		this.flag1 = flag1;
	}

}

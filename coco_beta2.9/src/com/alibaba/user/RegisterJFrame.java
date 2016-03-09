package com.alibaba.user;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterJFrame {
	JFrame reg = new JFrame("欢迎注册Coco");//顶层窗口
	JPanel main = new JPanel();//主画布、容器
	JPanel fu  = new JPanel();//画布、容器
    JLabel resimage = new JLabel();//用于放置注册界面的图标
    JTextField num = new JTextField();//账号输入框
    JPasswordField psw = new JPasswordField();//密码输入框
    JButton zhuce = new JButton("注册");//用于确认注册的按钮
    JButton giveup = new JButton("取消");//用于取消注册的按钮
    JPasswordField checkpsw = new JPasswordField();// 确认密码输入框
    JLabel resnum = new JLabel("帐号:");//用于显示"帐号："
    JLabel respsw = new JLabel("密码:");//用于显示"密码："
    JLabel rescheckpsw = new JLabel("确认密码:");//用于显示"确认密码:"
    JLabel checkexit = new JLabel("检测是否可用");//用于显示"检测是否可用"
    JDialog warn = new JDialog(reg,"警告",true);//用于显示警告信息
    JButton yes = new JButton("确认");//用于警告框的确认
    JLabel warnword = new JLabel("ddddd");//用于显示警告信息
    
	/**
	 * RegisterJFrame:used to create a interface
	 * Made by TaoChen
	 * Time:2010-04
	 */
	
    //构造器:构建注册界面
    public RegisterJFrame() {
		// TODO Auto-generated constructor stub
    	warn.setLayout(new GridLayout(2,1));
    	warn.setLocationRelativeTo(null);
    	fu.add(yes);
  	    warn.add(warnword);
  	    warn.add(fu);
  	    warn.pack();
    	main.setLayout(null);
    	resimage.setIcon(new ImageIcon("image//register.gif"));
    	resimage.setBounds(0, 0, 300, 50);
    	resnum.setBounds(10, 60, 40, 30);
    	num.setBounds(50, 60, 150, 30);
    	checkexit.setBounds(200, 60, 80, 30);
    	respsw.setBounds(10,100, 40, 30);
    	psw.setBounds(50, 100, 150, 30);
    	rescheckpsw.setBounds(10, 140, 60, 30);
    	checkpsw.setBounds(70, 140, 150, 30);
    	zhuce.setBounds(30, 180, 100, 30);
    	giveup.setBounds(150, 180, 100, 30);
    	main.add(resimage);
    	main.add(resnum);
    	main.add(num);
    	main.add(checkexit);
    	main.add(respsw);
    	main.add(psw);
    	main.add(rescheckpsw);
    	main.add(checkpsw);
    	main.add(zhuce);
    	main.add(giveup);
    	
        reg.add(main);
    	reg.setSize(300, 300);
    	reg.setLocationRelativeTo(null);
    	reg.setDefaultCloseOperation(reg.DISPOSE_ON_CLOSE);
    	reg.setVisible(true);
    	
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
   RegisterJFrame rjf = new RegisterJFrame();
	}

}

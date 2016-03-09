package com.alibaba.user;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Properties;

public class AddUser {
       private String username;
       private String userpsw;
       private boolean flag =  true;
       
       /**
        * AddUser:used to add user
        * Made by TaoChen
        * Time:2010-04
        */
       public AddUser(String username, String userpsw) {
		// TODO Auto-generated constructor stub
    	   this.username  = username;
    	   this.userpsw = userpsw;
	}
    	public static void writePro(Properties p){
    		FileOutputStream fos;
    		  try{
    			  fos = new FileOutputStream("E:/myproject/User.txt");
    			  p.store(fos,new String("User".getBytes("GBK"),"iso-8859-1"));
    		  }catch(FileNotFoundException e){
    			  e.printStackTrace();
    		  }catch(IOException e){
    			  e.printStackTrace();
    		  
    		}
    	}
    	public boolean readPro(){
    		FileInputStream fis;
    		try {
    			fis = new FileInputStream("E:/myproject/User.txt");
    			Properties p = new Properties();
    			p.load(fis);
    			p.setProperty(username, userpsw);
    			writePro(p);
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		return flag;
    	}
    	
    	 public boolean checkin(){
        	 Properties p = new Properties();
        	 FileInputStream fis;
    		try {
    			fis = new FileInputStream("E:/myproject/User.txt");
    			 p.load(fis);
    	    	 Enumeration p_names = p.propertyNames(); 
    	    	 for(int i = 0;p_names.hasMoreElements();i++){
    	    		 
    	    		 String p_name = (String)p_names.nextElement();
    	    		 if(p_name.equals(username)){
                     flag = false;
    	    		 }
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
    		return flag;
         }
}

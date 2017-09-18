package com.nms.rmi.ui.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

import com.nms.ui.manager.ExceptionManage;

public class BeforInstall{
	
	public boolean isOk(){
		String ptnC = ishaveThread("PTN-Client");
		if(null != ptnC){
			int i = JOptionPane.showConfirmDialog(null, "发现旧PTN-Client 进程,是否终止?", "安装PTN", JOptionPane.YES_NO_OPTION);
			if(i == 0){
				killthread(ptnC);
			}else {//i=1,用户选择否,退出安装程序
				return false;
			}
		}
		return true;
	}
	public boolean isServerOk(){
		String ptnC = ishaveThread("PTN-Server");
		if(null != ptnC){
			int i = JOptionPane.showConfirmDialog(null, "发现旧PTN-Server 进程,是否终止?", "安装PTN", JOptionPane.YES_NO_OPTION);
			if(i == 0){
				killthread(ptnC);
			}else {//i=1,用户选择否,退出安装程序
				return false;
			}
		}
		return true;
	}
	
	public String ishaveThread(String threadname){
		   Process process = null;      
	       BufferedReader bufReader = null;      
	       String bufReadLineString = null;
	       try {      
	           process = Runtime.getRuntime().exec( "tasklist /fi \"IMAGENAME eq java*\" /v ");   
	           bufReader = new BufferedReader(new InputStreamReader(process.getInputStream()));      
	           for (int i = 0; i < 6 && bufReader != null; i++) {      
	        	   bufReadLineString =bufReadLineString+  bufReader.readLine()+"\n";      
	               
	           }      	            
	           if(bufReadLineString.length()>5 && bufReadLineString.indexOf(threadname)>1){
	        	   bufReadLineString = bufReadLineString.substring(0, bufReadLineString.indexOf(threadname));
	        	   bufReadLineString = bufReadLineString.substring(bufReadLineString.lastIndexOf("\n")+1,bufReadLineString.length());
	        	   bufReadLineString = bufReadLineString.substring(bufReadLineString.indexOf("javaw.exe")+10, bufReadLineString.indexOf("Console"));
	        	   bufReadLineString = bufReadLineString.trim();
	        	   return bufReadLineString;
	           }else{
	        	   return null;
	           }   
	       } catch (IOException e) {      
	           ExceptionManage.dispose(e,this.getClass());  
	           return null;
	    }      
	       
	  }
	
	public boolean killthread(String threadPID){
		   if(threadPID==null){return false;}
		   Process process = null;      
	       BufferedReader bufReader = null;      
	       String bufReadLineString = null;      
	       try {      
	           process = Runtime.getRuntime().exec( "tskill "+threadPID);      
	           bufReader = new BufferedReader(new InputStreamReader(process.getInputStream()));      
	           for (int i = 0; i < 6 && bufReader != null; i++) {      
	        	   bufReadLineString =bufReadLineString+  bufReader.readLine()+"\n";      
	               
	           }      	            
	           System.out.println("--kill end-- "+bufReadLineString );
	           return true;
	       } catch (IOException e) {      
	           ExceptionManage.dispose(e,this.getClass());  
	           return false;
	       }    
	}
}

package com.nms.ui.ptn.systemManage.monitor.view;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MyJpanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4776654032948164825L;
	private String imagePath ;
	
	public MyJpanel(String imagePath){
		this.imagePath = imagePath;
		this.setSize(new Dimension(130, 159));
	}
	
	public void paintComponent(Graphics g)
	
	 {
	        int x=0,y=0;
	        java.net.URL imgURL=getClass().getResource(imagePath);
	        //test.jpg是测试图片，与Demo.java放在同一目录下
	        ImageIcon icon=new ImageIcon(imgURL);
	        g.drawImage(icon.getImage(),x,y,getSize().width,getSize().height,this);
	 }
}

package com.nms.ui.ptn.alarm.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;

/**
 * 圆形按钮
 * @author guoqc
 *
 */
public class CircleButton extends JButton {
	private static final long serialVersionUID = -1766531611741504188L;
	private Color BUTTON_BAK_COLOR1_2 = new Color(108, 135, 210, 255);  
	private Color BUTTON_BAK_COLOR2_2 = new Color(180, 230, 250, 255);  

	public CircleButton() {
	    // 这些声明把按钮扩展为一个圆而不是一个椭圆。
	    Dimension size = getPreferredSize();
	    size.width = size.height = 18;
	    setPreferredSize(size);
	    //这个调用使JButton不画背景，而允许我们画一个圆的背景。
	    setContentAreaFilled(false);
	    this.setFocusPainted(false);
	    this.setBorderPainted(false);
	  }
	
	  // 画圆的背景和标签
	  protected void paintComponent(Graphics g) {
	    if (getModel().isArmed()) {
	      // 你可以选一个高亮的颜色作为圆形按钮类的属性
	      g.setColor(Color.lightGray);
	    }
	    else {
	      g.setColor(getBackground());
	    }
	    g.fillOval(0, 0, getSize().width - 1,
	               getSize().height - 1);
	    //这个调用会画一个标签和焦点矩形。
	    super.paintComponent(g);
	  }
	
	  // 用简单的弧画按钮的边界。
	  protected void paintBorder(Graphics g) {
	    g.setColor(getForeground());
	    g.drawOval(0, 0, getSize().width - 1,
	               getSize().height - 1);
	    
	    //让按钮显示凸起效果
//	    Graphics2D g2d = (Graphics2D) g.create();  
//	    int h = getHeight();  
//        int w = getWidth();  
//        RoundRectangle2D.Float r2d2 = new RoundRectangle2D.Float(0, 0, w, h, h, h);  
//	     g2d.clip(r2d2);  
//	     GradientPaint gp2 = new GradientPaint(0.0F, 0.0F, BUTTON_BAK_COLOR2_2, 0.0F,  
//	              h, BUTTON_BAK_COLOR1_2, true);  
//	     g2d.setPaint(gp2);  
//	     g2d.fillRect(0, 0, w, h); 
	  }
}

package com.nms.ui.ptn.alarm.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;

public class AlarmBack extends PtnDialog
{
	/**
	 * 新增告警备注功能，add by dxh
	 */
	private static final long serialVersionUID = 2325034646192383888L;
	private JTextArea jta;
	private JButton jbtn;
	private JPanel jpbtn;
	@SuppressWarnings("unused")
	private int id;
	private String remarks;	
	
	AlarmBack(int id)
	{
		init();
		this.setId(id);
	}

	public void init()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = this.getSize();
		if (frameSize.height > screenSize.height)
		{
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width)
		{
			frameSize.width = screenSize.width;
		}
		this.setLocation((screenSize.width - frameSize.width) / 3,
				(screenSize.height - frameSize.height) /3);

		jta = new JTextArea();
		jbtn = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIRM));
		ActionListener btnOKActionListener = new ActionListener(){
            @Override
			public void actionPerformed(ActionEvent e)
            {
                btnOk_actionPerformed(e);
            }
        };
		jbtn.addActionListener(btnOKActionListener);
		jpbtn  = new JPanel();
		jpbtn.add(jbtn);
		FlowLayout flowLayout1 = new FlowLayout();
		flowLayout1.setAlignment(FlowLayout.RIGHT);
		jpbtn.setLayout(flowLayout1);
		this.add(jta);
		this.add(jpbtn,BorderLayout.SOUTH);
		this.setTitle(ResourceUtil.srcStr(StringKeysLbl.LBL_ALARM_REMARK));
	}

	public void btnOk_actionPerformed(ActionEvent e)
	{
		this.setRemarks(this.jta.getText());
		this.dispose();
	}
	
	public JTextArea getJta()
	{
		return jta;
	}
	public String getRemarks()
	{
		return remarks;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public static void main(String[] args)
	{
		new AlarmBack(5);
	}
}

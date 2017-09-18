package com.nms.ui.ptn.systemconfig;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.nms.ui.manager.control.NeTreePanel;
import com.nms.ui.ptn.systemconfig.dialog.udConfigure.view.UploadDownloadConfigureRightPanle;


/**
 * 上载/下载配置文件主面板
 * @author pc
 *
 */
public class UploadDownloadConfigurePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UploadDownloadConfigureRightPanle uploadDownloadConfigureRightPanle;
	private NeTreePanel neTreePanel;
	private JSplitPane splitPane=null;
	
	public UploadDownloadConfigurePanel(){
		this.initComponents();
		this.setLayout();
		
	}

	private void initComponents() {
		uploadDownloadConfigureRightPanle = new UploadDownloadConfigureRightPanle();
		neTreePanel = new NeTreePanel(true,2,uploadDownloadConfigureRightPanle.getTable(),false);
		this.splitPane=new JSplitPane();
		this.splitPane.add(this.neTreePanel, JSplitPane.LEFT);
		this.splitPane.add(this.uploadDownloadConfigureRightPanle, JSplitPane.RIGHT);
	}
	
	private void setLayout() {
		GridBagLayout panelLayout = new GridBagLayout();
		this.setLayout(panelLayout);
		GridBagConstraints c = null;
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		panelLayout.setConstraints(splitPane, c);
		this.add(splitPane);
	}

	public UploadDownloadConfigureRightPanle getUploadDownloadConfigureRightPanle() {
		return uploadDownloadConfigureRightPanle;
	}

	public void setUploadDownloadConfigureRightPanle(UploadDownloadConfigureRightPanle uploadDownloadConfigureRightPanle) {
		this.uploadDownloadConfigureRightPanle = uploadDownloadConfigureRightPanle;
	}

//	public UploadDownloadConfigureLeftPanle getUploadDownloadConfigureLeftPanle() {
//		return uploadDownloadConfigureLeftPanle;
//	}
//
//	public void setUploadDownloadConfigureLeftPanle(UploadDownloadConfigureLeftPanle uploadDownloadConfigureLeftPanle) {
//		this.uploadDownloadConfigureLeftPanle = uploadDownloadConfigureLeftPanle;
//	}
	
}

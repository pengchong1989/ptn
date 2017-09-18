package com.nms.ui.ptn.systemconfig.dialog.fieldConfig.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.system.Field;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.keys.StringKeysTitle;
import com.nms.ui.ptn.safety.roleManage.RootFactory;
import com.nms.ui.ptn.systemconfig.NeConfigView;
import com.nms.ui.ptn.systemconfig.dialog.fieldConfig.controller.SiteListController;

public class FieldConfigRightPanel extends ContentView<SiteInst>{
	
	private Field field;
	private NeConfigView neConfigView;
	private PtnButton configButton;
	private PtnButton correctTimeButton;
	private PtnButton clearButton; 
	private JButton clearSiteButton;
	private PtnButton dataUploadButton; //数据下载按钮
	/**   
	*   
	* @since Ver 1.1   
	*/   
	private static final long serialVersionUID = -7673414709008910387L;
	
	public FieldConfigRightPanel(NeConfigView neConfigView) {
		super("siteInstTable",RootFactory.DEPLOY_MANAGE);
		try {
			init();
			this.setField(neConfigView.getLeftPane().constField);
			this.setNeConfigView(neConfigView);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	@Override
	public void setController() {
		super.controller = new SiteListController(this);
	}
	
	private void init() throws Exception {

		getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysLbl.LBL_CONFIG_LIST)));
		getSynchroButton().setWait(false);
		setLayout();
		try {
			getController().refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	@Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needButtons = new ArrayList<JButton>();
		needButtons.add(getAddButton());
		needButtons.add(getSearchButton());
		return needButtons;
	}
	
	@Override
	public List<JButton> setAddButtons() {
		this.configButton=new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_ISSUED),false,RootFactory.DEPLOY_MANAGE);
		this.configButton.addActionListener(new MyActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			SiteListController siteListController=(SiteListController) getController();
			siteListController.confirmActionPerformed();
		}

		@Override
		public boolean checking() {		
			return true;
		}
	});
	this.correctTimeButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CURRECTTIME),false,RootFactory.DEPLOY_MANAGE);
	this.correctTimeButton.addActionListener(new MyActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			final TimeDialog filterDialog = new TimeDialog();			
			filterDialog.setLocation(UiUtil.getWindowWidth(filterDialog.getWidth()), UiUtil.getWindowHeight(filterDialog.getHeight()));
//			filterDialog.addWindowListener(new WindowAdapter() {
//				@Override
//				public void windowClosed(WindowEvent e) {
//					filterDialog.dispose();
//				}
//			});
		UiUtil.showWindow(filterDialog, 490, 300);
		}

		@Override
		public boolean checking() {
			
			return true;
		}
	});
	this.clearButton = new PtnButton(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_INIT),false,RootFactory.DEPLOY_MANAGE);
	this.clearButton.addActionListener(new MyActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			int result = DialogBoxUtil.confirmDialog(neConfigView.getRightPanel(), ResourceUtil.srcStr(StringKeysTip.TIP_INITIALISE));
			if(result == 0){
				SiteListController siteListController=(SiteListController) getController();
				siteListController.clearSiteActionPerformed();
			}
		}

		@Override
		public boolean checking() {
			
			return true;
		}
	});
	this.clearSiteButton = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CLEAR_DATA));
	this.clearSiteButton.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			int result = DialogBoxUtil.confirmDialog(neConfigView.getRightPanel(), ResourceUtil.srcStr(StringKeysTip.TIP_IS_DELETE));
			if(result == 0){
				SiteListController siteListController=(SiteListController) getController();
				siteListController.clearDataActionPerformed();
			}
		}
	}); 
	this.dataUploadButton = new PtnButton(ResourceUtil.srcStr(StringKeysTitle.TIT_DATE_DOWNLOAD),RootFactory.DEPLOY_MANAGE);
	this.dataUploadButton.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			SiteListController siteListController=(SiteListController) getController();
			siteListController.dataDownLoadActionPerformed();
		}
	});
	
	List<JButton> needButtons = new ArrayList<JButton>();
	needButtons.add(this.configButton);
	needButtons.add(this.correctTimeButton);
	needButtons.add(this.dataUploadButton);
	
	return needButtons;
	}

	public void setLayout(){
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
		panelLayout.setConstraints(getContentPanel(), c);
		this.add(getContentPanel());

	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public NeConfigView getNeConfigView() {
		return neConfigView;
	}

	public void setNeConfigView(NeConfigView neConfigView) {
		this.neConfigView = neConfigView;
	}

	public PtnButton getCorrectTimeButton() {
		return correctTimeButton;
	}

	public void setCorrectTimeButton(PtnButton correctTimeButton) {
		this.correctTimeButton = correctTimeButton;
	}

	public PtnButton getClearButton() {
		return clearButton;
	}

	public void setClearButton(PtnButton clearButton) {
		this.clearButton = clearButton;
	}
}

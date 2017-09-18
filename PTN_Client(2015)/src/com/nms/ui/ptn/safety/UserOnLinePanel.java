package com.nms.ui.ptn.safety;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.nms.db.bean.system.loginlog.LoginLog;
import com.nms.db.enums.EOperationLogType;
import com.nms.service.impl.util.ResultString;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.keys.StringKeysTitle;
import com.nms.ui.ptn.safety.controller.UserOnLinePanelController;
import com.nms.ui.ptn.safety.roleManage.RootFactory;
/**
 * 显示用户在Panel
 * @author Administrator
 *
 */
public class UserOnLinePanel extends ContentView<LoginLog>{
	private static final long serialVersionUID = 5697078194143066764L;


	/**
	 * 
	 */
	public UserOnLinePanel(){
		super("UserOnLineTable",RootFactory.SATYMODU);
		init();
		
	}

	public void init(){	
		try{
			this.setViewLayout();
			setLayout();
			this.getController().refresh();
		}catch(Exception e){
			ExceptionManage.dispose(e,this.getClass());
		}		
	}
	
	private void setViewLayout(){
		getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTitle.TT_SELECT_USERONLINE)));
	}
	
	@Override
	public void setTablePopupMenuFactory() {
		setMenuFactory(null);
	}

	@Override
	public Dimension setDefaultSize() {
		return new Dimension(160, ConstantUtil.INT_WIDTH_THREE);
	}
	
	@Override	
	public void setController() {
		controller = new UserOnLinePanelController(this);
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
		panelLayout.setConstraints(getContentPanel(), c);
		this.add(getContentPanel());
	}
	@Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(getUpdateButton());
		needRemoveButtons.add(getAddButton());
		needRemoveButtons.add(getDeleteButton());
		needRemoveButtons.add(getSynchroButton());
		needRemoveButtons.add(getSearchButton());
		return needRemoveButtons;
	}
	UserOnLinePanelController userOnLinePanelController=new UserOnLinePanelController(this);
	@Override
	public List<JButton> setAddButtons(){
		final PtnButton shotButton=new PtnButton(ResourceUtil.srcStr(StringKeysBtn.SHOT_UPDATE),false,RootFactory.SATY_MANAGE);
		//JButton clearButton=new JButton(ResourceUtil.srcStr(StringKeysBtn.CLEAR_UPDATE));
		shotButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				 JPanel contentPanel=new JPanel();
				
					if (getTable().getAllSelect().size() == 0) {
						DialogBoxUtil.errorDialog(contentPanel, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_MORE));					
						return;
					}
					else{
						try{							
								//确实注销吗							
								int result = DialogBoxUtil.confirmDialog(contentPanel, ResourceUtil.srcStr(StringKeysTip.TIP_IS_SHOT));
								if(result==0){						
									userOnLinePanelController.logOut()		;	
									//添加日志记录
									shotButton.setOperateKey(EOperationLogType.USERLOGOUT.getValue());
									shotButton.setResult(1);
									this.insertOpeLog(EOperationLogType.USERLOGOUT.getValue(), ResultString.CONFIG_SUCCESS, null, null);	
								}																						
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}
			}

			private void insertOpeLog(int operationType, String result, Object oldMac, Object newMac){
				AddOperateLog.insertOperLog(shotButton, operationType, result, oldMac, newMac, 0,ResourceUtil.srcStr(StringKeysTitle.TT_SELECT_USERONLINE),"");		
			}
		});
		List<JButton> list=new ArrayList<JButton>();
		list.add(shotButton);
		
		return list;
	}

	
	
}

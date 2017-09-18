package com.nms.ui.ptn.ne.reset.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import com.nms.db.bean.equipment.card.CardInst;
import com.nms.db.bean.equipment.slot.SlotInst;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.equipment.slot.SlotService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.ne.reset.controller.ResetController;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

public class ResetPanel extends ContentView<CardInst> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PtnButton resetButton;
	public ResetPanel() {	
		super("soltReset",RootFactory.CORE_MANAGE);
		init();
		addlisence();
		this.getRefreshButton().doClick();
	}

	public void init() {
		getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysPanel.PANEL_SOLT_RESET)));
		setLayout();
	}
	
	public void addlisence(){
		resetButton.addActionListener(new MyActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int result = DialogBoxUtil.confirmDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_IS_RESET));
				if (result == 1) {
					return;
				}
				 try {
					 if (getAllSelect().size() == 0) {
							DialogBoxUtil.errorDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_MORE));
						} else if (getAllSelect().size() >1){
							DialogBoxUtil.errorDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
						}else {
							CardInst cardInst = getAllSelect().get(0);
							DispatchUtil slotResetDispatch = new DispatchUtil(RmiKeys.RMI_SLOTRESET);
			 				SlotService_MB slotService=null;
							try {
								slotService =(SlotService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SLOT);
								SlotInst slot=new SlotInst();
								slot.setId(cardInst.getSlotId());
								slot=slotService.select(slot).get(0);
								cardInst.setSlotNum(slot.getNumber());
								String resultStr = slotResetDispatch.excuteInsert(cardInst);								
								this.insertOpeLog(EOperationLogType.RESETMANAGER.getValue(), resultStr, null,cardInst);								
								DialogBoxUtil.succeedDialog(null, resultStr);
							} catch (Exception e) {
								ExceptionManage.dispose(e,this.getClass());
							}finally{
								UiUtil.closeService_MB(slotService);
							}
						}
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
				
			}
			
			
			private void insertOpeLog(int operationType, String result, Object oldMac, Object newMac){			
			    AddOperateLog.insertOperLog(null, operationType, result, oldMac, newMac,ConstantUtil.siteId,ResourceUtil.srcStr(StringKeysPanel.PANEL_SOLT_RESET),"addCard");				
			}
			
			@Override
			public boolean checking() {
				// TODO Auto-generated method stub
				return true;
			}
		});
			
	
		
	}
	public void setLayout() {
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
		panelLayout.setConstraints(this.getContentPanel(), c);
		this.add(this.getContentPanel());
	}
	@Override
	public void setController() {
		controller = new ResetController(this);
		
	}
	@Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(getSearchButton());
		needRemoveButtons.add(getAddButton());
		needRemoveButtons.add(getDeleteButton());
		needRemoveButtons.add(getSynchroButton());
		needRemoveButtons.add(getUpdateButton());
		return needRemoveButtons;
	}
	
	@Override
	public List<JButton> setAddButtons(){
		List<JButton> needAddButtons = new ArrayList<JButton>();
		resetButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_RESET),true,RootFactory.CORE_MANAGE);
		needAddButtons.add(resetButton);
		return needAddButtons;
	}
}

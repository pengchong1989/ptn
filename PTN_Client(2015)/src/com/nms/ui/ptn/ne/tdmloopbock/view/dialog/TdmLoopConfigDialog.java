package com.nms.ui.ptn.ne.tdmloopbock.view.dialog;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import com.nms.db.bean.equipment.port.E1Info;
import com.nms.db.bean.equipment.port.TdmLoopInfo;
import com.nms.db.bean.ptn.SiteRoate;
import com.nms.db.enums.EOperationLogType;
import com.nms.db.enums.ETdmLoopLineType;
import com.nms.db.enums.ETdmLoopType;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.ptn.SiteRoateService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.ptn.safety.roleManage.RootFactory;
/**
 * @author guoqc
 */
public class TdmLoopConfigDialog extends PtnDialog {
	private static final long serialVersionUID = -4147934782855305867L;
	private E1Info e1Info;
	private TdmLoopInfo tdmLoop =null;
	
	public TdmLoopConfigDialog(E1Info e1Info, String title, int type,TdmLoopInfo tdm) {
		this.setTitle(title);
		this.e1Info = e1Info;
		this.tdmLoop = tdm;
//		this.tdmLoop.setLoopType(type);
//		this.tdmLoop.setSiteId(ConstantUtil.siteId);
		this.initComponent();
		this.setLayout();
		this.addListener();
		UiUtil.showWindow(this, 350, 300);
	}

	private void initComponent() {
		this.switchlLbl = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_TMD_ON_OFF));//开关设置
		this.openModel = new JRadioButton("开");//开
		this.openModel.setName("1");		
		this.closeModel = new JRadioButton("关");//关
		this.closeModel.setName("0");
		if(this.tdmLoop!=null){
			if(this.tdmLoop.getSwitchStatus()==1){
				this.openModel.setSelected(true);
			}else{
				this.closeModel.setSelected(true);
			}
		}else{
			this.openModel.setSelected(true);
		}
		this.switchBtnGroup = new ButtonGroup();
		this.switchBtnGroup.add(this.openModel);
		this.switchBtnGroup.add(this.closeModel);
		this.isAllLoopLbl = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_TMD_LINE));//全部链路环回
		this.allSelect = new JRadioButton("是");//是
		this.allSelect.setName("4");
		this.notAllSelect = new JRadioButton("否");//否
		if(this.tdmLoop!=null){
			if(this.tdmLoop.getLegId()==4){
				this.allSelect.setSelected(true);
			}else{
				this.notAllSelect.setSelected(true);
			}
		}else{
			this.allSelect.setSelected(true);
		}
		this.notAllSelect.setName((this.e1Info.getLegId()-1)+"");
		this.allBtnGroup = new ButtonGroup();
		this.allBtnGroup.add(this.allSelect);
		this.allBtnGroup.add(this.notAllSelect);
		this.saveBtn = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE),true,RootFactory.CORE_MANAGE);
		this.cancelBtn = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		this.buttonPanel = new JPanel();
	}
	
	/**
	 *  主界面布局
	 */
	private void setLayout(){
		this.setButtonLayout();
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 40, 40, 100, 130, 40};
		componentLayout.columnWeights = new double[] { 0, 0, 0 };
		componentLayout.rowHeights = new int[] {30, 30, 30, 30, 30, 30};
		componentLayout.rowWeights = new double[] { 0.1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		this.setLayout(componentLayout);
	
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		//第一行
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.insets = new Insets(30, 10, 10, 10);
		componentLayout.setConstraints(this.switchlLbl, c);
		this.add(this.switchlLbl);
		c.gridx = 2;
		c.gridy = 0;
		componentLayout.setConstraints(this.openModel, c);
		this.add(this.openModel);
		c.gridx = 3;
		c.gridy = 0;
		componentLayout.setConstraints(this.closeModel, c);
		this.add(this.closeModel);
		// 第二行
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(10, 10, 15, 10);
		componentLayout.setConstraints(this.isAllLoopLbl, c);
		this.add(this.isAllLoopLbl);
		c.gridx = 2;
		c.gridy = 1;
		c.insets = new Insets(10, 10, 15, 10);
		componentLayout.setConstraints(this.allSelect, c);
		this.add(this.allSelect);
		c.gridx = 3;
		c.gridy = 1;
		componentLayout.setConstraints(this.notAllSelect, c);
		this.add(this.notAllSelect);
		//第五行 
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 3;
		c.gridy = 4;
		c.insets = new Insets(5, 5, 5, 5);
		componentLayout.setConstraints(this.buttonPanel, c);
		this.add(this.buttonPanel);
	}
	
	private void setButtonLayout() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] {250, 50, 50};
		componentLayout.columnWeights = new double[] {0.1, 0, 0};
		componentLayout.rowHeights = new int[] {30};
		componentLayout.rowWeights = new double[] {0};
		this.setLayout(componentLayout);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		componentLayout.setConstraints(this.saveBtn, c);
		buttonPanel.add(this.saveBtn);
		c.gridx=2;
		componentLayout.setConstraints(this.cancelBtn, c);
		buttonPanel.add(this.cancelBtn);
		
	}
	
	/**
	 * 添加监听事件
	 */
	private void addListener(){
		this.saveBtn.addActionListener(new MyActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveAction();
				dispose();			
			}

			@Override
			public boolean checking() {
				return true;
			}
			
		});			
	
		this.cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	/**
	 * 保存按钮事件
	 */
	private void saveAction(){
		SiteRoateService_MB siteRoate=null;
		SiteRoate roate=null;
		TdmLoopInfo old =null;
		TdmLoopInfo old1 =null;
		TdmLoopInfo old2 =null;
		TdmLoopInfo old3 =null;
		TdmLoopInfo old4 =null;
		TdmLoopInfo newtmd =null;
		List<SiteRoate> sroate=null;
		List<SiteRoate> sroate1=null;
		List<SiteRoate> sroate2=null;
		List<SiteRoate> sroate3=null;
		List<SiteRoate> sroate4=null;
		try {
			this.getTdmLoopInfo();
			siteRoate =(SiteRoateService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITEROATE);
			roate=new SiteRoate();
			if(this.tdmLoop.getLoopType()==0){
				roate.setType("TdmLoopLine");
			}else{
				roate.setType("TdmLoopEquip");
			}
			roate.setSiteId(ConstantUtil.siteId);
			
			newtmd=new TdmLoopInfo();
			newtmd.setSiteId(this.tdmLoop.getSiteId());
			newtmd.setTmdType(ETdmLoopType.forms(this.tdmLoop.getLoopType()));
			newtmd.setSwitchStatus(this.tdmLoop.getSwitchStatus());
			newtmd.setTdmLegId(ETdmLoopLineType.forms(this.tdmLoop.getLegId()));
			newtmd.setLoopType(this.tdmLoop.getLoopType());
			if(this.tdmLoop.getLegId()==4){
				roate.setRoate(1);	
				sroate1=siteRoate.querSiteRoateByRoate(roate);
				if(sroate1!=null && sroate1.size()==1){
					old1 =new TdmLoopInfo();
					old1.setTmdType(ETdmLoopType.forms(this.tdmLoop.getLoopType()));
					old1.setLoopType(this.tdmLoop.getLoopType());
					old1.setLegId(sroate1.get(0).getTypeId());
					old1.setTdmLegId(ETdmLoopLineType.forms(sroate1.get(0).getTypeId()));
					old1.setSiteId(ConstantUtil.siteId);
					old1.setSwitchStatus(sroate1.get(0).getSiteRoate());
				}else{
					old1=new TdmLoopInfo();	
					old1.setTmdType(ETdmLoopType.forms(this.tdmLoop.getLoopType()));
					old1.setTdmLegId(ETdmLoopLineType.forms(0));
				}
				roate.setRoate(2);
				sroate2=siteRoate.querSiteRoateByRoate(roate);
				if(sroate2!=null && sroate2.size()==1){
					old2 =new TdmLoopInfo();
					old2.setTmdType(ETdmLoopType.forms(this.tdmLoop.getLoopType()));
					old2.setLoopType(this.tdmLoop.getLoopType());
					old2.setLegId(sroate2.get(0).getTypeId());
					old2.setTdmLegId(ETdmLoopLineType.forms(sroate2.get(0).getTypeId()));
					old2.setSiteId(ConstantUtil.siteId);
					old2.setSwitchStatus(sroate2.get(0).getSiteRoate());
				}else{
					old2=new TdmLoopInfo();	
					old2.setTmdType(ETdmLoopType.forms(this.tdmLoop.getLoopType()));
					old2.setTdmLegId(ETdmLoopLineType.forms(1));
					
				}
				roate.setRoate(3);
				sroate3=siteRoate.querSiteRoateByRoate(roate);
				if(sroate3!=null && sroate3.size()==1){
					old3 =new TdmLoopInfo();
					old3.setTmdType(ETdmLoopType.forms(this.tdmLoop.getLoopType()));
					old3.setLoopType(this.tdmLoop.getLoopType());
					old3.setLegId(sroate3.get(0).getTypeId());
					old3.setTdmLegId(ETdmLoopLineType.forms(sroate2.get(0).getTypeId()));
					old3.setSiteId(ConstantUtil.siteId);
					old3.setSwitchStatus(sroate3.get(0).getSiteRoate());					
				}else{
					old3=new TdmLoopInfo();		
					old3.setTmdType(ETdmLoopType.forms(this.tdmLoop.getLoopType()));
					old3.setTdmLegId(ETdmLoopLineType.forms(2));
				}
				roate.setRoate(4);
				sroate4=siteRoate.querSiteRoateByRoate(roate);
				if(sroate4!=null && sroate4.size()==1){
					old4 =new TdmLoopInfo();
					old4.setTmdType(ETdmLoopType.forms(this.tdmLoop.getLoopType()));
					old4.setLoopType(this.tdmLoop.getLoopType());
					old4.setLegId(sroate4.get(0).getTypeId());
					old4.setTdmLegId(ETdmLoopLineType.forms(sroate4.get(0).getTypeId()));
					old4.setSiteId(ConstantUtil.siteId);
					old4.setSwitchStatus(sroate3.get(0).getSiteRoate());
				}else{
					old4=new TdmLoopInfo();		
					old4.setTmdType(ETdmLoopType.forms(this.tdmLoop.getLoopType()));
					old4.setTdmLegId(ETdmLoopLineType.forms(3));
				}
			}else{
				roate.setRoate(this.tdmLoop.getE1Id());
				sroate=siteRoate.querSiteRoateByRoate(roate);
				if(sroate!=null && sroate.size()==1){
					old =new TdmLoopInfo();
					old.setTmdType(ETdmLoopType.forms(this.tdmLoop.getLoopType()));
					old.setLoopType(this.tdmLoop.getLoopType());
					old.setLegId(this.tdmLoop.getLegId());
					old.setTdmLegId(ETdmLoopLineType.forms(sroate.get(0).getTypeId()));
					old.setSiteId(ConstantUtil.siteId);
					old.setSwitchStatus(sroate.get(0).getSiteRoate());
				}else{
					old=new TdmLoopInfo();	
					old.setTmdType(ETdmLoopType.forms(this.tdmLoop.getLoopType()));
					old.setTdmLegId(ETdmLoopLineType.forms(this.tdmLoop.getLegId()));
				}
			}
			
	
			this.tdmLoop.setTmdType(ETdmLoopType.forms(this.tdmLoop.getLoopType()));
			this.tdmLoop.setTdmLegId(ETdmLoopLineType.forms(this.tdmLoop.getLegId()));
			DispatchUtil tdmLoopBackDispatch = new DispatchUtil(RmiKeys.RMI_TDMLOOPBACK);
			String resultStr = tdmLoopBackDispatch.excuteInsert(this.tdmLoop);
			DialogBoxUtil.succeedDialog(null, resultStr);
			if(this.tdmLoop.getLegId()==4){
				newtmd.setTdmLegId(ETdmLoopLineType.forms(0));
				this.insertOpeLog(EOperationLogType.TDMLOOPCONFIG.getValue(), resultStr, old1,newtmd);
				newtmd.setTdmLegId(ETdmLoopLineType.forms(1));
				this.insertOpeLog(EOperationLogType.TDMLOOPCONFIG.getValue(), resultStr, old2,newtmd);
				newtmd.setTdmLegId(ETdmLoopLineType.forms(2));
				this.insertOpeLog(EOperationLogType.TDMLOOPCONFIG.getValue(), resultStr, old3,newtmd);
				newtmd.setTdmLegId(ETdmLoopLineType.forms(3));
				this.insertOpeLog(EOperationLogType.TDMLOOPCONFIG.getValue(), resultStr, old4,newtmd);
			}else{
				this.insertOpeLog(EOperationLogType.TDMLOOPCONFIG.getValue(), resultStr, old,this.tdmLoop);
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			UiUtil.closeService_MB(siteRoate);
			old =null;
			old1 =null;
			old2 =null;
			old3 =null;
			old4 =null;
			newtmd =null;
			sroate=null;
			sroate1=null;
			sroate2=null;
			sroate3=null;
			sroate4=null;
		}
	}
	
	private void insertOpeLog(int operationType, String result, TdmLoopInfo oldMac, TdmLoopInfo newMac){
		SiteService_MB service = null;
		try {
			service = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			String siteName=service.getSiteName(newMac.getSiteId());
			oldMac.setSiteName(siteName);
			newMac.setSiteName(siteName);
		    AddOperateLog.insertOperLog(saveBtn, operationType, result, oldMac, newMac, newMac.getSiteId(),ResourceUtil.srcStr(StringKeysPanel.PANEL_SOLT_TDMLOOPBACK),"TDMLOOP");		
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(service);
		}
	}
	/**
	 * 获取对象
	 */
	private void getTdmLoopInfo() {
		JRadioButton radioButton = null;
		try {
			if(this.tdmLoop==null){
				this.tdmLoop=new TdmLoopInfo();
			}
			this.tdmLoop.setSiteId(ConstantUtil.siteId);
			//遍历所有radiobutton 获取选中的button
			Enumeration<AbstractButton> elements = this.switchBtnGroup.getElements();
			while (elements.hasMoreElements()) {
				radioButton = (JRadioButton) elements.nextElement();
				if (radioButton.isSelected()) {
					this.tdmLoop.setSwitchStatus(Integer.parseInt(radioButton.getName()));
					break;
				}
			}
			Enumeration<AbstractButton> elements1 = this.allBtnGroup.getElements();
			while (elements1.hasMoreElements()) {
				radioButton = (JRadioButton) elements1.nextElement();
				if (radioButton.isSelected()) {
					this.tdmLoop.setLegId(Integer.parseInt(radioButton.getName()));
					break;
				}
			}
		}catch(Exception e){
			ExceptionManage.dispose(e, this.getClass());
		}
	}
	
	private JLabel switchlLbl;//开关设置
	private JRadioButton openModel;//开
	private JRadioButton closeModel;//关
	private ButtonGroup switchBtnGroup;
	private JLabel isAllLoopLbl;//全部链路环回 
	private JRadioButton allSelect;//是
	private JRadioButton notAllSelect;//否
	private ButtonGroup allBtnGroup;
	private PtnButton saveBtn;//确认
	private JButton cancelBtn;//取消
	private JPanel buttonPanel;
}
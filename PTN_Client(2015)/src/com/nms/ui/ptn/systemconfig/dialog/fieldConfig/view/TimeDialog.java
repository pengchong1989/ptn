package com.nms.ui.ptn.systemconfig.dialog.fieldConfig.view;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.tree.TreePath;
import twaver.Element;
import twaver.tree.ElementNode;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.enums.EManufacturer;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.CheckingUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DateUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.NeTreePanel;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysOperaType;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.manager.keys.StringKeysTip;




/**
 * 校时
 * @author lp
 *
 */
public class TimeDialog  extends PtnDialog {
	private static final long serialVersionUID = -6767156953328928386L;
	private PtnButton confirm;  //确认按钮
	private JButton cancel;   //取消按钮
	private JLabel lblTaskObj; //告警对象
	private NeTreePanel neTreePanel = null; // 网元树panel	
	private JLabel CurrTimeJLabel;//当前时间	
	private JTextField Time;	
	private JPanel buttonPanel;
	

	public TimeDialog() {
		
		init();
	}	
	public PtnButton getConfirm() {
		return confirm;
	}
	public void setConfirm(PtnButton confirm) {
		this.confirm = confirm;
	}
	public void init() {
		initComponents();
		setLayout();
		addListener();
	}
	
	
	private void addListener() {		
		//取消按钮
	cancel.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			TimeDialog.this.dispose();
		}
	});
	
    
	
	confirm.addActionListener(new MyActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
		 TimeDialog.this.curtime();
		}
		@Override
		public boolean checking() {
			return true;
		}
	});
	}
	public Element getElement(TreeExpansionEvent e){
		TreePath path = e.getPath();
		  if (path != null) {
		    Object comp = path.getLastPathComponent();
		    if (comp instanceof ElementNode) {
		      ElementNode node = ((ElementNode) comp);
		      return node.getElement();
		    }
		  }
		  return null;
	}
	
	private void initComponents() {

		this.setTitle(ResourceUtil.srcStr(StringKeysBtn.BTN_CURRECTTIME));
		CurrTimeJLabel= new JLabel(ResourceUtil.srcStr(StringKeysObj.CURRENT_TIME));	
		lblTaskObj = new JLabel(ResourceUtil.srcStr(StringKeysObj.CURRENT_TIME_OBJECT));
		this.neTreePanel=new NeTreePanel(false, 2, null,false);
		this.buttonPanel=new JPanel();
		Time = new JTextField(100);		
		confirm = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIRM),true);
		cancel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		long l = System.currentTimeMillis();
		Time.setText(DateUtil.getDate(DateUtil.FULLTIME));		
		this.neTreePanel.setLevel(2);
	
	
	}

	//校正网元
	private void curtime() {
		String result = null;
        String time=this.Time.getText();
		try {
			if(this.neTreePanel.getSelectSiteInst().size()<=0){
				DialogBoxUtil.confirmDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_CHOOSE_CURRENT));
			}else{
				if (CheckingUtil.checking(time, CheckingUtil.TIME_REGULAR)) {
					//校时								
					SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.FULLTIME);				
					long l=sdf.parse(time).getTime();										
					List<SiteInst> siteInst=null;
					siteInst=new ArrayList<SiteInst>();
					siteInst = this.neTreePanel.getSelectSiteInst();										 
					SiteService_MB siteService = null;
					try {
							siteService=(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
							for(int m=0;m<siteInst.size();m++){
							    if(siteService.getManufacturer(((SiteInst) siteInst.get(m)).getSite_Inst_Id()) == EManufacturer.CHENXIAO.getValue()){
							      DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_TIMEERROR));
							      return;
							      }
								}
							DispatchUtil siteDispatch = new DispatchUtil(RmiKeys.RMI_SITE);
							List<Integer> siteIdList = new ArrayList<Integer>();
							for(int n=0;n<siteInst.size();n++){
								siteIdList.add(siteInst.get(n).getSite_Inst_Id());
								siteInst.get(n).setL(l);
								String str = siteDispatch.currectTime(siteInst.get(n));
								AddOperateLog.insertOperLog(confirm, EOperationLogType.SITELISTCURRECTTIME.getValue(), str, 
										null, siteInst.get(n), siteInst.get(n).getSite_Inst_Id(), ResourceUtil.srcStr(StringKeysOperaType.BTN_SITELIST_CURRECTTIME), "currecttimeLog");
							}
							WhImplUtil wu = new WhImplUtil();
							String str = wu.getNeNames(siteIdList);
							if(str.equals("")){
								result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
							}else{
								result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+str+ResultString.NOT_ONLINE_SUCCESS;
							}
							this.dispose();										
							this.confirm.setOperateKey(EOperationLogType.SITELISTCURRECTTIME.getValue());
								int operationResult=0;
							if(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS).equals(result)){
								operationResult=1;
								}else{
								operationResult=2;
										}
							this.confirm.setResult(operationResult);
								DialogBoxUtil.succeedDialog(this, result);
								} catch (Exception e) {
								ExceptionManage.dispose(e,this.getClass());
								} finally {
									UiUtil.closeService_MB(siteService);
										}
				
				}else{
         			DialogBoxUtil.confirmDialog(this, ResourceUtil.srcStr(StringKeysLbl.LBL_CURRENTTIME_ERROR));	
         			return;
         		    }	
				}
							
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	//按钮布局
	private void setButtonLayout() {
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] {  245,70,80 };
			layout.columnWeights = new double[] { 0.2, 0, 0};
			layout.rowHeights = new int[] { 10};
			layout.rowWeights = new double[] {0.1};
			this.setLayout(layout);
			GridBagConstraints c = new GridBagConstraints();
			//第一行
			c.fill = GridBagConstraints.BOTH;
			c.gridx = 1;
			c.gridy = 0;
			c.gridheight = 1;
			c.gridwidth = 1;
			c.insets = new Insets(5, 5, 5, 10);
			layout.addLayoutComponent(confirm, c);
			this.buttonPanel.add(confirm);
			c.gridx =2;
			layout.addLayoutComponent(this.cancel, c);
			this.buttonPanel.add(cancel);
		
	}
	
	private void setLayout() {
		setButtonLayout();
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] {20,40,20,40,20,40,20,40,20,40,20,40};
		layout.columnWeights = new double[] { 0, 0, 0, 0, 0,0, 0, 0, 0, 0,0, 0};
		layout.rowHeights = new int[] { 30, 30, 30, 30,20,30,20};
		layout.rowWeights = new double[] { 0, 0.1, 0, 0, 0, 0,0,0,0,0,0,0};
		this.setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
	   c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 0, 0, 5);
		layout.setConstraints(lblTaskObj, c);
		this.add(lblTaskObj);
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 2;
		c.gridwidth = 12;
		c.insets = new Insets(5, 0, 5, 5);
		layout.addLayoutComponent(this.neTreePanel, c);
		this.add(this.neTreePanel);
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 0, 0, 5);
		layout.addLayoutComponent(this.CurrTimeJLabel, c);
		this.add(this.CurrTimeJLabel);
				
		c.gridx = 1;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 8;
		c.insets = new Insets(5, 0, 0, 5);
		layout.addLayoutComponent(this.Time, c);
		this.add(this.Time);
				
		c.gridx=9;
		c.gridy = 5;
		c.gridheight = 1;
		c.gridwidth = 4;
		c.insets = new Insets(5, 5, 5, 10);
		layout.setConstraints(buttonPanel , c);
		this.add(buttonPanel );
	}
}

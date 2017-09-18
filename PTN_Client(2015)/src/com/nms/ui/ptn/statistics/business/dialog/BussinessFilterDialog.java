package com.nms.ui.ptn.statistics.business.dialog;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.NeTreePanel;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.statistics.business.ProfessController;


public class BussinessFilterDialog extends PtnDialog{
	private static final long serialVersionUID = -3649569000355874521L;
	private PtnButton confirm;
	private JButton cancel;
	private JButton clear;
	private JLabel lblQueryObj;
	private JLabel lblQueryType;
	private JComboBox cmbQueryType;
    private JPanel buttonPanel;
	private NeTreePanel neTreePanel;
	private ProfessController controller;
	private List<String> typeList =null;
	
	public BussinessFilterDialog(AbstractController controller){
		this.setModal(true);
		this.controller = (ProfessController) controller;
		this.initComponents();
		this.setLayout();
		this.initData();
		this.addListener();
		UiUtil.showWindow(this, 400, 400);
	}

	private void initComponents() {
		this.setTitle(ResourceUtil.srcStr(StringKeysBtn.BTN_FILTER));
		this.lblQueryType = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_QUERY_TYPE));
		this.cmbQueryType = new JComboBox();
		this.lblQueryObj = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_QUERY_OBJ));	
		typeList = new ArrayList<String>();
		this.typeList.add("ETH");
		this.neTreePanel = new NeTreePanel(4, this.getAllSiteList(), this.typeList, 1, false);
		this.confirm = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIRM),false);
		this.cancel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		this.buttonPanel = new JPanel();
		this.buttonPanel.add(confirm);
		this.buttonPanel.add(cancel);
		this.clear = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_FILTER_CLEAR));
	}

	/**
	 * 界面布局
	 */
	private void setLayout() {
		this.setCompentLayoutButton(buttonPanel,confirm,cancel);
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 40, 40, 40, 40,230 };
		layout.columnWeights = new double[] { 0, 0, 0, 0, 0.3 };
		layout.rowHeights = new int[] { 20, 20, 20, 20, 20, 20};
		layout.rowWeights = new double[] { 0, 0, 0, 0.3, 0.2,0, 0, 0, 0.2,0,0,0,0};
		this.setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 15, 10);
		layout.addLayoutComponent(lblQueryType, c);
		this.add(lblQueryType);
		c.gridx = 1;
		c.gridheight = 1;
		c.gridwidth = 5;
		c.insets = new Insets(5, 5, 15, 10);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.addLayoutComponent(cmbQueryType, c);
		this.add(cmbQueryType);
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 10);
		c.fill = GridBagConstraints.BOTH;
		layout.addLayoutComponent(lblQueryObj, c);
		this.add(lblQueryObj);
		
		c.gridx = 1;
		c.gridheight = 3;
		c.gridwidth = 5;
		c.insets = new Insets(5, 5, 5, 10);
		layout.addLayoutComponent(this.neTreePanel, c);
		this.add(neTreePanel);

		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 5;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 10);
		layout.addLayoutComponent(clear, c);
		this.add(clear);
		
		c.gridx = 1;
		c.gridheight = 1;
		c.gridwidth = 5;
		c.insets = new Insets(5, 5, 5, 5);
		layout.addLayoutComponent(buttonPanel, c);
		this.add(buttonPanel);
	}
	
	/**
	 * 按钮所在按钮布局
	 */
	private void setCompentLayoutButton(JPanel jpenl,JButton button1,JButton button2) {
		GridBagConstraints gridBagConstraints=null;
		GridBagLayout gridBagLayout = null;
		try {
			gridBagLayout = new GridBagLayout();
			gridBagConstraints = new GridBagConstraints();
			gridBagLayout.columnWidths=new int[]{100, 15, 15};
			gridBagLayout.columnWeights=new double[]{0,0};
			gridBagLayout.rowHeights=new int[]{21};
			gridBagLayout.rowWeights=new double[]{0};
			
			gridBagConstraints.insets=new Insets(5,5,5,5);
			gridBagConstraints= new GridBagConstraints();
			gridBagConstraints.fill=GridBagConstraints.NONE;
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(button1, gridBagConstraints);
			
			gridBagConstraints.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints.gridx = 2;
			gridBagLayout.setConstraints(button2, gridBagConstraints);
			
			jpenl.setLayout(gridBagLayout);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	
	/**
	 * 初始化数据
	 */
	private void initData() {
		ControlKeyValue con = new ControlKeyValue(1+"", "TUNNEL");
		this.cmbQueryType.addItem(con);
		con = new ControlKeyValue(2+"", "PW");
		this.cmbQueryType.addItem(con);
		con = new ControlKeyValue(3+"", "以太网业务");
		this.cmbQueryType.addItem(con);
		con = new ControlKeyValue(4+"", "TDM业务");
		this.cmbQueryType.addItem(con);
	}

	private void addListener() {
		//查询类型
		this.cmbQueryType.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				queryTypeChange();
			}
		});
		
		//保存按钮
		this.confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				saveBtnAction();
			}
		});
		
		// 取消按钮
		this.cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		// 清除按钮
		this.clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				clear();
			}
		});
	}

	/**
	 * 查询类型改变时,查询对象也要改变
	 * 类型是TUNNEL时,根据网元和NNI端口查
	 * 类型是PW时,根据网元和NNI端口查
	 * 类型为ETH业务时，根据网元和UNI端口查
	 * 类型为CES业务时，根据网元和PDH端口查
	 * 
	 */
	private void queryTypeChange() {
		ControlKeyValue con = (ControlKeyValue) this.cmbQueryType.getSelectedItem();
		if("PW".equals(con.getName())){
			typeList = new ArrayList<String>();
			this.typeList.add("ETH");
			this.neTreePanel.setFlag(false);
		    this.neTreePanel.setLevel(4,this.typeList,1);			
		}else if("TUNNEL".equals(con.getName())){
			typeList = new ArrayList<String>();
			this.typeList.add("ETH");
			this.neTreePanel.setFlag(false);
			this.neTreePanel.setLevel(4,this.typeList,1);		
		}else if("以太网业务".equals(con.getName())){
			typeList = new ArrayList<String>();
			this.typeList.add("ETH");
			this.neTreePanel.setFlag(false);
			this.neTreePanel.setLevel(4,this.typeList,2);
		}else if("TDM业务".equals(con.getName())){
			typeList = new ArrayList<String>();
			this.typeList.add("PDH");
			this.neTreePanel.setFlag(false);
			this.neTreePanel.setLevel(4,this.typeList,3);
		}
	}

	/**
	 * 保存按钮事件
	 */
	private void saveBtnAction() {
		ControlKeyValue con = (ControlKeyValue) this.cmbQueryType.getSelectedItem();
		if("PW".equals(con.getName())){
			//如果是pw类型,则根据网元或网元和端口过滤
			try {
				List<Integer> siteIdList = this.neTreePanel.getPrimaryKeyList("site");
				List<Integer> portIdList = this.neTreePanel.getPrimaryKeyList("port");
				this.controller.filterByPw(siteIdList, portIdList);
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}
		}else if("TUNNEL".equals(con.getName())){
			//如果是tunnel类型,则根据网元或者网元和端口过滤
			List<Integer> siteIdList = null;
			try {
				siteIdList = this.neTreePanel.getPrimaryKeyList("site");				
				List<Integer> portIdList = this.neTreePanel.getPrimaryKeyList("port");				
				this.controller.filterByTunnel(siteIdList, portIdList);
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}
		}else if("以太网业务".equals(con.getName())){
			//如果是eth类型,则根据网元或者网元和端口过滤
			try {
				List<Integer> siteIdList = this.neTreePanel.getPrimaryKeyList("site");
				List<Integer> portIdList = this.neTreePanel.getPrimaryKeyList("port");
				this.controller.filterByEth(siteIdList, portIdList);
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}
		}else{
			//如果是ces类型,则根据网元或者网元和端口过滤			
			try {
				List<Integer> siteIdList = this.neTreePanel.getPrimaryKeyList("site");
				List<Integer> portIdList = this.neTreePanel.getPrimaryKeyList("port");
				this.controller.filterByCes(siteIdList, portIdList);
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}
		}
		DialogBoxUtil.succeedDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS));
		this.dispose();
	}

	/**
	 * 清除按钮事件
	 */
	private void clear() {
		this.cmbQueryType.setSelectedIndex(0);
		this.neTreePanel.clear();
	}
	
	/**
	 * 查询所有网元
	 */
	public List<SiteInst> getAllSiteList(){
		List<SiteInst> siteList = new ArrayList<SiteInst>();
		SiteService_MB siteService = null;
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			siteList = siteService.select();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(siteService);
		}
		return siteList;
	}
}

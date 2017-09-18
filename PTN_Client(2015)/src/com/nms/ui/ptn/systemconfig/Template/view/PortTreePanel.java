package com.nms.ui.ptn.systemconfig.Template.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.NeTreePanel;
import com.nms.ui.manager.keys.StringKeysMenu;
import com.nms.ui.manager.keys.StringKeysTip;

/**
 * EXP窗口  端口树页面
 * @author dzy
 *
 */
public class PortTreePanel extends JPanel {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2827295921418069224L;
	private JLabel lblField;
	private JComboBox cmbSite;
	private String action;
	
	private NeTreePanel neTreePanel;//网元树
	private SiteInst siteInst=null;
	List<String> typeList=null;//要加载的类型集合（本次加载，ETH,LAG）
	private int isNNI;//ETH下   0 显示ETH 所有端口     1=只显示NNI端口 2=只显示UNI 端口 
	/**
	 * 创建一个新的实例
	 * @param action
	 *   动作类型
	 *   insert 新建
	 *   update  修改界面
	 * @param typeList
	 *     网元树下，的 端口类型集合（eth,pdh,sdh）
	 * @param isNNI
	 *  true  网元树下，只显示nni端口
	 * @param isLag
	 *    true,,加载lag
	 */
	public PortTreePanel(String action,List<String> typeList,int isNNI ) {
		try {
			this.isNNI=isNNI;
			this.action = action;
			this.typeList=typeList;
			initComponentss();
			this.setLayout();
			this.addListener();
			this.initData();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}

	}
	
	/**
	 * 初始化数据
	 * @throws Exception
	 */
	private void initData() throws Exception {
		initCombobox(this.cmbSite);
	}
	
	/**
	 * 添加监听事件
	 */
	private void addListener() {
		this.cmbSite.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cmbSiteactionPerformed();
			}
		});
		
	}
	/**
	 * 初始化控件
	 * @throws Exception
	 */
	private void initComponentss() throws Exception {
		try {
			this.lblField = new JLabel(ResourceUtil.srcStr(StringKeysTip.TIP_CHOOSE_SITE) + "");
			this.cmbSite = new JComboBox();
			if(TypeAndActionUtil.ACTION_UPDATE==action){
				cmbSite.setEnabled(false);
			}
			this.neTreePanel=new NeTreePanel(4,this.siteInst ,typeList,isNNI);
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 设置布局
	 */
	private void setLayout() {
		this.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysMenu.MENU_SELECT_PORT)));
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 30, 150 };
		componentLayout.columnWeights = new double[] { 0, 0, 0 };
		componentLayout.rowHeights = new int[] { 0, 40, 400 };
		componentLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0, 0, 0.2 };
		this.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(15, 5, 5, 5);
		componentLayout.setConstraints(this.lblField, c);
		this.add(this.lblField);
		c.gridx = 1;
		c.gridwidth = 2;
		componentLayout.setConstraints(this.cmbSite, c);
		this.add(this.cmbSite);

		
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 3;
		componentLayout.setConstraints(this.neTreePanel, c);
		this.add(this.neTreePanel);
	}
	
	/**
	 * 下拉菜单初始化
	 * @param comboBox 
	 */
	public void initCombobox(JComboBox comboBox) {
		SiteService_MB service = null;
		List<SiteInst> siteInstListAll = null;
		List<SiteInst> siteInstList = null;
		DefaultComboBoxModel defaultComboBoxModel = (DefaultComboBoxModel) comboBox.getModel();
		try {
			siteInstList = new ArrayList<SiteInst>();
			service = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			siteInstListAll = service. selectRootSite(ConstantUtil.user);
			for(SiteInst siteInst:siteInstListAll){
				if("700A".equals(siteInst.getCellType())||"700D".equals(siteInst.getCellType())||"700B".equals(siteInst.getCellType())||
						"700E".equals(siteInst.getCellType())){
					siteInstList.add(siteInst);
				}
			}
			defaultComboBoxModel.addElement(new ControlKeyValue("" , "", null));
			for (SiteInst siteInst : siteInstList) {
				defaultComboBoxModel.addElement(new ControlKeyValue(siteInst.getSite_Inst_Id() + "", siteInst.getCellId(), siteInst));
			}
			comboBox.setModel(defaultComboBoxModel);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(service);
		}
	}
	
	/**
	 * 下拉菜单 按钮监听
	 */
	private void cmbSiteactionPerformed() {
		ControlKeyValue controlKeyValue = (ControlKeyValue) this.cmbSite.getSelectedItem();
		this.siteInst= (SiteInst) controlKeyValue.getObject();
		this.neTreePanel.setSiteInst(siteInst);
		try {
			this.neTreePanel.clearBox();
			this.neTreePanel.initData();
		} catch (Exception e) {
			
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	public JComboBox getCmbSite() {
		return cmbSite;
	}

	public NeTreePanel getNeTreePanel() {
		return neTreePanel;
	}

	public void setNeTreePanel(NeTreePanel neTreePanel) {
		this.neTreePanel = neTreePanel;
	}
	
}
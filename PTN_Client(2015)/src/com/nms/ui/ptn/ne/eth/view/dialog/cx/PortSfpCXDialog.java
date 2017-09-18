package com.nms.ui.ptn.ne.eth.view.dialog.cx;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.nms.db.bean.equipment.port.PortAttr;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnPanel;
import com.nms.ui.manager.keys.StringKeysLbl;

/**
 * 
 * 项目名称：WuHanPTN2012 类名称：PortSfpCXDialog 类描述： 端口SFP信息晨晓界面 创建人：kk 创建时间：2013-7-15 上午11:53:35 修改人：kk 修改时间：2013-7-15 上午11:53:35 修改备注：
 * 
 * @version
 * 
 */
public class PortSfpCXDialog extends PtnPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2703404766700339L;
	private PortInst portInst;
	/**
	 * 创建一个新的实例
	 * @param portInst
	 * 			端口
	 */
	public PortSfpCXDialog(PortInst portInst) {
		try {
			this.portInst = portInst;
			this.initComponent();
			this.setLayout();
			if(null!=portInst){
				initData();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 初始化数据
	 * @throws Exception 
	 */
	private void initData() throws Exception {
		PortAttr portAttr = portInst.getPortAttr();
		super.getComboBoxDataUtil().comboBoxSelect(cmbSfpExpectType, portAttr.getSfpExpectType() + "");
		if(null != portAttr.getSfpActual() && !"".equals(portAttr.getSfpActual())){
			this.txtSfpActual.setText(UiUtil.getCodeByValue("portSfpType", portAttr.getSfpActual()).getCodeName());
		}
		this.txtWorkWavelength.setText(portAttr.getWorkWavelength());
		this.txtSfpVender.setText(portAttr.getSfpVender());
	}

	/**
	 * 仅用于显示，不能更改数据
	 * @throws Exception
	 */
	public void lock()throws Exception {
		try {
			cmbSfpExpectType.setEnabled(false);
			txtSfpActual.setEditable(false);
			txtWorkWavelength.setEditable(false);
			txtSfpVender.setEditable(false);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	/**
	 * 初始化控件
	 * @throws Exception
	 */
	private void initComponent() throws Exception {
		this.lblSfpExpectType = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SFP_EXPECT));
		this.lblSfpActual = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SFP_REALITY));
		this.lblWorkWavelength = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_JOB_WAVELENGTH));
		this.lblSfpVender = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_MANUFACTURERS));
		this.cmbSfpExpectType = new JComboBox();
		this.txtSfpActual = new JTextField();
		this.txtWorkWavelength = new JTextField();
		this.txtSfpVender = new JTextArea(5, 4);
		this.txtSfpVender.setBorder(BorderFactory.createEtchedBorder());
		this.txtSfpVender.setLineWrap(true);
		this.sllPanelSfpVender = new JScrollPane(this.txtSfpVender);
		super.getComboBoxDataUtil().comboBoxData(this.cmbSfpExpectType, "portSfpType");
		
		this.txtSfpActual.setEnabled(false);
		this.txtSfpVender.setEnabled(false);
		this.txtWorkWavelength.setEnabled(false);
	}

	/**
	 * 设置布局
	 */
	private void setLayout() {
		GridBagLayout componentLayout = new GridBagLayout();// 网格布局
		componentLayout.columnWidths = new int[] { 50, 100, 300, 250 };
		componentLayout.columnWeights = new double[] { 0, 0, 0, 0 };
		componentLayout.rowHeights = new int[] { 15, 40, 40, 40, 40, 40 };
		componentLayout.rowWeights = new double[] { 0, 0, 0, 0, 0, 0.2 };
		this.setLayout(componentLayout);

		GridBagConstraints gridCon = new GridBagConstraints();
		gridCon.fill = GridBagConstraints.HORIZONTAL;
		gridCon.insets = new Insets(5, 5, 5, 5);
		gridCon.anchor = GridBagConstraints.CENTER;

		gridCon.gridx = 1;
		gridCon.gridy = 1;
		gridCon.gridheight = 1;
		gridCon.gridwidth = 1;
		componentLayout.setConstraints(this.lblSfpExpectType, gridCon);
		this.add(this.lblSfpExpectType);
		gridCon.gridx = 2;
		gridCon.gridwidth = 1;
		gridCon.gridheight = 1;
		componentLayout.setConstraints(this.cmbSfpExpectType, gridCon);
		this.add(this.cmbSfpExpectType);

		gridCon.gridx = 1;
		gridCon.gridy = 2;
		gridCon.gridheight = 1;
		gridCon.gridwidth = 1;
		componentLayout.setConstraints(this.lblSfpActual, gridCon);
		this.add(this.lblSfpActual);
		gridCon.gridx = 2;
		gridCon.gridheight = 1;
		gridCon.gridwidth = 1;
		componentLayout.setConstraints(this.txtSfpActual, gridCon);
		this.add(this.txtSfpActual);

		gridCon.gridx = 1;
		gridCon.gridy = 3;
		gridCon.gridheight = 1;
		gridCon.gridwidth = 1;
		componentLayout.setConstraints(this.lblWorkWavelength, gridCon);
		this.add(this.lblWorkWavelength);
		gridCon.gridx = 2;
		gridCon.gridheight = 1;
		gridCon.gridwidth = 1;
		componentLayout.setConstraints(this.txtWorkWavelength, gridCon);
		this.add(this.txtWorkWavelength);

		gridCon.gridx = 1;
		gridCon.gridy = 4;
		gridCon.gridheight = 1;
		gridCon.gridwidth = 1;
		componentLayout.setConstraints(this.lblSfpVender, gridCon);
		this.add(this.lblSfpVender);
		gridCon.gridx = 2;
		gridCon.gridheight = 1;
		gridCon.gridwidth = 1;
		gridCon.fill = GridBagConstraints.HORIZONTAL;
		gridCon.insets = new Insets(5, 5, 5, 5);
		gridCon.anchor = GridBagConstraints.CENTER;
		componentLayout.setConstraints(this.sllPanelSfpVender, gridCon);
		this.add(this.sllPanelSfpVender);
	}

	/**
	 * 获取portInst对象
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	public void getPortInst(PortInst portInst) throws Exception {

		if (null == portInst) {
			throw new Exception("portInst is null");
		}
		ControlKeyValue sfpExpectType = (ControlKeyValue) this.cmbSfpExpectType.getSelectedItem();
		portInst.getPortAttr().setSfpActual(this.txtSfpActual.getText());
		portInst.getPortAttr().setSfpExpectType(Integer.parseInt(sfpExpectType.getId()));
		portInst.getPortAttr().setWorkWavelength(this.txtWorkWavelength.getText());
		portInst.getPortAttr().setSfpVender(this.txtSfpVender.getText());
		sfpExpectType = null;
	}

	private JLabel lblSfpExpectType;// SFP期望类型 对应code表主键
	private JComboBox cmbSfpExpectType;//
	private JLabel lblSfpActual;// SFP实际类型
	private JTextField txtSfpActual;
	private JLabel lblWorkWavelength;// 工作波长
	private JTextField txtWorkWavelength;
	private JLabel lblSfpVender;// sfp厂家信息
	private JTextArea txtSfpVender;
	private JScrollPane sllPanelSfpVender; // 厂家信息滚动条

}

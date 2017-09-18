package com.nms.ui.ptn.ne.eth.view.dialog.cx;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.system.code.Code;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnSpinner;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.ptn.clock.view.cx.time.TextFiledKeyListener;
import com.nms.ui.ptn.clock.view.cx.time.TextfieldFocusListener;
import com.nms.ui.ptn.ne.eth.view.dialog.base.PortEthDialog;

/**
*    
* 项目名称：WuHanPTN2012   
* 类名称：PortEthCXDailog   
* 类描述：   端口基本信息晨晓界面
* 创建人：kk   
* 创建时间：2013-7-15 上午11:52:57   
* 修改人：kk   
* 修改时间：2013-7-15 上午11:52:57   
* 修改备注：   
* @version    
*
 */
public class PortEthCXDailog extends PortEthDialog{
	private PortInst portInst;
	private static final long serialVersionUID = 5381813758986426586L;
	/**
	 * 创建一个新的实例
	 * @param portInst
	 * 			端口
	 */
	public PortEthCXDailog(PortInst portInst){
		
		try {
			this.portInst=portInst;
			this.initComponents();
			this.initData();
			this.addListener();
			addFocusListenerForTextfield();/*textfield聚焦事件监听，当离开此textfield判断值是否在指定范围内*/
		    addKeyListenerForTextfield();/*textfield添加键盘事件监听，只允许数字输入*/
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 *初始化数据 
	 */
	private void initData() throws Exception {
		super.getComboBoxDataUtil().comboBoxData(super.getCmbStatus(), "ENABLEDSTATUECX");
		SpinnerNumberModel numModel2 = (SpinnerNumberModel) this.spnInBandWidthLimit.getModel();
		numModel2.setValue(new Integer(0));
		numModel2.setMaximum(new Integer(1000000));
		numModel2.setMinimum(new Integer(0));
		numModel2.setStepSize(new Integer(64));
		
		super.getTxtName().setText(portInst.getPortName());
		this.txtMac.setText(portInst.getMacAddress());
		if("null".equals(String.valueOf(portInst.getPortAttr().getMaxFrameSize()))){
		super.getTxtFrame().setText("0");	
		}else{
		super.getTxtFrame().setText(String.valueOf(portInst.getPortAttr().getMaxFrameSize()));
		}
		super.getComboBoxDataUtil().comboBoxSelect(super.getCmbFluid(),portInst.getPortAttr().getFluidControl()+"");

		super.getComboBoxDataUtil().comboBoxSelect(this.cmbSetRate,portInst.getPortAttr().getPortSpeed()+"");
		this.txtActualRate.setText(portInst.getPortAttr().getActualSpeed());
		this.chbWAN.setSelected(portInst.getPortAttr().getTenWan()==1?true:false);
		this.chbAgreement.setSelected(portInst.getPortAttr().getSlowAgreement()==1?true:false);
		this.chbLoop.setSelected(portInst.getPortAttr().getMessageLoopback()==1?true:false);
		
		super.getComboBoxDataUtil().comboBoxSelectByValue(super.getCmbStatus(), portInst.getIsEnabled_code()+"");
		super.getComboBoxDataUtil().comboBoxSelectByValue(super.getCmbType(),portInst.getPortType());
		super.getSpnExitSpeed().setTxtData(portInst.getPortAttr().getExitLimit() == null ? "0" : portInst.getPortAttr().getExitLimit());
		this.spnInBandWidthLimit.setTxtData(portInst.getPortAttr().getEntranceLimit());
	}
	/**
	 * 初始化控件
	 * @throws Exception
	 */
	private void initComponents() throws Exception{
		String portName = portInst.getPortName();
		if(portName.contains("fe") || portName.contains("fx")){
			super.spnExitSpeed = new PtnSpinner(super.getChbExitSpeed(), 100000,0, 0,64);
		}else if(portName.contains("ge")){
			super.spnExitSpeed = new PtnSpinner(super.getChbExitSpeed(), 1000000,0, 0,64);
		}else if(portName.contains("xg")){
			super.spnExitSpeed = new PtnSpinner(super.getChbExitSpeed(), 10000000,0, 0,64);
		}
		this.chbInBandWidthLimit=new JCheckBox(ResourceUtil.srcStr(StringKeysLbl.LBL_PORT_EXIT_SPEED_LIMIT));
		this.spnInBandWidthLimit = new PtnSpinner(chbInBandWidthLimit,1000000);
		this.chbAgreement=new JCheckBox(ResourceUtil.srcStr(StringKeysLbl.LBL_SLOW_AGREEMENT));
		this.chbWAN=new JCheckBox(ResourceUtil.srcStr(StringKeysLbl.LBL_TEN_WAN));
		this.chbLoop=new JCheckBox(ResourceUtil.srcStr(StringKeysLbl.LBL_IS_MESSAGE_LOOP));
		this.lblSetRate=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SET_RATE));
		this.cmbSetRate=new JComboBox();
		this.lblActualRate=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ACTUAL_SPEED));
		this.txtActualRate=new JTextField();
		this.lblMac=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_LOCAL_MAC));
		this.txtMac=new JTextField();
		super.addComponent(this, super.getChbExitSpeed(), 4, 3, gridCon);
		super.addComponent(this, super.getSpnExitSpeed(), 5, 3, gridCon);
		super.addComponent(this, lblMac, 1, 4, gridCon);
		super.addComponent(this, txtMac, 2, 4, gridCon);
		super.addComponent(this, lblSetRate, 4, 4, gridCon);
		super.addComponent(this, cmbSetRate, 5, 4, gridCon);
		super.addComponent(this, chbInBandWidthLimit, 1, 5, gridCon);
		super.addComponent(this, spnInBandWidthLimit, 2, 5, gridCon);
		
		super.addComponent(this,  lblActualRate, 4, 5, gridCon);
		super.addComponent(this,  txtActualRate, 5, 5, gridCon);
		super.addComponent(this, chbWAN, 1, 6, gridCon);
		super.addComponent(this,chbAgreement,4,6,gridCon);
		super.addComponent(this,chbLoop,1,7,gridCon);
		
		//根据端口名称不同 端口速率不同。 通过名称的前两位验证从code取哪组值
		if(null!=this.portInst){
			String portName_short=this.portInst.getPortName().substring(0, 2);	//端口前两位名称
			//因为fx和fe的速率一致。 code组里是以fe命名。 所以当成fe取值
			if("fx".equals(portName_short)){
				portName_short="fe";
			}
			super.getComboBoxDataUtil().comboBoxData(this.cmbSetRate, "portSetRate"+portName_short.toUpperCase());
		}
		
	}
	
	/**
	 * 拿到端口
	 * @param portInst
	 * @throws Exception
	 */
	public void getPortInst(PortInst portInst) throws Exception{
		if (null == portInst) {
			throw new Exception("portInst is null");
		}
		ControlKeyValue enabledStatus = (ControlKeyValue) super.getCmbStatus().getSelectedItem();
		ControlKeyValue type = (ControlKeyValue) super.getCmbType().getSelectedItem();
		ControlKeyValue fluid = (ControlKeyValue) super.getCmbFluid().getSelectedItem();
		ControlKeyValue setRate = (ControlKeyValue) this.cmbSetRate.getSelectedItem();
		portInst.setPortName(super.getTxtName().getText());
		portInst.setMacAddress(this.txtMac.getText());
		portInst.getPortAttr().setMaxFrameSize(super.getTxtFrame().getText());
		portInst.setIsEnabled_code(Integer.parseInt(((Code)enabledStatus.getObject()).getCodeValue()));
		portInst.setPortType((((Code)type.getObject()).getCodeValue()));
		portInst.getPortAttr().setFluidControl(Integer.parseInt(fluid.getId()));
		portInst.getPortAttr().setPortSpeed(Integer.parseInt(setRate.getId()));
		portInst.getPortAttr().setActualSpeed(this.txtActualRate.getText());
		portInst.getPortAttr().setTenWan(this.chbWAN.isSelected()==true?1:0);
		portInst.getPortAttr().setSlowAgreement(this.chbAgreement.isSelected()==true?1:0);
		portInst.getPortAttr().setMessageLoopback(this.chbLoop.isSelected()==true?1:0);
		portInst.getPortAttr().setExitLimit(super.getSpnExitSpeed().getTxtData());
		portInst.getPortAttr().setEntranceLimit(this.spnInBandWidthLimit.getTxtData());
	}
	
	/**
	 * 添加监听事件
	 */
	private void addListener() {
		this.cmbSetRate.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent evt) {
				if(evt.getStateChange() == 1){
					ControlKeyValue code = (ControlKeyValue) cmbSetRate.getSelectedItem();
					String value = ((Code)code.getObject()).getCodeValue();
					SpinnerNumberModel numModel = (SpinnerNumberModel)getSpnExitSpeed().getModel();
					JTextField txt = ((JSpinner.NumberEditor) (getSpnExitSpeed().getComponents()[2])).getTextField();
					if(getChbExitSpeed().isSelected()){
						if(value.equals("0") || value.equals("3")){
							numModel.setMaximum(100000);
							txt.setText(100000+"");
						}
						if(value.equals("1") || value.equals("4")){
							//千兆
							numModel.setMaximum(1000000);
							txt.setText(1000000+"");
						}
						if(value.equals("2") || value.equals("5")){
							//万兆
							numModel.setMaximum(10000000);
							txt.setText(10000000+"");
						}					
					}
				}
			}
		});
	}
	
	/**
	 * <p>
	 * 判断输入数值是否在指定区间内
	 * </p>
	 * @throws Exception
	 */
	private void addFocusListenerForTextfield()throws Exception{
		
		TextfieldFocusListener textfieldFocusListener=null;
		String whichTextTield=null;
		try{
			whichTextTield=ResourceUtil.srcStr(StringKeysLbl.LBL_MAX_FRAME_WORDS);
			textfieldFocusListener = new TextfieldFocusListener(whichTextTield,17,super.getTxtFrame());
			super.getTxtFrame().addFocusListener(textfieldFocusListener);
			
		}catch(Exception e){
			
			throw e;
		}
	}
	
	/**
	 * <p>
	 * textfield添加监听，只允许输入数字
	 * </p>
	 * @throws Exception
	 */
	private void addKeyListenerForTextfield()throws Exception{
		
		TextFiledKeyListener textFIledKeyListener=null;
		try{
			/* 为1：只接受数字 **/
			textFIledKeyListener = new TextFiledKeyListener(1);
			super.getTxtFrame().addKeyListener(textFIledKeyListener);
		}catch(Exception e){
			
			throw e;
		}
	}
	private JLabel lblSetRate;//设置速率
	private JComboBox cmbSetRate;
	private JCheckBox chbInBandWidthLimit;//入口带宽限制
	private PtnSpinner spnInBandWidthLimit;
	private JCheckBox chbWAN;//启用10G WAN
	private JLabel lblActualRate;//实际速率
	private JTextField txtActualRate;
	private JCheckBox chbAgreement;//启用慢协议
	private JCheckBox chbLoop;//是否允许报文环回
	private JLabel lblMac;
	private JTextField txtMac;
	public PortInst getPortInst() {
		return portInst;
	}

	public void setPortInst(PortInst portInst) {
		this.portInst = portInst;
	}
	
}

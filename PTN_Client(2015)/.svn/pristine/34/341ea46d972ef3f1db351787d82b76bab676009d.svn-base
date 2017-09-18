package com.nms.ui.ptn.ne.time.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Collections;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.nms.db.bean.ptn.clock.PtpPortinfo;
import com.nms.db.bean.ptn.clock.TimeSyncInfo;
import com.nms.db.bean.system.code.Code;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnPanel;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTab;

public class TimeSyncView extends PtnPanel{
	/**
	 * 时间同步管理界面
	 */
	private static final long serialVersionUID = 5898957483189851010L;
	private JPanel contentPanel;	
	private JLabel lblptpModel;  //PTP模式
	private JComboBox cmbptpModel;
	private JLabel lblclockModel;  //时钟模型
	private JComboBox cmbclockModel;   
	private JLabel lblsrcclockModel;   //源时钟模式
	private JComboBox cmbsrcclockModel;	
	private JLabel lblsrcclockId;     //源时钟ID
	private JTextField txtsrcclockId;	
	private JLabel lblsrcclocktype;    //源时钟类型
	private JComboBox cmbsrcclockType;		
	private JLabel lblsrcclockpri1;    //源时钟优先级1
	private JTextField txtsrcclockpri1;
	private JLabel lblsrcclockpri2;    //源时钟优先级2
	private JTextField txtsrcclockpri2;
	private JLabel lblsrcclockLevel;    //源时钟等级
	private JTextField txtsrcclockLevel;
	private JLabel lblsrcclockaccuray;  //源时钟精度
	private JComboBox cmbsrcclockaccuray;		
	private JLabel lblSlaveOnlyModel;  //SlaveOnly 模式
	private JComboBox cmbSlaveOnlyModel;	
	private JLabel lblinCompensation;  //输入时延补偿属性
	private JComboBox cmbinCompensation;	
	private JLabel lblinDelay;    //输入时延补偿值
	private JTextField txtinDelay;	
	private JLabel lbloutCompensation;  //输出时延补偿属性
	private JComboBox cmboutCompensation;	
	private JLabel lbloutDelay;    //输出时延补偿值
	private JTextField txtoutDelay;	
	private JLabel lblSynCicle;  //同步周期
	private JTextField cmbSynCicle;
	private JLabel lblNoteCicle;  //通告周期
	private JTextField cmbNoteCicle;
	private JLabel lblTimeInfoIt;  //时间信息接口
	private JComboBox cmbTimeInfoIt;		
	private JLabel lblsynFrec;    //Sync报文发包频率(Hz)：
	private JComboBox cmbsynFrec;
	private JLabel lbldelayFre;    //Delay_Req报文发包频率(Hz)：
	private JComboBox cmbdelayFre;
	private JLabel lblannoFrec;    //Announce报文发包频率(Hz)：
	private JComboBox cmbannoFrec;
	private JLabel lbldelayOver;    //Delay_Req 报文超时系数
	private JTextField cmbdelayOver;
	private JLabel lblAnnOver;    //Announce报文超时系数
	private JTextField cmbAnnOver;
	private JLabel lblDomain;    //DomainNumber
	private JTextField jtfDomain;

	
	
	public TimeSyncView() {
		initComponents();
		setLayout();		
	}
	
	
	
	/**
	 * 初始化界面
	 * 
	 * @param info
	 * @throws Exception
	 */
	public void refresh(TimeSyncInfo info) throws Exception {
		
		if(info != null){		
			super.getComboBoxDataUtil().comboBoxSelectByValue(cmbptpModel, info.getPtpModel()+"");
			super.getComboBoxDataUtil().comboBoxSelectByValue(cmbclockModel, info.getClockModel()+"");
			super.getComboBoxDataUtil().comboBoxSelectByValue(cmbsrcclockModel, info.getSrcclockModel()+"");
			txtsrcclockId.setText(info.getSrcclockId());
			super.getComboBoxDataUtil().comboBoxSelectByValue(cmbsrcclockType, Integer.toHexString(info.getSrcclocktype())+"");
			txtsrcclockpri1.setText(info.getSrcclockpri1()+"");
			txtsrcclockpri2.setText(info.getSrcclockpri2()+"");
			txtsrcclockLevel.setText(info.getSrcclockLevel()+"");
			super.getComboBoxDataUtil().comboBoxSelectByValue(cmbsrcclockaccuray, Integer.toHexString(info.getSrcclockaccuray())+"");
			super.getComboBoxDataUtil().comboBoxSelectByValue(cmbSlaveOnlyModel, info.getSlaveOnlyModel()+"");
			super.getComboBoxDataUtil().comboBoxSelectByValue(cmbinCompensation, info.getInCompensation()+"");
			txtinDelay.setText(info.getInDelay()+"");
			super.getComboBoxDataUtil().comboBoxSelectByValue(cmboutCompensation, info.getOutCompensation()+"");
			txtoutDelay.setText(info.getOutDelay()+"");
			cmbSynCicle.setText(info.getSynCicle()+"");
			cmbNoteCicle.setText(info.getNoteCicle()+"");
			super.getComboBoxDataUtil().comboBoxSelectByValue(cmbTimeInfoIt, info.getTimeInfoIt()+"");
			super.getComboBoxDataUtil().comboBoxSelectByValue(cmbsynFrec, info.getSyncFreq()+"");
			super.getComboBoxDataUtil().comboBoxSelectByValue(cmbdelayFre, info.getDelayFreq()+"");
			super.getComboBoxDataUtil().comboBoxSelectByValue(cmbannoFrec, info.getAnnounceFreq()+"");
			cmbdelayOver.setText(info.getDelayOver()+"");
			cmbAnnOver.setText(info.getAnnounceOver()+"");
			jtfDomain.setText(info.getDomainNumbe()+"");					
			
		}
	}
	
	public TimeSyncInfo pageSetValue(TimeSyncInfo info,TimeSyncPanel view)
	{
		
		ControlKeyValue ptpModel = (ControlKeyValue) this.cmbptpModel.getSelectedItem();	
		info.setPtpModel(Integer.parseInt(((Code) ptpModel.getObject()).getCodeValue()));
			
		ControlKeyValue clockModel = (ControlKeyValue) this.cmbclockModel.getSelectedItem();
		info.setClockModel(Integer.parseInt(((Code) clockModel.getObject()).getCodeValue()));		
		
		info.setSrcclockId(txtsrcclockId.getText());
		
		
		ControlKeyValue srcclockModel = (ControlKeyValue) this.cmbsrcclockModel.getSelectedItem();
		info.setSrcclockModel(Integer.parseInt(((Code) srcclockModel.getObject()).getCodeValue()));
			
		ControlKeyValue srcclockType = (ControlKeyValue) this.cmbsrcclockType.getSelectedItem();
		info.setSrcclocktype(Integer.parseInt(((Code) srcclockType.getObject()).getCodeValue(),16));
			
		info.setSrcclockpri1(Integer.parseInt(txtsrcclockpri1.getText()));
			
		info.setSrcclockpri2(Integer.parseInt(txtsrcclockpri2.getText()));
			
		info.setSrcclockLevel(Integer.parseInt(txtsrcclockLevel.getText()));

		ControlKeyValue srcclockaccuray = (ControlKeyValue) this.cmbsrcclockaccuray.getSelectedItem();
		info.setSrcclockaccuray(Integer.parseInt(((Code) srcclockaccuray.getObject()).getCodeValue(),16));
		ControlKeyValue SlaveOnlyModel = (ControlKeyValue) this.cmbSlaveOnlyModel.getSelectedItem();
		info.setSlaveOnlyModel(Integer.parseInt(((Code) SlaveOnlyModel.getObject()).getCodeValue()));
			
		ControlKeyValue inCompensation = (ControlKeyValue) this.cmbinCompensation.getSelectedItem();
		info.setInCompensation(Integer.parseInt(((Code) inCompensation.getObject()).getCodeValue()));
			
		info.setInDelay(Integer.parseInt(txtinDelay.getText()));

		ControlKeyValue outCompensation = (ControlKeyValue) this.cmboutCompensation.getSelectedItem();
		info.setOutCompensation(Integer.parseInt(((Code) outCompensation.getObject()).getCodeValue()));			
		info.setOutDelay(Integer.parseInt(txtoutDelay.getText()));
		info.setSynCicle(Integer.parseInt(cmbSynCicle.getText()));
		info.setNoteCicle(Integer.parseInt(cmbNoteCicle.getText()));

		ControlKeyValue TimeInfoIt = (ControlKeyValue) this.cmbTimeInfoIt.getSelectedItem();
		info.setTimeInfoIt(Integer.parseInt(((Code) TimeInfoIt.getObject()).getCodeValue()));

		ControlKeyValue synFrec = (ControlKeyValue) this.cmbsynFrec.getSelectedItem();
		info.setSyncFreq(Integer.parseInt(((Code) synFrec.getObject()).getCodeValue()));
			
		ControlKeyValue delayFrec = (ControlKeyValue) this.cmbdelayFre.getSelectedItem();
		info.setDelayFreq(Integer.parseInt(((Code) delayFrec.getObject()).getCodeValue()));
			
		ControlKeyValue annoFrec = (ControlKeyValue) this.cmbannoFrec.getSelectedItem();
		info.setAnnounceFreq(Integer.parseInt(((Code) annoFrec.getObject()).getCodeValue()));
			
		info.setDelayOver(Integer.parseInt(cmbdelayOver.getText()));
			
		info.setAnnounceOver(Integer.parseInt(cmbAnnOver.getText()));	
		info.setDomainNumbe(Integer.parseInt(jtfDomain.getText()));			
		info.setSiteId(ConstantUtil.siteId);
		
		//PTP端口部分
		List<PtpPortinfo> p = view.getTable().getAllElement();
		Collections.reverse(p); 
		info.setPtpPortlist(p);
			
		return info;
	
		
		
	
	}
	
	
	
	
	
	private void initComponents() {
		try {
			contentPanel = new JPanel();
			contentPanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTab.TAB_TIMEMANAGE)));
			lblptpModel =  new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PTPMODE));
			cmbptpModel = new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(cmbptpModel, "ptpModel");
			
			lblclockModel =  new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_CLOCKMODE));
			cmbclockModel = new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(cmbclockModel, "clockModel");
			lblsrcclockModel =  new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SRCCLOCKMODE));
			cmbsrcclockModel = new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(cmbsrcclockModel, "ENABLEDSTATUE");
			lblsrcclockId = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SRCCLOCKID));
			txtsrcclockId = new JTextField("0");
			lblsrcclocktype =  new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SRCCLOCKTYPE));
			cmbsrcclockType = new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(cmbsrcclockType, "srcclockType");
			lblsrcclockpri1 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SRCCLOCKPRI1));
			txtsrcclockpri1 = new JTextField("255");
			lblsrcclockpri2 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SRCCLOCKPRI2));
			txtsrcclockpri2 = new JTextField("255");
			lblsrcclockLevel= new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SRCCLOCKLEVEL));
			txtsrcclockLevel = new JTextField("255");
			lblsrcclockaccuray= new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SRCCLOCKAC));
			cmbsrcclockaccuray = new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(cmbsrcclockaccuray, "srcclockaccuray");
			lblSlaveOnlyModel= new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SLAVEONLY));
			cmbSlaveOnlyModel = new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(cmbSlaveOnlyModel, "ENABLEDSTATUE");
			lblinCompensation= new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_INCOMPENSATION));
			cmbinCompensation= new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(cmbinCompensation, "Compensation");
			lblinDelay= new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_INDELAY));
			txtinDelay= new JTextField("0");
			lbloutCompensation= new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OUTDELAY));
			cmboutCompensation = new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(cmboutCompensation, "Compensation");
			
			lbloutDelay= new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OUTDELAYVALUE));
			txtoutDelay = new JTextField("0");
			lblSynCicle= new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SYNCCIRCLE));
			cmbSynCicle = new JTextField("0");
			
			lblNoteCicle= new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_NOTECIRCLE));
			cmbNoteCicle = new JTextField("0");
					
			lblTimeInfoIt= new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_TIMEINFORITE));
			cmbTimeInfoIt= new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(cmbTimeInfoIt, "TimeInfoIt");
			
			lblsynFrec = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SYNCFREC));
			cmbsynFrec = new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(cmbsynFrec, "SYNCSENDFREC");
			
			lbldelayFre = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_DELAYFRE));
			cmbdelayFre = new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(cmbdelayFre, "DELAYSENDFREC");
			lblannoFrec = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ANNOFREC));
			cmbannoFrec = new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(cmbannoFrec, "DELAYSENDFREC");
			lbldelayOver = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_DELAYFREC));
		    cmbdelayOver = new JTextField("5");
		
			lblAnnOver = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ANNOOVER));
			cmbAnnOver = new JTextField("5");
	
			lblDomain = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_DOMAIN));
			jtfDomain = new JTextField("0");

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	
	
	
	
	
	private void setGridBagLayout()  {
		GridBagConstraints c = null;
		try {
			c = new GridBagConstraints();
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[] { 100, 200, 100, 200, 100, 200 };
			gridBagLayout.columnWeights = new double[] { 0, 0, 0, 0, 0, 0 };
			gridBagLayout.rowHeights = new int[] { 15, 15, 15 ,15, 15 ,15};
			gridBagLayout.rowWeights = new double[] { 0, 0, 0 ,0, 0 ,0};
			contentPanel.setLayout(gridBagLayout);
			contentPanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTab.TAB_TIMEMANAGE)));
			c.insets = new Insets(5, 5, 5, 5);
			c.fill = GridBagConstraints.BOTH;

			c.gridx = 0;
			c.gridy = 0;
			gridBagLayout.setConstraints(lblptpModel, c);
			contentPanel.add(lblptpModel);
			
			c.gridx = 1;
			c.gridy = 0;
			gridBagLayout.setConstraints(cmbptpModel, c);
			contentPanel.add(cmbptpModel);
			
			c.gridx = 2;
			c.gridy = 0;
			gridBagLayout.setConstraints(lblclockModel, c);
			contentPanel.add(lblclockModel);
			
			c.gridx = 3;
			c.gridy = 0;
			gridBagLayout.setConstraints(cmbclockModel, c);
			contentPanel.add(cmbclockModel);
			
			c.gridx = 4;
			c.gridy = 0;
			gridBagLayout.setConstraints(lblsrcclockModel, c);
			contentPanel.add(lblsrcclockModel);
			
			c.gridx = 5;
			c.gridy = 0;
			gridBagLayout.setConstraints(cmbsrcclockModel, c);
			contentPanel.add(cmbsrcclockModel);
			
			c.gridx = 0;
			c.gridy = 1;
			gridBagLayout.setConstraints(lblsrcclockId, c);
			contentPanel.add(lblsrcclockId);
			
			c.gridx = 1;
			c.gridy = 1;
			gridBagLayout.setConstraints(txtsrcclockId, c);
			contentPanel.add(txtsrcclockId);
			
			c.gridx = 2;
			c.gridy = 1;
			gridBagLayout.setConstraints(lblsrcclocktype, c);
			contentPanel.add(lblsrcclocktype);
			
			c.gridx = 3;
			c.gridy = 1;
			gridBagLayout.setConstraints(cmbsrcclockType, c);
			contentPanel.add(cmbsrcclockType);
			
			c.gridx = 4;
			c.gridy = 1;
			gridBagLayout.setConstraints(lblsrcclockpri1, c);
			contentPanel.add(lblsrcclockpri1);
			
			c.gridx = 5;
			c.gridy = 1;
			gridBagLayout.setConstraints(txtsrcclockpri1, c);
			contentPanel.add(txtsrcclockpri1);
			
			c.gridx = 0;
			c.gridy = 2;
			gridBagLayout.setConstraints(lblsrcclockpri2, c);
			contentPanel.add(lblsrcclockpri2);
			
			c.gridx = 1;
			c.gridy = 2;
			gridBagLayout.setConstraints(txtsrcclockpri2, c);
			contentPanel.add(txtsrcclockpri2);
			
			c.gridx = 2;
			c.gridy = 2;
			gridBagLayout.setConstraints(lblsrcclockLevel, c);
			contentPanel.add(lblsrcclockLevel);
			
			c.gridx = 3;
			c.gridy = 2;
			gridBagLayout.setConstraints(txtsrcclockLevel, c);
			contentPanel.add(txtsrcclockLevel);
			
			c.gridx = 4;
			c.gridy = 2;
			gridBagLayout.setConstraints(lblsrcclockaccuray, c);
			contentPanel.add(lblsrcclockaccuray);
			
			c.gridx = 5;
			c.gridy = 2;
			gridBagLayout.setConstraints(cmbsrcclockaccuray, c);
			contentPanel.add(cmbsrcclockaccuray);
			
			c.gridx = 0;
			c.gridy = 3;
			gridBagLayout.setConstraints(lblSlaveOnlyModel, c);
			contentPanel.add(lblSlaveOnlyModel);
			
			c.gridx = 1;
			c.gridy = 3;
			gridBagLayout.setConstraints(cmbSlaveOnlyModel, c);
			contentPanel.add(cmbSlaveOnlyModel);
			
			c.gridx = 2;
			c.gridy = 3;
			gridBagLayout.setConstraints(lblinCompensation, c);
			contentPanel.add(lblinCompensation);
			
			c.gridx = 3;
			c.gridy = 3;
			gridBagLayout.setConstraints(cmbinCompensation, c);
			contentPanel.add(cmbinCompensation);
			
			c.gridx = 4;
			c.gridy = 3;
			gridBagLayout.setConstraints(lblinDelay, c);
			contentPanel.add(lblinDelay);
			
			c.gridx = 5;
			c.gridy = 3;
			gridBagLayout.setConstraints(txtinDelay, c);
			contentPanel.add(txtinDelay);	

			c.gridx = 0;
			c.gridy = 4;
			gridBagLayout.setConstraints(lbloutCompensation, c);
			contentPanel.add(lbloutCompensation);
			
			c.gridx = 1;
			c.gridy = 4;
			gridBagLayout.setConstraints(cmboutCompensation, c);
			contentPanel.add(cmboutCompensation);
			
			c.gridx = 2;
			c.gridy = 4;
			gridBagLayout.setConstraints(lbloutDelay, c);
			contentPanel.add(lbloutDelay);
			
			c.gridx = 3;
			c.gridy = 4;
			gridBagLayout.setConstraints(txtoutDelay, c);
			contentPanel.add(txtoutDelay);
			
			c.gridx = 4;
			c.gridy = 4;
			gridBagLayout.setConstraints(lblSynCicle, c);
			contentPanel.add(lblSynCicle);
			
			c.gridx = 5;
			c.gridy = 4;
			gridBagLayout.setConstraints(cmbSynCicle, c);
			contentPanel.add(cmbSynCicle);
			
			c.gridx = 0;
			c.gridy = 5;
			gridBagLayout.setConstraints(lblNoteCicle, c);
			contentPanel.add(lblNoteCicle);
			
			c.gridx = 1;
			c.gridy = 5;
			gridBagLayout.setConstraints(cmbNoteCicle, c);
			contentPanel.add(cmbNoteCicle);
			
			c.gridx = 2;
			c.gridy = 5;
			gridBagLayout.setConstraints(lblTimeInfoIt, c);
			contentPanel.add(lblTimeInfoIt);
			
			c.gridx = 3;
			c.gridy = 5;
			gridBagLayout.setConstraints(cmbTimeInfoIt, c);
			contentPanel.add(cmbTimeInfoIt);
			
			c.gridx = 4;
			c.gridy = 5;
			gridBagLayout.setConstraints(lblsynFrec, c);
			contentPanel.add(lblsynFrec);
			
			c.gridx = 5;
			c.gridy = 5;
			gridBagLayout.setConstraints(cmbsynFrec, c);
			contentPanel.add(cmbsynFrec);
			
			c.gridx = 0;
			c.gridy = 6;
			gridBagLayout.setConstraints(lbldelayFre, c);
			contentPanel.add(lbldelayFre);
			
			c.gridx = 1;
			c.gridy = 6;
			gridBagLayout.setConstraints(cmbdelayFre, c);
			contentPanel.add(cmbdelayFre);
			
			c.gridx = 2;
			c.gridy = 6;
			gridBagLayout.setConstraints(lblannoFrec, c);
			contentPanel.add(lblannoFrec);
			
			c.gridx = 3;
			c.gridy = 6;
			gridBagLayout.setConstraints(cmbannoFrec, c);
			contentPanel.add(cmbannoFrec);
			
			c.gridx = 4;
			c.gridy = 6;
			gridBagLayout.setConstraints(lbldelayOver, c);
			contentPanel.add(lbldelayOver);
			
			c.gridx = 5;
			c.gridy = 6;
			gridBagLayout.setConstraints(cmbdelayOver, c);
			contentPanel.add(cmbdelayOver);	
			
			
			c.gridx = 0;
			c.gridy = 7;
			gridBagLayout.setConstraints(lblAnnOver, c);
			contentPanel.add(lblAnnOver);
			
			c.gridx = 1;
			c.gridy = 7;
			gridBagLayout.setConstraints(cmbAnnOver, c);
			contentPanel.add(cmbAnnOver);
			
			c.gridx = 2;
			c.gridy = 7;
			gridBagLayout.setConstraints(lblDomain, c);
			contentPanel.add(lblDomain);
			
			c.gridx = 3;
			c.gridy = 7;
			gridBagLayout.setConstraints(jtfDomain, c);
			contentPanel.add(jtfDomain);
			

			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	

	private void setLayout(){
		setGridBagLayout();
		
		GridBagLayout contentLayout = new GridBagLayout();
		this.setLayout(contentLayout);
		GridBagConstraints c = null;
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		contentLayout.setConstraints(contentPanel, c);
		this.add(contentPanel);

	}
	



	public JTextField getTxtsrcclockId() {
		return txtsrcclockId;
	}

	public void setTxtsrcclockId(JTextField txtsrcclockId) {
		this.txtsrcclockId = txtsrcclockId;
	}

	public JTextField getsrcclockpri1() {
		return txtsrcclockpri1;
	}

	public void setTxtsrcclockpri1(JTextField txtsrcclockpri1) {
		this.txtsrcclockpri1 = txtsrcclockpri1;
	}
	public JTextField getTxtsrcclockpri2() {
		return txtsrcclockpri2;
	}

	public void setTxtsrcclockpri2(JTextField txtsrcclockpri2) {
		this.txtsrcclockpri2 = txtsrcclockpri2;
	}

	public JTextField getTxtsrcclockLevel() {
		return txtsrcclockLevel;
	}

	public void setTxtsrcclockLevel(JTextField txtsrcclockLevel) {
		this.txtsrcclockLevel = txtsrcclockLevel;
	}

	public JTextField getTxtinDelay() {
		return txtinDelay;
	}

	public void setTxtinDelay(JTextField txtinDelay) {
		this.txtinDelay = txtinDelay;
	}

	public JTextField getTxtoutDelay() {
		return txtoutDelay;
	}

	public void setTxtoutDelay(JTextField txtoutDelay) {
		this.txtoutDelay = txtoutDelay;
	}
	public JTextField getCmbSynCicle() {
		return cmbSynCicle;
	}

	public void setCmbSynCicle(JTextField cmbSynCicle) {
		this.cmbSynCicle = cmbSynCicle;
	}

	public JTextField getCmbNoteCicle() {
		return cmbNoteCicle;
	}

	public void setcmbNoteCicle(JTextField cmbNoteCicle) {
		this.cmbNoteCicle = cmbNoteCicle;
	}
	public JTextField getCmbAnnOver() {
		return cmbAnnOver;
	}

	public void setCmbAnnOver(JTextField cmbAnnOver) {
		this.cmbAnnOver = cmbAnnOver;
	}
	public JTextField getCmbdelayOver() {
		return cmbdelayOver;
	}

	public void setCmbdelayOver(JTextField cmbdelayOver) {
		this.cmbdelayOver = cmbdelayOver;
	}
	public JTextField getJtfDomain() {
		return jtfDomain;
	}

	public void setMacText(JTextField jtfDomain) {
		this.jtfDomain = jtfDomain;
	}
	
}

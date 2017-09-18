package com.nms.ui.ptn.ne.time.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import com.nms.db.bean.ptn.clock.PtpPortinfo;
import com.nms.db.bean.ptn.clock.TimeSyncInfo;
import com.nms.db.bean.system.code.Code;
import com.nms.ui.manager.CheckingUtil;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.control.PtnSpinner;
import com.nms.ui.manager.control.PtnTextField;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.keys.StringKeysTitle;


public class AddPtpPortDialog extends PtnDialog {


	private static final long serialVersionUID = 96267622523962139L;
	private TimeSyncPanel view;
	private TimeSyncInfo info;
	private PtpPortinfo  ptpPortinfo;
	
	public AddPtpPortDialog(TimeSyncInfo info, TimeSyncPanel view2, PtpPortinfo ptpPortinfo) {
		try {
			this.view = view2;
			this.info = info;
			this.ptpPortinfo = ptpPortinfo;
			this.initCompoent();
			this.setLayout();
			this.addListener();
			if (this.ptpPortinfo == null) {
				super.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_CREATE_PTP));
			}else{
				super.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_UPDATE_PTP));						
			}
			if(ResourceUtil.language.equals("zh_CN")){
				UiUtil.showWindow(this, 430, 440);
			}else{
				UiUtil.showWindow(this, 450, 440);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	
	/**
	 * 添加监听
	 */
	private void addListener() {
		this.btnCanel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddPtpPortDialog.this.dispose();
			}
		});
		

		this.btnSave.addActionListener(new MyActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {				
					saveInfo(ptpPortinfo);
					AddPtpPortDialog.this.dispose();
				} catch (Exception e1) {
					ExceptionManage.dispose(e1,this.getClass());
				}
			}

			@Override
			public boolean checking() {
				
				return true;
			}
		});
	}
	


	private void saveInfo(PtpPortinfo ptpPortinfo2) throws Exception {
		if(ptpPortinfo2 ==null){
			
			PtpPortinfo ptpinfo = new PtpPortinfo();
	        //端口号GE1
			ControlKeyValue PortNum = (ControlKeyValue) this.cmbPortNum.getSelectedItem();
			ptpinfo.setPortNum(Integer.parseInt(((Code) PortNum.getObject()).getCodeValue()));

			//工作模式
			ControlKeyValue workModel = (ControlKeyValue) this.cmbworkModel.getSelectedItem();
			ptpinfo.setWorkModel(Integer.parseInt(((Code) workModel.getObject()).getCodeValue()));
			
		    //线路不对称时延属性
			ControlKeyValue Line = (ControlKeyValue) this.cmbLine.getSelectedItem();
			ptpinfo.setLine(Integer.parseInt(((Code) Line.getObject()).getCodeValue()));
			
			//线路不对称时延补偿值
			ptpinfo.setLineCpn(Integer.parseInt(spinLineCpn.getTxt().getText()));
			
			//延时机制
			ControlKeyValue DelayMeca = (ControlKeyValue) this.cmbDelayMeca.getSelectedItem();
			ptpinfo.setDelayMeca(Integer.parseInt(((Code) DelayMeca.getObject()).getCodeValue()));
			
			//消息模式
			ControlKeyValue MessageMode = (ControlKeyValue) this.cmbMessageMode.getSelectedItem();
			ptpinfo.setMessageMode(Integer.parseInt(((Code) MessageMode.getObject()).getCodeValue()));
			
			//索引
			int index=Integer.parseInt(txtIndexId.getText());
			List<Integer> IndexIds =new ArrayList<Integer>();
			int size = this.view.getTable().getRowCount()+1;
			if(size!=1){
				for(int i=0;i<size-1;i++){
					IndexIds.add( this.view.getTable().getAllElement().get(i).getIndexId());				
				}
			}
			if(!txtIndexId.getText().matches(CheckingUtil.NUMBER_NUM)){
				 DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_INDEXERROR2));
			     return;	
			}else{
			      if(index<0 ||index>10){
				     DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_INDEXERROR));
				     return;				
			        }else{
				          if(ptpPortinfo2==null){
				                 if(IndexIds.contains(index)){
					               DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_INDEXERROR1));
					               return;	
				                   }else{
					                     ptpinfo.setIndexId(index);
				                   }
				              }
			        }
			}
			//端口ID
			List<Integer> portIds =new ArrayList<Integer>();
			int size2 = this.view.getTable().getRowCount()+1;
			if(size2!=1){
				for(int i=0;i<size-1;i++){
					portIds.add( this.view.getTable().getAllElement().get(i).getPortId());				
				}
			}
			
			boolean flag=true;
			int port =0;
			try{
			     port=Integer.parseInt(txtPortId.getText(), 16);
			}catch(Exception e){
				flag=false;
			}
			if(!flag){
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_PORTIDERROR));
				return;
			}else{
				if(port<1 ||port>65535){
					DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_PORTIDERROR1));
					return;	
				}else{					
					if(portIds.contains(port) ){
			               DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_PORTIDERROR2));
			               return;	
		                 }else{
		                	 ptpinfo.setPortId(port);
		                 }
					
				}
				
			}									
			int size1 = this.view.getTable().getRowCount()+1;
			if(size1 < 11)
			{				
				this.view.add(ptpinfo);
				this.view.updateUI();
			}
			else
			{
				DialogBoxUtil.errorDialog(this,ResourceUtil.srcStr(StringKeysTip.TIP_MAXNUM));
				return;
			}
	
		}else{
			
			PtpPortinfo ptpinfo = new PtpPortinfo();
	        //端口号GE1
			ControlKeyValue PortNum = (ControlKeyValue) this.cmbPortNum.getSelectedItem();
			ptpinfo.setPortNum(Integer.parseInt(((Code) PortNum.getObject()).getCodeValue()));
			
			//工作模式
			ControlKeyValue workModel = (ControlKeyValue) this.cmbworkModel.getSelectedItem();
			ptpinfo.setWorkModel(Integer.parseInt(((Code) workModel.getObject()).getCodeValue()));
			
		    //线路不对称时延属性
			ControlKeyValue Line = (ControlKeyValue) this.cmbLine.getSelectedItem();
			ptpinfo.setLine(Integer.parseInt(((Code) Line.getObject()).getCodeValue()));
			
			//线路不对称时延补偿值
			ptpinfo.setLineCpn(Integer.parseInt(spinLineCpn.getTxt().getText()));
			
			//延时机制
			ControlKeyValue DelayMeca = (ControlKeyValue) this.cmbDelayMeca.getSelectedItem();
			ptpinfo.setDelayMeca(Integer.parseInt(((Code) DelayMeca.getObject()).getCodeValue()));
			
			//消息模式
			ControlKeyValue MessageMode = (ControlKeyValue) this.cmbMessageMode.getSelectedItem();
			ptpinfo.setMessageMode(Integer.parseInt(((Code) MessageMode.getObject()).getCodeValue()));
			
			//索引
			int index=Integer.parseInt(txtIndexId.getText());
			List<Integer> IndexIds =new ArrayList<Integer>();
			int size = this.view.getTable().getRowCount()+1;
			if(size!=1){
				for(int i=0;i<size-1;i++){
					IndexIds.add( this.view.getTable().getAllElement().get(i).getIndexId());				
				}
			}
			if(!txtIndexId.getText().matches(CheckingUtil.NUMBER_NUM)){
				 DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_INDEXERROR2));
			     return;	
			   }else{
			         if(index<0 ||index>10){
			        	 
				          DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_INDEXERROR));
				          return;				          
			            }else{	
			            	
				              if(IndexIds.contains(index) && index!=ptpPortinfo2.getIndexId() ){
					               DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_INDEXERROR1));
					               return;	
				                 }else{
					                   ptpinfo.setIndexId(index);
				                 }
			             }
			}
			//端口ID
			
			List<Integer> portIds =new ArrayList<Integer>();
			int size2 = this.view.getTable().getRowCount()+1;
			if(size2!=1){
				for(int i=0;i<size-1;i++){
					portIds.add( this.view.getTable().getAllElement().get(i).getPortId());				
				}
			}
			
			boolean flag=true;
			int port =0;
			try{
			     port=Integer.parseInt(txtPortId.getText(), 16);
			}catch(Exception e){
				flag=false;
			}
			if(!flag){
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_PORTIDERROR));
				return;
			}else{					
				if(port<1 ||port>65535){
					DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_PORTIDERROR1));
					return;	
				}else{					
					if(portIds.contains(port) && port!=ptpPortinfo2.getPortId() ){
			               DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_PORTIDERROR2));
			               return;	
		                 }else{
		                	 ptpinfo.setPortId(port);
		                 }
					
				}				
			}								
			int size1 = this.view.getTable().getRowCount()+1;
			if(size1 < 11)
			{
				List<PtpPortinfo> p = view.getTable().getAllElement();				
		    		  for(int i=0;i<p.size();i++){
		    	          if(p.get(i)==this.view.getAllSelect().get(0)){	    		
		    		          p.set(i, ptpinfo)	;	    		         	    		
		    	           }
		    		 
		    	}	

		       Collections.reverse(p);
			   this.view.initData(p);
			   this.view.updateUI();			
			}
			else
			{
				DialogBoxUtil.errorDialog(this,ResourceUtil.srcStr(StringKeysTip.TIP_MAXNUM));
				return;
			}
	
			
				
				
			
			
		}
	}

	/**
	 * 初始化控件
	 * @throws Exception 
	 */
	private void initCompoent() throws Exception {
		this.lblMessage=new JLabel();
		this.btnSave = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE),false);
		this.btnCanel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		
		//端口号
		lblPortNum= new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PORTNUM));	
		cmbPortNum= new JComboBox();
		super.getComboBoxDataUtil().comboBoxData(cmbPortNum, "703portNum");	
		
		
		//工作模式
		lblworkModel= new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_WORKMODEL));
		cmbworkModel= new JComboBox();
		super.getComboBoxDataUtil().comboBoxData(cmbworkModel, "workModel");
		
		//端口ID十六进制数1-FFFFH
		lblPortId= new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PORTID));
		txtPortId = new PtnTextField();	
		txtPortId.setText("1"); 

	    //索引1-10
		indexId= new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_INDEX));		
		txtIndexId = new PtnTextField();
		txtIndexId.setText("1"); 
		
        //线路不对称时延属性
		lblLine= new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_LINE));
		cmbLine= new JComboBox();
		super.getComboBoxDataUtil().comboBoxData(cmbLine, "LinePerpo");
		
		//线路不对称时延补偿值
		lblLineCpn= new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_LINECPN));
		spinLineCpn = new PtnSpinner(1000,0,1);
		
		
		//延时机制
		lblDelayMeca= new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_DELAYMECA));
		cmbDelayMeca= new JComboBox();
		super.getComboBoxDataUtil().comboBoxData(cmbDelayMeca, "DelayMeca");
		
		//消息模式
		lblMessageMode= new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_MESSAGEMODE));
		cmbMessageMode= new JComboBox();
		super.getComboBoxDataUtil().comboBoxData(cmbMessageMode, "MessageMode");	
		if(ptpPortinfo!=null){
			txtPortId.setText(Integer.toHexString(view.getAllSelect().get(0).getPortId())+""); 			
			txtIndexId.setText(view.getAllSelect().get(0).getIndexId()+""); 
			super.getComboBoxDataUtil().comboBoxSelectByValue(cmbPortNum, view.getAllSelect().get(0).getPortNum()+"");
			super.getComboBoxDataUtil().comboBoxSelectByValue(cmbworkModel,view.getAllSelect().get(0).getWorkModel()+"");
			super.getComboBoxDataUtil().comboBoxSelectByValue(cmbLine,view.getAllSelect().get(0).getLine()+"");
			spinLineCpn.getTxt().setText(view.getAllSelect().get(0).getLineCpn()+"");				
			super.getComboBoxDataUtil().comboBoxSelectByValue(cmbDelayMeca,view.getAllSelect().get(0).getDelayMeca()+"");			
			super.getComboBoxDataUtil().comboBoxSelectByValue(cmbMessageMode,view.getAllSelect().get(0).getMessageMode()+"");
		}
	}

	private void setLayout() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 50, 200, 50 };
		componentLayout.columnWeights = new double[] { 0, 0, 0 };
		componentLayout.rowHeights = new int[] { 40, 40, 40, 40, 40, 40, 40, 40,40,15, 40, 15 };
		componentLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0, 0, 0, 0, 0,0,0, 0.2 };
		this.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.insets = new Insets(5, 5, 5, 5);
		componentLayout.setConstraints(this.lblMessage, c);
		this.add(this.lblMessage);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 15, 5);
		componentLayout.setConstraints(this.indexId, c);
		this.add(this.indexId);
		c.gridx = 1;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.txtIndexId, c);
		this.add(this.txtIndexId);
		
		
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 15, 5);
		componentLayout.setConstraints(this.lblPortNum, c);
		this.add(this.lblPortNum);
		c.gridx = 1;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.cmbPortNum, c);
		this.add(this.cmbPortNum);

		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.lblworkModel, c);
		this.add(this.lblworkModel);
		c.gridx = 1;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.cmbworkModel, c);
		this.add(this.cmbworkModel);

		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.lblPortId, c);
		this.add(this.lblPortId);
		c.gridx = 1;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.txtPortId, c);
		this.add(this.txtPortId);

		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.lblLine, c);
		this.add(this.lblLine);
		c.gridx = 1;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.cmbLine, c);
		this.add(this.cmbLine);
		
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.lblLineCpn, c);
		this.add(this.lblLineCpn);
		c.gridx = 1;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.spinLineCpn, c);
		this.add(this.spinLineCpn);

		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.lblDelayMeca, c);
		this.add(this.lblDelayMeca);
		c.gridx = 1;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.cmbDelayMeca, c);
		this.add(this.cmbDelayMeca);

		c.gridx = 0;
		c.gridy = 8;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.lblMessageMode, c);
		this.add(this.lblMessageMode);
		c.gridx = 1;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.cmbMessageMode, c);
		this.add(this.cmbMessageMode);

		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.EAST;
		c.gridx = 1;
		c.gridy = 10;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.btnSave, c);
		this.add(this.btnSave);
		c.gridx = 2;
		componentLayout.setConstraints(this.btnCanel, c);
		this.add(this.btnCanel);
	}

	
	
	



	private JLabel indexId;    //索引
	private PtnTextField txtIndexId;
	private JLabel lblPortNum;  //端口号
	private JComboBox cmbPortNum;	
	private JLabel lblworkModel;  //工作模式
	private JComboBox cmbworkModel;
	private JLabel lblPortId;    //端口ID
	private PtnTextField txtPortId;	
	private JLabel lblLine;  //线路不对称时延属性
	private JComboBox cmbLine;	
	private JLabel lblLineCpn;  //线路不对称时延补偿值
	private PtnSpinner spinLineCpn;
	private JLabel lblDelayMeca;  //延时机制
	private JComboBox cmbDelayMeca;	
	private JLabel lblMessageMode;  //消息模式
	private JComboBox cmbMessageMode;	
	private JLabel lblMessage;
	private PtnButton btnSave;
	private JButton btnCanel;
}

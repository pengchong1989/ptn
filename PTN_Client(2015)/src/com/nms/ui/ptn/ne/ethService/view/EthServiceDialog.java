package com.nms.ui.ptn.ne.ethService.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.CommonBean;
import com.nms.db.bean.ptn.EthServiceInfo;
import com.nms.db.enums.EOperationLogType;
import com.nms.drive.service.impl.CoderUtils;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.ptn.EthService_MB;
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
import com.nms.ui.manager.control.PtnSpinner;
import com.nms.ui.manager.control.PtnTextField;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.ne.ethService.controller.EthServiceController;

public class EthServiceDialog extends PtnDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4499393261798778659L;
	private EthServiceInfo ethServiceInfo;
	private EthServiceController controller;
	private JLabel vlanId;
	private PtnTextField vlanIdField;
	private PtnButton confirmButton;
	private JButton cancelButton;
	private List<JCheckBox> buttonList = new ArrayList<JCheckBox>();
	private List<PortInst> portList = new ArrayList<PortInst>();
	private JPanel contentPanel;
	private JPanel buttonPanel;
	private JScrollPane scrollPanel;
	private JPanel titlePanel;
	private JLabel lblMessage;
	private int maxNumber = 0;
	private JLabel numberLabel;
	private PtnSpinner ptnSpinnerNumber;
	private JPanel numberJpanel;
	
	
	public EthServiceDialog(EthServiceInfo ethServiceInfo, EthServiceController controller) {
		
		this.ethServiceInfo = ethServiceInfo;
		this.controller = controller;
		setModal(true);
		initComponent();
		setLayout();
		addListener();
		if (ethServiceInfo != null) {
			initData();
		}
		if(this.ethServiceInfo == null){
			this.ethServiceInfo = new EthServiceInfo();
		}
	}
	private void initComponent() {
		try {
			this.setTitle(ResourceUtil.srcStr(StringKeysPanel.ETHSERVICE_TABLE));
			lblMessage = new JLabel();
			titlePanel = new JPanel();
			contentPanel = new JPanel();
			buttonPanel = new JPanel();
			confirmButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIRM),true);
			cancelButton = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
			vlanId = new JLabel("Vlan ID");
			vlanIdField = new PtnTextField(false,PtnTextField.TYPE_INT,PtnTextField.INT_MAXLENGTH,this.lblMessage, this.confirmButton, this);
			setValidate(vlanIdField,ConstantUtil.LABOAMETNVLAN_MAXVALUE,ConstantUtil.LABETHSERVICSE_MINVALUE);
			vlanIdField.setText("2"); 
			getAllPort();
			contentPanel = new JPanel();
			scrollPanel = new JScrollPane();
			scrollPanel.setViewportView(contentPanel);
			scrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			/*批量创建以太网业务*/
			numberLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_CREATE_NUM));
			this.ptnSpinnerNumber =  new PtnSpinner(1, 1, 1000, 1);
			numberJpanel = new JPanel();
			numberJpanel.setLayout(new FlowLayout());
			numberJpanel.add(numberLabel);
			numberJpanel.add(ptnSpinnerNumber);
			ptnSpinnerNumber.setPreferredSize(new Dimension(80, 20));
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	/**
	 * 
	 * 修改，初始化查询的数据
	 */
	private void initData() {
		try {
			vlanIdField.setText(ethServiceInfo.getVlanId()+"");
			vlanIdField.setEditable(false);
			List<String> portNams = selectPort();
			for(int i = 0;i<buttonList.size();i++){
				if(portNams.contains(buttonList.get(i).getText().trim()))
				{
					buttonList.get(i).setSelected(true);
				}
			}
			this.ptnSpinnerNumber.setEnabled(false);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	public void setLayout(){
		// title面板布局
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		layout.rowHeights = new int[] { 10 };
		layout.rowWeights = new double[] { 0 };
		layout.columnWidths = new int[] { 30,100};
		layout.columnWeights = new double[] { 0, 1.0 };
		titlePanel.setLayout(layout);
		addComponent(titlePanel, vlanId, 0, 0, 0, 0, 1, 1, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.WEST, c);
		addComponent(titlePanel, vlanIdField, 1, 0, 0, 0,0, 1, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.WEST, c);
		// 主面板布局
		layout = new GridBagLayout();
		layout.rowHeights = new int[] { 60, 300, 60 };
		layout.rowWeights = new double[] { 0, 0.7, 0 };
		layout.columnWidths = new int[] { ConstantUtil.INT_WIDTH_THREE };
		layout.columnWeights = new double[] { 1 };
		this.setLayout(layout);
		
		addComponentJDialog(this, titlePanel, 0, 0, 0, 0, 1, 1, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER, c);
		addComponentJDialog(this, scrollPanel, 0, 1, 0, 0.2, 1, 1, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER, c);
		addComponentJDialog(this, buttonPanel, 0, 2, 0, 0, 1, 1, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER, c);

		// content面板布局
		setClockRorateButton();
		// button面板布局
		setButtonLayout();
	}
	

	private void addListener() {
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		
		confirmButton.addActionListener(new MyActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				confirm();
			}

			@Override
			public boolean checking() {
				// TODO Auto-generated method stub
				return true;
			}
		});
	}

	/**
	 * 保存按钮事件
	 * 
	 */
	private void confirm() {
		
		char[] portLine1 = {'0','0','0','0','0','0','0','0'};
		char[] portLine2 = {'0','0','0','0','0','0','0','0'};
		char[] portLine3 = {'0','0','0','0','0','0','0','0'};
		char[] portLine4 = {'0','0','0','0','0','0','0','0'};
		
		DispatchUtil dispath = null; 
		int count = 0;
		List<EthServiceInfo> ethServiceList = null;
		List<Integer> vlanId = null;
		EthService_MB ethService = null;
		EthServiceInfo eth=null;
		try {
			
			ethService = (EthService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ETHSERVICE);
			
			if(buttonList.size() ==0){
				DialogBoxUtil.errorDialog(this,ResourceUtil.srcStr(StringKeysTip.TIP_USER_ERROR));
				return ;
			}
			vlanId = new ArrayList<Integer>();
			if(ethServiceInfo.getId() >0){
				eth=ethService.selectById(ethServiceInfo.getId());
				List<String> portNams = selectPort();
				List<CommonBean> bean=new ArrayList<CommonBean>();
				for(int i = 0;i<buttonList.size();i++){
					if(portNams.contains(buttonList.get(i).getText().trim()))
					{
						CommonBean common = new CommonBean();
						common.setClockPortName(buttonList.get(i).getText().trim());
						bean.add(common);						
					}
				}
				eth.setCommon(bean);
			}
			if(ethServiceInfo.getId() >0){
				if(ethServiceInfo.getVlanId() != Integer.parseInt(vlanIdField.getText().trim())){
					vlanId.add(Integer.parseInt(vlanIdField.getText().trim()));
					if(isUser(vlanId,ethService)){
						DialogBoxUtil.errorDialog(this,ResourceUtil.srcStr(StringKeysTip.TIP_ISUSER_VLANID));
						return ;
					}
				}
			}
			//获取相应的port
			for(PortInst portInst : portList)
			{
				if(getPortNumber(portInst.getPortName()))
				{
					int number = portInst.getNumber();
					if(number<9){
						portLine1[8-number] = "1".charAt(0);
					}else if(number>8 && number<17){
						portLine2[16-number] = "1".charAt(0);
					}else if(number>16 && number<25){
						portLine3[24-number] = "1".charAt(0);
					}else {
						portLine4[32-number] = "1".charAt(0);
					}
					count = 1;
				}
			}
			
				
			if(count == 0)
			{
				DialogBoxUtil.errorDialog(this,ResourceUtil.srcStr(StringKeysTip.TIP_MUSTNETWORK_BEFORE));
				return ;
			}
			
			int num = Integer.parseInt(this.ptnSpinnerNumber.getValue().toString());
			ethServiceList = new ArrayList<EthServiceInfo>();
			//新建批量
            if(num >1)
            {
            	int allCount = ethService.allCount(ConstantUtil.siteId);
            	if(allCount+num >254)
            	{
            		DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_EXCEED_MAXVALUE));
            		return ;
            	}
            	if((Integer.parseInt(vlanIdField.getText().trim())+num-1)>4094){
            		DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_VLANID_OVER));
            		return ;
            	}
            	for(int i =0;i<num;i++)
            	{
            		EthServiceInfo ethInfo = getEthServiceInfo(portLine1,portLine2,portLine3,portLine3,i,0);
            		List<CommonBean> newcommList = new ArrayList<CommonBean>();
        			for(int j = 0; j<buttonList.size();j++){
        				if(buttonList.get(j).isSelected())
        				{					
        					CommonBean com = new CommonBean();
        					com.setClockPortName(buttonList.get(j).getText().trim());
        					newcommList.add(com);		
        				}
        			}
        			ethInfo.setCommon(newcommList);
            		vlanId.add(Integer.parseInt(vlanIdField.getText().trim())+i);
            		ethServiceList.add(ethInfo);
            	}
            }else
            {
            	ethServiceInfo = getEthServiceInfo(portLine1,portLine2,portLine3,portLine3,0,ethServiceInfo.getId());
            	List<CommonBean> newcommList = new ArrayList<CommonBean>();
    			for(int j = 0; j<buttonList.size();j++){
    				if(buttonList.get(j).isSelected())
    				{					
    					CommonBean com = new CommonBean();
    					com.setClockPortName(buttonList.get(j).getText().trim());
    					newcommList.add(com);		
    				}
    			}
    			ethServiceInfo.setCommon(newcommList);
            	vlanId.add(Integer.parseInt(vlanIdField.getText().trim()));
            	ethServiceList.add(ethServiceInfo);
            }
            if(ethServiceInfo.getId() ==0){
            	if(isUser(vlanId,ethService)){
    				DialogBoxUtil.errorDialog(this,ResourceUtil.srcStr(StringKeysTip.TIP_ISUSER_VLANID));
    				return ;
    			}
            }
            int operateKey = 0;
            if(ethServiceInfo.getId() > 0)
			{
				operateKey = EOperationLogType.UPDATESERVICE.getValue();
			}else
			{
				operateKey = EOperationLogType.CREATESERVICE.getValue();
			}
			dispath = new DispatchUtil(RmiKeys.RMI_ETHSERVICE);
			String	result = dispath.excuteInsert(ethServiceList);
			if(operateKey == EOperationLogType.CREATESERVICE.getValue()){
			   for(int i=0;i<ethServiceList.size();i++){
				   this.insertOpeLog(EOperationLogType.CREATESERVICE.getValue(), result, null,ethServiceList.get(i));
			   }
			}else if(operateKey == EOperationLogType.UPDATESERVICE.getValue()){
				this.insertOpeLog(EOperationLogType.UPDATESERVICE.getValue(), result, eth,ethServiceList.get(0));
			}
			
			//添加日志记录		
			// 隐藏界面
			this.dispose();
			DialogBoxUtil.succeedDialog(this, result);
		    this.controller.refresh();
		    
	   } catch (Exception e) {
	 	ExceptionManage.dispose(e,this.getClass());
	  }finally{
		 UiUtil.closeService_MB(ethService);
	     portLine1 = null;
	     portLine2 = null;
		 portLine3 = null;
		 portLine4 = null;
		 dispath = null; 
		 vlanId = null;
	  }
	}
	
	private void insertOpeLog(int operationType, String result, EthServiceInfo oldMac, EthServiceInfo newMac){		
	   AddOperateLog.insertOperLog(confirmButton, operationType, result, oldMac, newMac, newMac.getSiteId(),ResourceUtil.srcStr(StringKeysPanel.ETHSERVICE_TABLE),"ethService");		
	}
	
	private EthServiceInfo getEthServiceInfo(char[] portLine1,char[] portLine2,char[] portLine3,char[] portLine4,int i,int id)
	{
		EthServiceInfo ethService = new EthServiceInfo();
		ethService.setVlanId(Integer.parseInt(vlanIdField.getText().trim())+i);
		ethService.setPortLine1(CoderUtils.convertAlgorism(portLine1));
		ethService.setPortLine2(CoderUtils.convertAlgorism(portLine2));
		ethService.setPortLine3(CoderUtils.convertAlgorism(portLine3));
		ethService.setPortLine4(CoderUtils.convertAlgorism(portLine4));
		ethService.setId(id);
		ethService.setSiteId(ConstantUtil.siteId);
		return ethService;
	}
	
	private boolean getPortNumber(String portName)
	{
		try {			
			for(int i = 0; i<buttonList.size();i++){
				if(buttonList.get(i).isSelected() && buttonList.get(i).getText().equals(portName))
				{					
					return true;
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return false;
	}
	
	/**
	 * //验证根据网元上的所有VLANID不能一样
	 */
	private boolean isUser(List<Integer> vlans,EthService_MB ethService){
		try {
			return  ethService.select(vlans,ConstantUtil.siteId);
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return false;
	}
	
/**
 * 取消按钮事件
 */
	private void cancel() {
		this.dispose();
	}
	/**
	 * 为文本控件赋最大值和最小值
	 * @param txtField
	 * @param max
	 * @param min
	 * @throws Exception
	 */
	private void setValidate(PtnTextField txtField,int max,int min) throws Exception{
		try {
			txtField.setCheckingMaxValue(true);
			txtField.setCheckingMinValue(true);
			txtField.setMaxValue(max);
			txtField.setMinValue(min);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 保存，取消按钮布局
	 */
	private void setButtonLayout() {
		GridBagLayout buttonLayout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		buttonLayout.columnWidths = new int[] {300,50,50};
		buttonLayout.columnWeights = new double[] {  0, 0, 0 };
		buttonLayout.rowHeights = new int[] { 30 };
		buttonLayout.rowWeights = new double[] { 0.0 };
		buttonPanel.setLayout(buttonLayout);
		
		addComponent(buttonPanel, numberJpanel, 0, 0, 0, 1, 1, 1, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), GridBagConstraints.WEST, c);
		addComponent(buttonPanel, lblMessage, 0, 1, 0, 1, 1, 1, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), GridBagConstraints.WEST, c);
		addComponent(buttonPanel, confirmButton, 1, 1, 0, 1, 1, 1, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), GridBagConstraints.WEST, c);
		addComponent(buttonPanel, cancelButton, 2, 1, 0, 1, 1, 1, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), GridBagConstraints.WEST, c);
	}
	
	public void setClockRorateButton(){
		GridBagLayout contentLayout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPanel.setLayout(contentLayout);
		Insets insert = new Insets(5, 50, 5, 5);
		for (int i = 0; i < buttonList.size(); i++) {
			if(i%2 == 1){
				this.addComponent(contentPanel, buttonList.get(i), 1, (i+1)/2, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
			}else{
				this.addComponent(contentPanel, buttonList.get(i), 0, (i+2)/2, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
			}
		}
	}
	
	public void addComponent(JPanel panel, JComponent component, int gridx, int gridy, double weightx, double weighty, int gridwidth, int gridheight, int fill, Insets insets, int anchor, GridBagConstraints gridBagConstraints) {
		gridBagConstraints.gridx = gridx;
		gridBagConstraints.gridy = gridy;
		gridBagConstraints.weightx = weightx;
		gridBagConstraints.weighty = weighty;
		gridBagConstraints.gridwidth = gridwidth;
		gridBagConstraints.gridheight = gridheight;
		gridBagConstraints.fill = fill;
		gridBagConstraints.insets = insets;
		gridBagConstraints.anchor = anchor;
		panel.add(component, gridBagConstraints);
	}
	
	public void addComponentJDialog(JDialog panel, JComponent component, int gridx, int gridy, double weightx, double weighty, int gridwidth, int gridheight, int fill, Insets insets, int anchor, GridBagConstraints gridBagConstraints) {
		gridBagConstraints.gridx = gridx;
		gridBagConstraints.gridy = gridy;
		gridBagConstraints.weightx = weightx;
		gridBagConstraints.weighty = weighty;
		gridBagConstraints.gridwidth = gridwidth;
		gridBagConstraints.gridheight = gridheight;
		gridBagConstraints.fill = fill;
		gridBagConstraints.insets = insets;
		gridBagConstraints.anchor = anchor;
		panel.add(component, gridBagConstraints);
		
	}
	private List<String> selectPort(){
		
		  List<Integer> selectPort = new ArrayList<Integer>();
		  String port = setVlue(CoderUtils.convertBinary(ethServiceInfo.getPortLine1()));
		  String port2 = setVlue(CoderUtils.convertBinary(ethServiceInfo.getPortLine2()));
		  String port3 = setVlue(CoderUtils.convertBinary(ethServiceInfo.getPortLine3()));
		  String port4 = setVlue(CoderUtils.convertBinary(ethServiceInfo.getPortLine4()));
		  selectPort.addAll(allSlectPort(port,0));
		  selectPort.addAll(allSlectPort(port2,8));
		  selectPort.addAll(allSlectPort(port3,16));
		  selectPort.addAll(allSlectPort(port4,24));
		  PortService_MB portService = null;
		  List<String> portNames = null;
			try {
				portService = (PortService_MB)ConstantUtil.serviceFactory.newService_MB(Services.PORT);
				portNames = new ArrayList<String>();
				portNames = portService.getAllPortNameByNumber(selectPort,ConstantUtil.siteId);
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}finally{
				UiUtil.closeService_MB(portService);
				selectPort = null;
			}
			return portNames;
	}
	
	private String setVlue(String port){
		String ss ="";
		if(port.length()<8){
			for(int i=0;i<8-port.length();i++){
				ss+= "0";
			}
		}
		return ss+port;
	}
	
	private List<Integer> allSlectPort(String value,int count){
		 List<Integer> selectPort = new ArrayList<Integer>();
		try {
			int label = 1;
			for(int i= value.length()-1;i>=0;i--){
				if(value.substring(i, i+1).equals("1")){
				 selectPort.add(label+count);
				}
				label ++;
			 }
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return selectPort;
	}
	
	private void getAllPort(){
		PortService_MB portService = null;
		try {
			portService = (PortService_MB)ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			PortInst portInst = new PortInst();
			portInst.setSiteId(ConstantUtil.siteId);
			List<PortInst> portInfoList = portService.select(portInst);
			if(portInfoList != null && portInfoList.size()>0){
				for(int i=0;i<portInfoList.size();i++){
					if (portInfoList.get(i).getPortType().equalsIgnoreCase("NNI") || portInfoList.get(i).getPortType().equalsIgnoreCase("UNI")
							|| portInfoList.get(i).getPortType().equalsIgnoreCase("NONE")){
						JCheckBox ge = new JCheckBox(portInfoList.get(i).getPortName());
						if(portInfoList.get(i).getNumber() > maxNumber)
						{
							maxNumber = portInfoList.get(i).getNumber();
						}
						buttonList.add(ge);
						portList.add(portInfoList.get(i));
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(portService);
		}
	}
}
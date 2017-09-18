package com.nms.db.bean.ptn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.drive.service.impl.CoderUtils;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.util.Services;
import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class EthServiceInfo extends ViewDataObj implements Serializable{

	private int id;
	private int siteId;
	private int vlanId;
	private int portLine1;//所选端口bit0/bit1/…/bit7=port1/port2/…port8
	private int portLine2;//所选端口bit0/bit1/…/bit7=port9/port10/…port16
	private int portLine3;//所选端口bit0/bit1/…/bit7=port17/port10/…port24
	private int portLine4;//所选端口bit0/bit1/…/bit7=port17/port10/…port24
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVlanId() {
		return vlanId;
	}
	public void setVlanId(int vlanId) {
		this.vlanId = vlanId;
	}
	public int getPortLine1() {
		return portLine1;
	}
	public void setPortLine1(int portLine1) {
		this.portLine1 = portLine1;
	}
	public int getPortLine2() {
		return portLine2;
	}
	public void setPortLine2(int portLine2) {
		this.portLine2 = portLine2;
	}
	public int getPortLine3() {
		return portLine3;
	}
	public void setPortLine3(int portLine3) {
		this.portLine3 = portLine3;
	}
	public int getPortLine4() {
		return portLine4;
	}
	public void setPortLine4(int portLine4) {
		this.portLine4 = portLine4;
	}
	@Override
	public void putObjectProperty() {
		try {
			this.putClientProperty("Vlan ID", getVlanId()+"");
			this.putClientProperty("portName", getSelectPortName());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	
	
	private String getSelectPortName(){
		
		StringBuffer binary = new StringBuffer();
		try {
		  List<Integer> selectPort = new ArrayList<Integer>();
		  Map<Integer, String> portNameList = getAllPortList();
		  selectPort = selectPort();
	      if(selectPort != null && selectPort.size() >0){
             for(int i= 0;i<selectPort.size();i++){
				binary.append(portNameList.get(selectPort.get(i))==null?"":portNameList.get(selectPort.get(i))+"/"); 
		      }	  
		   }
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		if(!binary.toString().equals("")){
			return binary.toString().substring(0, binary.toString().lastIndexOf("/"));
		}else{
			return "";
		}
	}
	
	private List<Integer> selectPort(){
		  List<Integer> selectPort = new ArrayList<Integer>();
		  String port = setVlue(portLine1);
		  String port2 = setVlue(portLine2);
		  String port3 = setVlue(portLine3);
		  String port4 = setVlue(portLine4);
		  selectPort.addAll(allSlectPort(port,0));
		  selectPort.addAll(allSlectPort(port2,8));
		  selectPort.addAll(allSlectPort(port3,16));
		  selectPort.addAll(allSlectPort(port4,24));
		  return selectPort;
	}
	
	private String setVlue(int value){
		String ports = "";
		if(value == 1){
			ports = "1";
		}else{
			ports = CoderUtils.convertBinary(value);
		}
		String ss ="";
		if(ports.length()<8){
			for(int i =0 ;i< 8- ports.length();i++){
				ss+= "0";
			}
		}
		return ss+ports;
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
	
	
	
	private Map<Integer, String> getAllPortList(){
		
		PortService_MB portService = null;
		Map<Integer, String>  portNameList = new HashMap<Integer, String>();
		
		try {
			portService = (PortService_MB)ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			PortInst portInst = new PortInst();
			portInst.setSiteId(ConstantUtil.siteId);
			List<PortInst> portInfoList = portService.select(portInst);
			if(portInfoList != null && portInfoList.size()>0){
				for(int i=0;i<portInfoList.size();i++){
					if (portInfoList.get(i).getPortType().equalsIgnoreCase("NNI") || portInfoList.get(i).getPortType().equalsIgnoreCase("UNI")
							|| portInfoList.get(i).getPortType().equalsIgnoreCase("NONE")){
						portNameList.put(portInfoList.get(i).getNumber(), portInfoList.get(i).getPortName());
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(portService);
		}
		return portNameList;
	}
	
}

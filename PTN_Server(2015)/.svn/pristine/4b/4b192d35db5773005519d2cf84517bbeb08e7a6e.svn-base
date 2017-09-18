package com.nms.snmp.ninteface.impl.inventory;

import java.util.ArrayList;
import java.util.List;

import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;

import com.nms.snmp.ninteface.framework.TableHandler;
import com.nms.snmp.ninteface.impl.alarm.HisAlarmConvertXml;
import com.nms.snmp.ninteface.impl.ftp.FtpTransFile4SNMP;
import com.nms.ui.manager.ExceptionManage;


public class HisAlarmTable extends TableHandler{

   public HisAlarmTable (){
		
	}
	
	@Override
	public void updateTable(Object obj) {
		
//		 if(obj !=null){
//			addRow(new OID("1"), (Variable[])obj);
//		 }else{
//			HisAlarmConvertXml  HisAlarmConvertXml = new HisAlarmConvertXml();
//			String fileName = HisAlarmConvertXml.getHisAlarmXml(null);
			Variable[] variables = new Variable[]{
				new OctetString(""),
				new OctetString(""),
				new OctetString(""),
			 };
			addRow(new OID("1"), variables);
//		 }
	}

	//get命令 带参的处理
	@Override
	public Object getInterfaceData(List<VariableBinding> vbList) {
		
		String[] time = new String[2];
		VariableBinding var = null;
		Variable[] variables = null ;
		try {
//			String fileName = null;
//			HisAlarmConvertXml  HisAlarmConvertXml = new HisAlarmConvertXml();
//			if(vbList != null && vbList.size()>0){
//				var = vbList.get(0);                             
//				if(var.getOid().toString().contains("1.3.6.1.4.1.44484.1.4.2.1")){
//					if((!var.getVariable().toString().equalsIgnoreCase("null"))&&var.getVariable().toString().length()>13){
//						time[0] = var.getVariable().toString().split(",")[0];
//						time[1] = var.getVariable().toString().split(",")[1];
//					}
//				}
//				if((time[0] !=null && time[0].equalsIgnoreCase("null") )&&(time[1] != null && !time[1].equalsIgnoreCase("null"))){
//					fileName = HisAlarmConvertXml.getHisAlarmXml(time);
//					variables = new Variable[]{
//						new OctetString(""),
//						new OctetString(fileName),
//						new OctetString(fileName)
//					};
//				}else{
//					fileName = HisAlarmConvertXml.getHisAlarmXml(null);
//					 variables = new Variable[]{
//						new OctetString(""),
//						new OctetString(fileName),
//						new OctetString(fileName)
//					};
//				}
//			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return variables;
	}

	@Override
	public boolean setInterfaceData(List<VariableBinding> vbList) {
		String[] time = new String[2];
		VariableBinding var = null;
		try {
			String fileName = null;
			HisAlarmConvertXml  hisAlarmConvertXml = new HisAlarmConvertXml();
			if(vbList != null && vbList.size()>0){
				var = vbList.get(0);                             
				if(var.getOid().toString().contains("1.3.6.1.4.1.44484.1.4.2.1")){
					if((!var.getVariable().toString().equalsIgnoreCase("null"))&&var.getVariable().toString().length()>13){
						time[0] = var.getVariable().toString().split(",")[0];
						time[1] = var.getVariable().toString().split(",")[1];
					}
				}
				if((time[0] !=null && !time[0].equalsIgnoreCase("null") )&&(time[1] != null && !time[1].equalsIgnoreCase("null"))){
					fileName = hisAlarmConvertXml.getHisAlarmXml(time);
				}else{
					fileName = hisAlarmConvertXml.getHisAlarmXml(null);
				}
				//移动规范要求每次set操作之后,将文件通过ftp传输到指定路径
				if(fileName != null){
					List<String> transFileList = new ArrayList<String>();
					transFileList.add(fileName+".zip");
					new FtpTransFile4SNMP(transFileList).FileTranfer();
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return true;
	}

	@Override
	public void setTable(List<VariableBinding> vbList) {
		for(VariableBinding vb : vbList){
			moTable.setValue(vb);
		}
	}
	
}

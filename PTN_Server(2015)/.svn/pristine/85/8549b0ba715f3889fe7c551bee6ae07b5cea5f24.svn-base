package com.nms.snmp.ninteface.impl.inventory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;

import com.nms.snmp.ninteface.framework.TableHandler;

public class PerformanceTable extends TableHandler {
	public PerformanceTable(){
		
	}
	
	public static void main(String[] args) {
		new PerformanceTable().updateTable("hisPerformance_20140729000000.xml");
	}
	
	@Override
	public void updateTable(Object obj) {
		 List<String> fileList = new ArrayList<String>();
		 String[] filePaths = {"snmpData\\PM"};
		 if(null == obj){
			 //获取所有文件列表
			 for (String filePath : filePaths) {
				this.getPmFileList(filePath, fileList);
			}
		 }else{
			 if(obj instanceof String){
				 //获取单个文件路径
				 String fileName = (String) obj;
				 for (String filePath : filePaths) {
					File dir = new File(filePath+"\\"+fileName);
					if(dir.exists()){
						if(!fileList.contains(dir.getAbsolutePath())){
							fileList.add(dir.getAbsolutePath());
						}
					}
				}
			 }
		 }
		 int i = 1;
		 for (String filePath : fileList) {
			 Variable[] rowValues = new Variable[] {
					 new OctetString(filePath),
			 };
			 addRow(new OID(""+i),rowValues);
			 i++;
		}
	}

	/**
	 * 获取文件信息列表
	 */
	private void getPmFileList(String filePath, List<String> fileList) {
		 File dir = new File(filePath);
		 File[] fileArr = dir.listFiles();
		 if(fileArr != null){
			 for (int i = 0; i < fileArr.length; i++) {
				 String fileName = fileArr[i].getAbsolutePath();
				 if(!fileList.contains(fileName)){
					 fileList.add(fileName);
				 }
			}
		 }
	}

	@Override
	public Object getInterfaceData(List<VariableBinding> vbList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setInterfaceData(List<VariableBinding> vbList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setTable(List<VariableBinding> vbList) {
		for (VariableBinding vb : vbList) {
			moTable.setValue(vb);
		}
	}
}


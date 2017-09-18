package com.nms.drive.analysis.datablock;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.analysis.xmltool.bean.ListRoot;
import com.nms.drive.service.bean.AlarmShieldObject;
import com.nms.drive.service.bean.AlarmShieldType;
import com.nms.drive.service.impl.CoderUtils;
import com.nms.ui.manager.ExceptionManage;

public class AnalysisAlarmShieldTable extends AnalysisBase {
	
	
	public byte[] analysisAclToCommands(AlarmShieldObject alarmShieldObject, String configXml) throws Exception {
		try {
			long l = System.currentTimeMillis();
			Date date = new Date(l);
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			String year = calendar.get(calendar.YEAR) + "";
			String month = calendar.get(calendar.MONTH) + "";
			String dateTime = calendar.get(calendar.DAY_OF_MONTH) + "";
			String hours = calendar.get(calendar.HOUR_OF_DAY) + "";
			String min = calendar.get(calendar.MINUTE)+"";
			String secByte = calendar.get(calendar.SECOND)+"";
			
			DriveRoot driveRoot = super.LoadDriveXml(configXml);
			//协议版本号
			driveRoot.getDriveAttributeList().get(0).setValue(alarmShieldObject.getTreatyNumber()+"");
			driveRoot.getDriveAttributeList().get(1).setValue(alarmShieldObject.getNeShield()+"");
			driveRoot.getDriveAttributeList().get(2).setValue(alarmShieldObject.getAlarmShieldTypeList().size()+"");
			String file = driveRoot.getDriveAttributeList().get(2).getListRootList().get(0).getFile();
			driveRoot.getDriveAttributeList().get(2).getListRootList().clear();//清除本身自带的一个ListRoot
			driveRoot.getDriveAttributeList().get(4).setValue(year);
			driveRoot.getDriveAttributeList().get(5).setValue(month);
			driveRoot.getDriveAttributeList().get(6).setValue(dateTime);
			driveRoot.getDriveAttributeList().get(7).setValue(hours);
			driveRoot.getDriveAttributeList().get(8).setValue(min);
			driveRoot.getDriveAttributeList().get(9).setValue(secByte);
			
			for (int i = 0; i < alarmShieldObject.getAlarmShieldTypeList().size(); i++) {
				DriveRoot driveRoot_sub = super.LoadDriveXml(file);
				ListRoot listRoot = new ListRoot();
				listRoot.setDriveAttributeList(driveRoot_sub.getDriveAttributeList());
				for (int j = 0; j < listRoot.getDriveAttributeList().size(); j++) {
					// 属性赋值
					this.setValueToDriveAttribute(alarmShieldObject.getShieldType(),alarmShieldObject.getAlarmShieldTypeList().get(i),listRoot,listRoot.getDriveAttributeList().get(j));
				}
				driveRoot.getDriveAttributeList().get(2).getListRootList().add(listRoot);
			}	
			
			AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
			byte[] dataCommand = analysisDriveXmlToCommand.analysisCommands(driveRoot);
			CoderUtils.print16String(dataCommand);
			return dataCommand;
		} catch (Exception e) {
			throw e;
		}
	}

	private void setValueToDriveAttribute(int type,AlarmShieldType alarmShieldType,ListRoot listRoot,DriveAttribute driveAttribute) {
		//表示此屏蔽配置条目的格式,0x01 表示先列出线路代码，在列出屏蔽代码；0x02表示先列出屏蔽代码
		if ("屏蔽配置条目类型".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(type+ "");
		}else if("单线路号总个数".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(alarmShieldType.getLineCountList().size()+ "");
			if(alarmShieldType.getLineCountList().size()>0){
				String file = driveAttribute.getListRootList().get(0).getFile();
				driveAttribute.getListRootList().clear();
				try {
					for(int i = 0;i<alarmShieldType.getLineCountList().size();i++){
						DriveRoot	driveRoot_sub = super.LoadDriveXml(file);
						ListRoot listRoots = new ListRoot();
						listRoots.setDriveAttributeList(driveRoot_sub.getDriveAttributeList());
						for (int j = 0; j < listRoots.getDriveAttributeList().size(); j++) {
							// 属性赋值
							this.setValueToDriveAttribute(alarmShieldType.getLineCountList().get(i),listRoots.getDriveAttributeList().get(j));
						}
						driveAttribute.getListRootList().add(listRoots);
					}
				} catch (Exception e) {
					ExceptionManage.dispose(e, this.getClass());
				}
			}
		}else if("线路号区域总个数".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(alarmShieldType.getAreaCountList().size()+ "");
			if(alarmShieldType.getAreaCountList().size()>0){
				String file = driveAttribute.getListRootList().get(0).getFile();
				driveAttribute.getListRootList().clear();
				try {
					for(Integer key : alarmShieldType.getAreaCountList().keySet()){
						DriveRoot driveRoot_sub = super.LoadDriveXml(file);
						ListRoot listRoots = new ListRoot();
						listRoots.setDriveAttributeList(driveRoot_sub.getDriveAttributeList());
						for (int j = 0; j < listRoots.getDriveAttributeList().size(); j++) {
							// 属性赋值
							this.setValueToDriveAttribute(key,alarmShieldType.getAreaCountList().get(key),listRoots.getDriveAttributeList().get(j));
						}
						driveAttribute.getListRootList().add(listRoots);
					}
				} catch (Exception e) {
					ExceptionManage.dispose(e, this.getClass());
				}
			}
		}else if ("全屏蔽标志".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(alarmShieldType.getShieldAlarmCode()+ "");
		}else if ("代码总数".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(alarmShieldType.getShieldAlarmCodeCountList().size()+ "");
			if(alarmShieldType.getShieldAlarmCodeCountList().size() > 0){
				String file = driveAttribute.getListRootList().get(0).getFile();
				driveAttribute.getListRootList().clear();
				try {
					for(int i = 0;i<alarmShieldType.getShieldAlarmCodeCountList().size();i++){
						DriveRoot driveRoot_sub = super.LoadDriveXml(file);
						ListRoot listRoots = new ListRoot();
						listRoots.setDriveAttributeList(driveRoot_sub.getDriveAttributeList());
						for (int j = 0; j < listRoots.getDriveAttributeList().size(); j++) {
							// 属性赋值
							this.setValueAlarmCodeToDriveAttribute(alarmShieldType.getShieldAlarmCodeCountList().get(i),listRoots.getDriveAttributeList().get(j));
						}
						driveAttribute.getListRootList().add(listRoots);
					}
				} catch (Exception e) {
					ExceptionManage.dispose(e, this.getClass());
				}
			}
		}
	}

	private void setValueAlarmCodeToDriveAttribute(Integer integer,DriveAttribute driveAttribute) {
		
		if ("屏蔽代码个数".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(integer+ "");
		}
	}

	private void setValueToDriveAttribute(int startArea,int endArea,DriveAttribute driveAttribute) {
		if ("区域开始线路号".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(startArea+ "");
		}else if ("区域结束线路号".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(endArea+ "");
		}
	}

	private void setValueToDriveAttribute(Integer integer,DriveAttribute driveAttribute) {
		if ("单线路号".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(integer+ "");
		}
	}


}

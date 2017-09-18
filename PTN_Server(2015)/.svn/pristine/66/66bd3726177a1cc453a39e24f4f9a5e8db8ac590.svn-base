package com.nms.drive.analysis.datablock;

import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.analysis.xmltool.bean.ListRoot;
import com.nms.drive.service.bean.ManagementObject;
import com.nms.drive.service.bean.NE;
import com.nms.drive.service.bean.WorkSpace;
import com.nms.drive.service.bean.WorkSpaceUser;
import com.nms.ui.manager.ExceptionManage;

/**
 * 管理配置块解析
 * @author 郭清川
 *
 */
public class AnalysisManagementTable extends AnalysisBase {
	/**
	 * 根据管理配置对象生成字节数组
	 * @param ManagementObject对象
	 * @param configXml配置文件
	 * @return Commands命令
	 * @throws Exception
	 */
	 
	public byte[] AnalysisObjectToCommand(ManagementObject managementObj,String configXml)throws Exception{
		 
			DriveRoot driveRoot_config = super.LoadDriveXml(configXml);//获得driveRoot对象
			List<DriveAttribute> list1 = driveRoot_config.getDriveAttributeList();
			//赋值
			objToDriveAttribute(list1,managementObj);
			AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
			byte[] dataCommand = analysisDriveXmlToCommand.analysisCommands(driveRoot_config);
			return dataCommand;
	}
	
	 
	public void objToDriveAttribute(List<DriveAttribute> list1, ManagementObject managementObj) {
		for (int i = 0; i < list1.size(); i++) {
			DriveAttribute driveAttribute = list1.get(i);
			if("网元开关".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(managementObj.getNeSwitch()+"");
			}
			if("管理数据标识".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(managementObj.getManageDataTag()+"");
			}
			if("年".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(managementObj.getYear());
			}
			if("月".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(managementObj.getMonth());
			}
			if("日".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(managementObj.getDay());
			}
			if("时".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(managementObj.getHour());
			}
			if("分".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(managementObj.getMinute());
			}
			if("秒".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(managementObj.getSecond());
			}
			if("扩展标志".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(managementObj.getExpendFlag());
			}
			if("工作站数".equals(driveAttribute.getDescription())){
				List<WorkSpace> workSpaces = managementObj.getWorkSpaces();
				 
				//获得管理配置下工作站数配置子文件file1
				String file1 = driveAttribute.getListRootList().get(0).getFile();
				//清除自带的listRoot
				driveAttribute.getListRootList().clear();
				driveAttribute.setValue(workSpaces.size()+"");
				//WorkSpace对象集合循环
				for (int j = 0; j < workSpaces.size(); j++) {
					try {
						DriveRoot workSpaceDriveRoot = super.LoadDriveXml(file1);
						WorkSpace workSpace = workSpaces.get(j);
						List<DriveAttribute> list2 = workSpaceDriveRoot.getDriveAttributeList();
						ListRoot listRoot1 = new ListRoot();
						for (int k = 0; k < list2.size(); k++) {
							DriveAttribute driveAttribute1 = list2.get(k);
							objToWorkSpaceDriveAttribute(driveAttribute1,workSpace);
						}
						listRoot1.setDriveAttributeList(workSpaceDriveRoot.getDriveAttributeList());
						driveAttribute.getListRootList().add(listRoot1);
					} catch (Exception e) {
						 ExceptionManage.dispose(e,this.getClass());
					}
				}	
			}
			
			if("总网元数".equals(driveAttribute.getDescription())){
				List<NE> nes = managementObj.getNes();
				 
				//获得管理配置下中网元数配置子文件
				String file2 = driveAttribute.getListRootList().get(0).getFile();
				//清除自带的listRoot
				driveAttribute.getListRootList().clear();
				driveAttribute.setValue(nes.size()+"");
				//NE对象集合循环
				for (int l = 0; l < nes.size(); l++) {
					NE ne = nes.get(l);
					try {
						DriveRoot neDriveRoot = super.LoadDriveXml(file2);
						List<DriveAttribute> list3 = neDriveRoot.getDriveAttributeList();
						ListRoot listRoot2 = new ListRoot();
						for (int m = 0; m < list3.size(); m++) {
							DriveAttribute driveAttribute2 = list3.get(m);
							objToNeDriveAttribute(ne,driveAttribute2);
						}
						listRoot2.setDriveAttributeList(neDriveRoot.getDriveAttributeList());
						driveAttribute.getListRootList().add(listRoot2);
					} catch (Exception e) {
						ExceptionManage.dispose(e,this.getClass());
					}
				}
			} 
		}
		
	}

	public void objToWorkSpaceDriveAttribute(DriveAttribute driveAttribute1, WorkSpace workSpace) {
		 if("工作站号".equals(driveAttribute1.getDescription())){
			 driveAttribute1.setValue(workSpace.getWorkSpaceNo()+"");
		 }
		 String[] s2 = workSpace.getWorkSpaceIp().split("\\.");
		 if(s2.length>1){
	 		 if("工作站IP地址1".equals(driveAttribute1.getDescription())){
	 			 driveAttribute1.setValue(s2[0]);
	 		 }
	 		 if("工作站IP地址2".equals(driveAttribute1.getDescription())){
	 			 driveAttribute1.setValue(s2[1]);
	 		 }
	 		 if("工作站IP地址3".equals(driveAttribute1.getDescription())){
	 			 driveAttribute1.setValue(s2[2]);
	 		 }
	 		 if("工作站IP地址4".equals(driveAttribute1.getDescription())){
	 			 driveAttribute1.setValue(s2[3]);
	 		 }
		 }
		 if("工作站的用户数".equals(driveAttribute1.getDescription())){
			 
			 //获得WorkSpaceUser对象集合
			 List<WorkSpaceUser> workSpaceUsers = workSpace.getWorkSpaceUsers();
			 //获得管理配置下工作站数配置的工作站的用户配置子文件
			 String file2 = driveAttribute1.getListRootList().get(0).getFile();
			 //清除自带的listRoot
			 driveAttribute1.getListRootList().clear();
			 driveAttribute1.setValue(workSpaceUsers.size()+"");
			 //workSpaceUser集合对象循环
			 for (int k = 0; k < workSpaceUsers.size(); k++) {
				try {
					DriveRoot driveRootUsers = super.LoadDriveXml(file2);
					List<DriveAttribute> list3 = driveRootUsers.getDriveAttributeList();
					WorkSpaceUser workSpaceUser = workSpaceUsers.get(k); 
					DriveAttribute driveAttribute2 = list3.get(0);
					driveAttribute2.setValue(workSpaceUser.getWorkSpaceUserNo()+"");
					ListRoot userListRoot = new ListRoot();
					userListRoot.setDriveAttributeList(driveRootUsers.getDriveAttributeList());
					driveAttribute1.getListRootList().add(userListRoot); 
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}
			
		 }
	}

	 
	public void objToNeDriveAttribute(NE ne, DriveAttribute driveAttribute2) {
		if("网元开关".equals(driveAttribute2.getDescription())){
			driveAttribute2.setValue(ne.getNESwitch()+"");
		}
			 
		if("网元类型".equals(driveAttribute2.getDescription())){
			driveAttribute2.setValue(ne.getNEType()+"");
		}
		String[] s3 = ne.getNEAddr().split(",");
		if(s3.length>1){
			if("网元NE地址1".equals(driveAttribute2.getDescription())){
				driveAttribute2.setValue(s3[0]);
			}
			if("网元NE地址2".equals(driveAttribute2.getDescription())){
				driveAttribute2.setValue(s3[1]);
			}
		}
		String[] s5 = ne.getNEIp().split("\\.");
		if(s5.length>1){
			if("网元的IP地址1".equals(driveAttribute2.getDescription())){
				driveAttribute2.setValue(s5[0]);
			}
			if("网元的IP地址2".equals(driveAttribute2.getDescription())){
				driveAttribute2.setValue(s5[1]);
			}
			if("网元的IP地址3".equals(driveAttribute2.getDescription())){
				driveAttribute2.setValue(s5[2]);
			}
			if("网元的IP地址4".equals(driveAttribute2.getDescription())){
				driveAttribute2.setValue(s5[3]);
			}
			 
		}
		String[] s6 = ne.getNESubnetScreen().split("\\.");
		if(s6.length>1){
			if("网元的子网屏蔽1".equals(driveAttribute2.getDescription())){
				driveAttribute2.setValue(s6[0]);
			} 
			if("网元的子网屏蔽2".equals(driveAttribute2.getDescription())){
				driveAttribute2.setValue(s6[1]);
			} 
			if("网元的子网屏蔽3".equals(driveAttribute2.getDescription())){
				driveAttribute2.setValue(s6[2]);
			} 
			if("网元的子网屏蔽4".equals(driveAttribute2.getDescription())){
				driveAttribute2.setValue(s6[3]);
			} 
		}
		String[] s7 = ne.getNEEthGateway().split("\\.");
		if(s7.length>1){
			if("网元的以太网网关1".equals(driveAttribute2.getDescription())){
				driveAttribute2.setValue(s7[0]);
			}
			if("网元的以太网网关2".equals(driveAttribute2.getDescription())){
				driveAttribute2.setValue(s7[1]);
			}
			if("网元的以太网网关3".equals(driveAttribute2.getDescription())){
				driveAttribute2.setValue(s7[2]);
			}
			if("网元的以太网网关4".equals(driveAttribute2.getDescription())){
				driveAttribute2.setValue(s7[3]);
			}
		}
		if("网元盘备用类型".equals(driveAttribute2.getDescription())){
			driveAttribute2.setValue(ne.getNECardReserveType()+"");
		}
	}
}

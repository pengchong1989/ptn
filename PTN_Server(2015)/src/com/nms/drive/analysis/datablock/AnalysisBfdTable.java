package com.nms.drive.analysis.datablock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.analysis.xmltool.bean.ListRoot;
import com.nms.drive.service.bean.BfdObject;
import com.nms.drive.service.impl.CoderUtils;


/**
 * 时间同步配置块(1CH)
 * 
 * @author taopan
 * 
 */
public class AnalysisBfdTable extends AnalysisBase {
	String localIp=null;
	String peerIp=null;
	/**
	 * 将SecondMacStudyObject转换为命令
	 * 
	 * @param timeSynchronizeObject
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	public byte[] analysisBfdObjectToCommands(List<BfdObject> bfdObjectList, String configXml) throws Exception {		
		
		try {
			DriveRoot driveRoot_config = super.LoadDriveXml(configXml);
			String file = driveRoot_config.getDriveAttributeList().get(0).getListRootList().get(0).getFile();
			driveRoot_config.getDriveAttributeList().get(0).getListRootList().clear();// 清除本身自带的一个ListRoot
			driveRoot_config.getDriveAttributeList().get(0).setValue(bfdObjectList.size() + "");// 条目数

			for (int i = 0; i < bfdObjectList.size(); i++) {
				DriveRoot driveRoot = super.LoadDriveXml(file);
				ListRoot listRoot = new ListRoot();
				listRoot.setDriveAttributeList(driveRoot.getDriveAttributeList());
				for (int j = 0; j < listRoot.getDriveAttributeList().size(); j++) {
					bfdObjectDriveAttribute(listRoot.getDriveAttributeList().get(j), bfdObjectList.get(i));
				}
				driveRoot_config.getDriveAttributeList().get(0).getListRootList().add(listRoot);
			}
			// 将集合转换成命令
			AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
			byte[] dataCommand = analysisDriveXmlToCommand.analysisCommands(driveRoot_config);
			CoderUtils.print16String(dataCommand);
			return dataCommand;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 给bfdObject的DriveRoot对象赋值
	 * 
	 * @param driveAttribute
	 * @param secondMacStudyObject
	 * @throws Exception 
	 */
	private void bfdObjectDriveAttribute(DriveAttribute driveAttribute, BfdObject bfdObject) throws Exception {
		if ("BFDID".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(bfdObject.getBfdId()+ "");
		} else if ("BFD会话使能".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(bfdObject.getBfdEnabel() + "");
		} else if ("检测方式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(bfdObject.getTestMode() + "");
		} else if ("BFD报文发帧类型".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(bfdObject.getBfdMessageSendType()+ "");			
		}else if ("备用".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(0 + "");
		} else if ("Vlan优先级".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(bfdObject.getVlanPriority() + "");
		} else if ("VlanID".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(bfdObject.getVlanId()+ "");			
		}else if ("服务类型".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(bfdObject.getServiceType() + "");
		} else if ("本地IP地址".equals(driveAttribute.getDescription())) {			
			driveAttribute.setValue(Integer.parseInt(bfdObject.getLocalIp().split("\\.")[0]) + "");
		}else if ("本地IP地址1".equals(driveAttribute.getDescription())) {			
			driveAttribute.setValue(Integer.parseInt(bfdObject.getLocalIp().split("\\.")[1]) + "");
		}else if ("本地IP地址2".equals(driveAttribute.getDescription())) {			
			driveAttribute.setValue(Integer.parseInt(bfdObject.getLocalIp().split("\\.")[2]) + "");
		} else if ("本地IP地址3".equals(driveAttribute.getDescription())) {			
			driveAttribute.setValue(Integer.parseInt(bfdObject.getLocalIp().split("\\.")[3]) + "");
		}else if ("远端IP地址".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(Integer.parseInt(bfdObject.getPeerIp().split("\\.")[0]) + "");			
		}else if ("远端IP地址1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(Integer.parseInt(bfdObject.getPeerIp().split("\\.")[1]) + "");			
		}else if ("远端IP地址2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(Integer.parseInt(bfdObject.getPeerIp().split("\\.")[2]) + "");			
		}else if ("远端IP地址3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(Integer.parseInt(bfdObject.getPeerIp().split("\\.")[3]) + "");			
		}else if ("UDP端口".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(bfdObject.getUdpPort() + "");
		} else if ("本端会话标识符".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(bfdObject.getMySid() + "");
		} else if ("对端会话标识符自学习使能".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(bfdObject.getPeerStudyEnabel()+ "");			
		}else if ("对端会话标识符".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(bfdObject.getPeerSid() + "");
		} else if ("发送最小周期".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(bfdObject.getDmti() + "");
		} else if ("接收最小周期".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(bfdObject.getRmri()+ "");			
		}else if ("检测倍数".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(bfdObject.getDectMul() + "");
		} else if ("备用1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(0 + "");
		} else if ("PW时BFD封装方式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(bfdObject.getPwBfdStyle()+ "");			
		}else if ("PWTTL".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(bfdObject.getPwTtl() + "");
		} else if ("二层端口BFD标识".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(bfdObject.gettLayelPortMark() + "");
		} else if ("PW时BFD封装方式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(bfdObject.getPwBfdStyle()+ "");			
		}else if ("LSPID".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(bfdObject.getLspId() + "");
		} else if ("PWID".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(bfdObject.getPwId() + "");
		} else if ("备用2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(0+ "");			
		}              	 	   	  
	}

	

	public List<BfdObject> analysisCommandsToBfdObject(byte[] commands,String configXml) throws Exception {
		CoderUtils.print16String(commands);
		List<BfdObject> bfdObjList = new ArrayList<BfdObject>();
		// 条目数
		byte[] Count = new byte[4];
		Count[0] = 0;
		Count[1] = 0;
		Count[2] = commands[0];
		Count[3] = commands[1];
		int count = CoderUtils.bytesToInt(Count);		
		int pointer = 2;
		byte[] bfdByte = new byte[100];
		BfdObject bfdObject = null;
		for (int j = 0; j < count; j++) {
			System.arraycopy(commands, pointer, bfdByte, 0, bfdByte.length );				
			// 生成ptpPortObject对象
			bfdObject = getBfdObject(bfdByte, configXml);
			pointer += 100;// 移动下标	
			bfdObjList.add(bfdObject);
		}
		return  bfdObjList;
	}
	
	
	/**
	 * 给bfdObject的DriveRoot对象赋值
	 * 
	 * @param driveAttribute
	 * @param secondMacStudyObject
	 * @return 
	 * @throws Exception 
	 */
	private BfdObject getBfdObject(byte[] commands, String configXml) throws Exception {
		
		BfdObject bfdObject = new BfdObject();
		AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
		analysisCommandByDriveXml.setCommands(commands);
		try {
			DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
			DriveAttribute driveAttribute = null;
			for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
				driveAttribute = driveRoot.getDriveAttributeList().get(i);
				assignBfdObject(bfdObject, driveAttribute);// 给ptpPortObj赋值
			}
		} catch (Exception e) {
			throw e;
		}

		return bfdObject;
	}	
	
	/**
	 * 给BFD对象赋值
	 * 
	 * @param timeSynchronizeObject
	 * @param driveAttribute
	 */
	private void assignBfdObject(BfdObject bfdObject, DriveAttribute driveAttribute) {
		
		if ("BFDID".equals(driveAttribute.getDescription())) {
			bfdObject.setBfdId(Integer.parseInt(driveAttribute.getValue()));
		} else if ("BFD会话使能".equals(driveAttribute.getDescription())) {
			bfdObject.setBfdEnabel(Integer.parseInt(driveAttribute.getValue()));
		} else if ("检测方式".equals(driveAttribute.getDescription())) {
			bfdObject.setTestMode(Integer.parseInt(driveAttribute.getValue()));
		} else if ("BFD报文发帧类型".equals(driveAttribute.getDescription())) {
			bfdObject.setBfdMessageSendType(Integer.parseInt(driveAttribute.getValue()));				
		} else if ("Vlan优先级".equals(driveAttribute.getDescription())) {
			bfdObject.setVlanPriority(Integer.parseInt(driveAttribute.getValue()));
		} else if ("VlanID".equals(driveAttribute.getDescription())) {
			bfdObject.setVlanId(Integer.parseInt(driveAttribute.getValue()));		
		} else if ("服务类型".equals(driveAttribute.getDescription())) {
			bfdObject.setServiceType(Integer.parseInt(driveAttribute.getValue()));
		} else if ("本地IP地址".equals(driveAttribute.getDescription())) {
			localIp=driveAttribute.getValue();
		} else if ("本地IP地址1".equals(driveAttribute.getDescription())) {
			localIp+="."+driveAttribute.getValue();
		} else if ("本地IP地址2".equals(driveAttribute.getDescription())) {
			localIp+="."+driveAttribute.getValue();
		} else if ("本地IP地址3".equals(driveAttribute.getDescription())) {
			localIp+="."+driveAttribute.getValue();
			bfdObject.setLocalIp(localIp);
		}else if ("远端IP地址".equals(driveAttribute.getDescription())) {
			peerIp=driveAttribute.getValue();			
		} else if ("远端IP地址1".equals(driveAttribute.getDescription())) {
			peerIp+="."+driveAttribute.getValue();			
		}else if ("远端IP地址2".equals(driveAttribute.getDescription())) {
			peerIp+="."+driveAttribute.getValue();			
		}else if ("远端IP地址3".equals(driveAttribute.getDescription())) {
			peerIp+="."+driveAttribute.getValue();
			bfdObject.setPeerIp(peerIp);			
		}else if ("UDP端口".equals(driveAttribute.getDescription())) {
			bfdObject.setUdpPort(Integer.parseInt(driveAttribute.getValue()));		
		} else if ("本端会话标识符".equals(driveAttribute.getDescription())) {
			bfdObject.setMySid(Integer.parseInt(driveAttribute.getValue()));		
		} else if ("对端会话标识符自学习使能".equals(driveAttribute.getDescription())) {
			bfdObject.setPeerStudyEnabel(Integer.parseInt(driveAttribute.getValue()));			
		}else if ("对端会话标识符".equals(driveAttribute.getDescription())) {
			bfdObject.setPeerSid(Integer.parseInt(driveAttribute.getValue()));				
		} else if ("发送最小周期".equals(driveAttribute.getDescription())) {
			bfdObject.setDmti(Integer.parseInt(driveAttribute.getValue()));	
		} else if ("接收最小周期".equals(driveAttribute.getDescription())) {
			bfdObject.setRmri(Integer.parseInt(driveAttribute.getValue()));			
		}else if ("检测倍数".equals(driveAttribute.getDescription())) {
			bfdObject.setDectMul(Integer.parseInt(driveAttribute.getValue()));		
		} else if ("PW时BFD封装方式".equals(driveAttribute.getDescription())) {
			bfdObject.setPwBfdStyle(Integer.parseInt(driveAttribute.getValue()));				
		}else if ("PWTTL".equals(driveAttribute.getDescription())) {
			bfdObject.setPwTtl(Integer.parseInt(driveAttribute.getValue()));	
		} else if ("二层端口BFD标识".equals(driveAttribute.getDescription())) {
			bfdObject.settLayelPortMark(Integer.parseInt(driveAttribute.getValue()));	
		} else if ("PW时BFD封装方式".equals(driveAttribute.getDescription())) {
			bfdObject.setPwBfdStyle(Integer.parseInt(driveAttribute.getValue()));			
		}else if ("LSPID".equals(driveAttribute.getDescription())) {
			bfdObject.setLspId(Integer.parseInt(driveAttribute.getValue()));	
		} else if ("PWID".equals(driveAttribute.getDescription())) {
			bfdObject.setPwId(Integer.parseInt(driveAttribute.getValue()));	
		}             	 	   	  
	}
		
}

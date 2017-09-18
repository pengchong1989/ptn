package com.nms.drive.analysis.datablock;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.ResponseObject;
import com.nms.ui.manager.ExceptionManage;

 

/**
 * 管理块解析
 * @author 郭清川
 */
public class AnalysisResponseTable extends AnalysisBase {
	private int dataCount = 18;//每个数据块的长度
	private int NEhead = 5;//NE头长度
	private int controlPanelHead = 101;//盘头长度
	private int dataBlockHead = 25;//配置块头信息长度
	
	/**
	 * 根据字节数组生成对象
	 * @param commands命令
	 * @param configXml配置文件
	 * @return ResponseObject
	 * @throws Exception
	 */
	public ResponseObject analysisCommandToObject(byte[] commands,String configXml) throws Exception{
		ResponseObject responseObj = new ResponseObject();
		DriveRoot driveRoot = super.LoadDriveXml(configXml);
		//起始长度
		int start = NEhead + controlPanelHead + dataBlockHead;
		byte[] a = new byte[dataCount];
		System.arraycopy(commands, start, a, 0, a.length);
		AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
		analysisCommandByDriveXml.setCommands(a);
		driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
		try {
			//将driveRoot信息赋值到responseObj对象中
			for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
				DriveAttribute driveAttribute = driveRoot.getDriveAttributeList().get(i);
				//赋值
				DriveAttributeToResponseObj(driveAttribute,responseObj);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return responseObj;
	}

	public void DriveAttributeToResponseObj(DriveAttribute driveAttribute, ResponseObject responseObj) {
		if("方向".equals(driveAttribute.getDescription())){
			responseObj.setDirection(Integer.parseInt(driveAttribute.getValue()));
		}
		else if("对象范围".equals(driveAttribute.getDescription())){
			responseObj.setObjScope(Integer.parseInt(driveAttribute.getValue()));	
		}
		else if("对象标识号".equals(driveAttribute.getDescription())){
			responseObj.setObjTagNo(driveAttribute.getValue());
		}
		else if("流水号".equals(driveAttribute.getDescription())){
			responseObj.setStreamNo(Integer.parseInt(driveAttribute.getValue()));
		}
		else if("状态".equals(driveAttribute.getDescription())){
			responseObj.setState(driveAttribute.getValue());
		}
		else if("长度".equals(driveAttribute.getDescription())){
			responseObj.setLength(driveAttribute.getValue());
		}
		else if("配置完成状态".equals(driveAttribute.getDescription())){
			responseObj.setConfigState(Integer.parseInt(driveAttribute.getValue()));
		}
	}
	
}

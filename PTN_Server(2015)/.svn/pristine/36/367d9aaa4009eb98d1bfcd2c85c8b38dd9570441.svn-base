package com.nms.drive.analysis.datablock.status;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.AnalysisObjectService;
import com.nms.drive.analysis.datablock.AnalysisBase;
import com.nms.drive.service.bean.MacStudyObject;
import com.nms.drive.service.bean.status.InsertLinkOamObject;
import com.nms.drive.service.bean.status.OamLinkStatusInfoObject;
import com.nms.drive.service.bean.status.OamMepInfoObject;
import com.nms.drive.service.bean.status.OamPingFrameObject;
import com.nms.drive.service.bean.status.OamStatusObject;
import com.nms.drive.service.bean.status.OamTraceHopsObject;
import com.nms.drive.service.impl.CoderUtils;
import com.nms.service.bean.ActionObject;

/**
 * 状态监视
 */
public class AnalysisAllOamStatus extends AnalysisBase{
	private int contorl = 5;//网元信息，包括网元地址，盘地址
	private int marking = 1;//某种状态的标示信息
	
	public ActionObject analysisCommandToObject(byte[] commands) throws Exception{
		ActionObject actionObject = new ActionObject();
		OamStatusObject oamStatusObject = new OamStatusObject();
			int markStatus = commands[5];
			byte[] bytes = null;
			bytes = new byte[commands.length-6];
			if(markStatus == 49){// 以太网OAM Trace结果查询 
				List<OamTraceHopsObject> oamTraceHopsList = null;
				if(commands.length > 6){
				System.arraycopy(commands,6 ,bytes, 0, bytes.length);
				AnalysisObjectService analysisObjectService = new AnalysisObjectService();
				oamTraceHopsList = analysisObjectService.AnalysisCommandToOamTraceHops(bytes);
				}else{
					oamTraceHopsList = new ArrayList<OamTraceHopsObject>();
				}
				oamStatusObject.setOamTraceHopsObjectList(oamTraceHopsList);
			}else if(markStatus == 62){//以太网OAM  Ping结果查询
				List<OamPingFrameObject> oamPingFrameList = null;
				if(commands.length > 6){
					System.arraycopy(commands,6 ,bytes, 0, bytes.length);
					AnalysisObjectService analysisObjectService = new AnalysisObjectService();
					oamPingFrameList = analysisObjectService.AnalysisCommandToOamPingFrame(bytes);
				}else{
					oamPingFrameList = new ArrayList<OamPingFrameObject>();
				}
				oamStatusObject.setOamPingFrameObjectList(oamPingFrameList);
			}else if(markStatus == 55){
				InsertLinkOamObject insertLinkOamInfo = null;
				if(commands.length > 6){
					System.arraycopy(commands,6 ,bytes, 0, bytes.length);
					AnalysisObjectService analysisObjectService = new AnalysisObjectService();
					insertLinkOamInfo = analysisObjectService.AnalysisCommandToInsertLinkOam(commands);
				}else{
					insertLinkOamInfo = new InsertLinkOamObject();
				}
				oamStatusObject.setInsertLinkOamObject(insertLinkOamInfo);
			}else if(markStatus == 56){
				OamLinkStatusInfoObject oamLinkStatusInfoObject =null;
				if(commands.length > 6){
					System.arraycopy(commands,6 ,bytes, 0, bytes.length);
					AnalysisObjectService analysisObjectService = new AnalysisObjectService();
					oamLinkStatusInfoObject = analysisObjectService.AnalysisCommandToOamLinkStatusInfo(commands);
				}else{
					oamLinkStatusInfoObject = new OamLinkStatusInfoObject();
				}
				oamStatusObject.setOamLinkStatusInfoObject(oamLinkStatusInfoObject);
			}else if(markStatus == 57){
				//大于6说明设备返回有值
				OamMepInfoObject oamMepInfoObject = null;
				if(commands.length > 6){
					System.arraycopy(commands, 6, bytes, 0, bytes.length);
					AnalysisObjectService analysisObjectService = new AnalysisObjectService();
					oamMepInfoObject = analysisObjectService.AnalysisCommandToOamMepInfoObject(commands);
				}else{
					oamMepInfoObject = new OamMepInfoObject();
				}
				oamStatusObject.setOamMepInfoObject(oamMepInfoObject);
			}
			//端口mac学习//vlan 学习mac
			else if(markStatus == 64 || markStatus == 65){
				//大于6说明设备返回有值
				MacStudyObject macStudyObject  = new MacStudyObject();
				if(commands.length > 6){
					CoderUtils.print16String(commands);
					System.arraycopy(commands, 6, bytes, 0, bytes.length);
					macStudyObject = new MacStudyObject();
//					macStudyObject.setVlanId(CoderUtils.bytesToInteger(bytes));
					macStudyObject.setVlanId(CoderUtils.bytesToInt(bytes));
				}
				oamStatusObject.setManStydyObject(macStudyObject);
			}
		actionObject.setOamStatusObject(oamStatusObject);
		return actionObject;
		
	}
}

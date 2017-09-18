package com.nms.corba.ninterface.impl.resource.proxy;

import globaldefs.ExceptionType_T;
import globaldefs.NVSList_THolder;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.nms.corba.ninterface.enums.EOAMType;
import com.nms.corba.ninterface.impl.util.CheckParameterUtil;
import com.nms.corba.ninterface.util.CorbaConvertBase;
import com.nms.db.bean.ptn.oam.OamEthernetInfo;
import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.bean.ptn.oam.OamMepInfo;
import com.nms.db.enums.OamTypeEnum;
import com.nms.model.ptn.oam.OamEthreNetService_MB;
import com.nms.model.ptn.oam.OamInfoService_MB;
import com.nms.model.util.Services;
import com.nms.service.impl.dispatch.EthOAMConfigDispath;
import com.nms.service.impl.dispatch.TMCOAMConfigDispath;
import com.nms.service.impl.dispatch.TMPOAMConfigDispath;
import com.nms.service.impl.dispatch.TMSOAMConfigDispath;
import com.nms.service.impl.dispatch.TmsOamDispatch;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;
/**
 * OAM 调度类
 * @author Administrator
 *
 */
public class TestOamProxy {
	private static Logger LOG = Logger.getLogger(TestOamProxy.class.getName());

	/**
	 * @author guoqc
	 * 启动LB功能(新建按需OAM)
	 * 入参  megName 厂商信息/主键id
	 * 入参  enableLB  无论是否使能LB，需填写additionalInfo
	 * 入参  additionalInfo 环回帧周期，离线TLV类型，环回测试方式，环回TLV长度，TLV测试内容
	 * 出参  lbResult 业务类型(段层/tunnel层/pw层)
	 * @throws ProcessingFailureException 
	 */
	public void setMEGLB(NameAndStringValue_T[] megName, int enableLB,
							NameAndStringValue_T[] additionalInfo, NVSList_THolder lbResult) throws ProcessingFailureException {
		CorbaConvertBase corbaConvertBase;
		try {
			if(!CheckParameterUtil.checkMEGName(megName))
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"输入参数无效");
			corbaConvertBase = new CorbaConvertBase();
			int oamId = corbaConvertBase.getPrimaryKey(megName);
			
			String type = getType(megName);
			
			OamInfo oamInfo = this.getOamInfo(oamId, type);
			if(null == oamInfo.getOamMep() || 0 == oamInfo.getOamMep().getId())
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_ENTITY_NOT_FOUND,"实体没有找到");
			//从参数additionalInfo取相应的值赋给按需OAM
			this.setValue2OAM(additionalInfo, oamInfo, enableLB,type);
			//下发数据
			String result = this.sendData(oamInfo, type);
			lbResult.value = new NameAndStringValue_T[1];
			lbResult.value[0] = new NameAndStringValue_T("ErrorMessage",corbaConvertBase.outErrorMessage(result));
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"setMEGLB ex.");
		}
	}

	/**
	 * 获取MEG类型
	 * @param megName	MEG标识符
	 * @return
	 */
	private String getType(NameAndStringValue_T[] megName) {
		String type = null;
		int oamType = Integer.parseInt(megName[2].value.substring(0, 1));
		if(oamType == 1){
			type = EOAMType.SECTION.toString();
	     }else if(oamType == 2){
	    	 type = EOAMType.TUNNEL.toString();
	     }else if(oamType == 3){
	    	 type = EOAMType.PW.toString();
	     }else if(oamType == 4){
	    	 type = EOAMType.ETHERNET.toString();
	     }else if(oamType == 5){
	    	 type = EOAMType.ETHLINK.toString();
	     }else if(oamType == 6){
	    	 type = EOAMType.SECTION_TEST.toString();
	     }else if(oamType == 7){
	    	 type = EOAMType.TUNNEL_TEST.toString();
	     }else if(oamType == 8){
	    	 type = EOAMType.PW_TEST.toString();
	     }
		return type;
	}


	/**
	 * 获取oamInfo对象
	 * @param Id
	 * @param type 
	 * @return oamInfo
	 * @throws ProcessingFailureException 
	 */
	private OamInfo getOamInfo(int oamId, String type) throws ProcessingFailureException {
		OamInfo oamInfo = new OamInfo();
		OamEthernetInfo oamEthernet;
		OamInfoService_MB service = null;
		OamEthreNetService_MB oamInfoService = null;
		try {
			if(EOAMType.ETHERNET.toString().equals(type)){
				List<OamEthernetInfo> oamEthernetList = new ArrayList<OamEthernetInfo>();
				oamEthernet = new OamEthernetInfo();
				oamEthernet.setId(oamId);
				oamInfoService = (OamEthreNetService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OAMETHERNET);
				oamEthernetList = oamInfoService.queryByNeIDSide(oamEthernet);
				if(oamEthernetList.size() != 1)
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_ENTITY_NOT_FOUND,"实体没有找到");
				oamInfo.setOamEthernetInfo(oamEthernetList.get(0));
			}else{
				oamInfo.setOamType(OamTypeEnum.AMEP);
				OamMepInfo mepInfo = new OamMepInfo();
				mepInfo.setId(oamId);
				mepInfo.setObjType(type);
				oamInfo.setOamMep(mepInfo);
			    service = (OamInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OamInfo);
				oamInfo = service.queryByCondition(oamInfo, OamTypeEnum.AMEP);
				oamInfo.setOamType(OamTypeEnum.AMEP);
				if(null == oamInfo.getOamMep() || 0 == oamInfo.getOamMep().getId())
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_ENTITY_NOT_FOUND,"实体没有找到");
			}
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"getOamInfo ex.");
		} finally {
			UiUtil.closeService_MB(service);
			UiUtil.closeService_MB(oamInfoService);
		}
		return oamInfo;
	}
	

	
	/**
	 * 从参数additionalInfo取相应的值赋给按需OAM
	 * @param additionalInfo
	 * @param oamMepInfo 
	 * @param type 
	 */
	private void setValue2OAM(NameAndStringValue_T[] additionalInfo, OamInfo oam, int enableLB,String type) {
		OamMepInfo mep = oam.getOamMep();
		if(enableLB == 0){
			oam.getOamMep().setRingEnable(false);
		}else{
			oam.getOamMep().setRingEnable(true);
		}
		for (int i = 0; i < additionalInfo.length; i++) {
			if("RingCycle".equals(additionalInfo[i].name))
				mep.setRingCycle(this.getRingCycle(additionalInfo[i].value));//环回周期 
			else if("OffLineTestTLV".equals(additionalInfo[i].name))
				mep.setOffLineTestTLV(this.getOffLineTestTLV(additionalInfo[1].value));//离线TLV类型
			else if("RingTestWay".equals(additionalInfo[i].name))
				mep.setRingTestWay((additionalInfo[2].value).equals("onLine") ? 0 : 1);//环回方式
			else if("RingTLVLength".equals(additionalInfo[i].name))
				mep.setRingTLVLength(Integer.parseInt(additionalInfo[3].value));//环回TLV长度
			else if("RingTLVInfo".equals(additionalInfo[i].name))
				mep.setRingTLVInfo(Integer.parseInt(additionalInfo[4].value));//环回TLV测试内容
		}
	}

	private int getOffLineTestTLV(String value) {
		if("allZero".equals(value)){
			return 0;
		}else if("pseudoRandomCode".equals(value)){
			return 1;
		}
		return 0;
	}

	private int getRingCycle(String value) {
		int i = 0;
		if("3.33ms".equals(value)){
			i = 1;
		}else if("10ms".equals(value)){
			i = 10;
		}else if("100ms".equals(value)){
			i = 11;
		}else if("1s".equals(value)){
			i = 100;
		}
		return i;
	}

	/**
	 * 根据类型下发数据到设备
	 * @param OAM 需要下发的数据
	 * @param type OAM类型
	 * @throws ProcessingFailureException 
	 */
	private String sendData(OamInfo oam, String type) throws ProcessingFailureException {
		try {
			if(EOAMType.SECTION.toString().equals(type) ||
			   EOAMType.TUNNEL.toString().equals(type) ||
			   EOAMType.PW.toString().equals(type)){
				TmsOamDispatch tmsOamDispatch = new TmsOamDispatch();
				return tmsOamDispatch.excuteUpdate(oam);
			}else if(EOAMType.SECTION_TEST.toString().equals(type)){
				TMSOAMConfigDispath tmsDispatch = new TMSOAMConfigDispath();
				return tmsDispatch.excuteUpdate(oam);
			}else if(EOAMType.TUNNEL_TEST.toString().equals(type)){
				TMPOAMConfigDispath tmpDispatch = new TMPOAMConfigDispath();
				return tmpDispatch.excuteUpdate(oam);
			}else if(EOAMType.PW_TEST.toString().equals(type)){
				TMCOAMConfigDispath tmcDispatch = new TMCOAMConfigDispath();
				return tmcDispatch.excuteUpdate(oam);
			}else if(EOAMType.ETHERNET.toString().equals(type)){
				EthOAMConfigDispath ethDispatch = new EthOAMConfigDispath();
				List<OamInfo> oamList = new ArrayList<OamInfo>();
				oamList.add(oam);
				return ethDispatch.excuteInsert(oamList);
			}
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"sendData ex.");
		}
	}

	/**
	 * @author guoqc
	 * 启动OAM性能测量功能(按需OAM)
	 * 入参  megName  厂商信息/主键id
	 * 入参  collectParam LM发送使能，如使能，LM发送周期1s，100ms,DM发送使能，如使能，DM发送周期1s，100ms
	 * 出参  collectResult 业务类型
	 * @throws ProcessingFailureException 
	 */
	public void setMEGPerfCollection(NameAndStringValue_T[] megName, NameAndStringValue_T[] collectParam,
										NVSList_THolder collectResult) throws ProcessingFailureException {
		CorbaConvertBase corbaConvertBase;
		try {
			if(!CheckParameterUtil.checkMEGName(megName))
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"输入参数无效");
			corbaConvertBase = new CorbaConvertBase();
			int oamId = corbaConvertBase.getPrimaryKey(megName);
			//判断是否是德信厂商
			int oamType = Integer.parseInt(megName[2].value.substring(0, 1));
			String type = getType(megName);
			
			OamInfo oamInfo = this.getOamInfo(oamId, type);
			
			//从参数collectParam取相应的值赋给按需OAM
			this.setPerform2OAM(collectParam, oamInfo,type);
			//下发数据
			String result = this.sendData(oamInfo, type);
			//判断是否成功
			if(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS).equals(result)){
				//给返回值赋值
				if(oamType == EOAMType.ETHERNET.getValue()){
					collectResult.value = new NameAndStringValue_T[2];
					collectResult.value[0] = new NameAndStringValue_T("DM",oamInfo.getOamEthernetInfo().getDmENable()+"");
					collectResult.value[1] = new NameAndStringValue_T("LM",oamInfo.getOamEthernetInfo().getLmENable()+"");
				}else{
					collectResult.value = new NameAndStringValue_T[2];
					collectResult.value[0] = new NameAndStringValue_T("DM",oamInfo.getOamMep().isDm() == true ? "1":"0");
					collectResult.value[1] = new NameAndStringValue_T("LM",oamInfo.getOamMep().isLm() == true ? "1":"0");
				}
			}
		}catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"setMEGPerfCollection ex.");
		}
	}
	
	/**
	 * 从参数additionalInfo取相应的值赋给按需OAM
	 * @param additionalInfo
	 * @param oamMepInfo 
	 * @param type 
	 */
	private void setPerform2OAM(NameAndStringValue_T[] additionalInfo, OamInfo oam,String type) {
		if("ETHERNET".equals(type)){
			for (int i = 0; i < additionalInfo.length; i++) {
				if("Lm".equals(additionalInfo[i].name))
					oam.getOamEthernetInfo().setLmENable(Integer.parseInt(additionalInfo[i].value));//丢包率 LM帧发送使能 0/1=不使能/使能
				else if("LMCYCLE".equals(additionalInfo[i].name))
					oam.getOamEthernetInfo().setLmSendCycle((additionalInfo[i].value).equals("1s") ? 1 : 0);//LM帧发送周期
				else if("Dm".equals(additionalInfo[i].name))
					oam.getOamEthernetInfo().setDmENable(Integer.parseInt(additionalInfo[i].value));//延时帧发送使能 0/1=不使能/使能
				else if("DMCYCLE".equals(additionalInfo[i].name))
					oam.getOamEthernetInfo().setDmSendCycle((additionalInfo[i].value).equals("1s") ? 1 : 0);//延时帧发送周期
			}
		}else{
			for (int i = 0; i < additionalInfo.length; i++) {
				if("Lm".equals(additionalInfo[i].name))
					oam.getOamMep().setLm(Integer.parseInt(additionalInfo[i].value) == 0 ? false:true);//丢包率 LM帧发送使能 0/1=不使能/使能
				else if("LMCYCLE".equals(additionalInfo[i].name))
					oam.getOamMep().setLmCycle((additionalInfo[i].value).equals("1s") ? 1 : 0);//LM帧发送周期
				else if("Dm".equals(additionalInfo[i].name))
					oam.getOamMep().setDm(Integer.parseInt(additionalInfo[i].value) == 0 ? false:true);//延时帧发送使能 0/1=不使能/使能
				else if("DMCYCLE".equals(additionalInfo[i].name))
					oam.getOamMep().setDmCycle((additionalInfo[i].value).equals("1s") ? 1 : 0);//延时帧发送周期
			}
		}
	}
}

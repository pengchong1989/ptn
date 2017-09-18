package com.nms.service.impl.wh;

import java.math.BigInteger;
import java.util.List;

import com.nms.db.bean.equipment.port.V35PortInfo;
import com.nms.drive.service.bean.NEObject;
import com.nms.drive.service.bean.V35PortObject;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.ActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.WHOperationBase;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SynchroUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.manager.ExceptionManage;

public class V35PortWHServiceImpl extends WHOperationBase implements OperationServiceI{
	
	@Override
	public String excutionDelete(List objectList) throws Exception {
		return null;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		V35PortInfo v35PortInfo = null;
		OperationObject operationObject = null;
		OperationObject operationObjectResult=null;
		try {
			v35PortInfo = (V35PortInfo)object;
			operationObject = this.getOperationObject(v35PortInfo);
			super.sendAction(operationObject);
			operationObjectResult = super.verification(operationObject);
			if (operationObjectResult.isSuccess()) {
				return operationObjectResult.getActionObjectList().get(0).getStatus();
			} else {
				return super.getErrorMessage(operationObjectResult);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return null;
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {
		V35PortInfo v35PortInfo = null;
		OperationObject operationObject = null;
		OperationObject operationObjectResult=null;
		try {
			v35PortInfo = (V35PortInfo)object;
			operationObject = this.getOperationObject(v35PortInfo);
			super.sendAction(operationObject);
			operationObjectResult = super.verification(operationObject);
			if (operationObjectResult.isSuccess()) {
				return operationObjectResult.getActionObjectList().get(0).getStatus();
			} else {
				return super.getErrorMessage(operationObjectResult);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return null;
	}

	
	private OperationObject getOperationObject(V35PortInfo v35PortInfo) throws Exception {
		OperationObject operationObject = null;
		ActionObject actionObject = null;
		NEObject neObject = null;

		try {
			operationObject = new OperationObject();
			WhImplUtil whImplUtil = new WhImplUtil();
			neObject = whImplUtil.siteIdToNeObject(v35PortInfo.getSiteId());
			actionObject = new ActionObject();
			actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
			actionObject.setNeObject(neObject);
			actionObject.setType("v35port");
			actionObject.setV35PortObject(this.getV35PortObject(v35PortInfo));
			operationObject.getActionObjectList().add(actionObject);
				
		} catch (Exception e) {
			throw e;
		} finally {
			actionObject = null;
			neObject = null;
		}
		return operationObject;
	}
	
	private V35PortObject getV35PortObject(V35PortInfo v35PortInfo) {
		V35PortObject v35PortObject = new V35PortObject();
		String byte4 = ""+v35PortInfo.getTime7()+v35PortInfo.getTime6()+v35PortInfo.getTime5()+v35PortInfo.getTime4()+v35PortInfo.getTime3()+v35PortInfo.getTime2()+v35PortInfo.getTime1()+"1";
		String byte3 = ""+v35PortInfo.getTime15()+v35PortInfo.getTime14()+v35PortInfo.getTime13()+v35PortInfo.getTime12()+v35PortInfo.getTime11()+v35PortInfo.getTime10()+v35PortInfo.getTime9()+v35PortInfo.getTime8();
		String byte2 = ""+v35PortInfo.getTime23()+v35PortInfo.getTime22()+v35PortInfo.getTime21()+v35PortInfo.getTime20()+v35PortInfo.getTime19()+v35PortInfo.getTime18()+v35PortInfo.getTime17()+v35PortInfo.getTime16();
		String byte1 = ""+v35PortInfo.getTime31()+v35PortInfo.getTime30()+v35PortInfo.getTime29()+v35PortInfo.getTime28()+v35PortInfo.getTime27()+v35PortInfo.getTime26()+v35PortInfo.getTime25()+v35PortInfo.getTime24();
		v35PortObject.setByte1(Integer.parseInt(new BigInteger(byte1,2).toString()));
		v35PortObject.setByte2(Integer.parseInt(new BigInteger(byte2,2).toString()));
		v35PortObject.setByte3(Integer.parseInt(new BigInteger(byte3,2).toString()));
		v35PortObject.setByte4(Integer.parseInt(new BigInteger(byte4,2).toString()));
		v35PortObject.setFourthLeg(v35PortInfo.getFourthLeg());
		v35PortObject.setTimeModel(v35PortInfo.getTimeModel());
		v35PortObject.setFrame(v35PortInfo.getFrame());
		return v35PortObject;
	}

	@Override
	public Object synchro(int siteId) {
		OperationObject operaObj = new OperationObject();
		try {
			operaObj = this.getSynchroOperationObject(siteId);// 封装下发数据
			super.sendAction(operaObj);// 下发数据
			super.verification(operaObj);// 验证是否下发成功
			if (operaObj.isSuccess()) {
				/* 成功，则与数据库进行同步 */
				for (ActionObject actionObject : operaObj.getActionObjectList()) {
					this.synchro_db(actionObject.getV35PortObject(), siteId);
				}
				return ResultString.CONFIG_SUCCESS;
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			operaObj = null;
		}
		return "超时";
	}
	
	/**
	 * 封装下发数据
	 * 
	 * @param siteId
	 * @return operationObject
	 * @throws Exception
	 */
	private OperationObject getSynchroOperationObject(int siteId) throws Exception {
		OperationObject operationObject = null;
		ActionObject actionObject = null;
		NEObject neObject = null;
		try {
			operationObject = new OperationObject();
			WhImplUtil whImplUtil = new WhImplUtil();
			neObject = whImplUtil.siteIdToNeObject(siteId);
			actionObject = new ActionObject();
			actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
			actionObject.setNeObject(neObject);
			actionObject.setType("tmsOamSynchro");
			operationObject.getActionObjectList().add(actionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			actionObject = null;
		}
		return operationObject;
	}
	
	/**
	 * 转换后的V35信息与数据库进行对比
	 * @param tmsoamObjects
	 * @param siteId
	 */
	private void synchro_db(V35PortObject v35PortObject,int siteId){
		V35PortInfo v35PortInfo = this.getV35PortInfo(v35PortObject,siteId);
		try {
			SynchroUtil synchroUtil = new SynchroUtil();
			synchroUtil.v35PortSynchro(v35PortInfo);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
	}
	
	private V35PortInfo getV35PortInfo(V35PortObject v35PortObject,int siteId){
		V35PortInfo v35PortInfo = new V35PortInfo();
		v35PortInfo.setSiteId(siteId);
		v35PortInfo.setFourthLeg(v35PortObject.getFourthLeg());
		v35PortInfo.setTimeModel(v35PortObject.getTimeModel());
		v35PortInfo.setFrame(v35PortObject.getFrame());
		String byte1 = Integer.toBinaryString(v35PortObject.getByte1());
		String byte2 = Integer.toBinaryString(v35PortObject.getByte2());
		String byte3 = Integer.toBinaryString(v35PortObject.getByte3());
		String byte4 = Integer.toBinaryString(v35PortObject.getByte4());
		v35PortInfo.setTime1(Integer.parseInt(byte4.substring(6, 7)));
		v35PortInfo.setTime2(Integer.parseInt(byte4.substring(5, 6)));
		v35PortInfo.setTime3(Integer.parseInt(byte4.substring(4, 5)));
		v35PortInfo.setTime4(Integer.parseInt(byte4.substring(3, 4)));
		v35PortInfo.setTime5(Integer.parseInt(byte4.substring(2, 3)));
		v35PortInfo.setTime6(Integer.parseInt(byte4.substring(1, 2)));
		v35PortInfo.setTime7(Integer.parseInt(byte4.substring(0, 1)));
		
		v35PortInfo.setTime8(Integer.parseInt(byte3.substring(7, 6)));
		v35PortInfo.setTime9(Integer.parseInt(byte3.substring(6, 7)));
		v35PortInfo.setTime10(Integer.parseInt(byte3.substring(5, 6)));
		v35PortInfo.setTime11(Integer.parseInt(byte3.substring(4, 5)));
		v35PortInfo.setTime12(Integer.parseInt(byte3.substring(3, 4)));
		v35PortInfo.setTime13(Integer.parseInt(byte3.substring(2, 3)));
		v35PortInfo.setTime14(Integer.parseInt(byte3.substring(1, 2)));
		v35PortInfo.setTime15(Integer.parseInt(byte3.substring(0, 1)));
		
		v35PortInfo.setTime16(Integer.parseInt(byte2.substring(7, 6)));
		v35PortInfo.setTime17(Integer.parseInt(byte2.substring(6, 7)));
		v35PortInfo.setTime18(Integer.parseInt(byte2.substring(5, 6)));
		v35PortInfo.setTime19(Integer.parseInt(byte2.substring(4, 5)));
		v35PortInfo.setTime20(Integer.parseInt(byte2.substring(3, 4)));
		v35PortInfo.setTime21(Integer.parseInt(byte2.substring(2, 3)));
		v35PortInfo.setTime22(Integer.parseInt(byte2.substring(1, 2)));
		v35PortInfo.setTime23(Integer.parseInt(byte2.substring(0, 1)));
		
		v35PortInfo.setTime24(Integer.parseInt(byte1.substring(7, 6)));
		v35PortInfo.setTime25(Integer.parseInt(byte1.substring(6, 7)));
		v35PortInfo.setTime26(Integer.parseInt(byte1.substring(5, 6)));
		v35PortInfo.setTime27(Integer.parseInt(byte1.substring(4, 5)));
		v35PortInfo.setTime28(Integer.parseInt(byte1.substring(3, 4)));
		v35PortInfo.setTime29(Integer.parseInt(byte1.substring(2, 3)));
		v35PortInfo.setTime30(Integer.parseInt(byte1.substring(1, 2)));
		v35PortInfo.setTime31(Integer.parseInt(byte1.substring(0, 1)));
		
		return v35PortInfo;
	}
}

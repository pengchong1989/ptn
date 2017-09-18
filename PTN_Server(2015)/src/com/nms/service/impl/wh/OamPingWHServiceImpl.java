package com.nms.service.impl.wh;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.oamStatus.PingOrderControllerInfo;
import com.nms.drive.service.PtnServiceEnum;
import com.nms.drive.service.bean.NEObject;
import com.nms.drive.service.bean.status.PingOrderControllerObject;
import com.nms.drive.service.impl.CoderUtils;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.WHOperationBase;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.WhImplUtil;

public class OamPingWHServiceImpl extends WHOperationBase implements OperationServiceI{

	@Override
	public String excutionDelete(List objectList) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		PingOrderControllerInfo pingOrderControllerInfo = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		NEObject neObject = null;
		List<PingOrderControllerObject> pingOrderControllerInfos = null;
		PingOrderControllerObject pingOrderControllerObject = null;
		try {
			pingOrderControllerInfo = (PingOrderControllerInfo) object;
			pingOrderControllerInfos = new ArrayList<PingOrderControllerObject>();
			pingOrderControllerObject = new PingOrderControllerObject();
			CoderUtils.copy(pingOrderControllerInfo, pingOrderControllerObject);
			pingOrderControllerInfos.add(pingOrderControllerObject);
			operationObjectAfter = new OperationObject();
			SiteUtil siteUtil=new SiteUtil();
			WhImplUtil whImplUtil = new WhImplUtil();
				if("0".equals(siteUtil.SiteTypeUtil(pingOrderControllerInfo.getSiteId())+"")){//非失连网元、非虚拟网元下发设备
					neObject = whImplUtil.siteIdToNeObject(pingOrderControllerInfo.getSiteId());
					operationObjectAfter = new OperationObject();
					super.sendAction(operationObjectAfter, neObject, pingOrderControllerInfos,PtnServiceEnum.OAMPING);//下发tunnel配置
					operationObjectResult = super.verification(operationObjectAfter);//获取设备返回信息
					if (!(operationObjectResult.isSuccess())) {//失败
						return super.getErrorMessage(operationObjectResult);
					}
				}
				
			return ResultString.CONFIG_SUCCESS;
		} catch (Exception e) {
			throw e;
		} finally {
			operationObjectAfter = null;
			operationObjectResult = null;
		}
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object synchro(int siteId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}

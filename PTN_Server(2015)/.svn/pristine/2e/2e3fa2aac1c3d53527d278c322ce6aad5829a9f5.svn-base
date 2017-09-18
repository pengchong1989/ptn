package com.nms.service.impl.wh;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.path.eth.ElanInfo;
import com.nms.db.bean.ptn.path.eth.ElineInfo;
import com.nms.db.bean.ptn.path.eth.EtreeInfo;
import com.nms.db.bean.ptn.path.pw.PwNniInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.model.ptn.path.eth.ElanInfoService_MB;
import com.nms.model.ptn.path.eth.ElineInfoService_MB;
import com.nms.model.ptn.path.eth.EtreeInfoService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.WHOperationBase;
import com.nms.service.impl.util.ResultString;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class PwBufferWHServiceImpl extends WHOperationBase implements OperationServiceI{

	@Override
	public String excutionDelete(List objectList) throws Exception {
		
		return null;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		
		return null;
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {
		PwNniInfo pwNniInfo = null;
		List<PwNniInfo> pwNniInfoList = null;
		if(object instanceof List){
			pwNniInfoList = (List<PwNniInfo>) object;
		}else{
			pwNniInfo = (PwNniInfo) object;
		}
		List<Integer> siteList = null;
		List<Integer> pwIdList = null;
		ElineInfoService_MB elineService = null;
		EtreeInfoService_MB etreeService = null;
		ElanInfoService_MB elanInfoService = null;
		List<ElineInfo> elineInfos = null;
		List<EtreeInfo> etreeInfos = null;
		List<ElanInfo> elanInfos = null;
		ElineWHServiceImpl elineWHServiceImpl = null;
		EtreeWHServiceImpl etreeWHServiceImpl = null;
		ElanWHServiceImpl elanWHServiceImpl = null;
		String result = "";
		try {
			elineService = (ElineInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Eline);
			etreeService = (EtreeInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.EtreeInfo);
			elanInfoService = (ElanInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ElanInfo);
			siteList = new ArrayList<Integer>();
			pwIdList = new ArrayList<Integer>();
			if(pwNniInfoList != null){
				for(PwNniInfo info : pwNniInfoList){
					if(super.getManufacturer(info.getSiteId()) != EManufacturer.CHENXIAO.getValue()){
						siteList.add(info.getSiteId());
					}
					pwIdList.add(pwNniInfoList.get(0).getPwId());
				}
				
			}else if(pwNniInfo != null){
				pwIdList.add(pwNniInfo.getPwId());
			}
			elineInfos = elineService.selectElineByPwId(pwIdList);
			etreeInfos = etreeService.selectEtreeByPwId(pwIdList);
			elanInfos = elanInfoService.selectElanbypwid(pwIdList);
			if(elineInfos != null && elineInfos.size()>0 && elineInfos.get(0).getActiveStatus() ==1){
				elineWHServiceImpl = new ElineWHServiceImpl();
				result = elineWHServiceImpl.excutionUpdate(elineInfos.get(0));
			}else if(etreeInfos != null && etreeInfos.size()>0 && etreeInfos.get(0).getActiveStatus() ==1){
				etreeWHServiceImpl = new EtreeWHServiceImpl();
				result = etreeWHServiceImpl.excutionUpdate(etreeInfos);
			}else if(elanInfos != null && elanInfos.size()>0 && elanInfos.get(0).getActiveStatus() ==1){
				elanWHServiceImpl = new ElanWHServiceImpl();
				result = elanWHServiceImpl.excutionUpdate(elanInfos);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			UiUtil.closeService_MB(elanInfoService);
			UiUtil.closeService_MB(etreeService);
			UiUtil.closeService_MB(elineService);
			pwNniInfo = null;
			pwNniInfoList = null;
			siteList = null;
			pwIdList = null;
			elineInfos = null;
			etreeInfos = null;
			elanInfos = null;
			elineWHServiceImpl = null;
			etreeWHServiceImpl = null;
			elanWHServiceImpl = null;
		}
		return ResultString.CONFIG_SUCCESS;
	}

	@Override
	public Object synchro(int siteId) throws Exception {
		
		return null;
	}

}

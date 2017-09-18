package com.nms.service.impl.cx;

import java.util.List;

import com.nms.db.bean.ptn.path.pw.PwNniInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.drivechenxiao.service.bean.pweth.PwEthObject;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.CXActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.CXOperationBase;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

/**
 * 陈晓：
 *  vlan适配-（与pw同步，即：只有更新方法）
 * @author sy
 *
 */
public class PwBufferCXServiceImpl extends CXOperationBase implements OperationServiceI{

	@Override
	public String excutionInsert(Object object) throws Exception {
		return null;
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {
		List<PwNniInfo> pwInfoList=null;			
		OperationObject operationObject=null;
		pwInfoList=(List<PwNniInfo>) object;
		if (null != pwInfoList) {			
			//确保 网元为陈晓，并且为登陆网元状态			
			operationObject = this.convertOperation(operationObject, pwInfoList, TypeAndActionUtil.ACTION_UPDATE);
			super.sendAction(operationObject);
			operationObject = super.verification(operationObject);
			if (operationObject.isSuccess()) {
				if( operationObject.getCxActionObjectList()!=null&&operationObject.getCxActionObjectList().size()>0){
					return operationObject.getCxActionObjectList().get(0).getStatus();
				}
				
			} else {
				//返回失败信息
				return super.getErrorMessage(operationObject);
			}
		}else{
		return  ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		}
		operationObject = null;		
		/**
		 * 武汉网元则直接返回配置成功
		 */
		return ResultString.CONFIG_SUCCESS;
	}

	@Override
	public String excutionDelete(List objectList) throws Exception {
		
		return null;
	}

	@Override
	public Object synchro(int siteId) throws Exception {
		
		return null;
	}
	private OperationObject convertOperation(OperationObject operationObject, List<PwNniInfo> pwNniInfoList, String action) throws Exception {
		operationObject = new OperationObject();
		try {
			if(pwNniInfoList!=null&&pwNniInfoList.size()>0){
				for(PwNniInfo info:pwNniInfoList){
					if(super.getManufacturer(info.getSiteId()) == EManufacturer.CHENXIAO.getValue()){
						CXActionObject cxActionObject = new CXActionObject();
						cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
						cxActionObject.setCxNeObject(super.getCXNEObject(info.getSiteId()));
						cxActionObject.setType(TypeAndActionUtil.TYPE_PW_VLAN);
						cxActionObject.setAction(action);
						cxActionObject.setPwEthObject((PwEthObject)this.getPwEthObject(info));
						operationObject.getCxActionObjectList().add(cxActionObject);
					}				
				}
			}
			
		} catch (Exception e) {
			throw e;
		}
		return operationObject;
	}
	public PwEthObject getPwEthObject(PwNniInfo pwnniInfo)throws Exception{
		PwEthObject pwObject = null;
		try{
			pwObject=new PwEthObject();
			pwObject.setName(pwnniInfo.getPwBusinessId()+"");
			pwObject.setAction(UiUtil.getCodeById(pwnniInfo.getExitRule()).getCodeValue());
			pwObject.setSdvlan(pwnniInfo.getSvlan()+"");
			pwObject.setSdvlanpri(pwnniInfo.getVlanpri());
			pwObject.setTpid(UiUtil.getCodeById(pwnniInfo.getTpid()).getCodeValue());
			
		}catch (Exception e) {
			throw e;
		}
		return pwObject;
	}
	
}

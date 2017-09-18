package com.nms.service.impl.cx;

import java.util.List;

import com.nms.db.bean.ptn.SiteRoate;
import com.nms.db.bean.ptn.path.tunnel.Lsp;
import com.nms.db.enums.EManufacturer;
import com.nms.db.enums.ERotateType;
import com.nms.drivechenxiao.service.bean.tunnel.TunnelObject;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.CXActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.CXOperationBase;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.TypeAndActionUtil;

public class RotateCXServiceImpl extends CXOperationBase implements OperationServiceI {

	@Override
	public String excutionDelete(List objectList) throws Exception {
		return null;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		return null;
	}
	/*
	 * 删掉原有roate方法，武汉与陈晓统一写在实现类下的excutionUpdate()中
	 * @see com.nms.service.OperationServiceI#excutionUpdate(java.lang.Object)
	 */
	@Override
	public String excutionUpdate(Object object) throws Exception {
		OperationObject operationObject = null;
		List<SiteRoate> siteRoateList=null;
		try {
			siteRoateList=(List<SiteRoate>) object;
			operationObject = new OperationObject();
			this.convertOperaction(operationObject, siteRoateList);
			if (operationObject.getCxActionObjectList().size() > 0) {
				super.sendAction(operationObject);
				super.verification(operationObject);
				if (operationObject.isSuccess()) {
					return ResultString.CONFIG_SUCCESS;
				} else {
					return super.getErrorMessage(operationObject);
				}
			} else {
				return ResultString.CONFIG_SUCCESS;
			}

		} catch (Exception e) {
			throw e;
		} finally {
			operationObject = null;
			siteRoateList=null;
		}
	}

	@Override
	public Object synchro(int siteId) throws Exception {
		return null;
	}
	/**
	 * 转换operaction对象
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	private void convertOperaction(OperationObject operationObject, List<SiteRoate> siteRoateList) throws Exception {

		if(siteRoateList!=null&&siteRoateList.size()>0){
			for(SiteRoate siteRoate:siteRoateList){
				if(super.getManufacturer(siteRoate.getSiteId()) == EManufacturer.CHENXIAO.getValue()){
					this.convertAction(operationObject, siteRoate);
				}
			}
		}
	}

	private void convertAction(OperationObject operationObject, SiteRoate siteRoate) throws Exception {

		CXActionObject cxActionObject = null;
		try {		
			cxActionObject = new CXActionObject();
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setCxNeObject(super.getCXNEObject(siteRoate.getSiteId()));
			cxActionObject.setType(TypeAndActionUtil.TYPE_ROTATE);
			cxActionObject.setAction(TypeAndActionUtil.ACTION_UPDATE);
			cxActionObject.setTunnelObject(this.convertTunnel(siteRoate));
			operationObject.getCxActionObjectList().add(cxActionObject);

		} catch (Exception e) {
			throw e;
		}finally{
			cxActionObject = null;
		}
	}
	/**
	 * 将界面成数据，转为驱动程序对象
	 * @param siteRoate
	 * @return
	 * @throws Exception
	 */
	private TunnelObject convertTunnel(SiteRoate siteRoate) throws Exception {
		TunnelObject tunnelObject = null;
		int rotateCode = -1;
		int tunnelName = 0;
		try {
			tunnelObject = new TunnelObject();		
			for (Lsp lsp : siteRoate.getTunnel().getLspParticularList()) {				
				if (lsp.getASiteId() == siteRoate.getSiteId()) {
					tunnelName = lsp.getAtunnelbusinessid();
					rotateCode = siteRoate.getRoate();
					break;
				}		
				if (lsp.getZSiteId() == siteRoate.getSiteId()) {
					tunnelName = lsp.getZtunnelbusinessid();
					rotateCode =  siteRoate.getRoate();
					break;
				}
			}
			if (null != ERotateType.forms(rotateCode)) {
				tunnelObject.setName(tunnelName + "");
				tunnelObject.getProtection().setApscmd(ERotateType.forms(rotateCode).toString());
			}
		} catch (Exception e) {
			throw e;
		}finally{
			tunnelName=0;
			rotateCode=-1;
		}
		return tunnelObject;
	}
}

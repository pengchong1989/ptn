package com.nms.service.impl.cx;

import java.util.List;

import com.nms.db.bean.ptn.Businessid;
import com.nms.db.bean.ptn.qos.QosMappingAttr;
import com.nms.db.bean.ptn.qos.QosMappingMode;
import com.nms.db.enums.EMappingColorEnum;
import com.nms.db.enums.QosCosLevelEnum;
import com.nms.db.enums.QosTemplateTypeEnum;
import com.nms.drivechenxiao.service.bean.protocols.Cos2vlanpriObject;
import com.nms.drivechenxiao.service.bean.protocols.Vlanpri2cngObject;
import com.nms.drivechenxiao.service.bean.protocols.mpls.elsp.CostoexpObject;
import com.nms.drivechenxiao.service.bean.protocols.mpls.elsp.ExptocosObject;
import com.nms.drivechenxiao.service.bean.protocols.mpls.llsp.ClrtoexpObject;
import com.nms.drivechenxiao.service.bean.protocols.mpls.llsp.ExptoclrObject;
import com.nms.model.ptn.BusinessidService_MB;
import com.nms.model.ptn.qos.QosMappingModeAttrService_MB;
import com.nms.model.ptn.qos.QosMappingModeService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.CXActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.CXOperationBase;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.BusinessIdException;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;
/**
 * MSP保护 转换类
 * @author dzy
 *
 */
public class ExpMappingCXServiceImpl extends CXOperationBase implements OperationServiceI {
	/**
	 * 添加MSP
	 * 
	 * @param object  MSP保护 Bean
	 * 
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	@Override
	public String excutionInsert(Object object) throws Exception, BusinessIdException {
		QosMappingModeService_MB modeService = null;
		OperationObject operationObject = null;
		QosMappingMode qosMappingMode;
		String result = null;
		int mspId = 0;
		try {
			modeService = (QosMappingModeService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosMappingModeService);
			qosMappingMode = (QosMappingMode) object;
			if(!qosMappingMode.isDataDownLoad()){ 
				//保存数据
				mspId = modeService.saveForCX(qosMappingMode);
				qosMappingMode.setId(mspId);
			}
			operationObject = this.convertOperation(operationObject,qosMappingMode, TypeAndActionUtil.ACTION_INSERT);
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				result = operationObject.getCxActionObjectList().get(0).getStatus();
			} else {
				modeService.restoreData(qosMappingMode);
				result = super.getErrorMessage(operationObject);
			}
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		} catch (BusinessIdException e) {
			throw e;
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(modeService);
		}
		return result;
	}

	@Override
	/**
	 * 修改
	 * 
	 * @param object  QosMappingMode
	 * 
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	public String excutionUpdate(Object object) throws Exception {
		OperationObject operationObject = null;
		QosMappingMode qosMappingMode;
		String result = null;
		try {
			qosMappingMode = (QosMappingMode) object;
			operationObject = this.convertOperation(operationObject,qosMappingMode, TypeAndActionUtil.ACTION_UPDATE);
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				if(!qosMappingMode.isDataDownLoad()){
					//更新方法
				}
				result = operationObject.getCxActionObjectList().get(0).getStatus();
			} else {
				if(!qosMappingMode.isDataDownLoad()){
					//如果下发设备把数据修改至改动之前
	//				modeService.saveOrUpdate(mspProtect_before);
				}
				result = super.getErrorMessage(operationObject);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			operationObject = null;
			qosMappingMode = null;
		}
		return result;
	}

	
	/**
	 * 删除
     *	 
	 * @param object  QosMappingMode
	 * 
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	
	public String excutionDelete(List objectList) throws Exception {
		String result = null;
/*		OperationObject operationObject = null;
		List<QosMappingMode> mappingModeList=null;
		
		try {
			mappingModeList = objectList;
			for(QosMappingMode qosMappingMode:mappingModeList){
				//如果设备上没有不下发设备，只操作数据库
				if(0==qosMappingMode.getEquipExit()){
					operationObject = this.convertOperation(operationObject,qosMappingMode, TypeAndActionUtil.ACTION_DELETE);
					super.sendAction(operationObject);
					operationObject = super.verification(operationObject);
					if (operationObject.isSuccess()) {
						//如果成功删除数据
						modeService.delete(qosMappingMode);
						result = operationObject.getCxActionObjectList().get(0).getStatus();
					} else {
						result = super.getErrorMessage(operationObject);
					}
				}else{
					modeService.delete(qosMappingMode);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			operationObject = null;
			mappingModeList = null;
		}*/
		return result;
	}
	
	/**
	 * 同步
	 * 
	 * @param siteId网元ID
	 * 
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	@Override
	public Object synchro(int siteId) throws Exception {
		String result = null;
		QosMappingMode qosMappingMode = null;
		OperationObject operationObject = null;
		QosMappingModeService_MB modeService = null;
		QosMappingModeAttrService_MB qosMappingModeAttrService = null;
		try {
			modeService = (QosMappingModeService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosMappingModeService);
			qosMappingModeAttrService = (QosMappingModeAttrService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosMappingModeAttrService);
			qosMappingMode = new QosMappingMode();
			qosMappingMode.setSiteId(siteId);
			operationObject = this.convertOperation(operationObject, qosMappingMode, TypeAndActionUtil.ACTION_SYNCHRO);
			super.sendAction(operationObject);
			operationObject = super.verification(operationObject);
			if (operationObject.isSuccess()) {
				//删除此网元所有数据
				modeService.deleteForSiteId(siteId);
				qosMappingModeAttrService.deleteForSiteId(siteId);
				updateBusinessIdNotBeUsed(siteId);
			
				//数据转后后插入数据库
				this.getExpToColorMapping(operationObject.getCxActionObjectList().get(0).getExptoclrObjectList(), siteId);
				this.getColorToExpMapping(operationObject.getCxActionObjectList().get(0).getClrtoexpObjectList(), siteId);
				this.getExpToCosMapping(operationObject.getCxActionObjectList().get(0).getExptocosObjectList(), siteId);
				this.getCosToExpMapping(operationObject.getCxActionObjectList().get(0).getCostoexpObjectList(), siteId);
				this.getVlanpirToColorMapping(operationObject.getCxActionObjectList().get(0).getVlanpri2cngObjectList(), siteId);
				this.getCosToVlanpirMapping(operationObject.getCxActionObjectList().get(0).getCos2vlanpriObjectList(), siteId);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(modeService);
			UiUtil.closeService_MB(qosMappingModeAttrService);
		}
		return result;
	}
	
	/**
	 * 设置网元的businessID为未使用
	 * @param siteId
	 * @throws Exception
	 */
	private void updateBusinessIdNotBeUsed(int siteId) throws Exception {
		
		BusinessidService_MB businessidService = null;
		try {
			businessidService = (BusinessidService_MB) ConstantUtil.serviceFactory.newService_MB(Services.BUSINESSID);
			Businessid businessId = new Businessid();
			//llsppmappinginput
			businessId.setIdStatus(0);
			businessId.setSiteId(siteId);
			businessId.setType("llsppmappinginput");
			businessidService.updateBusinessidForSite(businessId);
			//llsppmappinginoutput
			businessId.setIdStatus(0);
			businessId.setSiteId(siteId);
			businessId.setType("llsppmappingoutput");
			businessidService.updateBusinessidForSite(businessId);
			//elsppmappinginput
			businessId.setIdStatus(0);
			businessId.setSiteId(siteId);
			businessId.setType("elsppmappinginput");
			businessidService.updateBusinessidForSite(businessId);
			//elsppmappingoutput
			businessId.setIdStatus(0);
			businessId.setSiteId(siteId);
			businessId.setType("elsppmappingoutput");
			businessidService.updateBusinessidForSite(businessId);
			
			//vlanpirtocolormapping
			businessId.setIdStatus(0);
			businessId.setSiteId(siteId);
			businessId.setType("vlanpirtocolormapping");
			businessidService.updateBusinessidForSite(businessId);
			
			//costovlanpirmapping
			businessId.setIdStatus(0);
			businessId.setSiteId(siteId);
			businessId.setType("costovlanpirmapping");
			businessidService.updateBusinessidForSite(businessId);
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(businessidService);
		}
	}
	
	private void getCosToVlanpirMapping(List<Cos2vlanpriObject> cos2vlanpriObjectList, int siteId) throws Exception {
		
		
		int modelId;
		Businessid businessId = new Businessid();
		QosMappingMode qosMappingMode = new QosMappingMode();
		QosMappingAttr qosMappingAttr = new QosMappingAttr();
		BusinessidService_MB businessidService = null;
		QosMappingModeService_MB modeService = null;
		QosMappingModeAttrService_MB qosMappingModeAttrService = null;
		try {
			businessidService = (BusinessidService_MB) ConstantUtil.serviceFactory.newService_MB(Services.BUSINESSID);
			modeService = (QosMappingModeService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosMappingModeService);
			qosMappingModeAttrService = (QosMappingModeAttrService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosMappingModeAttrService);
			businessId.setIdStatus(1);
			businessId.setSiteId(siteId);
			businessId.setType("costovlanpirmapping");
			qosMappingMode.setSiteId(siteId);
			qosMappingMode.setType(QosTemplateTypeEnum.COS_VLANPRI.getValue());
			qosMappingMode.setTypeName(QosTemplateTypeEnum.COS_VLANPRI.toString());
			qosMappingAttr.setSiteId(siteId);
			for(Cos2vlanpriObject cos2vlanpriObject:cos2vlanpriObjectList){
				qosMappingMode.setName("表"+siteId+cos2vlanpriObject.getName());
				qosMappingMode.setBusinessId(Integer.parseInt(cos2vlanpriObject.getName()));
				modelId = modeService.save(qosMappingMode);
				
				//修改businessId
				businessId.setIdValue(Integer.parseInt(cos2vlanpriObject.getName()));
				businessidService.updateBusinessid(businessId);
				
				qosMappingAttr.setPhbId(modelId);
				qosMappingAttr.setGrade("0");
				qosMappingAttr.setValue(Integer.parseInt(cos2vlanpriObject.getBe()));
				qosMappingModeAttrService.save(qosMappingAttr);
				qosMappingAttr.setGrade("1");
				qosMappingAttr.setValue(Integer.parseInt(cos2vlanpriObject.getAf1()));
				qosMappingModeAttrService.save(qosMappingAttr);
				qosMappingAttr.setGrade("2");
				qosMappingAttr.setValue(Integer.parseInt(cos2vlanpriObject.getAf2()));
				qosMappingModeAttrService.save(qosMappingAttr);
				qosMappingAttr.setGrade("3");
				qosMappingAttr.setValue(Integer.parseInt(cos2vlanpriObject.getAf3()));
				qosMappingModeAttrService.save(qosMappingAttr);
				qosMappingAttr.setGrade("4");
				qosMappingAttr.setValue(Integer.parseInt(cos2vlanpriObject.getAf4()));
				qosMappingModeAttrService.save(qosMappingAttr);
				qosMappingAttr.setGrade("5");
				qosMappingAttr.setValue(Integer.parseInt(cos2vlanpriObject.getEf()));
				qosMappingModeAttrService.save(qosMappingAttr);
				qosMappingAttr.setGrade("6");
				qosMappingAttr.setValue(Integer.parseInt(cos2vlanpriObject.getCs6()));
				qosMappingModeAttrService.save(qosMappingAttr);
				qosMappingAttr.setGrade("7");
				qosMappingAttr.setValue(Integer.parseInt(cos2vlanpriObject.getCs7()));
				qosMappingModeAttrService.save(qosMappingAttr);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}finally{
			UiUtil.closeService_MB(modeService);
			UiUtil.closeService_MB(qosMappingModeAttrService);
			UiUtil.closeService_MB(businessidService);
		}
	}
	
	private void getVlanpirToColorMapping(List<Vlanpri2cngObject> vlanpri2cngObjectList, int siteId) throws Exception {
		
		int modelId;
		Businessid businessId = new Businessid();
		QosMappingMode qosMappingMode = new QosMappingMode();
		QosMappingAttr qosMappingAttr = new QosMappingAttr();
		BusinessidService_MB businessidService = null;
		QosMappingModeService_MB modeService = null;
		QosMappingModeAttrService_MB qosMappingModeAttrService = null;
		try {
			businessidService = (BusinessidService_MB) ConstantUtil.serviceFactory.newService_MB(Services.BUSINESSID);
			modeService = (QosMappingModeService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosMappingModeService);
			qosMappingModeAttrService = (QosMappingModeAttrService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosMappingModeAttrService);
			
			businessId.setIdStatus(1);
			businessId.setSiteId(siteId);
			businessId.setType("vlanpirtocolormapping");
			qosMappingMode.setSiteId(siteId);
			qosMappingMode.setType(QosTemplateTypeEnum.VLANPRI_COLOR.getValue());
			qosMappingMode.setTypeName(QosTemplateTypeEnum.VLANPRI_COLOR.toString());
			qosMappingAttr.setSiteId(siteId);
			for(Vlanpri2cngObject vlanpri2cngObject:vlanpri2cngObjectList){
				qosMappingMode.setName("表"+siteId+vlanpri2cngObject.getName());
				qosMappingMode.setBusinessId(Integer.parseInt(vlanpri2cngObject.getName()));
				modelId = modeService.save(qosMappingMode);
				
				//修改businessId
				businessId.setIdValue(Integer.parseInt(vlanpri2cngObject.getName()));
				businessidService.updateBusinessid(businessId);
				
				qosMappingAttr.setPhbId(modelId);
				qosMappingAttr.setValue(0);
				qosMappingAttr.setColor(Integer.parseInt(vlanpri2cngObject.getVlanpri0()));
				qosMappingModeAttrService.save(qosMappingAttr);
				qosMappingAttr.setValue(1);
				qosMappingAttr.setColor(Integer.parseInt(vlanpri2cngObject.getVlanpri1()));
				qosMappingModeAttrService.save(qosMappingAttr);
				qosMappingAttr.setValue(2);
				qosMappingAttr.setColor(Integer.parseInt(vlanpri2cngObject.getVlanpri2()));
				qosMappingModeAttrService.save(qosMappingAttr);
				qosMappingAttr.setValue(3);
				qosMappingAttr.setColor(Integer.parseInt(vlanpri2cngObject.getVlanpri3()));
				qosMappingModeAttrService.save(qosMappingAttr);
				qosMappingAttr.setValue(4);
				qosMappingAttr.setColor(Integer.parseInt(vlanpri2cngObject.getVlanpri4()));
				qosMappingModeAttrService.save(qosMappingAttr);
				qosMappingAttr.setValue(5);
				qosMappingAttr.setColor(Integer.parseInt(vlanpri2cngObject.getVlanpri5()));
				qosMappingModeAttrService.save(qosMappingAttr);
				qosMappingAttr.setValue(6);
				qosMappingAttr.setColor(Integer.parseInt(vlanpri2cngObject.getVlanpri6()));
				qosMappingModeAttrService.save(qosMappingAttr);
				qosMappingAttr.setValue(7);
				qosMappingAttr.setColor(Integer.parseInt(vlanpri2cngObject.getVlanpri7()));
				qosMappingModeAttrService.save(qosMappingAttr);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(qosMappingModeAttrService);
			UiUtil.closeService_MB(businessidService);
			UiUtil.closeService_MB(modeService);
		}
	}
	/**
	 * 转换层llsp输入对象，并且插入数据库
	 * @param exptoclrObjectList
	 * @param siteId
	 * @throws Exception
	 */
	private void getExpToColorMapping(List<ExptoclrObject> exptoclrObjectList,int siteId) throws Exception {
		
		int modelId ;
		Businessid businessId = new Businessid();
		businessId.setIdStatus(1);
		businessId.setSiteId(siteId);
		businessId.setType("llsppmappinginput");
		BusinessidService_MB businessidService = null;
		QosMappingModeService_MB modeService = null;
		QosMappingModeAttrService_MB qosMappingModeAttrService = null;
		try {
			businessidService = (BusinessidService_MB) ConstantUtil.serviceFactory.newService_MB(Services.BUSINESSID);
			modeService = (QosMappingModeService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosMappingModeService);
			qosMappingModeAttrService = (QosMappingModeAttrService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosMappingModeAttrService);
			
			QosMappingMode qosMappingMode = new QosMappingMode();
			QosMappingAttr qosMappingAttr = new QosMappingAttr();
			qosMappingMode.setSiteId(siteId);
			qosMappingMode.setType(QosTemplateTypeEnum.LLSP.getValue());
			qosMappingMode.setTypeName(QosTemplateTypeEnum.LLSP.toString());
			qosMappingAttr.setModel(UiUtil.getCodeByValue("EXPTYPE", "0").getId());
			qosMappingAttr.setDirection(UiUtil.getCodeByValue("EXPDIRECTION", "1").getId());
			qosMappingAttr.setSiteId(siteId);
			for(ExptoclrObject exptoclrObject:exptoclrObjectList){
				
				businessId.setIdValue(Integer.parseInt(exptoclrObject.getName()));
				businessidService.updateBusinessid(businessId);
				
				qosMappingMode.setName("表"+siteId+exptoclrObject.getName());
				qosMappingMode.setBusinessId(Integer.parseInt(exptoclrObject.getName()));
				modelId = modeService.save(qosMappingMode);
				
				qosMappingAttr.setPhbId(modelId);
				qosMappingAttr.setValue(0);
				qosMappingAttr.setColor(Integer.parseInt(exptoclrObject.getExp0()));
				qosMappingModeAttrService.save(qosMappingAttr);
				qosMappingAttr.setValue(1);
				qosMappingAttr.setColor(Integer.parseInt(exptoclrObject.getExp1()));
				qosMappingModeAttrService.save(qosMappingAttr);
				qosMappingAttr.setValue(2);
				qosMappingAttr.setColor(Integer.parseInt(exptoclrObject.getExp2()));
				qosMappingModeAttrService.save(qosMappingAttr);
				qosMappingAttr.setValue(3);
				qosMappingAttr.setColor(Integer.parseInt(exptoclrObject.getExp3()));
				qosMappingModeAttrService.save(qosMappingAttr);
				qosMappingAttr.setValue(4);
				qosMappingAttr.setColor(Integer.parseInt(exptoclrObject.getExp4()));
				qosMappingModeAttrService.save(qosMappingAttr);
				qosMappingAttr.setValue(5);
				qosMappingAttr.setColor(Integer.parseInt(exptoclrObject.getExp5()));
				qosMappingModeAttrService.save(qosMappingAttr);
				qosMappingAttr.setValue(6);
				qosMappingAttr.setColor(Integer.parseInt(exptoclrObject.getExp6()));
				qosMappingModeAttrService.save(qosMappingAttr);
				qosMappingAttr.setValue(7);
				qosMappingAttr.setColor(Integer.parseInt(exptoclrObject.getExp7()));
				qosMappingModeAttrService.save(qosMappingAttr);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(modeService);
			UiUtil.closeService_MB(qosMappingModeAttrService);
			UiUtil.closeService_MB(businessidService);
		}
	}
	
	/**
	 * 转换层llsp输出对象，并且插入数据库
	 * @param clrtoexpObjectList
	 * @param siteId
	 * @throws Exception
	 */
	private void getColorToExpMapping(List<ClrtoexpObject> clrtoexpObjectList,int siteId) throws Exception {
		
		int modelId ;
		BusinessidService_MB businessidService = null;
		QosMappingModeService_MB modeService = null;
		QosMappingModeAttrService_MB qosMappingModeAttrService = null;
		try {
			businessidService = (BusinessidService_MB) ConstantUtil.serviceFactory.newService_MB(Services.BUSINESSID);
			modeService = (QosMappingModeService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosMappingModeService);
			qosMappingModeAttrService = (QosMappingModeAttrService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosMappingModeAttrService);
			
			Businessid businessId = new Businessid();
			businessId.setIdStatus(1);
			businessId.setSiteId(siteId);
			businessId.setType("llsppmappingoutput");
			QosMappingMode qosMappingMode = new QosMappingMode();
			QosMappingAttr qosMappingAttr = new QosMappingAttr();
			qosMappingMode.setSiteId(siteId);
			qosMappingMode.setType(QosTemplateTypeEnum.LLSP.getValue());
			qosMappingMode.setTypeName(QosTemplateTypeEnum.LLSP.toString());
			qosMappingAttr.setModel(UiUtil.getCodeByValue("EXPTYPE", "0").getId());
			qosMappingAttr.setDirection(UiUtil.getCodeByValue("EXPDIRECTION", "0").getId());
			qosMappingAttr.setSiteId(siteId);
			for(ClrtoexpObject clrtoexpObject:clrtoexpObjectList){
				businessId.setIdValue(Integer.parseInt(clrtoexpObject.getName()));
				businessidService.updateBusinessid(businessId);
				
				qosMappingMode.setName("表"+siteId+clrtoexpObject.getName());
				qosMappingMode.setBusinessId(Integer.parseInt(clrtoexpObject.getName()));
				modelId = modeService.save(qosMappingMode);
				
				qosMappingAttr.setPhbId(modelId);
				qosMappingAttr.setColor(EMappingColorEnum.YELLOW.getValue());//黄色
				qosMappingAttr.setValue(Integer.parseInt(clrtoexpObject.getYellow()));
				qosMappingModeAttrService.save(qosMappingAttr);
				qosMappingAttr.setColor(EMappingColorEnum.GREEN.getValue());//绿色
				qosMappingAttr.setValue(Integer.parseInt(clrtoexpObject.getGreen()));
				qosMappingModeAttrService.save(qosMappingAttr);
			}	
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(modeService);
			UiUtil.closeService_MB(qosMappingModeAttrService);
			UiUtil.closeService_MB(businessidService);
		}
	}
	
	/**
	 * 转换层elsp输入对象，并且插入数据库
	 * @param exptocosObjectList
	 * @param siteId
	 * @throws Exception
	 */
	private void getExpToCosMapping(List<ExptocosObject> exptocosObjectList,
			int siteId) throws Exception {
		int modelId ;
		BusinessidService_MB businessidService = null;
		QosMappingModeService_MB modeService = null;
		QosMappingModeAttrService_MB qosMappingModeAttrService = null;
		try {
			businessidService = (BusinessidService_MB) ConstantUtil.serviceFactory.newService_MB(Services.BUSINESSID);
			modeService = (QosMappingModeService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosMappingModeService);
			qosMappingModeAttrService = (QosMappingModeAttrService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosMappingModeAttrService);
			
			Businessid businessId = new Businessid();
			businessId.setIdStatus(1);
			businessId.setSiteId(siteId);
			businessId.setType("elsppmappinginput");
			QosMappingMode qosMappingMode = new QosMappingMode();
			QosMappingAttr qosMappingAttr = new QosMappingAttr();
			qosMappingMode.setSiteId(siteId);
			qosMappingMode.setType(QosTemplateTypeEnum.ELSP.getValue());
			qosMappingMode.setTypeName(QosTemplateTypeEnum.ELSP.toString());
			qosMappingAttr.setModel(UiUtil.getCodeByValue("EXPTYPE", "1").getId());
			qosMappingAttr.setDirection(UiUtil.getCodeByValue("EXPDIRECTION", "1").getId());
			qosMappingAttr.setSiteId(siteId);
			for(ExptocosObject exptocosObject:exptocosObjectList){
				qosMappingMode.setName("表"+siteId+exptocosObject.getName());
				qosMappingMode.setBusinessId(Integer.parseInt(exptocosObject.getName()));
				modelId = modeService.save(qosMappingMode);
				//修改businessid
				businessId.setIdValue(Integer.parseInt(exptocosObject.getName()));
				businessidService.updateBusinessid(businessId);
				
				qosMappingAttr.setPhbId(modelId);
				qosMappingAttr.setValue(0);
				qosMappingAttr.setGrade(exptocosObject.getExp0());
				qosMappingModeAttrService.save(qosMappingAttr);
				qosMappingAttr.setValue(1);
				qosMappingAttr.setGrade(exptocosObject.getExp1());
				qosMappingModeAttrService.save(qosMappingAttr);
				qosMappingAttr.setValue(2);
				qosMappingAttr.setGrade(exptocosObject.getExp2());
				qosMappingModeAttrService.save(qosMappingAttr);
				qosMappingAttr.setValue(3);
				qosMappingAttr.setGrade(exptocosObject.getExp3());
				qosMappingModeAttrService.save(qosMappingAttr);
				qosMappingAttr.setValue(4);
				qosMappingAttr.setGrade(exptocosObject.getExp4());
				qosMappingModeAttrService.save(qosMappingAttr);
				qosMappingAttr.setValue(5);
				qosMappingAttr.setGrade(exptocosObject.getExp5());
				qosMappingModeAttrService.save(qosMappingAttr);
				qosMappingAttr.setValue(6);
				qosMappingAttr.setGrade(exptocosObject.getExp6());
				qosMappingModeAttrService.save(qosMappingAttr);
				qosMappingAttr.setValue(7);
				qosMappingAttr.setGrade(exptocosObject.getExp7());
				qosMappingModeAttrService.save(qosMappingAttr);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(modeService);
			UiUtil.closeService_MB(qosMappingModeAttrService);
			UiUtil.closeService_MB(businessidService);
		}
	}
	
	/**
	 * 转换层elsp输出对象，并且插入数据库
	 * @param costoexpObjectList
	 * @param siteId
	 * @throws Exception
	 */
	private void getCosToExpMapping(List<CostoexpObject> costoexpObjectList,
			int siteId) throws Exception {
		int modelId ;
		BusinessidService_MB businessidService = null;
		QosMappingModeService_MB modeService = null;
		QosMappingModeAttrService_MB qosMappingModeAttrService = null;
		try {
			businessidService = (BusinessidService_MB) ConstantUtil.serviceFactory.newService_MB(Services.BUSINESSID);
			modeService = (QosMappingModeService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosMappingModeService);
			qosMappingModeAttrService = (QosMappingModeAttrService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosMappingModeAttrService);
			
			Businessid businessId = new Businessid();
			businessId.setIdStatus(1);
			businessId.setSiteId(siteId);
			businessId.setType("elsppmappingoutput");
			QosMappingMode qosMappingMode = new QosMappingMode();
			QosMappingAttr qosMappingAttr = new QosMappingAttr();
			qosMappingMode.setSiteId(siteId);
			qosMappingMode.setType(QosTemplateTypeEnum.ELSP.getValue());
			qosMappingMode.setTypeName(QosTemplateTypeEnum.ELSP.toString());
			qosMappingAttr.setModel(UiUtil.getCodeByValue("EXPTYPE", "1").getId());
			qosMappingAttr.setDirection(UiUtil.getCodeByValue("EXPDIRECTION", "0").getId());
			qosMappingAttr.setSiteId(siteId);
			for(CostoexpObject costoexpObject:costoexpObjectList){
				qosMappingMode.setName("表"+siteId+costoexpObject.getName());
				qosMappingMode.setBusinessId(Integer.parseInt(costoexpObject.getName()));
				modelId = modeService.save(qosMappingMode);
				
				//修改businessid
				businessId.setIdValue(Integer.parseInt(costoexpObject.getName()));
				businessidService.updateBusinessid(businessId);
				
				qosMappingAttr.setPhbId(modelId);
				qosMappingAttr.setValue(Integer.parseInt(costoexpObject.getBe()));
				qosMappingAttr.setGrade(QosCosLevelEnum.BE.getValue()+"");
				qosMappingModeAttrService.save(qosMappingAttr);
				qosMappingAttr.setValue(Integer.parseInt(costoexpObject.getAf1()));
				qosMappingAttr.setGrade(QosCosLevelEnum.AF1.getValue()+"");
				qosMappingModeAttrService.save(qosMappingAttr);
				qosMappingAttr.setValue(Integer.parseInt(costoexpObject.getAf2()));
				qosMappingAttr.setGrade(QosCosLevelEnum.AF2.getValue()+"");
				qosMappingModeAttrService.save(qosMappingAttr);
				qosMappingAttr.setValue(Integer.parseInt(costoexpObject.getAf3()));
				qosMappingAttr.setGrade(QosCosLevelEnum.AF3.getValue()+"");
				qosMappingModeAttrService.save(qosMappingAttr);
				qosMappingAttr.setValue(Integer.parseInt(costoexpObject.getAf4()));
				qosMappingAttr.setGrade(QosCosLevelEnum.AF4.getValue()+"");
				qosMappingModeAttrService.save(qosMappingAttr);
				qosMappingAttr.setValue(Integer.parseInt(costoexpObject.getEf()));
				qosMappingAttr.setGrade(QosCosLevelEnum.EF.getValue()+"");
				qosMappingModeAttrService.save(qosMappingAttr);
				qosMappingAttr.setValue(Integer.parseInt(costoexpObject.getCs6()));
				qosMappingAttr.setGrade(QosCosLevelEnum.CS6.getValue()+"");
				qosMappingModeAttrService.save(qosMappingAttr);
				qosMappingAttr.setValue(Integer.parseInt(costoexpObject.getCs7()));
				qosMappingAttr.setGrade(QosCosLevelEnum.CS7.getValue()+"");
				qosMappingModeAttrService.save(qosMappingAttr);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(modeService);
			UiUtil.closeService_MB(qosMappingModeAttrService);
			UiUtil.closeService_MB(businessidService);
		}
	}
	/**
	 * 获得msp对象
	 * 
	 * @param siteId	网元ID
	 * @param action 操作
	 * @return
	 * @throws Exception
	 */
	public OperationObject convertOperation(OperationObject operationObject, QosMappingMode qosMappingMode, String action) throws Exception {
		operationObject = new OperationObject();
		int direction;
		int mode;
		try {
			CXActionObject cxActionObject = new CXActionObject();
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setCxNeObject(super.getCXNEObject(qosMappingMode.getSiteId()));
			cxActionObject.setAction(action);
			if(null!=qosMappingMode.getQosMappingAttrList()&&qosMappingMode.getQosMappingAttrList().size()>0){
				mode = qosMappingMode.getQosMappingAttrList().get(0).getModel();
				direction = qosMappingMode.getQosMappingAttrList().get(0).getDirection();
				//LLSP输入
				if(UiUtil.getCodeByValue("EXPTYPE", "0").getId() == mode&&UiUtil.getCodeByValue("EXPDIRECTION", "1").getId()==direction){
					cxActionObject.setType(TypeAndActionUtil.TYPE_EXPMAPPINGLLSPINPUT);
					cxActionObject.setExptoclrObject((ExptoclrObject)this.getExptoclrObject(qosMappingMode));
				}
				//LLSP输出
				else if(UiUtil.getCodeByValue("EXPTYPE", "0").getId() == mode&&UiUtil.getCodeByValue("EXPDIRECTION", "0").getId()==direction){
					cxActionObject.setType(TypeAndActionUtil.TYPE_EXPMAPPINGLLSPOUTPUT);
					cxActionObject.setClrtoexpObject((ClrtoexpObject)this.getClrtoexpObject(qosMappingMode));
				}
				//ELSP输入
				else if(UiUtil.getCodeByValue("EXPTYPE", "1").getId() == mode&&UiUtil.getCodeByValue("EXPDIRECTION", "1").getId()==direction){
					cxActionObject.setType(TypeAndActionUtil.TYPE_EXPMAPPINGELSPINPUT);
					cxActionObject.setExptocosObject((ExptocosObject)this.getExptocosObject(qosMappingMode));
				}
				//ELSP输出
				else if(UiUtil.getCodeByValue("EXPTYPE", "1").getId() == mode&&UiUtil.getCodeByValue("EXPDIRECTION", "0").getId()==direction){
					cxActionObject.setType(TypeAndActionUtil.TYPE_EXPMAPPINGELSPOUTPUT);
					cxActionObject.setCostoexpObject((CostoexpObject)this.getCostoexpObject(qosMappingMode));
				}
				//VLANPRI_COLOR
				else if(QosTemplateTypeEnum.VLANPRI_COLOR.getValue() == qosMappingMode.getType()){
					cxActionObject.setType(TypeAndActionUtil.TYPE_MAPPINGVLANPRITOCOLOR);
					cxActionObject.setVlanpri2cngObject((Vlanpri2cngObject)this.getVlanpri2cngObject(qosMappingMode));
				}
				//COS_VLANPRI
				else if(QosTemplateTypeEnum.COS_VLANPRI.getValue() == qosMappingMode.getType()){
					cxActionObject.setType(TypeAndActionUtil.TYPE_MAPPINGCOSTOVLANPRI);
					cxActionObject.setCos2vlanpriObject((Cos2vlanpriObject)this.getCos2vlanpriObject(qosMappingMode));
				}
			}else{
				cxActionObject.setType(TypeAndActionUtil.TYPE_EXPMAPPINGLLSPINPUT);
			}
			operationObject.getCxActionObjectList().add(cxActionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return operationObject;
	}
	
	private Cos2vlanpriObject getCos2vlanpriObject(QosMappingMode qosMappingMode) {
		Cos2vlanpriObject cos2vlanpriObject = new Cos2vlanpriObject();
		cos2vlanpriObject.setName(qosMappingMode.getBusinessId()+"");
		for(QosMappingAttr qosMappingAttr:qosMappingMode.getQosMappingAttrList()){
			if(QosCosLevelEnum.from("BE")==Integer.parseInt(qosMappingAttr.getGrade())){
				cos2vlanpriObject.setBe(qosMappingAttr.getValue()+"");
			}else if(QosCosLevelEnum.from("AF1")==Integer.parseInt(qosMappingAttr.getGrade())){
				cos2vlanpriObject.setAf1(qosMappingAttr.getValue()+"");
			}else if(QosCosLevelEnum.from("AF2")==Integer.parseInt(qosMappingAttr.getGrade())){
				cos2vlanpriObject.setAf2(qosMappingAttr.getValue()+"");
			}else if(QosCosLevelEnum.from("AF3")==Integer.parseInt(qosMappingAttr.getGrade())){
				cos2vlanpriObject.setAf3(qosMappingAttr.getValue()+"");
			}else if(QosCosLevelEnum.from("AF4")==Integer.parseInt(qosMappingAttr.getGrade())){
				cos2vlanpriObject.setAf4(qosMappingAttr.getValue()+"");
			}else if(QosCosLevelEnum.from("EF")==Integer.parseInt(qosMappingAttr.getGrade())){
				cos2vlanpriObject.setEf(qosMappingAttr.getValue()+"");
			}else if(QosCosLevelEnum.from("CS6")==Integer.parseInt(qosMappingAttr.getGrade())){
				cos2vlanpriObject.setCs6(qosMappingAttr.getValue()+"");
			}else if(QosCosLevelEnum.from("CS7")==Integer.parseInt(qosMappingAttr.getGrade())){
				cos2vlanpriObject.setCs7(qosMappingAttr.getValue()+"");
			}
		}
		return cos2vlanpriObject;
	}
	private Vlanpri2cngObject getVlanpri2cngObject(QosMappingMode qosMappingMode) {
		Vlanpri2cngObject vlanpri2cngObject = new Vlanpri2cngObject();
		vlanpri2cngObject.setName(qosMappingMode.getBusinessId()+"");
		for(QosMappingAttr qosMappingAttr:qosMappingMode.getQosMappingAttrList()){
			if(0==qosMappingAttr.getValue()){
				vlanpri2cngObject.setVlanpri0(qosMappingAttr.getColor()+"");
			}else if(1==qosMappingAttr.getValue()){
				vlanpri2cngObject.setVlanpri1(qosMappingAttr.getColor()+"");
			}else if(2==qosMappingAttr.getValue()){
				vlanpri2cngObject.setVlanpri2(qosMappingAttr.getColor()+"");
			}else if(3==qosMappingAttr.getValue()){
				vlanpri2cngObject.setVlanpri3(qosMappingAttr.getColor()+"");
			}else if(4==qosMappingAttr.getValue()){
				vlanpri2cngObject.setVlanpri4(qosMappingAttr.getColor()+"");
			}else if(5==qosMappingAttr.getValue()){
				vlanpri2cngObject.setVlanpri5(qosMappingAttr.getColor()+"");
			}else if(6==qosMappingAttr.getValue()){
				vlanpri2cngObject.setVlanpri6(qosMappingAttr.getColor()+"");
			}else if(7==qosMappingAttr.getValue()){
				vlanpri2cngObject.setVlanpri7(qosMappingAttr.getColor()+"");
			}
		}
		return vlanpri2cngObject;
	}
	/**
	 * 转换CostoexpObject对象 （ELSP输出）
	 * @param qosMappingMode
	 * @return
	 */
	private CostoexpObject getCostoexpObject(QosMappingMode qosMappingMode) {
		CostoexpObject costoexpObject = new CostoexpObject();
		costoexpObject.setName(qosMappingMode.getBusinessId()+"");
		for(QosMappingAttr qosMappingAttr:qosMappingMode.getQosMappingAttrList()){
			if(QosCosLevelEnum.from("BE")==Integer.parseInt(qosMappingAttr.getGrade())){
				costoexpObject.setBe(qosMappingAttr.getValue()+"");
			}else if(QosCosLevelEnum.from("AF1")==Integer.parseInt(qosMappingAttr.getGrade())){
				costoexpObject.setAf1(qosMappingAttr.getValue()+"");
			}else if(QosCosLevelEnum.from("AF2")==Integer.parseInt(qosMappingAttr.getGrade())){
				costoexpObject.setAf2(qosMappingAttr.getValue()+"");
			}else if(QosCosLevelEnum.from("AF3")==Integer.parseInt(qosMappingAttr.getGrade())){
				costoexpObject.setAf3(qosMappingAttr.getValue()+"");
			}else if(QosCosLevelEnum.from("AF4")==Integer.parseInt(qosMappingAttr.getGrade())){
				costoexpObject.setAf4(qosMappingAttr.getValue()+"");
			}else if(QosCosLevelEnum.from("EF")==Integer.parseInt(qosMappingAttr.getGrade())){
				costoexpObject.setEf(qosMappingAttr.getValue()+"");
			}else if(QosCosLevelEnum.from("CS6")==Integer.parseInt(qosMappingAttr.getGrade())){
				costoexpObject.setCs6(qosMappingAttr.getValue()+"");
			}else if(QosCosLevelEnum.from("CS7")==Integer.parseInt(qosMappingAttr.getGrade())){
				costoexpObject.setCs7(qosMappingAttr.getValue()+"");
			}
		}
		return costoexpObject;
	}
	/**
	 * 转换ExptocosObject对象 （ELSP输入）
	 * @param qosMappingMode
	 * @return
	 */
	private ExptocosObject getExptocosObject(QosMappingMode qosMappingMode) {
		ExptocosObject exptocosObject = new ExptocosObject();
		exptocosObject.setName(qosMappingMode.getBusinessId()+"");
		for(QosMappingAttr qosMappingAttr:qosMappingMode.getQosMappingAttrList()){
			if(0==qosMappingAttr.getValue()){
				exptocosObject.setExp0(qosMappingAttr.getGrade());
			}else if(1==qosMappingAttr.getValue()){
				exptocosObject.setExp1(qosMappingAttr.getGrade());
			}else if(2==qosMappingAttr.getValue()){
				exptocosObject.setExp2(qosMappingAttr.getGrade());
			}else if(3==qosMappingAttr.getValue()){
				exptocosObject.setExp3(qosMappingAttr.getGrade());
			}else if(4==qosMappingAttr.getValue()){
				exptocosObject.setExp4(qosMappingAttr.getGrade());
			}else if(5==qosMappingAttr.getValue()){
				exptocosObject.setExp5(qosMappingAttr.getGrade());
			}else if(6==qosMappingAttr.getValue()){
				exptocosObject.setExp6(qosMappingAttr.getGrade());
			}else if(7==qosMappingAttr.getValue()){
				exptocosObject.setExp7(qosMappingAttr.getGrade());
			}
		}
		return exptocosObject;
	}
	/**
	 * 转换ExptoclrObject对象  (LLSP输出)
	 * @param qosMappingMode
	 * 			qosMappingMode对象
	 * @return
	 */
	private ClrtoexpObject getClrtoexpObject(QosMappingMode qosMappingMode) {
		ClrtoexpObject clrtoexpObject = new ClrtoexpObject();
		clrtoexpObject.setName(qosMappingMode.getBusinessId()+"");
		for(QosMappingAttr qosMappingAttr:qosMappingMode.getQosMappingAttrList()){
			if(EMappingColorEnum.YELLOW.getValue()==qosMappingAttr.getColor()){
				clrtoexpObject.setYellow(qosMappingAttr.getValue()+"");
			}else{
				clrtoexpObject.setGreen(qosMappingAttr.getValue()+"");
			}
		}
		
		return clrtoexpObject;
	}
	/**
	 * 转换ExptoclrObject对象  (LLSP输入)
	 * @param qosMappingMode 
	 * 				qosMappingMode对象
	 * @return
	 */
	private ExptoclrObject getExptoclrObject(QosMappingMode qosMappingMode) {
		ExptoclrObject exptoclrObject = new ExptoclrObject();
		exptoclrObject.setName(qosMappingMode.getBusinessId()+"");
		for(QosMappingAttr qosMappingAttr:qosMappingMode.getQosMappingAttrList()){
			if(0==qosMappingAttr.getValue()){
				exptoclrObject.setExp0(qosMappingAttr.getColor()+"");
			}else if(1==qosMappingAttr.getValue()){
				exptoclrObject.setExp1(qosMappingAttr.getColor()+"");
			}else if(2==qosMappingAttr.getValue()){
				exptoclrObject.setExp2(qosMappingAttr.getColor()+"");
			}else if(3==qosMappingAttr.getValue()){
				exptoclrObject.setExp3(qosMappingAttr.getColor()+"");
			}else if(4==qosMappingAttr.getValue()){
				exptoclrObject.setExp4(qosMappingAttr.getColor()+"");
			}else if(5==qosMappingAttr.getValue()){
				exptoclrObject.setExp5(qosMappingAttr.getColor()+"");
			}else if(6==qosMappingAttr.getValue()){
				exptoclrObject.setExp6(qosMappingAttr.getColor()+"");
			}else if(7==qosMappingAttr.getValue()){
				exptoclrObject.setExp7(qosMappingAttr.getColor()+"");
			}
		}
		return exptoclrObject;
	}
}

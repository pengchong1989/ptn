package com.nms.service.impl.cx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.path.eth.EtreeInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EManufacturer;
import com.nms.db.enums.EServiceType;
import com.nms.drivechenxiao.service.bean.porteth.EthPortObject;
import com.nms.drivechenxiao.service.bean.porteth.ac.AcObject;
import com.nms.drivechenxiao.service.bean.pweth.PwEthObject;
import com.nms.drivechenxiao.service.bean.service.etree.ETreeObject;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.ptn.path.eth.EtreeInfoService_MB;
import com.nms.model.ptn.port.AcPortInfoService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.CXActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.CXOperationBase;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.SynchroUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DateUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class EtreeCXServiceImpl extends CXOperationBase implements OperationServiceI {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String excutionDelete(List objectList) throws Exception {
		List<EtreeInfo> etreeinfoList = null;
		OperationObject operationObject = null;
		String errorMessage = null;
		try {
			etreeinfoList = objectList;
			operationObject = this.getOperationObject(etreeinfoList, TypeAndActionUtil.ACTION_DELETE);
			if (operationObject.getCxActionObjectList().size() > 0) {
				super.sendAction(operationObject);
				operationObject = super.verification(operationObject);

				if (operationObject.isSuccess()) {
					
					errorMessage = operationObject.getCxActionObjectList().get(0).getStatus();
				} else {
					errorMessage = super.getErrorMessage(operationObject);
					operationObject.setCxActionObjectList(super.getSuccess(operationObject, TypeAndActionUtil.ACTION_INSERT));
					super.sendAction(operationObject);
				}
			} else {
				errorMessage = ResultString.CONFIG_SUCCESS;
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			etreeinfoList = null;
			operationObject = null;
		}
		return errorMessage;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String excutionInsert(Object object) throws Exception {
		List<EtreeInfo> etreeInfoList = null;
		OperationObject operationObject = null;
		String errorMessage = null;
		try {
			etreeInfoList = (List<EtreeInfo>) object;

			operationObject = this.getOperationObject(etreeInfoList, TypeAndActionUtil.ACTION_INSERT);
			// 如果有晨晓的配置 下发设备。 否则返回提示
			if (operationObject.getCxActionObjectList().size() > 0) {
				super.sendAction(operationObject);
				operationObject = super.verification(operationObject);
				if (operationObject.isSuccess()) {
					errorMessage = operationObject.getCxActionObjectList().get(0).getStatus();
				} else {
					errorMessage = super.getErrorMessage(operationObject);
					operationObject.setCxActionObjectList(super.getSuccess(operationObject, TypeAndActionUtil.ACTION_DELETE));
					super.sendAction(operationObject);
				}
			} else {
				errorMessage = ResultString.CONFIG_SUCCESS;
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			etreeInfoList = null;
			operationObject = null;
		}
		return errorMessage;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String excutionUpdate(Object object) throws Exception {
		List<EtreeInfo> etreeinfoList = null;
		OperationObject operationObject = null;
		String result = null;
		String action=null;
		List<EtreeInfo> deleteList = new ArrayList<EtreeInfo>();
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		try {
			etreeinfoList = (List<EtreeInfo>) object;
			
			if(etreeinfoList.size()>0){
				
				//action全部 = 0  并且未激活状态 没有修改操作  执行激活或者去激活
				if(etreeinfoList.get(0).getActiveStatus() == EActiveStatus.ACTIVITY.getValue()){ //激活去激活
					if(etreeinfoList.get(0).getBeforeActiveStatus() == EActiveStatus.UNACTIVITY.getValue()){
						action=TypeAndActionUtil.ACTION_INSERT;
					}else{
						action=TypeAndActionUtil.ACTION_UPDATE;
					}
				}else{ //激活：修改
					if(etreeinfoList.get(0).getBeforeActiveStatus() == EActiveStatus.ACTIVITY.getValue()){
						for(EtreeInfo obj:etreeinfoList){ 
							obj.setAction(3);
							//恢复修改前数据下发设备
							if(null!=obj.getBeforeRootAc()){
//								map.put(obj.getBeforeRootAc().getId(), obj.getaAcId());
//								obj.setaAcId(obj.getBeforeRootAc().getId());
							}
							if(null!=obj.getBeforeBranchAc()){
//								map.put(obj.getBeforeBranchAc().getId(), obj.getzAcId());
//								obj.setzAcId(obj.getBeforeBranchAc().getId());
							}
							if(2==obj.getAction()){ //验证是否有添加操作，如果是在集合中删除
								deleteList.add(obj);
							}
						}
						action=TypeAndActionUtil.ACTION_DELETE;
						for(EtreeInfo etreeinfo:deleteList){
							etreeinfoList.remove(etreeinfo); //如果有添加操作  删除添加操作
						}
					}else{ //未激活：修改
						return result = ResultString.CONFIG_SUCCESS;
					}
				}
				operationObject = this.getOperationObject(etreeinfoList, action);
				if (operationObject.getCxActionObjectList().size() > 0) {
					super.sendAction(operationObject);
					operationObject = super.verification(operationObject);
					if (operationObject.isSuccess()) {
						result = operationObject.getCxActionObjectList().get(0).getStatus();
					} else {
						result = super.getErrorMessage(operationObject);
					}
				} else {
					result = ResultString.CONFIG_SUCCESS;
				}
			}
			//恢复修改后数据，用于入库
			if(etreeinfoList.get(0).getActiveStatus() == EActiveStatus.UNACTIVITY.getValue()&&etreeinfoList.get(0).getBeforeActiveStatus() == EActiveStatus.ACTIVITY.getValue()){ //激活去激活
				for(EtreeInfo obj :etreeinfoList){
					obj.setAction(0);
					if(null!=map.get(obj.getaAcId())){
						obj.setaAcId(map.get(obj.getaAcId()));
					}
					if(null!=map.get(obj.getzAcId())){
						obj.setzAcId(map.get(obj.getzAcId()));
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
			result = super.getErrorMessage(operationObject);
		} finally {
			etreeinfoList = null;
			operationObject = null;
			action=null;
		}
		return result;
	}

	private OperationObject getOperationObject_Update(
			List<EtreeInfo> etreeInfoList, String action) {

		CXActionObject rootcxActionObject = null;
		CXActionObject branchcxActionObject = null;
		OperationObject operationObject = null;
		EtreeInfo etreeinfo = null;
		Map<Integer, CXActionObject> map = null;
		PwEthObject pwEthObject = null;
		int siteId=0;
		try {
			operationObject = new OperationObject();
			map = new HashMap<Integer, CXActionObject>();
			SiteUtil siteUtil=new SiteUtil();
			for (int i = 0; i < etreeInfoList.size(); i++) {
				etreeinfo = etreeInfoList.get(i);
				//网络侧
				if(etreeinfo.getIsSingle() == 0&&etreeinfo.getAction()!=0){
					if (super.getManufacturer(etreeinfo.getRootSite()) == EManufacturer.CHENXIAO.getValue()&&0==siteUtil.SiteTypeUtil(etreeinfo.getRootSite())) {
						if(etreeinfo.getAction()!=0||(null!=etreeinfo.getBeforePw()||null!=etreeinfo.getBeforeRootAc())){
							pwEthObject = this.convertPwEthObject(etreeinfo, TypeAndActionUtil.ACTION_ROOT);
							if (null == map.get(etreeinfo.getServiceId())) {
								rootcxActionObject = new CXActionObject();
								rootcxActionObject.setCxNeObject(super.getCXNEObject(etreeinfo.getRootSite()));
								rootcxActionObject.setAcObject(this.convertAcObject(etreeinfo, TypeAndActionUtil.ACTION_ROOT,action));
								rootcxActionObject.getPwEthObjectList().add(pwEthObject);
								rootcxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
								rootcxActionObject.setAction(TypeAndActionUtil.ACTION_UPDATE);
								rootcxActionObject.setType(TypeAndActionUtil.TYPE_ETREE);
								rootcxActionObject.setEthPortObject(this.convertEthPortObject(etreeinfo, TypeAndActionUtil.ACTION_ROOT));
								rootcxActionObject.setEtreeObject(this.convertEtreeObject(etreeinfo, TypeAndActionUtil.ACTION_ROOT,rootcxActionObject.getPwEthObjectList()));
								map.put(etreeinfo.getServiceId(), rootcxActionObject);
							} else {
								map.get(etreeinfo.getServiceId()).getPwEthObjectList().add(this.convertPwEthObject(etreeinfo, TypeAndActionUtil.ACTION_ROOT));
							}
							if(null!=etreeinfo.getBeforeRootAc()){
								rootcxActionObject.setAcObject(this.convertAcObject(etreeinfo, TypeAndActionUtil.ACTION_ROOT,action));
								rootcxActionObject.setEthPortObject(this.convertEthPortObject(etreeinfo, TypeAndActionUtil.ACTION_ROOT));
							}
							if(etreeinfo.getAction() == 2){
								rootcxActionObject.getEtreeObject().getPwInsert().add(pwEthObject);
							}else if(etreeinfo.getAction() == 3){
								rootcxActionObject.getEtreeObject().getPwDelete().add(pwEthObject);
							}
							//更换PW
							pwChange(rootcxActionObject,etreeinfo,TypeAndActionUtil.ACTION_ROOT);
						}
					}
					if (super.getManufacturer(etreeinfo.getBranchSite()) == EManufacturer.CHENXIAO.getValue()&&0==siteUtil.SiteTypeUtil(etreeinfo.getBranchSite())) {
						if(etreeinfo.getAction()!=0||(null!=etreeinfo.getBeforeBranchAc()&&null!=etreeinfo.getBeforePw())){
							branchcxActionObject = new CXActionObject();
							branchcxActionObject.setCxNeObject(super.getCXNEObject(etreeinfo.getBranchSite()));
							branchcxActionObject.setAcObject(this.convertAcObject(etreeinfo, TypeAndActionUtil.ACTION_BRANCH,action));
							branchcxActionObject.getPwEthObjectList().add(this.convertPwEthObject(etreeinfo, TypeAndActionUtil.ACTION_BRANCH));
							branchcxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
							if(null!=etreeinfo.getBeforeBranchAc()||null!=etreeinfo.getBeforePw()){
								branchcxActionObject.setAction(TypeAndActionUtil.ACTION_UPDATE);
							}else if(etreeinfo.getAction() == 2){
								branchcxActionObject.setAction(TypeAndActionUtil.ACTION_INSERT);
							}else if(etreeinfo.getAction() == 3){
								branchcxActionObject.setAction(TypeAndActionUtil.ACTION_DELETE);
							}else{
								branchcxActionObject.setAction(action);
							}
							branchcxActionObject.setType(TypeAndActionUtil.TYPE_ETREE);
							branchcxActionObject.setEthPortObject(this.convertEthPortObject(etreeinfo, TypeAndActionUtil.ACTION_BRANCH));
							branchcxActionObject.setEtreeObject(this.convertEtreeObject(etreeinfo, TypeAndActionUtil.ACTION_BRANCH,null));
							operationObject.getCxActionObjectList().add(branchcxActionObject);
						}
						//更换PW
						pwChange(rootcxActionObject,etreeinfo,TypeAndActionUtil.ACTION_BRANCH);
					}
				}
				
				if(etreeinfo.getIsSingle() == 1&&etreeinfo.getAction()!=0){
					if(etreeinfo.getRootSite() != 0){
						siteId=etreeinfo.getRootSite();
					}else{
						siteId=etreeinfo.getBranchSite();
					}
					
					if (super.getManufacturer(siteId) == EManufacturer.CHENXIAO.getValue()&&0==siteUtil.SiteTypeUtil(siteId)) {
						if (etreeinfo.getRootSite() != 0) {
							if (null == map.get(etreeinfo.getServiceId())) {
								rootcxActionObject = new CXActionObject();
								rootcxActionObject.setCxNeObject(super.getCXNEObject(etreeinfo.getRootSite()));
								rootcxActionObject.setAction(action);
								rootcxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
								rootcxActionObject.setType(TypeAndActionUtil.TYPE_ETREE);
								rootcxActionObject.setEtreeObject(this.convertEtreeObject(etreeinfo, TypeAndActionUtil.ACTION_ROOT,null));
								//判断是否为离线网元数据下载并且操作
								offLineSiteAcion(etreeinfo,rootcxActionObject,action,TypeAndActionUtil.ACTION_ROOT);	
								map.put(etreeinfo.getServiceId(), rootcxActionObject);
							} else {
								offLineSitePwAction(etreeinfo,rootcxActionObject,map,action,TypeAndActionUtil.ACTION_ROOT);
							}
							pwEthObject = this.convertPwEthObject(etreeinfo, TypeAndActionUtil.ACTION_ROOT);
							if(null!=etreeinfo.getBeforeRootAc()){
								rootcxActionObject.setAcObject(this.convertAcObject(etreeinfo, TypeAndActionUtil.ACTION_ROOT,action));
								rootcxActionObject.setEthPortObject(this.convertEthPortObject(etreeinfo, TypeAndActionUtil.ACTION_ROOT));
							}
							if(etreeinfo.getAction() == 2){
								rootcxActionObject.getEtreeObject().getPwInsert().add(pwEthObject);
							}else if(etreeinfo.getAction() == 3){
								rootcxActionObject.getEtreeObject().getPwDelete().add(pwEthObject);
							}
						} else {
							branchcxActionObject = new CXActionObject();
							branchcxActionObject.setCxNeObject(super.getCXNEObject(etreeinfo.getBranchSite()));
							branchcxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
							branchcxActionObject.setAction(action);
							branchcxActionObject.setType(TypeAndActionUtil.TYPE_ETREE);
							branchcxActionObject.setEtreeObject(this.convertEtreeObject(etreeinfo, TypeAndActionUtil.ACTION_BRANCH,null));
							//判断是否为离线网元数据下载并且操作
							offLineSiteAcion(etreeinfo,branchcxActionObject,action,TypeAndActionUtil.ACTION_BRANCH);	
							operationObject.getCxActionObjectList().add(branchcxActionObject);
						}
					}
				}
			}
			for (Map.Entry<Integer, CXActionObject> entrySet : map.entrySet()) {
				operationObject.getCxActionObjectList().add(entrySet.getValue());
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			rootcxActionObject = null;
			branchcxActionObject = null;
			etreeinfo = null;
		}
		return operationObject;
	
	}
	
	/**
	 * 更换pW
	 * @param rootcxActionObject  
	 * @param etreeinfo  etreeinfo对象
	 * @param type 根节点 、叶子节点
	 * @throws Exception 
	 */
	private void pwChange(CXActionObject cxActionObject,
			EtreeInfo etreeinfo, String type) throws Exception {
		if(etreeinfo.getAction()==2||etreeinfo.getAction()==3){
			EtreeInfo etreepwUpdate = new EtreeInfo();
			if(null!=etreeinfo.getBeforePw()){
				etreepwUpdate.setPwId(etreeinfo.getBeforePw().getPwId());
				cxActionObject.getEtreeObject().getPwInsert().add(this.convertPwEthObject(etreeinfo, type));
				cxActionObject.getEtreeObject().getPwDelete().add(convertPwEthObject(etreepwUpdate, type));
			}
			
		}
	}

	/**
	 * 获取CXActionObject对象
	 * 
	 * @param operationObject
	 *            operationObject对象
	 * @param etreeinfo
	 *            etreeinfo对象 数据库实体bean
	 * @param action
	 *            动作
	 * @return
	 * @throws Exception
	 */
	private OperationObject getOperationObject(List<EtreeInfo> etreeInfoList, String action) throws Exception {
		CXActionObject rootcxActionObject = null;
		CXActionObject branchcxActionObject = null;
		OperationObject operationObject = null;
		EtreeInfo etreeinfo = null;
		Map<Integer, CXActionObject> map = null;
		int siteId=0;
		PwEthObject pwEthObject = null;
		try {
			operationObject = new OperationObject();
			map = new HashMap<Integer, CXActionObject>();
			SiteUtil siteUtil=new SiteUtil();
			for (int i = 0; i < etreeInfoList.size(); i++) {
				etreeinfo = etreeInfoList.get(i);
				//网络侧
				if (etreeinfo.getIsSingle() == 0) {
					if (super.getManufacturer(etreeinfo.getRootSite()) == EManufacturer.CHENXIAO.getValue()&&0==siteUtil.SiteTypeUtil(etreeinfo.getRootSite())) {
						pwEthObject = this.convertPwEthObject(etreeinfo, TypeAndActionUtil.ACTION_ROOT);
						if (null == map.get(etreeinfo.getServiceId())) {
							rootcxActionObject = new CXActionObject();
							rootcxActionObject.setCxNeObject(super.getCXNEObject(etreeinfo.getRootSite()));
							rootcxActionObject.setAcObject(this.convertAcObject(etreeinfo, TypeAndActionUtil.ACTION_ROOT,action));
							rootcxActionObject.getPwEthObjectList().add(pwEthObject);
							rootcxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
							rootcxActionObject.setAction(action);
							rootcxActionObject.setType(TypeAndActionUtil.TYPE_ETREE);
							rootcxActionObject.setEthPortObject(this.convertEthPortObject(etreeinfo, TypeAndActionUtil.ACTION_ROOT));
							rootcxActionObject.setEtreeObject(this.convertEtreeObject(etreeinfo, TypeAndActionUtil.ACTION_ROOT,null));
							map.put(etreeinfo.getServiceId(), rootcxActionObject);
						} else {
							map.get(etreeinfo.getServiceId()).getPwEthObjectList().add(this.convertPwEthObject(etreeinfo, TypeAndActionUtil.ACTION_ROOT));
						}
						if(null!=etreeinfo.getBeforeRootAc()){
							rootcxActionObject.setAcObject(this.convertAcObject(etreeinfo, TypeAndActionUtil.ACTION_ROOT,action));
							rootcxActionObject.setEthPortObject(this.convertEthPortObject(etreeinfo, TypeAndActionUtil.ACTION_ROOT));
						}
						if(etreeinfo.getAction() == 2){
							rootcxActionObject.getEtreeObject().getPwInsert().add(pwEthObject);
						}else if(etreeinfo.getAction() == 3){
							rootcxActionObject.getEtreeObject().getPwDelete().add(pwEthObject);
						}
						//更换PW
						pwChange(rootcxActionObject,etreeinfo,TypeAndActionUtil.ACTION_ROOT);
					}
					if (super.getManufacturer(etreeinfo.getBranchSite()) == EManufacturer.CHENXIAO.getValue()&&0==siteUtil.SiteTypeUtil(etreeinfo.getBranchSite())) {
						branchcxActionObject = new CXActionObject();
						branchcxActionObject.setCxNeObject(super.getCXNEObject(etreeinfo.getBranchSite()));
						branchcxActionObject.setAcObject(this.convertAcObject(etreeinfo, TypeAndActionUtil.ACTION_BRANCH,action));
						branchcxActionObject.getPwEthObjectList().add(this.convertPwEthObject(etreeinfo, TypeAndActionUtil.ACTION_BRANCH));
						branchcxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
						if(null!=etreeinfo.getBeforeBranchAc()||null!=etreeinfo.getBeforePw()){
							branchcxActionObject.setAction(TypeAndActionUtil.ACTION_UPDATE);
						}else if(etreeinfo.getAction() == 2){
							branchcxActionObject.setAction(TypeAndActionUtil.ACTION_INSERT);
						}else if(etreeinfo.getAction() == 3){
							branchcxActionObject.setAction(TypeAndActionUtil.ACTION_DELETE);
						}else{
							branchcxActionObject.setAction(action);
						}
						branchcxActionObject.setType(TypeAndActionUtil.TYPE_ETREE);
						branchcxActionObject.setEthPortObject(this.convertEthPortObject(etreeinfo, TypeAndActionUtil.ACTION_BRANCH));
						branchcxActionObject.setEtreeObject(this.convertEtreeObject(etreeinfo, TypeAndActionUtil.ACTION_BRANCH,null));
						operationObject.getCxActionObjectList().add(branchcxActionObject);
						//更换PW
						pwChange(rootcxActionObject,etreeinfo,TypeAndActionUtil.ACTION_BRANCH);
					}
				} else {//单网络侧
					if(etreeinfo.getRootSite() != 0){
						siteId=etreeinfo.getRootSite();
					}else{
						siteId=etreeinfo.getBranchSite();
					}
					
					if (super.getManufacturer(siteId) == EManufacturer.CHENXIAO.getValue()&&0==siteUtil.SiteTypeUtil(siteId)) {
						if (etreeinfo.getRootSite() != 0) {
							if (null == map.get(etreeinfo.getServiceId())) {
								rootcxActionObject = new CXActionObject();
								rootcxActionObject.setCxNeObject(super.getCXNEObject(etreeinfo.getRootSite()));
								rootcxActionObject.setAction(action);
								rootcxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
								rootcxActionObject.setType(TypeAndActionUtil.TYPE_ETREE);
								rootcxActionObject.setEtreeObject(this.convertEtreeObject(etreeinfo, TypeAndActionUtil.ACTION_ROOT,null));
								//判断是否为离线网元数据下载并且操作
								offLineSiteAcion(etreeinfo,rootcxActionObject,action,TypeAndActionUtil.ACTION_ROOT);	
						/*		rootcxActionObject.setEthPortObject(this.convertEthPortObject(etreeinfo, TypeAndActionUtil.ACTION_ROOT));
								rootcxActionObject.getPwEthObjectList().add(this.convertPwEthObject(etreeinfo, TypeAndActionUtil.ACTION_ROOT));
								rootcxActionObject.setAcObject(this.convertAcObject(etreeinfo, TypeAndActionUtil.ACTION_ROOT));*/
								map.put(etreeinfo.getServiceId(), rootcxActionObject);
							} else {
								offLineSitePwAction(etreeinfo,rootcxActionObject,map,action,TypeAndActionUtil.ACTION_ROOT);
								/*rootcxActionObject.getPwEthObjectList().add(this.convertPwEthObject(etreeinfo, TypeAndActionUtil.ACTION_ROOT));*/
							}
							pwEthObject = this.convertPwEthObject(etreeinfo, TypeAndActionUtil.ACTION_ROOT);
							if(null!=etreeinfo.getBeforeRootAc()){
								rootcxActionObject.setAcObject(this.convertAcObject(etreeinfo, TypeAndActionUtil.ACTION_ROOT,action));
								rootcxActionObject.setEthPortObject(this.convertEthPortObject(etreeinfo, TypeAndActionUtil.ACTION_ROOT));
							}
							if(etreeinfo.getAction() == 2){
								rootcxActionObject.getEtreeObject().getPwInsert().add(pwEthObject);
							}else if(etreeinfo.getAction() == 3){
								rootcxActionObject.getEtreeObject().getPwDelete().add(pwEthObject);
							}
						} else {
							branchcxActionObject = new CXActionObject();
							branchcxActionObject.setCxNeObject(super.getCXNEObject(etreeinfo.getBranchSite()));
							branchcxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
							branchcxActionObject.setAction(action);
							branchcxActionObject.setType(TypeAndActionUtil.TYPE_ETREE);
							branchcxActionObject.setEtreeObject(this.convertEtreeObject(etreeinfo, TypeAndActionUtil.ACTION_BRANCH,null));
							//判断是否为离线网元数据下载并且操作
							offLineSiteAcion(etreeinfo,branchcxActionObject,action,TypeAndActionUtil.ACTION_BRANCH);	
						/*	branchcxActionObject.setEthPortObject(this.convertEthPortObject(etreeinfo, TypeAndActionUtil.ACTION_BRANCH));
							branchcxActionObject.setAcObject(this.convertAcObject(etreeinfo, TypeAndActionUtil.ACTION_BRANCH));*/
							operationObject.getCxActionObjectList().add(branchcxActionObject);
						}
					}
				}
			}
			for (Map.Entry<Integer, CXActionObject> entrySet : map.entrySet()) {
				operationObject.getCxActionObjectList().add(entrySet.getValue());
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			rootcxActionObject = null;
			branchcxActionObject = null;
			etreeinfo = null;
		}
		return operationObject;
	}

	/**
	 * 获取ETreeObject对象
	 * 
	 * @param etreeinfo
	 *            etreeinfo对象 数据库实体bean
	 * @return
	 * @throws Exception
	 */
	private ETreeObject convertEtreeObject(EtreeInfo etreeinfo, String type,List<PwEthObject> pwEthObjectList) throws Exception {
		ETreeObject etreeobject = null;
		try {
			etreeobject = new ETreeObject();

			if (type == TypeAndActionUtil.ACTION_ROOT) {
				etreeobject.setName(etreeinfo.getaXcId() + "");
			} else {
				etreeobject.setName(etreeinfo.getzXcId() + "");
			}

			if (etreeinfo.getActiveStatus() == EActiveStatus.ACTIVITY.getValue()) {
				etreeobject.setAdmin("up");
			} else {
				etreeobject.setAdmin("down");
			}
			
			if(TypeAndActionUtil.ACTION_ROOT.equals(type)){
				etreeobject.setFlag(true); //设备判断ac是根节点还是叶子节点
			}else{
				etreeobject.setFlag(false); //设备判断ac是根节点还是叶子节点
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return etreeobject;
	}

	/**
	 * 获得PW对象
	 * 
	 * @param etreeInfoList
	 * @param type
	 * @return
	 * @throws Exception
	 */
	private PwEthObject convertPwEthObject(EtreeInfo etreeinfo, String type) throws Exception {
		PwInfo pwInfo = null;
		PwEthObject pwethobject = null;
		PwInfo pwBefore;
		try {

			pwethobject = new PwEthObject();
			pwBefore = etreeinfo.getBeforePw();//修改前pw
			pwInfo = super.getPwinfo(etreeinfo.getPwId());
			if (type.equals(TypeAndActionUtil.ACTION_ROOT)) {
				if (etreeinfo.getRootSite() == pwInfo.getASiteId()) {
					pwethobject.setName(pwInfo.getApwServiceId() + "");
					pwethobject.getEtree().setType(TypeAndActionUtil.TYPE_ETREE);
					pwethobject.getEtree().setVpn(etreeinfo.getaXcId() + "");
					pwethobject.getEtree().setRole(TypeAndActionUtil.ACTION_BRANCH);
				} else {
					pwethobject.setName(pwInfo.getZpwServiceId() + "");
					pwethobject.getEtree().setType(TypeAndActionUtil.TYPE_ETREE);
					pwethobject.getEtree().setVpn(etreeinfo.getaXcId() + "");
					pwethobject.getEtree().setRole(TypeAndActionUtil.ACTION_BRANCH);
				}
			} else {
				if (etreeinfo.getBranchSite() == pwInfo.getASiteId()) {
					pwethobject.setName(pwInfo.getApwServiceId() + "");
					pwethobject.getEtree().setType(TypeAndActionUtil.TYPE_ETREE);
					pwethobject.getEtree().setVpn(etreeinfo.getzXcId() + "");
					pwethobject.getEtree().setRole(TypeAndActionUtil.ACTION_ROOT);
				} else {
					pwethobject.setName(pwInfo.getZpwServiceId() + "");
					pwethobject.getEtree().setType(TypeAndActionUtil.TYPE_ETREE);
					pwethobject.getEtree().setVpn(etreeinfo.getzXcId() + "");
					pwethobject.getEtree().setRole(TypeAndActionUtil.ACTION_ROOT);
				}
			}
			//给修改前pw赋值
			if(null!=pwBefore){
				if (type.equals(TypeAndActionUtil.ACTION_ROOT)) {
					if (etreeinfo.getRootSite() == pwInfo.getASiteId()) {
						pwethobject.setIdentify(etreeinfo.getBeforePw().getApwServiceId()+"");
					}else{
						pwethobject.setIdentify(etreeinfo.getBeforePw().getZpwServiceId()+"");
					}
				}else{
					if (etreeinfo.getBranchSite() == pwInfo.getASiteId()) {
						pwethobject.setIdentify(etreeinfo.getBeforePw().getApwServiceId()+"");
					}else{
						pwethobject.setIdentify(etreeinfo.getBeforePw().getZpwServiceId()+"");
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			 pwInfo = null;
		
		}
		return pwethobject;
	}

	private EthPortObject convertEthPortObject(EtreeInfo etreeInfo, String type) throws Exception {
		int acId = 0;
		AcPortInfoService_MB acInfoService = null;
		List<AcPortInfo> acportInfpList = null;
		AcPortInfo acPortInfo = null;
		EthPortObject ethportobject = null;
		AcPortInfo acBefore = null;
		try {
			ethportobject = new EthPortObject();

			if (type == TypeAndActionUtil.ACTION_ROOT) {
				acId = etreeInfo.getaAcId();
//				acBefore = etreeInfo.getBeforeRootAc();
			} else {
				acId = etreeInfo.getzAcId();
//				acBefore = etreeInfo.getBeforeBranchAc();
			}
			if(0!=acId){
				acInfoService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
				acPortInfo = new AcPortInfo();
				acPortInfo.setId(acId);
				acportInfpList = acInfoService.queryByAcPortInfo(acPortInfo);

				acPortInfo = acportInfpList.get(0);
				if(acPortInfo.getPortId()>0){
					ethportobject.setName(super.getPortname(acPortInfo.getPortId()));
					ethportobject.setPortType("eth");
					ethportobject.setIdentify(super.getPortname(acPortInfo.getPortId()));
					ethportobject.setPrevious("eth");
				}else{
					ethportobject.setName(super.getLagName(acPortInfo.getLagId()));
					ethportobject.setPortType("lag");
					ethportobject.setIdentify(super.getLagName(acPortInfo.getLagId()));
					ethportobject.setPrevious("lag");
				}
			}
			//修改前端口赋值
			if(null != acBefore&&0!=acBefore.getAcBusinessId()){
				if(acBefore.getPortId()>0){
					ethportobject.setIdentify(super.getPortname(acBefore.getPortId()));
					ethportobject.setPrevious("eth");
				}else{
					ethportobject.setIdentify(super.getLagName(acBefore.getLagId()));
					ethportobject.setPrevious("lag");
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(acInfoService);
		}
		return ethportobject;
	}

	/**
	 * 获取AcObject
	 * 
	 * @return
	 * @throws Exception
	 */
	private AcObject convertAcObject(EtreeInfo etreeInfo, String type,String action) throws Exception {

		AcPortInfo acPortInfo = null;
		AcPortInfoService_MB acInfoService = null;
		List<AcPortInfo> acportInfpList = null;
		AcObject acObject = null;
		AcPortInfo acBefore;
		try {
			acInfoService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
			acPortInfo = new AcPortInfo();

			acObject = new AcObject();

			if (type == TypeAndActionUtil.ACTION_ROOT) {
				acPortInfo.setId(etreeInfo.getaAcId());
				acObject.getEtree().setVpn(etreeInfo.getaXcId() + "");
				acportInfpList = acInfoService.queryByAcPortInfo(acPortInfo);
				String name = acportInfpList.get(0).getAcBusinessId() + "";
				acObject.getEtree().setRole(TypeAndActionUtil.ACTION_ROOT);
				acObject.setName(name);
				acObject.getEtree().setType(TypeAndActionUtil.TYPE_ETREE);
//				acBefore = etreeInfo.getBeforeRootAc();
				//如果是非修改操作穿现在的acbusinessId
				acObject.setIdentify(name);
			} else {
				String name = null ;
				if(0!=etreeInfo.getzAcId()){
					acPortInfo.setId(etreeInfo.getzAcId());
					acObject.getEtree().setVpn(etreeInfo.getzXcId() + "");
					acportInfpList = acInfoService.queryByAcPortInfo(acPortInfo);
					name = acportInfpList.get(0).getAcBusinessId() + "";
					acObject.getEtree().setRole(TypeAndActionUtil.ACTION_BRANCH);
					acObject.setName(name);
					acObject.getEtree().setType(TypeAndActionUtil.TYPE_ETREE);
				}
				//如果是非修改操作穿现在的acbusinessId
				acObject.setIdentify(name);
//				acBefore = etreeInfo.getBeforeBranchAc();
			}
			
			//修改前AC赋值
//			if(null!=acBefore){
//				acObject.setIdentify(acBefore.getAcBusinessId()+"");
//			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(acInfoService);
		}
		return acObject;
	}

	@Override
	public Object synchro(int siteId) throws Exception {
		OperationObject operationObject = new OperationObject();
		EtreeInfoService_MB etreeservice = null;
		try {
			this.getOperactionObject_select(operationObject, siteId, TypeAndActionUtil.TYPE_ETREE);
			etreeservice = (EtreeInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.EtreeInfo);
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				etreeservice.updateStatusActivate(siteId, EActiveStatus.UNACTIVITY.getValue());
				this.synchro_db(operationObject.getCxActionObjectList().get(0).getEtreeObjectList(), siteId);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(etreeservice);
		}

		return null;
	}

	/**
	 * 获取OperationObject对象 查询用
	 * 
	 * @author kk
	 * 
	 * @param siteId
	 *            网元id
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	private void getOperactionObject_select(OperationObject operationObject, int siteId, String type) throws Exception {
		CXActionObject actionObject = new CXActionObject();

		actionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
		actionObject.setCxNeObject(super.getCXNEObject(siteId));
		actionObject.setType(type);
		actionObject.setAction(TypeAndActionUtil.ACTION_SELECT);
		operationObject.getCxActionObjectList().add(actionObject);

	}

	/**
	 * 与数据库同步
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
	private void synchro_db(List<ETreeObject> etreeObjectList, int siteId) throws Exception {

		if (null == etreeObjectList) {
			throw new Exception("etreeObjectList is null");
		}
		List<EtreeInfo> etreeInfoList = new ArrayList<EtreeInfo>();
		PwInfo pwInfo = null;
		AcPortInfo acPortInfo = null;
		EtreeInfoService_MB etreeService = null;
		try {
			for (ETreeObject etreeObject : etreeObjectList) {
				if (etreeObject.getPortList().getPwList().size() == 0) {
					ExceptionManage.logDebug("设备查询etree，没有查询到pw",this.getClass());
					continue;
				}
				
				EtreeInfo etreeInfo = null;
				
				if (etreeObject.getPortList().getPwList().size() > 1) {
					String etreeName = "etree_" + super.getNowMS();
					for (String pwname : etreeObject.getPortList().getPwList()) {
						etreeInfo = new EtreeInfo();
						// 转换AC对象
						acPortInfo = super.getAcPortInfo(etreeObject.getPortList().getAc(), siteId);
						if (null == acPortInfo) {
							ExceptionManage.logDebug("同步eline时，未同步AC，同步失败",this.getClass());
							continue;
						}
						etreeInfo.setName(etreeName);
						etreeInfo.setServiceType(EServiceType.ETREE.getValue());
						etreeInfo.setActiveStatus(EActiveStatus.ACTIVITY.getValue());
						etreeInfo.setIsSingle(1);
						etreeInfo.setCreateTime(DateUtil.getDate(DateUtil.FULLTIME));
						
						etreeInfo.setaAcId(acPortInfo.getId());
						etreeInfo.setaSiteId(siteId);
						etreeInfo.setRootSite(siteId);
//					etreeInfo.setaXcId(Integer.parseInt(etreeObject.getName()));
						// 转换pw对象
						pwInfo = this.getPwInfo(pwname, siteId);
						if (null == pwInfo) {
							ExceptionManage.logDebug("同步eline时，未同步PW，同步失败",this.getClass());
							continue;
						}
						etreeInfo.setPwId(pwInfo.getPwId());
						etreeInfoList.add(etreeInfo);
					}
					
				} else {
					etreeInfo = new EtreeInfo();
					etreeInfo.setName("etree_" + super.getNowMS());
					etreeInfo.setServiceType(EServiceType.ETREE.getValue());
					etreeInfo.setActiveStatus(EActiveStatus.ACTIVITY.getValue());
					etreeInfo.setIsSingle(1);
					etreeInfo.setCreateTime(DateUtil.getDate(DateUtil.FULLTIME));
					
					// 转换AC对象
					acPortInfo = super.getAcPortInfo(etreeObject.getPortList().getAc(), siteId);
					if (null == acPortInfo) {
						ExceptionManage.logDebug("同步eline时，未同步AC，同步失败",this.getClass());
						continue;
					}
					etreeInfo.setzAcId(acPortInfo.getId());
					etreeInfo.setBranchSite(siteId);
					// etreeObject.getPortList().getPwList().get(0);
					// 转换pw对象
					pwInfo = this.getPwInfo(etreeObject.getPortList().getPwList().get(0), siteId);
					if (null == pwInfo) {
						ExceptionManage.logDebug("同步eline时，未同步PW，同步失败",this.getClass());
						continue;
					}
					etreeInfo.setPwId(pwInfo.getPwId());
					etreeInfo.setzXcId(Integer.parseInt(etreeObject.getName()));
					etreeInfoList.add(etreeInfo);
				}
			}
			if (etreeInfoList.size() > 0) {
				etreeService = (EtreeInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.EtreeInfo);
				SynchroUtil synchroUtil = new SynchroUtil();
				synchroUtil.etreeSynchro(etreeService,etreeInfoList, siteId);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}finally
		{
			UiUtil.closeService_MB(etreeService);
		}
	}
	/**
	 * 离线网元对象转换
	 * @param etreeinfo 
	 * @param cxActionObject
	 * @param action
	 * @param Type
	 * @throws Exception
	 */
	private void offLineSiteAcion(EtreeInfo etreeinfo,CXActionObject cxActionObject,String action,String Type) throws Exception {
		
		PortService_MB portService = null;
		try {
			if(etreeinfo.isDataDownLoad()&&action.equals(TypeAndActionUtil.ACTION_DELETE)){
				portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
				EthPortObject ethportobject = new EthPortObject();
				if(0!=etreeinfo.getPwId()){
					PwEthObject pwEthObject = new PwEthObject();
					pwEthObject.setName(etreeinfo.getPwId()+"");
					cxActionObject.getPwEthObjectList().add(pwEthObject);
				}
				if(0!=etreeinfo.getAportId()){
					PortInst portInst = new PortInst(); 
					portInst.setPortId(etreeinfo.getAportId());
					portInst =  portService.select(portInst).get(0);
					ethportobject.setName(portInst.getPortName());
					ethportobject.setPortType("eth");
					cxActionObject.setEthPortObject(ethportobject);
				}else{
					ethportobject.setName(etreeinfo.getaAcId()+"");
					ethportobject.setPortType("lag");
				}
				if(0!=etreeinfo.getaAcId()){
					AcObject acObject = new AcObject();
					acObject.setName(etreeinfo.getaAcId()+"");
					cxActionObject.setAcObject(acObject);
				}
			}else{
				cxActionObject.setAcObject(this.convertAcObject(etreeinfo, Type,action));
				cxActionObject.getPwEthObjectList().add(this.convertPwEthObject(etreeinfo, Type));
				cxActionObject.setEthPortObject(this.convertEthPortObject(etreeinfo, Type));
				//更换PW
//			pwChange(cxActionObject,etreeinfo,Type);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(portService);
		}
	}
	/**
	 * 离线网元pw对象转换
	 * @param etreeinfo
	 * @param rootcxActionObject
	 * @throws Exception
	 */
	private void offLineSitePwAction(EtreeInfo etreeinfo,
			CXActionObject rootcxActionObject,Map<Integer, CXActionObject> map ,String action,String type) throws Exception {
		if(etreeinfo.isDataDownLoad()&&action.equals(TypeAndActionUtil.ACTION_DELETE)){
			if(0!=etreeinfo.getPwId()){
				PwEthObject pwEthObject = new PwEthObject();
				pwEthObject.setName(etreeinfo.getPwId()+"");
				map.get(etreeinfo.getServiceId()).getPwEthObjectList().add(pwEthObject);
			}
		}else{
			map.get(etreeinfo.getServiceId()).getPwEthObjectList().add(this.convertPwEthObject(etreeinfo, type));
		}
	}
}

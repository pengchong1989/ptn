package com.nms.service.impl.cx;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.drivechenxiao.network.NetWorkUtil;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.ptnne.PtnNeObject;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.CXActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.CXOperationBase;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.xmlbean.EquipmentType;

public class SiteCXServiceImpl extends CXOperationBase implements OperationServiceI {
	@SuppressWarnings("rawtypes")
	@Override
	public String excutionDelete(List objectList) throws Exception {
		return null;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		OperationObject operationObject = null;
		OperationObject operationObjectRegister = null;
		SiteInst siteInst = null;
		String result = null;
		PtnNeObject ptnNeObject = null;
		String netype = null;
		boolean isCreate = true;	//是否创建。
		SiteService_MB siteService = null;
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			siteInst = (SiteInst) object;
			// 如果是真实网元，连接设备，否则入库
			if ("0".equals(UiUtil.getCodeById(siteInst.getSiteType()).getCodeValue())) {
				// 如果没有主键 说明是新建操作，操作设备
				if (siteInst.getSite_Inst_Id() == 0) {

					// 登陆设备
					operationObject = new OperationObject();
					operationObject.getCxActionObjectList().add(this.getCXActionObject(operationObject, siteInst, TypeAndActionUtil.ACTION_LOGIN));
					super.sendAction(operationObject);
					operationObject = super.verification(operationObject);
					// 如果登入成功 就下发设备注册命令
					if (operationObject.getCxActionObjectList().get(0).getStatus().equals("登入成功")) {
						operationObjectRegister = this.getCXActionObjectRegister(operationObject);
						super.sendAction(operationObjectRegister);

						// 注册后入库
						siteInst.setLoginstatus(1);

						// 如果网元版本没有值 说明是在拓扑上创建网元。否则是搜索网元
						if (null == siteInst.getVersions() || "".equals(siteInst.getVersions())) {
							// 查询网元基本信息
							operationObject = null;
							operationObject = new OperationObject();
							operationObject.getCxActionObjectList().add(this.getCXActionObject(operationObject, siteInst, TypeAndActionUtil.ACTION_SELECT));
							super.sendAction(operationObject);
							super.verification(operationObject);
							// 如果查询成功 把设备信息转换成 siteinst信息。
							if (operationObject.isSuccess()) {
								ptnNeObject = operationObject.getCxActionObjectList().get(0).getPtnNeObject();

								// 如果创建的类型和查询出的类型不匹配。 设置是否创建属性为false
								netype = this.getCellType(ptnNeObject.getType());
								if (!netype.equals(siteInst.getCellType())) {
									isCreate = false;
								}else{
//									siteInst.setCellDescribe(ptnNeObject.getId());
									siteInst.setCellIdentifier(ptnNeObject.getDesc());
									siteInst.setCellIcccode(ptnNeObject.getIcccode());
									siteInst.setCellTPoam(ptnNeObject.getTpoamchntype());
									siteInst.setCellTimeZone(ptnNeObject.getTimezone());
									siteInst.setVersions(ptnNeObject.getVer());
								}
							}
						}
						if (isCreate) {
							// 入库
							siteService.saveOrUpdate(siteInst);
							result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
						} else {
							result = ResourceUtil.srcStr(StringKeysTip.TIP_SITETYPEDIF);
						}
					} else {
						result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
					}
				} else {
					siteService.saveOrUpdate(siteInst);
					result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
				}
			} else {
				siteService.saveOrUpdate(siteInst);
				result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(siteService);
		}
		return result;
	}
	
	/**
	 * 修改 
	 */
	@Override
	public String excutionUpdate(Object object) throws Exception {
		SiteInst siteInst = null;
		OperationObject operationObject = null;
		List<SiteInst> siteInstList = null;
		OperationObject operationObject_login = null;
		CXActionObject cActionObject_login = null;
		String result = null;
		SiteService_MB siteService = null;
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			siteInst = (SiteInst) object;
			operationObject = new OperationObject();
			operationObject.getCxActionObjectList().add(this.getCXActionObject(operationObject, siteInst, TypeAndActionUtil.ACTION_UPDATE));

			super.sendAction(operationObject);
			operationObject = super.verification(operationObject);
			if (operationObject.isSuccess()) {

				operationObject_login = new OperationObject();
				cActionObject_login = new CXActionObject();
				cActionObject_login.setActionId(super.getActionId(operationObject_login.getCxActionObjectList()));
				cActionObject_login.setCxNeObject(this.convertCXNeObject(siteInst));
				cActionObject_login.setType(TypeAndActionUtil.TYPE_SITE);
				cActionObject_login.setAction(TypeAndActionUtil.ACTION_LOGIN);
				operationObject_login.getCxActionObjectList().add(cActionObject_login);
				super.sendAction(operationObject_login);
				super.verification(operationObject_login);

				if (operationObject_login.isSuccess()) {
					result = ResultString.CONFIG_SUCCESS;
					siteService.saveOrUpdate(siteInst);
				} else {
					siteInst = siteService.select(siteInst.getSite_Inst_Id());
					operationObject.getCxActionObjectList().add(this.getCXActionObject(operationObject, siteInst, TypeAndActionUtil.ACTION_UPDATE));
					siteInstList = new ArrayList<SiteInst>();
					siteInstList.add(siteInst);
					this.siteLogin(siteInstList);

					siteInst.setCellDescribe("10.0.1.254");
					operationObject = new OperationObject();
					operationObject.getCxActionObjectList().add(this.getCXActionObject(operationObject, siteInst, TypeAndActionUtil.ACTION_UPDATE));
					super.sendAction(operationObject);
					super.verification(operationObject);

					result = super.getErrorMessage(operationObject_login);
				}

			} else {
				result = super.getErrorMessage(operationObject);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(siteService);
		}
		return result;
	}

	/**
	 * 登陆和注册网元
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	public void siteLogin(List<SiteInst> siteInstList) throws Exception {

		OperationObject operationObject = null;
		OperationObject operationObjectRegister = null;
		try {

			for (SiteInst siteInst : siteInstList) {
				operationObject = new OperationObject();
				operationObject.getCxActionObjectList().add(this.getCXActionObject(operationObject, siteInst, TypeAndActionUtil.ACTION_LOGIN));
				super.sendAction(operationObject);
				super.verification(operationObject);
				operationObjectRegister = this.getCXActionObjectRegister(operationObject);
				super.sendAction(operationObjectRegister);
				this.updateStatus(operationObject, siteInst);
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			operationObject = null;
			operationObjectRegister = null;
		}
	}

	/**
	 * 
	 * 查询网元
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	public SiteInst select(int siteId) throws Exception {
		SiteInst siteInst = null;
		OperationObject operationObject = null;
		try {
			operationObject = new OperationObject();
			siteInst = new SiteInst();
			siteInst.setSite_Inst_Id(siteId);
			operationObject.getCxActionObjectList().add(this.getCXActionObject(operationObject, siteInst, TypeAndActionUtil.ACTION_SELECT));

			super.sendAction(operationObject);
			operationObject = super.verification(operationObject);

			siteInst = this.convertSite(siteId, operationObject.getCxActionObjectList().get(0).getPtnNeObject());
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			operationObject = null;
		}
		return siteInst;
	}

	/**
	 * 
	 * 登出网元
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	public void siteLogout(List<SiteInst> siteInstList) throws Exception {

		OperationObject operationObject = null;
		try {
			operationObject = new OperationObject();

			for (SiteInst siteInst : siteInstList) {
				if (siteInst.getLoginstatus() == 1) {
					operationObject.getCxActionObjectList().add(this.getCXActionObject(operationObject, siteInst, TypeAndActionUtil.ACTION_LOGOUT));
				}
			}

			super.sendAction(operationObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			operationObject = null;
		}
	}

	/**
	 * 把登陆成功的网元转换成注册网元下发
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private OperationObject getCXActionObjectRegister(OperationObject operationObject) throws Exception {
		OperationObject operationObjectRegister = null;
		CXActionObject cxActionObjectRegister = null;
		try {
			operationObjectRegister = new OperationObject();
			for (CXActionObject cxActionObject : operationObject.getCxActionObjectList()) {
				if (cxActionObject.getStatus().equals("登入成功")) {
					cxActionObjectRegister = new CXActionObject();
					cxActionObjectRegister.setCxNeObject(cxActionObject.getCxNeObject());
					cxActionObjectRegister.setActionId(super.getActionId(operationObjectRegister.getCxActionObjectList()));
					cxActionObjectRegister.setType(TypeAndActionUtil.TYPE_SITE);
					cxActionObjectRegister.setAction(TypeAndActionUtil.ACTION_REGISTER);
					// if(0==SiteUtil.SiteTypeUtil(timeManageInfo.getSiteId()))
					operationObjectRegister.getCxActionObjectList().add(cxActionObjectRegister);
				}

			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			cxActionObjectRegister = null;
		}
		return operationObjectRegister;
	}

	/**
	 * 
	 * 转换CXActionObject对象
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private CXActionObject getCXActionObject(OperationObject operationObject, SiteInst siteInst, String action) throws Exception {
		CXActionObject cxActionObject = null;
		try {
			cxActionObject = new CXActionObject();
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			if (siteInst.getSite_Inst_Id() == 0) {
				cxActionObject.setCxNeObject(this.convertCXNeObject(siteInst));
			} else {
				cxActionObject.setCxNeObject(super.getCXNEObject(siteInst.getSite_Inst_Id()));
			}
			cxActionObject.setType(TypeAndActionUtil.TYPE_SITE);
			cxActionObject.setAction(action);
			if (action.equals(TypeAndActionUtil.ACTION_UPDATE)) {
				cxActionObject.setPtnNeObject(this.convertPtnNeObj(siteInst));
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return cxActionObject;
	}

	/**
	 * 转换NE对象
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private CXNEObject convertCXNeObject(SiteInst siteInst) throws Exception {
		CXNEObject cxNEObject = null;
		try {
			cxNEObject = new CXNEObject();
			cxNEObject.setNeIp(siteInst.getCellDescribe());
			cxNEObject.setAdmin(siteInst.getUsername());
			cxNEObject.setPassword(siteInst.getUserpwd());
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return cxNEObject;
	}

	/**
	 * 转换ptnne对象
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private PtnNeObject convertPtnNeObj(SiteInst siteInst) {

		PtnNeObject ptnNeObject = new PtnNeObject();
		ptnNeObject.setId(siteInst.getCellDescribe());
		ptnNeObject.setDesc(siteInst.getCellIdentifier());
		ptnNeObject.setIcccode(siteInst.getCellIcccode());
		ptnNeObject.setTpoamchntype(siteInst.getCellTPoam());

		return ptnNeObject;
	}

	/**
	 * 
	 * 修改状态，并且查询登入成功的告警
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private void updateStatus(OperationObject operationObject, SiteInst siteInst) throws Exception {
		CXNEObject cxNeObject = null;
		SiteService_MB siteService = null;
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			for (CXActionObject cxActionObject : operationObject.getCxActionObjectList()) {
				cxNeObject = cxActionObject.getCxNeObject();
				System.out.println(cxActionObject.getStatus());
				if (cxActionObject.getStatus().equals("登入成功")) {
					// System.out.println(cxNeObject.getNeIp() + "：登入成功");
					siteService.updateByIp(cxNeObject.getNeIp(), 1);
					this.updateData(siteInst.getSite_Inst_Id());
					// UiUtil.updateTopoSite(siteInst.getSite_Inst_Id());
					// UiUtil.updateSiteInstAlarm();
				} else {
					// System.out.println(cxNeObject.getNeIp() + "：登入失败");
					siteService.updateByIp(cxNeObject.getNeIp(), 0);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(siteService);
		}

	}

	/**
	 * 更新db中当前告警数据
	 * 
	 * @author add by wangwf 20130417
	 * @param operationObject
	 * @throws Exception
	 */
	private void updateData(int siteId) throws Exception {
		List<Integer> siteIds = new ArrayList<Integer>();
		;
		siteIds.add(siteId);
		try {
			AlarmCXServiceImpl alarmCXServiceImpl = new AlarmCXServiceImpl();
			alarmCXServiceImpl.selectAlarm(siteIds);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			siteIds = null;
		}

	}

	private SiteInst convertSite(int siteId, PtnNeObject ptnNeObject) throws Exception {

		if (ptnNeObject == null) {
			throw new Exception("ptnNeObject is null");
		}

		SiteInst siteInst = null;
		List<EquipmentType> equipmentTypeList = null;
		SiteService_MB siteService = null;
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			siteInst = siteService.select(siteId);
			siteInst.setCellDescribe(ptnNeObject.getId());
			siteInst.setCellIdentifier(ptnNeObject.getDesc());
			siteInst.setCellIcccode(ptnNeObject.getIcccode());
			siteInst.setCellTPoam(ptnNeObject.getTpoamchntype());
			siteInst.setCellTimeZone(ptnNeObject.getTimezone());
			siteInst.setVersions(ptnNeObject.getVer());

			equipmentTypeList = ConstantUtil.equipmentTypeList;

			for (EquipmentType equipmentType : equipmentTypeList) {

				if (equipmentType.getCxEquipmentName().equals(ptnNeObject.getType())) {
					siteInst.setCellType(equipmentType.getTypeName());
					break;
				}
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(siteService);
		}
		return siteInst;
	}

	@Override
	public Object synchro(int siteId) {
		return null;
	}

	/**
	 * 初始化方法
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initialize(SiteInst siteInst) throws Exception {
		OperationObject operationObject = null;
		SiteService_MB siteService = null;
		try {
			operationObject = new OperationObject();
			operationObject.getCxActionObjectList().add(this.getCXActionObject(operationObject, siteInst, TypeAndActionUtil.ACTION_CLEARSITE));

			// 下发并验证结果
			super.sendAction(operationObject);
			super.verification(operationObject);

			if (operationObject.isSuccess()) {
				// 下发成功,返回状态
				siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
				siteService.initializtionSite(siteInst);
				return operationObject.getCxActionObjectList().get(0).getStatus();
			} else {
				return super.getErrorMessage(operationObject);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(siteService);
		}
	}

	/**
	 * 网元搜索
	 * @param ip
	 * 		IP地址
	 * @return
	 */
	public List<SiteInst> siteSearch(String ip) {
		List<PtnNeObject> ptnNeList;
		SiteInst siteInst;
		NetWorkUtil netWorkUtil = new NetWorkUtil();
		List<SiteInst> siteInstList = new ArrayList<SiteInst>();
		ptnNeList = netWorkUtil.getPtnNeObject(ip);
		try {
			if (null != ptnNeList) {
				for (PtnNeObject ne : ptnNeList) {
					int proSeries = 1;
					siteInst = new SiteInst();
					siteInst.setCellDescribe(ne.getId());
					siteInst.setCellType(this.getCellType(ne.getType()));
					siteInst.setCellEditon(UiUtil.getCodeByValue("EDITION", proSeries + "").getId() + "");
					siteInst.setVersions(ne.getVer());
					siteInstList.add(siteInst);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			ptnNeList = null;
			netWorkUtil = null;
			siteInst = null;
		}
		return siteInstList;
	}

	/**
	 * 把设备类型转换成DB需要的类型格式
	 * 
	 * @param neType
	 * @return
	 */
	private String getCellType(String neType) {

		String result = "";

		if ("cxt100".equals(neType)) {
			result = "700B";
		} else if ("cxt20b".equals(neType)) {
			result = "700D";
		} else if ("cxt20a".equals(neType)) {
			result = "700E";
		} else if ("cxt21a".equals(neType)) {
			result = "700E";
		} else if ("cxt500".equals(neType)) {
			result = "700A";
		}
		return result;
	}
}

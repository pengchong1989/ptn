package com.nms.drive.service;

import com.nms.service.bean.ActionObject;
import com.nms.service.bean.OperationObject;

public interface DriveServiceI {

	/**
	 * 初始化驱动服务
	 * 
	 * @throws Exception
	 */
	public void init() throws Exception;

	/**
	 * 销毁
	 */
	public void destroy();
	
	/**
	 * 更新隧道
	 * 
	 * @param OperationObject回调方法
	 * @param actionObject事件方法
	 * @throws Exception
	 * 
	 */
	public void updataTunnal(OperationObject operationObject, ActionObject actionObject) throws Exception;
	
	/**
	 * 更新ELine
	 * 
	 * @param operationObject回调对象
	 * @param actionObject事件对象
	 * @throws Exception
	 */
	public void updataEline(OperationObject operationObject, ActionObject actionObject) throws Exception;
	
	/**
	 * 更新PW
	 * 
	 * @param operationObject回调对象
	 * @param actionObject事件对象
	 * @throws Exception
	 */
	public void updataPW(OperationObject operationObject, ActionObject actionObject) throws Exception;
	

	/**
	 * 更新ETHOAM
	 * 
	 * @param operationObject回调对象
	 * @param actionObject事件对象
	 * @throws Exception
	 */
	public void updataETHOAM(OperationObject operationObject, ActionObject actionObject) throws Exception;
	
	/**
	 * 更新ETree
	 * @param operationObject
	 * @param actionObject
	 * @throws Exception
	 */
	public void updataETree(OperationObject operationObject, ActionObject actionObject) throws Exception;
	
	/**
	 * 更新E1
	 * @param operationObject
	 * @param actionObject
	 * @throws Exception
	 */
	public void updataE1(OperationObject operationObject, ActionObject actionObject) throws Exception;
	
	/**
	 * 更新LSP保护
	 * @param operationObject回调对象
	 * @param actionObjectOperationObject
	 * @throws Exception
	 */
	public void updataLSPProtection(OperationObject operationObject, ActionObject actionObject) throws Exception;
	
	/**
	 * 更新TMP OAM故障管理配置
	 * @param operationObject
	 * @param actionObject
	 * @throws Exception
	 */
	public void updataTMPOAMBugControl(OperationObject operationObject, ActionObject actionObject) throws Exception;
	
	/**
	 * 更新Port LAG 端口聚合
	 * @param operationObject
	 * @param actionObject
	 * @throws Exception
	 */
	public void updatePortLAG(OperationObject operationObject,ActionObject actionObject)throws Exception;
	
	/**
	 * 更新 PHB到TMC/TMP EXP映射表(09H)
	 * @param operationObject
	 * @param actionObject
	 * @throws Exception
	 */
	public void updatePHBToEXP(OperationObject operationObject,ActionObject actionObject)throws Exception;
	
	/**
	 * 更新TMS OAM故障管理配置
	 * @param operationObject
	 * @param actionObject
	 * @throws Exception
	 */
	public void updataTMSOAMBugControl(OperationObject operationObject, ActionObject actionObject) throws Exception;
	
	/**
	 * 更新端口高级配置
	 * @param operationObject
	 * @param actionObject
	 * @throws Exception
	 */
	public void updataPortSeniorConfig(OperationObject operationObject, ActionObject actionObject) throws Exception;
	
	/**
	 * 更新EXP to PHB 
	 */
	public void updateEXPToPHB(OperationObject operationObject,ActionObject actionObject)throws Exception;
	
	/**
	 * 更新以太网链路 OAM
	 */
	public void updateETHLinkOAM(OperationObject operationObject,ActionObject actionObject)throws Exception;
	
	/**
	 * 更新 环网保护(04H) 
	 */
	public void updateRoundProtection(OperationObject operationObject,ActionObject actionObject)throws Exception;
	
	/**
	 * 更新IGMP SNOOPING配置(17H)
	 */
	public void updateIGMPSNOOPing(OperationObject operationObject,ActionObject actionObject)throws Exception;
	
	/**
	 * 更新 全局配置块(03H)
	 */
	public void updateGloble(OperationObject operationObject,ActionObject actionObject)throws Exception;
	/**
	 * 更新 静态组播 
	 */
	public void updateGroupSpread(OperationObject operationObject,ActionObject actionObject)throws Exception;
	/**
	 * 更新 单卡基本信息
	 */
	public void updateSingleObject(OperationObject operationObject,ActionObject actionObject)throws Exception;
	/**
	 * 更新 静态单播(0BH)
	 */
	public void updateStaticUnicast(OperationObject operationObject,ActionObject actionObject)throws Exception;
	/**
	 * 更新 管理配置
	 */ 
	public void updateManagement(OperationObject operationObject,ActionObject actionObject)throws Exception;
	/**
	 * 更新 单盘回答信息
	 */
	public void updateResponsePan(OperationObject operationObject,ActionObject actionObject)throws Exception;
	/**
	 * 软件升级配置 
	 */
	public void updateSoftwareUpdate(OperationObject operationObject, ActionObject actionObject) throws Exception ;
	/**
	 * 端口配置配置 
	 */
	public void updatePortConfig(OperationObject operationObject, ActionObject actionObject) throws Exception ;
	/**
	 * WS向M查询某NE某盘某配置块信息
	 * 
	 */
	public void queryBusinessObjectByCard(OperationObject operationObject, ActionObject actionObject,int number) throws Exception ;

	/**
	 * 强制倒换保护
	 * @param operationObject
	 * @param actionObject
	 * @throws Exception
	 */
	public void updateProtectRorate(OperationObject operationObject, ActionObject actionObject) throws Exception;

	/**
	 * 板卡复位
	 * @param operationObject
	 * @param actionObject
	 * @throws Exception
	 */
	public void updateSlotReset(OperationObject operationObject, ActionObject actionObject) throws Exception;

	/**
	 * tdm环回
	 * @param operationObject
	 * @param actionObject
	 * @throws Exception
	 */
	public void updateTdmLoopBack(OperationObject operationObject, ActionObject actionObject) throws Exception;
	
	/**
	 * 网元属性
	 * @param operationObject
	 * @param actionObject
	 */
	public void querySiteAttribute(OperationObject operationObject, ActionObject actionObject);
	/**
	 * 时钟倒换
	 * @param operationObject
	 * @param actionObject
	 * @throws Exception
	 */
	public void clockRorate(OperationObject operationObject, ActionObject actionObject) throws Exception;
	public void queryOamStatus(OperationObject operationObject, ActionObject actionObject) throws Exception ;
	public void updateOamPing(OperationObject operationObject, ActionObject actionObject) throws Exception ;
	/**
	 * 查询或下发软件摘要
	 * @param operationObject
	 * @param actionObject
	 * @throws Exception
	 */
	public void querySoftwareSummary(OperationObject operationObject,ActionObject actionObject)throws Exception;
	
	/**
	 * 取消软件升级
	 * @param operationObject
	 * @param actionObject
	 * @throws Exception
	 */
	public void cancelSoftware(OperationObject operationObject,ActionObject actionObject)throws Exception;
	
	/**
	 * 告警屏蔽
	 * @param operationObject
	 * @param actionObject
	 * @throws Exception
	 */
	public void updateShieldAlarm(OperationObject operationObject,ActionObject actionObject)throws Exception;
	
	/**
	 * L2CP配置块
	 * @param operationObject
	 * @param actionObject
	 * @throws Exception
	 */
	public void updateL2CP(OperationObject operationObject,ActionObject actionObject)throws Exception;
	
	/**
	 * 端口pri
	 * @param operationObject
	 * @param actionObject
	 * @throws Exception
	 */
	public void updatePortPri(OperationObject operationObject, ActionObject actionObject) throws Exception;
}

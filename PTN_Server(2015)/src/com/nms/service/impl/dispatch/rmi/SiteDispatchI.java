package com.nms.service.impl.dispatch.rmi;

import java.rmi.RemoteException;
import java.util.List;

import com.nms.db.bean.equipment.manager.UpgradeManage;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.path.Segment;
import com.nms.db.bean.ptn.SiteStatusInfo;
import com.nms.db.bean.ptn.oamStatus.OamStatusInfo;

/**
 * 网元rmi接口
 * @author kk
 *
 */
public interface SiteDispatchI extends DispatchInterface{

	/**
	 * 登陆网元
	 * @param siteInstList 要登陆的网元集合
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void siteLogin(List<SiteInst> siteInstList) throws RemoteException,Exception;
	
	/**
	 * 查询网元
	 * @param siteId
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public SiteInst selectSite(int siteId) throws RemoteException,Exception;
	
	/**
	 * 网元校时
	 * @param siteInst
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public String currectTime(SiteInst siteInst) throws RemoteException,Exception;
	
	/**
	 * 网元初始化
	 * @param siteInst
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public String clearSite(SiteInst siteInst) throws RemoteException,Exception;
	
	/**
	 * 网元上载
	 * @param siteInst
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public byte[] uploadConfig(SiteInst siteInst) throws RemoteException,Exception;
	
	/**
	 * 网元下载
	 * @param siteInst
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public String downloadConfig(SiteInst siteInst) throws RemoteException,Exception;
	
	/**
	 * 网元搜索
	 * @param ip
	 * @param manufacturer
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public List<SiteInst> siteSearch(String ip,int manufacturer) throws RemoteException,Exception;
	
	/**
	 * 查询网元状态
	 * @param siteInst 
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public SiteStatusInfo siteStatus(SiteInst siteInst) throws RemoteException,Exception;
	
	/**
	 * 数据下载
	 * @param a  要下载数据的类型
	 * @param fieldConfigRightPanel
	 */
	public String dataDownLoadActionPerformed(List<SiteInst> siteInstList, int[] a) throws RemoteException,Exception;

	/**
	 * 查询oam状态
	 * @param siteInst 
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public OamStatusInfo oamStatus(SiteInst siteInst) throws RemoteException,Exception;
	
	/**
	 * 创建或删除丢弃流
	 * @param siteInst
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public String createOrDeleteDiscardFlow(SiteInst siteInst) throws RemoteException,Exception;
	/**
	 * 查询或
	 * @param siteInst
	 * @return
	 */
	public List<UpgradeManage> softwareSummary(SiteInst siteInst)throws RemoteException,Exception;
	/**
	 * 下发软件摘要
	 * @param siteInst
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public String sendSummary(SiteInst siteInst)throws RemoteException,Exception;
	
	/**
	 * 用于取消软件升级
	 * @param siteInst
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public String cancelSlftWare(SiteInst siteInst) throws RemoteException,Exception;
	
	/**
	 * 查询SN(包含本地或相邻)
	 * @param siteInst
	 * @return
	 */
	public List<SiteInst> querySn(SiteInst siteInst,int isLocaltion) throws RemoteException;
	
	/**
	 * 设置网元IP
	 * @param siteInst
	 * @return
	 */
	public String setSiteIP(SiteInst siteInst) throws RemoteException;
	/**
	 * 拓扑自动发现
	 * @return
	 */
//	public String topologicalLinkFound(List<SiteInst> siteInsts,int netWorkId,List<Segment> seg) throws RemoteException;
	public List<Segment> topologicalLinkFound(List<SiteInst> siteInsts,int netWorkId,List<Segment> seg) throws RemoteException;
	
	
	/**
	 * 网元Ping功能
	 */
	public Object pingCMD(String neIP) throws RemoteException;
	/**
	 * 网元定时重启
	 * @param siteInsts
	 * @return
	 * @throws RemoteException
	 */
	public String taskRboot(List<SiteInst> siteInsts) throws RemoteException;
	
	public String routeIn()throws RemoteException;
	
	public String vlanMac(SiteInst siteInst,List<String> value)throws RemoteException;
	
}

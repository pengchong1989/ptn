package com.nms.model.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.system.OffLinesiteBusi;
import com.nms.db.dao.equipment.shelf.SiteInstMapper;
import com.nms.model.system.OffLinesiteBusiService_MB;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class ObjectService_Mybatis {
	
	protected SqlSession sqlSession = null;
	protected String ptnuser = null;
	
	/**
	 * 离线网元数据保存
	 * @param siteId 
	 * 			网元ID
	 * @param operId
	 * 			各个业务表的ID
	 * @param operType
	 * 			离线数据类型
	 * @param action
	 * 			离线数据操作方法
	 * @return
	 * @throws Exception
	 */
	protected int dateDownLoad(int siteId, int operId, int operType, int action) throws Exception {
		int result =0;
		OffLinesiteBusiService_MB offLinesiteBusiService = null;
		try {
			offLinesiteBusiService = (OffLinesiteBusiService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OFFLINESITEBUSISERVICE);
			SiteInstMapper siteInstMapper=offLinesiteBusiService.getSqlSession().getMapper(SiteInstMapper.class);
			SiteInst siteInst = new SiteInst();
			siteInst.setSite_Inst_Id(siteId);
			List<SiteInst> siteInsts = new ArrayList<SiteInst>();
			siteInsts = siteInstMapper.queryByCondition(siteInst);
			if(siteInsts!=null && siteInsts.size()!=0){
				siteInst=siteInsts.get(0);
				if(0==siteInst.getLoginstatus()){
					OffLinesiteBusi offLinesiteBusi = new OffLinesiteBusi();
					offLinesiteBusi.setSiteId(siteId);
					offLinesiteBusi.setOperId(operId);
					offLinesiteBusi.setOperType(operType);
					offLinesiteBusi.setAction(action);
					result = offLinesiteBusiService.dataDownLoadAction(offLinesiteBusi);
				}
			}
			
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(offLinesiteBusiService);
		}
		return result;
	}
	
	/**
	 * 离线网元数据保存
	 * @param siteId 
	 * 			网元ID
	 * @param operId
	 * 			离线数据表ID
	 * @param operType
	 * 			离线业务类型
	 * @param action
	 * 			离线数据操作方法
	 * @param actionIdentify
	 * 			businessId
	 * @param parentBusiId
	 * 			父业务ID
	 * @param portId	
	 * 			端口ID
	 * @param acId
	 * 			acID
	 * @param type
	 * 			业务子类类型
	 * @return
	 * @throws Exception
	 */
	protected int dateDownLoad(int siteId, int operId, int operType, int action,String actionIdentify,String parentBusiId,int portId ,int acId,String type) throws Exception {
		int result =0;
		OffLinesiteBusiService_MB offLinesiteBusiService = null;
		try {
			offLinesiteBusiService = (OffLinesiteBusiService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OFFLINESITEBUSISERVICE);
			SiteInstMapper siteInstMapper=offLinesiteBusiService.getSqlSession().getMapper(SiteInstMapper.class);
			SiteInst siteInst = new SiteInst();
			siteInst.setSite_Inst_Id(siteId);
			List<SiteInst> siteInstList = siteInstMapper.queryByCondition(siteInst);
			if(siteInstList != null && siteInstList.size()>0){
				siteInst = siteInstList.get(0);
			}
			if(0==siteInst.getLoginstatus()){
				OffLinesiteBusi offLinesiteBusi = this.getOffLinesiteBusi(siteId, operId, operType, action, actionIdentify, parentBusiId, portId, acId, type);
				result = offLinesiteBusiService.dataDownLoadAction(offLinesiteBusi);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}finally{
			UiUtil.closeService_MB(offLinesiteBusiService);
		}
		return result;
	}
	protected OffLinesiteBusi getOffLinesiteBusi(int siteId, int operId, int operType, int action,String actionIdentify,
			String parentBusiId,int portId ,int acId ,String type){
		OffLinesiteBusi offLinesiteBusi = new OffLinesiteBusi();
		offLinesiteBusi.setSiteId(siteId);
		offLinesiteBusi.setOperId(operId);
		offLinesiteBusi.setOperType(operType);
		offLinesiteBusi.setAction(action);
		offLinesiteBusi.setActionIdentify(actionIdentify);
		offLinesiteBusi.setParentBusiId(parentBusiId);
		offLinesiteBusi.setPortId(portId);
		offLinesiteBusi.setAcId(acId);
		offLinesiteBusi.setType(type);
		return offLinesiteBusi;
	}
	
	
	
	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	/**
	 * 关闭连接
	 */
	public void close(){
		if(null!=this.sqlSession){
			this.sqlSession.close();
			this.sqlSession = null;
		}
	}
}

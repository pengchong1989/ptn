package com.nms.model.ptn.path.tunnel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.path.tunnel.Lsp;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.dao.ptn.path.tunnel.LspinfoMapper;
import com.nms.model.ptn.LabelInfoService_MB;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;

public class LspInfoService_MB extends ObjectService_Mybatis{
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	private LspinfoMapper mapper;

	public LspinfoMapper getLspInfoMapper() {
		return mapper;
	}

	public void setLspInfoMapper(LspinfoMapper LspInfoMapper) {
		this.mapper = LspInfoMapper;
	}
	
	/**
	 * 根据条件查询
	 * 
	 * @param lspparticular
	 *            查询条件
	 * @return List<LspParticular> 集合
	 * @throws Exception
	 */
	public List<Lsp> select(Lsp lspparticular) throws Exception {
		List<Lsp> lspparticularList = null;
		try {
			lspparticularList = new ArrayList<Lsp>();
			lspparticularList = this.mapper.queryByCondition(lspparticular);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return lspparticularList;
	}
	
	/**
	 * 通过tunnelid查询出lsp集合 然后跟参数lspList进行比较
	 * 
	 * @param tunnelId
	 *            tunnel主键
	 * @param lspList
	 *            要比较的lsp集合
	 * @return true=路径相同 false=路径不同
	 * @throws Exception
	 */
	public boolean compareLsp(int tunnelId, List<Lsp> lspList) throws Exception {
		List<Lsp> lspList_select = null;
		boolean flag = true;
		Lsp lsp_select=null;	//lsp数据库对象
		Lsp lsp_ui=null;		//lsp界面对象
		try {
			// 通过tunnelid 查询出此tunnel下的lsp路径
			lspList_select = this.mapper.queryByTunnnelId(tunnelId);
			
			//如果数量相同。才去比较路径是否一样
			if (lspList_select.size() == lspList.size()) {
				for (int i = 0; i < lspList_select.size(); i++) {
					lsp_select=lspList_select.get(i);
					lsp_ui=lspList.get(i);
					
					//如果正向比较不等的话。  就反向在比较
					if(!this.compareLsp(lsp_select, lsp_ui)){
						lsp_ui=lspList.get(lspList.size()-i-1);
						
						//如果反向比较也不等的话。就直接结束循环 返回界面
						if(!this.compareLsp(lsp_select, lsp_ui)){
							flag=false;
							break;
						}
					}
				}
			}else{
				flag=false;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			lspList_select = null;
		}
		return flag;
	}
	
	/**
	 * 比较两个lsp是否为同样路径
	 * 
	 * @param lsp_select
	 * @param lsp_ui
	 * @return 相同返回true 不同返回false
	 */
	private boolean compareLsp(Lsp lsp_select, Lsp lsp_ui) throws Exception {

		// 比较asiteid zsiteid aportid zportid
		if ((lsp_select.getASiteId() == lsp_ui.getASiteId() && lsp_select.getZSiteId() == lsp_ui.getZSiteId() && lsp_select.getAPortId() == lsp_ui.getAPortId() && lsp_select.getZPortId() == lsp_ui.getZPortId()) || 
				(lsp_select.getASiteId() == lsp_ui.getZSiteId() && lsp_select.getZSiteId() == lsp_ui.getASiteId() && lsp_select.getAPortId() == lsp_ui.getZPortId() && lsp_select.getZPortId() == lsp_ui.getAPortId())) {
//			//比较标签
//			if(lsp_select.getFrontLabelValue() == lsp_ui.getFrontLabelValue() && lsp_select.getBackLabelValue() == lsp_ui.getBackLabelValue()){
				return true;
//			}else{
//				return false;
//			}
			
		} else {
			return false;
		}

	}

	/**
	 * 通过tunnelId查询
	 * @param tunnelId
	 * @return
	 */
	public List<Lsp> selectBytunnelId(int tunnelId) {
		List<Lsp> lspList_select  = this.mapper.queryByTunnnelId(tunnelId);
		return lspList_select;
	}

	/**
	 * 通过端口查询lsp
	 * @param portId
	 * @return
	 */
	public List<Lsp> selectByPort(int portId) {
		List<Lsp> lspList_select  = this.mapper.selectByPort(portId);
		return lspList_select;
	}

	/**
	 * 验证同一端口的lsp的入标签和该端口上的pw的入标签是否一样
	 * 一样返回false,不一样返回true
	 * @throws Exception 
	 */
	public boolean verifyInLabel(int siteId, int portId, int inLabel) throws Exception {
		List<Lsp> lspList = this.mapper.queryByPort(portId);
		if(lspList != null && lspList.size() > 0){
			for (Lsp lsp : lspList) {
				if(lsp.getASiteId() == siteId){
					if(lsp.getBackLabelValue() == inLabel){
						return false;
					}
				}else if(lsp.getZSiteId() == siteId){
					if(lsp.getFrontLabelValue() == inLabel){
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * 查询同一端口下标签是否可用
	 * @param portId
	 * @param label
	 * @return
	 * @throws Exception
	 */
	public boolean checkOutLabelUsable(int portId, int label) throws Exception{
		Lsp lsp = this.mapper.queryOutLabelUsable(portId,label);
		if(lsp != null && lsp.getId()>0){
			return false;
		}
		return true;
	}
	
	
	/**
	 * 根据端口查询已用的lsp标签
	 */
	public Map<Integer, Integer> selectLspLabelByPort(List<Integer> portIdList) {
		Map<Integer, Integer> labelMap = new HashMap<Integer, Integer>();
		try {
			List<Lsp> lspList = new ArrayList<Lsp>();
			lspList = this.mapper.queryByCondition(new Lsp());
			if(lspList != null){
				for (int portId : portIdList) {
					int count = 0;
					for (Lsp lsp : lspList) {
						if(portId == lsp.getAPortId() || portId == lsp.getZPortId()){
							count ++;
						}
					}
					labelMap.put(portId, count);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return labelMap;
	}
	
	public Map<Integer, Integer> selectLspLabelBySite(List<Integer> siteIdList) {
		Map<Integer, Integer> labelMap = new HashMap<Integer, Integer>();
		try {
			List<Lsp> lspList = new ArrayList<Lsp>();
			lspList = this.mapper.queryByCondition(new Lsp());
			if(lspList != null){
				for (int siteId : siteIdList) {
					int count = 0;
					for (Lsp lsp : lspList) {
						if(siteId == lsp.getASiteId() || siteId == lsp.getZSiteId()){
							count ++;
						}
					}
					labelMap.put(siteId, count);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return labelMap;
	}
	
	public List<Lsp> selectBySiteId(int siteId) throws Exception {

		List<Lsp> lspparticularList = null;

		try {
			lspparticularList = new ArrayList<Lsp>();
			lspparticularList = this.mapper.selectBySiteId(siteId);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return lspparticularList;
	}
	
	public List<Lsp> selectBySegmentId(int segmentId) throws Exception {
		List<Lsp> lspparticularList = null;

		try {
			lspparticularList = mapper.queryBySegmentId(segmentId);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return lspparticularList;
	}
	
	/**
	 * 新增或修改lspparticular对象，通过lspparticular.getId()来判断是修改还是新增
	 * 
	 * @param lspparticular
	 *            实体
	 * @return 执行成功的记录数
	 * @throws Exception
	 */
	public int saveOrUpdate(Lsp lspparticular) throws Exception {

		if (lspparticular == null) {
			throw new Exception("lspparticular is null");
		}
		LabelInfoService_MB labelService = null;
		int result = 0;
		try {

			if (lspparticular.getId() == 0) {
				result = this.mapper.insert(lspparticular);
			} else {
				result = this.mapper.updateByPrimaryKey(lspparticular);
			    labelService = (LabelInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.LABELINFO, this.sqlSession);
				// 修改两个网元的前向标签
				labelService.updateBatch(lspparticular.getFrontLabelValue(), lspparticular.getZSiteId(), 0, "TUNNEL");

				// 修改两个网元的后向标签
				labelService.updateBatch(lspparticular.getBackLabelValue(), lspparticular.getASiteId(), 0, "TUNNEL");

			}
			sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}
	
	/**
	 * 通过主键删除lspparticular 对象
	 * 
	 * @param id
	 *            主键
	 * @return 删除的记录数
	 * @throws Exception
	 */
	public int delete(int id) throws Exception {

		int result = 0;

		try {
			result = mapper.deleteByPrimaryKey(id);
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;

	}
	
	/**
	 * 同步时查询
	 * 
	 * @author kk
	 * @param roole
	 *            角色 "ingress"=A端 "egress"=z端 "xc"=中间节点 ""=武汉不确定是a还是z 所以同时比较az端
	 * @param siteid
	 *            网元id
	 * @param tunnel
	 *            tunnel对象
	 * @return
	 * @Exception 异常对象
	 */
	public List<Lsp> select_synchro(String role, int siteid, Tunnel tunnel) throws Exception {

		List<Lsp> lspparticularList = null;
		int atunnelbusinessid = 0;
		int ztunnelbusinessid = 0;
		try {
			if(tunnel.getLspParticularList().size() == 1){
				atunnelbusinessid = tunnel.getLspParticularList().get(0).getAtunnelbusinessid();
				ztunnelbusinessid = tunnel.getLspParticularList().get(0).getZtunnelbusinessid();
			}else if(tunnel.getLspParticularList().size() == 2){
				atunnelbusinessid = tunnel.getLspParticularList().get(1).getAtunnelbusinessid();
				ztunnelbusinessid = tunnel.getLspParticularList().get(0).getZtunnelbusinessid();
			}
			lspparticularList = mapper.querySychro(role, siteid, tunnel,tunnel.getLspParticularList().size(),atunnelbusinessid,ztunnelbusinessid);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return lspparticularList;
	}
}

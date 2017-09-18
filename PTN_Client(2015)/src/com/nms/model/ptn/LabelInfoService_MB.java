package com.nms.model.ptn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.LabelInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.path.tunnel.Lsp;
import com.nms.db.dao.ptn.LabelInfoMapper;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.ptn.path.tunnel.LspInfoService_MB;
import com.nms.model.util.LabelManage;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;

public class LabelInfoService_MB extends ObjectService_Mybatis {
	private LabelInfoMapper mapper = null;
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	public void setMapper(LabelInfoMapper mapper1) {
		this.mapper = mapper1;
	}
	
	/**
	 * 批量修改
	 * @throws Exception
	 */
	public void updateBatch(int labelValue, int siteid, int status, String type) throws Exception {
		int manufacturer = 0;
		SiteService_MB siteService = null;
		try {
			//目前设备的芯片不支持同一端口的lsp的入标签和该端口上的pw的入标签一样,所以要用下面的代码
			if(type.equals("TUNNEL") || type.equals("PW")){
				type = "WH";
			}
			//如果以后芯片支持同一端口的lsp的入标签和该端口上的pw的入标签一样,就用下面的代码,把上面的代码关掉
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE, this.sqlSession);
			manufacturer = siteService.getManufacturer(siteid);
			if(manufacturer == 1){
				type = "CX";
			}
			LabelInfo label = new LabelInfo();
			label.setLabelValue(labelValue);
			label.setSiteid(siteid);
			label.setLabelStatus(0);
			label.setType(type);
			this.mapper.updateBatch(label);
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	public void saveOrUpdate(int labelValue, int siteid, int status, String type) throws Exception {
		int manufacturer = 0;
		SiteService_MB siteService = null;
		try {
			//目前设备的芯片不支持同一端口的lsp的入标签和该端口上的pw的入标签一样,所以要用下面的代码
			if(type.equals("TUNNEL") || type.equals("PW")){
				type = "WH";
			}
			//如果以后芯片支持同一端口的lsp的入标签和该端口上的pw的入标签一样,就用下面的代码,把上面的代码关掉
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE, this.sqlSession);
			manufacturer = siteService.getManufacturer(siteid);
			if(manufacturer == 1){
				type = "CX";
			}
			LabelInfo condition = new LabelInfo();
			condition.setLabelValue(labelValue);
			condition.setSiteid(siteid);
			condition.setType(type);
			List<LabelInfo> labelInfos = this.mapper.quertyByLabelValue(condition);
			if (labelInfos.size() > 0) {
				this.updateBatch(labelValue, siteid, status, type);
			} else {
				LabelInfo labelInfo = new LabelInfo();
				labelInfo.setLabelStatus(status);
				labelInfo.setSiteid(siteid);
				labelInfo.setLabelValue(labelValue);
				labelInfo.setType(type);
				this.mapper.insert(labelInfo);
				this.sqlSession.commit();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 根据主键删除LabelInfo对象
	 * @param id
	 * @return 删除成功的记录数
	 * @throws Exception
	 */
	public int delete(int id) throws Exception {
		int result = 0;
		try {
			result = this.mapper.delete(id);
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}

	/**
	 * 查询全部
	 * @return List<LabelInfo> 集合
	 * @throws Exception
	 */
	public List<LabelInfo> select() throws Exception {
		List<LabelInfo> labelinfoList = new ArrayList<LabelInfo>();
		try {
			LabelInfo labelinfo = new LabelInfo();
			labelinfoList = this.mapper.queryByCondition(labelinfo);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return labelinfoList;
	}

	/**
	 * 根据条件查询
	 * @param labelinfo 查询条件
	 * @return List<LabelInfo> 集合
	 * @throws Exception
	 */
	public List<LabelInfo> select(LabelInfo labelinfo) throws Exception {
		List<LabelInfo> labelinfoList = new ArrayList<LabelInfo>();
		SiteService_MB siteService = null;
		try {
			String type = labelinfo.getType();
			//目前设备的芯片不支持同一端口的lsp的入标签和该端口上的pw的入标签一样,所以要用下面的代码
			if(type.equals("TUNNEL") || type.equals("PW")){
				type = "WH";
			}
			//如果以后芯片支持同一端口的lsp的入标签和该端口上的pw的入标签一样,就用下面的代码,把上面的代码关掉
			if(labelinfo.getSiteid() > 0 && ("TUNNEL".equals(type) || "PW".equals(type))){
				int manufacturer = 0;
				siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE, this.sqlSession);
				manufacturer = siteService.getManufacturer(labelinfo.getSiteid());
				if(manufacturer == 1)
					labelinfo.setType("CX");
			}
			labelinfoList = this.mapper.queryByCondition(labelinfo);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return labelinfoList;
	}

	/**
	 * 查询一组标签是否可用
	 * 
	 * @param labelValues
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public String select(List<Integer> labelValues, int siteId, String type) throws Exception {
		String result = "";
		List<LabelInfo> labelInfos = null;
		SiteService_MB siteService = null;
		try {
			if(labelValues != null && labelValues.size() > 0){
				//目前设备的芯片不支持同一端口的lsp的入标签和该端口上的pw的入标签一样,所以要用下面的代码
				if(type.equals("TUNNEL") || type.equals("PW")){
					type = "WH";
				}
				//如果以后芯片支持同一端口的lsp的入标签和该端口上的pw的入标签一样,就用下面的代码,把上面的代码关掉
				siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE, this.sqlSession);
				int manufacturer = siteService.getManufacturer(siteId);
				//等于1是晨晓设备,入标签网元唯一
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("condition", labelValues);
				map.put("siteId", siteId);
				if(manufacturer == 0){
					map.put("type", type);
					labelInfos = this.mapper.queryByLabelvalues(map);
				}else{
					labelInfos = this.mapper.queryByLabelvaluesForCX(map);
				}
				if (labelInfos != null) {
					int i = 1;
					for (LabelInfo labelInfo : labelInfos) {
						if(i <= 2){
							result += labelInfo.getLabelValue() + ",";
						}
						i++;
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}

	/**
	 * 通过出标签和端口ID 验证此出标签是否可用。 通过portid查询出此port下所有tunnel 验证每个tunnel的出标签是否与outLabel相同，如果相同，结束循环，返回false。 通过所有tunnel，查询pw集合，如果是pw的A Z两端就验证pw的出标签是否与outLabel相同，如果相同，结束循环，返回false
	 * 
	 * @param outLabel
	 *            要验证出标签
	 * @param portId
	 *            端口表主键
	 * @param siteId
	 *            网元主键
	 * @param type
	 * 			  类型,即tunnel和pw,武汉设备可以区分tunnel和pw的标签,所以tunnel和pw的出标签可以一致,不用做区分
	 * @return true 验证通过 false 验证不通过。
	 * @throws Exception
	 */
	public boolean checkingOutLabel(int outLabel, int portId, int siteId, String type) throws Exception {
		boolean result = true;
		LspInfoService_MB lspService = null;
		PwInfoService_MB pwInfoService = null;
		try {
			List<Integer> tunnelIdList = new ArrayList<Integer>();
			// 查询此端口下的所有LSP 
		    lspService = (LspInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.LSPINFO, this.sqlSession);
			List<Lsp> lspList = lspService.selectByPort(portId);

			if(type.equals("TUNNEL")){
				// 遍历lsp。比较outLabel签是否与lsp的出标签相同，如果有一个相同，结束方法，返回false
				if (null != lspList && lspList.size() > 0) {
					for (Lsp lsp : lspList) {

						// 如果此端口是A端， 前向标签为出标签
						if (portId == lsp.getAPortId()) {
							if (outLabel == lsp.getFrontLabelValue()) {
								result = false;
								break;
							}
						} else {
							// 如果此端口是Z端，后向标签为出标签
							if (outLabel == lsp.getBackLabelValue()) {
								result = false;
								break;
							}
						}
					}
				}
			}else if(type.equals("PW")){
				if (null != lspList && lspList.size() > 0) {
					for (Lsp lsp : lspList) {
						// 如果集合中不存在此tunnel主键，就添加到集合中
						if (!tunnelIdList.contains(lsp.getTunnelId())) {
							tunnelIdList.add(lsp.getTunnelId());
						}
					}
				}
				//根据tunnelid集合查询pw
				pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo, this.sqlSession);
				List<PwInfo> pwInfoList = pwInfoService.selectPwInfoByTunnelId(tunnelIdList);
				//遍历pw，比较pw的出标签是否有和outLabel相同的
				if (null != pwInfoList && pwInfoList.size() > 0) {
					
					for(PwInfo pwInfo : pwInfoList){
						// 如果此网元是A端， 前向标签为出标签
						if(pwInfo.getASiteId() == siteId){
							if (outLabel == pwInfo.getInlabelValue()) {
								result = false;
								break;
							}
						}else if(pwInfo.getZSiteId() == siteId){
							// 如果此网元是Z端，后向标签为出标签
							if (outLabel == pwInfo.getOutlabelValue()) {
								result = false;
								break;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	/**
	 * 从labelInfo里查找可用的label
	 * 找出A,Z端都可用的标签
	 * @param aSiteId
	 * @param zSiteId
	 * @param labelInfoDao
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public String matchingUsableLabel(int aSiteId, int zSiteId, Map<String, String> siteId_PortIdMap, List<Integer> labelValues, String type) throws Exception {
		int front_label = 0;
		int back_label = 0;
		String label = null;
		List<Integer> labelList = null;
		int aPortId = Integer.parseInt(siteId_PortIdMap.get(aSiteId+"-"+zSiteId).split("-")[0]);
		int zPortId = Integer.parseInt(siteId_PortIdMap.get(aSiteId+"-"+zSiteId).split("-")[1]);
		SiteService_MB siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE, this.sqlSession);
		//等于1是晨晓设备,入标签网元唯一
		int manufacturerA = siteService.getManufacturer(aSiteId);
		int manufacturerZ = siteService.getManufacturer(zSiteId);
		//目前设备的芯片不支持同一端口的lsp的入标签和该端口上的pw的入标签一样,所以要用下面的代码
		if(type.equals("TUNNEL") || type.equals("PW")){
			type = "WH";
		}
		//如果以后芯片支持同一端口的lsp的入标签和该端口上的pw的入标签一样,就用下面的代码,把上面的代码关掉
		//先分配前向标签,并验证出标签是否可用
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("aSiteId", aSiteId);
		map.put("zSiteId", zSiteId);
		map.put("condition", labelValues);
		map.put("type", type);
		map.put("manufacturerA", manufacturerA);
		map.put("manufacturerZ", manufacturerZ);
		labelList = this.mapper.queryLabelListBySite(map);
		Collections.shuffle(labelList);
		//this.removeRepeatedLabel(labelList);
		if(labelList.size() > 0){
			for (Integer integer : labelList) {
				if(checkingOutLabel(integer, aPortId, aSiteId, type)){
					front_label = integer;
					break;
				}
			}
		}
		labelList.clear();
		if (front_label == 0) {
			labelList = this.matchingNoLabel(aSiteId, zSiteId, labelValues, type);
			for (Integer integer : labelList) {
				if(checkingOutLabel(integer, aPortId, aSiteId, type)){
					front_label = integer;
					break;
				}
			}
		}
		//再分配后向标签
		labelValues.add(front_label);
		labelList.clear();
		map.put("aSiteId", aSiteId);
		map.put("zSiteId", zSiteId);
		map.put("condition", labelValues);
		map.put("type", type);
		map.put("manufacturerA", manufacturerA);
		map.put("manufacturerZ", manufacturerZ);
		labelList = this.mapper.queryLabelListBySite(map);
		Collections.shuffle(labelList);
		//this.removeRepeatedLabel(labelList);
		if(labelList.size() > 0){
			for (Integer integer : labelList) {
				if(checkingOutLabel(integer, zPortId, zSiteId, type)){
					back_label = integer;
					break;
				}
			}
		}
		labelList.clear();
		if (back_label == 0) {
			labelList = this.matchingNoLabel(aSiteId, zSiteId, labelValues,type);
			for (Integer integer : labelList) {
				if(checkingOutLabel(integer, zPortId, zSiteId, type)){
					back_label = integer;
					break;
				}
			}
		}
		label = front_label+","+back_label;
		return label;
	}

	/**
	 * 如果没有可用的标签，则根据规则分配标签
	 * @param asiteId
	 * @param zsiteId
	 * @param labelInfoDao
	 * @param connection
	 * @return
	 * @throws Exception
	 */
	public List<Integer> matchingNoLabel(int asiteId, int zsiteId, List<Integer> labelValues, String type) throws Exception {
		List<Integer> labelList = null;
		SiteService_MB siteService = null;
		try {
			//目前设备的芯片不支持同一端口的lsp的入标签和该端口上的pw的入标签一样,所以要用下面的代码
			if(type.equals("TUNNEL") || type.equals("PW")){
				type = "WH";
			}
			//如果以后芯片支持同一端口的lsp的入标签和该端口上的pw的入标签一样,就用下面的代码,把上面的代码关掉
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE, this.sqlSession);
			//等于1是晨晓设备,入标签网元唯一
			int manufacturerA = siteService.getManufacturer(asiteId);
			int manufacturerZ = siteService.getManufacturer(zsiteId);
			LabelManage labelManage = new LabelManage();
			Map<String, Object> map = new HashMap<String, Object>();
			while (true) {	
				labelManage.addLabel(asiteId, zsiteId, type, this.mapper);
				this.sqlSession.commit();
				map.put("aSiteId", asiteId);
				map.put("zSiteId", zsiteId);
				map.put("condition", labelValues);
				map.put("type", type);
				map.put("manufacturerA", manufacturerA);
				map.put("manufacturerZ", manufacturerZ);
			    labelList = this.mapper.queryLabelListBySite(map);
				if (labelList.size() > 0) {
					break;
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
//			UiUtil.closeService(siteService);
		}
		return labelList;
	}

	/**
	 * @param aSiteId
	 * @return
	 * @throws Exception
	 */
	public List<Integer> quertyAllLabel(int aSiteId, String type) throws Exception {
		SiteService_MB siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE, this.sqlSession);
		//等于1是晨晓设备,入标签网元唯一
		int manufacturer = siteService.getManufacturer(aSiteId);
//		UiUtil.closeService(siteService);
		if(manufacturer == 1){
			type = "CX";
		}
		//目前设备的芯片不支持同一端口的lsp的入标签和该端口上的pw的入标签一样,所以要用下面的代码
		if(type.equals("TUNNEL") || type.equals("PW")){
			type = "WH";
		}
		//如果以后芯片支持同一端口的lsp的入标签和该端口上的pw的入标签一样,就用下面的代码,把上面的代码关掉
		LabelInfo label = new LabelInfo();
		label.setSiteid(aSiteId);
		label.setType(type);
		return this.mapper.quertyAllLabel(label);
	}

	/**
	 * 根据标签值查询标签是否可用
	 * @param labelValue
	 * @param siteId
	 * @param connection
	 * @return
	 * @throws Exception
	 */
	public boolean isUsedLabel(int labelValue, int asiteId, String type) throws Exception {
		SiteService_MB siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE, this.sqlSession);
		int manufacturer = siteService.getManufacturer(asiteId);
		//等于1是晨晓设备,入标签网元唯一
		//目前设备的芯片不支持同一端口的lsp的入标签和该端口上的pw的入标签一样,所以要用下面的代码
		if(type.equals("TUNNEL") || type.equals("PW")){
			type = "WH";
		}
		//如果以后芯片支持同一端口的lsp的入标签和该端口上的pw的入标签一样,就用下面的代码,把上面的代码关掉
		String flag = null;
		if(manufacturer == 1){
			LabelInfo condition = new LabelInfo();
			condition.setLabelValue(labelValue);
			condition.setSiteid(asiteId);
			flag = this.mapper.isUsedLabelForCX(condition);
		}else{
			LabelInfo condition = new LabelInfo();
			condition.setLabelValue(labelValue);
			condition.setSiteid(asiteId);
			condition.setType(type);
			flag = this.mapper.isUsedLabel(condition);
		}
		if(flag == null){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 分配16到100的标签
	 */
	public void matchingLabel(int siteId, String type) throws Exception {
		int begin = 16;
		int end = 100;
		try {
			//目前设备的芯片不支持同一端口的lsp的入标签和该端口上的pw的入标签一样,所以要用下面的代码
			if(type.equals("TUNNEL") || type.equals("PW")){
				type = "WH";
			}
			//如果以后芯片支持同一端口的lsp的入标签和该端口上的pw的入标签一样,就用下面的代码,把上面的代码关掉
			for(int i = begin ; i <= end ; i++){
				LabelInfo labelInfo = new LabelInfo();
				labelInfo.setLabelStatus(1);
				labelInfo.setSiteid(siteId);
				labelInfo.setLabelValue(i);
				labelInfo.setType(type);
				if(i == end){
					labelInfo.setLsrId(1);
				}
				this.mapper.insert(labelInfo);
			}
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,LabelManage.class);
		}
	}
	
	public void insertNewLabel(int labelValue, int siteId, String type) {
		int manufacturer = 0;
		SiteService_MB siteService = null;
		try {
			//目前设备的芯片不支持同一端口的lsp的入标签和该端口上的pw的入标签一样,所以要用下面的代码
			if(type.equals("TUNNEL") || type.equals("PW")){
				type = "WH";
			}
			//如果以后芯片支持同一端口的lsp的入标签和该端口上的pw的入标签一样,就用下面的代码,把上面的代码关掉
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE, this.sqlSession);
			manufacturer = siteService.getManufacturer(siteId);
			if(manufacturer == 1){
				type = "CX";
			}
			LabelInfo condition = new LabelInfo();
			condition.setLsrId(labelValue%100 == 0 ? 1 : 0);
			condition.setLabelValue(labelValue);
			condition.setSiteid(siteId);
			condition.setType(type);
			this.mapper.insertNewLabel(condition);
			this.sqlSession.commit();
		}catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
//			UiUtil.closeService(siteService);
		}
	}

	public List<LabelInfo> selectUsedLabel(LabelInfo labelinfo) {
		List<LabelInfo> labelinfoList = new ArrayList<LabelInfo>();
		SiteService_MB siteService = null;
		try {
			//目前设备的芯片不支持同一端口的lsp的入标签和该端口上的pw的入标签一样,所以要用下面的代码
			if(labelinfo.getType().equals("TUNNEL") || labelinfo.getType().equals("PW")){
				labelinfo.setType("WH");
			}
			//如果以后芯片支持同一端口的lsp的入标签和该端口上的pw的入标签一样,就用下面的代码,把上面的代码关掉
			String type = labelinfo.getType();
			if(labelinfo.getSiteid() > 0 && ("TUNNEL".equals(type) || "PW".equals(type))){
				int manufacturer = 0;
				siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE, this.sqlSession);
				manufacturer = siteService.getManufacturer(labelinfo.getSiteid());
				if(manufacturer == 1)
					labelinfo.setType("CX");
			}
			labelinfoList = this.mapper.selectUsedLabel(labelinfo);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return labelinfoList;
	}
	
	/**
	 * 将所有已使用的标签状态改为未使用，0改为1
	 */
	public void initAllLabel(){
		try {
			this.mapper.initAllLabel();
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
}

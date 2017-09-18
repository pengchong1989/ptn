package com.nms.model.system;



import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.system.OffLinesiteBusi;
import com.nms.db.dao.system.OfflinesItebusiMapper;
import com.nms.db.enums.EActionType;
import com.nms.model.util.ObjectService_Mybatis;
/**
 * 离线网元业务下载Service
 * @author Dzy
 *
 */
public class OffLinesiteBusiService_MB extends ObjectService_Mybatis{
    private OfflinesItebusiMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	public OfflinesItebusiMapper getMapper() {
		return mapper;
	}

	public void setMapper(OfflinesItebusiMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * 搜索条件网元下 离线网元业务数据
	 * @param siteInstList 网元集合
	 * @return
	 * @throws Exception
	 */
	public List<OffLinesiteBusi> selectBySiteIds(List<SiteInst> siteInstList) throws Exception {
		return mapper.selectBySiteIds(siteInstList);
	}

	public int delete(OffLinesiteBusi offLinesiteBusi) throws Exception{
		int result=0;
		result=this.mapper.delete(offLinesiteBusi);
		this.sqlSession.commit();
		return result;
	}
	public List<OffLinesiteBusi> selectByCondition(OffLinesiteBusi offLinesiteBusi) throws Exception {
		return this.mapper.selectByCondition(offLinesiteBusi);
	}
	
	public int dataDownLoadAction(OffLinesiteBusi condition) throws Exception{
		int result = 0 ;
		List<OffLinesiteBusi> list = this.selectByCondition(condition);
		if(list.size()==0){
			this.mapper.insert(condition);
			result = condition.getId();
		}else{
			OffLinesiteBusi offLinesiteBusi = list.get(0);
			if(EActionType.UPDATE.getValue() == condition.getAction()){
				/*condition.setAction(offLinesiteBusi.getAction());
				return result = offLinesiteBusiDao.update(condition,connection);*/
			}else if(EActionType.DELETE.getValue() == condition.getAction()){
				if(list.get(0).getAction()==EActionType.INSERT.getValue()){
					return result = this.mapper.delete(offLinesiteBusi);
				}else{
					result = this.mapper.update(condition);
				}
			}
		}
		this.sqlSession.commit();
		return result;
		
	}
}

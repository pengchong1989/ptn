package com.nms.model.equipment.port;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.equipment.port.Port2LayerAttr;
import com.nms.db.dao.equipment.port.Port2LayerAttrMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class Port2LayerAttrService_MB extends ObjectService_Mybatis {
	private Port2LayerAttrMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	public void setMapper(Port2LayerAttrMapper mapper) {
		this.mapper = mapper;
	}
	
	public int saveOrUpdate(Port2LayerAttr port2LayerAttr) throws Exception {
		if (port2LayerAttr == null) {
			throw new Exception("port2LayerAttr is null");
		}

		int result = 0;
		try {
			if(port2LayerAttr.getId()==0){
				result = this.mapper.insert(port2LayerAttr);
			}else{
				result = this.mapper.update(port2LayerAttr);
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}
	
	public List<Port2LayerAttr> selectByCondition(Port2LayerAttr condition){
		List<Port2LayerAttr> port2LayerAttrList = new ArrayList<Port2LayerAttr>();
		try {
			port2LayerAttrList = this.mapper.selectByCondition(condition);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return port2LayerAttrList;
	}
	
}

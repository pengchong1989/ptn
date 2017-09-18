package com.nms.model.system;


import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.system.UdaAttr;
import com.nms.db.dao.system.UdaAttrMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;


public class UdaAttrService_MB extends ObjectService_Mybatis {

    private UdaAttrMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	public UdaAttrMapper getMapper() {
		return mapper;
	}

	public void setMapper(UdaAttrMapper mapper) {
		this.mapper = mapper;
	}

	 /**
     * 查询全部udaattr
     * @return UdaAttr集合
     * @throws Exception
     */
	public List<UdaAttr> select() throws Exception {
		List<UdaAttr> udaattrList=null;	
		try {
			UdaAttr udaattr = new UdaAttr();
			udaattrList = new ArrayList<UdaAttr>();
			udaattrList=this.mapper.queryByCondition(udaattr);
			if(udaattrList!=null && udaattrList.size()>0){
				for(int i=0;i<udaattrList.size();i++){
					if ("en_US".equals(ResourceUtil.language)) {
						udaattrList.get(i).setAttrName(udaattrList.get(i).getAttrENName());
					} else {
						udaattrList.get(i).setAttrName(udaattrList.get(i).getAttrName());
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return udaattrList;
	}
	
	 /**
     * 根据条件查询 udaattr
     * @param udaattr 查询条件
     * @return udaattr集合
     * @throws Exception
     */
	public List<UdaAttr> select(UdaAttr udaattr) throws Exception {
		List<UdaAttr> udaattrList=null;	
		try {
			udaattrList = new ArrayList<UdaAttr>();
			udaattrList=this.mapper.queryByCondition(udaattr);
			if(udaattrList!=null && udaattrList.size()>0){
				for(int i=0;i<udaattrList.size();i++){
					if ("en_US".equals(ResourceUtil.language)) {
						udaattrList.get(i).setAttrName(udaattrList.get(i).getAttrENName());
					} else {
						udaattrList.get(i).setAttrName(udaattrList.get(i).getAttrName());
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return udaattrList;
	}
	public int saveOrUpdate(UdaAttr udaAttr) throws Exception {

		if (udaAttr == null) {
			throw new Exception("udaAttr is null");
		}

		int result = 0;
		try {

			if (udaAttr.getId() == 0) {
				result = this.mapper.insert(udaAttr);
			} else {
				result = this.mapper.update(udaAttr);
			}
         this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}
	
	/**
     * 通过主键删除udaattr对象
     * @param id 主键
     * @return  删除记录数
     * @throws Exception
     */
	public int delete(int id) throws Exception {

		int result = 0;

		try {
			result = this.mapper.delete(id);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;

	}
}

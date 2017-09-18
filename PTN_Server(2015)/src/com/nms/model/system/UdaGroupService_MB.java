package com.nms.model.system;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.system.UdaAttr;
import com.nms.db.bean.system.UdaGroup;
import com.nms.db.dao.system.UdaAttrMapper;
import com.nms.db.dao.system.UdaGroupMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;


public class UdaGroupService_MB extends ObjectService_Mybatis {
	
		private UdaGroupMapper mapper = null;
		
		public void setPtnuser(String ptnuser) {
			super.ptnuser = ptnuser;
		}

		public void setSqlSession(SqlSession sqlSession) {
			super.sqlSession = sqlSession;
		}
		
		public UdaGroupMapper getMapper() {
			return mapper;
		}

		public void setMapper(UdaGroupMapper mapper) {
			this.mapper = mapper;
		}

		
		public int saveOrUpdate(UdaGroup udaGroup) throws Exception {

			if (udaGroup == null) {
				throw new Exception("udaGroup is null");
			}

			int result = 0;
			try {

				if (udaGroup.getId() == 0) {
					result = this.mapper.insert(udaGroup);
				} else {
					result = this.mapper.update(udaGroup);
				}
             this.sqlSession.commit();
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}
			return result;
		}
		
		/**
		 * 根据条件查询
		 * 
		 * @param udagroup
		 *            查询条件
		 * @return UdaGroup 集合
		 * @throws Exception
		 */
		public List<UdaGroup> select(UdaGroup udagroup) throws Exception {
			UdaAttrMapper udaAttrMapper = this.sqlSession.getMapper(UdaAttrMapper.class);
			UdaAttr udaattr = null;
			List<UdaAttr> udaattrList = null;
			List<UdaGroup> udagroupgroupList = null;
			try {
				udagroupgroupList =new ArrayList<UdaGroup>();
				udagroupgroupList=	this.mapper.queryByCondition(udagroup);
				if (null != udagroupgroupList && udagroupgroupList.size() != 0) {
					for (int i = 0; i < udagroupgroupList.size(); i++) {					
							udaattr = new UdaAttr();
							udaattr.setGroupId(udagroupgroupList.get(i).getId());
							udaattrList =new ArrayList<UdaAttr>();
							udaattrList = udaAttrMapper.queryByCondition(udaattr);
							udagroupgroupList.get(i).setUdaAttrList(udaattrList);
						}
//					}
				}
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			} finally{
				udaAttrMapper=null;
				udaattr = null;
				udaattrList =null;
			}
			return udagroupgroupList;
		}
		
		/**
		 * 查询UdaGroup全部
		 * 
		 * @return UdaGroup集合
		 * @throws Exception
		 */
		public List<UdaGroup> select() throws Exception {
			UdaGroup udaGroup =  null;
			UdaAttrMapper udaAttrMapper =null;
			UdaAttr udaattr = null;
			List<UdaGroup> udagroupgroupList =null;
			List<UdaAttr> udaattrList = null;
			try {
				udaAttrMapper=this.sqlSession.getMapper(UdaAttrMapper.class);			
				udagroupgroupList =new ArrayList<UdaGroup>();
				udagroupgroupList=	this.mapper.queryByCondition(new UdaGroup());
				if (null != udagroupgroupList && udagroupgroupList.size() != 0) {
					for (int i = 0; i < udagroupgroupList.size(); i++) {
						if (udagroupgroupList.get(i).getParentId() == -1) {
							udaGroup = new UdaGroup();
							udaGroup.setParentId(udagroupgroupList.get(i).getId());
							List<UdaGroup> childUdaGroupList =new ArrayList<UdaGroup>();
							childUdaGroupList=this.mapper.queryByCondition(udaGroup);
							if (null != childUdaGroupList) {
								for (int j = 0; j < childUdaGroupList.size(); j++) {
									udaattr = new UdaAttr();
									udaattr.setGroupId(childUdaGroupList.get(j).getId());
									udaattrList =new ArrayList<UdaAttr>();
									udaattrList=udaAttrMapper.queryByCondition(udaattr);
									childUdaGroupList.get(j).setUdaAttrList(udaattrList);
								}
							}
							udagroupgroupList.get(i).setChildUdaGroupList(
									childUdaGroupList);
						} else {
							udaattr = new UdaAttr();
							udaattr.setGroupId(udagroupgroupList.get(i).getId());						
							udaattrList =new ArrayList<UdaAttr>(); 
							udaattrList=udaAttrMapper.queryByCondition(udaattr);
							udagroupgroupList.get(i).setUdaAttrList(udaattrList);
						}
					}
				}
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			} finally{
				udaGroup = null;
				udaAttrMapper = null;
				udaattr =null;
				udaattrList =null;
			}
			return udagroupgroupList;
		}
	
		/**
		 * 根据udagroup删除
		 * 
		 * @param udagroup
		 *            删除条件
		 * @throws Exception
		 */
		public void delete(UdaGroup udagroup) throws Exception {
			try {
				this.sqlSession.getConnection().setAutoCommit(false);
				this.mapper.delete(udagroup.getId());
                UdaAttrMapper udaAttrMapper=this.sqlSession.getMapper(UdaAttrMapper.class);
				UdaAttr udaattr = new UdaAttr();
				udaattr.setGroupId(udagroup.getId());
 
				udaAttrMapper.delete(udaattr.getId());
				if(!this.sqlSession.getConnection().getAutoCommit()){
					this.sqlSession.getConnection().commit();
				}
			} catch (Exception e) {
				this.sqlSession.getConnection().rollback();
				ExceptionManage.dispose(e,this.getClass());
			} finally {
				this.sqlSession.getConnection().setAutoCommit(true);
			}
		}
}

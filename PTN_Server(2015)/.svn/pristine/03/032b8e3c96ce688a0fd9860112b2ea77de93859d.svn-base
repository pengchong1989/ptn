package com.nms.db.dao.ptn;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.CccInfo;





public interface CccInfoMapper {
	/**
	 * 查询一个最大的serviceid
	 * @return
	 */
	public List<Integer> selectMaxServiceId();
	
	/**
	 * 新增cccinfo 
	 * @param cccinfo
	 * @param connection
	 * @return 插入的记录数
	 */
	public int insert(CccInfo cccinfo);
	
	/**
	 * 
	 * 判断ac是否别其他的所使用
	 * @param connection
	 * @throws Exception
	 */
	public List<CccInfo> isRelatedAcByEline(@Param("acId")int acId);
	
	/**
	 * 
	 * 判断ac是否别其他的所使用
	 * @param pwId
	 * @param connection
	 * @return
	 * @throws Exception
	 */
	
	public List<CccInfo> isRelatedAc();
	
	public int update(CccInfo cccinfo);
    
    List<CccInfo> queryByAcIdAndSiteIdCondition(@Param("siteId")int siteId);
    
    /**
	 * 通过条件查询
	 * 
	 * @param etreeinfocondition
	 *            查询条件
	 * @param connection
	 *            数据库连接
	 * @return List<Etreeinfo>集合
	 * @throws Exception
	 */
	public List<CccInfo> queryByCondition(CccInfo cccinfocondition);
	
	public void updateStatus(@Param("siteId")int siteId,@Param("status")int status);
	
	public int delete(@Param("serviceId")int serviceId);
	
	public void updateStatusIds(@Param("ids")List<Integer> idList,@Param("status")int status);
	
	/**
	 * 查询单网元下的所有cccInfo
	 * @param siteId
	 * @return
	 */
	public List<CccInfo> queryNodeBySite(@Param("siteId")int siteId);
	
	/**
	 * 同步ccc时 查询
	 * query_synchro(这里用一句话描述这个方法的作用)
	 * @return
	 */
	public List<CccInfo> querySynchro(@Param("siteId")int siteId,@Param("xcid")int xcid);
	
	/**
	 * 查询名称是否重复
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	public int query_nameBySingle(@Param("afterName")String afterName,@Param("beforeName")String beforeName,@Param("siteId")int siteId);
	
	public int deleteBySiteId(@Param("siteId")int siteId);
}
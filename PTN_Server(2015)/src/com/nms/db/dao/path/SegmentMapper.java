package com.nms.db.dao.path;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.path.Segment;


public interface SegmentMapper {
	public int deletById(int id);
	
	public List<Segment> queryByCondition(@Param("segment")Segment segment);
	
	public int insert(Segment segment);
	
	public int update(Segment segment);
	
	/**
	 * 根据siteId和portId去查询
	 * 获取参数时取segmentCondition的aSiteId和aPortId
	 */
	public List<Segment> queryBySiteIdAndPortId(Segment segmentCondition);
	
	public List<Segment> query_site(Integer siteId);
	
	/**
	 * 根据两端端口ID查询数据
	 * 
	 * @author kk
	 * 
	 * @param portid_one
	 *            其中一个端口主键
	 * @param portid_two
	 *            第二个端口主键
	 * @param connection
	 *            数据库连接
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	public List<Segment> query_search(@Param("portIdOne")int portid_one,@Param("portIdTwo")int portid_two);

	public List<Segment> query_SegmentPortId(int portId);
	
	/**
	 * 查询名字
	 * @param afterName
	 * @param beforeName
	 * @return
	 */
	public int query_name(@Param("afterName")String afterName,@Param("beforeName")String beforeName);

	public List<Segment> select_north();
	
}

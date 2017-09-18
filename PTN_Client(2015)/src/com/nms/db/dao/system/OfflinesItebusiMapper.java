package com.nms.db.dao.system;

import java.util.List;

import com.nms.db.bean.system.OffLinesiteBusi;



public interface OfflinesItebusiMapper {
	public int delete(OffLinesiteBusi offLinesiteBusi);

    public int insert(OffLinesiteBusi record);

    public int update(OffLinesiteBusi record);
    
    /**
	 * 搜索条件网元下 离线网元业务数据
	 * @param allSelect
	 *            网元集合
	 * @param connection 
	 */
	public List<OffLinesiteBusi> selectByCondition(OffLinesiteBusi condition);
}
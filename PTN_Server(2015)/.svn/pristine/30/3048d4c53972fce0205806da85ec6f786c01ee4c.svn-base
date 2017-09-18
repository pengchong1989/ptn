package com.nms.db.dao.ptn;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.LabelInfo;


public interface LabelInfoMapper {
	public void insert(LabelInfo labelInfo);

	public void updateBatch(LabelInfo labelInfo);

	public List<LabelInfo> quertyByLabelValue(LabelInfo labelInfo);

	public int delete(int id);

	public List<LabelInfo> queryByCondition(LabelInfo labelinfo);

	public List<LabelInfo> queryByLabelvalues(Map<String, Object> map);

	public List<LabelInfo> queryByLabelvaluesForCX(Map<String, Object> map);

	public List<Integer> queryLabelListBySite(Map<String, Object> map);
	
	public List<Integer> quertyAllLabel(LabelInfo label);

	public String isUsedLabelForCX(LabelInfo condition);

	public String isUsedLabel(LabelInfo condition);

	public void insertNewLabel(LabelInfo condition);

	public void initAllLabel();

	public String queryMaxLabelValue(LabelInfo condition);

	public List<LabelInfo> selectUsedLabel(LabelInfo labelinfo);
	
	public void deleteBySite(int siteId);
	
	public void updateLabelStatus(int siteInstId);
}
package com.nms.db.dao.ptn;

import java.util.List;

import com.nms.db.bean.ptn.ARPInfo;


public interface ARPMapper {

	public List<ARPInfo> queryBySiteId(int siteId);

	public void insert(ARPInfo arpInfo);

	public void delete(int id);

	public void update(ARPInfo arpInfo);

	public ARPInfo queryById(int id);

	public void deleteBySiteId(int siteId);

	public ARPInfo select_synchro(int siteId, int pwProtectId);

}

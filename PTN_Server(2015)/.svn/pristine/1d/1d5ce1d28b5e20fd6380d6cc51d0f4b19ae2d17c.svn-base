package com.nms.db.dao.equipment.shelf;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.system.Field;
import com.nms.db.bean.system.user.UserInst;

public interface SiteInstMapper {

    public SiteInst selectByPrimaryKey(Integer siteInstId);
   
    public int insert(@Param("siteInst")SiteInst siteInst);
    
    public int selectFieldIsSingle(@Param("field")Field field);
    
    public List<SiteInst> queryByFields(@Param("fieldList")List<Integer> fieldList);
    
    public List<SiteInst> queryByIsMsite();
    
    public List<SiteInst> queryBySn(@Param("sn")String sn);
    
    public List<SiteInst> queryWHByCondition();
    
    public List<SiteInst> queryByCondition(@Param("siteInst")SiteInst siteInst);
   
    public List<SiteInst> queryByNeaddress(@Param("siteInst")SiteInst siteInst);
    
    public List<SiteInst> queryByLogin(@Param("loginState")Integer loginState);
    
    public int updateLoginStatusByIp(@Param("ip")String ip,@Param("loginState")Integer loginState);
    
    public int queryNeStatus(@Param("siteId")Integer siteId);
    
    public int update(@Param("siteInst")SiteInst siteInst);
    
    public List<Integer> queryByhumId(@Param("humId")Integer humId);

    public List<SiteInst> queryBySiteCount();
    
    public List<SiteInst> queryRootSiteByUserId(@Param("userInst")UserInst userInst);
    
    public int delete(@Param("siteId")Integer siteId);

	public void updateOnline(SiteInst siteInst);
    
    
}
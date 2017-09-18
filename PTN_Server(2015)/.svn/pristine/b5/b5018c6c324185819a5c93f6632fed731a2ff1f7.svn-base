package com.nms.db.dao.ptn.path.tunnel;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.path.tunnel.Tunnel;


public interface TunnelMapper {
    int deleteByPrimaryKey(Integer tunnelid);

    int insert(Tunnel record);

    int insertSelective(Tunnel record);

    Tunnel selectByPrimaryKey(Integer tunnelid);

    int updateByPrimaryKeySelective(Tunnel record);

    public int updateByPrimaryKey(Tunnel record);
    
    List<Tunnel> selectAll();
    
    /**
     * 根据siteId和portId查询
     * 参数从tunnelCondition的aSiteId和aPortId取
     * @param tunnelCondition
     * @return
     */
	public List<Tunnel> queryBySiteIdAndPortId(Tunnel tunnelCondition);
	/**
	 * 查询单网元的所有tunnel 带xc 带保护tunnel
	 * @param siteId
	 * @return
	 */
	public List<Tunnel> quertyWHNodeBySite(Integer siteId);
	/**
	 * 根据两个端口查询出跟这两个端口相关联的所有tunnel 段模块中查询剩余带宽时用 
	 * @param portId1
	 *            端口id=段的aportid
	 * @param portId2
	 *            端口id=段的zportid
	 * @return
	 */
	public List<Tunnel> queryPort(@Param("portId1")Integer portId1, @Param("portId2")Integer portId2);
	
	/**
	 * 通过portId和siteId查询tunnel
	 * @param siteId
	 * @param serviceId
	 * @return
	 */
	public List<Tunnel> queryByPortIdAndServiceId(@Param("siteId")Integer siteId,@Param("portId")Integer portId);

	/**
	 * 条件查询网络侧
	 * @param tunnel
	 * @return
	 */
	List<Tunnel> queryByCondition(Tunnel tunnel);

	/**
	 * 非关联表条件查询
	 * @param tunnel
	 * @return
	 */
	List<Tunnel> queryByCondition_nojoin(Tunnel tunnel);

	/**
	 * 修改激活状态
	 * @param tunnel
	 */
	void updateStatus(Tunnel tunnel);

	/**
	 * 查询名字是否存在
	 * @param afterName
	 * @param beforeName
	 * @param siteId
	 * @return
	 */
	int nameRepetition(Map<String, Object> map);

	/**
	 * 通过业务id和网元id查询
	 * @param siteId
	 * @param serviceId
	 * @return
	 */
	Tunnel queryBySiteIdAndServiceId(@Param("siteId")int siteId,@Param("serviceId") int serviceId);

	/**
	 * 验证tunnel是否存在
	 * @param tunnel
	 * @return
	 */
	List<Tunnel> queryExistTunnel(Tunnel tunnel);

	Tunnel queryBySiteIdAndProtectId(int siteId, int protectId);

	List<Tunnel> querySNCPbySiteId(int asiteId, int zsiteId, String string);

	List<Tunnel> selectBySiteId(int siteId);

	/**
	 * 查询同一端口下是否有多条tunnel
	 * @param portId
	 * @return
	 */
	List<Integer> checkPortUsable(int portId);

	/**
	 * 通过a、z端网元ID查询tunnel
	 * @param aSiteId
	 * @param zSiteId
	 * @return
	 */
	List<Tunnel> selectByASiteIdAndZSiteId(int aSiteId, int zSiteId);

	List<Tunnel> queryTunnelBySiteId(int siteId);

	/**
	 * 单站过滤刷新
	 * @param siteId
	 * @param tunnel
	 * @return
	 */
	List<Tunnel> filterSelectNE(@Param("siteId")int siteId, @Param("tunnel")Tunnel tunnel);

	List<Tunnel> filterSelect(Tunnel tunnelFilter);

	List<Tunnel> searchWh(int siteID);

	int insert_searchWH(int tunnelId, int tunnelId2, String newTunnelName);

	void updateProtectTunnelID(int newTunnelId, String tid);

	/**
	 * 搜索时，批量删除
	 * @param tid
	 */
	void deleteByTunnelIds(String tid);

	List<Tunnel> selectTunnelNameBySiteId(int siteId);

	List<Tunnel> queryByportId(int portId);

	List<Integer> selectTunnelIdByTunnelName(@Param("tunnelName")String tunnelName);

	List<Tunnel> quertyNodeBySite(int siteId);

	Tunnel queryBySiteIdAndTunnelId(@Param("siteId")int siteId, @Param("tunnelId")int tunnelId);
	
	List<Tunnel> queryProtectTunnel(Tunnel tunnel);

	public List<Tunnel> queryBySiteId(int siteId);

	void updateStatusIDs(@Param("idList")List<Integer> idList,@Param("type")int type);

	void update_activity(int siteId, int activity);

	public List<Tunnel> queryByCondition_joinRotate(Tunnel tunnel);

	public List<Tunnel> quertyNodeBySiteAndType(@Param("siteId")int siteId, @Param("tunnelType")String tunnelType, @Param("isSingle")int isSingle, @Param("flag")int flag);
	
}
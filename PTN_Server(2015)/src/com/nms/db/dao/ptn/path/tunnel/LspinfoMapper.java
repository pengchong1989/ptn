package com.nms.db.dao.ptn.path.tunnel;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.path.tunnel.Lsp;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;


public interface LspinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Lsp record);

    int insertSelective(Lsp record);

    Lsp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Lsp record);

    int updateByPrimaryKey(Lsp record);
    
    public List<Lsp> queryByCondition(Lsp lsp);
    /**
	 * 根据网元id查询
	 * @param siteId
	 * @return
	 */
    public List<Lsp>  queryBySiteId(@Param("siteId")Integer siteId,@Param("tunnelId")Integer tunnelId);

	List<Lsp> queryByTunnnelId(int protectTunnelId);

	void deleteByTunnelID(int protectTunnelId);

	List<Lsp> selectByPort(int portId);

	void insert_lspWhSearchdo(int newTunnelId, @Param("lsp")Lsp lsp, @Param("lsp2")Lsp lsp2);

	public List<Lsp> queryByPort(int portId);

	Lsp queryOutLabelUsable(int portId, int label);
	
	public List<Lsp> selectBySiteId(@Param("siteId")int siteId);

	public List<Lsp> queryBySegmentId(int segmentId);


	List<Lsp> querySychro(@Param("role")String role, @Param("siteId")int siteId, @Param("tunnel")Tunnel tunnel,@Param("size")int size,@Param("atunnelbusinessid")int atunnelbusinessid,@Param("ztunnelbusinessid")int ztunnelbusinessid);

	List<Lsp> queryBySiteIdAndTunnelId(int siteId, int tunnelId);

	List<Lsp> selectBusinessId(int siteId);
	
	public List<Lsp> queryBySiteIdAndTunnelserviceid(@Param("siteId")int siteId,@Param("tunnelServiceId")int tunnelServiceId,@Param("type")String type);
}
package com.nms.db.dao.report;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.report.SSAlarm;
import com.nms.db.bean.report.SSCard;
import com.nms.db.bean.report.SSLabel;
import com.nms.db.bean.report.SSPath;
import com.nms.db.bean.report.SSPort;
import com.nms.db.bean.report.SSProfess;



public interface StaticsticsMapper {
	/**
	 * 单板信息统计
	 */
	public List<SSCard> queryCard();
	
	public List<SSAlarm> queryByCountAlarmLevel();
	
	public List<SSProfess> queryBusinessOfTunnelList();
	
	public List<SSProfess> queryBusinessOfPwList();
	
	public List<SSProfess> queryBusinessOfMsPwList();
	
	public List<SSProfess> queryBusinessOfElineAndCesList();
	
	public List<SSProfess> queryBusinessOfEtreeList();
	
	/**
	 * 统计业务列表
	 * @param connection
	 * @throws Exception 
	 */
	public List<SSProfess> SSBusinessByPortId(@Param("type")String type ,@Param("portId")Integer portId);
	
	public List<SSProfess> SSBusinessEthByPortId(@Param("portId")Integer portId);
	
	public PortInst queryPwPortName(@Param("tunnelId")int tunnelId,@Param("siteName")String siteName);
	
	public PortInst queryPortName(@Param("acId")int acId);
	
	/**
	 * 查询还可用的标签数
	 */
	public List<SSLabel> queryCanUsedLabel();
	
	/**
	 * 查询标签总数
	 */
	public List<SSLabel> queryCountLabel();
	
	public List<SSPort> querySSPort();
	
	/**
	 * 统计查询全网的隧道、伪线数量。
	 */
	public List<SSPath> queryPathCount();
	
	/**
	 * 统计查询单网元的隧道、伪线数量
	 */
	public List<SSPath> queryPathCount_site();
	
}
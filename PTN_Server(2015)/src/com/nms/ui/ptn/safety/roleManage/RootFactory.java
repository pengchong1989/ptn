package com.nms.ui.ptn.safety.roleManage;
/**
 * 权限验证标签  
 * label的值  -与数据库对应
 * @author sy
 *
 */
public class RootFactory {
	public static final int SYSTEMMODU=1;//系統模塊
	public static final int TOPOLOGYMODU=2;//拓撲模塊
	public static final int DEPLOYMODU=3;//配置模塊
	public static final int COREMODU=4;//核心業務
	public static final int ALARMMODU=5;//告警管理
	public static final int PROFORMANCEMODU=6;//性能模塊
	public static final int COUNTMODU=7;//統計模塊
	public static final int SATYMODU=8;//安全模塊
	public static final int SYSTEM_MANAGE=9;//系統管理
	public static final int SYSTEM_SELECT=10;//系統查看
	public static final int TOPOLOGY_MANAGE=11;//拓撲管理：包括拓扑右键菜单  ：创建子网
	public static final int TOPOLOGY_SELECT=12;//拓撲查看
	public static final int DEPLOY_MANAGE=13;//配置管理  :包括拓扑右键菜单（除创建子网）
	public static final int DEPLOY_SELECT=14;//配置查看
	public static final int CORE_MANAGE=15;//核心業務管理  ：//包括功能树、即左侧网元所有信息
	public static final int CORE_SELECT=16;//核心業務查看
	public static final int ALARM_MANAGE=17;//告警管理
	public static final int ALARM_SELECT=18;//告警查看
	public static final int PROFOR_MANAGE=19;//性能管理
	public static final int PROFOR_SELECT=20;//性能查看
	public static final int COUNT_SELECT=21;//統計查看    :注 ：导出功能属于查看
	public static final int SATY_MANAGE=22;//安全管理 ;只有默认角色可以操作此模块  Administrator,Operator
	public static final int SATY_SELECT=23;//安全查看
	public static final int SATY_SELECTOTHRER=24;//安全查看
}

package com.nms.ui.ptn.alarm;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import twaver.AlarmSeverity;
import twaver.table.TTableColumn;

import com.nms.db.bean.alarm.CurrentAlarmInfo;
import com.nms.db.bean.alarm.WarningLevel;
import com.nms.db.enums.EObjectType;
import com.nms.model.alarm.AlarmVoiceService_MB;
import com.nms.model.alarm.CurAlarmService_MB;
import com.nms.model.util.Services;
import com.nms.ui.frame.RowIDRenderer;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysTab;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.alarm.model.AlarmVoiceInfo;

/**
 * 告警信息的工具类
 * 
 * @author lp
 * 
 */
public class AlarmTools {
	private int criticalColorRGB = -1564897;//默认紧急告警:红色
	private int majorColorRGB = -26368;//默认主要告警:橙色
	private int minorColorRGB = -1447651;//默认次要告警:黄色
	private int warningColorRGB = -7368720;//默认提示告警:紫色
	
	public AlarmTools(){
		
		//需要加载数据库里面的告警颜色
		AlarmVoiceService_MB alarmService = null;
		try {
			alarmService = (AlarmVoiceService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ALARMVOICE);
			List<AlarmVoiceInfo> voiceList = alarmService.queryAllVoice();
			for (AlarmVoiceInfo voice : voiceList) {
				if(voice.getAlarmType() == 1){
					this.criticalColorRGB = voice.getAlarmColorRGB();
				}else if(voice.getAlarmType() == 2){
					this.majorColorRGB = voice.getAlarmColorRGB();
				}else if(voice.getAlarmType() == 3){
					this.minorColorRGB = voice.getAlarmColorRGB();
				}else if(voice.getAlarmType() == 4){
					this.warningColorRGB = voice.getAlarmColorRGB();
				}
			}
			this.initAlarmSeverity();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(alarmService);
		}
		
	}
	
	/**
	 * 在没有必要加载://需要加载数据库里面的告警颜色时 可以通过这个来初始化 AlarmTools 对象
	 * @param label
	 */
	public AlarmTools(int label){}
	
	/**
	 * 初始化告警等级
	 */
	private void initAlarmSeverity(){
		AlarmSeverity.WARNING.setDisplayName(ResourceUtil.srcStr(StringKeysObj.ALARMSEVERITY_WARNING));
		AlarmSeverity.CRITICAL.setColor(new Color(this.criticalColorRGB));			
		AlarmSeverity.MAJOR.setColor(new Color(this.majorColorRGB));
		AlarmSeverity.MINOR.setColor(new Color(this.minorColorRGB));
		AlarmSeverity.WARNING.setColor(new Color(this.warningColorRGB));
		AlarmSeverity.CLEARED.setColor(Color.green);
	}
	
	/**
	 * 告警table表头
	 */
	public TTableColumn[] createDefaultColumns() {
		TTableColumn objColumn = createColumn("obj", ResourceUtil.srcStr(StringKeysObj.ORDER_NUM), 0, false);
		TTableColumn indexColumn = createColumn("index", ResourceUtil.srcStr(StringKeysObj.ORDER_NUM), 40, true);
		indexColumn.setRenderer(new RowIDRenderer());

		return new TTableColumn[] { objColumn, indexColumn,
		        // 告警级别
				createColumn("alarmSeverity", ResourceUtil.srcStr(StringKeysObj.ALARM_LEVEL), 100, true),
				// 网元编号
				createColumn("siteName", ResourceUtil.srcStr(StringKeysObj.STRING_SITE_NAME), 80, true), 
				createColumn("alarmSource", ResourceUtil.srcStr(StringKeysObj.STRING_ALARM_SOURCE), 80, true), 
				// 告警名称
				createColumn("warningNotes", ResourceUtil.srcStr(StringKeysObj.STRING_ALARM_DETAIL), 120, true),
				// 告警描述
				createColumn("alarmDesc", ResourceUtil.srcStr(StringKeysTab.TAB_ALARM), 120, true), 
				// 告警类型
				createColumn("warningTypes", ResourceUtil.srcStr(StringKeysObj.STRING_ALARM_TYPE), 80, true),
				
				// createColumn(Alarm.PROPERTY_ALARMTYPE, 60, true),
				createColumn("ackUser", ResourceUtil.srcStr(StringKeysObj.STRING_CONFIRM_USER), 80, true),
				// 发生时间
				createColumn("raisedTime", ResourceUtil.srcStr(StringKeysObj.HAPPENED_TIME), 120, true),
				// 确认时间
				createColumn("ackTime", ResourceUtil.srcStr(StringKeysObj.CONFIRM_TIME), 120, true),
				//是否清除
				createColumn("isCleared", ResourceUtil.srcStr(StringKeysObj.IS_CLEARED), 120, true),
				// 清除时间
				createColumn("clearedTime", ResourceUtil.srcStr(StringKeysObj.CLEAR_TIME), 120, true),
				//备注
				createColumn("remarks", ResourceUtil.srcStr(StringKeysLbl.LBL_ALARM_REMARK), 120, true),
			};
	}

	private TTableColumn createColumn(String name, String display, int width, boolean visible) {
		return new TTableColumn(name, display, width).setVisible(visible);
	}

	/**
	 * 告警级别
	 */
	public AlarmSeverity getAlarmSeverity(int value) {
		AlarmSeverity type = null;
		switch (value) {
		case 0:
			type = AlarmSeverity.CLEARED;
			break;
		case 1:
			type = AlarmSeverity.INDETERMINATE;
			break;
		case 2:
			type = AlarmSeverity.WARNING;
			break;
		case 3:
			type = AlarmSeverity.MINOR;
			break;
		case 4:
			type = AlarmSeverity.MAJOR;
			break;
		case 5:
			type = AlarmSeverity.CRITICAL;
			break;
		default:
			type = null;
			break;
		}
		return type;
	}

	/**
	 * 告警类型
	 */
	public String getAlarmType(int value) {
		String str = null;
		switch (value) {
		case 1:
			str = ResourceUtil.srcStr(StringKeysObj.STRING_COMMUNICATION_ALARM);//通讯告警
			break;
		case 2:
			str = ResourceUtil.srcStr(StringKeysObj.STRING_SERVICE_QUALITY_ALARM);//服务质量告警
			break;
		case 3:
			str = ResourceUtil.srcStr(StringKeysObj.STRING_EQUIPMENT_ALARM);//设备告警
			break;
		case 4:
			str = ResourceUtil.srcStr(StringKeysObj.STRING_DO_ERROR_ALARM);//处理错误告警
			break;
		case 5:
			str = ResourceUtil.srcStr(StringKeysObj.STRING_ENVIRONMENT_ALARM);//环境告警
			break;
		case 6:
			str = ResourceUtil.srcStr(StringKeysObj.STRING_EQUIPPOWER_ALARM);//设备电源告警
			break;
		default:
			str = ResourceUtil.srcStr(StringKeysObj.STRING_COMMUNICATION_ALARM);
			break;
		}
		return str;
	}
	
	/**
	 * 产生一条告警
	 * @param userInst
	 * @return
	 */
	private CurrentAlarmInfo userLockAlarm(int alarmCode,int level,String objectInfo){
		CurrentAlarmInfo userLockAlarm = new CurrentAlarmInfo();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		try {
			userLockAlarm.setAlarmCode(alarmCode);
			userLockAlarm.setAlarmLevel(level);
			userLockAlarm.setObjectName(ResourceUtil.srcStr(StringKeysTip.TIP_LABEL_EMSSERVICE)+objectInfo);
			userLockAlarm.setSiteName(objectInfo);
			userLockAlarm.setRaisedTime(date);
			userLockAlarm.setAlarmTime(sdf.format(date));
			userLockAlarm.setWarningLevel_temp(level);
			userLockAlarm.setObjectType(EObjectType.EMSCLIENT);
			WarningLevel warningLevel = new WarningLevel();
			warningLevel.setWarningcode(userLockAlarm.getAlarmCode());
			warningLevel.setWarninglevel(userLockAlarm.getAlarmLevel());
			userLockAlarm.setAlarmSeverity(this.getAlarmSeverity(warningLevel.getWarninglevel()));
			userLockAlarm.setWarningLevel(warningLevel);
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,AlarmTools.class);
		}finally
		{
			sdf = null;
			date = null;
		}
		return userLockAlarm;
	}
	
	/**
	 * 
	 * 
	 * @param userInst
	 * @param alarmCode 10052 代表数据库总内存对应的告警信息
	 *                  10053 代表每张表对应的告警
	 */
	public void createMointorClintAndService(int alarmCode,int level,String objectInfo,int label) throws Exception{
		CurAlarmService_MB curAlarmService = null;
		List<CurrentAlarmInfo> currentAlarmList = null;
		try {
			curAlarmService = (CurAlarmService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CurrentAlarm);
			CurrentAlarmInfo currentAlarmInfo = userLockAlarm(alarmCode,level,objectInfo);
			if(label  == 0)
			{
				currentAlarmList = curAlarmService.queryClientAlarm(alarmCode, level);	
			}else
			{
				currentAlarmList = curAlarmService.queryClientDISCAlarm(alarmCode, level,currentAlarmInfo.getObjectName());	
			}
			if(currentAlarmList !=null && currentAlarmList.size()>0){
				currentAlarmInfo.setId(currentAlarmList.get(0).getId());
			}
			curAlarmService.saveOrUpdate(currentAlarmInfo);
		} finally{
			UiUtil.closeService_MB(curAlarmService);
			currentAlarmList = null;
		}
	}
}
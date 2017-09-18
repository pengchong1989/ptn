package com.nms.corba.ninterface.impl.resource.tool;

import globaldefs.NameAndStringValue_T;

import java.util.Iterator;
import java.util.List;

import clockSource.ClockSourceQualityLevel_T;
import clockSource.ClockSourceType_T;
import clockSource.ClockSource_T;
import clockSource.ClockSyncState_T;

import com.nms.corba.ninterface.util.CorbaConvertBase;
import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.db.bean.ptn.clock.ClockSource_Corba;
import com.nms.db.enums.EManufacturer;
import com.nms.ui.manager.UiUtil;
/**
 * 时钟转换类
 * @author dzy
 *
 */
public class CorbaClockTool extends CorbaConvertBase{

	/**
	 * 对象集合转换
	 * @param clockSource_CorbaList  时钟源数据库模型集合
	 * @param clockList 时钟源Corba 模型集合
	 * @throws Exception
	 */
	public void convertClockListDataX2Corba(
			List<ClockSource_Corba> clockSource_CorbaList, ClockSource_T[] clockList,int manufacturer) throws Exception {
		int i = 0;
		for(Iterator<ClockSource_Corba> iter = clockSource_CorbaList.iterator(); iter.hasNext();){
			clockList[i] = new ClockSource_T();
			convertClockSourceDataX2Corba((ClockSource_Corba)iter.next(), clockList[i],manufacturer);
			i++;
		}
	}
	
	/**
	 * 时钟源对象转换
	 * @param clockSource_Corba 时钟源数据库模型
	 * @param clockSource_T		时钟源Corba 模型
	 * @throws Exception
	 */
	private void convertClockSourceDataX2Corba(
			ClockSource_Corba clockSource_Corba, ClockSource_T clockSource_T,int manufacturer) throws Exception {
		int qualityLevel;
		int syncState;
		clockSource_T.name = super.convertName(ELayerRate.CLOCKSOURCE.getValue(), clockSource_Corba.getId(), clockSource_Corba.getSiteId());
		//时钟源等级
		switch(clockSource_Corba.getType()){
			case 0:
				clockSource_T.type=ClockSourceType_T.CST_EXTERNAL;
				break;
			case 1:
				clockSource_T.type=ClockSourceType_T.CST_LINE;
				break;
			case 2:
				clockSource_T.type=ClockSourceType_T.CST_INNER;
				break;
			case 3:
				clockSource_T.type=ClockSourceType_T.CST_HOLD;
				break;
			case 4:
				clockSource_T.type=ClockSourceType_T.CST_AUTO;
				break;
		}
		
		clockSource_T.userLabel = clockSource_Corba.getName();
		clockSource_T.nativeEMSName = clockSource_Corba.getName();
		clockSource_T.location = "";
		clockSource_T.priority = clockSource_Corba.getPriorityLevel();
		clockSource_T.ifCurrentClockSource = clockSource_Corba.getStatus() == 1 ? true : false;
		clockSource_T.ifUseSSM = clockSource_Corba.getSsmEnable() == 1 ? true : false;
		clockSource_T.additionalInfo = new NameAndStringValue_T[0]; 
		
		clockSource_T.syncState=ClockSyncState_T.CSS_NA;
		clockSource_T.qualityLevel=ClockSourceQualityLevel_T.CSQ_LEVELUNKNOWN;
		//质量等级
		clockSource_T.qualityLevel=ClockSourceQualityLevel_T.CSQ_LEVELUNKNOWN;
		if((manufacturer == EManufacturer.CHENXIAO.getValue()&&2==clockSource_Corba.getType())){
			if(clockSource_Corba.getQualityLevel() != -1){
				qualityLevel = Integer.parseInt(UiUtil.getCodeById(clockSource_Corba.getQualityLevel()).getCodeValue());
				switch(qualityLevel){
					case 0://G811
						clockSource_T.qualityLevel=ClockSourceQualityLevel_T.CSQ_G811;
						break;
					case 1://G812TRANSIT
						clockSource_T.qualityLevel=ClockSourceQualityLevel_T.CSQ_G812TRANSIT;
						break;
					case 2://G812LOCAL
						clockSource_T.qualityLevel=ClockSourceQualityLevel_T.CSQ_G812LOCAL;
						break;
					case 3://G813
						clockSource_T.qualityLevel=ClockSourceQualityLevel_T.CSQ_G813;
						break;
					case 4://不可用作同步
						clockSource_T.qualityLevel=ClockSourceQualityLevel_T.CSQ_NOTFORSYNCLK;
						break;
					default://未知
						clockSource_T.qualityLevel=ClockSourceQualityLevel_T.CSQ_LEVELUNKNOWN;
				}
			}
		}
		//时钟源状态
		clockSource_T.syncState=ClockSyncState_T.CSS_NA;
		if(((manufacturer == EManufacturer.CHENXIAO.getValue()&&2==clockSource_Corba.getType()))||
				((manufacturer == EManufacturer.WUHAN.getValue()&&1==clockSource_Corba.getType()))){
			if(-1 != clockSource_Corba.getModel()){
				syncState = Integer.parseInt(UiUtil.getCodeById(clockSource_Corba.getModel()).getCodeValue());
				switch(syncState){
				case 0://自动
					clockSource_T.syncState=ClockSyncState_T.CSS_AUTO;
					break;
				case 1://自由震荡
					clockSource_T.syncState=ClockSyncState_T.CSS_MISLOCKED;
					break;
				case 2://保持
					clockSource_T.syncState=ClockSyncState_T.CSS_HOLD;
					break;
				default://未知
					clockSource_T.syncState=ClockSyncState_T.CSS_NA;
				}
			}
		}
	}
}

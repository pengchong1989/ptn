package com.nms.ui.ptn.alarm.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.csvreader.CsvWriter;
import com.nms.db.bean.alarm.HisAlarmInfo;
import com.nms.db.bean.alarm.OamEvent;
import com.nms.db.bean.perform.Capability;
import com.nms.db.bean.perform.CurrPerformCountInfo;
import com.nms.db.bean.perform.CurrentPerforInfo;
import com.nms.db.bean.perform.HisPerformanceInfo;
import com.nms.db.bean.perform.PathPerformCountInfo;
import com.nms.db.bean.system.ELayerRateInfo;
import com.nms.db.enums.EObjectType;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.keys.StringKeysTitle;
import com.nms.ui.ptn.alarm.AlarmTools;
import com.nms.db.bean.alarm.CurrentAlarmInfo;
import com.nms.ui.ptn.statistics.xmlanalysis.ReadTableAttrsXml;
/**
 * 导出文件对话框 的设置
 * @author Administrator
 *
 */
public class CSVUtil {

	public String showChooserWindow(String type, String title, String[] style) {
		String path = null;

		JFileChooser chooser = new JFileChooser();
		/* 过滤文件类型 */
		FileNameExtensionFilter filter[] = new FileNameExtensionFilter[style.length];
		for (int i = 0; i < style.length; i++) {
			filter[i] = new FileNameExtensionFilter("." + style[i], style[i]);
			chooser.setFileFilter(filter[i]);
		}

		// 设置文件选择框的标题
		chooser.setDialogTitle(title);
		// 弹出选择框
		if (type.equals("save")) {

			if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				try {
					path = chooser.getSelectedFile().getCanonicalPath();
				} catch (IOException e1) {
					ExceptionManage.dispose(e1,this.getClass());
				}
			}
		} else if (type.equals("open")) {

			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				try {
					path = chooser.getSelectedFile().getCanonicalPath();
				} catch (IOException e1) {
					ExceptionManage.dispose(e1,this.getClass());
				}
			}
		}
		return path;
	}

	@SuppressWarnings("rawtypes")
	public void WriteHisCsv(String path, List<HisAlarmInfo> hisAlarmInfos,String readName) throws Exception {
	
		String ackTime = null;
		String raisedTime = null;
		String clearedTime = null;
		List headName=null;
		String[] contents=null;
		AlarmTools alarmTools =new AlarmTools();
		try {
			if (null != path) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//	String csvFilePath = path + ".csv";
				CsvWriter wr = new CsvWriter(path, ',', Charset.forName("GBK"));
				
//				if(readName != null){
//					contents=new String[30];
//					headName= ReadTableAttrsXml.readTableXml("operationLogTable");
//					if(headName!=null&&headName.size()>0){
//						for(int i=0;i<headName.size();i++){
//							contents[i]=headName.get(i).toString();
//						}
//					}
//				}else{
				 contents = new String[]{ "序号", "告警级别", "网元名称", "告警源","告警名称", "告警描述", "告警类型", 
						 "确认人", "发生时间", "确认时间", "是否清除", "清除时间","备注"};
//				}
				wr.writeRecord(contents);
				for (int i = 0; i < hisAlarmInfos.size(); i++) {
					if (null != hisAlarmInfos.get(i).getClearedTime()) {
						clearedTime = sdf.format(hisAlarmInfos.get(i).getClearedTime());
					} else {
						clearedTime = "";
					}
					if (null != hisAlarmInfos.get(i).getRaisedTime()) {
						raisedTime = sdf.format(hisAlarmInfos.get(i).getRaisedTime());
					} else {
						raisedTime = "";
					}
					if (null != hisAlarmInfos.get(i).getAckTime()) {
						ackTime = sdf.format(hisAlarmInfos.get(i).getAckTime());
					} else {
						ackTime = "";
					}
					
					String[] s = { "" + (i + 1),//序号
							alarmTools.getAlarmSeverity(hisAlarmInfos.get(i).getWarningLevel().getWarninglevel_temp()).getDisplayName() + "",//"告警级别"
							hisAlarmInfos.get(i).getSiteName(),//"网元名称"
							hisAlarmInfos.get(i).getObjectType().toString() + "/" + hisAlarmInfos.get(i).getObjectName(),//告警源
							hisAlarmInfos.get(i).getWarningLevel().getWarningname(),//告警名称
							hisAlarmInfos.get(i).getAlarmDesc(), //告警描述
							alarmTools.getAlarmType(hisAlarmInfos.get(i).getWarningLevel().getWarningtype()), //告警类型
							hisAlarmInfos.get(i).getAckUser()==null? "":hisAlarmInfos.get(i).getAckUser(),//确认人
							raisedTime,//发生时间
							ackTime,//确认时间
							this.getAlarmIsCleared(hisAlarmInfos.get(i).getIsCleared()),
							clearedTime, //清除时间
							(String) (hisAlarmInfos.get(i).getCommonts()==null? "":hisAlarmInfos.get(i).getCommonts()//备注
)
							};
					wr.writeRecord(s);
				}
				String[] ss = {""+4,"2","2","2","2","2","2","2","2","2","2","2","2"};
				wr.writeRecord(ss,false);
				wr.close();
				JOptionPane.showMessageDialog(null, ResourceUtil.srcStr(StringKeysBtn.BTN_EXPORT)
						+ ResourceUtil.srcStr(StringKeysBtn.BTN_EXPORT_ISUCCESS), ResourceUtil.srcStr(StringKeysTitle.TIT_PROMPT),
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (IOException e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			 ackTime = null;
			 raisedTime = null;
			 clearedTime = null;
			 headName=null;
			 contents=null;
		}
	}
	
	/**导出oam 事件 到 文件
	 * **/
	public void WriteOamEvenCsv(String path, List<OamEvent> oamEventInfos) throws Exception {
		CsvWriter wr=null;
		String ackTime = null;
		String raisedTime = null;
		String clearedTime = null;
		try {
			if (null != path) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				String csvFilePath = path + ".csv";
				 wr = new CsvWriter(csvFilePath, ',',Charset.forName("GBK"));
				String[] contents = { "序号", "端口", "事件", "事件状态", "是否本端","时间戳", "事件周期", "事件门限", "次数", "错误总数", "事件总数","上报时间" };
				wr.writeRecord(contents);
				for (OamEvent oam : oamEventInfos) {					
					String[] s = {ifNull(oam.getId()),//序号
							ifNull(oam.getEnvPort()),
							ifNull(oam.getEnv()),
							ifNull(oam.getEnvState()),
							ifNull(oam.getIsthis()),
							ifNull(oam.getDatetime()),
							ifNull(oam.getEnvCrc()),
							ifNull(oam.getEnvLimit()),
							ifNull(oam.getTimes()),
							ifNull(oam.getErrSum()),
							ifNull(oam.getEnvSum()),
							ifNull(oam.getUpdate())
					           };
					wr.writeRecord(s);
				}
				wr.flush();
				wr.close();
				JOptionPane.showMessageDialog(null, ResourceUtil.srcStr(StringKeysBtn.BTN_EXPORT)
						+ ResourceUtil.srcStr(StringKeysBtn.BTN_EXPORT_ISUCCESS), ResourceUtil.srcStr(StringKeysTitle.TIT_PROMPT),
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (IOException e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			if(wr!=null){
				wr.close();
			}
			
			 ackTime = null;
			 raisedTime = null;
			 clearedTime = null;
		}
	}
	private String ifNull(String s){
		if(null==s )return "";
		return s;
	}
	/**当前告警 导出到cvs文件**/
	public void WriteCsv(String path, List<CurrentAlarmInfo> currInfos) throws Exception {
		CsvWriter wr=null;
		String ackTime = null;
		String raisedTime = null;
		String clearedTime = null;
		AlarmTools alarmTools =new AlarmTools();
		try {
			if (null != path) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				wr = new CsvWriter(path, ',',Charset.forName("GBK"));
				String[] contents = { "序号", "告警级别", "网元名称", "告警源", "告警名称","告警描述", "告警类型", "确认人",
						"发生时间", "确认时间", "是否清除", "清除时间","备注" };
				wr.writeRecord(contents);
				for (int i = 0; i < currInfos.size(); i++) {
					if (null != currInfos.get(i).getClearedTime()) {
						clearedTime = sdf.format(currInfos.get(i).getClearedTime());
					} else {
						clearedTime = "";
					}
					if (null != currInfos.get(i).getRaisedTime()) {
						raisedTime = sdf.format(currInfos.get(i).getRaisedTime());
					} else {
						raisedTime = "";
					}
					if (null != currInfos.get(i).getAckTime()) {
						ackTime = sdf.format(currInfos.get(i).getAckTime());
					} else {
						ackTime = "";
					}
					String[] s = { "" + (i + 1),//序号
							alarmTools.getAlarmSeverity(currInfos.get(i).getWarningLevel().getWarninglevel_temp()).getDisplayName() + "",//告警级别
							currInfos.get(i).getSiteName(),//网元名称
							currInfos.get(i).getObjectType().toString() + "/" + currInfos.get(i).getObjectName(),//告警源
							currInfos.get(i).getWarningLevel().getWarningname(), //告警名称
							currInfos.get(i).getAlarmDesc(),//告警描述
							alarmTools.getAlarmType(currInfos.get(i).getWarningLevel().getWarningtype()), //告警类型
							currInfos.get(i).getAckUser()==null? "":currInfos.get(i).getAckUser(),//确认人
							raisedTime,//发生时间
							ackTime,//确认时间
							this.getAlarmIsCleared(currInfos.get(i).getIsCleared()),
							clearedTime,//清除时间
							(String) (currInfos.get(i).getAlarmComments()==null? "":currInfos.get(i).getAlarmComments()//备注
)
					           };
					wr.writeRecord(s);
				}
				wr.flush();
				wr.close();
				JOptionPane.showMessageDialog(null, ResourceUtil.srcStr(StringKeysBtn.BTN_EXPORT)
						+ ResourceUtil.srcStr(StringKeysBtn.BTN_EXPORT_ISUCCESS), ResourceUtil.srcStr(StringKeysTitle.TIT_PROMPT),
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (IOException e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			if(wr!=null){
				wr.close();
			}
			
			 ackTime = null;
			 raisedTime = null;
			 clearedTime = null;
		}
	}

	private String getAlarmIsCleared(String isCleared) {
		if(ResourceUtil.srcStr(StringKeysTip.TIP_CLEARED).equals(isCleared)){
			return ResourceUtil.srcStr(StringKeysTip.TIP_CLEARED);
		}else{
			return ResourceUtil.srcStr(StringKeysTip.TIP_UNCLEARED);
		}
	}
	

	@SuppressWarnings("rawtypes")
	public void WriteCsvHisPerformace(String path, List<HisPerformanceInfo> hisAlarmInfos) throws Exception {
		CsvWriter wr=null;
		List headName=null;
		String[] contents=null;
		String ackTime = null;
		String raisedTime = null;
		String capabilityName = null;
		String performanceValue=null;
		Boolean isok = null;
		try {
			if (null != path) {
				wr = new CsvWriter(path, ',', Charset.forName("GBK"));
				String perforName=null;
				String unitName=null;
				contents=new String[30];
				 headName= ReadTableAttrsXml.readTableXml("hisPerTable");
				 if(headName!=null&&headName.size()>0){
					 for(int i=0;i<headName.size();i++){
						 contents[i]=headName.get(i).toString();
					 }
				 }
				wr.writeRecord(contents);
				for (int i = 0; i < hisAlarmInfos.size(); i++) {

					if (null != hisAlarmInfos.get(i).getCapability()) {
		
						if(ResourceUtil.language.equals("zh_CN")){
							capabilityName = hisAlarmInfos.get(i).getCapability().getCapabilitydesc();
						}else{
							capabilityName = hisAlarmInfos.get(i).getCapability().getCapabilitydesc_en();
						}
						
						unitName=hisAlarmInfos.get(i).getCapability().getUnitName();
						
						perforName=hisAlarmInfos.get(i).getCapability().getCapabilityname();
						//开始时间
						raisedTime=updateTime(hisAlarmInfos.get(i).getStartTime());
						//结束时间
						ackTime=updateTime(hisAlarmInfos.get(i).getPerformanceEndTime());
						if(unitName.trim().toString().equals("%")){
							// 性能值
							if(hisAlarmInfos.get(i).getPerformanceCode() == 116){
								performanceValue = hisAlarmInfos.get(i).getPerformanceValue()+unitName;
							}else{
								performanceValue=hisAlarmInfos.get(i).getPerformanceValue()/100+unitName;
							}
						}else{
							// 性能值
							performanceValue= hisAlarmInfos.get(i).getPerformanceValue()+unitName;
						}
						
						if (hisAlarmInfos.get(i).getUseAble() == 1) {
							isok = Boolean.FALSE;
						} else {
							isok = Boolean.TRUE;
						}
						
						String[] s = { "" + (i + 1), 
								hisAlarmInfos.get(i).getSiteName() + "",// 网元名称
								hisAlarmInfos.get(i).getObjectName()+"/"+hisAlarmInfos.get(i).getObjectType().toString(),// 性能对象
								perforName, //性能名称
								capabilityName,
								performanceValue,// 性能值
								raisedTime,// 开始时间
								ackTime, // 结束时间
								isok + ""};
						wr.writeRecord(s);
					}
				}
				wr.flush();
				wr.close();
				JOptionPane.showMessageDialog(null, ResourceUtil.srcStr(StringKeysBtn.BTN_EXPORT)
						+ ResourceUtil.srcStr(StringKeysBtn.BTN_EXPORT_ISUCCESS), ResourceUtil.srcStr(StringKeysTitle.TIT_PROMPT),
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (IOException e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			if(wr!=null){
				wr.close();
			}
			headName = null;
			contents = null;
			ackTime = null;
			raisedTime = null;
			capabilityName = null;
			performanceValue = null;
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void WriteCsvCureenPerformace(String path, List<CurrentPerforInfo> currentAlarmInfos) throws Exception {
		CsvWriter wr=null;
		List headName=null;
		String[] contents=null;
		String ackTime = null;
		String raisedTime = null;
		String capabilityName = null;
		String performanceValue=null;
		try {
			if (null != path) {
				 wr = new CsvWriter(path, ',', Charset.forName("GBK"));
				String perforName=null;
				String unitName=null;
				contents=new String[30];
				 headName= ReadTableAttrsXml.readTableXml("currPerTable");
				 if(headName!=null&&headName.size()>0){
					 for(int i=0;i<headName.size();i++){
						 contents[i]=headName.get(i).toString();
					 }
				 }
				wr.writeRecord(contents);
				for (int i = 0; i < currentAlarmInfos.size(); i++) {

					if (null != currentAlarmInfos.get(i).getCapability()) {
						
						if(ResourceUtil.language.equals("zh_CN")){
							capabilityName = currentAlarmInfos.get(i).getCapability().getCapabilitydesc();
						}else{
							capabilityName = currentAlarmInfos.get(i).getCapability().getCapabilitydesc_en();
						}
						unitName=currentAlarmInfos.get(i).getCapability().getUnitName();
						perforName=currentAlarmInfos.get(i).getCapability().getCapabilityname();
						//开始时间
						raisedTime=updateTime(currentAlarmInfos.get(i).getStartTime());
						//结束时间
						ackTime=updateTime(currentAlarmInfos.get(i).getPerformanceEndTime());
						if(unitName.trim().toString().equals("%")){
							// 性能值
							if(currentAlarmInfos.get(i).getPerformanceCode() == 116){
								performanceValue = currentAlarmInfos.get(i).getPerformanceValue()+unitName;
							}else{
								performanceValue=currentAlarmInfos.get(i).getPerformanceValue()/100+unitName;
							}
						}else{
							// 性能值
							performanceValue= currentAlarmInfos.get(i).getPerformanceValue()+unitName;
						}
						
						String[] s = { "" + (i + 1), 
								currentAlarmInfos.get(i).getSiteName() + "",// 网元名称
								currentAlarmInfos.get(i).getObjectName()+"/"+currentAlarmInfos.get(i).getObjectType().toString(),// 性能对象
								perforName, //性能名称
								capabilityName,
								performanceValue,// 性能值
								raisedTime,// 开始时间
								ackTime// 结束时间
								};
						wr.writeRecord(s);
					}
				}
				wr.flush();
				wr.close();
				JOptionPane.showMessageDialog(null, ResourceUtil.srcStr(StringKeysBtn.BTN_EXPORT)
						+ ResourceUtil.srcStr(StringKeysBtn.BTN_EXPORT_ISUCCESS), ResourceUtil.srcStr(StringKeysTitle.TIT_PROMPT),
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (IOException e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			if(wr!=null){
				wr.close();
			}
			headName = null;
			contents = null;
			ackTime = null;
			raisedTime = null;
			capabilityName = null;
			performanceValue = null;
		}
	}

	public List<HisPerformanceInfo> inportCsvHisPerformace(String path) {
		List<String> listString = null;
		List<HisPerformanceInfo> hisAlarmInfoa = null;
		String s = null;
		BufferedReader bufferdReader = null;
		String strTem = "";
		String[] sqlString;
		try {
			hisAlarmInfoa = new ArrayList<HisPerformanceInfo>();
			if (path != null && !"".equals(path) && path.contains(".") && path.length() > 0) {
				s = path.substring(path.lastIndexOf(".") + 1, path.length());
				listString = new ArrayList<String>();
				if (null != path && s.trim().equals("csv")) {
					bufferdReader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "GBK"));
					while ((strTem = bufferdReader.readLine()) != null) {
						listString.add(strTem);
					}
					if (isOk(listString)) {
						for (int i = 1; i < listString.size(); i++) {
							sqlString = listString.get(i).split(",");
							if (sqlString.length - 1 == 8) {
								Capability capa = new Capability();
								HisPerformanceInfo hisAlarm = new HisPerformanceInfo();
								hisAlarm.setUseAble(sqlString[sqlString.length - 1].trim().endsWith("true") ? 0 : 1);// 是否有效
								hisAlarm.setPerformanceEndTime(sqlString[sqlString.length - 2]);// 结束时间
								// 开始时间
								if (sqlString[sqlString.length - 3] != null && !"".equals(sqlString[sqlString.length - 3])) {
									hisAlarm.setStartTime(sqlString[sqlString.length - 3]);// 结束时间
								}
								String pervalueName=getPerformValueUiutName(sqlString[sqlString.length - 4]);
								if(!pervalueName.equals("")){
									String[] typeName=pervalueName.trim().split("/");
									hisAlarm.setPerformanceValue(Integer.valueOf(typeName[0]));// 性能值
									capa.setUnitName(typeName[1]);
								}
								capa.setCapabilitydesc(sqlString[sqlString.length - 5]);// 描述
								capa.setCapabilityname(sqlString[sqlString.length - 6]);//性能名称
								hisAlarm.setCapability(capa);// 描述
								if(sqlString[sqlString.length - 7].toString()!=null){
									String[] typeName=sqlString[sqlString.length - 7].toString().toString().trim().split("/");
									hisAlarm.setObjectType(getObjectTypeInteger(typeName[typeName.length-1]));
									hisAlarm.setObjectName(typeName[0]);
								}
								// 网元名称
								hisAlarm.setSiteName(sqlString[sqlString.length - 8]);
								// id
								hisAlarm.setId(Integer.parseInt(sqlString[sqlString.length - 9]));
								hisAlarmInfoa.add(hisAlarm);
							}
						}
						JOptionPane.showMessageDialog(null, ResourceUtil.srcStr(StringKeysBtn.BTN_INPORT)
								+ ResourceUtil.srcStr(StringKeysBtn.BTN_EXPORT_ISUCCESS), ResourceUtil.srcStr(StringKeysTitle.TIT_PROMPT),
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, ResourceUtil.srcStr(StringKeysBtn.BTN_INPORT)
								+ ResourceUtil.srcStr(StringKeysBtn.BTN_EXPORT_FALSE), ResourceUtil.srcStr(StringKeysTitle.TIT_PROMPT),
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, ResourceUtil.srcStr(StringKeysBtn.BTN_INPORT)
							+ ResourceUtil.srcStr(StringKeysBtn.BTN_EXPORT_FALSE), ResourceUtil.srcStr(StringKeysTitle.TIT_PROMPT),
							JOptionPane.INFORMATION_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, ResourceUtil.srcStr(StringKeysBtn.BTN_INPORT)
						+ ResourceUtil.srcStr(StringKeysBtn.BTN_EXPORT_FALSE), ResourceUtil.srcStr(StringKeysTitle.TIT_PROMPT),
						JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			listString = null;
			s = null;
			bufferdReader = null;
			strTem = "";
			sqlString = null;
		}
		return hisAlarmInfoa;
	}

	public  EObjectType getObjectTypeInteger(String ObjectType) {
		if (ObjectType.equals("SITEINST"))
			return EObjectType.SITEINST;
		else if (ObjectType.equals("SLOTINST"))
			return EObjectType.SLOTINST;
		else if (ObjectType.equals("TUNNEL"))
			return EObjectType.TUNNEL;
		else if (ObjectType.equals("PW"))
			return EObjectType.PW;
		else if (ObjectType.equals("PORT"))
			return EObjectType.PORT;
		else if (ObjectType.equals("LSP"))
			return EObjectType.LSP;
		else if (ObjectType.equals("POWER"))
			return EObjectType.POWER;
		else if (ObjectType.equals("SEGMENT"))
			return EObjectType.SEGMENT;
		else if (ObjectType.equals("VPWS"))
			return EObjectType.VPWS;
		else if (ObjectType.equals("VPLS"))
			return EObjectType.VPLS;
		else if (ObjectType.equals("CLOCK"))
			return EObjectType.CLOCK;
		else if (ObjectType.equals("E1"))
			return EObjectType.E1;
		else
			return EObjectType.NONE;
	}

	@SuppressWarnings("rawtypes")
	public boolean isOk(List<String> listString) throws Exception {
		boolean fal = false;
		List headName=null;
		String[] contents=null;
		if (listString != null && listString.size() > 0) {
			contents=new String[30];
			 headName= ReadTableAttrsXml.readTableXml("hisPerTable");
			 if(headName!=null&&headName.size()>0){
				 for(int i=0;i<headName.size();i++){
					 contents[i]=headName.get(i).toString();
				 }
			 }
			String[] s = listString.get(0).split(",");
			if (contents.length == s.length) {
				for (int i = 0; i < contents.length; i++) {
					if (contents[i].trim().equals(s[i])) {
						fal = true;
					} else {
						fal = false;
					}
				}
			}
		}
		return fal;
	}
	private String getPerformValueUiutName(String performValueUituName){
		String sql="";
		String rege=null;
		StringBuffer performValue=null;
		StringBuffer uiutName=null;
		try {
			rege="^[0-9_]+$";
			 performValue=new StringBuffer();
			 uiutName=new StringBuffer();
			if(performValueUituName!=null&&!performValueUituName.trim().equals("")){
				char[] chars=performValueUituName.toCharArray();
				for(int i=0;i<chars.length;i++){
					if(String.valueOf(chars[i]).matches(rege)){
						performValue.append(String.valueOf(chars[i]));
					}else{
						uiutName.append(String.valueOf(chars[i]));
					}
				}
				sql=performValue.toString()+"/"+uiutName.toString();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			 rege=null;
			 performValue=null;
			 uiutName=null;
		}
		return sql;
	}
	/**
	 * 修改时间的格式：yyyy-MM-dd HH:mm:ss
	 * @param updateTime
	 * @return
	 */
	private String updateTime(String updateTime){
		String newTime=null;
		SimpleDateFormat sdf=null;
		try {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		     newTime= sdf.format(sdf.parse(updateTime));
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return  newTime;
	}
	
	/**
	 * 实时性能统计
	 */
	public void WriteCsvCurrPerformCount(String path, List<CurrPerformCountInfo> currPerformInfoList) throws Exception {
		CsvWriter wr = null;
		try {
			if (null != path) {
				wr = new CsvWriter(path, ',', Charset.forName("GBK"));
				String[] contents = new String[8];
				List<String> headName = ReadTableAttrsXml.readTableXml("currPerCountTable");
				if(headName != null && headName.size()>0){
					for(int i = 0; i < headName.size(); i++){
						contents[i] = headName.get(i).toString();
					}
				}
				wr.writeRecord(contents);
				for (int i = 0; i < currPerformInfoList.size(); i++) {
					CurrPerformCountInfo currPerform = currPerformInfoList.get(i);
					if (null != currPerform) {
						String[] s = { "" + (i + 1), 
								currPerform.getSiteName(),//网元名称
								currPerform.getTime(),
								currPerform.getObjectName(),//监控对象
								currPerform.getReceiveByte(),//收字节
								currPerform.getSendByte(),//发字节
								currPerform.getInBandWidthUtil(),//入带宽利用率
								currPerform.getOutBandWidthUtil()//出带宽利用率
								};
						wr.writeRecord(s);
					}
				}
				wr.flush();
				wr.close();
				JOptionPane.showMessageDialog(null, ResourceUtil.srcStr(StringKeysBtn.BTN_EXPORT)
						+ ResourceUtil.srcStr(StringKeysBtn.BTN_EXPORT_ISUCCESS), ResourceUtil.srcStr(StringKeysTitle.TIT_PROMPT),
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (IOException e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			if(wr != null){
				wr.close();
			}
		}
	}

	/**
	 * 端到端性能统计
	 */
	public void WriteCsvPathPerformCount(String path, List<PathPerformCountInfo> pathPerformInfoList) {
		CsvWriter wr = null;
		try {
			if (null != path) {
				wr = new CsvWriter(path, ',', Charset.forName("GBK"));
				String[] contents = new String[10];
				List<String> headName = ReadTableAttrsXml.readTableXml("pathPerCountTable");
				if(headName != null && headName.size()>0){
					for(int i = 0; i < headName.size(); i++){
						contents[i] = headName.get(i).toString();
					}
				}
				wr.writeRecord(contents);
				for (int i = 0; i < pathPerformInfoList.size(); i++) {
					PathPerformCountInfo pathPerform = pathPerformInfoList.get(i);
					if (null != pathPerform) {
						String[] s = { "" + (i + 1), 
								pathPerform.getSiteName(),//网元名称
								pathPerform.getObjectName(),//监控对象
								pathPerform.getTime(),
								pathPerform.getPacklosr_near()+"",//近端丢包率
								pathPerform.getPacklosr_far()+"",//远端丢包率
								pathPerform.getPmpackdelay_s()+"",//时延秒
								pathPerform.getPmpackdelay_ns()+"",//时延纳秒
								pathPerform.getRx_cv()+"",//接收CV包统计
								pathPerform.getTx_cv()+"",//发送CV包统计
								};
						wr.writeRecord(s);
					}
				}
				wr.flush();
				wr.close();
				JOptionPane.showMessageDialog(null, ResourceUtil.srcStr(StringKeysBtn.BTN_EXPORT)
						+ ResourceUtil.srcStr(StringKeysBtn.BTN_EXPORT_ISUCCESS), ResourceUtil.srcStr(StringKeysTitle.TIT_PROMPT),
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (IOException e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			if(wr != null){
				wr.close();
			}
		}
	}

	/**
	 * 北向接口层速率统计
	 */
	public void WriteCsvLayerRateCount(String path, List<ELayerRateInfo> layerRateList) {
		CsvWriter wr = null;
		try {
			if (null != path) {
				String csvFilePath = path + ".csv";
				wr = new CsvWriter(csvFilePath, ',', Charset.forName("GBK"));
				String[] contents = new String[10];
				List<String> headName = ReadTableAttrsXml.readTableXml("layerRateTable");
				if(headName != null && headName.size()>0){
					for(int i = 0; i < headName.size(); i++){
						contents[i] = headName.get(i).toString();
					}
				}
				wr.writeRecord(contents);
				for (int i = layerRateList.size()-1; i >= 0; i--) {
					ELayerRateInfo layerRate = layerRateList.get(i);
					if (null != layerRate) {
						String[] s = { "" + (layerRateList.size() - i), 
								layerRate.getObjName(),
								layerRate.getLayerNumber()+"",
						};
						wr.writeRecord(s);
					}
				}
				wr.flush();
				wr.close();
				JOptionPane.showMessageDialog(null, ResourceUtil.srcStr(StringKeysBtn.BTN_EXPORT)
						+ ResourceUtil.srcStr(StringKeysBtn.BTN_EXPORT_ISUCCESS), ResourceUtil.srcStr(StringKeysTitle.TIT_PROMPT),
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (IOException e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			if(wr != null){
				wr.close();
			}
		}
	}

}
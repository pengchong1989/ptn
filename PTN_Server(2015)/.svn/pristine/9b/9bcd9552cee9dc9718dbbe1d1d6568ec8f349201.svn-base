package com.nms.snmp.ninteface.impl.alarm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.db.bean.alarm.AlarmInfo;
import com.nms.db.bean.alarm.CurrentAlarmInfo;
import com.nms.db.bean.alarm.HisAlarmInfo;
import com.nms.db.bean.alarm.WarningLevel;
import com.nms.db.bean.equipment.card.CardInst;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.equipment.slot.SlotInst;
import com.nms.model.alarm.CurAlarmService_MB;
import com.nms.model.alarm.HisAlarmService_MB;
import com.nms.model.equipment.card.CardService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.equipment.slot.SlotService_MB;
import com.nms.model.util.ServiceFactory;
import com.nms.model.util.Services;
import com.nms.snmp.ninteface.framework.SnmpConfig;
import com.nms.snmp.ninteface.util.FileTools;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysLbl;
/**
 * 将历史告警数据转换成xml文件
 * @author guoqc
 *
 */
public class HisAlarmConvertXml {
	public static void main(String[] args) {
		ConstantUtil.serviceFactory = new ServiceFactory();
		String[] times = {"20140829140000", "20140829172328"};
		new HisAlarmConvertXml().getHisAlarmXml(null);
	}
	
    /**
     * 调用此方法将数据库中的历史告警数据转换成xml文件
     * CMCC-PTN-FM-V1.0.0-20140411-1602.xml
     * 如果参数为null,则查询所有告警,否则按时间段查询
     * happenedTime数组, 第一个元素是起始时间,第二个元素是结束时间
     */
	public String getHisAlarmXml(String[] happenedTime) {
		
		String filePath = "";
		String version = ResourceUtil.srcStr(StringKeysLbl.LBL_SNMPMODEL_VERSION);
		String[] xmlPath = {"snmpData\\FM", "CMCC-PTN-FM-"+version+".xml"};
		FileTools fileTools = null;
		try {
			if(happenedTime != null){
				xmlPath[1] = "CMCC-PTN-FM-"+version+"-"+this.getTime(happenedTime[0])+".xml";
			}
			filePath = xmlPath[0] + File.separator + xmlPath[1];//生成文件路径
	    	List<HisAlarmInfo> hisAlarmList = this.getHisAlarmList(happenedTime,1);//获取历史告警数据
	    	List<CurrentAlarmInfo> currentAlarmList = this.getHisAlarmList(happenedTime,2); 
	    	this.createFile(xmlPath);//根据文件路径和文件名生成xml文件
	    	Document doc = this.getDocument(xmlPath);//生成doucument
		    this.createXML(doc, hisAlarmList,currentAlarmList);//生成xml文件内容
		    fileTools = new FileTools();
		    fileTools.putFile(doc, filePath);//根据xml文件内容生成对应的文件
		    fileTools.zipFile(filePath, filePath+".zip");//xml文件压缩成zip格式的文件
		} catch (Exception e){
			ExceptionManage.dispose(e, this.getClass());
		}
		return filePath;
	}

	/**
     * 将起始时间转换成我们所需的时间
     * 参数time格式为 YYYY-MM-DD HH:MM:SS
     * 转换之后: YYYYMMDD-HHMM
     * @throws Exception 
     */
	private String getTime(String time) {
		if(time == null || ("").equals(time)){
			return null;
		}else{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd-HHmm");   
	        try {   
	            Date date = sdf.parse(time);   
	            return sdf2.format(date);   
	        } catch (Exception e) {   
	            return null;   
	        }   
	    }
	}

	/**
     * 根据条件查询历史告警数据
	 * @param happenedTime 
	 * lable =1 表示历史告警 2：表示当前告警
     */
    private List getHisAlarmList(String[] happenedTime,int lable) {
		List<HisAlarmInfo> hisAlarmList = new ArrayList<HisAlarmInfo>();
		List<CurrentAlarmInfo> currentAlarmList = new ArrayList<CurrentAlarmInfo>();
		HisAlarmService_MB hisAlarmService = null;
		CurAlarmService_MB curalarmService = null;
		try { 
			curalarmService = (CurAlarmService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CurrentAlarm);
			hisAlarmService = (HisAlarmService_MB) ConstantUtil.serviceFactory.newService_MB(Services.HisAlarm);
			//时间段为null,说明是查询所有
			if(happenedTime == null){
				if(lable ==1){
					hisAlarmList = hisAlarmService.select();
				}else{
					currentAlarmList = curalarmService.select();
				}
			}else{
				String startTime = this.convertTime(happenedTime[0]);
				String endTime = this.convertTime(happenedTime[1]);
				if(startTime != null && endTime != null){
					if(lable ==1){
						hisAlarmList = hisAlarmService.selectByTime(startTime, endTime);
					}else{
						currentAlarmList = curalarmService.selectByStartTimeAndEndTime(startTime, endTime);
					}
				}else{
					throw new Exception("the time format is error !");
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(hisAlarmService);
			UiUtil.closeService_MB(curalarmService);
		}
		
		if(lable ==1){
	    	return hisAlarmList;
		}else{
			return currentAlarmList;
		}
	}
    
    /**
     * 将起始时间转换成我们所需的时间
     * 参数time格式为 YYYYMMDDHHMMSS
     * 转换之后: YYYY-MM-DD HH:MM:SS
     * @throws Exception 
     */
    public  String convertTime(String time) throws Exception {
		if(time == null || ("").equals(time)){
			return null;
		}else{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
	        try {   
	            Date date = sdf.parse(time);   
	            return sdf2.format(date);   
	        } catch (Exception e) {   
	            return null;   
	        }   
	    }
	}
    
	/**
     * 根据文件路径和文件名生成xml文件
     */
    private void createFile(String[] xmlPath) throws FileNotFoundException {
    	//根据路径生成文件目录
    	File dirname = new File(xmlPath[0]);
    	//如果本地目录不存在，则需要创建一个目录
		if (!dirname.exists()) 
			dirname.mkdirs();
		//生成xml文件
    	new FileOutputStream(xmlPath[0]+File.separator+xmlPath[1]);
	}
    
    /**
     * 生成document
     */
    private Document getDocument(String[] xmlPath) throws Exception {
	    try {  
	     DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
	     DocumentBuilder builder = factory.newDocumentBuilder();  
	     Document doc = builder.newDocument();  
	     return doc;
	    } catch (Exception e) {  
	     ExceptionManage.dispose(e,this.getClass());  
	    }
		return null;
	}

    /**
     * 根据需求生成相应的xml文件
     */
	private void createXML(Document doc, List<HisAlarmInfo> hisAlarmList,List<CurrentAlarmInfo> currentAlarmList){
		doc.setXmlVersion("1.0");
		doc.setXmlStandalone(true);
		Element root = doc.createElement("dm:Descriptor");
		root.setAttribute("xmlns:dm", "http://www.tmforum.org/mtop/mtnm/HistoryData/v1");
		root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
		root.setAttribute("xsi:schemaLocation", "http://www.tmforum.org/mtop/mtnm/HistoryData/v1 ../historyData.xsd");
		Element alarmList = this.createFileContent(doc, hisAlarmList,currentAlarmList);
		root.appendChild(alarmList);
		doc.appendChild(root);
	}
	
	private Element createFileContent(Document doc, List<HisAlarmInfo> hisAlarmList,List<CurrentAlarmInfo> currentAlarmList) {
		Element alarmList = doc.createElement("EventList_T");
		//增加历史告警
		if(hisAlarmList != null && hisAlarmList.size()>0){
			for (HisAlarmInfo alarm : hisAlarmList) {
				this.createAlarmData(alarm, alarmList, doc, 1);
			}
		}
		//增加当前告警数据
		if(currentAlarmList != null && currentAlarmList.size()>0){
			for (CurrentAlarmInfo alarm : currentAlarmList) {
				this.createAlarmData(alarm, alarmList, doc, 2);
			}
		}
		return alarmList;
	}

	/**
	 * 生成告警数据文件
	 * type 1/2 = 历史告警/当前告警
	 */
	private void createAlarmData(AlarmInfo alarm, Element alarmList, Document doc, int type) {
		try{
			Element StructuredEvent = doc.createElement("StructuredEvent");
			Element FilterableEventBody = doc.createElement("FilterableEventBody");
			StructuredEvent.appendChild(FilterableEventBody);
			this.createObjName(doc, alarm, FilterableEventBody, type);
			WarningLevel warningLevel = new WarningLevel();
			if(type == 1){
				warningLevel = ((HisAlarmInfo)alarm).getWarningLevel();
			}else{
				warningLevel = ((CurrentAlarmInfo)alarm).getWarningLevel();
			}
			//厂商告警标题
			this.createElementNode(doc, "nativeProbableCause", warningLevel.getWarningname(), FilterableEventBody);
			//告警唯一定位的辅助描述符
			this.createElementNode(doc, "probableCauseQualifier", warningLevel.getId()+"",FilterableEventBody);
			//告警产生对象类型(枚举值)
			//TODO
			this.createElementNode(doc, "objectType", this.getAlarmObjTypeAndLayerRate(alarm.getObjectType().
					getValue())[0], FilterableEventBody);
			//网管时间
			this.createElementNode(doc, "emsTime", this.convertEventTime(alarm.getRaisedTime()),
					FilterableEventBody);
			//网元时间
			this.createElementNode(doc, "neTime", this.convertEventTime(alarm.getRaisedTime()), 
					FilterableEventBody);
			//速率层次
			this.createElementNode(doc, "layerRate", this.getAlarmObjTypeAndLayerRate(alarm.getObjectType().
					getValue())[1], FilterableEventBody);
			//告警可能原因
			this.createElementNode(doc, "probableCause", warningLevel.getWarningmayreason(),
					FilterableEventBody);
			//告警是否影响业务
			//TODO
			this.createElementNode(doc, "serviceAffecting", this.isEffectService(warningLevel),
					FilterableEventBody);
			//告警级别
			this.createElementNode(doc, "perceivedSeverity", this.getAlarmLevel(alarm.getAlarmLevel()), 
					FilterableEventBody);
//			//告警详细原因
//			this.createElementNode(doc, "X733_SpecificProblems", alarm.getWarningLevel().getWarningname(), FilterableEventBody);
//			//故障处理的建议列表
//			this.createElementNode(doc, "X733_ProposedRepairActions", alarm.getWarningLevel().getWarningdescribe(), FilterableEventBody);
			//告警类型
			//TODO
			this.createElementNode(doc, "X733_EventType", getAlarmType(warningLevel.
					getWarningtype()), FilterableEventBody);
			//告警是否确认
			//TODO
			this.createElementNode(doc, "acknowledgeIndication", alarm.getAckTime()==null?
					"AI_EVENT_UNACKNOWLEDGED":"AI_EVENT_ACKNOWLEDGED", FilterableEventBody);
			//告警匹配ID
			if(type == 1){
				this.createElementNode(doc, "alarmID", alarm.getAlarmId()+"", FilterableEventBody);
			}else{
				this.createElementNode(doc, "alarmID", alarm.getId()+"", FilterableEventBody);
			}
			
			//告警确认时间
			this.createElementNode(doc, "acknowledgeTime", this.convertEventTime(alarm.getAckTime()), 
					FilterableEventBody);
			alarmList.appendChild(StructuredEvent);
		}
		catch(Exception e){
			ExceptionManage.dispose(e, this.getClass());
		}
	}

	/**
	 * 获取告警类型和层速率
	 * return 数组:第一个元素是告警类型,第二个是层速率
	 * OT_EMS, OT_MANAGED_ELEMENT, OT_MULTILAYER_SUBNETWORK, OT_TOPOLOGICAL_LINK,
	 * OT_SUBNETWORK_CONNECTION, OT_PHYSICAL_TERMINATION_POINT, OT_CONNECTION_TERMINATION_POINT,
	 * OT_TERMINATION_POINT_POOL, OT_EQUIPMENT_HOLDER,OT_EQUIPMENT, OT_PROTECTION_GROUP, 
	 * OT_TRAFFIC_DESCRIPTOR, OT_AID
	 */
	private String[] getAlarmObjTypeAndLayerRate(int value) {
		if(value == 1)//siteInst
			return new String[]{"OT_MANAGED_ELEMENT", "LR_Not_Applicable"};
		else if(value == 2)//slotInst 
			return new String[]{"OT_EQUIPMENT_HOLDER", "LR_Not_Applicable"};
		else if(value == 3)//tunnel
			return new String[]{"OT_SUBNETWORK_CONNECTION", ELayerRate.TUNNEL.getValue()+""};
		else if(value == 4)//pw
			return new String[]{"OT_SUBNETWORK_CONNECTION", ELayerRate.PW.getValue()+""};
		else if(value == 5)//port
			return new String[]{"OT_PHYSICAL_TERMINATION_POINT", ELayerRate.PORT.getValue()+""};
		else if(value == 6)//lsp 
			return new String[]{"OT_PROTECTION_GROUP", "LR_Not_Applicable"};
		else if(value == 7)//power
			return new String[]{"OT_MANAGED_ELEMENT", "LR_Not_Applicable"};
		else if(value == 8)//segment
			return new String[]{"OT_TOPOLOGICAL_LINK", ELayerRate.TOPOLOGICALLINK.getValue()+""};
		else if(value == 9)//vpws
			return new String[]{"OT_TRAFFIC_DESCRIPTOR", "LR_Not_Applicable"};
		else if(value == 10)//vpls
			return new String[]{"OT_TRAFFIC_DESCRIPTOR", "LR_Not_Applicable"};
		else if(value == 11)//clock
			return new String[]{"OT_MANAGED_ELEMENT", "LR_Not_Applicable"};
		else if(value == 12)//e1
			return new String[]{"OT_PHYSICAL_TERMINATION_POINT", ELayerRate.TDMPORT.getValue()+""};
		else if(value == 13)//ethoam
			return new String[]{"OT_TRAFFIC_DESCRIPTOR", "LR_Not_Applicable"};
		else if(value == 14)//ci_temp
			return new String[]{"OT_MANAGED_ELEMENT", "LR_Not_Applicable"};
		else if(value == 15)//lag
			return new String[]{"OT_TRAFFIC_DESCRIPTOR", "LR_Not_Applicable"};
		else if(value == 16)//ac
			return new String[]{"OT_TRAFFIC_DESCRIPTOR", "LR_Not_Applicable"};
		else if(value == 17)//msp
			return new String[]{"OT_SUBNETWORK_CONNECTION", "LR_Not_Applicable"};
		else if(value == 18)//dual
			return new String[]{"OT_SUBNETWORK_CONNECTION", "LR_Not_Applicable"};
		else if(value == 19)//mcn
			return new String[]{"OT_SUBNETWORK_CONNECTION", "LR_Not_Applicable"};
		else if(value == 20)//ccn
			return new String[]{"OT_SUBNETWORK_CONNECTION", "LR_Not_Applicable"};
		else if(value == 21)//ring
			return new String[]{"OT_PROTECTION_GROUP", "LR_Not_Applicable"};
		else if(value == 22)//slot
			return new String[]{"OT_EQUIPMENT_HOLDER", "LR_Not_Applicable"};
		else if(value == 23)//tod
			return new String[]{"OT_MANAGED_ELEMENT", "LR_Not_Applicable"};
		else if(value == 24)//fan1
			return new String[]{"OT_MANAGED_ELEMENT", "LR_Not_Applicable"};
		else if(value == 25)//fan2
			return new String[]{"OT_MANAGED_ELEMENT", "LR_Not_Applicable"};
		else if(value == 26)//fan3
			return new String[]{"OT_MANAGED_ELEMENT", "LR_Not_Applicable"};
		else if(value == 27)//emsclient
			return new String[]{"OT_EMS", "LR_Not_Applicable"};
		else return new String[]{"OT_MANAGED_ELEMENT", "LR_Not_Applicable"};
	}

	/**
	 * 判断是否影响业务
	 */
	private String isEffectService(WarningLevel warningLevel) {
		if("对业务没有影响".equals(warningLevel.getWaringeffect())){
			return "SA_NON_SERVICE_AFFECTING";
		}else{
			return "SA_SERVICE_AFFECTING";
		}
	}

	private void createObjName(Document doc, AlarmInfo alarm, Element filterableEventBody, int type) {
		Element objectName = doc.createElement("objectName");
		filterableEventBody.appendChild(objectName);
		//厂商
		Element EMSNode = doc.createElement("node");
		objectName.appendChild(EMSNode);
		this.createElementNode(doc, "name", "EMS", EMSNode);
		this.createElementNode(doc, "value", SnmpConfig.getInstanse().getEmsNativeName(), EMSNode);
		//网元
		Element MENode = doc.createElement("node");
		objectName.appendChild(MENode);
		this.createElementNode(doc, "name", "ManagedElement", MENode);
		if(type == 1){
			this.createElementNode(doc, "value", ((HisAlarmInfo)alarm).getSiteName(), MENode);		
		}else{
			this.createElementNode(doc, "value", ((CurrentAlarmInfo)alarm).getSiteName(), MENode);
		}
		SiteService_MB siteService = null;
		SiteInst siteInst = null;
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			siteInst = new SiteInst();
			siteInst = siteService.select(alarm.getSiteId());
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			UiUtil.closeService_MB(siteService);
		}
		//架框槽
		Element SlotNode = doc.createElement("node");
		objectName.appendChild(SlotNode);
		this.createElementNode(doc, "name", "EquipmentHolder", SlotNode);
		if(type == 1){
			this.createElementNode(doc, "value", "/rack="+siteInst.getRack()+"/shelf="+siteInst.getShelf()+"/slot="+this.getSlotName(((HisAlarmInfo)alarm).getSlotId()), SlotNode);		
		}else{
			this.createElementNode(doc, "value", "/rack="+siteInst.getRack()+"/shelf="+siteInst.getShelf()+"/slot="+this.getSlotName(((CurrentAlarmInfo)alarm).getSlotId()), SlotNode);
		}
		
		
		//板卡
		Element cardNode = doc.createElement("node");
		objectName.appendChild(cardNode);
		this.createElementNode(doc, "name", "Equipment", cardNode);
		if(type == 1){
			this.createElementNode(doc, "value", this.getCardName(((HisAlarmInfo)alarm).getSlotId()), cardNode);			
		}else{
			this.createElementNode(doc, "value", this.getCardName(((CurrentAlarmInfo)alarm).getSlotId()), cardNode);
		}
	}
	
	private String getSlotName(int slotId) {
		SlotService_MB slotService = null;
		try {
			slotService = (SlotService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SLOT);
			SlotInst slot = new SlotInst();
			slot.setId(slotId);
			List<SlotInst> slotList = slotService.select(slot);
			if(slotList != null && slotList.size() > 0){
				return slotList.get(0).getBestCardName();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return "";
	}

	private String getCardName(int slotId) {
		CardService_MB cardService = null;
		try {
			cardService = (CardService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CARD);
			CardInst card = new CardInst();
			card.setSlotId(slotId);
			List<CardInst> cardList = cardService.select(card);
			if(cardList.size() > 0){
				return cardList.get(0).getId()+"";
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(cardService);
		}
		return "";
	}

	private String convertEventTime(Date date) {
		if(date != null && !"".equals(date)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
			return sdf.format(date);
		}else{
			return "";
		}
	}

	/**
	 * 获取告警级别
	 */
	private String getAlarmLevel(int level) {
		if(level == 5){
			return "PS_CRITICAL";//紧急告警
		}else if(level == 4){
			return "PS_MAJOR";//主要告警
		}else if(level == 3){
			return "PS_MINOR";//次要告警
		}else if(level == 2){
			return "PS_WARNING";//提示告警
		}
		return "PS_INDETERMINATE";//不确定告警
	}
	
	/**
	 * 告警类型
	 */
	private String getAlarmType(int value) {
		String str = null;
		switch (value) {
		case 1:
			str = "communicationsAlarm";//通讯告警
			break;
		case 2:
			str = "qualityofServiceAlarm";//服务质量告警
			break;
		case 3:
			str = "equipmentAlarm";//设备告警
			break;
		case 4:
			str = "processingErrorAlarm";//处理错误告警
			break;
		case 5:
			str = "environmentalAlarm";//环境告警
			break;
		default:
			str = "communicationsAlarm";//通讯告警
			break;
		}
		return str;
	}


	/**
	 * 根据名称创建元素,并赋值
	 */
	private void createElementNode(Document doc, String childName, String childValue, Element alarmInfo){
		Element e = doc.createElement(childName);
        e.setTextContent(childValue);
        alarmInfo.appendChild(e);
	}
}

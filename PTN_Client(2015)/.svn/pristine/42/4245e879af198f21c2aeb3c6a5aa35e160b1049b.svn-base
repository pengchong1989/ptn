package com.nms.db.bean.ptn.qos;

import com.nms.db.enums.QosCosLevelEnum;
import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ExceptionManage;

public class QosQueue extends ViewDataObj{

	private static final long serialVersionUID = 1062005041013475650L;	
	private int id ;
	private int serviceId;
	private int siteId;
	private String objType;
	private int objId;	
	private String QusQueue="QusQueue";
	private String Level="Level";
	private String siteName;
	private String queueType;
	private int cos;
	private int cir;
	private int weight; //调度权重
	private int greenLowThresh ; 
	private int greenHighThresh ;
	private int greenProbability ;
	private int yellowLowThresh ;
	private int yellowHighThresh ;
	private int yellowProbability ;
	private boolean wredEnable ;
	private String mostBandwidth ; //最大带宽
	private int disCard;
	private int usedBand;  //已用带宽
	private int restBand;  //剩余带宽


	public String getQusQueue() {
		return QusQueue;
	}

	public void setQusQueue(String qusQueue) {
		QusQueue = qusQueue;
	}

	public String getLevel() {
		return Level;
	}

	public void setLevel(String Leve) {
		Level = Leve;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String toString(){
		return new StringBuffer().append(" id=").append(id).append(" ;cos=").append(cos)
		.append(" ;cir=").append(cir).append(" ;weight=").append(weight)
		.append(" ;mostBandwidth=").append(mostBandwidth).append(" ;usedBand=").append(usedBand)
		.append(" ;restBand=").append(restBand).toString();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	public String getObjType() {
		return objType;
	}
	public void setObjType(String objType) {
		this.objType = objType;
	}
	public int getObjId() {
		return objId;
	}
	public void setObjId(int objId) {
		this.objId = objId;
	}
	public String getQueueType() {
		return queueType;
	}
	public void setQueueType(String queueType) {
		this.queueType = queueType;
	}
	public int getCos() {
		return cos;
	}
	public void setCos(int cos) {
		this.cos = cos;
	}
	public int getCir() {
		return cir;
	}
	public void setCir(int cir) {
		this.cir = cir;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getGreenLowThresh() {
		return greenLowThresh;
	}
	public void setGreenLowThresh(int greenLowThresh) {
		this.greenLowThresh = greenLowThresh;
	}
	public int getGreenHighThresh() {
		return greenHighThresh;
	}
	public void setGreenHighThresh(int greenHighThresh) {
		this.greenHighThresh = greenHighThresh;
	}
	public int getGreenProbability() {
		return greenProbability;
	}
	public void setGreenProbability(int greenProbability) {
		this.greenProbability = greenProbability;
	}
	public int getYellowLowThresh() {
		return yellowLowThresh;
	}
	public void setYellowLowThresh(int yellowLowThresh) {
		this.yellowLowThresh = yellowLowThresh;
	}
	public int getYellowHighThresh() {
		return yellowHighThresh;
	}
	public void setYellowHighThresh(int yellowHighThresh) {
		this.yellowHighThresh = yellowHighThresh;
	}
	public int getYellowProbability() {
		return yellowProbability;
	}
	public void setYellowProbability(int yellowProbability) {
		this.yellowProbability = yellowProbability;
	}
	public boolean isWredEnable() {
		return wredEnable;
	}
	public void setWredEnable(boolean wredEnable) {
		this.wredEnable = wredEnable;
	}
	
	public String getMostBandwidth() {
		return mostBandwidth;
	}
	public void setMostBandwidth(String mostBandwidth) {
		this.mostBandwidth = mostBandwidth;
	}
	
	public int getUsedBand() {
		return usedBand;
	}
	public void setUsedBand(int usedBand) {
		this.usedBand = usedBand;
	}
	public int getRestBand() {
		return restBand;
	}
	public void setRestBand(int restBand) {
		this.restBand = restBand;
	}
	
	
	public int getDisCard() {
		return disCard;
	}

	public void setDisCard(int disCard) {
		this.disCard = disCard;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {
		try {
			getClientProperties().put("cos", QosCosLevelEnum.from(getCos()));
			getClientProperties().put("cir",  this.formatInteger(getCir()));
			getClientProperties().put("usedBand", this.formatInteger(getUsedBand()));
			getClientProperties().put("restBand", this.formatInteger(getRestBand()));
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	
	}
	
	/**
	 * 将整数转换成"1,000,000"标准数据格式
	 * 
	 * @param num
	 * @return
	 */
	private String formatInteger(Integer num) {
		StringBuilder str = new StringBuilder(num.toString());
		int len = str.length();
		if (len % 3 == 0) {
			for (int i = 1; i < len / 3; i++) {
				str.insert(len - 3 * i, ",");
			}
		} else {
			for (int i = 1; i <= len / 3; i++) {
				str.insert(len - 3 * i, ",");
			}
		}
		return str.toString();
	}
	
}

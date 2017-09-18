package com.nms.db.bean.report;

import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysObj;

/**
 *  for Staticstics CardDao
 * **/
public class SSCard extends ViewDataObj{
	/**
	 * 
	 */
	private static final long serialVersionUID = 351700629538613575L;
	int id;
	private String SiteName;
	private String CardId;
	private String CardType;
	private String Version;
	private int siteId;
	private int count;//同一类型的数量
	private String hardversion;
	private String installedSerialNumber;//安装序列号
	private String protectWay;//单板保护方式

	@Override
	public String toString(){
		return new StringBuffer().append("id=").append(id).append(" ;SiteName").append(SiteName)
				.append(" ;CardId=").append(CardId).append(" ;CardType=").append(CardType)
				.append(" ;Version=").append(Version).toString();
	}
	public int getId() {
		return id;
	}
	
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSiteName() {
		return SiteName;
	}
	public void setSiteName(String siteName) {
		SiteName = siteName;
	}
	public String getCardId() {
		return CardId;
	}
	public void setCardId(String cardId) {
		CardId = cardId;
	}
	public String getCardType() {
		return CardType;
	}
	public void setCardType(String cardType) {
		CardType = cardType;
	}
	public String getVersion() {
		return Version;
	}
	public void setVersion(String version) {
		Version = version;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getHardversion()
	{
		return hardversion;
	}
	public void setHardversion(String hardversion)
	{
		this.hardversion = hardversion;
	}
	@Override
	public void putObjectProperty() {
		this.putClientProperty("id", this.getId());
		this.putClientProperty("SiteName",this.getSiteName());
		this.putClientProperty("CardId", this.getCardId());
		//单独统计单板信息
		if(!this.getCardId().contains("703")){
			this.putClientProperty("EquipType","710系列");
		}else{
			this.putClientProperty("EquipType",this.getCardType());
		}
//		
//		if(this.getSiteName() != null){
//			this.putClientProperty("EquipType",this.getCardType());
//		}else{
//			//单独统计单板信息
//			if(!this.getCardId().contains("703")){
//				this.putClientProperty("EquipType","710系列");
//			}else{
//				this.putClientProperty("EquipType",this.getCardType());
//			}
//		}		
		this.putClientProperty("CardType",this.getCardId());
		this.putClientProperty("count",this.getCount());
		this.putClientProperty("Version",this.getVersion());	
		this.putClientProperty("hardwareVersion",this.getHardversion());
		this.putClientProperty("SN", this.getInstalledSerialNumber());
		this.putClientProperty("protectWay", ResourceUtil.srcStr(StringKeysObj.LSP_TYPE_NO));
	}
	public String getInstalledSerialNumber() {
		return installedSerialNumber;
	}
	public void setInstalledSerialNumber(String installedSerialNumber) {
		this.installedSerialNumber = installedSerialNumber;
	}
	public String getProtectWay() {
		return protectWay;
	}
	public void setProtectWay(String protectWay) {
		this.protectWay = protectWay;
	}
	
}

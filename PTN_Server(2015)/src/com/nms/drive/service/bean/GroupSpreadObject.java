package com.nms.drive.service.bean;
/**
 * 静态组播(0CH)
 * @author 张坤
 *
 */
public class GroupSpreadObject {
    private int SMID = 0;// SM ID
    private int vpls_Vs = 0;// VPLS-VS 选择
    private String channelProt = "";// 端口选择
    private String addressMAC = "";// MAC地址1

    public int getSMID() {
	return SMID;
    }

    public void setSMID(int sMID) {
	SMID = sMID;
    }

    public int getVpls_Vs() {
	return vpls_Vs;
    }

    public void setVpls_Vs(int vplsVs) {
	vpls_Vs = vplsVs;
    }

    public String getChannelProt() {
	return channelProt;
    }

    public void setChannelProt(String channelProt) {
	this.channelProt = channelProt;
    }

    public String getAddressMAC() {
	return addressMAC;
    }

    public void setAddressMAC(String addressMAC) {
	this.addressMAC = addressMAC;
    }
}

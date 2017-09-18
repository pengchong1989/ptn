package com.nms.drivechenxiao.service.bean.protocols;

/**
 * 保护 日志（例如： 保护倒换事件上报）
 * @author sy
 *
 */
public class ProtectLogObject {
	
	private String type;//类型 如： cfp
	private String obj;// 事件源 ： 如cfp1
	
	/**
	 * carse						signalStat		SF			列：slot/0 	列2：tunnel/1/2
	 * 事件原因 signalStat::SF::0x0 -本地自动倒换：：信号失效：：	0x0			0x4000 102
	 * 		none  未知类型						
	 */
	private String  carse;
	private String  desc;//详细描述  switchto:0x3 -倒换到： slot/3
	private String time;//发生时间
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getObj() {
		return obj;
	}
	public void setObj(String obj) {
		this.obj = obj;
	}
	public String getCarse() {
		return carse;
	}
	public void setCarse(String carse) {
		this.carse = carse;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * 读取设备上的数据 ：进行转义
  
	 *   （包括 板卡-slot;    lsp保护-tunnel,这2个已经侧过了）
	 * @param name
	 *   需要转义的字符（事件原因 carse，详细描述desc）
	 * @return
	 */
	public  String transProtectLog(String name){
		if(name.equals("none")||name.equals("NONE")){
			return name;
		}
//		 carse :: signalStat::SF:0x4000102   tunnel  
//		 desc ::   switch to:0x4000101		tunnel
		String result="";
		String head="";
		String path="";
		String[] thred=name.split("0x");
		if(thred.length==2){
			head=thred[0];
			if(thred[1].length()==7){//tunnel
				path="tunnel/"+thred[1].charAt(4)+"/"+thred[1].charAt(6);
			}else if(thred[1].length()==1){//板卡保护   slot
				path="slot/"+thred[1];
			}
			result=head+path;			
		}
		return result;
	}
}

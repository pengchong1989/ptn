package com.nms.drivechenxiao.analysis.protocols;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.protocols.CardProObject;

/**
 * 板卡保护
 * @author sy
 *
 */
public class AnalysisCard extends CxtOpLump{
	/**
	 * 网元类型
	 */
	private String type;
	/**
	 * 查询板卡保护
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] selectCardPro(CardProObject cardPro,int session,int seqid){
		type=cardPro.getNeType();
		List<CxtOpItem>  cxtOpItems=new ArrayList<CxtOpItem>();
		String s=null;
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/cfp"));
		cxtOpItems.add(get(s,1));
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	/**
	 * 解析
	 * @param command
	 * @return
	 */
	public List<CardProObject> analysisCardPro(byte[] command,CXNEObject cXNEObject){
		List<CardProObject> cardProList=new ArrayList<CardProObject>();
		int start=49;
		byte[] tt=command;
		byte[] t=new byte[tt.length-start];
		System.arraycopy(tt, start, t, 0, tt.length-start);
		cardProList=super.analysisTabble("cardPro", t);
		return cardProList;
	}
	/**
	 * 更新 板卡保护
	 *    即： 倒换
	 * @param cardPro
	 *    板卡保护对象
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] updateCardPro(CardProObject cardPro,int session,int seqid){
		List<CxtOpItem>  cxtOpItems=new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/cfp/"+cardPro.getName()));
		cxtOpItems.add(set("apscmd",tranAps(cardPro.getApscmd())));
		cxtOpItems.add(commit());
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	/**
	 *类型转换
	 *
	 * @param apscmd
	 * @return
	 */
	public String tranAps(String apscmd){
		if("1".equals(apscmd)){
			return "forceswitch::protection";//强制到工作
		}else if("2".equals(apscmd)){
			return "froceswitch::work";
		}else if("3".equals(apscmd)){
			return "manualswitch::protection";//人工到工作
		}else if("4".equals(apscmd)){
			return "manualswitch::work";//人工到保护
		}else{
			return "";
		}
	}
}

package com.nms.drivechenxiao.analysis.weihu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.nms.drive.service.impl.CoderUtils;
import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.UtilOper;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.persvr.PersvrObject;

/**
 * 光功率
 * @author sy
 *
 */
public class AnalysisPower extends CxtOpLump{
	/**
	 * *******   查询当前性能，api 参数已固定************
	 * -----------------------
	 * 查询光率（当前性能）
	 * @param slot
	 *   真实板卡，  ：若有多个真实板卡（即在线的板卡），需要调用多次
	 * @param portInstList
	 *   真实板卡下的所有端口
	 * 
	 * @param session
	 * @param seqid
	 * @return
	 */
	/*
	 * 202,35  测试通过
	 *  201  未通过
	 */
	public byte[] select(PowerObject powerObject,int session,int seqid){
		List<CxtOpItem> cxtOpItems=new ArrayList<CxtOpItem>();
		byte[] command = new byte[0];
		List<CxtAtomType> cxtAtom = new ArrayList<CxtAtomType>();
		
		cxtOpItems.add(begin(3));
		Iterator iterator=powerObject.getPortMap().entrySet().iterator();
		while(iterator.hasNext()){//只遍历一次,速度快
		Map.Entry e=(Map.Entry)iterator.next();
			int slot=(Integer) e.getKey();
			List<String> portList=(List<String>) e.getValue();
			for(int i=0;i<portList.size();i++){
				cxtAtom.add(getCxtAtomType(CxtAtomType.AT_NUM_32, i+1));
				cxtAtom.add(getCxtAtomType(CxtAtomType.AT_NUM_32,UtilOper.stringToInteger(portList.get(i))));			
			}
			// 查询性能
			List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
			cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, 1));
			cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, slot));
			cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, 2));
			cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "m15"));
			cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, 3));
			cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_TABLE,getCxtATTable(cxtAtom.size()/2,cxtAtom)));
			CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size()/2, cxtAtomTypes);
			CxtAtomType cxtAtomType = new CxtAtomType();
			cxtAtomType.setCxtATTable(cxtATTable);
			cxtAtomType.setType("AT_TABLE");		
			byte[] cxtString = getCxtString("ne/proc/persvr.getcurper");
			byte[] cxtTable = getCxtAtomType(cxtAtomType);
			command = CoderUtils.arraycopy(command, cxtString);
			command = CoderUtils.arraycopy(command, cxtTable);
		}
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_ACALL, command, session, seqid);
		return command;
	}

	/**
	 * 解析查询性能
	 * 
	 * @param command
	 * @return
	 */
	public List<PersvrObject> analysisSelectPersvr(byte[] command, CXNEObject CXNEObject) {
		List<PersvrObject> persvrObjectList = new ArrayList<PersvrObject>();
		int count = 44;
		int start = 54;
		int numberLength = 5;
		byte[] tt = command;
		byte[] tableCount = new byte[] { tt[45], tt[46], tt[47], tt[48] };
		int table = CoderUtils.bytesToInt(tableCount);
		for (int i = 0; i < table; i++) {
			byte[] t = new byte[count];
			System.arraycopy(tt, start, t, 0, count);
			persvrObjectList.addAll(super.analysisTabble("power", t));
			start = start + count + numberLength;
		}
		for (int j = 0; j < persvrObjectList.size(); j++) {
			persvrObjectList.get(j).setCXNEObject(CXNEObject);
		}
		return persvrObjectList;
	}
	/**
	 * 过滤设备响应  返回的性能数据
	 * @param persvr
	 *    性能对象的集合（根据发送命令-包括端口  --进行过滤）
	 * @return  powerObject
	 *    返回   光功率 对象的集合
	 */
	public List<PowerObject> getPowerObject(List<PersvrObject> persvr){
		List<PowerObject> powerObject=new ArrayList<PowerObject>();
		PowerObject power=null;
		String portName="";
		if(persvr!=null&&persvr.size()>0){
			for(int i=0;i<persvr.size();i++){
				System.out.println(persvr.get(i).getPerid());
				if(i==0){
					/*
					 * 循环开始，取出第一个端口
					 */
					power=new PowerObject();
					portName=persvr.get(i).getObjId();
				}				
				if(portName.equals(persvr.get(i).getObjId())){
					power.setName(persvr.get(i).getObjId());
					perType(power, persvr.get(i));
					/*
					 * 若此次为最后一个端口的多次循环
					 *   即  循环本次端口，后没有别的端口了
					 */
					if(i==persvr.size()-1){
						/*
						 * 去掉 解析 端口名称遗留的  端口类型即：：：eth/或者pdh/等
						 */
						power.setName(power.getName().substring(4));
						powerObject.add(power);
					}
				}
				/*
				 * 取出下一个端口
				 */
				else{
					/*
					 * 去掉 解析 端口名称遗留的  端口类型即：：：eth/或者pdh/等
					 */
					power.setName(power.getName().substring(4));
					powerObject.add(power);
					power=null;
					power=new PowerObject();
					portName=persvr.get(i).getObjId();
					perType(power, persvr.get(i));
					
				}
				
			}
		}
		return powerObject;
	}
	public void perType(PowerObject power,PersvrObject persver){
		if("53".equals(persver.getPerid())){//lb
			power.setLb(persver.getValue());
		}
		else if("54".equals(persver.getPerid())){//lt
			power.setLt(persver.getValue());
		}
		else if("51".equals(persver.getPerid())){//iop
			power.setIop(persver.getValue());
		}
		else if("52".equals(persver.getPerid())){//oop
			power.setOop(persver.getValue());
		}
	}
}

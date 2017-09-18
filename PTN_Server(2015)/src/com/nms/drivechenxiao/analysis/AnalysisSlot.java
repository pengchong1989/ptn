package com.nms.drivechenxiao.analysis;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.service.impl.CoderUtils;
import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.ptnne.PtnNeObject;
import com.nms.drivechenxiao.service.bean.slot.SlotObject;

public class AnalysisSlot extends CxtOpLump {

	/**
	 * 初始化板卡
	 * 
	 * @param slotId
	 * @param slotObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] setSlot(String slotId, SlotObject slotObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/slot/" + slotId + "/lg"));
		cxtOpItems.add(set("type", slotObject.getType()));
		cxtOpItems.add(commit());
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * 查询板卡类型
	 * 
	 * @param ptnNeObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] getAllSlot(PtnNeObject ptnNeObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "type"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		CxtATTable cxtATTable = getCxtATTable(1, cxtAtomTypes);
		
		List<CxtAtomType> cxtAtomType = new ArrayList<CxtAtomType>();
		cxtAtomType.add(getCxtAtomType(CxtAtomType.AT_STRING, "online"));
		cxtAtomType.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		CxtATTable cxtATTables = getCxtATTable(1, cxtAtomType);
		cxtOpItems.add(begin(3));
		if ("cxt20b".equals(ptnNeObject.getType())) {
			cxtOpItems.add(cd("ne/slot/1/lg"));
			cxtOpItems.add(get(cxtATTable, 1));
			cxtOpItems.add(cd("ne/slot/1/phy"));
			cxtOpItems.add(get(cxtATTables, 1));
		} else if ("cxt100".equals(ptnNeObject.getType())) {
			cxtOpItems.add(cd("ne/slot/1/lg"));
			cxtOpItems.add(get(cxtATTable, 1));
			cxtOpItems.add(cd("ne/slot/1/phy"));
			cxtOpItems.add(get(cxtATTables, 1));
			
			cxtOpItems.add(cd("ne/slot/2/lg"));
			cxtOpItems.add(get(cxtATTable, 1));
			cxtOpItems.add(cd("ne/slot/2/phy"));
			cxtOpItems.add(get(cxtATTables, 1));
			
			cxtOpItems.add(cd("ne/slot/3/lg"));
			cxtOpItems.add(get(cxtATTable, 1));
			cxtOpItems.add(cd("ne/slot/3/phy"));
			cxtOpItems.add(get(cxtATTables, 1));
			
			cxtOpItems.add(cd("ne/slot/4/lg"));
			cxtOpItems.add(get(cxtATTable, 1));
			cxtOpItems.add(cd("ne/slot/4/phy"));
			cxtOpItems.add(get(cxtATTables, 1));
			
			cxtOpItems.add(cd("ne/slot/5/lg"));
			cxtOpItems.add(get(cxtATTable, 1));
			cxtOpItems.add(cd("ne/slot/5/phy"));
			cxtOpItems.add(get(cxtATTables, 1));
			
			cxtOpItems.add(cd("ne/slot/6/lg"));
			cxtOpItems.add(get(cxtATTable, 1));
			cxtOpItems.add(cd("ne/slot/6/phy"));
			cxtOpItems.add(get(cxtATTables, 1));
			
			cxtOpItems.add(cd("ne/slot/7/lg"));
			cxtOpItems.add(get(cxtATTable, 1));
			cxtOpItems.add(cd("ne/slot/7/phy"));
			cxtOpItems.add(get(cxtATTables, 1));
			
		} else if ("cxt20a".equals(ptnNeObject.getType())) {
			cxtOpItems.add(cd("ne/slot/1/lg"));
			cxtOpItems.add(get(cxtATTable, 1));
			cxtOpItems.add(cd("ne/slot/1/phy"));
			cxtOpItems.add(get(cxtATTables, 1));
		} else if ("cxt21a".equals(ptnNeObject.getType())) {
			cxtOpItems.add(cd("ne/slot/1/lg"));
			cxtOpItems.add(get(cxtATTable, 1));
			cxtOpItems.add(cd("ne/slot/1/phy"));
			cxtOpItems.add(get(cxtATTables, 1));
		}else if ("cxt500".equals(ptnNeObject.getType())) {
			cxtOpItems.add(cd("ne/slot/1/lg"));
			cxtOpItems.add(get(cxtATTable, 1));
			cxtOpItems.add(cd("ne/slot/1/phy"));
			cxtOpItems.add(get(cxtATTables, 1));
			
			cxtOpItems.add(cd("ne/slot/2/lg"));
			cxtOpItems.add(get(cxtATTable, 1));
			cxtOpItems.add(cd("ne/slot/2/phy"));
			cxtOpItems.add(get(cxtATTables, 1));
			
			cxtOpItems.add(cd("ne/slot/3/lg"));
			cxtOpItems.add(get(cxtATTable, 1));
			cxtOpItems.add(cd("ne/slot/3/phy"));
			cxtOpItems.add(get(cxtATTables, 1));
			
			cxtOpItems.add(cd("ne/slot/4/lg"));
			cxtOpItems.add(get(cxtATTable, 1));
			cxtOpItems.add(cd("ne/slot/4/phy"));
			cxtOpItems.add(get(cxtATTables, 1));
			
			cxtOpItems.add(cd("ne/slot/5/lg"));
			cxtOpItems.add(get(cxtATTable, 1));
			cxtOpItems.add(cd("ne/slot/5/phy"));
			cxtOpItems.add(get(cxtATTables, 1));
			
			cxtOpItems.add(cd("ne/slot/6/lg"));
			cxtOpItems.add(get(cxtATTable, 1));
			cxtOpItems.add(cd("ne/slot/6/phy"));
			cxtOpItems.add(get(cxtATTables, 1));
			
			cxtOpItems.add(cd("ne/slot/7/lg"));
			cxtOpItems.add(get(cxtATTable, 1));
			cxtOpItems.add(cd("ne/slot/7/phy"));
			cxtOpItems.add(get(cxtATTables, 1));
			
			cxtOpItems.add(cd("ne/slot/8/lg"));
			cxtOpItems.add(get(cxtATTable, 1));
			cxtOpItems.add(cd("ne/slot/8/phy"));
			cxtOpItems.add(get(cxtATTables, 1));
			
			cxtOpItems.add(cd("ne/slot/9/lg"));
			cxtOpItems.add(get(cxtATTable, 1));
			cxtOpItems.add(cd("ne/slot/9/phy"));
			cxtOpItems.add(get(cxtATTables, 1));
			
			cxtOpItems.add(cd("ne/slot/10/lg"));
			cxtOpItems.add(get(cxtATTable, 1));
			cxtOpItems.add(cd("ne/slot/10/phy"));
			cxtOpItems.add(get(cxtATTables, 1));
			
			cxtOpItems.add(cd("ne/slot/11/lg"));
			cxtOpItems.add(get(cxtATTable, 1));
			cxtOpItems.add(cd("ne/slot/11/phy"));
			cxtOpItems.add(get(cxtATTables, 1));
			
			cxtOpItems.add(cd("ne/slot/12/lg"));
			cxtOpItems.add(get(cxtATTable, 1));
			cxtOpItems.add(cd("ne/slot/12/phy"));
			cxtOpItems.add(get(cxtATTables, 1));
			
			cxtOpItems.add(cd("ne/slot/13/lg"));
			cxtOpItems.add(get(cxtATTable, 1));
			cxtOpItems.add(cd("ne/slot/13/phy"));
			cxtOpItems.add(get(cxtATTables, 1));
			
			cxtOpItems.add(cd("ne/slot/14/lg"));
			cxtOpItems.add(get(cxtATTable, 1));
			cxtOpItems.add(cd("ne/slot/14/phy"));
			cxtOpItems.add(get(cxtATTables, 1));
			
			cxtOpItems.add(cd("ne/slot/15/lg"));
			cxtOpItems.add(get(cxtATTable, 1));
			cxtOpItems.add(cd("ne/slot/15/phy"));
			cxtOpItems.add(get(cxtATTables, 1));
			
			cxtOpItems.add(cd("ne/slot/16/lg"));
			cxtOpItems.add(get(cxtATTable, 1));
			cxtOpItems.add(cd("ne/slot/16/phy"));
			cxtOpItems.add(get(cxtATTables, 1));
			
			cxtOpItems.add(cd("ne/slot/17/lg"));
			cxtOpItems.add(get(cxtATTable, 1));
			cxtOpItems.add(cd("ne/slot/17/phy"));
			cxtOpItems.add(get(cxtATTables, 1));
			
			cxtOpItems.add(cd("ne/slot/18/lg"));
			cxtOpItems.add(get(cxtATTable, 1));
			cxtOpItems.add(cd("ne/slot/18/phy"));
			cxtOpItems.add(get(cxtATTables, 1));
			
			cxtOpItems.add(cd("ne/slot/19/lg"));
			cxtOpItems.add(get(cxtATTable, 1));
			cxtOpItems.add(cd("ne/slot/19/phy"));
			cxtOpItems.add(get(cxtATTables, 1));
		}

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);

		return command;
	}

	/**
	 * 解析查询板卡类型
	 * 
	 * @param command
	 * @param CXNEObject
	 * @return
	 */
	public SlotObject[] analysisSelectSlot(byte[] command, CXNEObject CXNEObject) {
		SlotObject[] slotObjectList = null;
		List<SlotObject> slotList = new ArrayList<SlotObject>();
		byte[] tt = command;
		byte[] tableCount = new byte[] { 0x00, tt[5], tt[6], tt[7] };
		int table = CoderUtils.bytesToInt(tableCount);
		int start = 49;
		int count = 19;//  板卡类型
		int online=13;//在线状态的
		int numberLength = 11;
		/**
		 * 注：：：211 这个还不知道是哪个网元
		 */
		if (table == 211) {
			slotObjectList = new SlotObject[12];
			for (int i = 0; i < 12; i++) {
				byte[] t = new byte[count];
				System.arraycopy(tt, start, t, 0, count);
				slotList.addAll(super.analysisTabble("slot", t));
				slotList.get(i).setId(i + 1 + "");
				
				slotObjectList[i] = slotList.get(i);
				start = start + count + numberLength;
			}
		}
		//700b ---35
		else if (table == 444) {
			slotObjectList = new SlotObject[7];
			for (int i = 0; i < 7; i++) {
				byte[] t = new byte[count+online];
				System.arraycopy(tt, start, t, 0, count);
				/**
				 * 去除  查在线状态的   table（30）
				 */
				t[4]=(byte) (t[4]+1);
				//  第2个 table 有5位
				start=start+count+numberLength+5;								
				System.arraycopy(tt, start, t, count, online);				
				slotList.addAll(super.analysisTabble("slot", t));
				slotList.get(i).setId(i + 1 + "");
//				System.out.println(slotList.get(i).getType());
//				System.out.println(slotList.get(i).getOnline());
				slotObjectList[i] = slotList.get(i);
				start = start+ online + numberLength;
			}
		}
		//700a ---201
		else if (table == 1152) {
			slotObjectList = new SlotObject[19];
			for (int i = 0; i < 19; i++) {
				byte[] t = new byte[count+online];
				System.arraycopy(tt, start, t, 0, count);

				/**
				 * 去除  查在线状态的   table（30）
				 */
				t[4]=(byte) (t[4]+1);
				//  第2个 table 有5位
				start=start+count+numberLength+5;								
				System.arraycopy(tt, start, t, count, online);				
				slotList.addAll(super.analysisTabble("slot", t));
				slotList.get(i).setId(i + 1 + "");
				slotObjectList[i] = slotList.get(i);
				start = start+ online + numberLength;
			}
		} 
		//700d  ---202/203
		else if (table == 90) {
			slotObjectList = new SlotObject[1];
			byte[] t = new byte[count+online];
			System.arraycopy(tt, start, t, 0, count);
			/**
			 * 去除  查在线状态的   table（30）
			 */
			t[4]=(byte) (t[4]+1);
			//  第2个 table 有5位(不解析，将板卡逻辑类型，物理板卡下的在线状态放在一起)
			start=start+count+numberLength+5;								
			System.arraycopy(tt, start, t, count, online);				
			slotList.addAll(super.analysisTabble("slot", t));
			slotList.get(0).setId("1");
			slotObjectList[0] = slotList.get(0);
		}
		return slotObjectList;
	}
}

package com.nms.drivechenxiao.analysis.slot;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.slot.SlotTempObject;

/**
 * 温度管理
 * @author sy
 *
 */
public class AnalysisSlotTemp extends CxtOpLump{
	
	/**
	 * 更新   高温，低温门限
	 * @param slotTempObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] updateTemp(SlotTempObject slotTempObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		List<CxtAtomType> cxtAtomTypes1 = new ArrayList<CxtAtomType>();
		String path = "ne/slot/"+slotTempObject.getId() ;
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd(path));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "temphighthr"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_NUM_32, slotTempObject.getTemphighthr()));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "templowthr"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_NUM_32, slotTempObject.getTemplowthr()));
		
		//cxtOpItems.add(mset("temphighthr", slotTempObject.getTemphighthr()));
		//cxtOpItems.add(setNum("templowthr", slotTempObject.getTemplowthr()));
		CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes1.size() / 2, cxtAtomTypes1);
		cxtOpItems.add(mset("lg", cxtATTable));
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
//		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
//		String path = "ne/slot/"+1+"/lg" ;
//		cxtOpItems.add(begin(3));
//		cxtOpItems.add(cd(path));
//		cxtOpItems.add(setNum("temphighthr", slotTempObject.getTemphighthr()));
//		cxtOpItems.add(setNum("templowthr", slotTempObject.getTemplowthr()));
//		cxtOpItems.add(commit());
//
//		byte[] command = getCommandBytes(cxtOpItems);
//		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
//		return command;
	}
	//生成查询命令
    
		public byte[] selectTemp( SlotTempObject slotTempObject,int session, int seqid){
			String pathr ="ne/slot/" +slotTempObject.getName();			
			List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
			String s = null;
			cxtOpItems.add(begin(3));
			cxtOpItems.add(cd(pathr));
			cxtOpItems.add(get(s, 2)) ;
			
			byte[] command = getCommandBytes(cxtOpItems);
			command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
			return command;
		}
		//解析
		public List<SlotTempObject> analysisSlotTemp(byte[] command, CXNEObject CXNEObject) {
		
			List<SlotTempObject> tempObject = new ArrayList<SlotTempObject>();
			int start = 49;
			byte[] tt = command;
			//		byte[] tt = new byte[] { 114, 109, 116, 1, 0, 0, 0, -104, 34, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 48, 0, 0, 0, 7, 32, 0, 0, 0, 3, 97, 114, 112, 48, 0, 0, 0, 0, 32, 0, 0, 0, 4, 100, 101, 115, 99, 32, 0, 0, 0, 3, 112, 116, 110, 32, 0, 0, 0, 7, 105, 102, 105, 110, 100, 101, 120, 19, 0, 0, 4, 1, 32, 0, 0, 0, 6, 105, 102, 110, 97, 109, 101, 19, 0, 0, 4, 1, 32, 0, 0, 0, 2, 105, 112, 26, 66, 64, 5, 0, -128, -1, 0, 0, 32, 0, 0, 0, 3, 109, 116, 117, 19, 0, 0, 5, -36, 32, 0, 0, 0, 3, 114, 101, 102, 19, 0, 0, 0, 0, 0, };
			byte[] t = new byte[tt.length - start];
			System.arraycopy(tt, start, t, 0, tt.length - start);
			tempObject = super.analysisTabble("slotTemp", t);
			return tempObject;
		}
		public static void main(String[] s){
//			AnalysisSlotTemp ac = new AnalysisSlotTemp();
//			SlotTempObject t=new SlotTempObject();
//			t.setTemphighthr("62");
//			t.setTemplowthr("23");
//			List<SlotTempObject> st=ac.analysisSlotTemp(ac.getTemp(), null);
//			for(SlotTempObject st1:st){
//				System.out.println("高温门限"+st1.getHisHighTemp());
//				System.out.println("低温门限"+st1.getHisLowTemp());
//			}
		}
		private byte[] getTemp(){
			byte[] bt=new byte[]{
				(byte)0x72,(byte)0x6D,(byte)0x74,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x95,
				(byte)0x12,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,
				(byte)0x05,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x01,(byte)0x13,(byte)0x00,(byte)0x00,
				(byte)0x00,(byte)0x03,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x02,(byte)0x20,
				(byte)0x03,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,
				(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x0C,(byte)0x6E,(byte)0x65,(byte)0x2F,(byte)0x73,
				(byte)0x6C,(byte)0x6F,(byte)0x74,(byte)0x2F,(byte)0x31,(byte)0x2F,(byte)0x6C,(byte)0x67,
				(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x04,(byte)0x20,(byte)0x00,(byte)0x00,
				(byte)0x00,(byte)0x0B,(byte)0x74,(byte)0x65,(byte)0x6D,(byte)0x70,(byte)0x68,(byte)0x69,
				(byte)0x67,(byte)0x68,(byte)0x74,(byte)0x68,(byte)0x72,(byte)0x13,(byte)0x00,(byte)0x00,
				(byte)0x00,(byte)0x40,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x04,(byte)0x20,(byte)0x00,
				(byte)0x00,(byte)0x00,(byte)0x0A,(byte)0x74,(byte)0x65,(byte)0x6D,(byte)0x70,(byte)0x6C,
				(byte)0x6F,(byte)0x77,(byte)0x74,(byte)0x68,(byte)0x72,(byte)0x13,(byte)0x00,(byte)0x00,
				(byte)0x00,(byte)0x17,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x0B,(byte)0x13,(byte)0x00,
				(byte)0x00,(byte)0x00,(byte)0x02,(byte)0x20,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x25,
				(byte)0x24,(byte)0x65,(byte)0x36,(byte)0x35,(byte)0x39,(byte)0x30,(byte)0x63,(byte)0x31,
				(byte)0x65,(byte)0x2D,(byte)0x61,(byte)0x64,(byte)0x37,(byte)0x65,(byte)0x2D,(byte)0x34,
				(byte)0x65,(byte)0x66,(byte)0x64,(byte)0x2D,(byte)0x61,(byte)0x32,(byte)0x38,(byte)0x65,
				(byte)0x2D,(byte)0x39,(byte)0x63,(byte)0x31,(byte)0x62,(byte)0x363,(byte)0x61,(byte)0x61,
				(byte)0x62,(byte)0x61,(byte)0x36,(byte)0x31,(byte)0x64//,(byte)0x20,(byte)0x00,(byte)0x00,
//				(byte)0x00,(byte)0x05,(byte)0x69,(byte)0x63,(byte)0x61,(byte)0x70,(byte)0x31,(byte)0x30,
//				(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x01,(byte)0x20,(byte)0x00,(byte)0x00,(byte)0x00,
			
				
			};
			return bt;
		}
}

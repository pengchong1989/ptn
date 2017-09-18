package com.nms.drivechenxiao.analysis.weihu;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nms.drive.service.impl.CoderUtils;
import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.clock.ExtclkObject;
import com.nms.drivechenxiao.service.bean.clock.TodObject;
import com.nms.drivechenxiao.service.bean.portpdh.PdhPortObject;
import com.nms.drivechenxiao.service.bean.protsdh.SdhPortObject;
import com.nms.ui.manager.ExceptionManage;

/**
 * TMD环回
 * @author sy
 *
 */
public class AnalysisLoopBack extends CxtOpLump {
	/**
	 * 设置环回模式 (新建，修改，删除：-都属于更新)---包括（pdh,sdh,extclk端口）
	 * @param pdhPortObejct
	 * @param session
	 * @param seqid
	 */
	public byte[] updateLoop(List<Object> objectList,int session,int seqid){
		PdhPortObject pdhPortObject=null;
		SdhPortObject sdhPortObject=null;
		ExtclkObject extclkObject=null;
		List<CxtOpItem> cxtOpItems=new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		//取出参数集合：
		if(objectList!=null&&objectList.size()>0){
			//遍历集合
			for(Object object:objectList){
				if(object instanceof PdhPortObject){//pdh端口
					pdhPortObject=(PdhPortObject) object;
					if(pdhPortObject.getName()!=null&&!pdhPortObject.getName().equals("")){
						cxtOpItems.add(cd("ne/interfaces/pdh/"+pdhPortObject.getName()));
						cxtOpItems.add(setmac("loopback", getInt(pdhPortObject.getLoopback())));
					}					
				}else if(object instanceof SdhPortObject){// sdh端口
					sdhPortObject=(SdhPortObject) object;
					if(sdhPortObject.getName()!=null&&!sdhPortObject.getName().equals("")){
						cxtOpItems.add(cd("ne/interfaces/sdh/"+sdhPortObject.getName()));
						cxtOpItems.add(setmac("loopback", getInt(sdhPortObject.getLoopback())));
					}					
				}else if(object instanceof ExtclkObject){//extclk
					extclkObject=(ExtclkObject) object;
					if(extclkObject.getName()!=null&&!extclkObject.getName().equals("")){
						cxtOpItems.add(cd("ne/interfaces/extclk/"+extclkObject.getName()));
						cxtOpItems.add(setmac("loopback", getInt(extclkObject.getLoopback())));
					}
				}
			}
		}
		cxtOpItems.add(commit());
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	/**
	 * 查询
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] selectLoopBack(int session, int seqid){		
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		List<CxtAtomType> clockporttype = new ArrayList<CxtAtomType>();
		clockporttype.add(getCxtAtomType(CxtAtomType.AT_STRING, "admin"));
		clockporttype.add(getCxtAtomType(CxtAtomType.AT_STRING, "" ));
		clockporttype.add(getCxtAtomType(CxtAtomType.AT_STRING, "oper"));
		clockporttype.add(getCxtAtomType(CxtAtomType.AT_STRING, "" ));
		clockporttype.add(getCxtAtomType(CxtAtomType.AT_STRING, "loopback"));
		clockporttype.add(getCxtAtomType(CxtAtomType.AT_STRING, "" ));
		CxtATTable clocktype = getCxtATTable(clockporttype.size() / 2, clockporttype);
		
		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "__noempty"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "__match_^%w+.%d+.%d+$"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_TABLE, clocktype ));
		//__match_^%w+.%d+.%d+$
		CxtATTable cxt = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);	
		
		cxtOpItems.add(cd("ne/interfaces/pdh"));			
		cxtOpItems.add(get(cxt, 2)) ;		
		cxtOpItems.add(cd("ne/interfaces/sdh"));
		cxtOpItems.add(get(cxt, 2)) ;
		cxtOpItems.add(cd("ne/interfaces/extclk"));
		cxtOpItems.add(get(0, 1)) ;
		
//		cxtOpItems.add(cd("ne/interfaces/tod"));
//		cxtOpItems.add(get(0,1)) ;
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	/**
	 * 解析
	 * @param command
	 * @param CXNEObject
	 * @return
	 */
	public List<Object> analysisSelectLoopBack(byte[] command, CXNEObject cxNEObject){
		List<Object> objectList=new ArrayList<Object>();
		List<Object> objSdh=null;
		List<Object> objPdh=null;
		List<Object> objExt=null;
		List<Object> objdh=null;
		List<Object> obje=null;
		int start = 65;
		byte[] tt = command;
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		List<byte[]> lbe = new ArrayList<byte[]>(2);
		bytedelete0x00(t,lbe);
		if(lbe!=null&&lbe.size()>0){
			byte[] bepdh=lbe.get(0);
			objPdh = super.analysisTabble("loopback", bepdh);
			List<byte[]> lbh = new ArrayList<byte[]>(2);
			bytedelete0x00(lbe.get(1),lbh);
			if(lbh!=null&&lbh.size()>0){
				byte[] besdh=lbh.get(0);
				objSdh = super.analysisTabble("loopback", besdh);
				byte[] beext=lbh.get(1);
				objExt= super.analysisTabble("loopback", beext);
			}else{
				obje=super.analysisTabble("loopback", lbe.get(1));
			}
			
		}else{
			objdh=super.analysisTabble("loopback", t);
		}
		if(objSdh!=null){
			objectList.addAll(objSdh);
		}
		if(objPdh!=null){
			objectList.addAll(objPdh);
		}
		if(objExt!=null){
			objectList.addAll(objExt);
		}
		if(objdh!=null){
			objectList.addAll(objdh);
		}
		if(obje!=null){
			objectList.addAll(obje);
		}
		return objectList;
	}
	public byte[] checkLoopBack(List<Object> objectList,int session, int seqid){		
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		PdhPortObject pdhPortObject=null;
		SdhPortObject sdhPortObject=null;
		TodObject todObject=null;
		ExtclkObject extclkObject=null;
		cxtOpItems.add(begin(3));
		if(objectList!=null&&objectList.size()>0){
			for(Object object:objectList){
				if(object instanceof PdhPortObject){
					pdhPortObject=(PdhPortObject) object;					
					cxtOpItems.add(cd("ne/interfaces/pdh/"+pdhPortObject.getName()));
					cxtOpItems.add(get(0, 0)) ;					
				}else if(object instanceof SdhPortObject){
					sdhPortObject=(SdhPortObject) object;
					cxtOpItems.add(cd("ne/interfaces/sdh/"+sdhPortObject.getName()));
					cxtOpItems.add(get(0, 0)) ;
				}else if(object instanceof TodObject){
					todObject=(TodObject) object;
					cxtOpItems.add(cd("ne/interfaces/tod/"+todObject.getName()));
					cxtOpItems.add(get(0, 0)) ;
				}else if(object instanceof ExtclkObject){
					extclkObject=(ExtclkObject) object;
					cxtOpItems.add(cd("ne/interfaces/extclk/"+extclkObject.getName()));
					cxtOpItems.add(get(0, 0)) ;
				}
			}	
		}
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	/**
	 * 由于命令里有多步cd然后get(null,2) 的格式,发现设备每次cd间隔11个0x00.所以人为去掉11个0x00,有助于
	 * **/
	public int bytedelete0x00(byte[] arr,List<byte[]> lb){
		int i=0;
		byte[] tem1  ;
		byte[] tem2  ;
		for(;i<arr.length;i++){
			if(arr[i] == (byte)0x00 ){
				for(int q=1; q<=12&&i<arr.length ; q++,i++){					
					
					if(i < arr.length){
						if(arr[i] != 0x00) break;
						if((arr[i]== 0x00 )&& (q >= 11 )  ){
							tem1 = java.util.Arrays.copyOfRange(arr, 0,i-10);
							tem2 = java.util.Arrays.copyOfRange(arr, i+1,arr.length) ;
							System.out.println("i = "+i+" ; t1.le="+tem1.length+" ;t2.le="+tem2.length);
							lb.add(0, tem1);
							lb.add(1, tem2);
							return i;
						}
					}
					
				}
			}
		}	
		return i;
	}
	/**
	 * 字符转为Int
	 * @param loop
	 * @return
	 */
	private int getInt(String loop){
		if(loop!=null&&!loop.equals("")){
			return Integer.parseInt(loop);
		}else{
			return 0;
		}
	}
	public static void main(String args[]){
		byte[] b=new byte[]{0x42,0x73,(byte) 0xAD,(byte) 0x87,(byte) 0xDD,0x00,(byte) 0xD0,0x00};
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String n="1398646462855";
			System.out.println(new Date(n));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,AnalysisLoopBack.class);
		}
		//System.out.println(CoderUtils.a1ToTimeL(b));
		//System.out.println(CoderUtils.bytesToInt(b));
		byte[] a=new byte[]{0x00,0x00,0x00,0x08};
		System.out.println(String.valueOf(CoderUtils.bytesToInt(a)));
	}
}

package com.nms.service.impl.dispatch;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.management.ManagementFactory;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.ui.manager.ExceptionManage;
import com.sun.management.OperatingSystemMXBean;

public class ServiceDispatch implements DispatchInterface{

	/**
	 * 获取相应的CPU/内存/硬盘容量
	 * CPU=1/内存=2/硬盘=3
	 */
	@Override
	public Object consistence(int siteId) throws RemoteException, Exception {
		Map<Integer,Object> serviceValue = new HashMap<Integer, Object>();
		//cpu的使用率
		serviceValue.put(1, getCpuRatioForWindows());
		//内存的性能参数
		serviceValue.put(2, getMemoryValue());
		//硬盘的性能参数
		serviceValue.put(3, getDiscValue());
		return serviceValue;
	}

	@Override
	public String excuteDelete(Object object) throws RemoteException, Exception {
		return null;
	}

	/**
	 * 获取服务器端数据库的信息
	 * 
	 */
	@Override
	public String excuteInsert(Object object) throws RemoteException, Exception {
		BufferedReader br = null;
		List<String> allCommends = new ArrayList<String>();
		String line = "";
		String[] commendsData = null;
		String result = "";
		try {
			 Process proc = Runtime.getRuntime().exec( "tasklist /fi \"IMAGENAME eq mysql*\" /v ");   
		     br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			 StringBuffer stringBuffer = new StringBuffer();
			 
			 while ((line = br.readLine()) != null) {
				 if(!"".equals(line)){
					 stringBuffer.append(line.trim()+";");
				 }
			 }
			 String commend = stringBuffer.toString();
			 if(commend != null && !"".equals(commend)){
				 String[] commends = commend.split(";");
				 for(int i = 0;i<commends.length;i++){
					 if(commends[i].contains("mysql")){
						 commendsData = commends[i].split(" ");
					 }
				 }
				 if(commendsData != null && commendsData.length >0){
					 
					 for(int i = 0; i<commendsData.length; i++){
						 if(!"".equals(commendsData[i])){
							 allCommends.add(commendsData[i]);
						 }
					 }
				 }
				 if(allCommends.size()>1){
					 result = allCommends.get(1)+","+allCommends.get(6);
				 }
			 }
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}finally{
			if(br!= null){
				try {
					 br.close();
				} catch (Exception e2) {
					ExceptionManage.dispose(e2, getClass());
				}finally{
					br = null;
				}
			}
		}
		return result;
	 }

	/**
	 *返回服务器端的PId的值
	 * 
	 */
	@Override
	public String excuteUpdate(Object object) throws RemoteException, Exception {
		String pid = "0";
		try {
			String name = ManagementFactory.getRuntimeMXBean().getName();  
			pid = name.split("@")[0]; 
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
	   return pid;
	}

	/**
	 * 
	 * 获取服务器相关信息
	 */
	@Override
	public String synchro(int siteId) throws RemoteException, Exception {
		String serviceInfo = "";
		Properties props= System.getProperties();  
		try {
	        serviceInfo = props.getProperty("user.name")+";"+props.getProperty("os.name")+" "+props.getProperty("os.version");
		} catch (Exception e) {
			props = null;
		}
		return serviceInfo;
	}
	
	
	  /** 
     * 获得CPU使用率. 
     * @return 返回cpu使用率 
     * @author GuoHuang 
     */ 
    private double getCpuRatioForWindows() { 
    	 final int CPUTIME = 30;
    	 final int PERCENT = 100; 
        try { 
            String procCmd = System.getenv("windir") 
                    + "\\system32\\wbem\\wmic.exe process get Caption,CommandLine," 
                    + "KernelModeTime,ReadOperationCount,ThreadCount,UserModeTime,WriteOperationCount"; 
            // 取进程信息 
            long[] c0 = readCpu(Runtime.getRuntime().exec(procCmd)); 
            Thread.sleep(CPUTIME); 
            long[] c1 = readCpu(Runtime.getRuntime().exec(procCmd)); 
            if (c0 != null && c1 != null) { 
                long idletime = c1[0] - c0[0]; 
                long busytime = c1[1] - c0[1]; 
                return  Double.valueOf(PERCENT * (busytime) / (busytime + idletime)).doubleValue(); 
            } else { 
                return 0.0; 
            } 
        } catch (Exception ex) { 
            ExceptionManage.dispose(ex, getClass());
            return 0.0; 
        } 
    } 

   /**
    * 读取CPU信息. 
    * @param proc 
    * @return 
    * @author GuoHuang 
    */ 
   private long[] readCpu(final Process proc) { 
       long[] retn = new long[2]; 
       InputStreamReader ir = null;
       LineNumberReader input = null;
       try { 
    	    try 
    	    {
    		   if(proc != null && proc.getOutputStream() != null)
        	   {
    			   proc.getOutputStream().close();
        	   }
			} catch (Exception e) 
			{
				ExceptionManage.dispose(e, getClass());
			}
            ir = new InputStreamReader(proc.getInputStream()); 
            input = new LineNumberReader(ir); 
           String line = input.readLine(); 
           if (line == null || line.length() < 100) { 
               return null; 
           } 
           int capidx = line.indexOf("Caption"); 
           int cmdidx = line.indexOf("CommandLine"); 
           int rocidx = line.indexOf("ReadOperationCount"); 
           int umtidx = line.indexOf("UserModeTime"); 
           int kmtidx = line.indexOf("KernelModeTime"); 
           int wocidx = line.indexOf("WriteOperationCount"); 
           long idletime = 0; 
           long kneltime = 0; 
           long usertime = 0; 
           while ((line = input.readLine()) != null) { 
               if (line.length() < wocidx) { 
                   continue; 
               } 
               // 字段出现顺序：Caption,CommandLine,KernelModeTime,ReadOperationCount, 
               // ThreadCount,UserModeTime,WriteOperation 
               String caption = this.substring(line, capidx, cmdidx - 1).trim(); 
               String cmd = this.substring(line, cmdidx, kmtidx - 1).trim(); 
               if (cmd.indexOf("wmic.exe") >= 0) { 
                   continue; 
               } 
               String s1 = this.substring(line, kmtidx, rocidx - 1).trim(); 
               String s2 = this.substring(line, umtidx, wocidx - 1).trim(); 
               if (caption.equals("System Idle Process") || caption.equals("System")) { 
                   if (s1.length() > 0) 
                       idletime += Long.valueOf(s1).longValue(); 
                   if (s2.length() > 0) 
                       idletime += Long.valueOf(s2).longValue(); 
                   continue; 
               } 
             try {
            	 if (s1.length() > 0){
            		 kneltime += Long.valueOf(s1).longValue(); 
            	 }
            	 if (s2.length() > 0) {
            		 usertime += Long.valueOf(s2).longValue(); 
            	 }
            	 
			} catch (Exception e) {
				continue; 
			}
           } 
           retn[0] = idletime; 
           retn[1] = kneltime + usertime; 
           return retn; 
       } catch (Exception ex) { 
           ExceptionManage.dispose(ex, getClass());
       } finally { 
           try { 
               proc.getInputStream().close(); 
           } catch (Exception e) { 
        	   ExceptionManage.dispose(e, getClass());
           } 
           try { 
        	   ir.close();
           } catch (Exception e) { 
        	   ExceptionManage.dispose(e, getClass());
           } 
           try { 
        	   input.close();
           } catch (Exception e) { 
        	   ExceptionManage.dispose(e, getClass());
           } 
       } 
       return null; 
   }
    /**
     *  其中，Bytes类用来处理字符串 
     * @param src
     * @param start_idx
     * @param end_idx
     * @return
     */
    private  String substring(String src, int start_idx, int end_idx){ 
        byte[] b = src.getBytes(); 
        String tgt = ""; 
        for(int i = start_idx; i<end_idx; i++){ 
            tgt +=(char)b[i]; 
        } 
        return tgt; 
    } 
    
    /**
     * 用来读取内存
     * @return
     */
    private List<Long> getMemoryValue() {
    	int kb = 1024;
    	List<Long> memoryList = new ArrayList<Long>();
    	OperatingSystemMXBean osmxb = null;
    	try {
    		osmxb = (OperatingSystemMXBean) ManagementFactory .getOperatingSystemMXBean(); 
    		//// 总的物理内存 
    		long totalMemorySize = osmxb.getTotalPhysicalMemorySize() / kb; 
    		memoryList.add(totalMemorySize);
    		// 已使用的物理内存 
    		long usedMemory = (osmxb.getTotalPhysicalMemorySize() - osmxb .getFreePhysicalMemorySize())  / kb; 
    		memoryList.add(usedMemory);
    		// 剩余的物理内存 
    		long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize() / kb;
    		memoryList.add(freePhysicalMemorySize);
		} catch (Exception e) {
			 ExceptionManage.dispose(e, getClass());
		}finally{
			osmxb = null;
		}
		return memoryList;
	}
    
	/**
	 * 
	 * 获取磁盘的内存空间
	 * @return
	 */
	private Object getDiscValue() {
		try {
			return  File.listRoots();
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return null;
	}
	
	public static void main(String[] args) {
		ServiceDispatch x = new ServiceDispatch();
		try {
//			System.out.println("ccc=="+x.consistence(1));
			System.out.println(x.synchro(0));
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}



package com.nms.drive.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.nms.drive.service.impl.bean.DriveUtilObject;
import com.nms.drive.service.impl.bean.ResponseCommandObject;
import com.nms.service.bean.OperationObject;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;

public class MonitroMergerPagesThread extends Thread{
	private boolean isRun = true;
	private int dividePageHandByteCount = 12;// 适配层分片头字节数
	// 已收到响应的分片 报文序列号-分片号
	private Map<String, ResponseCommandObject> responseCommandMap = new HashMap<String, ResponseCommandObject>();
	
	
	@Override
	public void run() {
		while(isRun){
			Iterator<Map<String,byte[]>> maps = ConstantUtil.monitorResponseLinkedQueue.iterator();
			while(maps.hasNext()){
				Map<String,byte[]> map = maps.next();
				String sourceIp = (String) map.keySet().iterator().next();
				byte[] commands = map.get(sourceIp);
				ResponseCommandObject responseCommandObject = new ResponseCommandObject();
				responseCommandObject.setResponseDate(new Date());// 获得时间

				// 报文序列号
				byte[] uuid = new byte[] { commands[0], commands[1], commands[2], commands[3] };
				responseCommandObject.setUuid(CoderUtils.bytesToInt(uuid));
//				 System.out.println("【报文序列号】" + CoderUtils.bytesToInt(uuid));

				// 分片号
				byte[] pagesCount = new byte[] { 0x00, 0x00, commands[4], commands[5] };
				responseCommandObject.setPagesCount(CoderUtils.bytesToInt(pagesCount));
//				 System.out.println("【分片号】" +
//				 CoderUtils.bytesToInt(pagesCount));

				// 总片数
				byte[] pagesMaxCount = new byte[] { 0x00, 0x00, commands[6], commands[7] };
				responseCommandObject.setPagesMaxCount(CoderUtils.bytesToInt(pagesMaxCount));
//				 System.out.println("【总片数】" +
//				 CoderUtils.bytesToInt(pagesMaxCount));
	//
//				// 控制字
				byte[] ctrl = new byte[] { 0x00, 0x00, 0x00, commands[8] };
				responseCommandObject.setCtrl(CoderUtils.bytesToInt(ctrl));
//				 System.out.println("【控制字】" + CoderUtils.bytesToInt(ctrl));

//				// 备用（全0）
				responseCommandObject.setSpare(commands[9]);
//				 System.out.println("【备用】" + commands[9]);

//				// 数据长度
				byte[] length = new byte[] { 0x00, 0x00, commands[10], commands[11] };
				responseCommandObject.setLength(CoderUtils.bytesToInt(length));
////				 System.out.println("【数据长度】" + CoderUtils.bytesToInt(length));
	//
//				// 去掉前面12个字节的分片头
				byte[] newCommand = new byte[commands.length - dividePageHandByteCount];
				System.arraycopy(commands, dividePageHandByteCount, newCommand, 0, newCommand.length);
				responseCommandObject.setCommand(newCommand);

				// 判断是响应信息 还是 主动发过来的 告警信息 重传命令
				// ？？？？？还没有实现
				if(responseCommandObject.getPagesMaxCount()>1){
					ExceptionManage.infor("remove"+responseCommandObject.getUuid() + "-" + responseCommandObject.getPagesCount()+sourceIp, this.getClass());
				}
				responseCommandMap.put(responseCommandObject.getUuid() + "-" + responseCommandObject.getPagesCount()+sourceIp, responseCommandObject);
				if(CoderUtils.bytesToInt(pagesCount) == CoderUtils.bytesToInt(pagesMaxCount)){
					
					mergerPages(sourceIp);// 请求后响应的命令合并
				}
				
				maps.remove();
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				ExceptionManage.dispose(e, this.getClass());
			}
		}
	}

	@Override
	public void destroy() {
		isRun = false;// 停止线程
	}
	
	/**
	 * 将多条相应命令合并成一条完整的命令
	 * 
	 * @throws Exception
	 */
	private void mergerPages(String sourceIp){
		try {
			ResponseCommandObject responseCommandObject = null;
			int pagesMaxCount = 0;
			String[] uuidArr = null;
			List<String> removeUuidArr = new ArrayList<String>();
			String temp = "[-1]";// 临时存储
			int jsq = 0;
			
			for (Map.Entry<String, ResponseCommandObject> entry : responseCommandMap.entrySet()) {
				jsq = 0;
				responseCommandObject = entry.getValue();
				
				pagesMaxCount = responseCommandObject.getPagesMaxCount();
				uuidArr = new String[pagesMaxCount];

				for (int i = 1, j = 0; i <= pagesMaxCount; i++, j++) {
					// 过滤掉是否为NULL，过滤掉已经匹配完成的UUID
					if (temp.indexOf("[" + responseCommandObject.getUuid() + "-" + i + "]") == -1 && responseCommandMap.get(responseCommandObject.getUuid() + "-"+i+sourceIp) != null) {
						uuidArr[j] = responseCommandObject.getUuid() + "-" + i;
						jsq++;
					}
				}
				if (pagesMaxCount == jsq) {
					// PDU + 命令控制 + 数据体
					byte[] a = new byte[0];
					for (int i = 0; i < uuidArr.length; i++) {
						byte[] b = responseCommandMap.get(uuidArr[i]+sourceIp).getCommand();
						byte[] c = new byte[a.length + b.length];
						System.arraycopy(a, 0, c, 0, a.length);// 两个数组合并
						System.arraycopy(b, 0, c, a.length, b.length);// 两个数组合并
						removeUuidArr.add(uuidArr[i]+sourceIp);// 添加要删除的UUID
						temp += ",[" + uuidArr[i] + "]";// 已经找到匹配的UUID
						a = c;
					}

					// 如果是请求返回的命令如下处理
					int pduuuid = CoderUtils.bytesToInt(new byte[] { a[8], a[9], a[10], a[11] });
					// 所指的UUID全部都是PDU的会唔号	
					DriveUtilObject driveUtilObject = ConstantUtil.send_commmandListMap.get(pduuuid + "");
					if(driveUtilObject!=null){
						driveUtilObject.setResponseSendCommands(a);
						driveUtilObject.setStates(2);// 标记为响应完整
						driveUtilObject.setResponseDate(new Date());
						 ConstantUtil.send_commmandListMap.remove(pduuuid + "");
						putDriveUtilObject(pduuuid + ""+sourceIp, driveUtilObject);
					}else if(driveUtilObject==null && CoderUtils.bytesToInt(a[5])== 227){//a[5]=227表示主动上报
						driveUtilObject = new DriveUtilObject();
						driveUtilObject.setResponseSendCommands(a);
						driveUtilObject.setStates(2);// 标记为响应完整
						driveUtilObject.setResponseDate(new Date());
						driveUtilObject.setDirection(1);// PTN向网管发送
						driveUtilObject.setUuid(pduuuid);// 这个服务器必须给我，不然整个框架不能支持
						driveUtilObject.setOperID("999999");// 临时的
						driveUtilObject.setOperationObject(new OperationObject());// 临时的
						putDriveUtilObject(pduuuid + ""+sourceIp, driveUtilObject);
					}
					
				}
			}
			for (int i = 0; i < removeUuidArr.size(); i++) {
				responseCommandMap.remove(removeUuidArr.get(i));
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}
	
	/**
	 * 添加已响应完成 DriveUtilObject
	 * 
	 * @param uuid
	 * @param driveUtilObject
	 */
	private void putDriveUtilObject(String uuid, DriveUtilObject driveUtilObject) {
		ConstantUtil.recive_commmandListMap.put(uuid, driveUtilObject);
	}
}

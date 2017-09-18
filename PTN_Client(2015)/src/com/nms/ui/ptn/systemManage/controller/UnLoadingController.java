package com.nms.ui.ptn.systemManage.controller;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.nms.db.bean.system.UnLoadFactory;
import com.nms.db.bean.system.UnLoading;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.alarm.HisAlarmService_MB;
import com.nms.model.perform.HisPerformanceService_Mb;
import com.nms.model.system.OperationLogService_MB;
import com.nms.model.system.TranferService_Mb;
import com.nms.model.system.loginlog.LoginLogServiece_Mb;
import com.nms.model.util.Services;
import com.nms.service.impl.util.ResultString;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysMenu;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.systemManage.ReadUnloadXML;
import com.nms.ui.ptn.systemManage.bean.TranferInfo;
import com.nms.ui.ptn.systemManage.view.InportDialog;
import com.nms.ui.ptn.systemManage.view.UnLoadUpdateDialog;
import com.nms.ui.ptn.systemManage.view.UnLoadingPanel;
/**
 * 主界面按钮事件处理
 * @author Administrator
 *
 */
public class UnLoadingController extends AbstractController {
	private UnLoadingPanel view=null;
	UnLoading unload=null;
	List<UnLoading> unloadList=null;
	//转储结果   的时间
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//  转储  生成的 文件 名
	private DateFormat dateFile = new SimpleDateFormat("yyyyMMddHHmmss");
	
	public UnLoadingController(UnLoadingPanel unloadingPanel) {
		this.view = unloadingPanel;
	}

	public UnLoadingController() {
		super();
	}

	@Override
	public void refresh() throws Exception {
		try {
			unloadList = ReadUnloadXML.selectUnloadXML();
			this.view.clear();
			this.view.initData(unloadList);
			this.view.updateUI();

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			unload=null;
			unloadList=null;
		}

	}
	/**
	 * 修改按钮
	 */
	public void openUpdateDialog()throws Exception{
		
		final UnLoadUpdateDialog dialog = new UnLoadUpdateDialog(this);
//		if(this.getView().getSelect().getUnloadType() == 3 || this.getView().getSelect().getUnloadType() == 4){
//			UiUtil.showWindow(dialog, 380, 300);
//		}else{
			UiUtil.showWindow(dialog, 380, 380);
//		}
	}
	/**
	 * 导入
	* @author sy
	
	* @Exception 异常对象
	 */
	public void inport() throws Exception{
		InportDialog dialog = new InportDialog(this);
		UiUtil.showWindow(dialog, 300, 180);
	
	}
	/**
	 * 导出 
	* @author sy
	
	* @Exception 异常对象
	 */
	public void export()throws Exception{
		JTextArea textArea=this.view.getTextArea();
		final List<UnLoading> unloadList=this.getView().getTable().getAllElement();
		HisPerformanceService_Mb hisPerformanceService = null;
		OperationLogService_MB operationLogService = null;
		LoginLogServiece_Mb loginlogServiece = null;
		HisAlarmService_MB service = null;
		TranferService_Mb tranferService=null;
		List<Integer> idList=null;
		final JPanel contentPanel = new JPanel();	
		int cellCount=0;
		/**
		 * 操作日志记录  结果
		 * operationResult
		 * null  成功
		 * ""  失败
		 */
		String operationResult="";
		try {
			int result = DialogBoxUtil.confirmDialog(contentPanel, ResourceUtil.srcStr(StringKeysTip.TIP_UN_LOADING));
			if (result == 0) {
				textArea.setText("");
				int unloadSize=unloadList.size();
				//  遍历    已经选择  激活的对象有几个
				if(unloadSize>0){
					for(UnLoading load: unloadList){
						if(load.getCellType()==0){
							cellCount++;
						}
					}
					if(cellCount>0){
						textArea.append(ResourceUtil.srcStr(StringKeysTip.TIP_UNLOAD_START));
						textArea.append("\n");	
						
						tranferService=(TranferService_Mb)ConstantUtil.serviceFactory.newService_MB(Services.TRANFERSERVICE);
						hisPerformanceService = (HisPerformanceService_Mb) ConstantUtil.serviceFactory.newService_MB(Services.HisPerformance);
						operationLogService = (OperationLogService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OPERATIONLOGSERVIECE);
						loginlogServiece=(LoginLogServiece_Mb) ConstantUtil.serviceFactory.newService_MB(Services.LOGINLOGSERVIECE);
						service = (HisAlarmService_MB) ConstantUtil.serviceFactory.newService_MB(Services.HisAlarm);
						
						
						for(int i=unloadSize-1;i>=0;i--){
							UnLoading unload=(UnLoading) unloadList.get(i);					
							if(unload.getCellType()==0){					
							/**
							 * 验证文件路径是否存在
							 */
								if(null!=unload.getFileWay()){
									 
									File file=new File(unload.getFileWay());
									if(!file.canRead()||file==null){
										textArea.append(UnLoadFactory.trans(unload.getUnloadType())+"  "+ResourceUtil.srcStr(StringKeysTip.TIP_UNLOAD_FILE_FAIL)+unload.getFileWay());
										textArea.append("\n");
									}else{
										if(1 == unload.getUnloadType()){//历史告警																	
											int count = service.selectCount(null, null);
											//z总记录大于保留条目，可以转储
											if(count > unload.getHoldEntry()){
												//可以转储部分溢出条目
												if(count-unload.getHoldEntry()<=unload.getSpillEntry()){								
													//根据溢出条目,取出历史报警记录
													idList=this.unloadHistoryAlarm(tranferService.getDataStr(unload,count-unload.getHoldEntry()),  unload);
													//将记录写入文件中																																		 																		
												}else{
													//数据库中条目多余溢出条目，
													idList=this.unloadHistoryAlarm(tranferService.getDataStr(unload,unload.getSpillEntry()),  unload);																
												}	
	//											 转储成功后，删除表中数据
												service.delete(idList);
												//添加日志记录
												operationResult=null;
												textArea.append(ResourceUtil.srcStr(StringKeysBtn.BTN_HISTORYALARM_SUCCESS)+" "+dateFormat.format(new Date()));	
												textArea.append("\n");
											}else{
												operationResult="";
												textArea.append(ResourceUtil.srcStr(StringKeysBtn.BTN_HISTORYALARM_ALARM_LITTERDATA));
												textArea.append("\n");
											}
										
										}
								if(2==unload.getUnloadType()){//历史性能-15									
									int count=hisPerformanceService.selectCount();
									//z总记录大于保留条目，可以转储
									if(count>unload.getHoldEntry()){
										//可以转储部分溢出条目
										if(count-unload.getHoldEntry()<=unload.getSpillEntry()){									
											//根据溢出条目,取出历史报警记录
											idList=this.unloadHisPerformance(tranferService.getDataStr(unload,count-unload.getHoldEntry()), unload);
											//将记录写入文件中						
										}else{
											//数据库中条目多余溢出条目，
											idList=this.unloadHisPerformance(tranferService.getDataStr(unload,unload.getSpillEntry()), unload);																						
										}
										
										hisPerformanceService.delete(idList);
										operationResult=null;
										textArea.append(ResourceUtil.srcStr(StringKeysBtn.BTN_HISTORYPERFORMANCE_SUCCESS)+" "+dateFormat.format(new Date()));
										textArea.append("\n");
									}else{
										operationResult="";
										textArea.append(ResourceUtil.srcStr(StringKeysBtn.BTN_HISTORYALARM_PERFORMANCE_LITTERDATA));
										textArea.append("\n");
									}	
									
								}
								if(3==unload.getUnloadType()){//操作日志												
									int count=operationLogService.selectCount();
									//z总记录大于保留条目，可以转储
									if(count>unload.getHoldEntry()){
										//可以转储部分溢出条目
										if(count-unload.getHoldEntry()<=unload.getSpillEntry()){								
											//根据溢出条目,取出历史报警记录					
											idList=this.unloadOperationLog(tranferService.getDataStr(unload,count-unload.getHoldEntry()), unload);
											//将记录写入文件中																																		 																		
										}else{
											//数据库中条目多余溢出条目，
											idList=this.unloadOperationLog(tranferService.getDataStr(unload,unload.getSpillEntry()), unload);																
										}	
	                                    // 转储成功后，删除表中数据
										operationLogService.delete(idList);
										operationResult=null;
										textArea.append(ResourceUtil.srcStr(StringKeysBtn.BTN_OPERATIONLOG_SUCCESS)+" "+dateFormat.format(new Date()));	
										textArea.append("\n");
									}else{
										operationResult="";
										textArea.append(ResourceUtil.srcStr(StringKeysBtn.BTN_OPERATIONLOG_LITTERDATA));
										textArea.append("\n");
									}
								} 
								if(4==unload.getUnloadType()){//登录日志												
									int count = loginlogServiece.selectLogCount();
									//z总记录大于保留条目，可以转储
									if(count > unload.getHoldEntry()){
										//可以转储部分溢出条目   
										if(count-unload.getHoldEntry() <= unload.getSpillEntry()){								
											//根据溢出条目,登录日志				
											idList=this.unloadOperationLog(tranferService.getDataStr(unload,count-unload.getHoldEntry()), unload);
											//将记录写入文件中																																		 																		
										}else{
											//数据库中条目多余溢出条目，
											idList=this.unloadOperationLog(tranferService.getDataStr(unload,unload.getSpillEntry()), unload);																
										}	
	                                    // 转储成功后，删除表中数据
										loginlogServiece.delete(idList);
										operationResult=null;
										textArea.append(ResourceUtil.srcStr(StringKeysBtn.BTN_LONG_SUCCESS)+" "+dateFormat.format(new Date()));	
										textArea.append("\n");
									}else{
										operationResult="";
										textArea.append(ResourceUtil.srcStr(StringKeysBtn.BTN_LONGLOG_LITTERDATA));
										textArea.append("\n");
									}
								}

							}
						}
							else{
								textArea.setText("");
								operationResult="";
								textArea.append(ResourceUtil.srcStr(StringKeysTip.TIP_UNLOAD_FILE_FAIL));
								textArea.append("\n");
							}
						}
						}
						textArea.append(ResourceUtil.srcStr(StringKeysTip.TIP_UNLOAD_FINESH));
						textArea.append("\n");	
				}
			else{
				DialogBoxUtil.errorDialog(this.view, ResourceUtil.srcStr(StringKeysTip.TIP_UNLOAD_OBJECT));
				this.insertOpeLog(EOperationLogType.UNLOADOBJECT.getValue(), ResultString.CONFIG_FAILED, null, null);
				return;
			}
			}
				//添加日志记录
				this.insertOpeLog(EOperationLogType.UNLOADEXPORT.getValue(), ResultString.CONFIG_SUCCESS, null, null);		
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(tranferService);
			UiUtil.closeService_MB(service);
			UiUtil.closeService_MB(hisPerformanceService);
			UiUtil.closeService_MB(operationLogService);
			UiUtil.closeService_MB(loginlogServiece);
		}
	}
	
	private void insertOpeLog(int operationType, String result, Object oldMac, Object newMac){
		AddOperateLog.insertOperLog(this.view.getExportButton(), operationType, result, oldMac, newMac, 0,ResourceUtil.srcStr(StringKeysMenu.MENU_UNLOADING),"");		
	}
	/** 导出所有的
	 * @throws Exception
	 */
	public void allExport() throws Exception{
		
	}

	public UnLoadingPanel getView() {
		return view;
	}

	public void setView(UnLoadingPanel view) {
		this.view = view;
	}
	/**
	 * 导出历史告警
	 * @param unload
	 */
	public List<Integer> unloadHistoryAlarm(List<TranferInfo> tranferInfoList,UnLoading unload){
		FileWriter fileWriter=null;
		BufferedWriter bufferedWriter=null;		
		 String fileName=null;
		 List<Integer> idList=null;
		 try {
			 idList=new ArrayList<Integer>();
			 fileName=unload.getFileWay()+"\\"+"ALARM"+"_"+dateFile.format(new Date())+".sql";
			 fileWriter = new FileWriter(fileName, true);
			 bufferedWriter=new BufferedWriter(fileWriter);
			 for(TranferInfo tranferInfo:tranferInfoList){
				 bufferedWriter.write(tranferInfo.getSql());
				 idList.add(tranferInfo.getId());
			 }
		 } catch (Exception e) {
			 ExceptionManage.dispose(e,this.getClass());
		 }finally{
			 // 关闭流		
			 if (null != bufferedWriter) {
				 try {
					 bufferedWriter.close();
				 } catch (Exception e) {
					 ExceptionManage.dispose(e,this.getClass());
				 } finally {
					 bufferedWriter = null;
				}
			 }	
			 // 关闭流		
			 if (null != fileWriter) {
				 try {
					 fileWriter.close();
				 } catch (Exception e) {
					 ExceptionManage.dispose(e,this.getClass());
				 } finally {
					 fileWriter = null;
				 }
			 }	
			 fileName=null;
			 fileWriter = null;	
		 }
		 return idList;
	}
	/**
	 *历史性能 导出
	 * @param hisList
	 * @param unload
	 * @return
	 */
	public List<Integer> unloadHisPerformance(List<TranferInfo> tranferInfoList,UnLoading unload){
		FileWriter fileWriter=null;
		BufferedWriter bufferedWriter=null;	
		 String fileName=null;
		 List<Integer> idList=null;
		 try {
			 idList=new ArrayList<Integer>();
			 fileName=unload.getFileWay()+"\\"+"PERFORMANCE"+"_"+dateFile.format(new Date())+".sql";
			 fileWriter = new FileWriter(fileName, true);
			 bufferedWriter = new BufferedWriter(fileWriter);
			 for(TranferInfo tranferInfo:tranferInfoList){				 			 
					bufferedWriter.write(tranferInfo.getSql());		
					idList.add(tranferInfo.getId());
				}
		 } catch (Exception e) {
			 ExceptionManage.dispose(e,this.getClass());
		 }finally{
			 // 关闭流		
			 if (null != bufferedWriter) {
				 try {
					 bufferedWriter.close();
				 } catch (Exception e) {
					 ExceptionManage.dispose(e,this.getClass());
				 } finally {
					 bufferedWriter = null;
				}
			 }	
			 fileName=null;
			 fileWriter = null;	
		 }
		 return idList;
	}
	/**
	 * 导出操作日志记录
	 * @param unload
	 */
	public List<Integer> unloadOperationLog(List<TranferInfo> tranferInfoList,UnLoading unload){
		FileWriter fileWriter=null;
		BufferedWriter bufferedWriter=null;		
		 String fileName=null;
		 List<Integer> idList=null;
		 try {
			 idList=new ArrayList<Integer>();
			 if(unload.getUnloadType() ==4 ){
				 fileName=unload.getFileWay()+"\\"+"LOGINLOG"+"_"+dateFile.format(new Date())+".sql";
			 }else{
				 fileName=unload.getFileWay()+"\\"+"OPERATIONLOG"+"_"+dateFile.format(new Date())+".sql";
			 }
			 fileWriter = new FileWriter(fileName, true);			
			 bufferedWriter = new BufferedWriter(fileWriter);
			 for(TranferInfo tranferInfo:tranferInfoList){				 			 
				 bufferedWriter.write(tranferInfo.getSql());	
				 idList.add(tranferInfo.getId());
			 }
		 } catch (Exception e) {
			 ExceptionManage.dispose(e,this.getClass());
		 }finally{
			 // 关闭流		
			 if (null != bufferedWriter) {
				 try {
					 bufferedWriter.close();
				 } catch (Exception e) {
					 ExceptionManage.dispose(e,this.getClass());
				 } finally {
					 bufferedWriter = null;
				}
			 }	
			 fileName=null;
			 fileWriter = null;	
		 }
		 return idList;
	}
	
}

package com.nms.rmi.ui;

import java.util.List;
import java.util.TimerTask;

import com.nms.rmi.ui.util.backups.BackupsDatabase;

/**
 * 继承定时器任务，将需要做的事情放入定时任务中
 * 自动备份数据库数据，通过表名
 * @author zk
 */
public class AutoDatabaseBackTask extends TimerTask{
	
    private List<String> tableNames ;//需要备份的所有数据库表名
    private String fileAddress;//备份的目录地址
    private BackupsDatabase backupsDatabase ;
    
	public AutoDatabaseBackTask(List<String> tableNames,String fileAddress){
		this.tableNames = tableNames;
		this.fileAddress = fileAddress;
		backupsDatabase = new BackupsDatabase();
	}
	@Override
	public void run() {
		backupsDatabase.backups(tableNames, fileAddress);
	}
	
}

package com.nms.ui.ptn.alarm.view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import com.nms.db.bean.alarm.AlarmShieldInfo;
import com.nms.db.enums.EOperationLogType;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.UiUtil;

/**
 *告警屏蔽 
 * @author Administrator
 *
 */
public class SiteAlarmShield extends AlarmShield{

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public SiteAlarmShield() {
//		initComponents();
//		setLayout();
		addListention();
	}

	/**
	 * 保存事件
	 */
	private void addListention() {
		super.getConfirmButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				confirmListener();
			}
		}) ;
		
//		super.getShieldModelCombox().addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//		      if(getShieldModelCombox().getSelectedIndex() == 1){
//		    	  getAlarmLineJpanel().newAlarmCodeLevel(2);
//		      }else if(getShieldModelCombox().getSelectedIndex() == 2){
//		    	  getAlarmLineJpanel().newLineCodeLevel(3);
//		      }else{
//		    	  getAlarmLineJpanel().newAlarmLevel(1);		    	  
//		      }
//			}
//		});
		
		super.getClearAlarm().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearAlarmListener();		
			}			
		});
		
	}

	private void clearAlarmListener() {
		String result = "";	
		String result1 = "";
		result=clearAllAlarm(1,0,ConstantUtil.siteId);
		result1=clearAllAlarm(2,0,ConstantUtil.siteId);
		if(result.equals(result1) && result.equals(ResultString.CONFIG_SUCCESS)){
		    DialogBoxUtil.succeedDialog(null, result);
		    UiUtil.insertOperationLog(EOperationLogType.ALARMSHIELD.getValue(),result);
		}else{
			DialogBoxUtil.succeedDialog(null, ResultString.CONFIG_FAILED);
			UiUtil.insertOperationLog(EOperationLogType.ALARMSHIELD.getValue(),ResultString.CONFIG_FAILED);
		}
	}

	private void confirmListener(){
		SiteUtil siteUtil = new SiteUtil();			
		AlarmShieldInfo alarmShieldInfo = new AlarmShieldInfo();
	    DispatchUtil alarmDispatch = null;
	    String result = "";	
		List<Integer> siteIds = null;
		try {
			if(1==siteUtil.SiteTypeUtil(ConstantUtil.siteId)){
				WhImplUtil wu = new WhImplUtil();
				siteIds = new ArrayList<Integer>();
				siteIds.add(ConstantUtil.siteId);
				String str=wu.getNeNames(siteIds);
				DialogBoxUtil.succeedDialog(null, ResultString.CONFIG_SUCCESS+","+str+ResultString.NOT_ONLINE_SUCCESS);
			}else{
				if(getShieldAllAlarm().getSelectedIndex()== 1 || getShieldAllAlarm().getSelectedIndex() ==2){					
					result=clearAllAlarm(getShieldAllAlarm().getSelectedIndex(),1,ConstantUtil.siteId);
					DialogBoxUtil.succeedDialog(null, result);
					UiUtil.insertOperationLog(EOperationLogType.ALARMSHIELD.getValue(),result);
				}else{
					alarmDispatch = new DispatchUtil(RmiKeys.RMI_ALARM);
					alarmShieldInfo = getAlarmLineJpanel().getAlarmShieldInfo();
					alarmShieldInfo.setSiteId(ConstantUtil.siteId);	
					result = alarmDispatch.alarmShield(alarmShieldInfo); 
					DialogBoxUtil.succeedDialog(this, result);
					UiUtil.insertOperationLog(EOperationLogType.ALARMSHIELD.getValue(),result);
				}	
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{			
			alarmDispatch = null;
			siteUtil = null;
			siteIds = null;
		}
	}

}

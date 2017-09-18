package com.nms.ui.manager.util;

import javax.swing.JTable;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.enums.EJobStatus;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;

/**
 * 
 * @author Administrator
 *
 */
public class TableUtil {
	/**
	 * 单击端口时 给table赋端口信息
	 * 
	 * @param jTable
	 * @param portInst
	 */
	@SuppressWarnings("unused")
	public void portTableDate(JTable jTable, PortInst portInst) {

		Object[][] obj = null;
		String managerStatus = null;
		try {
			if (portInst != null) {

				if (UiUtil.getCodeById(portInst.getManageStatus()) != null) {
					managerStatus = UiUtil.getCodeById(portInst.getManageStatus()).getCodeName();
				}

				obj = new Object[][] { { ResourceUtil.srcStr(StringKeysLbl.LBL_NAME), portInst.getPortName() }, { ResourceUtil.srcStr(StringKeysLbl.LBL_ENABLED_STATUS), portInst.getIsEnabled_code() == 0 ? ResourceUtil.srcStr(StringKeysObj.ALLCONFIG_FID_ENABLED_NO) : ResourceUtil.srcStr(StringKeysObj.ALLCONFIG_FID_ENABLED) }, { ResourceUtil.srcStr(StringKeysLbl.LBL_TYPE), portInst.getPortType() }, { ResourceUtil.srcStr(StringKeysLbl.LBL_JOB_STATUS), this.getJobStatus(portInst.getJobStatus()) } };
			}

			jTable.setModel(new javax.swing.table.DefaultTableModel(obj, new String[] { ResourceUtil.srcStr(StringKeysObj.STRING_ATTRIBUTE), ResourceUtil.srcStr(StringKeysObj.STRING_VALUE) }));
		} catch (Exception e) {
			ExceptionManage.dispose(e, UiUtil.class);
		} finally {
			obj = null;
			managerStatus = null;
		}
	}
	
	// TODO
	/**
	 * 根据数据库的工作状态代码转义成字符串
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private String getJobStatus(String jobStatus) {

		if (null == jobStatus || "".equals(jobStatus)) {
			return "";
		}

		String[] jobStatusArray = null;
		String result = "";
		try {
			jobStatusArray = jobStatus.split(",");

			for (String str : jobStatusArray) {
				result += EJobStatus.forms(Integer.parseInt(str)) + ",";
			}
			if (null != result && !"".equals(result)) {
				result = result.substring(0, result.length() - 1);
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e, UiUtil.class);
		}

		return result;
	}
}

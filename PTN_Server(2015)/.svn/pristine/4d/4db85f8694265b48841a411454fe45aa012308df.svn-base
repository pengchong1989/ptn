package com.nms.snmp.ninteface.impl.inventory;

import java.util.List;

import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;

import com.champor.license.Feature;
import com.champor.license.Features;
import com.nms.rmi.ui.util.ServerConstant;
import com.nms.snmp.ninteface.framework.SnmpConfig;
import com.nms.snmp.ninteface.framework.TableHandler;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysLbl;

public class EmsTable extends TableHandler {
	public EmsTable() {
	}

	@Override
	public boolean setInterfaceData(List<VariableBinding> vbList) {
		return true;
	}

	@Override
	public Object getInterfaceData(List<VariableBinding> vbList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateTable(Object obj) {
		Features features = ServerConstant.features;
		if (null != features) {
			for (Feature feature : features.getFeatureList()) {
				if ("NECount".equals(feature.getName())) { // 最大网元数
					SnmpConfig.getInstanse().setEmsMaxSiteCount(feature.getValue());
				}
			}
		}
		Variable[] rowValues = new Variable[] {
				new OctetString(SnmpConfig.getInstanse().getsnmpEmsName()),//厂商网管系统标识符
				new OctetString(SnmpConfig.getInstanse().getEmsUserlabel()),//厂商网管系统友好名称
				new OctetString(SnmpConfig.getInstanse().getEmsNativeName()),//本地名称
				new OctetString(SnmpConfig.getInstanse().getEmsType()),//厂商网管系统类型
				new OctetString(SnmpConfig.getInstanse().getEmsVersion()),//厂商网管系统北向接口版本
				new OctetString(SnmpConfig.getInstanse().getEmsLocation()),//厂商网管系统所在的地理位置
				new OctetString(SnmpConfig.getInstanse().getEmsRunningState().equals("1")?"Available":"Unavailable"),//厂商网管系统运行状态
				new OctetString(SnmpConfig.getInstanse().getEmsMaxSiteCount()),//厂商网管系统最大网元数
				new OctetString(ResourceUtil.srcStr(StringKeysLbl.LBL_JLABTL3_PTN).split(":")[1]),//厂商网管系统软件版本
				};
		
		addRow(new OID("1"), rowValues);
//		EMSConvertXml con = new EMSConvertXml();
//		con.getEMSXml();
	}

	@Override
	public void setTable(List<VariableBinding> vbList) {
		for (VariableBinding vb : vbList) {
			moTable.setValue(vb);
		}

	}

}

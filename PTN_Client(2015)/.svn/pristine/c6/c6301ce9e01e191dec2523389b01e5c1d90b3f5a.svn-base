package com.nms.ui.ptn.statistics.path;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.bean.ptn.qos.QosInfo;
import com.nms.db.bean.report.PathStatisticsWidthRate;
import com.nms.db.enums.EOperationLogType;
import com.nms.db.enums.EQosDirection;
import com.nms.db.enums.QosTemplateTypeEnum;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.ptn.path.tunnel.TunnelService_MB;
import com.nms.model.util.ExportExcel;
import com.nms.model.util.Services;
import com.nms.service.impl.util.ResultString;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTab;
import com.nms.ui.manager.keys.StringKeysTip;


/**
 * 导出服务路径统计的处理类
 * @author sy
 *
 */
public class PathStatisticsWidthController extends AbstractController {
	private PathStatisticsWidthPanel view;
	private  JTable table;
	private  List<PathStatisticsWidthRate> dataList = new ArrayList<PathStatisticsWidthRate>();

	public PathStatisticsWidthController(PathStatisticsWidthPanel pathStatisticsWidthPanel) {
		this.setView(pathStatisticsWidthPanel);
		this.table = this.view.getWidthRightPane().getTable();
		addListener();
	}
	//事件处理
	private void addListener() {
		this.view.getBtnBack().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 1; i < 19; i++) {
					setColumnWidth(table, i, 0);
				}
				for (int i = 1; i < 19; i++) {
					if (i < 3 || i >= 11) {
						setColumnWidth(table, i, 133);
					}
					if (i >= 3 && i < 11) {
						setColumnWidth(table, i, 0);
					}
				}
			}
		});

		this.view.getBtnForward().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 1; i < 19; i++) {
					setColumnWidth(table, i, 0);
				}
				for (int i = 1; i < 19; i++) {
					if (i < 11) {
						setColumnWidth(table, i, 133);
					}
					if (i >= 11) {
						setColumnWidth(table, i, 0);
					}
				}
			}
		});

		this.view.getBtnAll().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 1; i < 19; i++) {
					setColumnWidth(table, i, 0);
				}
				for (int i = 1; i < 19; i++) {
					setColumnWidth(table, i, 74);
				}
			}
		});

		this.view.getLeftPane().getBtnImport().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				try {
					dataList.clear();
					calculateWidth();
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}
		});

		this.view.getBtnExport().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					export();
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}
		});
	}

	/**
	 * 通过网元id查找对应的qos信息，计算带宽利用率
	 * 
	 * @param siteIds
	 * @throws Exception
	 */
	private void calculateWidth() throws Exception {

		List<Integer> siteIds = this.view.getLeftPane().getNeTreePanel().getPrimaryKeyList("site");
		if (siteIds.size() == 0) {
			DialogBoxUtil.succeedDialog(this.view, ResourceUtil.srcStr(StringKeysTip.TIP_CHOOSE_SITE));
		} else {

			TunnelService_MB tunnelService = null;
			Map<Integer, Tunnel> map = new HashMap<Integer, Tunnel>();
			PwInfoService_MB pwInfoService = null;
			QosInfo qosInfo = null;
			try {
				tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
				pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);

				// 将所有网元下的tunnel放入map中，过滤掉重复的值
				for (int i = 0; i < siteIds.size(); i++) {
					int siteId = siteIds.get(i);
					List<Tunnel> tunnelList = new ArrayList<Tunnel>();
					tunnelList=tunnelService.selectBySiteId(siteId);				
					for (Tunnel tunnel_select : tunnelList) {
						if (null == map.get(tunnel_select.getTunnelId())) {
							map.put(tunnel_select.getTunnelId(), tunnel_select);
						}
					}
				}
				// 遍历map，即遍历所有的tunnel
				Set<Map.Entry<Integer, Tunnel>> set = map.entrySet();
				for (Iterator<Map.Entry<Integer, Tunnel>> its = set.iterator(); its.hasNext();) {
					Map.Entry<Integer, Tunnel> entry = its.next();
					// 以tunnel为单位，统计该条tunnel下关联的所有pw的cir和eir，计算带宽
					int tunnelId = entry.getKey();
					List<PwInfo> pwInfoList = pwInfoService.selectByTunnelId(tunnelId);
					if( entry.getValue().getQosList()!=null&& entry.getValue().getQosList().size()>0){
						qosInfo = entry.getValue().getQosList().get(0);
					}
					

					if (QosTemplateTypeEnum.LLSP!=null&&qosInfo!=null&&QosTemplateTypeEnum.LLSP.toString().equals(qosInfo.getQosType())) {
						// 统计LLSP类型的带宽利用率
						caculateLLSP(entry.getValue(), pwInfoList);
					} else {
						// 统计ELSP类型的带宽利用率
						caculateELSP(entry.getValue(), pwInfoList);
					}
				}
				this.view.getWidthRightPane().clear();
				this.view.getWidthRightPane().initData(dataList);
				this.view.getWidthRightPane().updateUI();
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			} finally {
				UiUtil.closeService_MB(pwInfoService);
				UiUtil.closeService_MB(tunnelService);
			}
		}
	}

	/**
	 * 统计ELSP类型的带宽利用率
	 * 
	 * @param qosInfoList
	 * @param tunnelId
	 * @param pwIds
	 * @param tunnelName
	 */
	private void caculateELSP(Tunnel tunnel, List<PwInfo> pwInfoList) throws Exception {
		int cos = -1;
		int tunnel_forWard_cir = -1;
		int tunnel_forWard_eir = 0;
		int tunnel_back_cir = 0;
		int tunnel_back_eir = 0;
		int pw_forWard_cir = 0;
		int pw_forWard_eir = 0;
		int pw_back_cir = 0;
		int pw_back_eir = 0;
		Map<Integer, String> cirMap = new HashMap<Integer, String>();
		Map<Integer, String> eirMap = new HashMap<Integer, String>();

		try {
			if(tunnel.getQosList()!=null&&tunnel.getQosList().size()>0){
				for(QosInfo qosInfo : tunnel.getQosList()){
					cos = qosInfo.getCos();// 0,1,2,3,4,5,6,7中的一种
					if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.FORWARD.getValue()) {
						tunnel_forWard_cir = qosInfo.getCir();// 最低带宽
						tunnel_forWard_eir = qosInfo.getEir();// 额外带宽
					} else {
						tunnel_back_cir = qosInfo.getCir();// 最低带宽
						tunnel_back_eir = qosInfo.getEir();// 额外带宽
					}
					
					// 统计该条tunnel下的所有pw
					for(PwInfo pwInfo : pwInfoList){
						for(QosInfo qosInfo_pw : pwInfo.getQosList()){
							if(cos == qosInfo_pw.getCos()){
								if (Integer.parseInt(qosInfo_pw.getDirection()) == EQosDirection.FORWARD.getValue()) {
									pw_forWard_cir += qosInfo.getCir();// 最低带宽
									pw_forWard_eir += qosInfo.getEir();// 额外带宽
								} else {
									pw_back_cir += qosInfo.getCir();// 最低带宽
									pw_back_eir += qosInfo.getEir();// 额外带宽
								}
							}
						}
					}
					
					String rate_cir_forWard = caculateRate(pw_forWard_cir, tunnel_forWard_cir);
					String rate_cir_back = caculateRate(pw_back_cir, tunnel_back_cir);
					cirMap.put(cos, rate_cir_forWard + ":" + rate_cir_back);
					String rate_eir_forWard = caculateRate(pw_forWard_eir, tunnel_forWard_eir);
					String rate_eir_back = caculateRate(pw_back_eir, tunnel_back_eir);
					eirMap.put(cos, rate_eir_forWard + ":" + rate_eir_back);
					// 计算完毕之后，pw的cir和eir要归零
					pw_forWard_cir = 0;
					pw_forWard_eir = 0;
					pw_back_cir = 0;
					pw_back_eir = 0;
					
				}
			}
			
			
			if (tunnel_forWard_cir >= 0) {
				// 计算带宽
				// 名称 + qos类型 + 剩余率
				collectData(tunnel.getTunnelName(), cirMap, eirMap, "ELSP");
			}
			
		} catch (Exception e) {
			throw e;
		}
		
	}

	/**
	 * 统计LLSP类型的带宽利用率
	 * 
	 * @param qosInfoList
	 * @param tunnelId
	 * @param pwIds
	 * @param tunnelName
	 */
	private void caculateLLSP(Tunnel tunnel, List<PwInfo> pwInfoList) throws Exception {
		int cos = -1;
		int tunnel_forWard_cir = -1;
		int tunnel_forWard_eir = -1;
		int tunnel_back_cir = -1;
		int tunnel_back_eir = -1;
		int pw_forWard_cir = 0;
		int pw_forWard_eir = 0;
		int pw_back_cir = 0;
		int pw_back_eir = 0;
		Map<Integer, String> cirMap = new HashMap<Integer, String>();
		Map<Integer, String> eirMap = new HashMap<Integer, String>();

		try {
			// 计算tunnel带宽
			for (QosInfo qosInfo : tunnel.getQosList()) {
				cos=qosInfo.getCos();
				if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.FORWARD.getValue()) {
					tunnel_forWard_cir = qosInfo.getCir();// 最低带宽
					tunnel_forWard_eir = qosInfo.getEir();// 额外带宽
				} else {
					tunnel_back_cir = qosInfo.getCir();// 最低带宽
					tunnel_back_eir = qosInfo.getEir();// 额外带宽
				}
			}
			
			//计算pw带宽
			for(PwInfo pwInfo : pwInfoList){
				
				for(QosInfo qosInfo : pwInfo.getQosList()){
					if (Integer.parseInt(qosInfo.getDirection()) == EQosDirection.FORWARD.getValue()) {
						 pw_forWard_cir += qosInfo.getCir();// 最低带宽
						 pw_forWard_eir += qosInfo.getEir();// 额外带宽
					} else {
						 pw_back_cir += qosInfo.getCir();// 最低带宽
						 pw_back_eir += qosInfo.getEir();// 额外带宽
					}
				}
				
			}

			// 当qosInfo中没有tunnel信息时，不计算，有tunnel信息没有pw信息时，pw带宽为0，有tunnel信息和pw信息时，pw带宽大于等于0
			if (tunnel_forWard_cir != -1) {
				// 计算带宽
				// 名称 + qos类型 + 剩余率
				String rate_cir_forWard = caculateRate(pw_forWard_cir, tunnel_forWard_cir);
				String rate_cir_back = caculateRate(pw_back_cir, tunnel_back_cir);
				String rate_eir_forWard = caculateRate(pw_forWard_eir, tunnel_forWard_eir);
				String rate_eir_back = caculateRate(pw_back_eir, tunnel_back_eir);
				for (int i = 0; i < 8; i++) {
					if (cos == i) {
						cirMap.put(cos, rate_cir_forWard + ":" + rate_cir_back);
						eirMap.put(cos, rate_eir_forWard + ":" + rate_eir_back);
					} else {
						cirMap.put(i, "0/0 (0.0%)" + ":" + "0/0 (0.0%)");
						eirMap.put(i, "0/0 (0.0%)" + ":" + "0/0 (0.0%)");
					}

				}
				collectData(tunnel.getTunnelName(), cirMap, eirMap, "LLSP");
			}
		} catch (Exception e) {
			throw e;
		}


	}

	/**
	 * 获取数据，封装到对象中
	 * 
	 * @param tunnelName
	 * @param cirMap
	 * @param eirMap
	 * @param qosType
	 */
	private void collectData(String tunnelName, Map<Integer, String> cirMap, Map<Integer, String> eirMap, String qosType) {
		String name_Cir = "(Cir)" + tunnelName;
		String name_Eir = "(Eir)" + tunnelName;
		PathStatisticsWidthRate rate_Cir = new PathStatisticsWidthRate();
		PathStatisticsWidthRate rate_Eir = new PathStatisticsWidthRate();
		rate_Cir.setRateName(name_Cir);
		rate_Eir.setRateName(name_Eir);
		rate_Cir.setQosType(qosType);
		rate_Eir.setQosType(qosType);
		// cir
		rate_Cir.setForWard_BE(cirMap.get(0).split(":")[0]);
		rate_Cir.setBackWard_BE(cirMap.get(0).split(":")[1]);
		rate_Cir.setForWard_AF1(cirMap.get(1).split(":")[0]);
		rate_Cir.setBackWard_AF1(cirMap.get(1).split(":")[1]);
		rate_Cir.setForWard_AF2(cirMap.get(2).split(":")[0]);
		rate_Cir.setBackWard_AF2(cirMap.get(2).split(":")[1]);
		rate_Cir.setForWard_AF3(cirMap.get(3).split(":")[0]);
		rate_Cir.setBackWard_AF3(cirMap.get(3).split(":")[1]);
		rate_Cir.setForWard_AF4(cirMap.get(4).split(":")[0]);
		rate_Cir.setBackWard_AF4(cirMap.get(4).split(":")[1]);
		rate_Cir.setForWard_EF(cirMap.get(5).split(":")[0]);
		rate_Cir.setBackWard_EF(cirMap.get(5).split(":")[1]);
		rate_Cir.setForWard_CS6(cirMap.get(6).split(":")[0]);
		rate_Cir.setBackWard_CS6(cirMap.get(6).split(":")[1]);
		rate_Cir.setForWard_CS7(cirMap.get(7).split(":")[0]);
		rate_Cir.setBackWard_CS7(cirMap.get(7).split(":")[1]);
		// eir
		rate_Eir.setForWard_BE(eirMap.get(0).split(":")[0]);
		rate_Eir.setBackWard_BE(eirMap.get(0).split(":")[1]);
		rate_Eir.setForWard_AF1(eirMap.get(1).split(":")[0]);
		rate_Eir.setBackWard_AF1(eirMap.get(1).split(":")[1]);
		rate_Eir.setForWard_AF2(eirMap.get(2).split(":")[0]);
		rate_Eir.setBackWard_AF2(eirMap.get(2).split(":")[1]);
		rate_Eir.setForWard_AF3(eirMap.get(3).split(":")[0]);
		rate_Eir.setBackWard_AF3(eirMap.get(3).split(":")[1]);
		rate_Eir.setForWard_AF4(eirMap.get(4).split(":")[0]);
		rate_Eir.setBackWard_AF4(eirMap.get(4).split(":")[1]);
		rate_Eir.setForWard_EF(eirMap.get(5).split(":")[0]);
		rate_Eir.setBackWard_EF(eirMap.get(5).split(":")[1]);
		rate_Eir.setForWard_CS6(eirMap.get(6).split(":")[0]);
		rate_Eir.setBackWard_CS6(eirMap.get(6).split(":")[1]);
		rate_Eir.setForWard_CS7(eirMap.get(7).split(":")[0]);
		rate_Eir.setBackWard_CS7(eirMap.get(7).split(":")[1]);
		// 放入集合中
		dataList.add(rate_Cir);
		dataList.add(rate_Eir);
	}

	/**
	 * 计算剩余率
	 * 
	 * @param pw_ForWard_Cir
	 * @param tunnel_ForWard_Cir
	 * @return
	 */
	private String caculateRate(int pw_ForWard_Cir, int tunnel_ForWard_Cir) {
		double rate = 0.0d;
		String rate_temp = "";
		if (tunnel_ForWard_Cir > 0) {
			Format format = new DecimalFormat("0.00");
			rate_temp = format.format((double) (tunnel_ForWard_Cir - pw_ForWard_Cir) / tunnel_ForWard_Cir);
			rate = (Double.parseDouble(rate_temp)) * 100;
		} else {
			rate = 0.0d;
		}
		String rate_cir_forWard = (tunnel_ForWard_Cir - pw_ForWard_Cir) + "/" + tunnel_ForWard_Cir + " (" + rate + "%)";
		return rate_cir_forWard;
	}

	/**
	 * 设置列宽
	 * 
	 * @param table
	 * @param count
	 * @param width
	 */
	private void setColumnWidth(JTable table, int count, int width) {
		TableColumn tc = table.getTableHeader().getColumnModel().getColumn(count);
		if (width == 0) {
			tc.setPreferredWidth(width);
			tc.setMinWidth(width);
			tc.setMaxWidth(width);
		} else {
			tc.setPreferredWidth(width);
			tc.setMinWidth(width - 50);
			tc.setMaxWidth(width + 50);
		}
	}

	/**
	 * 导出统计数据保存到excel
	 */
	@Override
	public void export() throws Exception {
		String result;
		ExportExcel export=null;
		// 得到页面信息
		try {
			export=new ExportExcel();
			//得到bean的集合转为  String[]的List
			List<String[]> beanData=export.tranListString(this.dataList,"pathWidthTable");
			//导出页面的信息-Excel
			result=export.exportExcel(beanData, "pathWidthTable");
			//添加操作日志记录
			this.insertOpeLog(EOperationLogType.PATHWIDTH.getValue(),ResultString.CONFIG_SUCCESS, null, null);					
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			result=null;
			export=null;
		}
	}
	private void insertOpeLog(int operationType, String result, Object oldMac, Object newMac){
		AddOperateLog.insertOperLog(null, operationType, result, oldMac, newMac, 0,ResourceUtil.srcStr(StringKeysTab.TAB_PATHTNFOWIDTH),"");		
	}
	public PathStatisticsWidthPanel getView() {
		return view;
	}

	public void setView(PathStatisticsWidthPanel view) {
		this.view = view;
	}

	@Override
	public void refresh() throws Exception {
	}
}
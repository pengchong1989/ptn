package com.nms.ui.manager;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.system.OperationDataLog;
import com.nms.db.bean.system.OperationLog;
import com.nms.db.bean.system.code.Code;
import com.nms.db.bean.system.code.CodeGroup;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EActiveStatusUnLoad;
import com.nms.db.enums.EActiveStatusUnLoadType;
import com.nms.db.enums.ECesType;
import com.nms.db.enums.EClockModelType;
import com.nms.db.enums.EClockQLEnabelType;
import com.nms.db.enums.EClockQLType;
import com.nms.db.enums.ECycleType;
import com.nms.db.enums.EMonitorCycle;
import com.nms.db.enums.EObjectType;
import com.nms.db.enums.EOutClockSelectType;
import com.nms.db.enums.EPortLagMode;
import com.nms.db.enums.EPwType;
import com.nms.db.enums.EQosDirection;
import com.nms.db.enums.ERunStates;
import com.nms.db.enums.ESMClockType;
import com.nms.db.enums.ETdmLoopLineType;
import com.nms.db.enums.ETdmLoopType;
import com.nms.db.enums.OamTypeEnum;
import com.nms.db.enums.QosCosLevelEnum;
import com.nms.model.system.OperationLogService_MB;
import com.nms.model.system.code.CodeGroupService_MB;
import com.nms.model.system.code.CodeService_MB;
import com.nms.model.util.CodeConfigItem;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.model.util.Services;
import com.nms.service.impl.util.ResultString;
import com.nms.ui.frame.ViewDataObj;

public class UiUtil {

	private static Map<Integer, Code> codeMap = null;

	/**
	 * 弹出窗口显示在屏幕中间，获取距离屏幕边缘的宽度
	 * 
	 * @param width
	 *            弹出窗口的宽度
	 */
	public static int getWindowWidth(int width) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		if (width > screenSize.width)
			width = screenSize.width;
		return (screenSize.width - width) / 2;
	}

	/**
	 * 弹出窗口显示在屏幕中间，获取距离屏幕边缘的高度
	 * 
	 * @param height
	 *            弹出窗口的高度
	 */
	public static int getWindowHeight(int height) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		if (height > screenSize.height)
			height = screenSize.height;
		return (screenSize.height - height) / 2;
	}

	/**
	 * 获取code组的MAP 可通过code的标识获取codegroup对象
	 * 
	 * @return Map<String, CodeGroup> key是code组的标识 value是code组对象
	 * @throws Exception
	 */
	public static Map<String, CodeGroup> getCodeGroupMap() throws Exception {
		CodeGroupService_MB codeGroupService = null;
		List<CodeGroup> codeGroupList = null;
		try {
			if (null == ConstantUtil.codeGroupMap) {
				ConstantUtil.codeGroupMap = new HashMap<String, CodeGroup>();

				codeGroupService = (CodeGroupService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CodeGroup);

				codeGroupList = codeGroupService.select();
				if (codeGroupList != null) {
					for (CodeGroup codeGroup : codeGroupList) {
						ConstantUtil.codeGroupMap.put(codeGroup.getCodeIdentily(), codeGroup);
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, UiUtil.class);
		} finally {
			UiUtil.closeService_MB(codeGroupService);
		}

		return ConstantUtil.codeGroupMap;
	}

	/**
	 * 跟组code组的标识获得code组对象
	 * 
	 * @param identity
	 *            code组标识
	 * @return codeGroup对象
	 * @throws Exception
	 */
	public static CodeGroup getCodeGroupByIdentity(String identity) throws Exception {
		return UiUtil.getCodeGroupMap().get(identity);
	}

	/**
	 * 获取code的MAP 可通过code的ID获取code对象
	 * @return Map<Integer, CodeGroup> key是codeID value是code对象
	 * @throws Exception
	 */
	private static Map<Integer, Code> getCodeMap() throws Exception {
		CodeService_MB codeService = null;
		List<Code> codeList = null;
		try {
			if (null == UiUtil.codeMap) {
				UiUtil.codeMap = new HashMap<Integer, Code>();
				codeService = (CodeService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Code);
				codeList = codeService.selectAll();
				if (null != codeList) {
					for (Code code : codeList) {
						UiUtil.codeMap.put(code.getId(), code);
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, UiUtil.class);
		} finally {
			UiUtil.closeService_MB(codeService);
		}
		return UiUtil.codeMap;
	}

	/**
	 * 跟组code的ID获得code对象
	 * 
	 * @param id
	 *            codeId
	 * @return code对象
	 * @throws Exception
	 */
	public static Code getCodeById(int codeId) throws Exception {
		return UiUtil.getCodeMap().get(codeId);
	}

	/**
	 * 隐藏表的列和设置表中序号列宽
	 * 
	 * @param jTable
	 *            表
	 * @param count
	 *            标识多少个列需要隐藏
	 */
	public static void jTableColumsHide(JTable jTable, int count) {

		for (int i = 0; i < count; i++) {
			jTable.getColumnModel().getColumn(i).setMinWidth(0);
			jTable.getColumnModel().getColumn(i).setMaxWidth(0);
		}
		jTable.getColumnModel().getColumn(count).setMinWidth(40);
		jTable.getColumnModel().getColumn(count).setMaxWidth(40);

	}

	/**
	 * 根据code的值获取code对象
	 * 
	 * @param groupIdentity
	 *            所属code组标识
	 * @param codeValue
	 *            值
	 * @return
	 * @throws Exception
	 */
	public static Code getCodeByValue(String groupIdentity, String codeValue) throws Exception {

		CodeGroup codeGroup = null;
		List<Code> codeList = null;
		Code code_result = new Code();
		try {
			codeGroup = UiUtil.getCodeGroupByIdentity(groupIdentity);
			codeList = codeGroup.getCodeList();

			for (Code code : codeList) {
				if (code.getCodeValue().equals(codeValue)) {
					code_result = code;
					break;
				}
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e, UiUtil.class);
		} finally {
			codeGroup = null;
			codeList = null;
		}
		return code_result;
	}

	/**
	 * 弹出dialog
	 * 
	 * @author kk
	 * @param jdialog
	 *            dialog对象
	 * @param windth
	 *            宽
	 * @param height
	 *            高
	 */
	public static void showWindow(JDialog jdialog, int windth, int height) {
		jdialog.setModal(true);
		Dimension dimension = new Dimension(windth, height);
		jdialog.setSize(dimension);
		jdialog.setMinimumSize(dimension);
		jdialog.setLocation(UiUtil.getWindowWidth(jdialog.getWidth()), UiUtil.getWindowHeight(jdialog.getHeight()));
		jdialog.setVisible(true);
	}

	/**
	 * 通过端口名称，获取端口最大带宽
	 * 
	 * @author kk
	 * 
	 * @param portName
	 *            端口名称
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	public static int getMaxCir(String portName) throws Exception {
		if (null == portName || "".equals(portName)) {
			throw new Exception("portName is null");
		}

		String portName_short = portName.substring(0, portName.indexOf("."));
		if ("ge".equals(portName_short)) {
			return ConstantUtil.QOS_CIR_MAX_1G;
		} else if ("fe".equals(portName_short) || "fx".equals(portName_short)) {
			return ConstantUtil.QOS_CIR_MAX_100M;
		} else {
			return ConstantUtil.QOS_CIR_MAX_10G;
		}
	}

	/**
	 * 判断输入参数是否为空 -sy :::::::暂时只判断———— 对象，String,List(类型)
	 * 
	 * @param object
	 *            传人参数: 可能为 某种对象，String类型,List集合
	 * @return flag true :验证通过，此参数可以使用 false:验证没有通过
	 */
	public static boolean isNull(Object object) {
		boolean flag = false;
		// 当参数为对象时，只判断是否为空
		if (object != null) {
			// 当参数为String 类型时，还要判断 不为""
			if (object instanceof String) {
				if (!object.equals("")) {
					flag = true;
				}
			} else if (object instanceof List) {// list集合是否有数据
				if (((List) object).size() > 0) {
					flag = true;
				}

			} else {// 对象
				flag = true;
			}

		}
		return flag;
	}

	/**
	 * 设置frame的logo
	 * 
	 * @param frame
	 */
	public static void setLogo(JFrame frame) {
		int value = 0;
		String imagePath = null;
		try {
			CodeConfigItem codeConfigItem = CodeConfigItem.getInstance();
			value = Integer.parseInt(codeConfigItem.getValueByKey("IconImageShowOrHide"));

			switch (value) {
			case 1:
				imagePath = ConstantUtil.LOGOPATH;
				break;
			case 2:
				imagePath = ConstantUtil.LOGOPATHHIDE;
				break;
			case 3:
				imagePath = "/com/nms/ui/images/log_jiuJiang.png";
				break;
			case 4:
				imagePath = "/com/nms/ui/images/yixun_logo.jpg";
				break;
			case 5:
				imagePath = "/com/nms/ui/images/yixun_logo.jpg";
				break;
			case 6:
				imagePath = "/com/nms/ui/images/keda_logo.jpg";
				break;
			default:
				break;
			}
			frame.setIconImage(ImageIO.read(UiUtil.class.getResource(imagePath)));
		} catch (Exception e) {
			ExceptionManage.dispose(e, UiUtil.class);
		}

	}

	/**
	 * 获取厂家标识图片对象
	 * 
	 * @return
	 */
	public static Icon getIcon() {
		Icon icon = null;
		try {
			icon = new ImageIcon(UiUtil.class.getResource(UiUtil.getIconImagePath()));
		} catch (Exception e) {
			ExceptionManage.dispose(e, UiUtil.class);
		}
		return icon;
	}

	/**
	 * 获取厂家标识图片路径
	 * 
	 * @return
	 */
	public static String getIconImagePath() {
		String imagePath = null;
		int value = 0;
		try {
			CodeConfigItem codeConfigItem = CodeConfigItem.getInstance();
			value = Integer.parseInt(codeConfigItem.getValueByKey("IconImageShowOrHide"));

			switch (value) {
			case 1:
				imagePath = "zh_CN".equals(ResourceUtil.language) ? "/com/nms/ui/images/ptn_login_top.png" : "/com/nms/ui/images/ptn_login_top_en.png";
				break;
			case 2:
				imagePath = "/com/nms/ui/images/zt.png";
				break;
			case 3:
				imagePath = "/com/nms/ui/images/jiuJiang.png";
				break;
			case 4:
				imagePath = "zh_CN".equals(ResourceUtil.language) ? "/com/nms/ui/images/ptn_login_top_yixun.png" : "/com/nms/ui/images/ptn_login_top_yixun_en.png";
				break;
			case 5:
				imagePath = "zh_CN".equals(ResourceUtil.language) ? "/com/nms/ui/images/ptn_login_top_yixun.png" : "/com/nms/ui/images/ptn_login_top_yixun_en.png";
				break;
			case 6:
				imagePath = "zh_CN".equals(ResourceUtil.language) ? "/com/nms/ui/images/ptn_login_top_keda.png" : "/com/nms/ui/images/ptn_login_top_keda_en.png";
				break;
			default:
				imagePath = "/com/nms/ui/images/ptn_login_top_hide.png";
				break;
			}

			if ("en_US".equals(ResourceUtil.language)) {
				imagePath += "";
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e, UiUtil.class);
		}
		return imagePath;
	}

	public static void closeService_MB(ObjectService_Mybatis objectService){
		if(null!=objectService){
			try {
				objectService.close();
			} catch (Exception e) {
				ExceptionManage.dispose(e, UiUtil.class);
			}finally{
				objectService=null;
			}
		}
	}	
		
	/**
	 *判断文件夹中是否存在相同的文件
	 * 
	 * @param filepath
	 *            d:\我的文档\dd
	 * @return
	 */
	public boolean isExistAlikeFileName(String fileName) {

		boolean fals = false;
		try {
			if (fileName != null && !"".equals(fileName)) {
				String filePath = fileName.substring(0, fileName.lastIndexOf("\\"));
				File file = new File(filePath);
				for (String fileString : file.list()) {
					if (fileString.equals(fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.length()))) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return fals;
	}

	/**
	 * 获取所有的AC集合
	 * 
	 * @return
	 */
	public Set<Integer> getAcIdSets(String acStrings) {
		Set<Integer> acSets = new HashSet<Integer>();
		try {
			if (null != acStrings && !"".equals(acStrings)) {
				for (String acId : acStrings.split(",")) {
					acSets.add(Integer.parseInt(acId.trim()));
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return acSets;
	}

	/**
	 * 添加操作日志记录 OperateKey 操作类型
	 */
	public static void insertOperationLog(int OperateKey) {

		OperationLogService_MB operationService = null;
		OperationLog operationLog = null;
		int result = 2;// 默认是操作失败
		try {
			if (OperateKey > 0) {
				// 记录操作日志
				operationLog = new OperationLog();
				operationService = (OperationLogService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OPERATIONLOGSERVIECE);
				operationLog.setOverTime(DateUtil.getDate(DateUtil.FULLTIME));
				operationLog.setUser_id(ConstantUtil.user.getUser_Id());
				operationLog.setOperationType(OperateKey);
				/**
				 * 操作日志结果 int result= 1 成功 ，2 失败
				 * 
				 * @param text
				 */
				operationLog.setOperationResult(result);
				operationService.insertOperationLog(operationLog);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, UiUtil.class);
		} finally {
			operationLog = null;
			UiUtil.closeService_MB(operationService);
		}
	}

	/**
	 * 添加操作日志记录 OperateKey 操作类型
	 */
	public static void insertOperationLog(int OperateKey, String result) {

		OperationLogService_MB operationService = null;
		OperationLog operationLog = null;
		try {
			if (OperateKey > 0) {
				// 记录操作日志
				operationLog = new OperationLog();
				operationService = (OperationLogService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OPERATIONLOGSERVIECE);
				operationLog.setOverTime(DateUtil.getDate(DateUtil.FULLTIME));
				operationLog.setUser_id(ConstantUtil.user.getUser_Id());
				operationLog.setOperationType(OperateKey);
				/**
				 * 操作日志结果 int result= 1 成功 ，2 失败
				 * 
				 * @param text
				 */
				if (null != result && !result.equals("") && result.contains(ResultString.CONFIG_SUCCESS)) {
					operationLog.setOperationResult(1);
				} else {
					operationLog.setOperationResult(2);
				}
				operationService.insertOperationLog(operationLog);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, UiUtil.class);
		} finally {
			operationLog = null;
			UiUtil.closeService_MB(operationService);
		}
	}

	/**
	 * 传入操作前和操作后的参数，将每个属性的值进行对比，不一样则记录下来
	 * @param fileName 
	 * @param before_Object　操作前的对象
	 * @param after_Object　操作后的对象
	 * @return　返回一个对象的所有属性的集合
	 */
	@SuppressWarnings("unchecked")
	public static List<OperationDataLog> compare(Object before_Object, Object after_Object, List<OperationDataLog> logList, String fileName) {
		try {
			List<Field> srcFieldList = new ArrayList<Field>();
			List<Field> destFieldList = new ArrayList<Field>();
			getFieldList(before_Object, srcFieldList);
			getFieldList(after_Object, destFieldList);

			Map<String, Object> srcMap = new HashMap<String, Object>();
			for (Field fd : srcFieldList) {
				fd.setAccessible(true);// 取消访问权限检查
				srcMap.put(fd.getName(), fd.get(before_Object)); // 获取属性值
			}
			Map<String, Object> destMap = new HashMap<String, Object>();
			for (Field fd : destFieldList) {
				fd.setAccessible(true);// 取消访问权限检查
				destMap.put(fd.getName(), fd.get(after_Object)); // 获取属性值
			}
			if(destFieldList.size() == 0){
				destFieldList.addAll(srcFieldList);
			}
			for (Field fd : destFieldList) {
				try {
					fd.setAccessible(true);// 取消访问权限检查
					if(!fd.getName().equals("serialVersionUID")){
						if (fd.getType() == int.class || fd.getType() == Integer.class ||fd.getType() == String.class || fd.getType() == long.class || fd.getType() == boolean.class
								|| fd.getType() == short.class || fd.getType() == float.class || fd.getType() == double.class || fd.getType() == char.class || 
								fd.getType() == byte.class) {
							nameValuemap = getNameValueMap(fileName);
							if(nameValuemap.containsKey(fd.getName())){
								OperationDataLog log = new OperationDataLog();
								log.setFieldNameId(fd.getName());
								if(log.getFieldNameId().equals(fd.get(after_Object == null?before_Object:after_Object))){
									log.setValue_before(log.getFieldNameId());
									log.setValue_after(log.getFieldNameId());
									logList.add(log);
								}else{
									log.setValue_before(srcMap.get(fd.getName())==null?"":srcMap.get(fd.getName()).toString());
									log.setValue_after(destMap.get(fd.getName())==null?"":destMap.get(fd.getName()).toString());
									if(log.getFieldNameId().contains("siteName") || log.getFieldNameId().contains("sitename") 
											|| log.getFieldNameId().contains("acName4vpls")||log.getFieldNameId().equals("cos")||log.getFieldNameId().contains("tmdtype")||
											log.getFieldNameId().contains("tdmLegId")||log.getFieldNameId().equals("clockPortName")||log.getFieldNameId().equals("macAdd")||
											log.getFieldNameId().contains("topuPortName")||log.getFieldNameId().contains("topuSiteName")||log.getFieldNameId().contains("netWorkName")||
											log.getFieldNameId().contains("parentName")|| log.getFieldNameId().contains("switchStatus")||log.getFieldNameId().contains("unloadType")){
										logList.add(log);
									}else if(log.getFieldNameId().contains("rootName") || log.getFieldNameId().contains("branchName")
											|| log.getFieldNameId().contains("acName4vpls") || log.getFieldNameId().contains("pwName4vpls")
											|| log.getFieldNameId().contains("qinQVlanLimit") || log.getFieldNameId().contains("cosName")
											|| log.getFieldNameId().contains("cosValue") || log.getFieldNameId().contains("uniBuffer")){
										if(!log.getValue_before().equals("") || !log.getValue_after().equals("")){
											logList.add(log);
										}
									}
									else{
										if(!log.getValue_before().equals(log.getValue_after())){
											logList.add(log);
										}
									}
								}
							}
						} else {
							if(fd.getType() == List.class){
								List<Object> objList1 = null;
								if(srcMap.get(fd.getName()) != null && ((List<Object>) srcMap.get(fd.getName())).size() > 0){
									objList1 = (List<Object>) srcMap.get(fd.getName());
								}
								List<Object> objList2 = null;
								if(destMap.get(fd.getName()) != null && ((List<Object>) destMap.get(fd.getName())).size() > 0){
									objList2 = (List<Object>) destMap.get(fd.getName());
								}
								if(objList1 != null && objList2 != null){
									if(objList1.size() <= objList2.size()){
										for (int i = 0; i < objList2.size(); i++) {
											if(checkObjType(objList2.get(i))){
												if(i <= (objList1.size()-1)){
													compare(objList1.get(i), objList2.get(i), logList, fileName);
												}else{
													compare(null, objList2.get(i), logList, fileName);
												}
											}
										}
									}else{
										for (int i = 0; i < objList1.size(); i++) {
											if(checkObjType(objList1.get(i))){
												if(i <= (objList2.size()-1)){
													compare(objList1.get(i), objList2.get(i), logList, fileName);
												}else{
													compare(objList1.get(i), null, logList, fileName);
												}
											}
										}
									}
								}else{
									if(objList2 != null){
										for (int i = 0; i < objList2.size(); i++) {
											if(checkObjType(objList2.get(i))){
												compare(null, objList2.get(i), logList, fileName);
											}
										}
									}else if(objList1 != null){
										for (int i = 0; i < objList1.size(); i++) {
											if(checkObjType(objList1.get(i))){
												compare(objList1.get(i), null, logList, fileName);
											}
										}
									}
								}
							}else if(fd.getType() == Map.class){
								Map map1 = null;
								if(srcMap.get(fd.getName()) != null && ((Map) srcMap.get(fd.getName())).size() > 0){
									map1 = (Map) srcMap.get(fd.getName());
								}
								Map map2 = null;
								if(destMap.get(fd.getName()) != null && ((Map) destMap.get(fd.getName())).size() > 0){
									map2 = (Map) destMap.get(fd.getName());
								}
								if(map1 != null && map2 != null){
									for (Object key : map1.keySet()) {
										Object obj4 = map1.get(key);
										Object obj5 = map2.get(key);
										if(obj5 != null){
											if(obj5 instanceof List){
												for (int i = 0; i < ((List)obj5).size(); i++) {
													compare(((List)obj4).get(i), ((List)obj5).get(i), logList, fileName);
												}
											}else if(checkObjType(obj5)){
												compare(obj4, obj5, logList, fileName);
											}
										}
									}
								}else{
									if(map2 != null){
										for (Object key : map2.keySet()) {
											Object obj5 = map2.get(key);
											if(obj5 != null){
												if(obj5 instanceof List){
													for (Object obj6 : (List)obj5) {
														compare(null, obj6, logList, fileName);
													}
												}else if(checkObjType(obj5)){
													compare(null, obj5, logList, fileName);
												}
											}
										}
									}else if(map1 != null){
										for (Object key : map1.keySet()) {
											Object obj4 = map1.get(key);
											if(obj4 != null){
												if(obj4 instanceof List){
													for (int i = 0; i < ((List)obj4).size(); i++) {
														Object obj6 = ((List)obj4).get(i);
														compare(obj6, null, logList, fileName);
													}
												}else if(checkObjType(obj4)){
													compare(obj4, null, logList, fileName);
												}
											}
										}
									}	
								}
							}else if(fd.getType() == OamTypeEnum.class){
								OamTypeEnum oamType1 = null;
								OamTypeEnum oamType2 = null;
								if(srcMap.get(fd.getName()) != null){
									oamType1 = (OamTypeEnum) srcMap.get(fd.getName());
								}
								if(destMap.get(fd.getName()) != null){
									oamType2 = (OamTypeEnum) destMap.get(fd.getName());
								}
								if(oamType1 != null || oamType2 != null){
									OperationDataLog log = new OperationDataLog();
									log.setFieldNameId(fd.getName());
									log.setValue_before(oamType1==null?"":oamType1.getValue()+"");
									log.setValue_after(oamType2==null?"":oamType2.getValue()+"");
									if(!log.getValue_before().equals(log.getValue_after())){
										logList.add(log);
									}
								}
							}else if(fd.getType() == EPwType.class){
								EPwType pwType1 = null;
								EPwType pwType2 = null;
								if(srcMap.get(fd.getName()) != null){
									pwType1 = (EPwType) srcMap.get(fd.getName());
								}
								if(destMap.get(fd.getName()) != null){
									pwType2 = (EPwType) destMap.get(fd.getName());
								}
								if(pwType1 != null || pwType2 != null){
									OperationDataLog log = new OperationDataLog();
									log.setFieldNameId(fd.getName());
									log.setValue_before(pwType1==null?"":pwType1.getValue()+"");
									log.setValue_after(pwType2==null?"":pwType2.getValue()+"");
									if(!log.getValue_before().equals(log.getValue_after())){
										logList.add(log);
									}
								}
							}else if(fd.getType() == EClockQLType.class){
								EClockQLType pwType1 = null;
								EClockQLType pwType2 = null;
								if(srcMap.get(fd.getName()) != null){
									pwType1 = (EClockQLType) srcMap.get(fd.getName());
								}
								if(destMap.get(fd.getName()) != null){
									pwType2 = (EClockQLType) destMap.get(fd.getName());
								}
								if(pwType1 != null || pwType2 != null){
									OperationDataLog log = new OperationDataLog();
									log.setFieldNameId(fd.getName());
									log.setValue_before(pwType1==null?"":pwType1.getValue()+"");
									log.setValue_after(pwType2==null?"":pwType2.getValue()+"");
									if(!log.getValue_before().equals(log.getValue_after())){
										logList.add(log);
									}
								}	
							}else if(fd.getType() == ETdmLoopType.class){
								ETdmLoopType pwType1 = null;
								ETdmLoopType pwType2 = null;
								if(srcMap.get(fd.getName()) != null){
									pwType1 = (ETdmLoopType) srcMap.get(fd.getName());
								}
								if(destMap.get(fd.getName()) != null){
									pwType2 = (ETdmLoopType) destMap.get(fd.getName());
								}
								if(pwType1 != null || pwType2 != null){
									OperationDataLog log = new OperationDataLog();
									log.setFieldNameId(fd.getName());
									log.setValue_before(pwType1==null?"":pwType1.getValue()+"");
									log.setValue_after(pwType2==null?"":pwType2.getValue()+"");
									if(!log.getValue_before().equals(log.getValue_after())){
										logList.add(log);
									}
								}	
							}else if(fd.getType() == ETdmLoopLineType.class){
								ETdmLoopLineType pwType1 = null;
								ETdmLoopLineType pwType2 = null;
								if(srcMap.get(fd.getName()) != null){
									pwType1 = (ETdmLoopLineType) srcMap.get(fd.getName());
								}
								if(destMap.get(fd.getName()) != null){
									pwType2 = (ETdmLoopLineType) destMap.get(fd.getName());
								}
								if(pwType1 != null || pwType2 != null){
									OperationDataLog log = new OperationDataLog();
									log.setFieldNameId(fd.getName());
									log.setValue_before(pwType1==null?"":pwType1.getValue()+"");
									log.setValue_after(pwType2==null?"":pwType2.getValue()+"");
									if(!log.getValue_before().equals(log.getValue_after())){
										logList.add(log);
									}
								}	
							}else if(fd.getType() == EObjectType.class){
								EObjectType object1 = null;
								EObjectType object2 = null;
								if(srcMap.get(fd.getName()) != null){
									object1 = (EObjectType) srcMap.get(fd.getName());
								}
								if(destMap.get(fd.getName()) != null){
									object2 = (EObjectType) destMap.get(fd.getName());
								}
								if(object1 != null || object2 != null){
									OperationDataLog log = new OperationDataLog();
									log.setFieldNameId(fd.getName());
									log.setValue_before(object1==null?"":object1.getValue()+"");
									log.setValue_after(object2==null?"":object2.getValue()+"");
									if(!log.getValue_before().equals(log.getValue_after())){
										logList.add(log);
									}
								}	
							}else if(fd.getType() == EMonitorCycle.class){
								EMonitorCycle object1 = null;
								EMonitorCycle object2 = null;
								if(srcMap.get(fd.getName()) != null){
									object1 = (EMonitorCycle) srcMap.get(fd.getName());
								}
								if(destMap.get(fd.getName()) != null){
									object2 = (EMonitorCycle) destMap.get(fd.getName());
								}
								if(object1 != null || object2 != null){
									OperationDataLog log = new OperationDataLog();
									log.setFieldNameId(fd.getName());
									log.setValue_before(object1==null?"":object1.getValue()+"");
									log.setValue_after(object2==null?"":object2.getValue()+"");
									if(!log.getValue_before().equals(log.getValue_after())){
										logList.add(log);
									}
								}	
							}else if(fd.getType() == ERunStates.class){
								ERunStates object1 = null;
								ERunStates object2 = null;
								if(srcMap.get(fd.getName()) != null){
									object1 = (ERunStates) srcMap.get(fd.getName());
								}
								if(destMap.get(fd.getName()) != null){
									object2 = (ERunStates) destMap.get(fd.getName());
								}
								if(object1 != null || object2 != null){
									OperationDataLog log = new OperationDataLog();
									log.setFieldNameId(fd.getName());
									log.setValue_before(object1==null?"":object1.getValue()+"");
									log.setValue_after(object2==null?"":object2.getValue()+"");
									if(!log.getValue_before().equals(log.getValue_after())){
										logList.add(log);
									}
								}	
							}else if(fd.getType() != SiteInst.class){
								compare(srcMap.get(fd.getName()), destMap.get(fd.getName()), logList, fileName);
							}else if(fd.getType() == EActiveStatusUnLoad.class){
								EActiveStatusUnLoad object1 = null;
								EActiveStatusUnLoad object2 = null;
								if(srcMap.get(fd.getName()) != null){
									object1 = (EActiveStatusUnLoad) srcMap.get(fd.getName());
								}
								if(destMap.get(fd.getName()) != null){
									object2 = (EActiveStatusUnLoad) destMap.get(fd.getName());
								}
								if(object1 != null || object2 != null){
									OperationDataLog log = new OperationDataLog();
									log.setFieldNameId(fd.getName());
									log.setValue_before(object1==null?"":object1.getValue()+"");
									log.setValue_after(object2==null?"":object2.getValue()+"");
									if(!log.getValue_before().equals(log.getValue_after())){
										logList.add(log);
									}
								}								
							
							}else if(fd.getType() == EActiveStatusUnLoadType.class){
								EActiveStatusUnLoadType object1 = null;
								EActiveStatusUnLoadType object2 = null;
								if(srcMap.get(fd.getName()) != null){
									object1 = (EActiveStatusUnLoadType) srcMap.get(fd.getName());
								}
								if(destMap.get(fd.getName()) != null){
									object2 = (EActiveStatusUnLoadType) destMap.get(fd.getName());
								}
								if(object1 != null || object2 != null){
									OperationDataLog log = new OperationDataLog();
									log.setFieldNameId(fd.getName());
									log.setValue_before(object1==null?"":object1.getValue()+"");
									log.setValue_after(object2==null?"":object2.getValue()+"");
									if(!log.getValue_before().equals(log.getValue_after())){
										logList.add(log);
									}
								}								
							
							}
						}
					}
				} catch (Exception e) {
					ExceptionManage.dispose(e, UiUtil.class);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, UiUtil.class);
		}
		return logList;
	}
	
	/**
	 * 验证对象是否是基本类型之一，如果是返回false，不是返回true
	 * @param obj
	 * @return
	 */
	private static boolean checkObjType(Object obj){
		if(!(obj instanceof String) && !(obj instanceof Integer) && !(obj instanceof Short) && !(obj instanceof Long) 
				&& !(obj instanceof Float) && !(obj instanceof Double) && !(obj instanceof Character) && !(obj instanceof Byte)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 获取一个对象的自身以及父类的所有Field
	 * @param before_Object
	 * @param srcFieldList
	 */
	private static void getFieldList(Object before_Object, List<Field> srcFieldList) {
		if(before_Object != null){
			if(!before_Object.getClass().getSuperclass().equals(Object.class)
					&& !before_Object.getClass().getSuperclass().equals(ViewDataObj.class)){  
				Field[] srcFields = before_Object.getClass().getSuperclass().getDeclaredFields();
				for (Field field : srcFields) {
					srcFieldList.add(field);
				}
			}
			Field[] srcFields = before_Object.getClass().getDeclaredFields();
			for (Field field : srcFields) {
				srcFieldList.add(field);
			}
		}
	}

	private static Map<String, String> nameValuemap = null;
	private static String tableName = null;
	/**
	 * 操作日志界面显示操作记录时，需要根据属性名获取属性值
	 * @param log
	 */
	public static void convertOperationLog(OperationDataLog log, String fileName) {
		try {
			nameValuemap = getNameValueMap(fileName);
			String logValue = nameValuemap.get(log.getFieldNameId());
			if(logValue != null){
				if(logValue.contains(",")){
					String[] arr = logValue.split(",");
					log.setFieldNameId(getValue(arr[0]));
					log.setValue_before(convertType(arr[1], arr[2], log.getValue_before()));
					log.setValue_after(convertType(arr[1], arr[2], log.getValue_after()));
				}else{
					log.setFieldNameId(getValue(logValue));
				}
			}else{
				log.setFieldNameId(null);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, UiUtil.class);
		}
	}
	
	private static Map<String, String> getNameValueMap(String fileName) throws IOException {
		if(!fileName.equals(tableName)){
			nameValuemap = null;
		}
		tableName = fileName;
		if (null == nameValuemap) {
			nameValuemap = new HashMap<String, String>();
			String baseName = "config/operationlog/"+fileName+".properties"; // 根据所选语言,前缀以及后缀生成文件名
			InputStream is = ResourceUtil.class.getClassLoader().getResourceAsStream(baseName); // 生成文件输入流
			PropertyResourceBundle pr = new PropertyResourceBundle(is); // 根据输入流构造PropertyResourceBundle的实例
			Set<String> set = pr.keySet();
			for (String key : set) {
				String value = pr.getString(key);
				nameValuemap.put(key, new String(value.getBytes("ISO-8859-1"), "GB2312"));
			}
			is.close();
		}
		return nameValuemap;
	}

	/**
	 * 根据值转换类型，如：根据cod标志转换成对应的名称，根据value转换成对应的枚举
	 * @param type 类型：Code/Enum
	 * @param typeValue: 具体的code值，具体的枚举类(如EpwType)
	 *         如果typeValue是code值，有的存的code主键id，有的存的是code的value，需要在取值的做区分
	 * @param value:具体的属性值
	 * @return
	 * @throws Exception
	 */
	private static String convertType(String type, String typeValue, String value) throws Exception{
		if(value != null && value.length() > 0){
			if(type.equals("Enum")){
				if(typeValue.equals("EPwType")){
					return EPwType.forms(Integer.parseInt(value)).toString();
				}else if(typeValue.equals("OamTypeEnum")){
					return OamTypeEnum.from(Integer.parseInt(value)).toString();
				}else if(typeValue.equals("EActiveStatus")){
					return EActiveStatus.forms(Integer.parseInt(value)).toString();
				}else if(typeValue.equals("EActiveStatusUnLoad")){
					return EActiveStatusUnLoad.forms(Integer.parseInt(value)).toString();
				}else if(typeValue.equals("EActiveStatusUnLoadType")){
					return EActiveStatusUnLoadType.forms(Integer.parseInt(value)).toString();
				}else if(typeValue.equals("QosCosLevelEnum")){
					return QosCosLevelEnum.from(Integer.parseInt(value)).toString();
				}else if(typeValue.equals("EQosDirection")){
					return EQosDirection.from(Integer.parseInt(value)).toString();
				}else if(typeValue.equals("ECesType")){
					return ECesType.forms(Integer.parseInt(value)).toString();
				}else if(typeValue.equals("ETdmLoopType")){
					return ETdmLoopType.forms(Integer.parseInt(value)).toString();
				}else if(typeValue.equals("ETdmLoopLineType")){
					return ETdmLoopLineType.forms(Integer.parseInt(value)).toString();
				}else if(typeValue.equals("EClockQLType")){
					return EClockQLType.forms(Integer.parseInt(value)).toString();
				}else if(typeValue.equals("EClockModelType")){
					return EClockModelType.forms(Integer.parseInt(value)).toString();
				}else if(typeValue.equals("ESMClockType")){
					return ESMClockType.forms(Integer.parseInt(value)).toString();
				}else if(typeValue.equals("EClockQLEnabelType")){
					return EClockQLEnabelType.forms(Integer.parseInt(value)).toString();
				}else if(typeValue.equals("EOutClockSelectType")){
					return EOutClockSelectType.forms(Integer.parseInt(value)).toString();
				}else if(typeValue.equals("ECycleType")){
					return ECycleType.forms(Integer.parseInt(value)).toString();
				}else if(typeValue.equals("EPortLagMode")){
					return EPortLagMode.forms(Integer.parseInt(value)).toString();
				}
			}else if(type.equals("Code")){
				return getValueByCode(typeValue, value);
			}
			return value;
		}
		return value;
		
	}
	
	/**
	 * 有的存的code主键id，有的存的是code的value，需要在取值的做区分
	 * @param identity BUSINESSTYPE_value(根据value取值),PAYLOAD_id(根据主键id取值)
	 * @param value
	 * @return
	 * @throws Exception
	 */
	private static String getValueByCode(String identity, String value) throws Exception {
		try {
			//第一个元素是标识，第二个是值(主键id或者是codeValue)
			String[] arr = identity.split("\\|");
			CodeGroup codeGroup = UiUtil.getCodeGroupByIdentity(arr[0]);
			List<Code> codeList = codeGroup.getCodeList();
			for (Code code : codeList) {
				if(arr[1].equals("value")){
					if(value.equals(code.getCodeValue())){
						return code.getCodeName();
					}
				}else if(arr[1].equals("id")){
					if(value.equals(code.getId()+"")){
						return code.getCodeName();
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, UiUtil.class);
		}
		return value;
	}
	
	private static Map<String, String> map1 = null;
	
	/**
	 * 根据key获取中英文词条
	 * @param disStr
	 * @return
	 */
	private static String getValue(String disStr) {
		String result = null;
		try {
			if (null == map1) {
				map1 = new HashMap<String, String>();
				String baseName = "config/ATTR_"+ResourceUtil.language+".properties"; // 根据所选语言,前缀以及后缀生成文件名
				InputStream is = ResourceUtil.class.getClassLoader().getResourceAsStream(baseName); // 生成文件输入流
				PropertyResourceBundle pr = new PropertyResourceBundle(is); // 根据输入流构造PropertyResourceBundle的实例
				Set<String> set = pr.keySet();
				for (String key : set) {
					String value = pr.getString(key);
					map1.put(key, new String(value.getBytes("ISO-8859-1"), "GB2312"));
				}
				is.close();
			}
			result = map1.get(disStr);
		} catch (Exception e) {
			ExceptionManage.dispose(e, UiUtil.class);
			return disStr;
		}
		if(result == null){
			result = disStr;
		}
		return result;
	}
}

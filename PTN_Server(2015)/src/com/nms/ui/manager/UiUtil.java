package com.nms.ui.manager;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;

import com.nms.db.bean.system.code.Code;
import com.nms.db.bean.system.code.CodeGroup;
import com.nms.model.system.code.CodeGroupService_MB;
import com.nms.model.system.code.CodeService_MB;
import com.nms.model.util.CodeConfigItem;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.model.util.Services;


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
				if (codeGroupList != null && codeGroupList.size()!=0) {
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
	 * 
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
				codeList = codeService.select();

				if (null != codeList && codeList.size()!=0) {
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
	 * 判断输入参数是否为空 -sy
	 * 			:::::::暂时只判断———— 对象，String,List(类型)
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
	 *判断文件夹中是否存在相同的文件 
	 * @param filepath d:\我的文档\dd
	 * @return
	 */
	public boolean isExistAlikeFileName(String fileName){
		
		boolean fals = false;
		try {
			if(fileName != null && !"".equals(fileName)){
				String filePath = fileName.substring(0, fileName.lastIndexOf("\\"));
				File file = new File(filePath);
				for(String fileString :file.list()){
					if(fileString.equals(fileName.substring(fileName.lastIndexOf("\\")+1,fileName.length()))){
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
	 * @return
	 */
	public Set<Integer> getAcIdSets(String acStrings)
	{
		Set<Integer> acSets = new HashSet<Integer>();
		try 
		{
			if(null != acStrings && !"".equals(acStrings))
			{
				for(String acId : acStrings.split(","))
				{
					acSets.add(Integer.parseInt(acId.trim())); 
				}
			}
		} catch (Exception e) 
		{
			ExceptionManage.dispose(e, getClass());
		}
		return acSets;
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
	
}

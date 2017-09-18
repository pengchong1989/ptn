package com.nms.drive.analysis.datablock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.analysis.xmltool.bean.ListRoot;
import com.nms.drive.service.bean.TunnelProtection;
/**
 * 解析 LSP保护
 * @author 罗磊
 *
 */
public class AnalysisLSPProtectionTable extends AnalysisBase{
	private int dataCount = 20;// 每个数据块的长度
	private int NEhead = 5;// NE头长度
	private int controlPanelHead = 101;// 盘头长度
	private int dataBlockHead = 25;// 配置块头信息长度
	private int clauses = 2;// 条目数长度
	
	/**
	 * 根据LSP保护对象生成字节数组
	 * @param LSPProtectionObject 对象
	 * @param configXml 配置XML
	 * @return 命令
	 * @throws Exception
	 */
	public byte[] analysisObjectToCommand(List<TunnelProtection> lspProtectionObject,String configXml)throws Exception{
		try {
			DriveRoot driveRoot_config = super.LoadDriveXml(configXml);
			String file = driveRoot_config.getDriveAttributeList().get(0).getListRootList().get(0).getFile();
			//清除自带的listroot
			driveRoot_config.getDriveAttributeList().get(0).getListRootList().clear();
			//设置条目数
			driveRoot_config.getDriveAttributeList().get(0).setValue(lspProtectionObject.size()+"");
		
			//按现条目数循环
			for(int i = 0; i < lspProtectionObject.size(); i++){
				DriveRoot driveRoot_config_1 = super.LoadDriveXml(file);
				ListRoot listRoot = new ListRoot();
				listRoot.setDriveAttributeList(driveRoot_config_1.getDriveAttributeList());
				
				for(int j = 0; j < driveRoot_config_1.getDriveAttributeList().size(); j++){
					DriveAttribute driveAttribute = driveRoot_config_1.getDriveAttributeList().get(j);
					//属性 赋值
					if("保护类型".equals(driveAttribute.getDescription())){
						driveAttribute.setValue(lspProtectionObject.get(i).getProtectionType()+"");
					}
					
					if("主用槽位".equals(driveAttribute.getDescription())){
						driveAttribute.setValue(lspProtectionObject.get(i).getMainSlot()+"");
					}
					
					if("主用端口".equals(driveAttribute.getDescription())){
						driveAttribute.setValue(lspProtectionObject.get(i).getMainPort()+"");
					}
					
					if("主用LSP ID".equals(driveAttribute.getDescription())){
						driveAttribute.setValue(lspProtectionObject.get(i).getMainLspID()+"");
					}
					
					if("备用槽位".equals(driveAttribute.getDescription())){
						driveAttribute.setValue(lspProtectionObject.get(i).getStandbySlot()+"");
					}
					
					if("备用端口".equals(driveAttribute.getDescription())){
						driveAttribute.setValue(lspProtectionObject.get(i).getStandbyPort()+"");
					}
					
					if("备用LSP ID".equals(driveAttribute.getDescription())){
						driveAttribute.setValue(lspProtectionObject.get(i).getStandbyLspID()+"");
					}
					
					if("拖延时间".equals(driveAttribute.getDescription())){
						driveAttribute.setValue(lspProtectionObject.get(i).getProtractedTime()+"");
					}
					
					if("返回类型".equals(driveAttribute.getDescription())){
						driveAttribute.setValue(lspProtectionObject.get(i).getReturnType()+"");
					}
					if("LSP逻辑ID".equals(driveAttribute.getDescription())){
						driveAttribute.setValue(lspProtectionObject.get(i).getLspLogicId()+"");
					}
					
					if("业务类型".equals(driveAttribute.getDescription())){
						driveAttribute.setValue(lspProtectionObject.get(i).getOperationType()+"");
					}
					if("等待恢复时间".equals(driveAttribute.getDescription())){
						driveAttribute.setValue(lspProtectionObject.get(i).getWaittime()+"");
					}
				}
				driveRoot_config.getDriveAttributeList().get(0).getListRootList().add(listRoot);
			}
			AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
			byte[] dataCommand = analysisDriveXmlToCommand.analysisCommands(driveRoot_config);
//			CoderUtils.print16String(dataCommand);
			return dataCommand;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 根据字节数组转换生成LSP保护对象
	 * @param commands 命令
	 * @param configXml 配置XML
	 * @return LSP Protection
	 * @throws Exception
	 */
	public List<TunnelProtection> analysisCommandToObject(byte[] commands,String configXml)throws Exception{
		List<TunnelProtection> LSPProtectionList = new ArrayList<TunnelProtection>();
		DriveRoot driveRoot_config = super.LoadDriveXml(configXml);
		String file = driveRoot_config.getDriveAttributeList().get(0).getListRootList().get(0).getFile();
		// 起始长度
		int start = clauses;
		// 条目数
		int count = (commands.length) / dataCount;
		try{
			for(int i = 0; i<count; i++){
				byte[] a = new byte[dataCount];
				System.arraycopy(commands, start+i*dataCount, a, 0, a.length);
				AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
				analysisCommandByDriveXml.setCommands(a);
				
				DriveRoot driveRoot =analysisCommandByDriveXml.analysisDriveAttrbute(file);
				TunnelProtection LSPObject = new TunnelProtection();
				//driveRoot赋值 LSPObject 对象中
				for(int j=0; j<driveRoot.getDriveAttributeList().size(); j++){
					DriveAttribute driveAttribute = driveRoot.getDriveAttributeList().get(j);
					//属性赋值
					if("保护类型".equals(driveAttribute.getDescription())){
						LSPObject.setProtectionType(Integer.parseInt(driveAttribute.getValue()));
					}
					if("主用槽位".equals(driveAttribute.getDescription())){
						LSPObject.setMainSlot(Integer.parseInt(driveAttribute.getValue()));
					}
					if("主用端口".equals(driveAttribute.getDescription())){
						LSPObject.setMainPort(Integer.parseInt(driveAttribute.getValue()));
					}
					if("主用LSP ID".equals(driveAttribute.getDescription())){
						LSPObject.setMainLspID(Integer.parseInt(driveAttribute.getValue()));
					}
					if("备用槽位".equals(driveAttribute.getDescription())){
						LSPObject.setStandbySlot(Integer.parseInt(driveAttribute.getValue()));
					}
					if("备用端口".equals(driveAttribute.getDescription())){
						LSPObject.setStandbyPort(Integer.parseInt(driveAttribute.getValue()));
					}
					if("备用LSP ID".equals(driveAttribute.getDescription())){
						LSPObject.setStandbyLspID(Integer.parseInt(driveAttribute.getValue()));
					}
					if("拖延时间".equals(driveAttribute.getDescription())){
						LSPObject.setProtractedTime(Integer.parseInt(driveAttribute.getValue()));
					}
					if("返回类型".equals(driveAttribute.getDescription())){
						LSPObject.setReturnType(Integer.parseInt(driveAttribute.getValue()));
					}
					if("LSP逻辑ID".equals(driveAttribute.getDescription())){
						LSPObject.setLspLogicId(Integer.parseInt(driveAttribute.getValue()));
					}
					if("业务类型".equals(driveAttribute.getDescription())){
						LSPObject.setOperationType(Integer.parseInt(driveAttribute.getValue()));
					}
					if("等待恢复时间".equals(driveAttribute.getDescription())){
						LSPObject.setWaittime(Integer.parseInt(driveAttribute.getValue()));
					}
				}
				LSPProtectionList.add(LSPObject);
			}
		}catch(Exception e){
			throw e;
		}
		return LSPProtectionList;
	}
	
	
}

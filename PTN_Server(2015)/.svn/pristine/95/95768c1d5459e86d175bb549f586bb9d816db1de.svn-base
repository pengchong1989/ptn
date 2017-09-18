package com.nms.rmi.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.champor.license.Features;
import com.champor.license.client.LicenseClient;
import com.nms.rmi.ui.util.LicenseClientUtil;
import com.nms.rmi.ui.util.ServerConstant;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnFileChooser;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;

/**
 * license导入panel
 * 
 * @author kk
 * 
 */
public class LicensePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public LicensePanel() {
		this.initComponent();
		this.setLayout();
		this.addListener();
		this.initIpData();
	}

	/**
	 * 初始化IP地址
	 */
	public void initIpData() {

		try {
			this.initIpComboxData();
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {

		}
	}

	/**
	 * 添加监听事件
	 */
	private void addListener() {

		// 查询ID按钮
		this.btnSelect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnSelectAction();
			}

		});
		// 选择文件按钮
		this.btnSelectFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnSelectFileAction();
			}

		});
		// 导入按钮
		this.btnImport.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnImportAction();
			}

		});
	}

	/**
	 * 导入按钮事件
	 */
	private void btnImportAction() {
		Features features = null;
		LicenseClientUtil licenseClientUtil = null;
		List<Features> f= new ArrayList<Features>();
		List<String> ipList=null;
		try {
			if (this.txtImport.getText().length() == 0) {
				DialogBoxUtil.errorDialog(null, ResourceUtil.srcStr(StringKeysLbl.LBL_SELECT_FILE));
				return;
			}
			// 通过选择的路径获取许可对象
			licenseClientUtil = new LicenseClientUtil();
			ipList = licenseClientUtil.getIp();
			if(ipList.size() == 0){
				DialogBoxUtil.errorDialog(null ,ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_LICENSE_FILE));
				return;	
			}else{			
			   for(int i=0;i<ipList.size();i++){
			       features = licenseClientUtil.getFeatures(this.txtImport.getText(),ipList.get(i));
			       f.add(features);
			       if (null != features) {
				       try {
					        this.copyFile(this.txtImport.getText(), ServerConstant.LICENSEPATH + ServerConstant.LICENSEFILENAME);					       
				       } catch (Exception e) {
					       DialogBoxUtil.errorDialog(null ,ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_MANAGER_IMPORT_FAIL));
					       return;
				       }
				    DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_IMPORT_SUCCESS));
				    break;
			       }
			  }
			//判断导入的license是否存在该网卡，不存在时		     
	        Set<Features> s=new HashSet<Features>(f);
	        if(s.size()==1 && null == f.get(0)){
		      DialogBoxUtil.errorDialog(null, ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_LICENSE_FILE));
		      return;
	
	        }
			}

		} catch (IOException e) {
			DialogBoxUtil.errorDialog(null ,ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_IMPORT_FAIL));
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}

	}

	/**
	 * 复制单个文件
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf.txt
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf.txt
	 * @return boolean
	 * @throws IOException
	 */
	private void copyFile(String oldPath, String newPath) throws IOException {
		InputStream inStream  = null;
		FileOutputStream fs = null;
		try {
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				 inStream = new FileInputStream(oldPath); // 读入原文件
				 fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					fs.write(buffer, 0, byteread);
				}
				fs.flush();
			}
		} catch (IOException e) {
			throw e;
		}finally{
			if(fs != null){
				try {
					fs.close();
				} catch (Exception e2) {
					ExceptionManage.dispose(e2, getClass());
				}finally{
					fs = null;
				}
			}
			if(inStream != null){
				try {
					inStream.close();
				} catch (Exception e2) {
					ExceptionManage.dispose(e2, getClass());
				}finally{
					inStream = null;
				}
			}
		}

	}

	/**
	 * 选择按钮事件
	 */
	private void btnSelectFileAction() {
		new PtnFileChooser(PtnFileChooser.TYPE_FILE, this.txtImport, new FileNameExtensionFilter("XML", "xml"));

	}

	/**
	 * 查询按钮事件
	 */
	private void btnSelectAction() {

		String ip = null;
		LicenseClient licenseClient = null;
		String neId = null;
		try {
			ip = (String) this.ipSelect.getSelectedItem();          
			// 验证是否填写了IP
			if (null == ip) {
				DialogBoxUtil.errorDialog(null,  ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_IP_SELECT));
				return;
			}
			// 从许可程序中获取机器唯一标识ID
			licenseClient = new LicenseClient();
			neId = licenseClient.getSig(ip);
			// 如果没有返回值，说明IP地址填写不正确
			if (null == neId || "".equals(neId)) {
				DialogBoxUtil.errorDialog(null, ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_IP_ADDRESS));
				return;
			}
			this.txtNeId.setText(neId);

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			ip = null;
			licenseClient = null;
			neId = null;
		}

	}

	/**
	 * 初始化ip下拉列表
	 * @throws Exception 
	 */
	private void initIpComboxData() throws Exception {	
		try {
			DefaultComboBoxModel defaultComboBoxModel = null;
			try {
				defaultComboBoxModel = (DefaultComboBoxModel) ipSelect.getModel();
				LicenseClientUtil licenseClientUtil = new LicenseClientUtil();
				List<String> ipList = licenseClientUtil.getIp();
				for(int i=0;i<ipList.size();i++){
					    defaultComboBoxModel.addElement(ipList.get(i).toString());
		                ipSelect.setModel(defaultComboBoxModel);					
				}
		    					
			} catch (Exception e) {
				ExceptionManage.dispose(e, UiUtil.class);
			} finally {
				defaultComboBoxModel = null;
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {

		}		
	}
	
	
	
	
	
	
	/**
	 * 初始化控件
	 */
	private void initComponent() {
		this.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_IMPORT_LICENSE)));

		this.lblIp = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_OWER_IPADDRE));
		this.ipSelect = new JComboBox();
		this.btnSelect = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SELECT));
		this.txtNeId = new JTextField();
		this.txtNeId.setEditable(false);
		this.lblNeid = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_OWER_ID));
		this.panel_select = new JPanel();
		this.panel_select.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_QUERY_OWERID)));
		this.panel_import = new JPanel();
		this.panel_import.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_IMPORT_LICENSE)));
		this.lblImport = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SELECT_FILE));
		this.btnImport = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_INPORT));
		this.txtImport = new JTextField();
		this.txtImport.setEditable(false);
		this.btnSelectFile = new JButton(ResourceUtil.srcStr(StringKeysLbl.LBL_SELECT_FILE));
	}

	/**
	 * 导入panel布局
	 */
	private void setLayoutImport() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 120, 200, 100 };
		componentLayout.columnWeights = new double[] { 0, 0.1, 0 };
		componentLayout.rowHeights = new int[] { 30, 30 };
		componentLayout.rowWeights = new double[] { 0.1, 0.1 };
		this.panel_import.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;

		// 导入文件的label
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(25, 5, 5, 5);
		componentLayout.setConstraints(this.lblImport, c);
		this.panel_import.add(this.lblImport);

		// 导入文件的text
		c.gridx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		componentLayout.setConstraints(this.txtImport, c);
		this.panel_import.add(this.txtImport);

		// 选择文件按钮
		c.fill = GridBagConstraints.NONE;
		c.gridx = 2;
		componentLayout.setConstraints(this.btnSelectFile, c);
		this.panel_import.add(this.btnSelectFile);

		// 导入按钮
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(5, 5, 25, 5);
		componentLayout.setConstraints(this.btnImport, c);
		this.panel_import.add(this.btnImport);
	}

	/**
	 * 查询本机ID的panel布局
	 */
	private void setLayoutSelect() {

		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 120, 200, 100 };
		componentLayout.columnWeights = new double[] { 0, 0.1, 0 };
		componentLayout.rowHeights = new int[] { 30, 30 };
		componentLayout.rowWeights = new double[] { 0.1, 0.1 };
		this.panel_select.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;

		// 本机Ip的label
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(25, 5, 5, 5);
		componentLayout.setConstraints(this.lblIp, c);
		this.panel_select.add(this.lblIp);

		// 本机Ip的text
		c.gridx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		componentLayout.setConstraints(this.ipSelect, c);
		this.panel_select.add(this.ipSelect);

		// 查询按钮
		c.fill = GridBagConstraints.NONE;
		c.gridx = 2;
		componentLayout.setConstraints(this.btnSelect, c);
		this.panel_select.add(this.btnSelect);

		// 本机Id的label
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(5, 5, 25, 5);
		componentLayout.setConstraints(this.lblNeid, c);
		this.panel_select.add(this.lblNeid);

		// 本机Id的text
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		componentLayout.setConstraints(this.txtNeId, c);
		this.panel_select.add(this.txtNeId);
	}

	/**
	 * 此页面总布局
	 */
	private void setLayout() {
		this.setLayoutSelect();
		this.setLayoutImport();

		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWeights = new double[] { 0.1 };
		componentLayout.rowWeights = new double[] { 0.1, 0.1 };
		this.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTH;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(30, 0, 15, 0);
		componentLayout.setConstraints(this.panel_select, c);
		this.add(this.panel_select);

		c.insets = new Insets(15, 0, 0, 0);
		c.gridy = 1;
		componentLayout.setConstraints(this.panel_import, c);
		this.add(this.panel_import);
	}

	
	private JLabel lblIp; // 本机IPlabel
//	private JTextField txtIp; // 本机IP文本框
	private JComboBox ipSelect;
	private JButton btnSelect; // 查询本机ID按钮
	private JLabel lblNeid; // 本机IDlabel
	private JTextField txtNeId; // 本机ID文本框
	private JPanel panel_select; // 查询本机ID的panel
	private JPanel panel_import; // 导入panel
	private JLabel lblImport; // 导入label
	private JButton btnImport; // 导入按钮
	private JTextField txtImport; // 导入文本框
	private JButton btnSelectFile; // 选择文件按钮
	
	public static void main(String[] args) {
		try {
			FileOutputStream  fs = new FileOutputStream( System.getProperty("user.dir") + "license.xml");
			try {
				fs.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}

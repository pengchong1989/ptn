package com.nms.rmi.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.apache.commons.net.ntp.TimeStamp;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTip;




/**
 * 时间同步
 * @author guoqc
 * 
 */
public class TimeSynchroPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public TimeSynchroPanel() {
		this.initComponent();				
		this.setLayout();
		this.addListener();
				
	}
	
	/**
	 * 添加监听事件
	 */
	private void addListener() {

		// 确定按钮
		this.btnSelect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				 btnSelectAction();
			}

		});
	}
	

	

	/**
	 * 确定按钮事件
	 * @throws Exception 
	*/
	private void btnSelectAction() {
	    String time=this.getDateTime();
	    if(time.equals(ResourceUtil.srcStr(StringKeysLbl.LBL_NET_ERROR))){
	    	DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysLbl.LBL_NET_ERROR));
	    }else{
		    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Date da;
		    Boolean a=true;
			try {
				da = format.parse(time);
				a=this.setSystemTime(da);
				if(a==true){
					this.txtSetIp.setText(time);
					DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS));
				}else{
					DialogBoxUtil.succeedDialog(null, "没有权限");
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
	    
	    }
		


	}
 
	public  String getDateTime(){
		  NTPUDPClient timeClient = new NTPUDPClient();
		  timeClient.setDefaultTimeout(5000);
		  Date date= new Date();
		  TimeInfo info;
		  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  try {
			 timeClient.open();			
			 try {
				    for(int i=0;i<hostName.length;i++){
				    	InetAddress timeServerAddress = InetAddress.getByName(hostName[i]);					
						try {
							info = timeClient.getTime(timeServerAddress);
							TimeStamp timeStamp =info.getMessage().getTransmitTimeStamp();
						    date = timeStamp.getDate();
						    dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
							if(date!=null){
								break;
								
							}
							
						} catch (IOException e) {
							if(i==2){
								return ResourceUtil.srcStr(StringKeysLbl.LBL_NET_ERROR);
							}
						}
				    }
					
					
				} catch (UnknownHostException e) {
					return ResourceUtil.srcStr(StringKeysLbl.LBL_NET_ERROR);
				}
		} catch (SocketException e1) {
			return ResourceUtil.srcStr(StringKeysLbl.LBL_NET_ERROR);
		}finally{
			
			timeClient.close();
		}
		  return dateFormat.format(date);
		}
	
	
	public boolean  setSystemTime(Date date){
	    String os = System.getProperty("os.name");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");		
		DateFormat dateFormat1 = new SimpleDateFormat("yyyyMMdd");	
		DateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss");
		String dateWindows = dateFormat.format(date);
		String dateLinux = dateFormat1.format(date);
		String time = dateFormat2.format(date);
		try {
			if(os.matches("^(?i)Windows.*$")){
				Runtime.getRuntime().exec("cmd /c date "+dateWindows);
				Runtime.getRuntime().exec("cmd /c time "+time);
			}else{
				Runtime.getRuntime().exec(" sudo date -s "+dateLinux);
				Runtime.getRuntime().exec(" sudo date -s "+time);			
			}
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	
		
	/**
	 * 初始化控件
	 */
	private void initComponent() {
		this.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_SYSTEM_CONFIG)));
		this.lblIp = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_CURRENT_TIME));
		this.btnSelect = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_TIME_SYNCHOR));
		this.panel_select = new JPanel();
		this.panel_select.setBorder(null);
		this.txtSetIp = new JTextField();
		this.txtSetIp.setEditable(false);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		txtSetIp.setText(df.format(new Date()));// new Date()为获取当前系统时间
		
	}


	/**
	 * 系统IP设置panel布局
	 */
	private void setLayoutSelect() {

		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 120, 200, 120 };
		componentLayout.columnWeights = new double[] { 0, 0.1,0 };
		componentLayout.rowHeights = new int[] { 20, 20 };
		componentLayout.rowWeights = new double[] { 0.1, 0.1 };
				
		this.panel_select.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;

		// 本机Ip的label
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(25, 5, 5, 5);
		componentLayout.setConstraints(this.lblIp, c);
		this.panel_select.add(this.lblIp);

		// 本机Ip的选择
		c.gridx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		componentLayout.setConstraints(this.txtSetIp, c);
		this.panel_select.add(this.txtSetIp);

		// 确定按钮
		c.fill = GridBagConstraints.NONE;
		c.gridx = 2;
		componentLayout.setConstraints(this.btnSelect, c);
		this.panel_select.add(this.btnSelect);
				
	}


	/**
	 * 此页面总布局
	 */
	private void setLayout() {
		this.setLayoutSelect();

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

	}


	private JLabel lblIp; // 本机IPlabel
	private JButton btnSelect; // 确定按钮
	private JPanel panel_select; // 查询本机ID的panel
	private JTextField txtSetIp; // 系统设置IP文本框
//    private static final String[] hostName = {"time-a.nist.gov", "time-nw.nist.gov", "time.nist.gov"}; 
    private static final String[] hostName = {"cn.pool.ntp.org", "time.windows.com", "ntp1.aliyun.com"};
	
	public static void main(String[] args) {

	  	          	
	}
	
}

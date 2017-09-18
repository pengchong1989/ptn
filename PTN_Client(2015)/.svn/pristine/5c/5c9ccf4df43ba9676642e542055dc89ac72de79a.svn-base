package com.nms.ui.ptn.performance.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysTip;

/**
 * @author Administrator function：历史性能过滤时的时间小窗口
 */
public class TimeWindow extends PtnDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel textLable;
	private JLabel startTimeLabel;
	private JTextField startTimeText;
	private JLabel endTimeLabel;
	private JTextField endTimeText;
	private JButton confirm;
	private JButton cancel;
	private JPanel buttonPanel = null;
	private GridBagLayout gridBagLayout = null;
	private JDialog jDialog = null;
	private JLabel timeRegex;
	public TimeWindow() {
		init();
	}

	private void init() {
		try {
			initComponents();
			addListener();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void initComponents() throws Exception {
		SimpleDateFormat sdf = null;
		String time = null;
		try {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			textLable = new JLabel(ResourceUtil.srcStr(StringKeysTip.CUSTOMDATESCOPE));
			timeRegex=new JLabel(ResourceUtil.srcStr(StringKeysTip.TIMEREGEX));
			startTimeLabel = new JLabel(ResourceUtil.srcStr(StringKeysTip.START));
			endTimeLabel = new JLabel(ResourceUtil.srcStr(StringKeysTip.END));
			time = sdf.format(new java.util.Date());
			startTimeText = new JTextField(time);
			endTimeText = new JTextField(time);
			this.setTitle(ResourceUtil.srcStr(StringKeysTip.CUSTOMSCOPE));
			confirm = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIRM));
			cancel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
			buttonPanel = new JPanel();
			gridBagLayout = new GridBagLayout();
			buttonPanel.add(confirm);
			buttonPanel.add(cancel);
			this.add(buttonPanel);
			setButtonLayout();
			setLayout();
			this.setLayout(gridBagLayout);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			sdf = null;
			time = null;
		}
	}

	private void addListener() {
		try {
			jDialog = this;
			confirm.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					btnConfirm();
				}
			});
			cancel.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					jDialog.dispose();
				}
			});
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/* 确定按钮事件 */
	private void btnConfirm() {
		SimpleDateFormat sdf = null;
		String regex=null;
		String startTime=null;
		String endTime=null;
		try {
			regex = "^(((20[0-3][0-9]-(0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|(20[0-3][0-9]-(0[2469]|11)-(0[1-9]|[12][0-9]|30))) (20|21|22|23|[0-1][0-9]):[0-5][0-9]:[0-5][0-9])$";
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			startTime=startTimeText.getText().trim();
			endTime=endTimeText.getText().trim();
			if(startTime.equals("")||endTime.equals("")){
				DialogBoxUtil.succeedDialog(this,ResourceUtil.srcStr(StringKeysTip.IMPORTDATE));
				return ;
			}
			else if(!startTime.matches(regex)||!endTime.matches(regex)){
				DialogBoxUtil.succeedDialog(this,ResourceUtil.srcStr(StringKeysTip.DATEREGEXFALSE));
				return ;
			}else{
				if(sdf.parse(startTime).getTime()>sdf.parse(endTime).getTime()){
					DialogBoxUtil.succeedDialog(this,ResourceUtil.srcStr(StringKeysTip.STARTTIMEANDENDTIME));
					return ;
				}
			}
			jDialog.dispose();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			sdf=null;
			regex=null;
			 startTime=null;
			 endTime=null;
		}
	}

	@SuppressWarnings("static-access")
	private void setLayout() {
		GridBagConstraints gridBagConstraints = null;
		try {
			gridBagConstraints = new GridBagConstraints();
			gridBagLayout.columnWidths = new int[] { 30, 30, 30 };
			gridBagLayout.columnWeights = new double[] { 0, 0, 0 };
			gridBagLayout.rowHeights = new int[] { 35, 35, 35, 35 };
			gridBagLayout.rowWeights = new double[] { 0, 0, 0, 0, 0, 0, 0, 0 };
			gridBagConstraints.insets = new Insets(5, 10, 0, 0);
			gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(textLable, gridBagConstraints);
			this.add(textLable);
			
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 1;
			gridBagLayout.setConstraints(timeRegex, gridBagConstraints);
			this.add(timeRegex);
			
			gridBagConstraints.anchor = gridBagConstraints.WEST;
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 2;
			gridBagLayout.setConstraints(startTimeLabel, gridBagConstraints);
			this.add(startTimeLabel);

			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 2;
			gridBagLayout.setConstraints(startTimeText, gridBagConstraints);
			this.add(startTimeText);

			gridBagConstraints.anchor = gridBagConstraints.WEST;
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 3;
			gridBagLayout.setConstraints(endTimeLabel, gridBagConstraints);
			this.add(endTimeLabel);

			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 3;
			gridBagLayout.setConstraints(endTimeText, gridBagConstraints);
			this.add(endTimeText);

			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 4;
			gridBagLayout.setConstraints(buttonPanel, gridBagConstraints);

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 按钮所在panel布局
	 */
	private void setButtonLayout() throws Exception {
		GridBagConstraints gridBagConstraints = null;
		GridBagLayout gridBagLayout = null;
		try {
			gridBagLayout = new GridBagLayout();
			gridBagConstraints = new GridBagConstraints();
			gridBagLayout.columnWidths = new int[] { 20, 20 };
			gridBagLayout.columnWeights = new double[] { 0, 0 };
			gridBagLayout.rowHeights = new int[] { 21 };
			gridBagLayout.rowWeights = new double[] { 0 };

			gridBagConstraints.insets = new Insets(5, 10, 5, 0);
			gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(confirm, gridBagConstraints);

			gridBagConstraints.insets = new Insets(5, 25, 5, 5);
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(cancel, gridBagConstraints);

			buttonPanel.setLayout(gridBagLayout);

		} catch (Exception e) {
			throw e;
		}
	}

	public JTextField getStartTimeText() {
		return startTimeText;
	}

	public void setStartTimeText(JTextField startTimeText) {
		this.startTimeText = startTimeText;
	}

	public JTextField getEndTimeText() {
		return endTimeText;
	}

	public void setEndTimeText(JTextField endTimeText) {
		this.endTimeText = endTimeText;
	}
}

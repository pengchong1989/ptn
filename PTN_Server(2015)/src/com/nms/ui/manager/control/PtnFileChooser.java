package com.nms.ui.manager.control;

import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysBtn;


/**
 * 自定义文件选择器
 * 
 * @author kk
 * 
 */
public class PtnFileChooser extends JFileChooser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 文件
	 */
	public static final int TYPE_FILE = 1;
	/**
	 * 文件夹
	 */
	public static final int TYPE_FOLDER = 2;

	// /**
	// * 文本框
	// */
	// private JTextField textField = null;
	//
	// /**
	// * 文件过滤器
	// */
	// private FileNameExtensionFilter fileNameExtensionFilter = null;

	/**
	 * 创建一个新的实例
	 * 
	 * @param type
	 *            类型 文件或文件夹
	 * @param textField
	 *            文本框
	 */
	public PtnFileChooser(int type, JTextField textField, FileNameExtensionFilter fileNameExtensionFilter) {
		Component comp = null;
		try {

			if (type == TYPE_FILE) {
				this.setFileFilter(fileNameExtensionFilter);
				this.removeChoosableFileFilter(this.getAcceptAllFileFilter());
				// 打开窗口
				this.showOpenDialog(null);
			} else {
				this.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);// 只能选择目录
				// 打开窗口
				this.setApproveButtonText(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIRM));
				this.showOpenDialog(null);
			}

			// 找出文件名text控件 并且把可编辑性设为false
			comp = getLabelForInChooser(this, "FileChooser.fileNameLabelText");
			if (comp instanceof JTextField) {
				JTextField field = ((JTextField) comp);
				field.setEditable(false);
			}


			// 关闭选择文件窗口时，如果选择的文件不为null，就把此路径显示到控件中。
			if (null != this.getSelectedFile()) {
				textField.setText(this.getSelectedFile().toString());
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			comp = null;
		}
	}

	/**
	 * 通过key 从 chooser中找出text控件
	 * 
	 * @param chooser
	 * @param key
	 * @return
	 */
	public Component getLabelForInChooser(JFileChooser chooser, String key) {
		java.util.Locale l = chooser.getLocale();
		String s = UIManager.getString(key, l);

		javax.swing.plaf.FileChooserUI ui = chooser.getUI();
		int count = ui.getAccessibleChildrenCount(chooser);
		for (int i = 0; i < count; i++) {
			javax.accessibility.Accessible a = ui.getAccessibleChild(chooser, i);
			JLabel label = findLabel((JComponent) a, s);
			if (label != null) {
				return label.getLabelFor();
			}
		}
		return null;
	}

	/**
	 * 找出txt控件
	 * 
	 * @param comp
	 * @param s
	 * @return
	 */
	private JLabel findLabel(JComponent comp, String s) {
		JLabel label = null;
		if (comp instanceof JLabel) {
			if (((JLabel) comp).getText().equals(s)) {
				label = (JLabel) comp;
			}
		} else if (comp instanceof JComponent) {
			Component[] comps = comp.getComponents();
			for (int i = 0; i < comps.length; i++) {
				if (comps[i] instanceof JComponent) {
					label = findLabel((JComponent) comps[i], s);
					if (label != null) {
						break;
					}
				}
			}
		}
		return label;
	}

}

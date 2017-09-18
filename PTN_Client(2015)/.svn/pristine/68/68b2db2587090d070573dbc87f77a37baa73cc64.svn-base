/*
 * dialog.java
 *
 * Created on __DATE__, __TIME__
 */

package com.nms.model.util;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import twaver.Element;
import twaver.Node;
import twaver.TDataBox;
import twaver.tree.TTree;

import com.nms.db.bean.system.roleManage.RoleManage;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

/**
 * 权限 显示 Panel
 * 
 * @author _sy__
 */
public class RoleRootPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TDataBox box = null; // 数据容器
	private TTree tree = null; // tree对象
	private JScrollPane scrollPane = null; // 带滚动条的panel
	private boolean isCheck; // 是否显示复选框

	/**
	 * 创建一个新的实例
	 * 
	 * @param isCheck
	 *            是否显示复选框
	 */
	public RoleRootPanel(boolean isCheck) {
		this.isCheck = isCheck;
		this.initComponent();
		this.setLayout();
		this.addAction();
	}

	/**
	 * 添加事件
	 */
	private void addAction() {
		// 如果有复选框，就添加复选框选中事件
		if (this.isCheck) {
			this.tree.addTreeSelectionListener(new TreeSelectionListener() {
				@Override
				public void valueChanged(TreeSelectionEvent e) {
					try {
						treeNodeAction();
					} catch (Exception e1) {
						ExceptionManage.dispose(e1,this.getClass());
					}
				}
			});
		}
	}

	/**
	 * tree的节点选中事件
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void treeNodeAction() throws Exception {

		List<Element> elementList = null;
		Node node_manage = null; // 管理node
		Node node_select = null; // 查看node
		try {
			elementList = this.box.getAllElements();
			// 遍历界面上所有元素
			for (Element element : elementList) {
				if (element instanceof Node) {
					// 如果此节点为父节点
					if (element.getParent() == null) {
						// 如果此节点的子节点数量为2时
						if (element.getChildren().size() == 2) {
							// 默认0为管理 node 1为查看node
							node_manage = (Node) element.getChildren().get(0);
							node_select = (Node) element.getChildren().get(1);

							// 当管理和查看同时没有选中时， 此父节点也不能选中
							if (!node_manage.isSelected() && !node_select.isSelected()) {
								element.setSelected(false);
							} else if (node_manage.isSelected() && node_select.isSelected()) {
								element.setSelected(true);
							} else if (node_manage.isSelected()) {
								// 如果管理节点为选中， 那么此父节点和查看节点都为选中 既 有管理功能 就有查看功能。
								node_select.setSelected(true);
							}

						}
					}
				}
			}

		} catch (Exception e) {
			throw e;
		} finally {
			elementList = null;
			node_manage = null; // 管理node
			node_select = null; // 查看node
		}
	}

	/**
	 * 初始化控件
	 */
	private void initComponent() {
		this.box = new TDataBox(ResourceUtil.srcStr(StringKeysLbl.LBL_ROOT_LIST));
		this.tree = new TTree(this.box);
		// 如果ischeck为true tree的模式设为显示复选框模式
		if (this.isCheck) {
			this.tree.setTTreeSelectionMode(TTree.CHECK_DESCENDANT_ANCESTOR_SELECTION);
		}
		this.tree.setDataBoxIconURL(null);
		this.scrollPane = new JScrollPane(this.tree);
	}

	/**
	 * 设置布局
	 */
	private void setLayout() {
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 0 };
		layout.columnWeights = new double[] { 0.1 };
		layout.rowHeights = new int[] { 300 };
		layout.rowWeights = new double[] { 0.1 };
		this.setLayout(layout);

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		layout.addLayoutComponent(this.scrollPane, c);
		this.add(scrollPane);
	}

	/**
	 * 绑定数据
	 * 
	 * @param roleManageList
	 *            权限集合
	 * @throws Exception
	 */
	public void bindingData(List<RoleManage> roleManageList) throws Exception {

		if (null == roleManageList || roleManageList.size() == 0) {
			throw new Exception("roleManageList is null");
		}
		try {
			// 遍历权限集合
			for (RoleManage roleManage : roleManageList) {
				// 如果是最上层节点 就递归找出此节点的所有子节点
				if (roleManage.getParentId() == 0) {
					this.recursionFindNode(roleManage, roleManageList, null);
				}
			}
			this.tree.expandAll();
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 递归查询节点并加载到box中
	 * 
	 * @param roleManage
	 *            当前节点
	 * @param roleManageList
	 *            所有节点集合
	 * @param parentNode
	 *            父节点对象
	 * @throws Exception
	 */
	private void recursionFindNode(RoleManage roleManage, List<RoleManage> roleManageList, Node parentNode) throws Exception {
		Node node = null;
		try {
			// 创建node
			/**
			 * 添加条件  （新建 NODE  时）
			 * 只有在查看界面，且角色为默认角色  Admainistrator 时，安全管理才可显示：  即创建
			 * 其他： 新建和修改界面   -都不可显示  ：  创建
			 */
			if(null!=roleManage){
				if(null!=roleManage.getRoleName()&&roleManage.getRoleName().equals("Administrator")){
					node = this.createNode(roleManage, parentNode);
					this.box.addElement(node);
				}else if(null==roleManage.getRoleName()&&roleManage.getId()!=RootFactory.SATY_MANAGE&&roleManage.getId()!=RootFactory.SATYMODU&&roleManage.getId()!=RootFactory.SATY_SELECT){
					node = this.createNode(roleManage, parentNode);
					this.box.addElement(node);
				}
			}else{
				node = this.createNode(roleManage, parentNode);
				this.box.addElement(node);
			}
			// 遍历权限集合
			for (RoleManage roleManage_child : roleManageList) {
				// 如果此此节点的父节点等于roleManage 就递归继续遍历子节点
				if (roleManage_child.getParentId() == roleManage.getId()) {
					this.recursionFindNode(roleManage_child, roleManageList, node);
				}
			}

		} catch (Exception e) {
			throw e;
		} finally {
			node = null;
		}
	}

	/**
	 * 创建节点
	 * 
	 * @param roleManage
	 *            权限对象
	 * @param parentNode
	 *            父节点
	 */
	private Node createNode(RoleManage roleManage, Node parentNode) throws Exception {
		Node node = new Node();
		if(ResourceUtil.language.equals("en_US")){
				//英文
			node.setName(roleManage.getName_en());
		}else{
			// 汉语
			node.setName(roleManage.getName());
		}
		
		node.setUserObject(roleManage);
		node.setParent(parentNode);
		return node;
	}

	/**
	 * 获取所有选中的菜单
	 * 
	 * @return 选中的菜单集合
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<RoleManage> getSelectRoleManage() throws Exception {
		List<RoleManage> roleManageList = null;
		List<Element> elementList = null;
		try {
			roleManageList = new ArrayList<RoleManage>();
			elementList = this.box.getAllElements();
			// 遍历box上面所有元素
			for (Element element : elementList) {
				// 如果元素为Node
				if (element instanceof Node) {
					// 如果是选中的，就放入结果集中。
					if (element.isSelected()) {
						roleManageList.add((RoleManage) element.getUserObject());
					}
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			elementList = null;
		}
		return roleManageList;
	}

	/**
	 * 清空box方法
	 */
	public void clear() {
		this.box.clear();
	}

	/**
	 * 选中数据
	 * 
	 * @param roleManageList
	 *            要选中的权限集合
	 * @throws Exception
	 */
	public void checkData(List<RoleManage> roleManageList) throws Exception {

		if (null == roleManageList || roleManageList.size() == 0) {
			throw new Exception("roleManageList is null");
		}

		try {
			// 如果复选框为显示状态
			if (this.isCheck) {
				for (RoleManage roleManage : roleManageList) {
					this.checkNode(roleManage);
				}
			}
			treeNodeAction();
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * 根据权限对象选中树节点
	 * 
	 * @param roleManage
	 *            权限对象
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void checkNode(RoleManage roleManage) throws Exception {

		List<Element> elementList = null;
		RoleManage roleManageNode = null;
		try {
			elementList = this.box.getAllElements();
			// 遍历box中所有元素
			for (Element element : elementList) {
				// 如果元素为node
				if (element instanceof Node) {
					// 如果此元素的userObject属性不为null 并且为roleManage对象
					if(element.getParent()!=null){
						if (null != element.getUserObject() && element.getUserObject() instanceof RoleManage) {
							roleManageNode = (RoleManage) element.getUserObject();
							// 如果此node的roleMnage对象和入参的主键相同。 把此node设为选中 并且跳出循环
							if (roleManageNode.getId() == roleManage.getId()) {
								element.setSelected(true);
								element.getParent().setSelected(true);
								break;
							}
						}
					}
					
				}
			}

		} catch (Exception e) {
			throw e;
		} finally {
			elementList = null;
			roleManageNode = null;
		}
	}
}
package com.nms.ui.ptn.business.dialog.etreepath;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuItem;

import twaver.Element;
import twaver.Link;
import twaver.Node;
import twaver.TView;

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.ptn.path.eth.EtreeInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysMenu;
import com.nms.ui.ptn.ne.ac.view.AcListDialog;

/**
 * 添加etree的右键菜单类
 * 
 * @author kk
 * 
 */
public class AddEtreeMenu {

	private AddEtreeDialog addEtreeDialog = null;
	private TView tView = null;

	public AddEtreeMenu(AddEtreeDialog addEtreeDialog, TView tView) {
		this.addEtreeDialog = addEtreeDialog;
		this.tView = tView;
	}

	/**
	 * 创建右键菜单
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	protected JMenuItem createMenu(final String key, final Element element,final List<EtreeInfo> etreeInfos) throws Exception {
		if (null == key && "".equals(key)) {
			throw new Exception("key is null");
		}
		JMenuItem menuItem = null;
		try {

			menuItem = new JMenuItem(ResourceUtil.srcStr(key));
			menuItem.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("unchecked")
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					final List<Element> allElements = tView.getDataBox().getAllElements();
					Link link = null;
					try {
						// 设置根
						if (key.equals(StringKeysMenu.MENU_SELECT_ROOT)) {

							// 如果有其他根，取消其他根上显示的root字样。
							if (element instanceof Node) {
								if (addEtreeDialog.getRootId() != 0) {
									for (Element e : allElements) {
										if (e instanceof Node) {
											Node n = (Node) e;
											n.setBusinessObject("");
											n.removeAttachment("topoTitle");
										}
									}
									addEtreeDialog.clearFields();
								}
								Node node = (Node) element;
								node.setBusinessObject("root");
								node.addAttachment("topoTitle");
								SiteInst site = (SiteInst) node.getUserObject();
								addEtreeDialog.setRootId(site.getSite_Inst_Id());

								// 弹出选择AC端口界面
								selectPort(element,etreeInfos);
								// 自动选择路径
								autoSelectPw("root", node);
							}

						} else if (key.equals(StringKeysMenu.MENU_SELECT_LEAF)) { // 设置叶子

							if (element instanceof Node) {
								Node node = (Node) element;
								node.setBusinessObject("branch");
								node.addAttachment("topoTitle");
								addEtreeDialog.getSelBranchNodeList().add(node);
								// 弹出选择AC端口界面
								selectPort(element,etreeInfos);
								// 自动选择路径
								autoSelectPw("branch", node);
							}

						} else if (key.equals(StringKeysMenu.MENU_CANEL_CONFIG)) { // 取消设置

							if (element instanceof Node) {
								Node node = (Node) element;
								element.removeAttachment("topoTitle");
								element.setBusinessObject("");
								// 如果是根。 设置根的id为0 清空pw列表
								if ("root".equals(element.getBusinessObject())) {
									addEtreeDialog.setRootId(0);
								} else {
									// 如果是叶子，移除此叶子关联的pw，移除此叶子的AC端口
									deleteAc(element);
									addEtreeDialog.getSelBranchNodeList().remove(node);
								}
								deletePw(element);
							} else if (element instanceof Link) {
								link = (Link) element;
								deletePath(link);
							}

						} else if (key.equals(StringKeysMenu.MENU_SELECT_PORT)) { // 选择端口
							selectPort(element,etreeInfos);
							Node node = (Node) element;
							if(node.getBusinessObject().toString().equals("root"))
							{
								// 自动选择路径
								autoSelectPw("root", node);
							}else
							{
								// 自动选择路径
								autoSelectPw("branch", node);
							}
							
						} else if (key.equals(StringKeysMenu.MENU_SELECT_PATH)) { // 设置路径
							link = (Link) element;
							setPath(link);
						}
					} catch (Exception e) {
						ExceptionManage.dispose(e,this.getClass());
					}

				}
			});

		} catch (Exception e) {
			throw e;
		}
		return menuItem;
	}

	/**
	 * 设置端口
	 * 
	 * @param type
	 *            根或叶子
	 * @throws Exception
	 */
	private void selectPort(Element element,List<EtreeInfo> etreeInfos) throws Exception {
		AcListDialog acListDialog = null;
		try {
//			acListDialog = new AcListDialog(true, element,0);
			acListDialog = new AcListDialog(true, element,0,etreeInfos);
			addEtreeDialog.setport(acListDialog, element);
		} catch (Exception e) {
			throw e;
		} finally {
			acListDialog = null;
		}

	}

	/**
	 * 设置路径
	 * 
	 * @throws Exception
	 */
	private void setPath(Link link) throws Exception {
		PwInfo pwInfo = null;
		List<PwInfo> pwInfoList = null;
		try {
			pwInfo = (PwInfo) link.getUserObject();
			this.checkingPath(pwInfo);

			link.putLinkColor(Color.BLUE);
			pwInfoList = this.addEtreeDialog.getPwInfoTable().getAllElement();
			pwInfoList.add(pwInfo);
			this.addEtreeDialog.getPwInfoTable().clear();
			this.addEtreeDialog.getPwInfoTable().initData(pwInfoList);
		} catch (Exception e) {
			throw e;
		} finally {
			pwInfo = null;
			pwInfoList = null;
		}

	}

	/**
	 * 删除路径
	 * 
	 * @throws Exception
	 */
	private void deletePath(Link link) throws Exception {
		PwInfo pwInfo = null;
		List<PwInfo> pwInfoList = null;
		List<PwInfo> pwInfoList_add = null;
		try {
			pwInfoList_add = new ArrayList<PwInfo>();

			link.putLinkColor(Color.GREEN);
			pwInfo = (PwInfo) link.getUserObject();
			pwInfoList = this.addEtreeDialog.getPwInfoTable().getAllElement();
			for (PwInfo pwInfo_table : pwInfoList) {
				if (pwInfo_table.getPwId() != pwInfo.getPwId()) {
					pwInfoList_add.add(pwInfo_table);
				}
			}
			this.addEtreeDialog.getPwInfoTable().clear();
			this.addEtreeDialog.getPwInfoTable().initData(pwInfoList_add);
		} catch (Exception e) {
			throw e;
		} finally {
			pwInfo = null;
			pwInfoList = null;
			pwInfoList_add = null;
		}

	}

	/**
	 * 验证所有link中是否有与pwinfo相同路径的 如果有调用移除方法
	 * 
	 * @param pwinfo
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void checkingPath(PwInfo pwinfo) throws Exception {
		List<Element> allElements = null;
		PwInfo pwInfoLink = null;
		Link link = null;
		try {
			allElements = tView.getDataBox().getAllElements();

			for (Element element : allElements) {
				if (element instanceof Link) {
					link = (Link) element;
					pwInfoLink = (PwInfo) link.getUserObject();
					if ((pwInfoLink.getASiteId() == pwinfo.getASiteId() || pwInfoLink.getASiteId() == pwinfo.getZSiteId()) && (pwInfoLink.getZSiteId() == pwinfo.getASiteId() || pwInfoLink.getZSiteId() == pwinfo.getZSiteId())) {
						this.deletePath(link);
					}
				}
			}

		} catch (Exception e) {
			throw e;
		} finally {
			allElements = null;
			pwInfoLink = null;
			link = null;
		}
	}

	/**
	 * 删除叶子AC
	 * 
	 * @param element
	 * @throws Exception
	 */
	private void deleteAc(Element element) throws Exception {

		SiteInst siteInst = null;
		List<AcPortInfo> acPortList = null;
		List<AcPortInfo> acPortList_add = null;
		try {
			acPortList_add = new ArrayList<AcPortInfo>();

			siteInst = (SiteInst) element.getUserObject();

			acPortList = this.addEtreeDialog.getBranchAcTable().getAllElement();
			if (null != acPortList && acPortList.size() > 0) {
				for (AcPortInfo acPortInfo : acPortList) {
					if (acPortInfo.getSiteId() != siteInst.getSite_Inst_Id()) {
						acPortList_add.add(acPortInfo);
					}
				}
				this.addEtreeDialog.getBranchAcTable().clear();
				this.addEtreeDialog.getBranchAcTable().initData(acPortList_add);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			siteInst = null;
			acPortList = null;
			acPortList_add = null;
		}

	}

	/**
	 * 删除pw
	 * 
	 * @param element
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void deletePw(Element element) throws Exception {
		List<Element> allElements = null;
		SiteInst siteInst = null;
		Link link = null;
		PwInfo pwInfoLink = null;
		try {
			allElements = tView.getDataBox().getAllElements();
			siteInst = (SiteInst) element.getUserObject();
			for (Element element_all : allElements) {
				if (element_all instanceof Link) {
					link = (Link) element_all;
					pwInfoLink = (PwInfo) link.getUserObject();
					if (pwInfoLink.getASiteId() == siteInst.getSite_Inst_Id() || pwInfoLink.getZSiteId() == siteInst.getSite_Inst_Id()) {
						this.deletePath(link);
					}
				}
			}

		} catch (Exception e) {
			throw e;
		} finally {
			allElements = null;
			siteInst = null;
			link = null;
			pwInfoLink = null;
		}
	}

	/**
	 * 自动发现pw 选择往根或叶子调用此方法
	 * 
	 * @param type
	 * @throws Exception
	 */
	private void autoSelectPw(String type, Node node) throws Exception {
		Node node_root = null;
		try {
			// 选择完根节点后自动计算
			if ("root".equals(type)) {
				// 如果存在叶子节点，自动计算pw
				if (this.addEtreeDialog.getSelBranchNodeList().size() > 0) {
					for (Node node_branch : this.addEtreeDialog.getSelBranchNodeList()) {
						this.findLink(node, node_branch);
					}
				}
			} else {
				// 如果根节点不为null，自动计算pw
				if (null != this.addEtreeDialog.getRootAcAndSiteInst()) {
					node_root = this.findNode((SiteInst) this.addEtreeDialog.getRootAcAndSiteInst().getObject());
					if (null != node_root) {
						this.findLink(node_root, node);
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * 找nodeA和nodez之间的link
	 * 
	 * @param node_a
	 * @param node_z
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void findLink(Node node_a, Node node_z) throws Exception {

		List<Link> linkList = null;
		List<Element> elementList = null;
		Link link = null;
		boolean flag = true;
		try {
			linkList = new ArrayList<Link>();
			elementList = tView.getDataBox().getAllElements();

			// 遍历拓扑上所有元素，如果是link。并且from和to 与参数node_a和node_z一致，就放到集合中。
			for (Element element : elementList) {

				if (element instanceof Link) {
					link = (Link) element;
					if ((link.getFrom() == node_a || link.getFrom() == node_z) && (link.getTo() == node_a || link.getTo() == node_z)) {
						linkList.add(link);
					}
				}
			}

			// 如果node_a和node_z中link大与0条。 并且没有蓝色（已经选择）的,就默认选中一条路径
			if (linkList.size() > 0) {
				for (Link link_find : linkList) {
					if (link_find.getLinkColor() == Color.BLUE) {
						flag = false;
						break;
					}
				}

				if (flag) {
					this.setPath(linkList.get(0));
				}
			}

		} catch (Exception e) {
			throw e;
		} finally {
			linkList = null;
			elementList = null;
			link = null;
		}
	}

	/**
	 * 根据网元对象，在拓扑中找出对应的node
	 * 
	 * @param siteInst
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private Node findNode(SiteInst siteInst) throws Exception {
		List<Element> elementList = null;
		SiteInst siteInst_node = null;
		Node result_node = null;
		try {
			elementList = tView.getDataBox().getAllElements();

			for (Element element : elementList) {
				if (element instanceof Node) {
					siteInst_node = (SiteInst) element.getUserObject();

					if (siteInst.getSite_Inst_Id() == siteInst_node.getSite_Inst_Id()) {
						result_node = (Node) element;
						break;
					}

				}
			}

		} catch (Exception e) {
			throw e;
		} finally {
			elementList = null;
			siteInst_node = null;
		}
		return result_node;
	}

}

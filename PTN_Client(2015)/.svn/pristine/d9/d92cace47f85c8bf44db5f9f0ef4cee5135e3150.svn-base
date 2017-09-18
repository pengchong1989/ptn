/*
 * EquipmentTopology.java
 *
 * Created on __DATE__, __TIME__
 */

package com.nms.ui.ptn.business.dialog.tunnel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import twaver.Element;
import twaver.Link;
import twaver.Node;
import twaver.PopupMenuGenerator;
import twaver.Port;
import twaver.TDataBox;
import twaver.TUIManager;
import twaver.TView;
import twaver.TWaverConst;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.path.Segment;
import com.nms.db.bean.ptn.path.tunnel.Lsp;
import com.nms.db.bean.system.Field;
import com.nms.db.bean.system.code.Code;
import com.nms.db.enums.ETunnelMenu;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.path.SegmentService_MB;
import com.nms.model.system.FieldService_MB;
import com.nms.model.system.SiteLockService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.SegmentTopoPanel;
import com.nms.ui.manager.TopoAttachment;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysMenu;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.basicinfo.dialog.site.SelectSiteDialog;
import com.nms.ui.ptn.business.dialog.eline.AddElineAllDialog;

/**
 * 
 * @author __USER__
 */
public class EquipmentTopology extends javax.swing.JPanel {

	private static final long serialVersionUID = -4064842245314003518L;
//	private static EquipmentTopology equipmentTopology = null;
	private SegmentTopoPanel segmentTopo = null;
	private PassNode node_a = null;
	private PassNode node_z = null;
	private PassNode node_a_pro = null; // A端保护端�?
	private PassNode node_z_pro = null; // Z端保护端�?
	private PassNode psNode = null;
	private PassNode psNode_protect = null;
	private final List<PassNode> mustPNList = new ArrayList<PassNode>();
	private List<PassNode> protectPNList = new ArrayList<PassNode>();
	private List<Lsp> lspP = null;
	private List<Lsp> lspList_protect = null;
	private  SiteInst siteA = null;
	private  SiteInst siteZ = null;
	private int aPortId=0;
	private int zPortId=0;
	private int aProPortId=0;
	private int zProPortId=0;
	private  Element ElementA = null;
	private  Element ElementZ = null;
	private  List<Element> ElementM = new ArrayList<Element>();
	private  List<Segment> sgMust = new ArrayList<Segment>();
	private  List<Segment> sgproMust = new ArrayList<Segment>();
	private  List<SiteInst> SiteMust = new ArrayList<SiteInst>();
	private AddTunnelPathDialog addTunnelPathDialog = null;
	private  boolean isSgMust = false;//设为必经工作路径后置为true
	private  boolean isMust = false;//设为必经网元后置为true
	private  boolean isproSgMust = false;//设为必经保护路径后置为true

	public int getAProPortId() {
		return aProPortId;
	}

	public void setAProPortId(int proPortId) {
		aProPortId = proPortId;
	}

	public int getZProPortId() {
		return zProPortId;
	}

	public void setZProPortId(int proPortId) {
		zProPortId = proPortId;
	}
	
	public int getAPortId() {
		return aPortId;
	}

	public void setAPortId(int portId) {
		aPortId = portId;
	}

	
	public int getZPortId() {
		return zPortId;
	}

	public void setZPortId(int portId) {
		zPortId = portId;
	}
	public PassNode getPsNode() {
		return psNode;
	}

	public void setPsNode(PassNode psNode) {
		this.psNode = psNode;
	}

	public PassNode getNode_a() {
		return node_a;
	}

	public void setNode_a(PassNode node_a) {
		this.node_a = node_a;
	}

	public PassNode getNode_z() {
		return node_z;
	}

	public void setNode_z(PassNode node_z) {
		this.node_z = node_z;
	}

	/** Creates new form EquipmentTopology */
	public EquipmentTopology(final AddTunnelPathDialog addTunnelPath) {
		super(new BorderLayout());
		try {
			addTunnelPathDialog = addTunnelPath;
			//equipmentTopology = this;
			segmentTopo = new SegmentTopoPanel();
//			segmentTopo.getNetwork().doLayout(TWaverConst.LAYOUT_CIRCULAR);//自动布局
			segmentTopo.getNetwork().setPopupMenuGenerator(new PopupMenuGenerator() {
				@Override
				public JPopupMenu generate(TView tview, MouseEvent mouseEvent) {

					JPopupMenu menu = new JPopupMenu();

					if (tview.getDataBox().getSelectionModel().isEmpty()) {
						// 添加搜索网元菜单
						creatMenu(addTunnelPath,menu, ResourceUtil.srcStr(StringKeysMenu.MENU_SELECTSITE), ETunnelMenu.SERACH.getValue(), null);
					} else {
						final Element element = tview.getDataBox().getLastSelectedElement();
						if (element instanceof Link) {
							creatMenu(addTunnelPath,menu, ResourceUtil.srcStr(StringKeysTip.TIP_SETMUSTPATH), ETunnelMenu.MUSTSEGMENT.getValue(), element);
							creatMenu(addTunnelPath,menu, ResourceUtil.srcStr(StringKeysTip.TIP_CANCELSETMUSTPATH), ETunnelMenu.CANCELMUSTSEGMENT.getValue(),element);
							creatMenu(addTunnelPath,menu, ResourceUtil.srcStr(StringKeysTip.TIP_SETPROMUSTPATH), ETunnelMenu.MUSTPROSEGMENT.getValue(), element);
							creatMenu(addTunnelPath,menu, ResourceUtil.srcStr(StringKeysTip.TIP_CANCELPROSETMUSTPATH), ETunnelMenu.CANCELPROMUSTSEGMENT.getValue(),element);
						}
						if (element instanceof Node) {

							Node nodeSelect = (Node) element;

							// 如果此网元没有任何连接的�?就没有菜�?
							if (nodeSelect.getAllLinks() == null) {
								return null;
							}
							// 获取dialog中tunnel类型的�?
							
							Code code_type = (Code) ((ControlKeyValue) addTunnelPathDialog.getCmbType().getSelectedItem()).getObject();
							if (null == nodeSelect.getBusinessObject()) {

								// 设置选择A端菜�?
								creatMenu(addTunnelPath,menu, ResourceUtil.srcStr(StringKeysMenu.MENU_SETA), ETunnelMenu.SELECTA.getValue(), element);

								// 设置选择Z端菜�?
								creatMenu(addTunnelPath,menu, ResourceUtil.srcStr(StringKeysMenu.MENU_SETZ), ETunnelMenu.SELECTZ.getValue(), element);

								// 设置选择必经网元菜单
								creatMenu(addTunnelPath,menu, ResourceUtil.srcStr(StringKeysMenu.MENU_SETMUSTPASSTHROUGH), ETunnelMenu.MUSTSITE.getValue(), element);
							} else {
								// 如果右键选中网元不是A不是Z 才能可以选择保护
								if (!"A".equals(nodeSelect.getBusinessObject()) && !"Z".equals(nodeSelect.getBusinessObject())
										&& !"between".equals(nodeSelect.getBusinessObject())) {

									// tunnel类型?:1保护
									if ("2".equals(code_type.getCodeValue())) {

										if (element.getBusinessObject().toString().indexOf("/") != -1) { // 如果既是保护网元又是必经网元
											// 取消设置菜单
											creatMenu(addTunnelPath,menu, ResourceUtil.srcStr(StringKeysMenu.MENU_CANEL_CONFIG),
													ETunnelMenu.CANELCONFIG.getValue(), element);
											// 取消保护菜单
											creatMenu(addTunnelPath,menu, ResourceUtil.srcStr(StringKeysMenu.MENU_CANEL_PROTECT), ETunnelMenu.CANELPROTECT
													.getValue(), element);
										} else {
											if (!ResourceUtil.srcStr(StringKeysObj.STRING_PROTECT_SITE).equals(element.getBusinessObject())) {
												// 设置保护菜单
												creatMenu(addTunnelPath,menu, ResourceUtil.srcStr(StringKeysMenu.MENU_CONFIG_PROTECT), ETunnelMenu.PROTECT
														.getValue(), element);

												// 取消设置菜单
												creatMenu(addTunnelPath,menu, ResourceUtil.srcStr(StringKeysMenu.MENU_CANEL_CONFIG), ETunnelMenu.CANELCONFIG
														.getValue(), element);
											} else {

												// 设置选择必经网元菜单
												creatMenu(addTunnelPath,menu, ResourceUtil.srcStr(StringKeysMenu.MENU_SETMUSTPASSTHROUGH), ETunnelMenu.MUSTSITE
														.getValue(), element);

												// 取消保护菜单
												creatMenu(addTunnelPath,menu, ResourceUtil.srcStr(StringKeysMenu.MENU_CANEL_PROTECT), ETunnelMenu.CANELPROTECT
														.getValue(), element);
											}
										}

									} else {
										// 取消设置菜单
										creatMenu(addTunnelPath,menu, ResourceUtil.srcStr(StringKeysMenu.MENU_CANEL_CONFIG), ETunnelMenu.CANELCONFIG.getValue(),
												element);
									}

								} else {
									// 取消设置菜单
									creatMenu(addTunnelPath,menu, ResourceUtil.srcStr(StringKeysMenu.MENU_CANEL_CONFIG), ETunnelMenu.CANELCONFIG.getValue(),
											element);
								}
							}

						}
						// 设置标签。目前只支持自动分配标签
						// else if (element instanceof Link) {
						// JMenuItem jMenuItem = new
						// JMenuItem(MenuTipMessageUtil.SETLABLE);
						// jMenuItem.addActionListener(new
						// java.awt.event.ActionListener() {
						// public void
						// actionPerformed(java.awt.event.ActionEvent
						// evt) {
						// setLabel(element);
						// }
						// });
						// menu.add(jMenuItem);
						// }
					}

					return menu;
				}
				
			});
			
			
			segmentTopo.getNetwork().addElementClickedActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Element element = (Element) e.getSource();
					if(element!=null&&element instanceof Link){
						if(element.getUserObject()!=null&&element.getBusinessObject()==null){
							TUIManager.registerAttachment("SegmenttopoTitle", TopoAttachment.class,1, (int) element.getX(), (int) element.getY());
							Segment sgmust =  (Segment)element.getUserObject();
							element.setBusinessObject(sgmust.getNAME());
							element.addAttachment("SegmenttopoTitle");
						}else{
							element.removeAttachment("SegmenttopoTitle");
							element.setBusinessObject(null);
						}    
					}
				}
			});

			this.add(segmentTopo);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 清除保护
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	public void clearProtect(AddTunnelPathDialog dialog) {
		this.protectPNList = new ArrayList<PassNode>();

		List<Element> elementList = segmentTopo.getBox().getAllElements();
		for (Element element : elementList) {
			if (element instanceof Node) {
				if (element.getBusinessObject() != null) {
					if (element.getBusinessObject().toString().indexOf("/") == -1) {
						if (ResourceUtil.srcStr(StringKeysObj.STRING_PROTECT_SITE).equals(element.getBusinessObject())) {
							element.removeAttachment("topoTitle");
							element.setBusinessObject(null);
						}
					} else {
						element.removeAttachment("topoTitle");
						element.setBusinessObject(null);

						element.setBusinessObject(ResourceUtil.srcStr(StringKeysObj.STRING_MUST_PASS_SITE));
						element.removeAttachment("topoTitle");
					}
				}
			}
		}
		// 设置完路径后要做路径检?
		dialog.setHasCheck(false);
		// 设置所有link为绿色。需要重新检查路?
		this.setLinkColor(Color.GREEN);
	}

	/**
	 * 
	 * 设置菜单
	 * 
	 * @author kk
	 * 
	 * @param menu
	 *            菜单对象
	 * @param menuText
	 *            菜单显示语言
	 * @param type
	 *            菜单类型
	 * @param element
	 *            点击网元对象
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private void creatMenu(final AddTunnelPathDialog dialog,JPopupMenu menu, String menuText, final int type, final Element element) {
		
		JMenuItem jMenuItem = new JMenuItem(menuText);
		jMenuItem.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				if (ETunnelMenu.SERACH.getValue() == type) { // 搜索网元
					try {
						SelectSiteDialog selectsitedialog = new SelectSiteDialog(segmentTopo.getNetwork(), true);
						selectsitedialog.setLocation(UiUtil.getWindowWidth(selectsitedialog.getWidth()), UiUtil.getWindowHeight(selectsitedialog.getHeight()));
						selectsitedialog.setVisible(true);
					} catch (Exception e) {
						ExceptionManage.dispose(e,this.getClass());
					}
				} else if (ETunnelMenu.SELECTA.getValue() == type) {
					// 返回A网元
					setSiteA((SiteInst) element.getUserObject());
					setNodeAttachment("A", element);
					ElementA = element;
					addTunnelPathDialog.setASite(getSiteA().getCellId());
					// 如果是eline界面 给eline对象中的网元对象赋
					if (addTunnelPathDialog instanceof AddElineAllDialog) {
						AddElineAllDialog addElineAllDialog = (AddElineAllDialog) addTunnelPathDialog;
						addElineAllDialog.setSiteInst_a(getSiteA());
					}
				} else if (ETunnelMenu.SELECTZ.getValue() == type) {
					// 返回Z网元
					setSiteZ((SiteInst) element.getUserObject());
					setNodeAttachment("Z", element);
					setElementZ(element)  ;
					addTunnelPathDialog.setZSite(getSiteZ().getCellId());
					// 如果是eline界面 给eline对象中的网元对象赋
					if (addTunnelPathDialog instanceof AddElineAllDialog) {
						AddElineAllDialog addElineAllDialog = (AddElineAllDialog) addTunnelPathDialog;
						addElineAllDialog.setSiteInst_z(getSiteZ());
					}
				} else if (ETunnelMenu.MUSTSITE.getValue() == type) {
					SiteInst site = null;
					// 设为必经
					if (getSiteA() == null || getSiteZ() == null) {
						mustTip();
						return;
					}
					site = (SiteInst) element.getUserObject();
					setNodeAttachment("between", element);
					ElementM.add(element);
					SiteMust.add(site);
					isMust = true;
				} else if (ETunnelMenu.PROTECT.getValue() == type) { // 设为保护
					if (getNode_a_pro() == null || getNode_z_pro() == null) {
						DialogBoxUtil.errorDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_AZ_PROTECT_BEFORE));
						return;
					}
					showEquipmentDialog((SiteInst) element.getUserObject(), "protect", element);
				} else if (ETunnelMenu.CANELPROTECT.getValue() == type) { // 取消保护
					canelConfig(element, "protect",dialog);
				} else if (ETunnelMenu.CANELCONFIG.getValue() == type) { // 取消设置
					if ("A".equals(element.getBusinessObject()) || "Z".equals(element.getBusinessObject())) {
						canelConfig(element, element.getBusinessObject().toString(),dialog);
					} else {
						canelConfig(element, "between",dialog);
					}

				} else if (ETunnelMenu.SELECTPROTECT.getValue() == type) {
					showEquipmentDialog((SiteInst) element.getUserObject(), "protectport", element);
				} else if (ETunnelMenu.MUSTSEGMENT.getValue() == type) {
					// 设为工作必经段
					Segment sgmust = (Segment) element.getUserObject();
					((Link) element).putLinkColor(new Color(75, 0, 130));
					sgMust.add(sgmust);
					isSgMust = true;
				} else if (ETunnelMenu.CANCELMUSTSEGMENT.getValue() == type) {
					// 取消工作必经段
					((Link) element).putLinkColor(Color.GREEN);
					sgMust.clear();
					isSgMust = false;
				}else if (ETunnelMenu.MUSTPROSEGMENT.getValue() == type) {
					// 设为保护必经段
					Segment sgmust = (Segment) element.getUserObject();
					((Link) element).putLinkColor(Color.YELLOW);
					sgproMust.add(sgmust);
					isproSgMust = true;
				} else if (ETunnelMenu.CANCELPROMUSTSEGMENT.getValue() == type) {
					// 取消设为保护必经段
					((Link) element).putLinkColor(Color.GREEN);
					sgproMust.clear();
					isproSgMust = false;
				}

			}
		});
		menu.add(jMenuItem);
	}

	public void removePopMenu() {
		segmentTopo.getNetwork().setPopupMenuGenerator(null);
	}

	/**
	 * 取消设置菜单事件
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private void canelConfig(Element element, String type,AddTunnelPathDialog dialog) {

		if ("A".equals(type)) {
			this.setNode_a(null);
			addTunnelPathDialog.setPortInst_A(null);
			addTunnelPathDialog.setASite("");
			this.setNode_a_pro(null);
			this.setSiteA(null);
			// 移除提示?
			element.removeAttachment("topoTitle");
			element.setBusinessObject(null);
		} else if ("Z".equals(type)) {
			this.setNode_z(null);
			addTunnelPathDialog.setPortInst_A(null);
			addTunnelPathDialog.setZSite("");
			this.setNode_z_pro(null);
			this.setSiteZ(null);
			// 移除提示?
			element.removeAttachment("topoTitle");
			element.setBusinessObject(null);
		} else if ("between".equals(type)) {
			for (PassNode passNode : this.mustPNList) {
				if (passNode.getElement() == element) {
					this.mustPNList.remove(passNode);
					break;
				}
			}
			isMust = false;
			SiteMust.clear();
			this.removeBusinessObjct(element, ResourceUtil.srcStr(StringKeysObj.STRING_PROTECT_SITE));
		} else if ("protect".equals(type)) {
			for (PassNode passNode : this.protectPNList) {
				if (passNode.getElement() == element) {
					this.protectPNList.remove(passNode);
					break;
				}
			}
			this.removeBusinessObjct(element, ResourceUtil.srcStr(StringKeysObj.STRING_MUST_PASS_SITE));
		}

		// 设置完路径后要做路径检?
		dialog.setHasCheck(false);
		// 设置所有link为绿色。需要重新检查路?
		this.setLinkColor(Color.GREEN);
	}

	/**
	 * 移除提示文本
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	public  void removeBusinessObjct(Element element, String tip) {

		if (element.getBusinessObject().toString().indexOf("/") == -1) {
			// 移除提示?
			element.removeAttachment("topoTitle");
			element.setBusinessObject(null);
		} else {
			// 移除提示?
			element.removeAttachment("topoTitle");
			element.setBusinessObject(null);

			element.setBusinessObject(tip);
			element.addAttachment("topoTitle");
		}

	}

	private void mustTip() {
		DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_AZ_CONFIG_BEFORE));
	}

	/**
	 * 设置AZ端的title
	 */
	@SuppressWarnings("unchecked")
	public void setNodeAttachment(String type, Element selectElement) {
		List<Element> elementList = segmentTopo.getBox().getAllElements();
		if ("A".equals(type) || "Z".equals(type)) {
			for (Element element : elementList) {
				if (element instanceof Node) {
					if (element.getBusinessObject() != null) {
						if (element.getBusinessObject().equals(type)) {
							element.removeAttachment("topoTitle");
							element.setBusinessObject(null);
						}
					}
				}
			}
			selectElement.setBusinessObject(type);
		} else {

			String text = null;
			if (null == selectElement.getBusinessObject()) {
				text = type;
			} else {
				text = selectElement.getBusinessObject() + "/" + type;
				selectElement.removeAttachment("topoTitle");
				selectElement.setBusinessObject(null);
			}

			if (text.equals("between")) {
				text = ResourceUtil.srcStr(StringKeysObj.STRING_MUST_PASS_SITE);
			}

			selectElement.setBusinessObject(text);
		}

		selectElement.addAttachment("topoTitle");
	}

	/**
	 * 弹出设为A、Z、必经的窗口
	 */
	private void showEquipmentDialog(SiteInst siteInst, final String type, final Element siteElement) {
		SiteLockService_MB siteLockServiceMB = null;
		try {
			siteLockServiceMB = (SiteLockService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITELOCK);
			boolean isLock = false;
			if (isLock) {
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_SITE_LOCK));
				return;
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(siteLockServiceMB);
		}

		final EquipmentDialog equipmentDialog = new EquipmentDialog(true, siteInst);
		equipmentDialog.setLocation(UiUtil.getWindowWidth(equipmentDialog.getWidth()), UiUtil.getWindowHeight(equipmentDialog.getHeight()));
		equipmentDialog.getNetwork().setPopupMenuGenerator(new PopupMenuGenerator() {
			@Override
			public JPopupMenu generate(TView tview, MouseEvent mouseEvent) {

				JPopupMenu menu = new JPopupMenu();
				final Element portElement = tview.getDataBox().getLastSelectedElement();

				if (portElement instanceof Port) {
					final PortInst portInst = (PortInst) portElement.getUserObject();
					if (!portInst.getPortType().equals("NNI")) {
						return null;
					}

					if (portInst.getIsEnabled_code() == 0) {// 端口不使�?
						return null;
					}

					if ("between".equals(type) || "protect".equals(type)) {
						JMenuItem jMenuItemA = new JMenuItem(ResourceUtil.srcStr(StringKeysMenu.MENU_SETA));
						jMenuItemA.addActionListener(new java.awt.event.ActionListener() {
							@Override
							public void actionPerformed(java.awt.event.ActionEvent evt) {
								try {
									setport(portInst, "NNI", type, siteElement, equipmentDialog, "A", portElement);
								} catch (Exception e) {
									ExceptionManage.dispose(e,this.getClass());
								}
							}
						});
						menu.add(jMenuItemA);

						JMenuItem jMenuItemZ = new JMenuItem(ResourceUtil.srcStr(StringKeysMenu.MENU_SETZ));
						jMenuItemZ.addActionListener(new java.awt.event.ActionListener() {
							@Override
							public void actionPerformed(java.awt.event.ActionEvent evt) {
								try {
									setport(portInst, "NNI", type, siteElement, equipmentDialog, "Z", portElement);
								} catch (Exception e) {
									ExceptionManage.dispose(e,this.getClass());
								}
							}
						});
						menu.add(jMenuItemZ);
					} else {
						JMenuItem jMenuItemSelect = new JMenuItem(ResourceUtil.srcStr(StringKeysMenu.MENU_CONFIG));
						jMenuItemSelect.addActionListener(new java.awt.event.ActionListener() {
							@Override
							public void actionPerformed(java.awt.event.ActionEvent evt) {
								try {
									setport(portInst, "NNI", type, siteElement, equipmentDialog, null, null);
								} catch (Exception e) {
									ExceptionManage.dispose(e,this.getClass());
								}
							}
						});
						menu.add(jMenuItemSelect);
					}

					return menu;
				} else {
					return null;
				}
			}
		});
		equipmentDialog.setVisible(true);
	}

	private void setport(PortInst portInst, String portType, final String type, final Element siteElement, final EquipmentDialog equipmentDialog,
			final String mustType, final Element portElement) {
		if (portInst.getIsOccupy() == 0) {
			DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_PORT_UNUNITED));
			return;
		}
		try {
			addTunnelPathDialog.getTunnel().getPortInstList().add(portInst);

			if (type.equals("between")) {
				if (this.portRepeat(type, portInst)) {
					DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_JOB_PROJECT_PORT));
				} else {
					this.selectNode(mustType, portElement, portInst, siteElement, equipmentDialog);

				}
			} else if ("protect".equals(type)) {
				if (this.portRepeat(type, portInst)) {
					DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_JOB_PROJECT_PORT));
				} else {
					this.selectProtectNode(mustType, portElement, portInst, siteElement, equipmentDialog);
				}
			} else if ("protectport".equals(type)) {

				if (this.getNode_a().getElement() == siteElement) {

					if (this.getNode_a().getaPort().getPortId() == portInst.getPortId()) {
						DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_JOB_PROJECT_PORT));
						return;
					} else {
						this.setNode_a_pro(new PassNode(siteElement, portInst, null));
						equipmentDialog.dispose();
					}

				} else {
					if (this.getNode_z().getzPort().getPortId() == portInst.getPortId()) {
						DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_JOB_PROJECT_PORT));
						return;
					} else {
						this.setNode_z_pro(new PassNode(siteElement, null, portInst));
						equipmentDialog.dispose();
					}
				}

			} else {
				SiteInst siteInst = (SiteInst) siteElement.getUserObject();
				this.setPath(siteInst, portInst, type);
				this.setNode(type, portInst, siteElement);
				// this.createFirstLink();
				equipmentDialog.dispose();
			}
			// 设置完路径后要做路径检�?
			addTunnelPathDialog.setHasCheck(false);
			// 设置所有link为绿色。需要重新检查路�?
			this.setLinkColor(Color.GREEN);

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 
	 * 验证端口是否重复
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private boolean portRepeat(String type, PortInst portInst) throws Exception {

		List<PassNode> passNodeList = new ArrayList<PassNode>();
		boolean flag = false;
		// 如果是必经网元，验证保护端口集合是否存在 否则验证毕竟端口集合
		if (type.equals("between")) {
			passNodeList.addAll(this.protectPNList);
		} else {
			passNodeList.addAll(this.mustPNList);
		}

		// 端口集合元素? 说明不存?
		if (passNodeList.size() == 0) {
			flag = false;
		} else {
			for (PassNode passNode : passNodeList) {
				if (passNode.getaPort().getPortId() == portInst.getPortId()) {
					flag = true;
					break;
				}
				if (passNode.getzPort().getPortId() == portInst.getPortId()) {
					flag = true;
					break;
				}
			}
		}

		return flag;
	}

	/**
	 * 设置link的颜�?
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	@SuppressWarnings("unchecked")
	public  void setLinkColor(Color color) {
		TDataBox box = segmentTopo.getBox();
		List<Element> elementList = box.getAllElements();

		for (int i = elementList.size() - 1; i >= 0; i--) {
			Element element = elementList.get(i);
			if (element instanceof Link) {
				((Link) element).setLinkType(TWaverConst.LINE_TYPE_DEFAULT);
				((Link) element).putLinkColor(color);
				((Link) element).putLinkFlowingWidth(3);
				((Link) element).putLinkWidth(3);
			}
		}
	}

	/**
	 * 给tunnel面板上的控件赋�?
	 * 
	 * @param siteInst
	 * @param portInst
	 * @param type
	 */
	private void setPath(SiteInst siteInst, PortInst portInst, String type) {
		Field field = null;
		FieldService_MB fieldServiceMB = null;
		List<Field> fieldList = null;
		String text = null;
		try {
			field = new Field();
			field.setId(siteInst.getFieldID());
			fieldServiceMB = (FieldService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Field);
			fieldList = fieldServiceMB.select(field);
			if (fieldList == null || fieldList.size() != 1) {
				throw new Exception(ResourceUtil.srcStr(StringKeysTip.TIP_NOT_FULL));
			}
			text = fieldList.get(0).getFieldName() + "/" + siteInst.getCellId() + "/" + portInst.getPortName();
			
			if (type.equals("A")) {
				addTunnelPathDialog.setPortInst_A(portInst);
				addTunnelPathDialog.setASite(text);
			} else if (type.equals("Z")) {
				addTunnelPathDialog.setPortInst_Z(portInst);
				addTunnelPathDialog.setZSite(text);
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(fieldServiceMB);
		}
	}

	/**
	 * 给node赋�?
	 * 
	 * @param type
	 * @param portInst
	 * @param element
	 * @throws Exception
	 */
	private void setNode(String type, PortInst portInst, Element element) throws Exception {
		try {
			if (type.equals("A")) {
				this.setNode_a(new PassNode(element, portInst, null));
			} else {
				this.setNode_z(new PassNode(element, null, portInst));
			}
			this.setNodeAttachment(type, element);

		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 必经网元选择node
	 * 
	 * @throws Exception
	 */
	private void selectNode(String type, Element element, PortInst portInst, Element elementPanent, EquipmentDialog equipmentDialog) throws Exception {
		try {
			this.setAttachment(type);
			element.setBusinessObject(type);
			element.addAttachment("topoTitle");

			if (psNode == null)
				psNode = new PassNode(null, null, null);

			if (type.equals("A")) {
				psNode.setElement(elementPanent);
				psNode.setaPort(portInst);

			} else {
				psNode.setElement(elementPanent);
				psNode.setzPort(portInst);
			}

			if (psNode.getaPort() != null && psNode.getzPort() != null) {

				mustPNList.add(psNode);
				psNode = null;
				equipmentDialog.dispose();
				this.setNodeAttachment(ResourceUtil.srcStr(StringKeysObj.STRING_MUST_PASS_SITE), elementPanent);
			}

		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 保护网元选择node
	 * 
	 * @throws Exception
	 */
	private void selectProtectNode(String type, Element element, PortInst portInst, Element elementPanent, EquipmentDialog equipmentDialog)
			throws Exception {
		try {
			this.setAttachment(type);
			element.setBusinessObject(type);
			element.addAttachment("topoTitle");

			if (this.psNode_protect == null)
				this.psNode_protect = new PassNode(null, null, null);

			if (type.equals("A")) {
				this.psNode_protect.setElement(elementPanent);
				this.psNode_protect.setaPort(portInst);

			} else {
				this.psNode_protect.setElement(elementPanent);
				this.psNode_protect.setzPort(portInst);
			}

			if (this.psNode_protect.getaPort() != null && this.psNode_protect.getzPort() != null) {

				this.protectPNList.add(this.psNode_protect);
				this.psNode_protect = null;
				equipmentDialog.dispose();
				this.setNodeAttachment(ResourceUtil.srcStr(StringKeysObj.STRING_PROTECT_SITE), elementPanent);
			}

		} catch (Exception e) {
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	private void setAttachment(String type) {
		List<Element> elementList = segmentTopo.getBox().getAllElements();

		for (Element element : elementList) {
			if (element instanceof Port) {
				if (element.getBusinessObject() != null) {
					if (type.equals(element.getBusinessObject())) {
						element.removeAttachment("topoTitle");
						element.setBusinessObject(null);
					}
				}
			}
		}
	}

	
	/**
	 *查询并返回完整路径的�?
	 * 
	 * @return
	 */
	private List<Segment> checkAndGetPathWithSegment(List<Segment> list, String type) {

		List<PassNode> passNodes = new ArrayList<PassNode>();
		List<Segment> link = new ArrayList<Segment>();
		PortInst Aport = null;
		PortInst Zport = null;
		PortInst startPort = null;
		PortInst endPort = null;
		Segment segment = null;
		try {
			if ("between".equals(type)) {
				passNodes.addAll(mustPNList);
				Aport = this.getNode_a().getaPort();
				Zport = this.getNode_z().getzPort();
			} else {
				passNodes.addAll(this.protectPNList);
				Aport = this.getNode_a_pro().getaPort();
				Zport = this.getNode_z_pro().getzPort();
			}
			startPort = Aport;
			while (passNodes.size() > 0) {
				segment = getSegmentByport(startPort, list);
				if (segment == null) // there is no segment with the port of
					// startPort
					return null;
				link.add(segment);

				endPort = getPortinstBySegment(startPort, segment);
				startPort = getPeerportInPassnode(endPort, passNodes);
				if (startPort == null) // peer site is not in mustPNList
					return null;
			}
			segment = existSegmetwithPorts(startPort, Zport, list);// judge the
		
			if (segment != null)
				link.add(segment);
			else
				return null;
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			Aport = null;
			Zport = null;
			startPort = null;
			endPort = null;
			segment = null;
		}
		return link;
	}

	/**
	 *@return 两端口对应的�?
	 */
	private Segment existSegmetwithPorts(PortInst startPort, PortInst zport, List<Segment> list) {
		for (Segment obj : list) {
			if ((obj.getAPORTID() == startPort.getPortId() && obj.getASITEID() == startPort.getSiteId() && obj.getZPORTID() == zport.getPortId() && obj
					.getZSITEID() == zport.getSiteId())
					|| (obj.getZPORTID() == startPort.getPortId() && obj.getZSITEID() == startPort.getSiteId()
							&& obj.getAPORTID() == zport.getPortId() && obj.getASITEID() == zport.getSiteId())) {
				return obj;
			}
		}
		return null;
	}

	/**
	 * 通过端口找必经网元的另一出端?
	 */
	private PortInst getPeerportInPassnode(PortInst endPort, List<PassNode> passNodes) {
		for (PassNode obj : passNodes) {
			if (obj.getaPort().getPortId() == endPort.getPortId() && obj.getaPort().getSiteId() == endPort.getSiteId()) {
				passNodes.remove(obj);
				return obj.getzPort();
			} else if (obj.getzPort().getPortId() == endPort.getPortId() && obj.getzPort().getSiteId() == endPort.getSiteId()) {
				passNodes.remove(obj);
				return obj.getaPort();
			}
		}
		return null;
	}

	/**
	 * 通过端口返回段的另一端口
	 */
	private PortInst getPortinstBySegment(PortInst startPort, Segment segment) {
		PortInst port = new PortInst();
		if (segment.getASITEID() == startPort.getSiteId() && segment.getAPORTID() == startPort.getPortId()) {
			port.setPortId(segment.getZPORTID());
			port.setSiteId(segment.getZSITEID());
		} else if (segment.getZSITEID() == startPort.getSiteId() && segment.getZPORTID() == startPort.getPortId()) {
			port.setPortId(segment.getAPORTID());
			port.setSiteId(segment.getASITEID());
		}
		return port;
	}

	/**
	 * 查找端口所承载的段
	 */
	private Segment getSegmentByport(PortInst startPort, List<Segment> list) {
		for (Segment obj : list) {
			if ((obj.getASITEID() == startPort.getSiteId() && obj.getAPORTID() == startPort.getPortId())
					|| (obj.getZSITEID() == startPort.getSiteId() && obj.getZPORTID() == startPort.getPortId())) {
				return obj;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public void drawTopoByPassLinks(List<Segment> segmentList) throws Exception {
		TDataBox box = segmentTopo.getBox();
		List<Element> elementList = box.getAllElements();
		PortInst startnode = this.node_a.getaPort();
		PortInst endnode = new PortInst();
		lspP = new ArrayList<Lsp>();
		Lsp lsp = null;

		for (Segment obj : segmentList) {
			for (int i = elementList.size() - 1; i >= 0; i--) {
				Element element = elementList.get(i);
				if (element instanceof Link && linkSimilarWithSegment((Link) element, obj)) {
					
					((Link) element).setLinkType(TWaverConst.LINE_TYPE_DEFAULT);
					((Link) element).putLinkColor(Color.RED);
					((Link) element).putLinkFlowingWidth(3);
					((Link) element).putLinkWidth(3);

					lsp = getLspParticular(obj, startnode, endnode);
					((Link) element).setUserObject(lsp);
					startnode = getStartnode(endnode);
					lspP.add(lsp);
					break;
				}
			}
		}
	}

	/**
	 * 检查保护路径后 路径变色
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	@SuppressWarnings("unchecked")
	private void drawTopoByPassLinks_protect(List<Segment> segmentList) throws Exception {
		TDataBox box = segmentTopo.getBox();
		List<Element> elementList = box.getAllElements();
		PortInst startnode = this.getNode_a_pro().getaPort();
		PortInst endnode = new PortInst();
		this.lspList_protect = new ArrayList<Lsp>();
		Lsp lsp = null;

		for (Segment obj : segmentList) {
			for (int i = elementList.size() - 1; i >= 0; i--) {
				Element element = elementList.get(i);
				if (element instanceof Link && linkSimilarWithSegment((Link) element, obj)) {
					((Link) element).setLinkType(TWaverConst.LINE_TYPE_DEFAULT);
					((Link) element).putLinkColor(Color.yellow);
					((Link) element).putLinkFlowingWidth(3);
					((Link) element).putLinkWidth(3);

					lsp = getLspParticular(obj, startnode, endnode);
					((Link) element).setUserObject(lsp);
					startnode = getStartnode_protect(endnode);
					this.lspList_protect.add(lsp);
					break;
				}
			}
		}
	}

	private boolean linkSimilarWithSegment(Link link, Segment seg2) {
		if (link.getUserObject() instanceof Segment) {
			Segment seg1 = (Segment) link.getUserObject();
			if ((seg1.getAPORTID() == seg2.getAPORTID() && seg1.getASITEID() == seg2.getASITEID() && seg1.getZPORTID() == seg2.getZPORTID() && seg1
					.getZSITEID() == seg2.getZSITEID())
					|| (seg1.getAPORTID() == seg2.getZPORTID() && seg1.getASITEID() == seg2.getZSITEID() && seg1.getZPORTID() == seg2.getAPORTID() && seg1
							.getZSITEID() == seg2.getASITEID()))
				return true;
		} else if (link.getUserObject() instanceof Lsp) {
			Lsp lspp = (Lsp) link.getUserObject();
			if ((lspp.getAPortId() == seg2.getAPORTID() && lspp.getASiteId() == seg2.getASITEID() && lspp.getZPortId() == seg2.getZPORTID() && lspp
					.getZSiteId() == seg2.getZSITEID())
					|| (lspp.getAPortId() == seg2.getZPORTID() && lspp.getASiteId() == seg2.getZSITEID() && lspp.getZPortId() == seg2.getAPORTID() && lspp
							.getZSiteId() == seg2.getASITEID()))
				return true;
		}

		return false;
	}

	private PortInst getStartnode(PortInst endtnode) {
		for (PassNode node : mustPNList) {
			if (node.getaPort().getSiteId() == endtnode.getSiteId() && node.getaPort().getPortId() == endtnode.getPortId())
				return node.getzPort();
			else if (node.getzPort().getSiteId() == endtnode.getSiteId() && node.getzPort().getPortId() == endtnode.getPortId())
				return node.getaPort();
		}
		return null;
	}

	/**
	 * 获取端口对象 保护时用
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private PortInst getStartnode_protect(PortInst endtnode) {
		for (PassNode node : this.protectPNList) {
			if (node.getaPort().getSiteId() == endtnode.getSiteId() && node.getaPort().getPortId() == endtnode.getPortId())
				return node.getzPort();
			else if (node.getzPort().getSiteId() == endtnode.getSiteId() && node.getzPort().getPortId() == endtnode.getPortId())
				return node.getaPort();
		}
		return null;
	}

	private Lsp getLspParticular(Segment obj, PortInst startnode, PortInst endnode) throws Exception {
		SiteService_MB siteServiceMB=null;
		Lsp lspP = new Lsp();
		try {
			siteServiceMB=(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			lspP.setAPortId(startnode.getPortId());
			lspP.setASiteId(startnode.getSiteId());
			lspP.setZoppositeId(siteServiceMB.getSiteID(startnode.getSiteId()));
			lspP.setSegmentId(obj.getId());
			
			if (obj.getASITEID() == startnode.getSiteId()) {
				lspP.setAoppositeId(siteServiceMB.getSiteID(obj.getZSITEID()));
				lspP.setZPortId(obj.getZPORTID());
				lspP.setZSiteId(obj.getZSITEID());
				endnode.setPortId(obj.getZPORTID());
				endnode.setSiteId(obj.getZSITEID());
			} else {
				lspP.setAoppositeId(siteServiceMB.getSiteID(obj.getASITEID()));
				lspP.setZPortId(obj.getAPORTID());
				lspP.setZSiteId(obj.getASITEID());
				endnode.setPortId(obj.getAPORTID());
				endnode.setSiteId(obj.getASITEID());
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(siteServiceMB);
		}
		return lspP;
	}
	
	/**
	 * 查找指定端口的所有段
	 * @param aportId
	 * @param zPortId
	 * @return
	 */
	public  List<Segment> getAllSegmentFromTopo(int aportId, int zPortId) {
		List<Segment> segmentList = null;
		List<Segment> segs = null;
		try {
			segmentList = getAllSegmentFromTopo();
			if(aportId==0 || zPortId==0)
			{
				return segmentList;
			}
			segs = new ArrayList<Segment>();
			for(Segment sg:segmentList)
			{
				if(sg.getAPORTID()==aportId && sg.getZPORTID() == zPortId)
				{
					segs.add(sg);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,EquipmentTopology.class);
		}
		return segs;
	}

	public  List<Segment> getAllSegmentFromTopo() {
		SegmentService_MB serviceSegmentServiceMB = null;
		List<Segment> segmentList = null;
		try {
			serviceSegmentServiceMB = (SegmentService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SEGMENT);
			segmentList = serviceSegmentServiceMB.select();
		} catch (Exception e) {
			ExceptionManage.dispose(e,EquipmentTopology.class);
		} finally {
			UiUtil.closeService_MB(serviceSegmentServiceMB);
		}
		return segmentList;
	}

	public List<Segment> checkAndGetPathWithSegment() {
		return checkAndGetPathWithSegment(getAllSegmentFromTopo(), "between");
	}

	public boolean checkAnddrawTopo() throws Exception {
		List<Segment> segmentList = checkAndGetPathWithSegment(getAllSegmentFromTopo(), "between");
		if (segmentList != null) {
			drawTopoByPassLinks(segmentList);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * 验证保护路径
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */

	public boolean checkAnddrawTopo_protect() throws Exception {
		List<Segment> segmentList = checkAndGetPathWithSegment(getAllSegmentFromTopo(), "protect");
		if (segmentList != null) {
			this.drawTopoByPassLinks_protect(segmentList);
			return true;
		} else {
			return false;
		}
	}

	public  SegmentTopoPanel getSegmentTopo() {
		return segmentTopo;
	}

//	public static void setSegmentTopo(SegmentTopoPanel segmentTopo) {
//		EquipmentTopology.segmentTopo = segmentTopo;
//	}

	public List<Lsp> getLspP() {
		return lspP;
	}

	public void setLspP(List<Lsp> lspP) {
		this.lspP = lspP;
	}

	public PassNode getNode_a_pro() {
		return node_a_pro;
	}

	public void setNode_a_pro(PassNode node_a_pro) {
		this.node_a_pro = node_a_pro;
	}

	public PassNode getNode_z_pro() {
		return node_z_pro;
	}

	public void setNode_z_pro(PassNode node_z_pro) {
		this.node_z_pro = node_z_pro;
	}

	public List<Lsp> getLspList_protect() {
		return lspList_protect;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// }// </editor-fold>
	// GEN-END:initComponents
	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	// End of variables declaration//GEN-END:variables
	public class PassNode {

		private Element element;
		private PortInst aPort;
		private PortInst zPort;

		public PassNode(Element element, PortInst aPort, PortInst zPort) {
			super();
			this.element = element;
			this.aPort = aPort;
			this.zPort = zPort;
		}

		public Element getElement() {
			return element;
		}

		public void setElement(Element element) {
			this.element = element;
		}

		public PortInst getaPort() {
			return aPort;
		}

		public void setaPort(PortInst aPort) {
			this.aPort = aPort;
		}

		public PortInst getzPort() {
			return zPort;
		}

		public void setzPort(PortInst zPort) {
			this.zPort = zPort;
		}

	}

	public SiteInst getSiteA() {
		return siteA;
	}

	public void setSiteA(SiteInst siteA) {
		this.siteA = siteA;
	}

	public SiteInst getSiteZ() {
		return siteZ;
	}

	public void setSiteZ(SiteInst siteZ) {
		this.siteZ = siteZ;
	}

	public List<Segment> getSgMust() {
		return sgMust;
	}

	public void setSgMust(List<Segment> sgMust) {
		this.sgMust = sgMust;
	}

	public List<SiteInst> getSiteMust() {
		return SiteMust;
	}

	public void setSiteMust(List<SiteInst> siteMust) {
		SiteMust = siteMust;
	}

	public boolean isMust() {
		return isMust;
	}

	public void setMust(boolean isMust) {
		this.isMust = isMust;
	}

	public boolean isSgMust() {
		return isSgMust;
	}

	public void setSgMust(boolean isSgMust) {
		this.isSgMust = isSgMust;
	}

	public List<Segment> getSgproMust() {
		return sgproMust;
	}

	public void setSgproMust(List<Segment> sgproMust) {
		this.sgproMust = sgproMust;
	}
	public boolean isIsproSgMust() {
		return isproSgMust;
	}

	public void setIsproSgMust(boolean isproSgMust) {
		this.isproSgMust = isproSgMust;
	}

	public Element getElementA() {
		return ElementA;
	}

	public void setElementA(Element elementA) {
		ElementA = elementA;
	}

	public Element getElementZ() {
		return ElementZ;
	}

	public void setElementZ(Element elementZ) {
		ElementZ = elementZ;
	}

	public List<Element> getElementM() {
		return ElementM;
	}

	public void setElementM(List<Element> elementM) {
		ElementM = elementM;
	}
	
}
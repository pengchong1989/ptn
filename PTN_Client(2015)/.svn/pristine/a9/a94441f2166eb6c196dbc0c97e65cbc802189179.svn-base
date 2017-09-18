package com.nms.ui.ptn.business.dialog.pwpath;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import twaver.Element;
import twaver.Link;
import twaver.Node;
import twaver.PopupMenuGenerator;
import twaver.TDataBox;
import twaver.TUIManager;
import twaver.TView;
import twaver.TWaverConst;

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.enums.ETunnelMenu;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.TopoAttachment;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysMenu;
import com.nms.ui.ptn.basicinfo.dialog.site.SelectSiteDialog;
import com.nms.ui.ptn.business.dialog.cespath.AddCESAllDialog;
import com.nms.ui.ptn.business.dialog.tunnel.TunnelTopoPanel;

/**
 * PW网络侧 拓扑
 * @author dzy
 */
public class TunnelTopology extends javax.swing.JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4803410512979063565L;
	//public static TunnelTopology tunnelTopology = null;	//PW网络侧 拓扑
	private TunnelTopoPanel tunnelTopo = null;	//网元拓扑
	private   SiteInst siteA = null;  //a端网元
	private  SiteInst siteZ = null;  //z端网元	
	private  SiteInst mustSite = null;
	private AddPDialog addPDialog = null;  //pw编辑面板
	public List<SiteInst> siteMust = new ArrayList<SiteInst>();//必经网元
	public List<Tunnel> tunnelMust = new ArrayList<Tunnel>();//必经tunnel
	private TView tView; //拓扑
//	private AddCESAllDialog addCESDialog = null;//快速新建ces业务面板
	
	 public SiteInst getMustSite() {
		return mustSite;
	}

	public void setMustSite(SiteInst mustSite) {
		this.mustSite = mustSite;
	}

	{
		TUIManager.registerAttachment("topoTitle", TopoAttachment.class);
	}

	/**
	 * 创建PW网络侧 拓扑
	 * @param addPDialog	pw编辑页面
	 */
	public TunnelTopology(final AddPDialog addPDialog) {
		super(new BorderLayout());
		try {
			this.addPDialog = addPDialog;
		//	tunnelTopology = this;
			tunnelTopo = new TunnelTopoPanel(addPDialog.getPwType());
			//拓扑右键菜单
//			tunnelTopo.getNetWork().doLayout(TWaverConst.LAYOUT_CIRCULAR);//自动布局
			tunnelTopo.getNetWork().setPopupMenuGenerator(new PopupMenuGenerator() {
				@Override
				public JPopupMenu generate(TView tview, MouseEvent mouseEvent) {

					JPopupMenu menu = new JPopupMenu();
					tView = tview;
					if (tview.getDataBox().getSelectionModel().isEmpty()) {
						// 添加搜索网元菜单
						creatMenu(addPDialog,menu, ResourceUtil.srcStr(StringKeysMenu.MENU_SELECTSITE), ETunnelMenu.SERACH.getValue(), null);
					} else {
						final Element element = tview.getDataBox().getLastSelectedElement();
						if (element instanceof Link) {
							if (((Link)element).getLinkColor() == Color.GREEN) {
								creatMenu(addPDialog,menu, ResourceUtil.srcStr(StringKeysMenu.MENU_SELECT_PATH), ETunnelMenu.SELECTPATH.getValue(), element);
							} else {
								// 否则加载取消设置菜单
								creatMenu(addPDialog,menu, ResourceUtil.srcStr(StringKeysMenu.MENU_CANEL_CONFIG), ETunnelMenu.CANELPATH.getValue(),element);
							}
						}
						if (element instanceof Node) {

							Node nodeSelect = (Node) element;

							// 如果此网元没有任何连接的�?就没有菜�?
							if (nodeSelect.getAllLinks() == null) {
								return null;
							}
							// 获取dialog中tunnel类型的�?
							if (null == nodeSelect.getBusinessObject()) {
								// 设置选择A端菜�?
								creatMenu(addPDialog,menu, ResourceUtil.srcStr(StringKeysMenu.MENU_SETA), ETunnelMenu.SELECTA.getValue(), element);

								// 设置选择Z端菜�?
								creatMenu(addPDialog,menu, ResourceUtil.srcStr(StringKeysMenu.MENU_SETZ), ETunnelMenu.SELECTZ.getValue(), element);
								
								//设置必经网元
								creatMenu(addPDialog,menu, ResourceUtil.srcStr(StringKeysMenu.MENU_SETMUSTPASSTHROUGH), ETunnelMenu.MUSTSITE.getValue(), element);

							} else {
								// 取消设置菜单
								creatMenu(addPDialog,menu, ResourceUtil.srcStr(StringKeysMenu.MENU_CANEL_CONFIG), ETunnelMenu.CANELCONFIG.getValue(),element);
							}
						}
					}

					return menu;
				}
			});
			
			//链接link右键监听事件
			tunnelTopo.getNetWork().addElementClickedActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Element element = (Element) e.getSource();
					if(element!=null&&element instanceof Link){
						if(element.getUserObject()!=null&&element.getBusinessObject()==null){
							TUIManager.registerAttachment("TunnelTopoTitle", TopoAttachment.class,1, (int) element.getX(), (int) element.getY());
							Tunnel tunnel =  (Tunnel)element.getUserObject();
							element.setBusinessObject(tunnel.getTunnelName());
							element.addAttachment("TunnelTopoTitle");
						}else{
							element.removeAttachment("TunnelTopoTitle");
							element.setBusinessObject(null);
						}    
					}
				}
			});
			this.add(tunnelTopo);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	/**
	 * 创建快速新建ces业务的拓扑
	 * @author guoqc
	 */
	public TunnelTopology(final AddCESAllDialog addDialog) {
		super(new BorderLayout());
		try {
			this.addPDialog = addDialog;
			tunnelTopo = new TunnelTopoPanel(addDialog.getPwType());
			//拓扑右键菜单
			tunnelTopo.getNetWork().doLayout(TWaverConst.LAYOUT_CIRCULAR);
			tunnelTopo.getNetWork().setPopupMenuGenerator(new PopupMenuGenerator() {
				@Override
				public JPopupMenu generate(TView tview, MouseEvent mouseEvent) {

					JPopupMenu menu = new JPopupMenu();
					tView = tview;
					if (tview.getDataBox().getSelectionModel().isEmpty()) {
						// 添加搜索网元菜单
						creatMenu(addPDialog, menu, ResourceUtil.srcStr(StringKeysMenu.MENU_SELECTSITE), ETunnelMenu.SERACH.getValue(), null);
					} else {
						final Element element = tview.getDataBox().getLastSelectedElement();
						if (element instanceof Link) {
							if (((Link)element).getLinkColor() == Color.GREEN) {
								creatMenu(addPDialog, menu, ResourceUtil.srcStr(StringKeysMenu.MENU_SELECT_PATH), ETunnelMenu.SELECTPATH.getValue(), element);
							} else {
								// 否则加载取消设置菜单
								creatMenu(addPDialog, menu, ResourceUtil.srcStr(StringKeysMenu.MENU_CANEL_CONFIG), ETunnelMenu.CANELPATH.getValue(),element);
							}
						}
						if (element instanceof Node) {
							Node nodeSelect = (Node) element;
							// 如果此网元没有任何连接的�?就没有菜�?
							if (nodeSelect.getAllLinks() == null) {
								return null;
							}
							// 获取dialog中tunnel类型的�?
							if (null == nodeSelect.getBusinessObject()) {
								// 设置选择A端菜单
								creatMenu(addPDialog, menu, ResourceUtil.srcStr(StringKeysMenu.MENU_SETA), ETunnelMenu.SELECTA.getValue(), element);
								// 设置选择Z端菜单
								creatMenu(addPDialog, menu, ResourceUtil.srcStr(StringKeysMenu.MENU_SETZ), ETunnelMenu.SELECTZ.getValue(), element);
							} else {
								// 取消设置菜单
								creatMenu(addPDialog, menu, ResourceUtil.srcStr(StringKeysMenu.MENU_CANEL_CONFIG), ETunnelMenu.CANELCONFIG.getValue(),element);
							}
						}
					}

					return menu;
				}
			});
			
			//链接link右键监听事件
			tunnelTopo.getNetWork().addElementClickedActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Element element = (Element) e.getSource();
					if(element!=null&&element instanceof Link){
						if(element.getUserObject()!=null&&element.getBusinessObject()==null){
							TUIManager.registerAttachment("TunnelTopoTitle", TopoAttachment.class,1, (int) element.getX(), (int) element.getY());
							Tunnel tunnel =  (Tunnel)element.getUserObject();
							element.setBusinessObject(tunnel.getTunnelName());
							element.addAttachment("TunnelTopoTitle");
						}else{
							element.removeAttachment("TunnelTopoTitle");
							element.setBusinessObject(null);
						}    
					}
				}
			});
			this.add(tunnelTopo);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 
	 * 设置菜单
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
	 * @Exception 异常对象
	 */
	private void creatMenu(final AddPDialog dialog,JPopupMenu menu, String menuText, final int type, final Element element) {
		
		JMenuItem jMenuItem = new JMenuItem(menuText);
		jMenuItem.addActionListener(new java.awt.event.ActionListener() {
			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				if (ETunnelMenu.SERACH.getValue() == type) { // 搜索网元
					try {
						SelectSiteDialog selectsitedialog = new SelectSiteDialog(tunnelTopo.getNetWork(), true);
						selectsitedialog.setLocation(UiUtil.getWindowWidth(selectsitedialog.getWidth()), UiUtil.getWindowHeight(selectsitedialog.getHeight()));
						selectsitedialog.setVisible(true);
					} catch (Exception e) {
						ExceptionManage.dispose(e,this.getClass());
					}
				} else if (ETunnelMenu.SELECTA.getValue() == type) {//选择A端网元
					try {
						if(addPDialog instanceof AddCESAllDialog){
							((AddCESAllDialog) addPDialog).clearPortTableA();
						}
						setSiteA((SiteInst) element.getUserObject());
						setNodeAttachment("A", element);
						addPDialog.setaSiteId(getSiteA().getSite_Inst_Id());
						tunnelMust.clear();
					} catch (Exception e) {
						ExceptionManage.dispose(e, this.getClass());
					}
					
				} else if (ETunnelMenu.SELECTZ.getValue() == type) { //选择Z端网元
					try {
						if(addPDialog instanceof AddCESAllDialog){
							((AddCESAllDialog) addPDialog).clearPortTableZ();
						}
						setSiteZ((SiteInst) element.getUserObject());
						setNodeAttachment("Z", element);
						addPDialog.setzSiteId(getSiteZ().getSite_Inst_Id());
						tunnelMust.clear();
					} catch (Exception e) {
						ExceptionManage.dispose(e, this.getClass());
					}
				}else if (ETunnelMenu.MUSTSITE.getValue() == type) { //选择必经
					try {
						SiteInst siteInst = (SiteInst) element.getUserObject();
						setNodeAttachment("XC", element);
						if(!siteMust.contains(siteInst)){
							siteMust.add(siteInst);
						}
					} catch (Exception e) {
						ExceptionManage.dispose(e, this.getClass());
					}
				}else if (ETunnelMenu.CANELCONFIG.getValue() == type) { // 取消设置
					if ("A".equals(element.getBusinessObject())) {
						addPDialog.aSiteIdChange(0);
						canelConfig(element, element.getBusinessObject().toString(),dialog);
					}else if ("Z".equals(element.getBusinessObject())) {
						addPDialog.zSiteIdChange(0);
						canelConfig(element, element.getBusinessObject().toString(),dialog);
					}else if ("XC".equals(element.getBusinessObject())) {
						SiteInst siteInst = (SiteInst) element.getUserObject();
						siteMust.remove(siteInst);
						canelConfig(element, element.getBusinessObject().toString(),dialog);
					}
				} else if (ETunnelMenu.SELECTPATH.getValue() == type) { //选择路径
					Tunnel tunnel = (Tunnel) element.getUserObject();
					if(!tunnelMust.contains(tunnel)){
						int check = chcekTunnel(tunnel);
						if(check>=0){
							setLinkColor(tunnelMust.get(check), Color.GREEN);
							//选择完路径，需要更改左边界面上分段路由下拉列表和tunnel选择下拉列表
							Tunnel tunnelBefore = tunnelMust.get(check);
							int tunnelId = tunnelBefore.getTunnelId();
							Map<String, Tunnel> route_TunnelMap = addPDialog.getRoute_TunnelMap();
							for (String key : route_TunnelMap.keySet()) {
								if(tunnelId == route_TunnelMap.get(key).getTunnelId()){
									route_TunnelMap.put(key, tunnel);
									addPDialog.updateMSRoute(key, tunnelBefore, tunnel);
									break;
								}
							}
							tunnelMust.remove(check);
							tunnelMust.add(check, tunnel);
						}else{
							tunnelMust.add(tunnel);
						}
						setTunnelPath(element);
					}
				} else if (ETunnelMenu.CANELPATH.getValue() == type) {  //取消路径
					Tunnel tunnel = (Tunnel) element.getUserObject();
					tunnelMust.remove(tunnel);
					((Link) element).putLinkColor(Color.GREEN);
					addPDialog.getTunnelTable().clear();
					List<Element> allElements = tunnelTopo.getBox().getAllElements();
					for (Element elementObj : allElements) {
						elementObj.removeAttachment("topoTitle");
						elementObj.setBusinessObject(null);
					}
				}
			}
		});
		menu.add(jMenuItem);
	}
	
	public int chcekTunnel(Tunnel tunnel){
		for (int i = 0; i < tunnelMust.size(); i++) {
			if((tunnelMust.get(i).getASiteId() == tunnel .getASiteId() && tunnelMust.get(i).getZSiteId() == tunnel.getZSiteId()) ||(tunnelMust.get(i).getZSiteId() == tunnel .getASiteId() && tunnelMust.get(i).getASiteId() == tunnel.getZSiteId())){
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * 取消菜单
	 */
	public void removePopMenu() {
		tunnelTopo.getNetWork().setPopupMenuGenerator(null);
	}

	/**
	 * 取消设置菜单事件
	 * @param element 拓扑	
	 * @param type	拓扑类型
	 */
	private void canelConfig(Element element, String type,AddPDialog dialog) { 
		if ("A".equals(type)) {
			addPDialog.setPortInst_A(null);
			addPDialog.setAText("");
			setSiteA(null);
			// 移除提示?
			element.removeAttachment("topoTitle");
			element.setBusinessObject(null);
		} else if ("Z".equals(type)) {
			addPDialog.setPortInst_A(null);
			addPDialog.setZText("");
			setSiteZ(null);
			// 移除提示?
			element.removeAttachment("topoTitle");
			element.setBusinessObject(null);
		} else if ("XC".equals(type)) {
			addPDialog.setPortInst_A(null);
			addPDialog.setZText("");
			setSiteZ(null);
			// 移除提示?
			element.removeAttachment("topoTitle");
			element.setBusinessObject(null);
		}

		// 设置完路径后要做路径检?
		dialog.setHasCheck(false);
		//取消路径
	}

	/**
	 * 设置AZ端的title
	 * @param type  A、Z端
	 * @param selectElement 拓扑控件
	 */
	@SuppressWarnings("unchecked")
	public void setNodeAttachment(String type, Element selectElement) {
		List<Element> elementList = tunnelTopo.getBox().getAllElements();
		if ("A".equals(type) || "Z".equals(type) ) {
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
			selectElement.setBusinessObject(text);
		}

		selectElement.addAttachment("topoTitle");
	}
	/**
	 * 设置link的颜色
	 * @param color 颜色
	 */
	@SuppressWarnings("unchecked")
	public void setLinkColor(Color color) {
		TDataBox box = tunnelTopo.getBox();
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

	public void setLinkColor(Tunnel tunnel, Color color){
		TDataBox box = tunnelTopo.getBox();
		List<Element> elementList = box.getAllElements();
		for (int i = elementList.size() - 1; i >= 0; i--) {
			Element element = elementList.get(i);
			if (element instanceof Link) {
				if(tunnel.getTunnelId() == ((Tunnel)element.getUserObject()).getTunnelId()){
					((Link) element).putLinkColor(color);
					break;
				}
			}
		}
	}
	
	/**
	 * 设置tunnel路径
	 * @param elementObject 选择的控件
	 */ 
	@SuppressWarnings("unchecked")
	private void setTunnelPath(Element elementObject){
		try {
			((Link) elementObject).putLinkColor(Color.BLUE);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}
	
	/**
	 * 自动路由后，改变拓扑颜色
	 * @param tunnels
	 */
	public void setPath(List<Tunnel> tunnels){

		List<Element> Elements = tunnelTopo.getBox().getAllElements();
		for(Tunnel tunnel:tunnels){
			for(Element elementObject : Elements){
				if(elementObject instanceof Link){
					Tunnel tunnel2 = (Tunnel) elementObject.getUserObject();
					if(tunnel2.getTunnelId() == tunnel.getTunnelId()){
						((Link) elementObject).putLinkColor(Color.BLUE);
					}
				}
			}
		}
	}
	
	public  TunnelTopoPanel getTunnelTopo() {
		return tunnelTopo;
	}

	public  void setTunnelTopo(TunnelTopoPanel tunnelTopo) {
		this.tunnelTopo = tunnelTopo;
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

}
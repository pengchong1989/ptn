package com.nms.db.bean.ptn.path.eth;

import com.nms.db.bean.ptn.path.ServiceInfo;
import com.nms.db.bean.ptn.path.protect.PwProtect;
import com.nms.db.enums.EActiveStatus;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DateUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

/**
 * 带双归保护的VPWS业务
 * @author pc
 *
 */
public class DualInfo extends ServiceInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1610870642159626921L;
	private int pwId;
	private int aXcId;
	private int zXcId;
	private int rootSite;//源节点
	private int branchMainSite;//目的主节点
	private int branchProtectSite;//目的辅节点
	private PwProtect pwProtect;//pw保护配置块
	private String rootName;//log日志显示使用
	private String rootAcName;//log日志显示使用
	private String branchMainName;//log日志显示使用
	private String branchMainAcName;//log日志显示使用
	private String branchMainPwName;//log日志显示使用
	private String branchProtectName;//log日志显示使用
	private String branchProtectAcName;//log日志显示使用
	private String branchProtectPwName;//log日志显示使用
	
	public String getRootAcName() {
		return rootAcName;
	}

	public void setRootAcName(String rootAcName) {
		this.rootAcName = rootAcName;
	}

	public String getBranchMainAcName() {
		return branchMainAcName;
	}

	public void setBranchMainAcName(String branchMainAcName) {
		this.branchMainAcName = branchMainAcName;
	}

	public String getBranchMainPwName() {
		return branchMainPwName;
	}

	public void setBranchMainPwName(String branchMainPwName) {
		this.branchMainPwName = branchMainPwName;
	}

	public String getBranchProtectAcName() {
		return branchProtectAcName;
	}

	public void setBranchProtectAcName(String branchProtectAcName) {
		this.branchProtectAcName = branchProtectAcName;
	}

	public String getBranchProtectPwName() {
		return branchProtectPwName;
	}

	public void setBranchProtectPwName(String branchProtectPwName) {
		this.branchProtectPwName = branchProtectPwName;
	}

	public String getRootName() {
		return rootName;
	}

	public void setRootName(String rootName) {
		this.rootName = rootName;
	}

	public String getBranchMainName() {
		return branchMainName;
	}

	public void setBranchMainName(String branchMainName) {
		this.branchMainName = branchMainName;
	}

	public String getBranchProtectName() {
		return branchProtectName;
	}

	public void setBranchProtectName(String branchProtectName) {
		this.branchProtectName = branchProtectName;
	}

	public int getPwId() {
		return pwId;
	}

	public void setPwId(int pwId) {
		this.pwId = pwId;
	}

	public int getaXcId() {
		return aXcId;
	}

	public void setaXcId(int aXcId) {
		this.aXcId = aXcId;
	}

	public int getzXcId() {
		return zXcId;
	}

	public void setzXcId(int zXcId) {
		this.zXcId = zXcId;
	}

	public int getRootSite() {
		return rootSite;
	}

	public void setRootSite(int rootSite) {
		this.rootSite = rootSite;
	}

	public int getBranchMainSite() {
		return branchMainSite;
	}

	public void setBranchMainSite(int branchMainSite) {
		this.branchMainSite = branchMainSite;
	}

	public int getBranchProtectSite() {
		return branchProtectSite;
	}

	public void setBranchProtectSite(int branchProtectSite) {
		this.branchProtectSite = branchProtectSite;
	}

	public PwProtect getPwProtect() {
		return pwProtect;
	}

	public void setPwProtect(PwProtect pwProtect) {
		this.pwProtect = pwProtect;
	}

	@Override
	public void putObjectProperty() {
		SiteService_MB siteService = null;
		try {
			siteService=(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			this.putClientProperty("id", getId());
			this.putClientProperty("name", getName());
			this.putClientProperty("activeStates", getActiveStatus() == 1 ? EActiveStatus.ACTIVITY.toString() : EActiveStatus.UNACTIVITY.toString());
			this.putClientProperty("root", siteService.getSiteName(getRootSite()));
//			this.putClientProperty("branchMain", UiUtil.getSiteName(getBranchMainSite()));
//			this.putClientProperty("branchProtect", UiUtil.getSiteName(getBranchProtectSite()));
			this.putClientProperty("creater", getCreateUser());
			this.putClientProperty("createTime", DateUtil.strDate(getCreateTime(), DateUtil.FULLTIME));
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(siteService);
		}
	}
}

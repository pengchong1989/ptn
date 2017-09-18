package com.nms.db.bean.system.loginlog;
import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysBtn;
/**
 * Bean  
 * 与 表login_log对应
 * @author Administrator
 *
 */
public class LoginLog extends ViewDataObj{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int user_id;
	private String loginTime;
	private String outTime;
	private String IP;
	private int loginState;//登陆: 0/1/2 = ""/失败/成功
	private int logoutState;//退出: 0/1/2 = ""/失败/成功
	/**
	 *    注： 数据库中没有此数据 -仅做过滤查询时  判断
	 * 判断  ：是精确查找还是模糊查询
	 *     true - 模糊查询
	 *  默认     false -精确查询
	 */
	private boolean isSelect=false;
	/**
	 * logOut
	 * 数据库中没有此数据
	 * 仅作为显示 、判断
	 */
	private String logOut;
	private int state;
	
	/**
	 * 数据库中没有，
	 * 只做显示用
	 */
	private String user_name;//登录的用户名
	/**
	 * 数据库中没有，
	 * 只做显示用
	 */
	
	private String onLineTime;//逗留时间
	private String loginBeginTime; //登录开始时间
	private String loginEndTime; //登录结束时间 
	private String leaveBeginTime; //离开开始时间
	private String leaveEndTime; //离开结束时间 
	private String loginIp;//登录IP
	private boolean isIpSelect=false;//登录IP模糊查询是否选中
	
	
	public String getLoginBeginTime()
	{
		return loginBeginTime;
	}
	public void setLoginBeginTime(String loginBeginTime)
	{
		this.loginBeginTime = loginBeginTime;
	}
	public String getLoginEndTime()
	{
		return loginEndTime;
	}
	public void setLoginEndTime(String loginEndTime)
	{
		this.loginEndTime = loginEndTime;
	}
	public String getLeaveBeginTime()
	{
		return leaveBeginTime;
	}
	public void setLeaveBeginTime(String leaveBeginTime)
	{
		this.leaveBeginTime = leaveBeginTime;
	}
	public String getLeaveEndTime()
	{
		return leaveEndTime;
	}
	public void setLeaveEndTime(String leaveEndTime)
	{
		this.leaveEndTime = leaveEndTime;
	}
	public String getLoginIp()
	{
		return loginIp;
	}
	public void setLoginIp(String loginIp)
	{
		this.loginIp = loginIp;
	}
	public boolean isIpSelect()
	{
		return isIpSelect;
	}
	public void setIpSelect(boolean isIpSelect)
	{
		this.isIpSelect = isIpSelect;
	}
	public boolean isSelect() {
		return isSelect;
	}
	public void setSelect(boolean isSelect) {
		this.isSelect = isSelect;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getOnLineTime() {
		return onLineTime;
	}
	public void setOnLineTime(String onlineTime) {
		this.onLineTime = onlineTime;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getLogOut() {
		return logOut;
	}
	public void setLogOut(String logOut) {
		this.logOut = logOut;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String ip) {
		IP = ip;
	}
	public LoginLog() {
		super();
	}
	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {
		try {
			getClientProperties().put("id", getId());
			getClientProperties().put("user_name", this.getUser_name());
			getClientProperties().put("user_id", this.getUser_id());
			getClientProperties().put("loginTime", this.getLoginTime());
			getClientProperties().put("outTime", getOutTime());
			getClientProperties().put("Ip", getIP());
			getClientProperties().put("onlineTime",this.getOnLineTime());
			getClientProperties().put("logOut", this.getLogOut());
			getClientProperties().put("loginState", loginState(this.getLoginState()));
			getClientProperties().put("logoutState", loginState(this.getLogoutState()));
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
		}
	}
	
	private String loginState(int state) {
		if(state == 0){
			return "";
		}else if(state == 1){
			return ResourceUtil.srcStr(StringKeysBtn.BTN_EXPORT_FALSE);
		}else{
			return ResourceUtil.srcStr(StringKeysBtn.BTN_EXPORT_ISUCCESS);
		}
	}
	
	public Integer getSite_Inst_Id() {
		return null;
	}
	public int getLoginState() {
		return loginState;
	}
	
	public void setLoginState(int loginState) {
		this.loginState = loginState;
	}
	
	public int getLogoutState() {
		return logoutState;
	}
	
	public void setLogoutState(int logoutState) {
		this.logoutState = logoutState;
	}
	
}

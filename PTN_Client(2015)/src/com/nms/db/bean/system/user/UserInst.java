package com.nms.db.bean.system.user;

import java.util.List;

import com.nms.db.bean.system.NetWork;
import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysObj;


public class UserInst extends ViewDataObj{

	private static final long serialVersionUID = 865085143312800199L;
	private int User_Id ;
	private String User_Name;
	private String User_Pass;
	private String User_Inface;//详细信息
	private String pswExpires;
	private int beforeRemind;
	private String deadTime;
	private String startIp;
	private String endIp;
	
	/**
	 * 界面显示  
	 * 数据库无此数据
	 */
	private String User_Group;//用户   角色  标签
	private String user_GroupEn;//英文用户   角色  标签
	private int roleInfo_id; //关联角色  ID
	private int isAll;
	/**数据库中没有此数据
	 * 仅作为显示
	 *  用户—域  关联
	 */
	private List<NetWork> FieldList;
	
	public int getRoleInfo_id() {
		return roleInfo_id;
	}

	public String getUser_Inface() {
		return User_Inface;
	}

	public void setUser_Inface(String user_Inface) {
		User_Inface = user_Inface;
	}

	public void setRoleInfo_id(int roleInfo_id) {
		this.roleInfo_id = roleInfo_id;
	}

	public List<NetWork> getFieldList() {
		return FieldList;
	}

	public void setFieldList(List<NetWork> userFieldList) {
		this.FieldList = userFieldList;
	}
	/**数据库中没有此数据
	 * 仅作为显示
	 * 
	 */
	private int isLock;

	

	public int getIsLock() {
		return isLock;
	}

	public void setIsLock(int isLock) {
		this.isLock = isLock;
	}

	public int getUser_Id() {
		return User_Id;
	}

	public void setUser_Id(int userId) {
		User_Id = userId;
	}

	public String getUser_Name() {
		return User_Name;
	}

	public void setUser_Name(String userName) {
		User_Name = userName;
	}

	public String getUser_Pass() {
		return User_Pass;
	}

	public void setUser_Pass(String userPass) {
		User_Pass = userPass;
	}

	public String getUser_Group() {
		return User_Group;
	}

	public void setUser_Group(String userGroup) {
		User_Group = userGroup;
	}

	public int getIsAll() {
		return isAll;
	}

	public void setIsAll(int isAll) {
		this.isAll = isAll;
	}
	
	public String getPswExpires()
	{
		return pswExpires;
	}

	public void setPswExpires(String pswExpires)
	{
		this.pswExpires = pswExpires;
	}

	public int getBeforeRemind()
	{
		return beforeRemind;
	}

	public void setBeforeRemind(int beforeRemind)
	{
		this.beforeRemind = beforeRemind;
	}
	
	public String getDeadTime()
	{
		return deadTime;
	}

	public void setDeadTime(String deadTime)
	{
		this.deadTime = deadTime;
	}
	
	public String getStartIp()
	{
		return startIp;
	}

	public void setStartIp(String startIp)
	{
		this.startIp = startIp;
	}

	public String getEndIp()
	{
		return endIp;
	}

	public void setEndIp(String endIp)
	{
		this.endIp = endIp;
	}

	public String getUser_GroupEn() {
		return user_GroupEn;
	}

	public void setUser_GroupEn(String userGroupEn) {
		user_GroupEn = userGroupEn;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {
		try {
			String status = null;
			getClientProperties().put("User_Inface", this.getUser_Inface());
			getClientProperties().put("id", getUser_Id());
			getClientProperties().put("user_name", getUser_Name());
			getClientProperties().put("startIp", getStartIp());
			getClientProperties().put("endIp", getEndIp());
			if(getIsAll() == 1){
				status = ResourceUtil.srcStr(StringKeysObj.OBJ_YES);
			}else{
				status = ResourceUtil.srcStr(StringKeysObj.OBJ_NO);
			}
			getClientProperties().put("adminstatus", status);
			if(ResourceUtil.language.equals("zh_CN")){
				getClientProperties().put("User_Group", this.getUser_Group());
			}else{
				getClientProperties().put("User_Group", this.getUser_GroupEn());
			}
			if(this.getIsLock()==1){
				status = ResourceUtil.srcStr(StringKeysObj.OBJ_YES);
			}else{
				status = ResourceUtil.srcStr(StringKeysObj.OBJ_NO);
			}
			getClientProperties().put("isLock", status);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
}

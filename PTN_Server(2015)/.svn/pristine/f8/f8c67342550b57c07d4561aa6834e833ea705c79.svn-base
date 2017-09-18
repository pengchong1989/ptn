package com.nms.db.bean.system.loginlog;

import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysObj;
/**
 * Bean 
 * 与表 user_lock对应
 * @author Administrator
 *
 */
public class UserLock extends ViewDataObj{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int user_id;
	private String lockTime;
	private String clearTime;
	private int lockType;
	private int clearType;
	private String lockUsername;
	private String clearUsername;
	/**
	 * 数据库中没有此数据
	 * 仅作为显示
	 */
	private String user_name;
	/**
	 * 数据库中没有此数据
	 * 仅作标识
	 */
	private int lock;
	
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public int getLock() {
		return lock;
	}

	public void setLock(int lock) {
		this.lock = lock;
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

	public String getLockTime() {
		return lockTime;
	}

	public void setLockTime(String lockTime) {
		this.lockTime = lockTime;
	}

	public String getClearTime() {
		return clearTime;
	}

	public void setClearTime(String clearTime) {
		this.clearTime = clearTime;
	}

	public int getLockType() {
		return lockType;
	}

	public void setLockType(int lockType) {
		this.lockType = lockType;
	}

	public int getClearType() {
		return clearType;
	}

	public void setClearType(int clearType) {
		this.clearType = clearType;
	}

	public String getLockUsername() {
		return lockUsername;
	}

	public void setLockUsername(String lockUsername) {
		this.lockUsername = lockUsername;
	}

	public String getClearUsername() {
		return clearUsername;
	}

	public void setClearUsername(String clearUsername) {
		this.clearUsername = clearUsername;
	}

	public UserLock() {
		super();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {
		try {
			getClientProperties().put("id", getId());
			getClientProperties().put("user_id", this.getUser_id());
			getClientProperties().put("lockTime", this.getLockTime());
			getClientProperties().put("clearTime", getClearTime());
			
			getClientProperties().put("lockUsername", this.getLockUsername());
			getClientProperties().put("clearUsername",this.getClearUsername());
			getClientProperties().put("user_name",this.getUser_name());
			String lockstate="";
			if(this.getLock()==1){
				lockstate = ResourceUtil.srcStr(StringKeysObj.OBJ_YES);
			}else{
				lockstate = ResourceUtil.srcStr(StringKeysObj.OBJ_NO);
				
			}
			getClientProperties().put("lock",lockstate);
			String lockType="";
			
	String clearType="";
	if(this.getClearType()==1){//管理员
		clearType=ResourceUtil.srcStr(StringKeysObj.ADMIN_LOCK);
		}else if(this.getClearType()==0&&this.getClearTime()!=null){//系统
			clearType=ResourceUtil.srcStr(StringKeysObj.SYSTEM_LOCK);
		}
			if(this.getLockType()==1){
			
				lockType=ResourceUtil.srcStr(StringKeysObj.ADMIN_LOCK);
			}else if(this.getLockType()==0){
			
				lockType=ResourceUtil.srcStr(StringKeysObj.SYSTEM_LOCK);
			}
			getClientProperties().put("lockType", lockType);
			getClientProperties().put("clearType",clearType);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
		}
	}
	public Integer getSite_Inst_Id() {
		return null;
	}

}

package com.nms.db.bean.path;




import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysTip;


public class SetNameRule extends ViewDataObj {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;//名称  
	private String namerule;//命名规则
	private String nameexample;//命名示例
	private String sourcename;//资源名称
	private int rowcount;//行数
	private int isUsed;//是否生效 1/2
	private String connect;//连接符
	
	public String getConnect() {
		return connect;
	}

	public void setConnect(String connect) {
		this.connect = connect;
	}

	public int getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}

	public int getRowcount() {
		return rowcount;
	}

	public void setRowcount(int rowcount) {
		this.rowcount = rowcount;
	}

	public String getSourcename() {
		return sourcename;
	}

	public void setSourcename(String sourcename) {
		this.sourcename = sourcename;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNamerule() {
		return namerule;
	}

	public void setNamerule(String namerule) {
		this.namerule = namerule;
	}

	public String getNameexample() {
		return nameexample;
	}

	public void setNameexample(String nameexample) {
		this.nameexample = nameexample;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {
		try {
			getClientProperties().put("id", getId());
			getClientProperties().put("sourcename", this.getSourcename());
			getClientProperties().put("name", this.getName());
			getClientProperties().put("namerule", this.getNamerule());
			getClientProperties().put("nameexample", this.getNameexample());
			getClientProperties().put("type", ResourceUtil.srcStr(StringKeysTip.TIP_SHOW_TYPE));
			getClientProperties().put("rowcount", this.getRowcount());
			getClientProperties().put("isUsed", this.getIsUsed() == 1?ResourceUtil.srcStr(StringKeysObj.OBJ_YES):ResourceUtil.srcStr(StringKeysObj.OBJ_NO));
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
		}
	}
	

}

package com.nms.service;

import java.util.List;

/**
 * 
 * @author k.k
 * 
 */
public interface OperationServiceI {

	/**
	 * 执行插入方法
	 * 
	 * @param object
	 *            实体bean对象
	 * @return
	 * @throws Exception
	 */
	public String excutionInsert(Object object) throws Exception;

	/**
	 * 执行修改方法
	 * 
	 * @param object
	 *            实体bean对象
	 * @return
	 * @throws Exception
	 */
	public String excutionUpdate(Object object) throws Exception;

	/**
	 * 执行删除方法
	 * 
	 * @param objectId
	 *            数据库主键
	 * @return
	 * @throws Exception
	 */
	public String excutionDelete(List objectList) throws Exception;
	/**
	 * 执行同步方法
	 * @param siteId
	 * @return
	 * @throws Exception
	 */
	public Object synchro(int siteId) throws Exception;

}

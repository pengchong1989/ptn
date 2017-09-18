package com.nms.ui.manager;

import com.nms.db.enums.EOperationLogType;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.keys.StringKeysTip;

/**
 * 添加 操作日志记录
 *
 * @author sy
 *
 */
public class InsertOperate {
	/**
	 *添加操作日志记录时
	 *			直接 调用
	 * @param jbutton
	 * 			本次操作的 引发按钮( 为 PtnButton 类型)
	 * @param operateKey
	 * 			操作类型  （以写好枚举类，添加需要  到 枚举中添加）枚举类 ：EOperationLogType
	 * 				需要新增
	 * @param result
	 * 			返回结果： 配置成功 则  为1，否则 为 2；
	 *   1为成功，，2 为失败
	 */
	public static void setOperation(PtnButton ptnButton,int operateKey,String result){
		//添加日志记录
		ptnButton.setOperateKey(EOperationLogType.TUNNELDELETE.getValue());
		int operationResult=0;
		/**
		 * 返回结果是否为 ： 配置成功
		 */
		if(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS).equals(result)){
			operationResult=1;
		}else{
			operationResult=2;
		}
		ptnButton.setResult(operationResult);
	}
}

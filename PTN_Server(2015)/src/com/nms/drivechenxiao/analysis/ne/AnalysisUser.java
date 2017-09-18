package com.nms.drivechenxiao.analysis.ne;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.service.impl.CoderUtils;
import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.user.UserObject;
/**用户有关操作**/
public class AnalysisUser extends CxtOpLump {
	/**
	 * 设置空闲时长
	 * 
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] setiIdle(UserObject userObject, int session, int seqid) {
		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, 1));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, userObject.getName()));// 账号
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, 2));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_D, userObject.getIdle()));// 时间
		CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);

		CxtAtomType cxtAtomType = new CxtAtomType();
		cxtAtomType.setCxtATTable(cxtATTable);
		cxtAtomType.setType("AT_TABLE");

		byte[] command = new byte[0];
		byte[] cxtString = getCxtString("ne/proc/secu.setidle");
		byte[] cxtTable = getCxtAtomType(cxtAtomType);
		command = CoderUtils.arraycopy(command, cxtString);
		command = CoderUtils.arraycopy(command, cxtTable);

		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_ACALL, command, session, seqid);
		return command;
	}

	/**
	 * 新建用户
	 * 
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] addUser(UserObject userObject, int session, int seqid) {
		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, 1));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, userObject.getName()));// 账号
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, 2));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, userObject.getPassWord()));// 密码
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, 3));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, userObject.getPower()));// 权限
		CxtATTable cxtATTable = getCxtATTable(3, cxtAtomTypes);

		CxtAtomType cxtAtomType = new CxtAtomType();
		cxtAtomType.setCxtATTable(cxtATTable);
		cxtAtomType.setType("AT_TABLE");

		byte[] command = new byte[0];
		byte[] cxtString = getCxtString("ne/proc/secu.adduser");
		byte[] cxtTable = getCxtAtomType(cxtAtomType);
		command = CoderUtils.arraycopy(command, cxtString);
		command = CoderUtils.arraycopy(command, cxtTable);

		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_ACALL, command, session, seqid);
		return command;
	}

	/**
	 * 删除用户
	 * 
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] delUser(UserObject userObject, int session, int seqid) {
		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, 1));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, userObject.getName()));// 账号
		CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);

		CxtAtomType cxtAtomType = new CxtAtomType();
		cxtAtomType.setCxtATTable(cxtATTable);
		cxtAtomType.setType("AT_TABLE");

		byte[] command = new byte[0];
		byte[] cxtString = getCxtString("ne/proc/secu.deluser");
		byte[] cxtTable = getCxtAtomType(cxtAtomType);
		command = CoderUtils.arraycopy(command, cxtString);
		command = CoderUtils.arraycopy(command, cxtTable);

		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_ACALL, command, session, seqid);
		return command;
	}

	/**
	 * 修改用户密码
	 * 
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] updatePassWord(UserObject userObject, int session, int seqid) {
		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, 1));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, userObject.getName()));// 账号
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, 2));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, userObject.getPassWord()));// 旧密码
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, 3));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, userObject.getNewPassWord()));// 新密码
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, 4));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, userObject.getNewPassWordToo()));// 新密码确认
		CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);

		CxtAtomType cxtAtomType = new CxtAtomType();
		cxtAtomType.setCxtATTable(cxtATTable);
		cxtAtomType.setType("AT_TABLE");

		byte[] command = new byte[0];
		byte[] cxtString = getCxtString("ne/proc/secu.passwd");
		byte[] cxtTable = getCxtAtomType(cxtAtomType);
		command = CoderUtils.arraycopy(command, cxtString);
		command = CoderUtils.arraycopy(command, cxtTable);

		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_ACALL, command, session, seqid);
		return command;
	}

	/**
	 * 查询所有用户
	 * 
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] getAllUser(int session, int seqid) {

		byte[] command = new byte[] { 0 };
		byte[] cxtString = getCxtString("ne/proc/secu.alluser");
		command = CoderUtils.arraycopy(cxtString, command);

		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_ACALL, command, session, seqid);
		return command;
	}

	/**
	 * 解析查询所有用户
	 * 
	 * @param command
	 * @param CXNEObject
	 * @return
	 */
	public List<UserObject> analysisAllUser(byte[] command, CXNEObject CXNEObject) {
		List<UserObject> userObjectList = new ArrayList<UserObject>();
		int start = 29;
		// byte[] tt = command;
		byte[] tt = new byte[] { 114, 109, 116, 1, 0, 0, 2, 30, 35, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 48, 0, 0, 0, 7, 19, 0, 0, 0, 1, 48, 0, 0, 0, 4, 32, 0, 0, 0, 5, 103, 114, 111, 117, 112, 48, 0, 0, 0, 1, 32, 0, 0, 0, 5, 65, 68, 77, 73, 78, 1, 1, 32, 0, 0, 0, 4, 105, 100, 108, 101, 19, 0, 0, 3, -84, 32, 0, 0, 0, 4, 108, 111, 99, 107, 1, 0, 32, 0, 0, 0, 4, 110, 97, 109, 101, 32, 0, 0, 0, 5, 97, 100, 109, 105, 110, 19, 0, 0, 0, 2, 48, 0, 0, 0, 3, 32, 0, 0, 0, 5, 103, 114, 111, 117, 112, 48, 0, 0, 0, 1, 32, 0, 0, 0, 6, 86, 73, 69, 87, 69, 82, 1, 1, 32, 0, 0, 0, 4, 105, 100, 108, 101, 19, 0, 0, 7, 8, 32, 0, 0, 0, 4, 110, 97, 109, 101, 32, 0, 0, 0, 6, 118, 105, 101, 119, 101, 114, 19, 0, 0, 0, 3, 48, 0, 0, 0, 3, 32, 0, 0, 0, 5, 103, 114, 111, 117, 112, 48, 0, 0, 0,
				1, 32, 0, 0, 0, 8, 79, 80, 69, 82, 65, 84, 79, 82, 1, 1, 32, 0, 0, 0, 4, 105, 100, 108, 101, 19, 0, 0, 7, 8, 32, 0, 0, 0, 4, 110, 97, 109, 101, 32, 0, 0, 0, 8, 111, 112, 101, 114, 97, 116, 111, 114, 19, 0, 0, 0, 4, 48, 0, 0, 0, 3, 32, 0, 0, 0, 5, 103, 114, 111, 117, 112, 48, 0, 0, 0, 1, 32, 0, 0, 0, 6, 86, 73, 69, 87, 69, 82, 1, 1, 32, 0, 0, 0, 4, 105, 100, 108, 101, 19, 0, 0, 0, 60, 32, 0, 0, 0, 4, 110, 97, 109, 101, 32, 0, 0, 0, 9, 108, 99, 116, 118, 105, 101, 119, 101, 114, 19, 0, 0, 0, 5, 48, 0, 0, 0, 3, 32, 0, 0, 0, 5, 103, 114, 111, 117, 112, 48, 0, 0, 0, 1, 32, 0, 0, 0, 5, 65, 68, 77, 73, 78, 1, 1, 32, 0, 0, 0, 4, 105, 100, 108, 101, 19, 0, 0, 0, 60, 32, 0, 0, 0, 4, 110, 97, 109, 101, 32, 0, 0, 0, 3, 108, 99, 116, 19, 0, 0, 0, 6, 48, 0, 0, 0, 3, 32, 0, 0, 0, 5, 103,
				114, 111, 117, 112, 48, 0, 0, 0, 1, 32, 0, 0, 0, 5, 65, 68, 77, 73, 78, 1, 1, 32, 0, 0, 0, 4, 105, 100, 108, 101, 19, 0, 0, 0, 60, 32, 0, 0, 0, 4, 110, 97, 109, 101, 32, 0, 0, 0, 3, 101, 109, 115, 19, 0, 0, 0, 7, 48, 0, 0, 0, 3, 32, 0, 0, 0, 5, 103, 114, 111, 117, 112, 48, 0, 0, 0, 1, 32, 0, 0, 0, 6, 86, 73, 69, 87, 69, 82, 1, 1, 32, 0, 0, 0, 4, 105, 100, 108, 101, 19, 0, 0, 0, 60, 32, 0, 0, 0, 4, 110, 97, 109, 101, 32, 0, 0, 0, 9, 101, 109, 115, 118, 105, 101, 119, 101, 114, 0, };
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		userObjectList = super.analysisTabble("user", t);
		return userObjectList;
	}
}

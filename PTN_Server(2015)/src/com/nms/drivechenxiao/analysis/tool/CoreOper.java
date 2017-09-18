package com.nms.drivechenxiao.analysis.tool;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.service.impl.CoderUtils;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpBuffer;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
/**统一处理基本类 主要负责解析
 * 
 * **/
public class CoreOper {

	public static String ECXTMSG_REQ_LOGIN = "ECXTMSG_REQ_LOGIN";
	public static String ECXTMSG_REQ_OPS = "ECXTMSG_REQ_OPS";
	public static String ECXTMSG_REQ_ACALL = "ECXTMSG_REQ_ACALL";
	public static String ECXTMSG_NOTIFY = "ECXTMSG_NOTIFY";

	public CxtOpBuffer getCxtOpBuffer(int opCount, List<CxtOpItem> cxtOpItems) {
		CxtOpBuffer cxtOpBuffer = new CxtOpBuffer();
		cxtOpBuffer.setOpCount(opCount);
		cxtOpBuffer.setCxtOpItems(cxtOpItems);
		return cxtOpBuffer;
	}

	public CxtOpItem getCxtOpItem(int op, CxtAtomType cxtAtomType1, CxtAtomType cxtAtomType2) {
		CxtOpItem cxtOpItem = new CxtOpItem();
		cxtOpItem.setOp(op);
		cxtOpItem.setCxtAtomType1(cxtAtomType1);
		cxtOpItem.setCxtAtomType2(cxtAtomType2);
		return cxtOpItem;
	}
	public CxtOpItem getACallCxtOpItem(CxtAtomType cxtAtomType1, CxtAtomType cxtAtomType2) {
		CxtOpItem cxtOpItem = new CxtOpItem();
		cxtOpItem.setCxtAtomType1(cxtAtomType1);
		cxtOpItem.setCxtAtomType2(cxtAtomType2);
		return cxtOpItem;
	}

	public CxtATTable getCxtATTable(int tableCount, List<CxtAtomType> cxtAtomTypes) {
		CxtATTable cxtATTable = new CxtATTable();
		cxtATTable.setTableCount(tableCount);// table数量
		cxtATTable.setCxtAtomTypes(cxtAtomTypes);
		return cxtATTable;
	}

	public CxtAtomType getCxtAtomType(String type, Object value) {
		CxtAtomType cxtAtomType = new CxtAtomType();
		cxtAtomType.setType(type);
		if (CxtAtomType.AT_NUM_32.equalsIgnoreCase(type)) {
			cxtAtomType.setType(type);
			cxtAtomType.setCxtInt(Integer.valueOf(value.toString()));
		} else if (CxtAtomType.AT_NUM_64.equalsIgnoreCase(type)) {
			cxtAtomType.setType(type);
			cxtAtomType.setCxtLong(Long.valueOf(value.toString()));
		} else if (CxtAtomType.AT_NUM_D.equalsIgnoreCase(type)) {
			cxtAtomType.setType(type);
			cxtAtomType.setCxtDouble(Double.valueOf(value.toString()));
		} else if (CxtAtomType.AT_STRING.equalsIgnoreCase(type)) {
			cxtAtomType.setType(type);
			cxtAtomType.setCxtString(value.toString());
		} else if (CxtAtomType.AT_TABLE.equalsIgnoreCase(type)) {
			cxtAtomType.setType(type);
			cxtAtomType.setCxtATTable((CxtATTable) value);
		} else if (CxtAtomType.AT_BOOL.equalsIgnoreCase(type)) {
			cxtAtomType.setType(type);
			cxtAtomType.setBool(String.valueOf(value));
		} else if (CxtAtomType.AT_NUM_8.equalsIgnoreCase(type)) {
			cxtAtomType.setType(type);
			cxtAtomType.setCxtInt(Integer.valueOf(String.valueOf(value)));
		}
		return cxtAtomType;
	}

	public byte[] getHeader(int len, String cxtEMsgType, int session, int seqid, boolean svc) {
		byte[] HeaderCommand = new byte[0];

		byte[] PMark = new byte[] { (byte) CoderUtils.char2ASCII('r'), (byte) CoderUtils.char2ASCII('m'), (byte) CoderUtils.char2ASCII('t'), 0x01 };
		HeaderCommand = arraycopy(HeaderCommand, PMark);

		byte[] _len = null;
		if (svc) {
			_len = CoderUtils.intToBytes(len + 16);
		} else {
			_len = CoderUtils.intToBytes(len + 13);
		}
		HeaderCommand = arraycopy(HeaderCommand, _len);

		byte[] _cxtEMsgType = new byte[1];
		if ("ECXTMSG_REQ_LOGIN".equalsIgnoreCase(cxtEMsgType)) {
			_cxtEMsgType[0] = 0x11;
		} else if ("ECXTMSG_REQ_OPS".equalsIgnoreCase(cxtEMsgType)) {
			_cxtEMsgType[0] = 0x12;
		} else if ("ECXTMSG_REQ_ACALL".equalsIgnoreCase(cxtEMsgType)) {
			_cxtEMsgType[0] = 0x13;
		} else if ("ECXTMSG_NOTIFY".equalsIgnoreCase(cxtEMsgType)) {
			_cxtEMsgType[0] = 0x30;
		}
		HeaderCommand = arraycopy(HeaderCommand, _cxtEMsgType);

		if (svc) {
			byte[] _svcCount = CoderUtils.intToBytes(3);
			HeaderCommand = arraycopy(HeaderCommand, _svcCount);

			byte[] _svc = new byte[] { (byte) CoderUtils.char2ASCII('o'), (byte) CoderUtils.char2ASCII('a'), (byte) CoderUtils.char2ASCII('m') };
			HeaderCommand = arraycopy(HeaderCommand, _svc);
		} else {
			byte[] _svcCount = CoderUtils.intToBytes(0);
			HeaderCommand = arraycopy(HeaderCommand, _svcCount);
						
		}
		byte[] _session = CoderUtils.intToBytes(session);
		HeaderCommand = arraycopy(HeaderCommand, _session);

		byte[] _seqid = CoderUtils.intToBytes(seqid);
		HeaderCommand = arraycopy(HeaderCommand, _seqid);
		return HeaderCommand;
	}
	public byte[] getOAMHeader(int len, String cxtEMsgType, int session, int seqid, boolean svc) {
		byte[] HeaderCommand = new byte[0];

		byte[] PMark = new byte[] { (byte) CoderUtils.char2ASCII('r'), (byte) CoderUtils.char2ASCII('m'), (byte) CoderUtils.char2ASCII('t'), 0x01 };
		HeaderCommand = arraycopy(HeaderCommand, PMark);

		byte[] _len = null;
		if (svc) {
			_len = CoderUtils.intToBytes(len + 16);
		} else {
			_len = CoderUtils.intToBytes(len + 13);
		}
		HeaderCommand = arraycopy(HeaderCommand, _len);

		byte[] _cxtEMsgType = new byte[1];
		if ("ECXTMSG_REQ_LOGIN".equalsIgnoreCase(cxtEMsgType)) {
			_cxtEMsgType[0] = 0x11;
		} else if ("ECXTMSG_REQ_OPS".equalsIgnoreCase(cxtEMsgType)) {
			_cxtEMsgType[0] = 0x12;
		} else if ("ECXTMSG_REQ_ACALL".equalsIgnoreCase(cxtEMsgType)) {
			_cxtEMsgType[0] = 0x13;
		} else if ("ECXTMSG_NOTIFY".equalsIgnoreCase(cxtEMsgType)) {
			_cxtEMsgType[0] = 0x30;
		}
		HeaderCommand = arraycopy(HeaderCommand, _cxtEMsgType);

		if (svc) {
			byte[] _svcCount = CoderUtils.intToBytes(3);
			HeaderCommand = arraycopy(HeaderCommand, _svcCount);

			byte[] _svc = new byte[] { (byte) CoderUtils.char2ASCII('o'), (byte) CoderUtils.char2ASCII('a'), (byte) CoderUtils.char2ASCII('m') };
			HeaderCommand = arraycopy(HeaderCommand, _svc);
		} else {
			byte[] _svcCount = CoderUtils.intToBytes(0);
			HeaderCommand = arraycopy(HeaderCommand, _svcCount);
					
		}
		byte[] _session = CoderUtils.intToBytes(session);
		HeaderCommand = arraycopy(HeaderCommand, _session);

		byte[] _seqid = CoderUtils.intToBytes(seqid);
		HeaderCommand = arraycopy(HeaderCommand, _seqid);	
		return HeaderCommand;
	}

	public byte[] getCxtAtomType(CxtAtomType cxtAtomType) {
		byte[] CxtAtomTypeCommand = new byte[0];
		if (CxtAtomType.AT_NUM_32.equalsIgnoreCase(cxtAtomType.getType())) {
			CxtAtomTypeCommand = getAT_NUM_32(cxtAtomType.getCxtInt());
		} else if (CxtAtomType.AT_NUM_64.equalsIgnoreCase(cxtAtomType.getType())) {
			CxtAtomTypeCommand = getAT_NUM_64(cxtAtomType.getCxtLong());
		} else if (CxtAtomType.AT_NUM_D.equalsIgnoreCase(cxtAtomType.getType())) {
			CxtAtomTypeCommand = getAT_NUM_D(cxtAtomType.getCxtDouble());
		} else if (CxtAtomType.AT_STRING.equalsIgnoreCase(cxtAtomType.getType())) {
			byte[] CxtAtomString = new byte[1];
			CxtAtomString[0] = 0x20;
			CxtAtomTypeCommand = getCxtString(cxtAtomType.getCxtString());
			CxtAtomTypeCommand = arraycopy(CxtAtomString, CxtAtomTypeCommand);
		} else if (CxtAtomType.AT_TABLE.equalsIgnoreCase(cxtAtomType.getType())) {
			byte[] CxtAtomTable = new byte[1];
			CxtAtomTable[0] = 0x30;
			CxtAtomTypeCommand = getCxtATTable(cxtAtomType.getCxtATTable());
			CxtAtomTypeCommand = arraycopy(CxtAtomTable, CxtAtomTypeCommand);
		} else if (CxtAtomType.AT_BOOL.equalsIgnoreCase(cxtAtomType.getType())) {
			byte[] CxtAtomTable = new byte[1];
			CxtAtomTable[0] = 0x01;
			CxtAtomTypeCommand = getAT_BOOL(cxtAtomType.getBool());
			CxtAtomTypeCommand = arraycopy(CxtAtomTable, CxtAtomTypeCommand);
		} else if (CxtAtomType.AT_NUM_8.equalsIgnoreCase(cxtAtomType.getType())) {
			byte[] CxtAtomTable = new byte[1];
			CxtAtomTable[0] = 0x11;
			CxtAtomTypeCommand = new byte[] { (byte) cxtAtomType.getCxtInt() };
			CxtAtomTypeCommand = arraycopy(CxtAtomTable, CxtAtomTypeCommand);
		}
		return CxtAtomTypeCommand;
	}

	public byte[] getAT_NUM_32(int cxtInt) {
		byte[] CxtAtomInt = new byte[5];
		CxtAtomInt[0] = 0x13;
		byte[] _intValue = CoderUtils.intToBytes(cxtInt);
		CxtAtomInt[1] = _intValue[0];
		CxtAtomInt[2] = _intValue[1];
		CxtAtomInt[3] = _intValue[2];
		CxtAtomInt[4] = _intValue[3];
		return CxtAtomInt;
	}

	public byte[] getAT_BOOL(String bool) {
		byte[] CxtAtomBool = new byte[1];
		if ("true".equals(bool)) {
			CxtAtomBool[0] = 0x01;
		} else {
			CxtAtomBool[0] = 0x00;
		}
		return CxtAtomBool;
	}

	public byte[] getAT_NUM_64(long cxtLong) {
		byte[] toByte = new byte[8];
		byte[] CxtAtomLong = new byte[9];
		toByte = CoderUtils.longToBytes(cxtLong);
		CxtAtomLong[0] = 0x14;
		CxtAtomLong[1] = toByte[0];
		CxtAtomLong[2] = toByte[1];
		CxtAtomLong[3] = toByte[2];
		CxtAtomLong[4] = toByte[3];
		CxtAtomLong[5] = toByte[4];
		CxtAtomLong[6] = toByte[5];
		CxtAtomLong[7] = toByte[6];
		CxtAtomLong[8] = toByte[7];
		return CxtAtomLong;
	}

	public byte[] getAT_NUM_D(Double cxtDouble) {
		byte[] toByte = new byte[8];
		byte[] CxtAtomDouble = new byte[9];
		toByte = CoderUtils.doubleToByte(cxtDouble);
		CxtAtomDouble[0] = 0x1a;
		CxtAtomDouble[1] = toByte[7];
		CxtAtomDouble[2] = toByte[6];
		CxtAtomDouble[3] = toByte[5];
		CxtAtomDouble[4] = toByte[4];
		CxtAtomDouble[5] = toByte[3];
		CxtAtomDouble[6] = toByte[2];
		CxtAtomDouble[7] = toByte[1];
		CxtAtomDouble[8] = toByte[0];
		return CxtAtomDouble;
	}

	public byte[] getCxtString(String cxtString) {
		byte[] CxtAtomString = new byte[0];
		byte[] _stringValueLen = CoderUtils.intToBytes(cxtString.length());
		CxtAtomString = arraycopy(CxtAtomString, _stringValueLen);
		byte[] _stringValueByte = cxtString.getBytes();
		CxtAtomString = arraycopy(CxtAtomString, _stringValueByte);
		return CxtAtomString;
	}

	public byte[] getCxtATTable(CxtATTable cxtATTable) {
		byte[] CxtAtomTable = new byte[0];
		byte[] _tableCountValue = CoderUtils.intToBytes(cxtATTable.getTableCount());
		CxtAtomTable = arraycopy(CxtAtomTable, _tableCountValue);

		List<CxtAtomType> cxtAtomTypeList = cxtATTable.getCxtAtomTypes();
		byte[] _CxtAtomTypeCommand = null;
		int count = 1;
		for (int i = 0; i < cxtAtomTypeList.size(); i++) {
			// CxtAtomTable = arraycopy(CxtAtomTable, getAT_NUM_32(count));
			_CxtAtomTypeCommand = getCxtAtomType(cxtAtomTypeList.get(i));
			CxtAtomTable = arraycopy(CxtAtomTable, _CxtAtomTypeCommand);
			count++;
		}
		return CxtAtomTable;
	}

	public byte[] getCxtOpItem(CxtOpItem cxtOpItem) {
		byte[] CxtOpItemCommand = new byte[0];
		byte[] _op = CoderUtils.intToBytes(cxtOpItem.getOp());
		CxtOpItemCommand = arraycopy(CxtOpItemCommand, _op);

		if (cxtOpItem.getCxtAtomType1() != null) {
			byte[] _attr1 = getCxtAtomType(cxtOpItem.getCxtAtomType1());
			CxtOpItemCommand = arraycopy(CxtOpItemCommand, _attr1);
		} else {
			byte[] _attr1 = new byte[1];
			_attr1[0] = 0x00;
			CxtOpItemCommand = arraycopy(CxtOpItemCommand, _attr1);
		}

		if (cxtOpItem.getCxtAtomType2() != null) {
			byte[] _attr2 = getCxtAtomType(cxtOpItem.getCxtAtomType2());
			CxtOpItemCommand = arraycopy(CxtOpItemCommand, _attr2);
		} else {
			byte[] _attr2 = new byte[1];
			_attr2[0] = 0x00;
			CxtOpItemCommand = arraycopy(CxtOpItemCommand, _attr2);
		}

		return CxtOpItemCommand;
	}

	public byte[] getCxtOpBuffer(CxtOpBuffer cxtOpBuffer) {
		byte[] CxtOpBufferCommand = new byte[0];
		byte[] _opCount = CoderUtils.intToBytes(cxtOpBuffer.getOpCount());
		CxtOpBufferCommand = arraycopy(CxtOpBufferCommand, _opCount);

		List<CxtOpItem> cxtOpItemList = cxtOpBuffer.getCxtOpItems();
		byte[] _CxtOpItemCommand = null;
		for (int i = 0; i < cxtOpItemList.size(); i++) {
			_CxtOpItemCommand = getCxtOpItem(cxtOpItemList.get(i));
			CxtOpBufferCommand = arraycopy(CxtOpBufferCommand, _CxtOpItemCommand);
		}

		return CxtOpBufferCommand;
	}
	public byte[] getCallCxtOpBuffer(CxtOpBuffer cxtOpBuffer) {
		byte[] CxtOpBufferCommand = new byte[0];
//		byte[] _opCount = CoderUtils.intToBytes(cxtOpBuffer.getOpCount());
//		CxtOpBufferCommand = arraycopy(CxtOpBufferCommand, _opCount);

		List<CxtOpItem> cxtOpItemList = cxtOpBuffer.getCxtOpItems();
		byte[] _CxtOpItemCommand = null;
		for (int i = 0; i < cxtOpItemList.size(); i++) {
			_CxtOpItemCommand = getCxtOpItem(cxtOpItemList.get(i));
			CxtOpBufferCommand = arraycopy(CxtOpBufferCommand, _CxtOpItemCommand);
		}

		return CxtOpBufferCommand;
	}
/**add by stones 20130709
从命令 byte[] 解析对象出来。
 * 
 * **/
	public CxtOpBuffer analysisCxtOpBuffer(byte[] opBufferBytes) {
		CxtOpBuffer cxtOpBuffer = new CxtOpBuffer();
		int i = 30;
		byte[] opCountBytes = new byte[] { opBufferBytes[i], opBufferBytes[i++], opBufferBytes[i++], opBufferBytes[i++] };
		int opCount = CoderUtils.bytesToInt(opCountBytes);
		cxtOpBuffer.setOpCount(opCount);

		while (i != opBufferBytes.length) {
			byte[] opBytes = new byte[] { opBufferBytes[i++], opBufferBytes[i++], opBufferBytes[i++], opBufferBytes[i++] };
			int op = CoderUtils.bytesToInt(opBytes);// 第几个OP
			CxtOpItem cxtOpItem = new CxtOpItem();
			cxtOpItem.setOp(op);

			CxtAtomType cxtAtomType1 = null;
			byte type = opBufferBytes[i++];
			if (type == 0x00) {
				cxtAtomType1 = null;
			} else if (type == 0x13) {
				cxtAtomType1 = analysisAT_NUM_32(opBufferBytes[i++], opBufferBytes[i++], opBufferBytes[i++], opBufferBytes[i++]);
			} else if (type == 0x20) {
				byte[] stringLenByte = new byte[] { opBufferBytes[i++], opBufferBytes[i++], opBufferBytes[i++], opBufferBytes[i++] };
				int stringLen = CoderUtils.bytesToInt(stringLenByte);
				byte[] stringBytes = new byte[stringLen];
				for (int j = 0; j < stringLen; j++) {
					stringBytes[j] = opBufferBytes[i++];
				}
				cxtAtomType1 = analysisCxtString(stringBytes);
			} else if (type == 0x30) {
				cxtAtomType1 = new CxtAtomType();
				cxtAtomType1.setType(CxtAtomType.AT_TABLE);
				byte[] tableCountByte = new byte[] { opBufferBytes[i++], opBufferBytes[i++], opBufferBytes[i++], opBufferBytes[i++] };
				int tableCount = CoderUtils.bytesToInt(tableCountByte);
				CxtATTable cxtATTable = new CxtATTable();
				cxtATTable.setTableCount(tableCount);

				for (int k = 0; k < (tableCount * 2); k++) {
					CxtAtomType tableCxtAtomType = null;
					byte tableType = opBufferBytes[i++];
					if (tableType == 0x13) {
						tableCxtAtomType = analysisAT_NUM_32(opBufferBytes[i++], opBufferBytes[i++], opBufferBytes[i++], opBufferBytes[i++]);
					} else if (tableType == 0x20) {
						byte[] stringLenByte = new byte[] { opBufferBytes[i++], opBufferBytes[i++], opBufferBytes[i++], opBufferBytes[i++] };
						int stringLen = CoderUtils.bytesToInt(stringLenByte);
						byte[] stringBytes = new byte[stringLen];
						for (int j = 0; j < stringLen; j++) {
							stringBytes[j] = opBufferBytes[i++];
						}
						tableCxtAtomType = analysisCxtString(stringBytes);
					}
					if (tableCxtAtomType != null) {
						cxtATTable.getCxtAtomTypes().add(tableCxtAtomType);
					}
				}
				cxtAtomType1.setCxtATTable(cxtATTable);
			}
			cxtOpItem.setCxtAtomType1(cxtAtomType1);

			CxtAtomType cxtAtomType2 = null;
			byte type2 = opBufferBytes[i++];
			if (type2 == 0x00) {
				cxtAtomType2 = null;
			} else if (type2 == 0x13) {
				cxtAtomType2 = analysisAT_NUM_32(opBufferBytes[i++], opBufferBytes[i++], opBufferBytes[i++], opBufferBytes[i++]);
			} else if (type2 == 0x20) {
				byte[] stringLenByte = new byte[] { opBufferBytes[i++], opBufferBytes[i++], opBufferBytes[i++], opBufferBytes[i++] };
				int stringLen = CoderUtils.bytesToInt(stringLenByte);
				byte[] stringBytes = new byte[stringLen];
				for (int j = 0; j < stringLen; j++) {
					stringBytes[j] = opBufferBytes[i++];
				}
				cxtAtomType2 = analysisCxtString(stringBytes);
			} else if (type2 == 0x30) {
				cxtAtomType2 = new CxtAtomType();
				cxtAtomType2.setType(CxtAtomType.AT_TABLE);
				byte[] tableCountByte = new byte[] { opBufferBytes[i++], opBufferBytes[i++], opBufferBytes[i++], opBufferBytes[i++] };
				int tableCount = CoderUtils.bytesToInt(tableCountByte);
				CxtATTable cxtATTable = new CxtATTable();
				cxtATTable.setTableCount(tableCount);

				for (int k = 0; k < (tableCount * 2); k++) {
					CxtAtomType tableCxtAtomType = null;
					byte tableType = opBufferBytes[i++];
					if (tableType == 0x13) {
						tableCxtAtomType = analysisAT_NUM_32(opBufferBytes[i++], opBufferBytes[i++], opBufferBytes[i++], opBufferBytes[i++]);
					} else if (tableType == 0x20) {
						byte[] stringLenByte = new byte[] { opBufferBytes[i++], opBufferBytes[i++], opBufferBytes[i++], opBufferBytes[i++] };
						int stringLen = CoderUtils.bytesToInt(stringLenByte);
						byte[] stringBytes = new byte[stringLen];
						for (int j = 0; j < stringLen; j++) {
							stringBytes[j] = opBufferBytes[i++];
						}
						tableCxtAtomType = analysisCxtString(stringBytes);
					}
					if (tableCxtAtomType != null) {
						cxtATTable.getCxtAtomTypes().add(tableCxtAtomType);
					}
				}
				cxtAtomType2.setCxtATTable(cxtATTable);
			}
			cxtOpItem.setCxtAtomType2(cxtAtomType2);

			cxtOpBuffer.getCxtOpItems().add(cxtOpItem);
		}
		return cxtOpBuffer;
	}

	public CxtAtomType analysisAT_NUM_32(byte num1, byte num2, byte num3, byte num4) {
		CxtAtomType CxtAtomType = new CxtAtomType();
		CxtAtomType.setType(CxtAtomType.AT_NUM_32);
		CxtAtomType.setCxtInt(CoderUtils.bytesToInt(new byte[] { num1, num2, num3, num4 }));
		return CxtAtomType;
	}

	public CxtAtomType analysisCxtString(byte[] strBytes) {
		CxtAtomType CxtAtomType = new CxtAtomType();
		CxtAtomType.setType(CxtAtomType.AT_STRING);
		CxtAtomType.setCxtString(new String(strBytes));
		return CxtAtomType;
	}

	public byte[] arraycopy(byte[] a1, byte[] b2) {
		byte[] c3 = new byte[a1.length + b2.length];
		System.arraycopy(a1, 0, c3, 0, a1.length);
		System.arraycopy(b2, 0, c3, a1.length, b2.length);
		return c3;
	}
/**转换发送字节流为 字符串 --add by stonesLi 20130709 **/
	public static String print16String(byte b) { 
		byte[] temp = new byte[4];
		temp[0] = 0x00;
		temp[1] = 0x00;
		temp[2] = 0x00;
		temp[3] = b;
		String value16 = CoderUtils.intTo16String(CoderUtils.bytesToInt(temp));
		return value16;
		}
	public static String print16String(byte[] bytes) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("00000000  ");
		int count = 1;
		int index = 1;
		for (int i = 0; i < bytes.length; i++) {
			byte[] temp = new byte[4];
			temp[0] = 0x00;
			temp[1] = 0x00;
			temp[2] = 0x00;
			temp[3] = bytes[i];
			String value16 = CoderUtils.intTo16String(CoderUtils.bytesToInt(temp));
			if (value16.length() == 1) {
				value16 = "0" + value16;
			}
			buffer.append(value16 + " ");
			if (count != 0 && count % 8 == 0) {
				buffer.append(" ");
			}
			if (count != 0 && count % 16 == 0) {
				buffer.append("\r\n");
				String value16Index = CoderUtils.intTo16String(index);
				value16Index += "0";
				int bq = 8 - value16Index.length();
				String tempIndexStr = "";
				for (int j = 0; j < bq; j++) {
					tempIndexStr += "0";
				}
				tempIndexStr += value16Index;
				buffer.append(tempIndexStr + "  ");
				index++;
			}
			count++;
		}
		System.out.println(buffer.toString());
		return buffer.toString();
	}

	public byte[] getCommandBytes(List<CxtOpItem> cxtOpItems) {
		List<CxtOpItem> cxtOpItems1 = cxtOpItems;
		CxtOpBuffer cxtOpBuffer = getCxtOpBuffer(cxtOpItems1.size(), cxtOpItems1);
		byte[] cxtOpBufferbyte = getCxtOpBuffer(cxtOpBuffer);
		return cxtOpBufferbyte;
	}
	public byte[] getACallCommandBytes(List<CxtOpItem> cxtOpItems) {
		List<CxtOpItem> cxtOpItems1 = cxtOpItems;
		CxtOpBuffer cxtOpBuffer = getCxtOpBuffer(cxtOpItems1.size(), cxtOpItems1);
		byte[] cxtOpBufferbyte = getCallCxtOpBuffer(cxtOpBuffer);
		return cxtOpBufferbyte ;
	}

	public byte[] getHeaderCommandBytes(String type, byte[] command, int session, int seqid) {
		byte[] head = getHeader(command.length, type, session, seqid, false);
		byte[] HeaderCommandBytes = arraycopy(head, command);
		return HeaderCommandBytes;
	}
	public byte[] getHeaderOAMCommandBytes(String type, byte[] command, int session, int seqid) {
		byte[] head = getOAMHeader(command.length, type, session, seqid, true);
		byte[] HeaderCommandBytes = arraycopy(head, command);
		return HeaderCommandBytes;
	}

	public byte[] cxtOpItemsToCommand(List<CxtOpItem> cxtOpItems, int session, int seqid) {
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	private int num = 5;
	private int isMore = 0;
	private List list = new ArrayList();
	private String oldAttributeName = "";
	private String oldoldAttributeName = "";
	private String attributeName = "";

	/**
	 * 解析tabble
	 * 
	 * @param command
	 * @return
	 */
	public List analysisTabble(String type, byte[] command) {
		List objectList = new ArrayList();
		Object object = null;
		analysisCurrencyTabble(1, type, num, command, object);

		num = 5;
		isMore = 0;
		objectList = list;
		list = new ArrayList();
		return objectList;
	}

	public List analysisTabble(int isMore, String type, byte[] command) {
		this.isMore = isMore;
		return analysisTabble(type, command);
	}
/**numberTimes : table 的层数；
 * type 	   : 类型 
 * count       :命令下标，(默认5 为 从第5位开始截取解析，头5位为 30 + 4位table个数。)
 * **/
	private Object analysisCurrencyTabble(int numberTimes, String type, int count, byte[] command, Object object) {
		int identifying = 1;
//if("allethport".equals(type)&&"nni".equals(oldAttributeName)){	System.out.println("___numberTimes="+numberTimes+" ,type="+type+" ;count="+count+" ;command.length="+command.length+" ");}		
//System.out.println("command[48] = "+command[count - 1]);
		for (int i = 0; i < command[count - 1] * 2; i++) {
//if ((identifying % 2 == 0)&&"allethport".equals(type) && "mac".equals(attributeName)) {
//	//调试输出对象的传输格式
//	System.out.println("-- command[num] = "+CoreOper.print16String(command[num]));
//	System.out.println("-- ");
//	if(command[num] == 0x1a){
//		byte[] bt = new byte[8];
//		System.arraycopy(command, num + 1, bt, 0, 8);
//		System.out.println("-- bt_1a = "+CoreOper.print16String(bt));
//		System.out.println("-- bt_1a to ip = "+CoderUtils.MacBy1a(bt));
//	}if(command[num] == 0x13){
//		byte[] bt = new byte[4];
//		System.arraycopy(command, num + 1, bt, 0, 4);
//		System.out.println("-- bt_13 = "+CoreOper.print16String(bt));
//		System.out.println("-- bt_13 to ip = "+CoderUtils.MacBy13(bt));
//	}
//}		
//if("alarm".equals(type)){System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");}			
			if (command[num] == 0x20) {
				 byte[] lengByte = new byte[4];
				 System.arraycopy(command,num+1,lengByte,0,4);
				 int leng = CoderUtils.bytesToInt(lengByte);
//				byte[] b = new byte[command[num + 4]];
//				System.arraycopy(command, num + 4 + 1, b, 0, command[num + 4]);
				 byte[] b = new byte[leng];
			 	 System.arraycopy(command, num + 4 + 1, b, 0, leng);
				if (identifying % 2 == 0) {
					SetObject setObject = new SetObject(numberTimes, oldAttributeName, oldoldAttributeName, b);
					object = setObject.setObjectValue(type, new String(b).toString().trim(), attributeName, object);
//if("allethport".equals(type))System.out.println("allethport-----numberTimes = "+numberTimes+"0x20 : attributeName = "+attributeName+" ; new String(b).toString().trim() = "+new String(b).toString().trim());
				} else {
					attributeName = new String(b).toString().trim();
					
				}
 				num = num + 5 + leng;
			} else if (command[num] == 0x13) {
				byte[] b = new byte[4];
				System.arraycopy(command, num + 1, b, 0, 4);
				if (identifying % 2 == 0) {
					if ("ne".equals(type) && "id".equals(attributeName)) {
						SetObject setObject = new SetObject(numberTimes, oldAttributeName, oldoldAttributeName, b);
//						object = setObject.setObjectValue(type, "" + CoderUtils.bytesToInt(b[0]) + "." + CoderUtils.bytesToInt(b[1]) + "." + CoderUtils.bytesToInt(b[2]) + "." + CoderUtils.bytesToInt(b[3]), attributeName, object);
						object = setObject.setObjectValue(type, String.valueOf(CoderUtils.bytesToInt(b)), attributeName, object);
					}else if ("ACN".equals(type) && "ifname".equals(attributeName)) {
						SetObject setObject = new SetObject(numberTimes, oldAttributeName, oldoldAttributeName, b);
						object = setObject.setObjectValue(type, String.valueOf(CoderUtils.bytesToInt(b[3])), attributeName, object);
					}else if ("tunnel".equals(type) && ("lspw".equals(attributeName)||"ifname".equals(attributeName))) {
						SetObject setObject = new SetObject(numberTimes, oldAttributeName, oldoldAttributeName, b);
						object = setObject.setObjectValue(type, String.valueOf(CoderUtils.bytesToInt(b[3])), attributeName, object);
					}else if ("allethport".equals(type) && "mac".equals(attributeName)) {
						SetObject setObject = new SetObject(numberTimes, oldAttributeName, oldoldAttributeName, b);
						object = setObject.setObjectValue(type, CoderUtils.MacBy13(b), attributeName, object);
					}else if ("allethport".equals(type) && ("ifname".equals(attributeName) || "ifindex".equals(attributeName)) ) {
						SetObject setObject = new SetObject(numberTimes, oldAttributeName, oldoldAttributeName, b);
						object = setObject.setObjectValue(type, CoderUtils.x13ToifName(b), attributeName, object);
					}
					else if ("lag".equals(type) && "mac".equals(attributeName)) {
						SetObject setObject = new SetObject(numberTimes, oldAttributeName, oldoldAttributeName, b);
						object = setObject.setObjectValue(type, CoderUtils.MacBy13(b), attributeName, object);						
					}else if ("Ring".equals(type) && ("westport".equals(attributeName) || "eastport".equals(attributeName))) {
						SetObject setObject = new SetObject(numberTimes, oldAttributeName, oldoldAttributeName, b);
						object = setObject.setObjectValue(type, CoderUtils.x13ToifName(b), attributeName, object);						
					}else if ("Ring".equals(type) && attributeName.indexOf("xpdu")>1 ) {
						SetObject setObject = new SetObject(numberTimes, oldAttributeName, oldoldAttributeName, b);
						object = setObject.setObjectValue(type, CoderUtils.x13ToU32Toxpdu(b), attributeName, object);						
					}else if ("tod".equals(type) && "ifname".equals(attributeName)) {
						SetObject setObject = new SetObject(numberTimes, oldAttributeName, oldoldAttributeName, b);
						object = setObject.setObjectValue(type, CoderUtils.x13ToifName(b), attributeName, object);						
					}else if ("extclk".equals(type) && "ifname".equals(attributeName)) {
						SetObject setObject = new SetObject(numberTimes, oldAttributeName, oldoldAttributeName, b);
						object = setObject.setObjectValue(type, CoderUtils.x13ToifName(b), attributeName, object);						
					}else if ("Msp".equals(type) && ("prtport".equals(attributeName) || "workport".equals(attributeName) ) ) {
						SetObject setObject = new SetObject(numberTimes, oldAttributeName, oldoldAttributeName, b);
						object = setObject.setObjectValue(type, CoderUtils.x13ToifName(b), attributeName, object);						
					}else if ( "cmapl2".equals(type) && ("dstmac".equals(attributeName)||"dstmacmask".equals(attributeName)||"srcmac".equals(attributeName)||"srcmacmask".equals(attributeName) )) {
						SetObject setObject = new SetObject(numberTimes, oldAttributeName, oldoldAttributeName, b);
						object = setObject.setObjectValue(type, CoderUtils.MacBy13(b), attributeName, object);						
					}else if ("staticRoute".equals(type) && "nexthop".equals(attributeName)) {
						SetObject setObject = new SetObject(numberTimes, oldAttributeName, oldoldAttributeName, b);
						object = setObject.setObjectValue(type, CoderUtils.longToIpAddress(CoderUtils.bytesToInt(b)), attributeName, object);
					}else if("protectLog".equals(type)&&"2".equals(attributeName)){//0x00,0x00,0x08,0x01
						SetObject setObject = new SetObject(numberTimes, oldAttributeName, oldoldAttributeName, b);
						object = setObject.setObjectValue(type, String.valueOf(b[3]), attributeName, object);
					}else if("hooknotify".equals(type)&&"1".equals(attributeName)){//0x00,0x02,0x00,0x01
						SetObject setObject = new SetObject(numberTimes, oldAttributeName, oldoldAttributeName, b);
						object = setObject.setObjectValue(type, String.valueOf(b[3]), attributeName, object);
					}
					else {
						SetObject setObject = new SetObject(numberTimes, oldAttributeName, oldoldAttributeName, b);
						object = setObject.setObjectValue(type, String.valueOf(CoderUtils.bytesToInt(b)), attributeName, object);
					}
//if("alllsp".equals(type))System.out.println("numberTimes = "+numberTimes+"0x13 : attributeName = "+attributeName+" ; String.valueOf(CoderUtils.bytesToInt(b)) = "+String.valueOf(CoderUtils.bytesToInt(b))+" ; b = "+CoreOper.print16String(b));						
				} else {
					attributeName = String.valueOf(CoderUtils.bytesToInt(b));
				}
				num = num + 5;
 			} else if (command[num] == 0x1a) {
				byte[] b = new byte[8];
				System.arraycopy(command, num + 1, b, 0, 8);
				if (identifying % 2 == 0) {
//System.out.println("---- CoreOper . : 0x1a : type="+type+" ; attributeName="+attributeName+" ; macby1a="+CoderUtils.MacBy1a(b)+" ;oldAttributeName="+oldAttributeName+" ;oldoldAttributeName="+oldoldAttributeName);					
//					if ("alllsp".equals(type) && "peer".equals(attributeName)) {
//						SetObject setObject = new SetObject(numberTimes, oldAttributeName, oldoldAttributeName, b);
//						object = setObject.setObjectValue(type, CoderUtils.a1ToIP200(b), attributeName, object);
//					}
				    if ("allethport".equals(type) && ("mac".equals(attributeName)||"smac".equals(attributeName) ) ) {
						SetObject setObject = new SetObject(numberTimes, oldAttributeName, oldoldAttributeName, b);
//System.out.println("0x1a:mac:allethport . type="+type+" ;object.name="+((EthPortObject)object).getName()+" ;attributeName="+attributeName+" ;CoderUtils.MacBy1a(b)="+CoderUtils.MacBy1a(b)+" ; b[]="+CoreOper.print16String(b));						
						object = setObject.setObjectValue(type, CoderUtils.MacBy1a(b), attributeName, object);
					}
				    else if ("lag".equals(type) && "mac".equals(attributeName)) {
						SetObject setObject = new SetObject(numberTimes, oldAttributeName, oldoldAttributeName, b);
						object = setObject.setObjectValue(type, CoderUtils.MacBy1a(b), attributeName, object);
					}
				    else if("persvr".equals(type)){
				    	identifying--;
				    }
					else{
					SetObject setObject = new SetObject(numberTimes, oldAttributeName, oldoldAttributeName, b);			
					object = setObject.setObjectValue(type, CoderUtils.a1ToTimeL(b) + "", attributeName, object);
	//	System.out.println("0x1a : .b[]="+CoreOper.print16String(b));			
					}
				} else {
					attributeName = String.valueOf(CoderUtils.bytesToInt(b));
				}
				num = num + 9;
			}
 			else if (command[num] == 0x14) {
				byte[] b = new byte[8];
				System.arraycopy(command, num + 1, b, 0, 8);
				if (identifying % 2 == 0) {
					if ("alarm".equals(type) && "4".equals(attributeName)) {
						SetObject setObject = new SetObject(numberTimes, oldAttributeName, oldoldAttributeName, b);
						object = setObject.setObjectValue(type,CoderUtils.byteToTime("14", b), attributeName, object);
					}else if("hooknotify".equals(type) && "4".equals(attributeName)){
						SetObject setObject = new SetObject(numberTimes, oldAttributeName, oldoldAttributeName, b);			
						object = setObject.setObjectValue(type, CoderUtils.byteToTime("14", b) + "", attributeName, object);
					}
					
					else{
					SetObject setObject = new SetObject(numberTimes, oldAttributeName, oldoldAttributeName, b);
					object = setObject.setObjectValue(type, CoderUtils.byteToLong(b) + "", attributeName, object);
//	System.out.println("0x14 : attributeName = "+attributeName+" ; byteToLong(b) = "+CoderUtils.byteToLong(b));	
					}
				} else {
					attributeName = String.valueOf(CoderUtils.bytesToInt(b));
				}
				num = num + 9;
			} else if (command[num] == 0x01) {
				byte[] b = new byte[1];
				System.arraycopy(command, num + 1, b, 0, 1);
				String bool = "";
				if (b[0] == 0x00) {
					bool = "false";
				} else if (b[0] == 0x01) {
					bool = "true";
				}
				num = num + 2;
				SetObject setObject = new SetObject(numberTimes, oldAttributeName, oldoldAttributeName, b);
				object = setObject.setObjectValue(type, bool, attributeName, object);
			} else if (command[num] == 0x30) {
				
				/**
				 * 如果  area.32   下有2个table   则  此  ospf id 32  的 类别为none
				 */
				if("ospfarea".equals(type)){
					if(command[num+4]==2&&attributeName.contains("area.")){
						oldoldAttributeName=attributeName;
						SetObject setObject = new SetObject(numberTimes, oldAttributeName, oldoldAttributeName, null);
						object = setObject.setObjectValue(type, null + "", attributeName, object);
						list.add(object);
						object=null;
					}
				}
				num = num + 5;
//	System.out.println(" ++ oldAttributeName="+oldAttributeName+" ; identifying="+identifying);			
				oldAttributeName = attributeName;
//System.out.println("=0x30 ; oldAttributeName=attributeName="+attributeName+" ;numberTimes="+numberTimes+" ;num="+num+" ;type="+type+" ;oldoldAttributeName="+oldoldAttributeName);				
				if (numberTimes == 1 && object == null) {
//System.out.print(" --- in if ");					
					oldoldAttributeName = oldAttributeName;
					object = analysisCurrencyTabble(2, type, num, command, null);
					oldoldAttributeName = "";
				} else {
//System.out.print(" --- in else ");					
					object = analysisCurrencyTabble(3, type, num, command, object);
				}
				oldAttributeName = "";
			} else if (command[num] == 0x21) {
				byte[] b = new byte[command[num + 4]];
				System.arraycopy(command, num + 4 + 1, b, 0, command[num + 4]);
				if (identifying % 2 == 0) {
					SetObject setObject = new SetObject(numberTimes, oldAttributeName, oldoldAttributeName, b);
					object = setObject.setObjectValue(type, new String(b).toString().trim(), attributeName, object);
//System.out.println("0x21 : attributeName = "+attributeName+" ; String(b) = "+new String(b).toString().trim());					
				} else {
					attributeName = new String(b).toString().trim();
				}
				num = num + 5 + command[num + 4];
			}
			identifying++;
		}
		if (object != null && (numberTimes == 1 || (isMore == 0 && numberTimes == 2))) {
			list.add(object);
			object = null;
		} else if ((isMore == 3 && numberTimes == 3)) {
			list.add(object);
			object = null;
		}
		
		return object;
	}
	/**add by stones 20130906
	 *  comtype 命令类型
	 *  command 命令字节码
	 *  objType 命令归属对象
	 *  objAttr 命令归属对象的属性名
	 * **/
	private Object ana(int comtype,byte[] command,String objType,String objAttr){
		if(comtype == 20){
			byte[] lengByte = new byte[4];
			 System.arraycopy(command,num+1,lengByte,0,4);
			 int leng = CoderUtils.bytesToInt(lengByte);
			 byte[] b = new byte[leng];
		 	 System.arraycopy(command, num + 4 + 1, b, 0, leng);
		 	 return new String(b).toString().trim();
		}
		
		return null;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}
	public  static void main(String args[]){
		byte[] b=new byte[]{(byte) 0xFF, (byte) 0xFF ,(byte) 0xFF ,(byte) 0xF9};
		byte[] t=new byte[]{0x00,0x0,(byte) 0x8F,(byte) 0xEA};
		System.out.println(String.valueOf(CoderUtils.bytesToInt(b)));
	}
	
}

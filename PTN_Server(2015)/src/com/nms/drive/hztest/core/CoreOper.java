package com.nms.drive.hztest.core;

import java.util.List;

import com.nms.drive.hztest.core.bean.CxtATTable;
import com.nms.drive.hztest.core.bean.CxtAtomType;
import com.nms.drive.hztest.core.bean.CxtOpBuffer;
import com.nms.drive.hztest.core.bean.CxtOpItem;
import com.nms.drive.service.impl.CoderUtils;

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
		} else if (CxtAtomType.AT_STRING.equalsIgnoreCase(type)) {
			cxtAtomType.setType(type);
			cxtAtomType.setCxtString(value.toString());
		} else if (CxtAtomType.AT_TABLE.equalsIgnoreCase(type)) {
			cxtAtomType.setType(type);
			cxtAtomType.setCxtATTable((CxtATTable) value);
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

	public byte[] getCxtAtomType(CxtAtomType cxtAtomType) {
		byte[] CxtAtomTypeCommand = new byte[0];
		if (CxtAtomType.AT_NUM_32.equalsIgnoreCase(cxtAtomType.getType())) {
			CxtAtomTypeCommand = getAT_NUM_32(cxtAtomType.getCxtInt());
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

}

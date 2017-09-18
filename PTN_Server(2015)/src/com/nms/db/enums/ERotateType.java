package com.nms.db.enums;

/**
 * 枚举
 * 倒换命令
 * @author sy
 *
 */
public enum ERotateType {
	FORCESWORK (0), //陈晓：强制倒换到工作侧--武汉： 恢复到主用
	FORCESPRO(1), //陈晓：强制倒换到保护侧--武汉： 强制到备用
	 MANUALWORK(2), //陈晓：人工倒换到工作侧--武汉： 锁定到主用
	 MANUALPRO(3),//陈晓：人工倒换到保护侧--武汉： 人工倒换到备用
	 CLEAR(4), //清除
	 LOCK(5),//锁定
	PRACTICEJOB(6),
	PRACTICEPRO(7);
	
	private int value=-1;

	private ERotateType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static ERotateType forms(int value) {
		for (ERotateType rotateType : ERotateType.values()) {
			if (value == rotateType.value)
				return rotateType;
		}
		return null;
	}

	@Override
	public String toString() {
		
		if (value == 0)
			return "forceswitch::work";
		else if (value == 1)
			return "forceswitch::protection";
		
		else if (value == 2)
			return "manualswitch::work";
		else if (value == 3)
			return "manualswitch::protection";
		else if (value == 4)
			return "clear";
		else if (value == 5)
			return "lock::protection";
		else if (value == 6)
			return "exercise::work";
		else if (value == 7)
			return "exercise::protection";
		else
			return "";
	}
	/**
	 * 根据设备返回的值，转为int
	 * @param key
	 * @return
	 */
	public static int getByValue(String key) {
		if (key.equals("clear"))
			return 4;
		else if (key.equals("lock::protection"))
			return 5;
		else if (key.equals("forceswitch::protection"))//强制倒换到工作侧
			return 1;
		else if (key.equals("forceswitch::work"))
			return 0;
		else if (key.equals("manualswitch::protection"))
			return 2;
		else if (key.equals("manualswitch::work"))
			return 3;
		else if (key.equals("exercise::work"))
			return 6;
		else if (key.equals("exercise::protection"))
			return 7;
		else
			return -1;
	}
}

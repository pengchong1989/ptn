package com.nms.corba.ninterface.util;

/**
 * 整理武汉与陈晓 不统一部分： AC属性-与Corba对象转换时， 陈晓-出口规则：武汉下话TAG
 *              在与北向接口转换时：统一只有4个选项： 即  0不处理    1 新增2删除3更新
 * @author sy
 *
 */
public enum EexitRule {
	UNDEL(0),INSERT(1),DELETE(2),UPDATE(3);
	private int value;
	
	private EexitRule(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static EexitRule from(int value) {
		for (EexitRule exiteRule : EexitRule.values()) {
			if (value == exiteRule.value)
				return exiteRule;
		}
		return null;
	}
}

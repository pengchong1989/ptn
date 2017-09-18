package com.nms.db.dao.ptn.path.protect;

import java.util.List;

import com.nms.db.bean.ptn.path.protect.Protect_Corba;


public interface ProtectCorbaMapper {
	List<Protect_Corba> queryProtectByCondition(Protect_Corba protect_Corba);
}
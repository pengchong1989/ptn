package com.nms.db.dao.ptn.path.protect;

import java.util.List;

import com.nms.db.bean.ptn.path.protect.ProtectRorateInfo;

public interface ProtectRorateInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProtectRorateInfo record);

    int insertSelective(ProtectRorateInfo record);

    ProtectRorateInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProtectRorateInfo record);

    int updateByPrimaryKey(ProtectRorateInfo record);

	List<ProtectRorateInfo> queryByCondition(ProtectRorateInfo protectRorateInfo);
}
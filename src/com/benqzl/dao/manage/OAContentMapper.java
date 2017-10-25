package com.benqzl.dao.manage;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.manage.OAContent;

public interface OAContentMapper {

    int insert(List<OAContent> list);

    OAContent selectByPrimaryKey(String id);

    List<OAContent> selectByAll(Map<String, Object> map);
}
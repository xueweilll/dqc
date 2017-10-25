package com.benqzl.dao.production;

import java.util.List;

import com.benqzl.pojo.production.DefectItems;


public interface DefectItemsMapper {
    int insert(List<DefectItems> list);
    
    int deleteAll();
    
    List<DefectItems> selectByParentId(Integer parentId);

}
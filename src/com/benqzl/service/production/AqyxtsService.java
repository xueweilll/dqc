package com.benqzl.service.production;

import com.benqzl.pojo.production.Aqyxts;

public interface AqyxtsService {
	int update(Aqyxts record)throws Exception;

    Aqyxts selectByPrimaryKey()throws Exception;
}

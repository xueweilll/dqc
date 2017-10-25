package com.benqzl.service.system;

import java.util.List;
import java.util.Map;

import com.benqzl.pojo.system.User;

public interface UserService {
	int deleteByPrimaryKey(String userid)throws Exception;

	int insert(User record)throws Exception;

	int insertSelective(User record)throws Exception;

	User selectByPrimaryKey(String userid)throws Exception;

	int updateByPrimaryKeySelective(User record)throws Exception;

	int updateByPrimaryKey(User record)throws Exception;

	List<User> findByPage(Map<String, Object> map)throws Exception;

	int pageCount(Map<String, Object> map)throws Exception;

	boolean selectByName(String userid, String userName) throws Exception;
	
	User selectByName(String userName) throws Exception;

	void update_reloadpwdbyid(String userid);

	int validatepwd(Map<String, Object> map);

	void editpwd(Map<String, Object> map);
	

	
}

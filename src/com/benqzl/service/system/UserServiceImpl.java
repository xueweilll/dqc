package com.benqzl.service.system;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.system.UserMapper;
import com.benqzl.pojo.system.User;
import com.benqzl.unit.CommonUtil;
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper mapper;
	@Override
	public int deleteByPrimaryKey(String userid) {
		return mapper.deleteByPrimaryKey(userid);
	}

	@Override
	public int insert(User record) {
		return mapper.insert(record);
	}

	/* (非 Javadoc) 
	* <p>Title: insertSelective</p> 
	* <p>Description:保存用户信息 </p> 
	* @param record
	* @return 
	* @see com.benqzl.service.UserService#insertSelective(com.benqzl.pojo.User) 
	*/


	@Override
	public User selectByPrimaryKey(String userid) {
		return mapper.selectByPrimaryKey(userid);
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		record.setEdittime(new Date());
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(User record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<User> findByPage(Map<String, Object> map) {
		return mapper.findByPage(map);
	}

	@Override
	public int pageCount(Map<String, Object> map) {
		return mapper.pageCount(map);
	}

	@Override
	public User selectByName(String userName) throws Exception {
		return mapper.selectUserByName(userName);
	}

	@Override
	public int insertSelective(User record) throws Exception {
		return mapper.insert(record);
	}

	@Override
	public boolean selectByName(String userid, String userName)
			throws Exception {
		User user=mapper.selectUserByName(userName);
		if(user==null){
			return true;
		}else{
			return false;
		}		
	}

	@Override
	public void update_reloadpwdbyid(String userid) {		
	try {
		Map<String, Object> map =  new HashMap<>();
		map.put("userid", userid);
		map.put("userpwd", CommonUtil.GetMD5Code("qdapp"));
		mapper.update_reloadpwdbyid(map);		
	} catch (Exception e) {
		e.printStackTrace();
	}
		
	}

	@Override
	public int validatepwd(Map<String, Object> map) {		
		return mapper.validatepwd(map);
	}

	@Override
	public void editpwd(Map<String, Object> map) {
		mapper.editpwd(map);
	}

}

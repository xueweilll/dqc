package com.benqzl.service.system;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.core.LeeTree;
import com.benqzl.dao.system.DepartmentMapper;
import com.benqzl.pojo.system.Department;
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentMapper mapper;
	
	
	@Override
	public int insert(Department record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(Department record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public Department selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Department record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Department record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}
	
	/* (非 Javadoc) 
	* <p>Title: selectAll</p> 
	* <p>Description:获取全部的部门Tree信息</p> 
	* @return 
	* @see com.benqzl.service.DepartmentService#selectAll() 
	*/
	@Override
	public LeeTree<Department> selectAll() {
		Department department1 = new Department();
		department1.setId("0");
		department1.setName("江苏华电戚墅堰发电有限公司");
		LeeTree<Department> leeTree =  new LeeTree<>("0","江苏华电戚墅堰发电有限公司","",department1,"open");
		List<Department> list = mapper.selectAll();
		LeeTree<Department> node;
		for(Department department : list){
			node = new LeeTree<>(department.getId(), department.getName(), department.getParent(), department, "closed");
			if(node.getPareId().equals(leeTree.getId())){
				leeTree.addNode(recursionLeeTree(node,list));
			}
		}
		return leeTree;
	}
	
	private LeeTree<Department> recursionLeeTree(LeeTree<Department> parent,List<Department> list){
		LeeTree<Department> node = null;
		for(Department department : list){
			if(department.getParent().equals(parent.getId())){
				node = new LeeTree<>(department.getId(), department.getName(), department.getParent(), department, "open");
				parent.addNode(recursionLeeTree(node,list));
			}
		}
		return parent;
	}

	@Override
	public int updateDepartment(String id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public boolean selectByName(String id, String name) throws Exception {
		Department department = mapper.selectByName(name);
		if (department != null ) {
			if(department.getId().equals(id)&&department.getName().equals(name)){
				return true;
			}else{
				return false;
			}
		} else {
			return true;
		}
	}

	@Override
	public List<Map<String, Object>> selectDepartments() throws Exception {
		List<Department> list = mapper.selectAll();
		List<Map<String, Object>> maps = new ArrayList<>();
		for (Department department : list) {
			if(department.getParent().equals("0")){
				Map<String, Object> map = new HashMap<String, Object>();
				List<Map<String, Object>> listMaps = new ArrayList<>();
				map.put("name", department.getName());
				if(department.getCornet()!=null&&!department.getCornet().equals("")){
					map.put("phone", department.getCornet());
				}else{
					map.put("phone", department.getPhone());
				}
				listMaps.addAll(recursionList(list,department.getId()));
				map.put("list", listMaps);
				maps.add(map);
			}
		}
		return maps;
	}
	
	private List<Map<String, Object>>  recursionList(List<Department> list,String id){
		List<Map<String, Object>> maps = new ArrayList<>();
		for(Department department : list){
			if(department.getParent().equals(id)){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", department.getName());
				if(department.getCornet()!=null&&!department.getCornet().equals("")){
					map.put("phone", department.getCornet());
				}else{
					map.put("phone", department.getPhone());
				}
				maps.add(map);
				maps.addAll(recursionList(list,department.getId()));
			}
		}
		return maps;
	}
}

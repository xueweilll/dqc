package com.benqzl.socket;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


@Service("messageQueue")
@Scope("singleton")
public class MessageQueue {
	/**
	 * 存放参数
	 * 3个参数
	 * guid 格式uuid 不清理 在同步的时候更新值
	 * ids 以逗号隔开的格式uuid 发完清理
	 * keys 以逗号隔开的String 发完清理
	 */
	private ConcurrentHashMap<String, String> parameterMap = new ConcurrentHashMap<>();
	
	//public ConcurrentHashMap<String, String> pks = new ConcurrentHashMap<>();
	
	private void writeParameterMap(String key,String value){
		parameterMap.put(key, value);
	}

	public void writeParameter(String ids,String keys){
		//String guid = readParameterMap("guid");
		//writeParameterMap("guid", guid);
		//pks.put(ids, keys);
		writeParameterMap("ids",ids);
		writeParameterMap("keys", keys);
		writeParameterMap("ischanged", "yes");
	}
	
	private String readParameterMap(String key){
		return parameterMap.get(key);
	}
	
	public String readParameterIds(){
		return readParameterMap("ids");
	}
	
	public String readParameterKeys(){
		return readParameterMap("keys");
	}
	
	public String readParameterGuid(){
		return readParameterMap("guid");
	}
	
	public boolean ischange(){
		String flag = readParameterMap("ischanged");
		if(flag==null){
			return false;
		}
		if(flag.equals("yes")){
			return true;
		}
		return false;
	}
	
	/*public String readParameterKeysByGuid(){
		writeParameterMap("ischanged", "no");
		return readParameterMap("guid")+"|"+readParameterMap("keys");
	}*/
	
	public void clearParameter(){
		//pks.clear();
		parameterMap.remove("keys");
		parameterMap.remove("ids");
		writeParameterMap("ischanged", "no");
	}
	
	/**
	 * 存放实时数据
	 * 数据格式
	 * id 格式uuid
	 * value
	 */
	public ConcurrentHashMap<String, String> currentData = new ConcurrentHashMap<>();
	
	/**
	 * 存放历史数据
	 * 数据格式
	 * id 格式uuid
	 * json {'date':'2016-04-18:01:00:00','value':'xx'}
	 */
	public ConcurrentHashMap<String, String> historyData = new ConcurrentHashMap<>();
	
	/**
	 * 存放趋势数据
	 * 数据格式
	 * id 格式uuid
	 * json {'date':'2016-04-18:01:00:00','value':'xx'}
	 */
	public ConcurrentHashMap<String, String> trendData = new ConcurrentHashMap<>();
}

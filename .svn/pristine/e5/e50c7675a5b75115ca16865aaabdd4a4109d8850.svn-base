package com.benqzl.core;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.benqzl.pojo.production.UnitParameter;
import com.benqzl.pojo.system.SysParameter;
import com.benqzl.service.production.UnitHisTimeDateService;
import com.benqzl.service.production.UnitParameterService;
import com.benqzl.service.system.SysParameterService;
import com.benqzl.socket.MessageQueue;

public class InstantiationTracingBeanPostProcessor implements
		ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private UnitParameterService unitservice;
	@Autowired
	private SysParameterService service;
	@Autowired
	private MessageQueue messageQueue;
	@Autowired
	private UnitHisTimeDateService hisTimeDateService;
	//private static Random rd=new Random();
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		try {
			StringBuffer pkeybuffer = new StringBuffer();
			StringBuffer idbuffer = new StringBuffer();
			List<SysParameter> sysParameters = service.selectAll();
			for (SysParameter sysParameter : sysParameters) {
				pkeybuffer.append(sysParameter.getPkey());
				pkeybuffer.append(",");
				idbuffer.append(sysParameter.getId());
				idbuffer.append(",");
				messageQueue.currentData.put(sysParameter.getId(),0+"");
			}
			List<UnitParameter> unitParameters  =unitservice.selectAll();
			for (UnitParameter unitParameter : unitParameters) {
				pkeybuffer.append(unitParameter.getPkey());
				pkeybuffer.append(",");
				idbuffer.append(unitParameter.getId());
				idbuffer.append(",");
			}
			pkeybuffer.delete(pkeybuffer.length()-1, pkeybuffer.length());
			idbuffer.delete(idbuffer.length()-1, idbuffer.length());
			messageQueue.writeParameter(idbuffer.toString(),pkeybuffer.toString());
			hisTimeDateService.deleteByAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

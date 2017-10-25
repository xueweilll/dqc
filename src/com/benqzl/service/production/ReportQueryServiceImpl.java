package com.benqzl.service.production;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benqzl.dao.production.ReportMapper;
import com.benqzl.pojo.production.Report;
@Service("reportQueryService")
public class ReportQueryServiceImpl implements ReportQueryService {
	@Autowired
	private ReportMapper mapper;
	
	@Override
	public List<Report> selectResultByDay(Map<String, Object> map)
			throws Exception {
		return mapper.selectResultByDay(map);
	}

	@Override
	public List<Report> selectResultByWeek(Map<String, Object> map)
			throws Exception {
		return mapper.selectResultByWeek(map);
	}

	@Override
	public List<Report> selectResultByYear(Map<String, Object> map)
			throws Exception {
		return mapper.selectResultByYear(map);
	}
	static DecimalFormat df = new DecimalFormat("0.00");
	static DecimalFormat dff = new DecimalFormat("0.0000"); 
	@Override
	public Map<String,Object> selectTotalResult(String dateTime) throws Exception {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> map1 = new HashMap<>();
		Map<String, Object> map2 = new HashMap<>();
		String[] str=dateTime.split("-");
		map.put("riqi", dateTime);
		map.put("type", 0);
		map.put("riqis", dateTime);
		map1.put("riqi", str[0]+"-"+str[1]);
		map1.put("type", 1);
		map1.put("riqis", dateTime);
		map2.put("riqi", str[0]);
		map2.put("type", 2);
		map2.put("riqis", dateTime);
		Report day = new Report();
		Report month = new Report();
		Report year = new Report();
		day = mapper.selectTotalResult(map);
		month = mapper.selectTotalResult(map1);
		year = mapper.selectTotalResult(map2);
		Map<String, Object> resultMap = new HashMap<>();
		/*
		 * 日统计
		 */
		if(day!=null){
			resultMap.put("day", true);
			resultMap.put("fdlrj1", df.format(day.getFdlrj1()));
			resultMap.put("fdlrj2", df.format(day.getFdlrj2()));
			resultMap.put("fdlrj12", df.format(day.getFdlrj2()+day.getFdlrj1()));
			resultMap.put("fdlrj3", df.format(day.getFdlrj3()));
			resultMap.put("fdlrj4", df.format(day.getFdlrj4()));
			resultMap.put("fdlrj34", df.format(day.getFdlrj4()+day.getFdlrj3()));
			resultMap.put("fdlrj5", df.format(day.getFdlrj5()));
			resultMap.put("fdlrj6", df.format(day.getFdlrj6()));
			resultMap.put("fdlrj56", df.format(day.getFdlrj6()+day.getFdlrj5()));
			resultMap.put("fdlrjAll", df.format(day.getFdlrj6()+day.getFdlrj5()+day.getFdlrj1()+day.getFdlrj2()+day.getFdlrj3()+day.getFdlrj4()));
			resultMap.put("zhcydl1", df.format(day.getZhcydl1()));
			resultMap.put("zhcydl2", df.format(day.getZhcydl2()));
			resultMap.put("zhcydl3", df.format(day.getZhcydl3()));
			resultMap.put("fdqh1", dff.format(day.getFdqh1()));
			resultMap.put("fdqh2", dff.format(day.getFdqh2()));
			resultMap.put("fdqh3", dff.format(day.getFdqh3()));
			resultMap.put("grlrr", df.format(day.getGrlrr()));
			resultMap.put("grgs", df.format(day.getGrgs()));
		}else{
			resultMap.put("day", false);
		}
		/*
		 * 月统计
		 */
		if(month!=null){
			resultMap.put("month", true);
			resultMap.put("monthfdlrj1", df.format(month.getFdlrj1()));
			resultMap.put("monthfdlrj2", df.format(month.getFdlrj2()));
			resultMap.put("monthfdlrj12", df.format(month.getFdlrj2()+month.getFdlrj1()));
			resultMap.put("monthfdlrj3", df.format(month.getFdlrj3()));
			resultMap.put("monthfdlrj4", df.format(month.getFdlrj4()));
			resultMap.put("monthfdlrj34", df.format(month.getFdlrj4()+month.getFdlrj3()));
			resultMap.put("monthfdlrj5", df.format(month.getFdlrj5()));
			resultMap.put("monthfdlrj6", df.format(month.getFdlrj6()));
			resultMap.put("monthfdlrj56", df.format(month.getFdlrj6()+month.getFdlrj5()));
			resultMap.put("monthfdlrjAll", df.format(month.getFdlrj6()+month.getFdlrj5()+month.getFdlrj1()+month.getFdlrj2()+month.getFdlrj3()+month.getFdlrj4()));
			resultMap.put("monthzhcydl1", df.format(month.getZhcydl1()));
			resultMap.put("monthzhcydl2", df.format(month.getZhcydl2()));
			resultMap.put("monthzhcydl3", df.format(month.getZhcydl3()));
			resultMap.put("monthfdqh1", dff.format(month.getFdqh1()));
			resultMap.put("monthfdqh2", dff.format(month.getFdqh2()));
			resultMap.put("monthfdqh3", dff.format(month.getFdqh3()));
			resultMap.put("monthgrlrr", df.format(month.getGrlrr()));
			resultMap.put("monthgrgs", df.format(month.getGrgs()));
		}else{
			resultMap.put("month", false);
		}
		/*
		 * 年统计
		 */
		if(year!=null){
			resultMap.put("year", true);
			resultMap.put("yearfdlrj1", df.format(year.getFdlrj1()));
			resultMap.put("yearfdlrj2", df.format(year.getFdlrj2()));
			resultMap.put("yearfdlrj12", df.format(year.getFdlrj2()+year.getFdlrj1()));
			resultMap.put("yearfdlrj3", df.format(year.getFdlrj3()));
			resultMap.put("yearfdlrj4", df.format(year.getFdlrj4()));
			resultMap.put("yearfdlrj34", df.format(year.getFdlrj4()+year.getFdlrj3()));
			resultMap.put("yearfdlrj5", df.format(year.getFdlrj5()));
			resultMap.put("yearfdlrj6", df.format(year.getFdlrj6()));
			resultMap.put("yearfdlrj56", df.format(year.getFdlrj6()+year.getFdlrj5()));
			resultMap.put("yearfdlrjAll", df.format(year.getFdlrj6()+year.getFdlrj5()+year.getFdlrj1()+year.getFdlrj2()+year.getFdlrj3()+year.getFdlrj4()));
			resultMap.put("yearzhcydl1", df.format(year.getZhcydl1()));
			resultMap.put("yearzhcydl2", df.format(year.getZhcydl2()));
			resultMap.put("yearzhcydl3", df.format(year.getZhcydl3()));
			resultMap.put("yearfdqh1", dff.format(year.getFdqh1()));
			resultMap.put("yearfdqh2", dff.format(year.getFdqh2()));
			resultMap.put("yearfdqh3", dff.format(year.getFdqh3()));
			resultMap.put("yeargrlrr", df.format(year.getGrlrr()));
			resultMap.put("yeargrgs", df.format(year.getGrgs()));
		}else{
			resultMap.put("year", false);
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> selectResultByMonth(String dateTime,int type)
			throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM");
		Date date = new Date();
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> smap = new HashMap<>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sd.parse(dateTime));
		calendar.add(Calendar.YEAR,-1);
		if(sd.format(date).equals(dateTime)){
			Date date1 = calendar.getTime();
			String[] strs = dateFormat.format(date).split("-");
			String[] strs1 = dateFormat.format(date1).split("-");
			map.put("endTime", dateFormat.parseObject(dateFormat.format(date)));
			map.put("startTime", dateFormat.parseObject(strs[0]+"-"+strs[1]+"-"+"01"));
			smap.put("endTime", dateFormat.parseObject(sd.format(date1)+"-"+strs[2]));
			smap.put("startTime", dateFormat.parseObject(strs1[0]+"-"+strs1[1]+"-"+"01"));
			map.put("month",null);
			smap.put("month",null);
			map.put("type",type);
			smap.put("type",type);
		}else{
			map.put("type",type);
			smap.put("type",type);
			map.put("month", dateTime);
			smap.put("month", sd.format(calendar.getTime()));
		}
		Report report = new Report();
		report=mapper.selectResultByMonth(map);
		Report sreport = new Report();
		sreport=mapper.selectResultByMonth(smap);
		Map<String, Object> resultMap = new HashMap<>();
		String noResult="0.00";
		switch (type) {
		case 0:// 全厂电量
			if(sreport.getFdlrj1()==0){
				resultMap.put("fdlrj1", noResult);
			}else{
				resultMap.put("fdlrj1", df.format((1-(report.getFdlrj1()/sreport.getFdlrj1()))*100));
			}
			if(sreport.getFdlrj2()==0){
				resultMap.put("fdlrj2", noResult);
			}else{
				resultMap.put("fdlrj2", df.format((1-(report.getFdlrj2()/sreport.getFdlrj2()))*100));
			}
			if(sreport.getFdlrj3()==0){
				resultMap.put("fdlrj3", noResult);
			}else{
				resultMap.put("fdlrj3", df.format((1-(report.getFdlrj3()/sreport.getFdlrj3()))*100));
			}
			if(sreport.getFdlrj4()==0){
				resultMap.put("fdlrj4", noResult);
			}else{
				resultMap.put("fdlrj4", df.format((1-(report.getFdlrj4()/sreport.getFdlrj4()))*100));
			}
			if(sreport.getFdlrj5()==0){
				resultMap.put("fdlrj5", noResult);
			}else{
				resultMap.put("fdlrj5", df.format((1-(report.getFdlrj5()/sreport.getFdlrj5()))*100));
			}
			if(sreport.getFdlrj6()==0){
				resultMap.put("fdlrj6", noResult);
			}else{
				resultMap.put("fdlrj6", df.format((1-(report.getFdlrj6()/sreport.getFdlrj6()))*100));
			}
			/*double count = report.getFdlrj1()+report.getFdlrj2()+report.getFdlrj3()+report.getFdlrj4()+report.getFdlrj5()+report.getFdlrj6();
			double count1 = sreport.getFdlrj1()+sreport.getFdlrj2()+sreport.getFdlrj3()+sreport.getFdlrj4()+sreport.getFdlrj5()+sreport.getFdlrj6();
			resultMap.put("fdlrjAll", df.format((count/(count1==0 ? 1:count1))));*/
			break;
		case 1:// 供热管损
			if(sreport.getGrlrr()==0){
				resultMap.put("grlrr", noResult);
			}else{
				resultMap.put("grlrr", df.format((1-(report.getGrlrr()/sreport.getGrlrr()))*100));
			}
			if(sreport.getGrgs()==0){
				resultMap.put("grgs", noResult);
			}else{
				resultMap.put("grgs", df.format((1-(report.getGrgs()/sreport.getGrgs()))*100));
			}
			break;
		case 2:// #1/2
			if(sreport.getFdlrj1()==0){
				resultMap.put("fdlrj1", noResult);
			}else{
				resultMap.put("fdlrj1", df.format((1-(report.getFdlrj1()/sreport.getFdlrj1()))*100));
			}
			if(sreport.getFdlrj2()==0){
				resultMap.put("fdlrj2", noResult);
			}else{
				resultMap.put("fdlrj2", df.format((1-(report.getFdlrj2()/sreport.getFdlrj2()))*100));
			}
			if(sreport.getZhcydl1()==0){
				resultMap.put("zhcydl1", noResult);
			}else{
				resultMap.put("zhcydl1", df.format((1-(report.getZhcydl1()/sreport.getZhcydl1()))*100));
			}
			if(sreport.getFdqh1()==0){
				resultMap.put("fdqh1", noResult);
			}else{
				resultMap.put("fdqh1", df.format((1-(report.getFdqh1()/sreport.getFdqh1()))*100));
			}
			break;
		case 3:// #3/4
			if(sreport.getFdlrj3()==0){
				resultMap.put("fdlrj3", noResult);
			}else{
				resultMap.put("fdlrj3", df.format((1-(report.getFdlrj3()/sreport.getFdlrj3()))*100));
			}
			if(sreport.getFdlrj4()==0){
				resultMap.put("fdlrj4", noResult);
			}else{
				resultMap.put("fdlrj4", df.format((1-(report.getFdlrj4()/sreport.getFdlrj4()))*100));
			}
			if(sreport.getZhcydl2()==0){
				resultMap.put("zhcydl2", noResult);
			}else{
				resultMap.put("zhcydl2", df.format((1-(report.getZhcydl2()/sreport.getZhcydl2()))*100));
			}
			if(sreport.getFdqh2()==0){
				resultMap.put("fdqh2", noResult);
			}else{
				resultMap.put("fdqh2", df.format((1-(report.getFdqh2()/sreport.getFdqh2()))*100));
			}
			break;
		case 4:// #56/78
			if(sreport.getFdlrj5()==0){
				resultMap.put("fdlrj5", noResult);
			}else{
				resultMap.put("fdlrj5", df.format((1-(report.getFdlrj5()/sreport.getFdlrj5()))*100));
			}
			if(sreport.getFdlrj6()==0){
				resultMap.put("fdlrj6", noResult);
			}else{
				resultMap.put("fdlrj6", df.format((1-(report.getFdlrj6()/sreport.getFdlrj6()))*100));
			}
			if(sreport.getZhcydl3()==0){
				resultMap.put("zhcydl3", noResult);
			}else{
				resultMap.put("zhcydl3", df.format((1-(report.getZhcydl3()/sreport.getZhcydl3()))*100));
			}
			if(sreport.getFdqh3()==0){
				resultMap.put("fdqh3", noResult);
			}else{
				resultMap.put("fdqh3", df.format((1-(report.getFdqh3()/sreport.getFdqh3()))*100));
			}
			break;
		default:
			break;
		}
		return resultMap;
	}

	@Override
	public int insert(Report report) {
		return mapper.insertSelective(report);
	}

}

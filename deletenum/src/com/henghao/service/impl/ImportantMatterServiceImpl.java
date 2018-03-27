package com.henghao.service.impl;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.henghao.dao.ImportantMatterDao;
import com.henghao.entity.Result;
import com.henghao.service.ImportantMatterService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("ImportantMatterService")
public class ImportantMatterServiceImpl implements ImportantMatterService {
	@Autowired
	private ImportantMatterDao importantMatterDao;
	/**
	 *项目安排月份情况
	 */
	@Override
	public Result importantMatterMonth() {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject(); 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        String dateNowStr = sdf.format(new Date()); 
        String year = dateNowStr.substring(0, 4);
        String date = "";
        for (int i = 1; i < 13; i++) {
			int  month = i ;
			if (month < 10) {
				String mon = "0"+i;
				 date = year + "-" + mon;
			}else {
				 date = year + "-" + month;
			}
			List<?> list = importantMatterDao.importantMatterMonth(date);
			jsonObject.put("month", month +"月");
			jsonObject.put("number", list.get(0));
			jsonArray.add(jsonObject);
			
		}
        String msg="查询成功";
        if (jsonArray == null || jsonArray.size()==0) {
        	msg = "查无数据";
		}
        result.setMsg(msg);
        result.setData(jsonArray);
		return result;
	}
	@Override
	public Result importantMatterYear() {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String year = sdf.format(new Date());
		int y= Integer.parseInt(year);
		for (int i = y; i <= y && i>=y-1; i--) {
			String date = Integer.toString(i);
			List<?> list = importantMatterDao.importantMatterYear(date);
			jsonObject.put("year", date);
			jsonObject.put("number", list.get(0));
			jsonArray.add(jsonObject);
			
		}
		String  msg = "查询成功";
		if (jsonArray == null || jsonArray.size() == 0) {
			msg = "查询无数据";
		}
		result.setMsg(msg);
		result.setData(jsonArray);
		return result;
	}
	/**
	 * 重大事项分布情况
	 */
	@Override
	public Result importantMatterDistribution() {
		Result result = new Result();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		List<?> lists = importantMatterDao.importantMatterNumber();
		for (int i = 0; i < lists.size(); i++) {
			String deptName = lists.get(i).toString();
		//String deptName = "安监二处";
			List<?> list= importantMatterDao.importantMatterDistribution(deptName);
			int num =Integer.parseInt(String.valueOf(list.get(0)));
			if (num != 0) {
				jsonObject.put("dept", deptName);
				jsonObject.put("num", list.get(0));
				jsonArray.add(jsonObject);
			}
			
		}
		
		String msg = "查询成功";
		if (jsonArray==null || jsonArray.size()==0) {
			msg ="查询无数据";
		}
		result.setMsg(msg);
		result.setData(jsonArray);
		return result;
	}
	

}

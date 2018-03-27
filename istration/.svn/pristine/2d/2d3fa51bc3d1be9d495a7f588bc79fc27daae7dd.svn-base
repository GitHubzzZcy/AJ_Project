package com.henghao.istration.dao;

import java.util.List;

import com.henghao.istration.entity.CarRealtime;
import com.henghao.istration.entity.Carapply;
import com.henghao.istration.entity.Exceed;
import com.henghao.news.dao.IBaseDao;

public interface ICarRealtimeDao extends IBaseDao<CarRealtime> {

	public List<CarRealtime> findByCarnum(String carNumber, String dates);

	public List<String> queryCarlist();

	public List<Exceed> exceed();

	public List<String> carlog();

	public Carapply carbook(String wrokid);

	public List<CarRealtime> lnglat(String substring, String carno);

	public List<CarRealtime> park();

	


}

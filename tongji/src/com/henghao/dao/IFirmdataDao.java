package com.henghao.dao;

import java.util.List;
import java.util.Map;

import com.henghao.entity.Enterprise;
import com.henghao.entity.Troop;
import com.henghao.entity.Troopemp;


public interface IFirmdataDao {

	public Map<String, Object> queryFirmdata(int page, int size);

	public String queryTerrCode(String district1, String string);

	public String queryIsplan(String enterpriseid);

	public Map<String, Object> queryFirmdataSeek(String firmname, int page,
			int size);

	public List<Troopemp> queryTroopemp(String id);

	public List<Troop> queryTroop();

	public Enterprise findFirmById(String enterpriseid);

}

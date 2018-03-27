package com.henghao.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.henghao.dao.IFirmdataDao;
import com.henghao.entity.Enterprise;
import com.henghao.entity.Troop;
import com.henghao.entity.Troopemp;
import com.henghao.service.IFirmdataService;
@Service("firmdataService")
public class FirmdataServiceImpl implements IFirmdataService {
	
	@Resource
	private IFirmdataDao firmdataDao;
	private Map<String, Object> map = new HashMap<String, Object>();

	private Map<String, Object> queryFirm(Map<String, Object> maps) {
		@SuppressWarnings("unchecked")
		List<Enterprise> list = (List<Enterprise>) maps.get("list");
		map.clear();
		map.put("data", this.querySys(list));
		map.put("total", maps.get("total"));
		return map;
	}
	private List<Enterprise> querySys(List<Enterprise> list) {
		for(Enterprise ent : list) {
			//查询数据字典表SYS_DICTTREEDATA
			ent.setDISTRICT1(firmdataDao.queryTerrCode(ent.getDISTRICT1(), "CityDistrict"));
			ent.setDISTRICT2(firmdataDao.queryTerrCode(ent.getDISTRICT2(), "CityDistrict"));
			ent.setDISTRICT3(firmdataDao.queryTerrCode(ent.getDISTRICT3(), "CityDistrict"));
			ent.setINDUSTRY1(firmdataDao.queryTerrCode(ent.getINDUSTRY1(), "EnterpriseIndustryType"));
			ent.setINDUSTRY2(firmdataDao.queryTerrCode(ent.getINDUSTRY2(), "EnterpriseIndustryType"));
			ent.setDEPARTMENT(firmdataDao.queryTerrCode(ent.getDEPARTMENT(), "Industrysector"));
			ent.setRELEVEL(firmdataDao.queryTerrCode(ent.getRELEVEL(), "EnterpriseDistrictLevel"));
			ent.setSCALE(firmdataDao.queryTerrCode(ent.getSCALE(), "EnterpriseScale"));
			//查询计划表有无计划
			ent.setISPLAN(firmdataDao.queryIsplan(ent.getENTERPRISEID()));
		}
		return list;
	}
	
	@Override
	public Map<String, Object> queryFirmdata(int page, int size) {
		Map<String, Object> maps  = firmdataDao.queryFirmdata(page, size);
		return this.queryFirm(maps);
	}
	
	@Override
	public Object queryFirmdataSeek(String firmname, int page, int size) {
		if("".equals(firmname)) {
			return this.queryFirmdata(page, size);
		}
		Map<String, Object> maps  = firmdataDao.queryFirmdataSeek(firmname,page, size);
		return this.queryFirm(maps);
	}

	/**
	 * 查询执法队伍人员
	 */
	@Override
	public Object queryTroopemp(String id) {
		List<Troopemp> list = firmdataDao.queryTroopemp(id);
		return list;
	}

	

	/**
	 * 查询执法队伍
	 */
	@Override
	public Object queryTroop() {
		List<Troop> list = firmdataDao.queryTroop();
		return list;
	}
	//根据企业id查询企业
	@Override
	public Enterprise findFirmById(String enterpriseid) {
		Enterprise enter = firmdataDao.findFirmById(enterpriseid);
		if(null == enter) {
			return null;
		}
		List<Enterprise> list = new ArrayList<Enterprise>();
		list.add(enter);
		List<Enterprise> sys = this.querySys(list);
		return sys.get(0);
	}

}

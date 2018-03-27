package com.henghao.istration.dao;

import java.util.List;

import com.henghao.istration.entity.Carapply;

public interface ICarapplyDao {


	public List<Carapply> findApply(String carNumber, String dates);


	public List<Object> findByCarnum(String carNumber, String dates);

	
}

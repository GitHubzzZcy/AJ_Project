package com.henghao.dao;

import com.henghao.entity.Note;

public interface INoteDao extends IBaseDao<Note> {


	public Note queryPlanId(String id);
	

}

package com.mybitop.gameversioncontrol.core.service;

import com.mybitop.gameversioncontrol.core.entity.Userinfo;

import java.util.List;

public interface IUserInfoService {
	
	int deleteByPrimaryKey(Long uid);

	int insert(Userinfo record);

	List<Userinfo> select();

	Userinfo selectByPrimaryKey(Long uid);

	/**通过username查找用户信息;*/
	Userinfo selectByUsername(String username);

	int update(Userinfo record);
	
}

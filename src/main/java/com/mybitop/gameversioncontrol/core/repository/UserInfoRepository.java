package com.mybitop.gameversioncontrol.core.repository;


import com.mybitop.gameversioncontrol.core.entity.Userinfo;
import org.springframework.data.repository.CrudRepository;

/**
 * UserInfo持久化类;
 * @author Angel(QQ:412887952)
 * @version v.0.1
 */
public interface UserInfoRepository extends CrudRepository<Userinfo,Long> {
	
	/**通过username查找用户信息;*/
	public Userinfo findByUsername(String username);
	
}

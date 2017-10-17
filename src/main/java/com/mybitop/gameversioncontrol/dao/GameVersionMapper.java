package com.mybitop.gameversioncontrol.dao;


import com.mybitop.gameversioncontrol.entity.VersionConfig;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 版本控制
 */
@Mapper
public interface GameVersionMapper {

    @Insert("insert into versioncontrol(appid, appname, channelid) values(#{name}, #{money})")
    int add(@Param("name") String name, @Param("money") double money);

    @Update("update versioncontrol set name = #{name}, money = #{money} where id = #{id}")
    int update(@Param("name") String name, @Param("money") double money, @Param("id") int id);

    @Delete("delete from versioncontrol where id = #{id}")
    int delete(int id);

    @Select("select id, name as name, money as money from versioncontrol where id = #{id}")
    VersionConfig findAccount(@Param("id") int id);

    @Select("select * from versioncontrol")
    List<VersionConfig> findAccountList();
}
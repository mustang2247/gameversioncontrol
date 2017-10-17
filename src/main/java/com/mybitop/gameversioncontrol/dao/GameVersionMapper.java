package com.mybitop.gameversioncontrol.dao;


import com.mybitop.gameversioncontrol.entity.VersionConfig;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * 版本控制
 */
@Mapper
public interface GameVersionMapper {

    @Insert("insert into versioncontrol (" +
            "appid, appname, channelid,channelname,appVersion,createtime,updatetime," +
            "serverIp,serverPort,hotfix,shields,define1,define2,params) " +
            "values(#{appid}, #{appname}, #{channelid}, #{channelname}, #{appVersion}, #{createtime}, #{updatetime}, " +
            "#{serverIp}, #{serverPort}, #{hotfix}, #{shields}, #{define1}, #{define2}, #{params})")
    int add(@Param("appid") String appid,
            @Param("appname") String appname,
            @Param("channelid") String channelid,
            @Param("channelname") String channelname,
            @Param("appVersion") String appVersion,
            @Param("createtime") Date createtime,
            @Param("updatetime") Date updatetime,

            @Param("serverIp") String serverIp,
            @Param("serverPort") String serverPort,
            @Param("hotfix") String hotfix,
            @Param("shields") String shields,
            @Param("define1") String define1,
            @Param("define2") String define2,
            @Param("params") String params
    );

    @Update("update versioncontrol set appid = #{appid}, appname = #{appname} , channelid = #{channelid} , " +
            "channelname = #{channelname} , appVersion = #{appVersion} , createtime = #{createtime} , " +
            "updatetime = #{updatetime} , serverIp = #{serverIp} , serverPort = #{serverPort} , " +
            "hotfix = #{hotfix} , shields = #{shields} , define1 = #{define1} , define2 = #{define2} , " +
            "params = #{params} where id = #{id}")
    int update(@Param("appid") String appid,
               @Param("appname") String appname,
               @Param("channelid") String channelid,
               @Param("channelname") String channelname,
               @Param("appVersion") String appVersion,
               @Param("createtime") Date createtime,
               @Param("updatetime") Date updatetime,

               @Param("serverIp") String serverIp,
               @Param("serverPort") String serverPort,
               @Param("hotfix") String hotfix,
               @Param("shields") String shields,
               @Param("define1") String define1,
               @Param("define2") String define2,
               @Param("params") String params,
               @Param("id") int id
    );

    @Delete("delete from versioncontrol where id = #{id}")
    int delete(int id);

    @Select("select * from versioncontrol where appid = #{appid} " +
            "and channelid = #{channelid} and appVersion = #{appVersion}")
    VersionConfig findAccount(@Param("appid") String appid,
                              @Param("channelid") String channelid,
                              @Param("appVersion") String appVersion);

    @Select("select * from versioncontrol")
    List<VersionConfig> findAccountList();
}
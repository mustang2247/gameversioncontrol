package com.mybitop.gameversioncontrol.mapper;

public interface IRedisMapper {

    void setKey(String key, String value);

    String getValue(String key);
}

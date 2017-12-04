package com.mybitop.gameversioncontrol.utils;

public class Utils {
    public static final String CACHE_NAME_CONF = "redis-cache-conf";
    public static final String CACHE_NAME_CHECK = "redis-cache-check";
    public static final String CACHE_NAME_CHECK_ONLINE = "redis-cache-check-online";


    //  1.非强制更新  2.强制更新 3.不更新
    public final static int TIP_UPDATE = 1;
    public final static int FORCE_UPDATE = 2;
    public final static int NOT_UPDATE = 3;
    public final static int CHOOSE_APP_STORE = 4;
}

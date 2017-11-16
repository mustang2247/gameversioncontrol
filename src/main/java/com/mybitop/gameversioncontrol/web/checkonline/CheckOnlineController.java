package com.mybitop.gameversioncontrol.web.checkonline;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mybitop.gameversioncontrol.entity.HotupdateCheckOnline;
import com.mybitop.gameversioncontrol.service.IHotupdateCheckOnline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 版本检查开放接口
 */
@Controller
@RequestMapping("/checkOnline/")
public class CheckOnlineController {
    private static final Logger logger = LoggerFactory.getLogger(CheckOnlineController.class);
    @Autowired
    private IHotupdateCheckOnline checkOnline;

    @RequestMapping(value = "getCheckOnlineInfo", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public HotupdateCheckOnline getCheckOnlineInfo(@RequestParam(value = "appid", required = true) String appid,
                                             @RequestParam(value = "channelid", required = true) String channelid,
                                             @RequestParam(value = "appVersion", required = true) String appVersion) {
        logger.info("appid:  " + appid + "  channelid: " + channelid + "  appversion: " + appVersion);
        return checkOnline.selectByConf(appid, channelid, appVersion);
    }

    /**
     * 通过json获取线上配置
     * @param data
     * @return
     */
    @RequestMapping(value = "getCheckInfoOnlineByJson", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public HotupdateCheckOnline getCheckInfoOnlineByJson(@RequestParam(value = "data", required = true) String data) {
        logger.info("data:  " + data);
        if (data == null || data.isEmpty()) return null;
        String appid;
        String channelid;
        String appVersion;
        try {
            JSONObject object = JSON.parseObject(data);
            appid = String.valueOf(object.getString("productId"));
            channelid = String.valueOf(object.getString("id"));
            appVersion = String.valueOf(object.getString("version"));

            return checkOnline.selectByConf(appid, channelid, appVersion);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "getCheckOnlineInfoById", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public HotupdateCheckOnline getCheckOnlineInfoById(@RequestParam(value = "id", required = true) int id) {
        logger.info("getConfigById: " + id);
        return checkOnline.selectByPrimaryKey(id);
    }


}

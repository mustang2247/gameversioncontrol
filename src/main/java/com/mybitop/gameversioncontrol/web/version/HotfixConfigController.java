package com.mybitop.gameversioncontrol.web.version;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mybitop.gameversioncontrol.entity.Versioncontrol;
import com.mybitop.gameversioncontrol.service.IVersioncontrol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 版本表格提交
 */
@Controller
@RequestMapping("/conf/")
public class HotfixConfigController {
    private static final Logger logger = LoggerFactory.getLogger(HotfixConfigController.class);

    @Autowired
    IVersioncontrol iVersioncontrol;

    @RequestMapping(value = "getConfigInfo", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Versioncontrol getConfigInfo(@RequestParam(value = "appid", required = true) String appid,
                                           @RequestParam(value = "channelid", required = true) String channelid,
                                           @RequestParam(value = "appVersion", required = true) String appVersion) {
        logger.info("appid:  " + appid + "  channelid: " + channelid + "  appversion: " + appVersion);
        return iVersioncontrol.findVersionInfo(appid, channelid, appVersion);
    }

    @RequestMapping(value = "getConfigInfoByJson", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Versioncontrol getConfigInfoByJson(@RequestParam(value = "data", required = true) String data) {
        logger.info("getConfigInfoByJson value:  " + data);
        if(data == null || data.isEmpty()) return null;

        String appid;
        String channelid;
        String appVersion;
        try {
            JSONObject object = JSON.parseObject(data);
            appid = String.valueOf(object.getString("productId"));
            channelid = String.valueOf(object.getString("id"));
            appVersion = String.valueOf(object.getString("version"));

            logger.info("getConfigInfoByJson appid:  " + appid);

            return iVersioncontrol.findVersionInfo(appid, channelid, appVersion);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("getConfigById")
    public String getConfigById(@RequestParam(value = "id", required = true) int id, Model model) {
        logger.info("getConfigById: " + id);
        model.addAttribute("versioning", iVersioncontrol.selectByPrimaryKey(id));
        return "form/version";
    }



}

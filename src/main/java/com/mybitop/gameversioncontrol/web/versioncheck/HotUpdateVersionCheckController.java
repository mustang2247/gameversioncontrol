package com.mybitop.gameversioncontrol.web.versioncheck;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mybitop.gameversioncontrol.entity.Hotupdatecheck;
import com.mybitop.gameversioncontrol.service.IHotupdatecheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/check/")
public class HotUpdateVersionCheckController {
    private static final Logger logger = LoggerFactory.getLogger(HotUpdateVersionCheckController.class);
    @Autowired
    private IHotupdatecheck hotupdatecheck;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<Hotupdatecheck> getAllConfigs() {
        return hotupdatecheck.select();
    }

    @RequestMapping(value = "getConfigByInfo", method = RequestMethod.GET)
    @ResponseBody
    public Hotupdatecheck getConfigByInfo(@RequestParam(value = "appid", required = true) String appid,
                                           @RequestParam(value = "channelid", required = true) String channelid,
                                           @RequestParam(value = "appVersion", required = true) String appVersion) {
        logger.info("appid:  " + appid + "  channelid: " + channelid + "  appversion: " + appVersion);
        return hotupdatecheck.selectByConf(appid, channelid, appVersion);
    }

    @RequestMapping(value = "getConfigByJson", method = RequestMethod.GET)
    @ResponseBody
    public Hotupdatecheck getConfigByJson(@RequestParam(value = "value", required = true) String value) {
        logger.info("value:  " + value);

        if(value == null || value.isEmpty()) return null;

        String appid;
        String channelid;
        String appVersion;
        try {
            JSONObject object = JSON.parseObject(value);
            appid = object.getString("productId");
            channelid = object.getString("id");
            appVersion = object.getString("version");
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return hotupdatecheck.selectByConf(appid, channelid, appVersion);
    }

    @GetMapping("getCheckInfoById")
    @ResponseBody
    public Hotupdatecheck getCheckInfoById(@RequestParam(value = "id", required = true) int id) {
        logger.info("getConfigById: " + id);
        return hotupdatecheck.selectByPrimaryKey(id);
    }

    @GetMapping("addcheckinfo")
    public String addCheckInfo(Model model) {
        model.addAttribute("checkinfo", new Hotupdatecheck());
        return "check/check";
    }

    @GetMapping("checkinfo")
    public String checkForm(@RequestParam(value = "id", required = true) int id, Model model) {
        Hotupdatecheck check = hotupdatecheck.selectByPrimaryKey(id);
        if(check != null){
            model.addAttribute("checkinfo", check);
        }else {
            model.addAttribute("checkinfo", new Hotupdatecheck());
        }

        return "check/check";
    }

    @PostMapping("checkinfo")
    public String checkSubmit(@ModelAttribute Hotupdatecheck versionConfig) {
        hotupdatecheck.insert(versionConfig);
        return "check/result";
    }

}

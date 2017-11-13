package com.mybitop.gameversioncontrol.web.version;

import com.mybitop.gameversioncontrol.entity.Versioncontrol;
import com.mybitop.gameversioncontrol.service.IVersioncontrol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 版本表格提交
 */
@Controller
@RequestMapping("/conf/")
public class GameVersionHotfixController {
    private static final Logger logger = LoggerFactory.getLogger(GameVersionHotfixController.class);

    @Autowired
    IVersioncontrol iVersioncontrol;

    @RequestMapping(value = "getConfig", method = RequestMethod.GET)
    @ResponseBody
    public Versioncontrol getConfigByAppID(@RequestParam(value = "appid", required = true) String appid,
                                           @RequestParam(value = "channelid", required = true) String channelid,
                                           @RequestParam(value = "appVersion", required = true) String appVersion) {
        logger.info("appid:  " + appid + "  channelid: " + channelid + "  appversion: " + appVersion);
        return iVersioncontrol.findVersionInfo(appid, channelid, appVersion);
    }

    @GetMapping("getConfigById")
    public String versionForm(@RequestParam(value = "id", required = true) int id, Model model) {
        logger.info("getConfigById: " + id);
        model.addAttribute("versioning", iVersioncontrol.selectByPrimaryKey(id));
        return "form/version";
    }



}

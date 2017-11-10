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
public class GameVersionFormCommitController {
    private static final Logger logger = LoggerFactory.getLogger(GameVersionFormCommitController.class);

    @Autowired
    IVersioncontrol iVersioncontrol;

    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<Versioncontrol> getConfigs() {
        return iVersioncontrol.select();
    }

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

    @GetMapping("form")
    public String versionForm(Model model) {
        model.addAttribute("versioning", iVersioncontrol.selectByPrimaryKey(1));
//        model.addAttribute("versioning", new VersionConfig());
        return "form/version";
    }

    @PostMapping("form")
    public String versionSubmit(@ModelAttribute Versioncontrol versionConfig) {
        iVersioncontrol.insert(versionConfig);
        return "form/result";
    }

}

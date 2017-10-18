package com.mybitop.gameversioncontrol.web.version;


import com.mybitop.gameversioncontrol.entity.VersionConfig;
import com.mybitop.gameversioncontrol.service.GameVersionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/versionSelect")
public class GameVersionController {

    private static final Logger logger = LoggerFactory.getLogger( GameVersionController.class );

    @Autowired
    GameVersionService accountService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<VersionConfig> getAccounts() {
        return accountService.findAccountList();
    }

    @RequestMapping(value = "/{appid}", method = RequestMethod.GET)
    @ResponseBody
    public VersionConfig getAccountById(@PathVariable("appid") String appid,
                                        @RequestParam(value = "channelid", required = true) String channelid,
                                        @RequestParam(value = "appVersion", required = true) String appVersion) {
        logger.info("appid:  " + appid + "  channelid: " + channelid + "  appversion: " + appVersion);
        return accountService.findVersionInfo(appid, channelid, appVersion);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updateAccount(@PathVariable("id") int id, @RequestParam(value = "name", required = true) String name,
                                @RequestParam(value = "money", required = true) double money) {
        int t = 0;//accountService.update(name, money, id);
        if (t == 1) {
            return "success";
        } else {
            return "fail";
        }

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "id") int id) {
        int t = accountService.delete(id);
        if (t == 1) {
            return "success";
        } else {
            return "fail";
        }

    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postAccount(@RequestParam(value = "name") String name,
                              @RequestParam(value = "money") double money) {

        int t = 1;//accountService.add(name, money);
        if (t == 1) {
            return "success";
        } else {
            return "fail";
        }

    }


}
package com.mybitop.gameversioncontrol.web.form;

import com.mybitop.gameversioncontrol.entity.Hotupdatecheck;
import com.mybitop.gameversioncontrol.entity.Versioncontrol;
import com.mybitop.gameversioncontrol.service.IHotupdatecheck;
import com.mybitop.gameversioncontrol.service.IVersioncontrol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/form/")
public class GameVersionFormCommitController {

    private static final Logger logger = LoggerFactory.getLogger(GameVersionFormCommitController.class);

    @Autowired
    IVersioncontrol iVersioncontrol;
    @Autowired
    private IHotupdatecheck hotupdatecheck;

    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<Versioncontrol> getConfigs() {
        return iVersioncontrol.select();
    }

    @GetMapping("hotfixForm")
    public String versionForm(Model model) {
        model.addAttribute("versioning", iVersioncontrol.selectByPrimaryKey(1));
//        model.addAttribute("versioning", new VersionConfig());
        return "form/version";
    }

    @PostMapping("hotfixForm")
    public String versionSubmit(@ModelAttribute Versioncontrol versionConfig) {
        iVersioncontrol.insert(versionConfig);
        return "form/result";
    }


    //==================================================================


    /**
     * 部署版本列表
     * @param model
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String getAllConfigs(Model model) {
        List<Hotupdatecheck> checkList = hotupdatecheck.select();
        model.addAttribute("funTitle", "部署版本");
        if(checkList != null){
            model.addAttribute("checkList", checkList);
        }else {
            model.addAttribute("checkList", null);
        }
        return "home";
    }

    @GetMapping("addcheckinfo")
    public String addCheckInfo(Model model) {
        model.addAttribute("checkinfo", new Hotupdatecheck());
        return "check/check";
    }

    @GetMapping("checkForm")
    public String checkForm(@RequestParam(value = "id", required = true) int id, Model model) {
        Hotupdatecheck check = hotupdatecheck.selectByPrimaryKey(id);
        if(check != null){
            model.addAttribute("checkinfo", check);
        }else {
            model.addAttribute("checkinfo", new Hotupdatecheck());
        }

        return "check/check";
    }

    @PostMapping("checkForm")
    public String checkSubmit(@ModelAttribute Hotupdatecheck versionConfig) {
        hotupdatecheck.insert(versionConfig);
        return "check/result";
    }

}

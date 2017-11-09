package com.mybitop.gameversioncontrol.web.versioncheck;

import com.mybitop.gameversioncontrol.entity.Hotupdatecheck;
import com.mybitop.gameversioncontrol.service.IHotupdatecheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/check/")
public class HotUpdateVersionCheckController {

    @Autowired
    private IHotupdatecheck hotupdatecheck;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<Hotupdatecheck> getAllConfigs() {
        return hotupdatecheck.select();
    }

    @GetMapping("checkinfo")
    public String checkForm(Model model) {
        model.addAttribute("versioning", hotupdatecheck.selectByPrimaryKey(1));
//        model.addAttribute("versioning", new VersionConfig());
        return "form/version";
    }

    @PostMapping("checkinfo")
    public String checkSubmit(@ModelAttribute Hotupdatecheck versionConfig) {
        hotupdatecheck.insert(versionConfig);
        return "form/result";
    }

}

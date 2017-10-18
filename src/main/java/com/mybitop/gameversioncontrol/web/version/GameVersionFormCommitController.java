package com.mybitop.gameversioncontrol.web.version;

import com.mybitop.gameversioncontrol.entity.VersionConfig;
import com.mybitop.gameversioncontrol.service.GameVersionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 版本表格提交
 */
@Controller
public class GameVersionFormCommitController {
    private static final Logger logger = LoggerFactory.getLogger( GameVersionController.class );

    @Autowired
    GameVersionService accountService;

    @GetMapping("/versioning")
    public String versionForm(Model model) {
        model.addAttribute("versioning", new VersionConfig());
        return "form/version";
    }

    @PostMapping("/versioning")
    public String versionSubmit(@ModelAttribute VersionConfig versionConfig) {
        accountService.add(versionConfig);
        return "form/result";
    }

}

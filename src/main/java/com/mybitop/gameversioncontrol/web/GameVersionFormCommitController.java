package com.mybitop.gameversioncontrol.web;

import com.mybitop.gameversioncontrol.entity.VersionConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 版本表格提交
 */
@Controller("/edit/")
public class GameVersionFormCommitController {

    @GetMapping("version")
    public String greetingForm(Model model) {
        model.addAttribute("version", new VersionConfig());
        return "version";
    }

    @PostMapping("submit")
    public String greetingSubmit(@ModelAttribute VersionConfig greeting) {
        return "result";
    }

}

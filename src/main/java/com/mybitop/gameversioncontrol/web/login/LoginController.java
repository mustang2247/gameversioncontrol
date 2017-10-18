package com.mybitop.gameversioncontrol.web.login;

import com.mybitop.gameversioncontrol.entity.Login;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 登陆
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("login", new Login());
        return "login/login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute Login login) {
        return "login/result";
    }

}

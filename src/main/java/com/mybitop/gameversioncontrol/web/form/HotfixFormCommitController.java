package com.mybitop.gameversioncontrol.web.form;

import com.alibaba.fastjson.JSON;
import com.mybitop.gameversioncontrol.entity.HotupdateCheckOnline;
import com.mybitop.gameversioncontrol.entity.Hotupdatecheck;
import com.mybitop.gameversioncontrol.entity.Versioncontrol;
import com.mybitop.gameversioncontrol.service.IHotupdateCheckOnline;
import com.mybitop.gameversioncontrol.service.IHotupdatecheck;
import com.mybitop.gameversioncontrol.service.IVersioncontrol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 表单相关操作
 */
@Controller
@RequestMapping("/form/")
public class HotfixFormCommitController {

    private static final Logger logger = LoggerFactory.getLogger(HotfixFormCommitController.class);

    /**
     * 配置文件
     */
    @Autowired
    private IVersioncontrol versionConfig;
    /**
     * 检查热更新
     */
    @Autowired
    private IHotupdatecheck hotupdatecheck;

    @Autowired
    private IHotupdateCheckOnline checkOnline;

    //==============================热更配置文件====================================

    @ResponseBody
    @RequestMapping(value = "getVersionConfigs", method = RequestMethod.GET)
    public List<Versioncontrol> getVersionConfigs() {
        return versionConfig.select();
    }

    /**
     * 新建配置文件
     * @param model
     * @return
     */
    @GetMapping("hotfixNewForm")
    public String hotfixNewForm(Model model) {
        model.addAttribute("versioning", new Versioncontrol());
        return "conf/configInfo";
    }

    /**
     * 修改配置文件
     * @param id
     * @param model
     * @return
     */
    @GetMapping("hotfixUpdateForm")
    public String hotfixUpdateForm(@RequestParam(value = "id", required = true) int id, Model model) {
        Versioncontrol config = versionConfig.selectByPrimaryKey(id);
        if (config != null){
            model.addAttribute("versioning", config);
        }else {
            model.addAttribute("versioning", new Versioncontrol());
        }
        return "conf/configUpdateInfo";
    }

    /**
     * 提交配置
     * @param versionConfig
     * @return
     */
    @PostMapping("hotfixForm")
    public void hotfixSubmit(@ModelAttribute Versioncontrol versionConfig, HttpServletResponse response) throws IOException {
        this.versionConfig.insert(versionConfig);
        response.sendRedirect("/");
//        return "conf/result";
    }

    @GetMapping("deleteConfigInfoItem")
    @ResponseBody
    public String deleteConfigInfoItem(@RequestParam(value = "id", required = true) int id, Model model) {
        return String.valueOf(versionConfig.deleteByPrimaryKey(id));
    }


    //==============================热更新检查====================================


    /**
     * 部署版本列表
     * @param model
     * @return
     */
    @RequestMapping(value = "getCheckConfigs", method = RequestMethod.GET)
    public void getCheckConfigs(Model model, HttpServletResponse response) throws IOException {
        List<Hotupdatecheck> checkList = hotupdatecheck.select();
        model.addAttribute("funTitle", "部署版本");
        if(checkList != null){
            model.addAttribute("checkList", checkList);
        }else {
            model.addAttribute("checkList", null);
        }
        response.sendRedirect("/");
    }

    /**
     * 新增应用版本信息
     * @param model
     * @return
     */
    @GetMapping("addCheckinfo")
    public String addCheckInfo(Model model) {
        model.addAttribute("checkinfo", new Hotupdatecheck());
        return "check/checkInfo";
    }

    /**
     * 修改应用版本信息
     * @param id
     * @param model
     * @return
     */
    @GetMapping("updateCheckForm")
    public String updateCheckForm(@RequestParam(value = "id", required = true) int id, Model model) {
        Hotupdatecheck check = hotupdatecheck.selectByPrimaryKey(id);
        if(check != null){
            model.addAttribute("checkinfo", check);
        }else {
            model.addAttribute("checkinfo", new Hotupdatecheck());
        }

        return "check/checkUpdateInfo";
    }

    /**
     * 提交应用版本信息
     * @param versionConfig
     * @return
     */
    @PostMapping("checkInfoSubmit")
    public void checkSubmit(@ModelAttribute Hotupdatecheck versionConfig, HttpServletResponse response) throws IOException {
        hotupdatecheck.insert(versionConfig);
        response.sendRedirect("/");
//        return "check/result";
    }

    @GetMapping("deleteCheckInfoItem")
    @ResponseBody
    public String deleteCheckInfoItem(@RequestParam(value = "id", required = true) int id, Model model) {
        return String.valueOf(hotupdatecheck.deleteByPrimaryKey(id));
    }

    //==============================热更新线上同步====================================

    /**
     * 将热更新配置同步到线上
     * @param id
     * @param model
     * @return
     */
    @GetMapping("checkFormOnline")
    public String checkFormOnline(@RequestParam(value = "id", required = true) int id, Model model) {
        Hotupdatecheck check = hotupdatecheck.selectByPrimaryKey(id);
        try {
            if(check != null){
                String json = JSON.toJSONString(check);
                HotupdateCheckOnline online = JSON.parseObject(json, HotupdateCheckOnline.class);

                checkOnline.insert(online);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return "/";
    }

    @GetMapping("viewCheckOnlineItem")
    public String viewCheckOnlineItem(@RequestParam(value = "id", required = true) int id, Model model) {
        HotupdateCheckOnline check = checkOnline.selectByPrimaryKey(id);
        if(check != null){
            model.addAttribute("checkInfoOnline", check);
        }else {
            model.addAttribute("checkInfoOnline", new HotupdateCheckOnline());
        }

        return "/checkonline/checkonline";
    }

}

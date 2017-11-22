package com.mybitop.gameversioncontrol.web.notice;

import com.mybitop.gameversioncontrol.entity.Hotupdatenotice;
import com.mybitop.gameversioncontrol.service.IHotUpdateNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(value = "/notice/")
public class HotUpdateNoticeController {

    @Autowired
    private IHotUpdateNotice hotUpdateNotice;


    /**
     * 新建通知
     * @param model
     * @return
     */
    @GetMapping("addNotice")
    public String addNotice(Model model) {
        model.addAttribute("notice", new Hotupdatenotice());
        return "notice/noticeInfo";
    }

    /**
     * 修改通知
     * @param id
     * @param model
     * @return
     */
    @GetMapping("updateNotice")
    public String updateNotice(@RequestParam(value = "id", required = true) int id, Model model) {
        Hotupdatenotice config = hotUpdateNotice.findById(id);
        if (config != null){
            model.addAttribute("notice", config);
        }else {
            model.addAttribute("notice", new Hotupdatenotice());
        }
        return "notice/noticeInfoUpdate";
    }

    /**
     * 提交通知
     * @param hotupdatenotice
     * @return
     */
    @PostMapping("noticeForm")
    public void hotfixSubmit(@ModelAttribute Hotupdatenotice hotupdatenotice, HttpServletResponse response) throws IOException {
        hotUpdateNotice.insert(hotupdatenotice);
        response.sendRedirect("/");
//        return "conf/result";
    }

    @GetMapping("deleteNoticeInfoItem")
    public void deleteConfigInfoItem(@RequestParam(value = "id", required = true) int id, HttpServletResponse response) throws IOException {
        hotUpdateNotice.deleteHotupdatenoticeById(id);
        response.sendRedirect("/");
    }


}

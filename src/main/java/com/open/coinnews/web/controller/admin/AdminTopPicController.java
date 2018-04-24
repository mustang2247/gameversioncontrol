package com.open.coinnews.web.controller.admin;

import com.open.coinnews.app.model.TopPic;
import com.open.coinnews.app.service.ITopPicService;
import com.open.coinnews.basic.auth.annotations.AdminAuth;
import com.open.coinnews.basic.auth.annotations.Token;
import com.open.coinnews.basic.auth.tools.TokenTools;
import com.open.coinnews.basic.tools.*;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping(value = "admin/topPic")
@AdminAuth(name="页头图片管理", orderNum=3, psn="网站管理", pentity=0, porderNum=1)
public class AdminTopPicController {

    @Autowired
    private ITopPicService topPicService;

    @Autowired
    private ConfigTools configTools;

    private static final String PATH_PRE = "topic/";

    /** 列表 */
    @AdminAuth(name = "页头图片列表", orderNum = 1, icon="icon-list")
    @RequestMapping(value="list", method= RequestMethod.GET)
    public String list(Model model, Integer page, HttpServletRequest request) {
        Page<TopPic> datas = topPicService.findAll(new ParamFilterTools<TopPic>().buildSpecification(model, request), PageableTools.basicPage(page, "asc", "orderNo"));
        model.addAttribute("datas", datas);
        return "admin/topPic/list";
    }

    @Token(flag=Token.READY)
    @AdminAuth(name = "添加页头图片", orderNum = 2, icon="icon-plus")
    @RequestMapping(value="add", method=RequestMethod.GET)
    public String add(Model model, HttpServletRequest request) {
        TopPic tp = new TopPic();
        tp.setStatus("1");
        tp.setUrlTarget("1");
        model.addAttribute("topPic", tp);
        return "admin/topPic/add";
    }

    /** 添加POST */
    @Token(flag=Token.CHECK)
    @RequestMapping(value="add", method=RequestMethod.POST)
    public String add(Model model, TopPic topPic, HttpServletRequest request, @RequestParam("file")MultipartFile[] files) {
        if(TokenTools.isNoRepeat(request)) {

            if(files!=null && files.length>=1) {
                BufferedOutputStream bw = null;
                try {
                    String fileName = files[0].getOriginalFilename();
                    if(fileName!=null && !"".equalsIgnoreCase(fileName.trim()) && NormalTools.isImageFile(fileName)) {
                        File outFile = new File(configTools.getUploadPath(PATH_PRE) + "/" + UUID.randomUUID().toString()+ NormalTools.getFileType(fileName));
                        topPic.setPicPath(configTools.getFileBaseUrl() + outFile.getAbsolutePath().replace(configTools.getUploadPath(), ""));
                        FileUtils.copyInputStreamToFile(files[0].getInputStream(), outFile);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if(bw!=null) {bw.close();}
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            topPicService.save(topPic);
        }
        return "redirect:/admin/topPic/list";
    }

    @Token(flag=Token.READY)
    @AdminAuth(name="修改页头图片", orderNum=3, type="2")
    @RequestMapping(value="update/{id}", method=RequestMethod.GET)
    public String update(Model model, @PathVariable Integer id, HttpServletRequest request) {
        TopPic tp = topPicService.findOne(id);

        model.addAttribute("topPic", tp);
        return "admin/topPic/update";
    }

    @Token(flag=Token.CHECK)
    @RequestMapping(value="update/{id}", method=RequestMethod.POST)
    public String update(Model model, @PathVariable Integer id, TopPic topPic, HttpServletRequest request, @RequestParam("file")MultipartFile[] files) {
        if(TokenTools.isNoRepeat(request)) {
            TopPic tp = topPicService.findOne(id);
            MyBeanUtils.copyProperties(topPic, tp, new String[]{"id"});

            if(files!=null && files.length>=1) {
                BufferedOutputStream bw = null;
                try {
                    String fileName = files[0].getOriginalFilename();
                    if(fileName!=null && !"".equalsIgnoreCase(fileName.trim()) && NormalTools.isImageFile(fileName)) {

                        File oldFile = new File(configTools.getUploadPath()+tp.getPicPath());
                        if(oldFile.exists()) {oldFile.delete();}

                        File outFile = new File(configTools.getUploadPath(PATH_PRE) + "/" + UUID.randomUUID().toString()+ NormalTools.getFileType(fileName));
                        tp.setPicPath(configTools.getFileBaseUrl() + outFile.getAbsolutePath().replace(configTools.getUploadPath(), ""));
                        FileUtils.copyInputStreamToFile(files[0].getInputStream(), outFile);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if(bw!=null) {bw.close();}
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            topPicService.save(tp);
        }
        return "redirect:/admin/topPic/list";
    }

    @AdminAuth(name="删除页头图片", orderNum=4, type="2")
    @RequestMapping(value="delete/{id}", method=RequestMethod.POST)
    public @ResponseBody
    String delete(@PathVariable Integer id) {
        try {
            topPicService.delete(id);
            return "1";
        } catch (Exception e) {
            return "0";
        }
    }
}

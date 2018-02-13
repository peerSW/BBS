package com.controller;

import com.model.BanKuai;
import com.service.BanKuaiService;
import com.service.HuiFuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by yedaran on 2018/2/8.
 */
@Controller
public class BanKuaiController {

    @Autowired
    BanKuaiService banKuaiService;

    @Autowired
    HuiFuService huiFuService;

    //显示所有板块
    @RequestMapping("/index")
    public String getBanKuais(HttpServletRequest request, Model model) throws IOException{
        String username = request.getSession().getAttribute("username").toString();
        List<BanKuai> BanKuaiList = banKuaiService.getBanKuais();
        model.addAttribute("BanKuaiList", BanKuaiList);
        if (username.equals("admin")) {
            return "admin-index";
        }
        return "index";
    }

    //管理员创建板块
    @RequestMapping("/admin-createBanKuai")
    public String createBanKuai(HttpServletRequest request, Model model) throws IOException{
        request.setCharacterEncoding("utf-8");
        String topic = request.getParameter("topic");
        String description = request.getParameter("description");
        if (banKuaiService.createBanKuai(topic, description)) {
            model.addAttribute("msg", "创建新板块成功");
        } else {
            model.addAttribute("msg", "创建新板块失败，请联系管理员");
        }
        return "forward:/index";
    }

    //管理员扩建回复表
    @RequestMapping("/expandHuiFuTable")
    public String expandHuiFu(HttpServletRequest request, Model model)throws IOException{
        if(huiFuService.expandTable())
        {
            model.addAttribute("msg","回复表扩建成功");
        }
        else {
            model.addAttribute("msg", "回复表扩建失败，请联系管理员");
        }
        return "forward:/index";
    }
}

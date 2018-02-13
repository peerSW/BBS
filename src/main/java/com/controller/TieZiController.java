package com.controller;

import com.model.BanKuai;
import com.service.TieZiService;
import com.model.TieZi;
import com.tool.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by yedaran on 2018/1/26.
 */

@Controller
public class TieZiController {

    @Autowired
    TieZiService tieZiService;

    //首页显示帖子
    @RequestMapping("/BanKuai")
    public String findTieZis(HttpServletRequest request, Model model) {
        //确认帖子总数
        String strPageNum = request.getParameter("pageNum");
        int BanKuaiID = Integer.parseInt(request.getParameter("BanKuaiID") );
        BanKuai banKuai=tieZiService.getBanKuai(BanKuaiID);
        int pageCount = 1 + banKuai.getCount()/ Integer.parseInt(new Settings().getSetting("TieZi", "count"));
        //确认当前页码
        int pageNum;
        if (strPageNum == null) {
            pageNum = 1;
        } else {
            pageNum = Integer.parseInt(strPageNum);
            if (pageNum < 1) {
                pageNum = 1;
            }
            if (pageNum > pageCount) {
                pageNum = pageCount;
            }
        }

        //获得当前页面的帖子list
        List<TieZi> TieZiList = tieZiService.getTieZis(BanKuaiID,pageNum);

        model.addAttribute("pageNum", pageNum);
        model.addAttribute("pageCount", pageCount);
        model.addAttribute("TieZiList", TieZiList);
        model.addAttribute("BanKuaiID",BanKuaiID);
        model.addAttribute("topic",banKuai.getTopic());

        return "BanKuai";
    }

    //发帖子
    @RequestMapping("/PostTieZi")
    public String postTieZi(HttpServletRequest request, Model model) throws IOException {
        request.setCharacterEncoding("utf-8");
        String userID = request.getSession().getAttribute("userID").toString();
        String username = request.getSession().getAttribute("username").toString();
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        int BanKuaiID = Integer.parseInt(request.getParameter("BanKuaiID"));
        if (title.isEmpty() || content.isEmpty()) {
            model.addAttribute("title", title);
            model.addAttribute("content", content);
            model.addAttribute("msg_post", "主题和内容不得为空");

            return "forward:BanKuai";
        } else {
            if (tieZiService.postTieZi(userID, username, title, content,BanKuaiID)) {
                model.addAttribute("msg_post","发帖成功");
            }
            return findTieZis(request,model);
        }
    }
}

package com.controller;

import com.model.HuiFu;
import com.model.TieZi;
import com.service.HuiFuService;
import com.service.TieZiService;
import com.tool.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by yedaran on 2018/1/31.
 */

@Controller
public class HuiFuController {

    @Autowired
    TieZiService tieZiService;

    @Autowired
    HuiFuService huiFuService;

    //显示帖子内容
    @RequestMapping("/TieZi")
    public String findHuiFus(HttpServletRequest request, Model model){

        String TieZiID=request.getParameter("TieZiID");
        String strPageNum=request.getParameter("pageNum");
        int BanKuaiID=Integer.parseInt(request.getParameter("BanKuaiID"));
        TieZi tieZi=tieZiService.getTieZi(BanKuaiID,TieZiID);
        int pageCount=1+Integer.parseInt(tieZi.getCount())/Integer.parseInt(new Settings().getSetting("HuiFu","count") );
        String title=tieZi.getTitle();
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
        //获取该页面的回复
        List<HuiFu>HuiFuList=huiFuService.getHuiFus(TieZiID,pageNum);

        model.addAttribute("pageNum",pageNum);
        model.addAttribute("pageCount",pageCount);
        model.addAttribute("HuiFuList",HuiFuList);
        model.addAttribute("title",title);
        model.addAttribute("TieZiID",TieZiID);
        model.addAttribute("BanKuaiID",BanKuaiID);
        return "TieZi";
    }

    //回复帖子
    @RequestMapping("/postHuiFu")
    public String postHuiFu(HttpServletRequest request, Model model)throws IOException{
        request.setCharacterEncoding("utf-8");
        String userID = request.getSession().getAttribute("userID").toString();
        String username = request.getSession().getAttribute("username").toString();
        String TieZiID = request.getParameter("TieZiID");
        String content = request.getParameter("content");
        int BanKuaiID = Integer.parseInt(request.getParameter("BanKuaiID"));
        if(content.isEmpty()){
            request.setAttribute("msg_post","内容不得为空");
            return "forward:/TieZi";
        }
        else{
            if(huiFuService.postHuiFu(userID,username,TieZiID,content,BanKuaiID)){
                request.setAttribute("msg_post","回复成功");
            }
            return findHuiFus(request,model);
        }
    }
}

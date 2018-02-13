package com.service;

import com.Dao.BanKuaiDao;
import com.Dao.TieZiDao;
import com.model.BanKuai;
import com.model.TieZi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * Created by yedaran on 2018/1/17.
 * 用于生产帖子列表
 */
@Service
public class TieZiService {

    @Autowired
    TieZiDao tieZiDao;

    @Autowired
    HuiFuService huiFuService;

    @Autowired
    BanKuaiDao banKuaiDao;


    public List<TieZi> getTieZis(int BanKuaiID,int page){
        return tieZiDao.getTieZis(BanKuaiID,page);
    }

    public TieZi getTieZi(int BanKuaiID,String TieZiID){
        return tieZiDao.getTieZi(BanKuaiID,TieZiID);
    }

    public BanKuai getBanKuai(int BanKuaiID){
        return banKuaiDao.getBanKuai(BanKuaiID);
    }

    public boolean postTieZi(String userID, String username, String title, String content,int BanKuaiID) {
        UUID TieZiID = UUID.randomUUID();
        String createTime = new Timestamp(System.currentTimeMillis()).toString();
        TieZi tiezi = new TieZi();
        tiezi.setTieZiID(TieZiID.toString());
        tiezi.setTitle(title);
        tiezi.setUserID(userID);
        tiezi.setUsername(username);
        tiezi.setCount("0");
        tiezi.setCreateTime(createTime);
        tiezi.setUpdateTime(createTime);
        tiezi.setBanKuaiID(BanKuaiID);
        if (tieZiDao.postTieZi(tiezi) > 0) {
            huiFuService.postHuiFu(userID, username, TieZiID.toString(), content, BanKuaiID);
            banKuaiDao.addRecord(BanKuaiID);
        }
        return false;
    }
}

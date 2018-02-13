package com.service;

import com.Dao.BanKuaiDao;
import com.Dao.TieZiDao;
import com.model.BanKuai;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yedaran on 2018/2/8.
 */
@Service
public class BanKuaiService {

    @Autowired
    BanKuaiDao banKuaiDao;

    @Autowired
    TieZiDao tieZiDao;

    //用于创建新板块
    public boolean createBanKuai(String topic,String description){
        int BanKuaiID=banKuaiDao.countBanKuai()+1;
        BanKuai banKuai=new BanKuai();
        banKuai.setBanKuaiID(BanKuaiID);
        banKuai.setCount(0);
        banKuai.setTopic(topic);
        banKuai.setDescription(description);
        if(banKuaiDao.addBanKuai(banKuai)>0){
            if(tieZiDao.createTable(BanKuaiID)>0)
            {return true;}
        }return false;
    }

    //用于获得所有板块
    public List<BanKuai> getBanKuais(){
        List<BanKuai> BanKuais=banKuaiDao.getBanKuais();
        return BanKuais;
    }
}

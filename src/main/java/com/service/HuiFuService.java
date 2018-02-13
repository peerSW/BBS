package com.service;

import com.Dao.HuiFuDao;
import com.Dao.TieZiDao;
import com.model.HuiFu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by yedaran on 2018/1/19.
 */
@Service
public class HuiFuService {
    @Autowired
    HuiFuDao huiFuDao;

    @Autowired
    TieZiDao tieZiDao;

    @Autowired
    SettingService settingService;

    //根据TieZiID查找指定页码的所有回复
    public List<HuiFu> getHuiFus(String TieZiID, int page) {
        int table=getHash(TieZiID);
        return huiFuDao.getHuiFus(TieZiID, page,table);
    }

    //用于回复主题帖
    public boolean postHuiFu(String userID, String username, String TieZiID, String content, int BanKuaiID) {
        String createTime = new Timestamp(System.currentTimeMillis()).toString();
        return postHuiFu(userID, username, TieZiID, content, createTime, BanKuaiID);
    }

    //传入创建时间发表回复，用于主题帖发布
    public boolean postHuiFu(String userID, String username, String TieZiID, String content, String createTime,int BanKuaiID) {
        UUID HuiFuID = UUID.randomUUID();
        HuiFu huiFu = new HuiFu();
        huiFu.setUserID(userID);
        huiFu.setTieZiID(TieZiID);
        huiFu.setUsername(username);
        huiFu.setContent(content);
        huiFu.setHuiFuID(HuiFuID.toString());
        huiFu.setCreateTime(createTime);
        int table= getHash(TieZiID);
        if (huiFuDao.postHuiFu(huiFu,table) > 0) {
            if(tieZiDao.addRecord(TieZiID, createTime, BanKuaiID )>0){
                return true;
            }
        }return false;
    }

    //根据TieZiID返回table的hash值
    private int getHash(String TieZiID){
        int hash=settingService.getHuiFuHash();
        return TieZiID.hashCode()&0x7FFFFFFF%hash;
    }
    private int getHash(String TieZiID, int hash){
        return TieZiID.hashCode()&0x7FFFFFFF%hash;
    }

    //扩建表
    public boolean expandTable(){
        List<HuiFu>reAlloc =new ArrayList<HuiFu>();
        //原来的分表数
        int hash=settingService.getHuiFuHash();
        //新的分表数
        int newhash=hash*2;
//        try {
            //创建新表
            for (int i = hash ; i < newhash; i++) {
                huiFuDao.createTable(i);
            }
            //把原来所有的回复找出，并将需要重新分配表的回复提取出来
            for (int j = 0; j < hash; j++) {
                List<HuiFu> huiFus = huiFuDao.allHuiFus(j);
                for (HuiFu huifu : huiFus) {
                    int table = getHash(huifu.getTieZiID(),hash);
                    int newtable = getHash(huifu.getTieZiID(), newhash);
                    if (table != newtable) {
                        reAlloc.add(huifu);
                    }
                }
            }
            //将提取出的回复插入新表
            for (HuiFu huifu : reAlloc) {
                int newtable = getHash(huifu.getTieZiID(), newhash);
                huiFuDao.postHuiFu(huifu, newtable);
            }
            //更改配置文件，让其他读写操作在新表进行
            settingService.setHuiFuHash(newhash);
            //删除旧表中废弃的回复数据
            for (HuiFu huiFu : reAlloc) {
                int table = getHash(huiFu.getTieZiID(), hash);
                huiFuDao.deleteHuiFu(huiFu.getHuiFuID(), table);
            }
        return true;
    }
}

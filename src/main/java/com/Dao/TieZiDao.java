package com.Dao;

import com.model.TieZi;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yedaran on 2018/2/5.
 */
@Repository
public class TieZiDao extends SqlSessionDaoSupport{

    //获得指定板块指定页面帖子列表
    public List<TieZi> getTieZis(int BanKuaiID,int page){
        Map<String,Object>params=new HashMap<String,Object>();
        params.put("BanKuaiID",BanKuaiID);
        params.put("start",(page-1)*30);
        params.put("limit",30);
        return this.getSqlSession().selectList("TieZi.getTieZis",params );
    }

    //获得指定板块指定帖子
    public TieZi getTieZi(int BanKuaiID, String TieZiID){
        Map<String,Object>params=new HashMap<String,Object>();
        params.put("BanKuaiID",BanKuaiID);
        params.put("TieZiID",TieZiID);
        return this.getSqlSession().selectOne("TieZi.getTieZi",params);
    }

    //在指定板块发帖
    public int postTieZi(TieZi tieZi){return this.getSqlSession().insert("TieZi.insert",tieZi);}

    //帖子被回复后增加回帖纪录
    public int addRecord(String TieZiID,String updateTime,int BanKuaiID){
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("TieZiID",TieZiID);
        params.put("updateTime",updateTime);
        params.put("BanKuaiID",BanKuaiID);
        return this.getSqlSession().update("TieZi.addRecord",params);
    }

    //为新板块创建新表存放帖子
    public int createTable(int BanKuaiID){
        return this.getSqlSession().update("TieZi.createTable",BanKuaiID);
    }

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        // TODO Auto-generated method stub
        super.setSqlSessionFactory(sqlSessionFactory);
    }
}

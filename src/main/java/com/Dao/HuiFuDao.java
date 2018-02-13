package com.Dao;

import com.model.HuiFu;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yedaran on 2018/2/7.
 */
@Repository
public class HuiFuDao extends SqlSessionDaoSupport{
    String namespace="HuiFu.";

    //获得指定帖子指定页面的回复
    public List<HuiFu>getHuiFus(String TieZiID,int page,int table){
        Map<String,Object>params=new HashMap<String,Object>();
        params.put("TieZiID",TieZiID);
        params.put("start",(page-1)*30);
        params.put("limit",30);
        params.put("table",table);
        return this.getSqlSession().selectList(namespace+"getHuiFus",params);
    }

    //在指定帖子中发表回复
    public int postHuiFu(HuiFu huiFu,int table){
        Map<String,Object>params=new HashMap<String,Object>();
        params.put("huifu",huiFu);
        params.put("table",table);
        return this.getSqlSession().insert(namespace+"postHuiFu",params);
    }

    //删除指定回复
    public int deleteHuiFu(String HuiFuID,int table){
        Map<String,Object>params=new HashMap<String,Object>();
        params.put("HuiFuID",HuiFuID);
        params.put("table",table);
        return this.getSqlSession().delete(namespace+"deleteHuiFu",params);
    }

    //获得某张表里所有回复
    public List<HuiFu>allHuiFus(int table){
     return this.getSqlSession().selectList(namespace+"allHuiFus",table);
    }

    //创建新表
    public int createTable(int table){
        return this.getSqlSession().update(namespace+"createTable",table);
    }
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        // TODO Auto-generated method stub
        super.setSqlSessionFactory(sqlSessionFactory);
    }
}

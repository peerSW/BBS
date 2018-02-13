package com.Dao;

import com.model.BanKuai;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yedaran on 2018/2/8.
 */
@Repository
public class BanKuaiDao extends SqlSessionDaoSupport{

    //发帖后板块帖子数+1
    public int addRecord(int BanKuaiID){
        return this.getSqlSession().update("BanKuai.addRecord",BanKuaiID);
    }

    //获得所有版块
    public List<BanKuai> getBanKuais(){
        return this.getSqlSession().selectList("BanKuai.getBanKuais");
    }

    //获得指定板块
    public BanKuai getBanKuai(int BanKuaiID){
        return this.getSqlSession().selectOne("BanKuai.getBanKuai",BanKuaiID);
    }

    //新增板块
    public int addBanKuai(BanKuai banKuai){
        return this.getSqlSession().insert("BanKuai.addBanKuai",banKuai);
    }

    //获得板块总数
    public int countBanKuai(){
        return this.getSqlSession().selectOne("BanKuai.countBanKuai");
    }

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        // TODO Auto-generated method stub
        super.setSqlSessionFactory(sqlSessionFactory);
    }
}

package com.Dao;

import com.model.Setting;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yedaran on 2018/2/12.
 */
@Repository
public class SettingDao extends SqlSessionDaoSupport{
    String namespace="Setting.";
    public Setting getSetting(String settingName){
        return this.getSqlSession().selectOne(namespace+"getSetting",settingName);
    }
    public int changeSetting(String settingName,String settingValue){
        Map<String,String>params=new HashMap<String, String>();
        params.put("settingName",settingName);
        params.put("settingValue",settingValue);
        return this.getSqlSession().update(namespace+"changeSetting",params);
    }

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        // TODO Auto-generated method stub
        super.setSqlSessionFactory(sqlSessionFactory);
    }
}

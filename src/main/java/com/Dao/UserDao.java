package com.Dao;

/**
 * Created by yedaran on 2018/2/2.
 */

import com.model.User;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends SqlSessionDaoSupport{
    private String namespace="user.";

    //通过username找user
    public User findByUsername(String username){return this.getSqlSession().selectOne(namespace+"findByUsername",username);}

    //增加新user
    public int addUser(User user){return  this.getSqlSession().insert(namespace+"addUser",user);}

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        // TODO Auto-generated method stub
        super.setSqlSessionFactory(sqlSessionFactory);
    }

}

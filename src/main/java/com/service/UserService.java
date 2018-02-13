package com.service;


import java.util.UUID;

import com.Dao.UserDao;
import com.model.User;
import com.tool.Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    //创建新用户,如果用户名已存在则返回false
    public boolean addUser(String username, String password) {
        if (userDao.findByUsername(username) != null) {    //确认没有同名用户,先判断缓存里的
            return false;
        } else {
            UUID uid = UUID.randomUUID();                 //生成唯一用户ID
            User user=new User();
            user.setUserID(uid.toString());
            user.setUsername(username);
            user.setPassword(Encrypt.MD5(password));            //对传入的密码进行加密
            if(userDao.addUser(user)>0){
                return true;
            }
            return false;
        }
    }

    //检查用户密码是否正确，如正确，返回ID
    public String checkPW(String username, String password) {
        User user=userDao.findByUsername(username);
        if (user!=null) {
            if (Encrypt.MD5(password).equals(user.getPassword() ) ) {
                return user.getUserID();
            }
        }
        return null;
    }
}
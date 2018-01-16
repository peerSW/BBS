/**
 * Created by yedaran on 2018/1/8.
 */
package com.Redis;

import redis.clients.jedis.Jedis;

public class RedisServer{
    public static String findPW(String username) {
        String password;
        Jedis jedis = getJedis();
        password=jedis.hget("user",username);
        return password;
    }

    public static  boolean addUser(String username,String password){
        boolean result=false;
        Jedis jedis=getJedis();
        if(jedis.hset("user",username,password)==1)
            result=true;
        return  result;
    }

    private static Jedis getJedis() {
        return new Jedis("localhost",6379);
    }
    public static void main(String[] args){
        //查看服务是否运行
/*        addUser("test1","test1");
        System.out.println("test1:"+findPW("test1"));*/
    }
}
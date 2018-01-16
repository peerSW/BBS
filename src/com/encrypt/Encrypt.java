package com.encrypt;

import java.security.MessageDigest;
/**
 * Created by yedaran on 2018/1/5.
 */
public class Encrypt {
    private static final String PUBLIC_SALT="pipixia";
    public static String MD5(String s,String salt) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            String dish=PUBLIC_SALT+s+salt;
            byte[] strTemp = dish.getBytes();
            //使用MD5创建MessageDigest对象
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte b = md[i];
                //System.out.println((int)b);
                //将没个数(int)b进行双字节加密
                str[k++] = hexDigits[b >> 4 & 0xf];
                str[k++] = hexDigits[b & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //测试
    public static void main(String[] args) {/*
        System.out.println("caidao的MD5加密后：/n"+Encrypt.MD5("caidao"));
        System.out.println("caidao的MD5加密后：/n"+Encrypt.MD5("caidao"));
        System.out.println("http://www.baidu.com/的MD5加密后：/n"+Encrypt.MD5("http://www.baidu.com/"));*/
    }
}

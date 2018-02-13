package com.model;

/**
 * Created by yedaran on 2018/1/26.
 */
public class TieZi {
    private String TieZiID;
    private String userID;
    private String createTime;
    private String title;
    private String updateTime;
    private String count;
    private String username;
    private int BanKuaiID;
    public String getTieZiID(){return TieZiID;}
    public void setTieZiID(String TieZiID){this.TieZiID=TieZiID;}
    public String getUserID(){return userID;}
    public void setUserID(String userID){this.userID=userID;}
    public String getCreateTime(){return createTime;}
    public void setCreateTime(String createTime){this.createTime=createTime;}
    public String getTitle(){return title;}
    public void setTitle(String title){this.title=title;}
    public String getUpdateTime(){return updateTime;}
    public void setUpdateTime(String updateTime){this.updateTime=updateTime;}
    public String getUsername(){return username;}
    public void setUsername(String username){this.username=username;}
    public String getCount(){return count;}
    public void setCount(String count){this.count=count;}
    public int getBanKuaiID(){return BanKuaiID;}
    public void setBanKuaiID(int BanKuaiID){this.BanKuaiID=BanKuaiID;}

}

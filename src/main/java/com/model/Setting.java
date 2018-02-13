package com.model;

/**
 * Created by yedaran on 2018/2/12.
 */
public class Setting {
    int settingID;
    String settingName;
    String settingValue;
    public int getsettingID(){return settingID;}
    public void setsettingID(int settingID){this.settingID=settingID;}
    public String getsettingName(){return settingName;}
    public void setsettingName(String settingName){this.settingName=settingName;}
    public String getsettingValue(){return settingValue;}
    public void setsettingValue(String settingValue){this.settingValue=settingValue;}
}

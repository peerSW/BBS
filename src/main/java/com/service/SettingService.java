package com.service;

import com.Dao.SettingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yedaran on 2018/2/12.
 */
@Service
public class SettingService {
    @Autowired
    SettingDao settingDao;
    public int getHuiFuHash(){
        String result=settingDao.getSetting("HuiFuHash").getsettingValue();
        return Integer.parseInt(result);
    }
    public int setHuiFuHash(int hash){
        return settingDao.changeSetting("HuiFuHash",String.valueOf(hash));
    }
}

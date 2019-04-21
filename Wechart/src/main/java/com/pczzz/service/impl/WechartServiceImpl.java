package com.pczzz.service.impl;

import com.pczzz.common.WeUtil;
import com.pczzz.dao.WechartDao;
import com.pczzz.domain.WXUser;
import com.pczzz.service.WechartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;


@Service
public class WechartServiceImpl implements WechartService {
    @Autowired
    private WechartDao wechartDao;
    //固定注册微信的信息
    private String appid = "?????";
    private String secret = "?????";
    private String accessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token";
    private String wxInfoUrl = "https://api.weixin.qq.com/sns/userinfo";

    @Override
    public WXUser wxLogin(String code) {
        WXUser user = null;
        //1.根据code获取access_token和openId
        String atUtl = accessTokenUrl + "?code="+code+"&appid="+appid+"&secret="+secret+"&grant_type=authorization_code";
        System.out.println(atUtl);
        Map<String, Object> map1 = null;
        try {
            map1 = WeUtil.sendGet(atUtl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Object access_token = map1.get("access_token");
        Object openid = map1.get("openid").toString();
        if(access_token == null && openid == null) {
            return user;
        }
        //2.根据openId判断用户是否存在
        user = wechartDao.findByOpenid(openid.toString());

        if(user != null) {
            //3.如果用户存在返回用户信息
            return user;
        }else{
            //4.如果用户不存在，根据access_token和openId获取微信用户信息
            String wxurl = wxInfoUrl + "?access_token=" + access_token +"&openid="+openid;
            Map<String, Object> map2 = null;
            try {
                map2 = WeUtil.sendGet(wxurl);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Object nickname = map2.get("nickname");
            Object headimgurl = map2.get("headimgurl");
            if(nickname == null || headimgurl == null) {
                return user;
            }
            //5.将微信用户信息保存到数据库，返回用户数据
            user = new WXUser();
            user.setOpenid(openid.toString());//用户openid
            user.setNickname(nickname.toString());//用户名
            user.setAvatar(headimgurl.toString());//用户头像
//            wechartDao.save(user);
        }
        return user;

    }
}

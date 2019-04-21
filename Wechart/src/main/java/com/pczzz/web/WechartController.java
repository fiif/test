package com.pczzz.web;
import com.pczzz.domain.WXUser;
import com.pczzz.service.WechartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WechartController {

    @Autowired
    private WechartService wechartService;

    @RequestMapping("/weixinlogin")
    public String weixinLogin(HttpServletRequest request, String code){
        String cd = request.getParameter("code");
        WXUser user = wechartService.wxLogin(code);

		//github提交信息
        //下面我就简单输出了，获取了user，可以根据需求自己进行相应操作了
        if (user == null){
            System.out.println("登录失败");
        }else {
            System.out.println("登录成功");
        }
        return "";
    }

}

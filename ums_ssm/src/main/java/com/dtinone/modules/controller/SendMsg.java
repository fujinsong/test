package com.dtinone.modules.controller;

import com.dtinone.utils.Result;
import com.dtinone.utils.SendMobileSms;
import com.dtinone.utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * ClassName: SendMsg
 * Description:
 * date: 2019/12/1 8:42
 * @author : 付 劲 松
 */
@Controller
@RequestMapping("send")
public class SendMsg {
    @RequestMapping("msg")
    @ResponseBody
    public Result send(String mobile, HttpSession session){
        if (!StringUtils.checkMobile(mobile)){
            return Result.setFailure("请输入正确的手机号");
        }
        // 产生六位的随机数
        int code = StringUtils.getRandomNumber(6);
        boolean flag = SendMobileSms.send(code + "" ,mobile);
        if (!flag){
            return Result.setFailure("发送失败");
        }
        System.out.println("手机验证码" + code);
        session.setAttribute("sessionVerification",code);
        return Result.setSuccess("发送成功");
    }
}

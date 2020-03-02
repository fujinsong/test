package com.dtinone.modules.interceptor;

import com.dtinone.modules.entity.User;
import com.dtinone.modules.service.inter.UserService;
import com.dtinone.utils.Constant;
import com.dtinone.utils.StringUtils;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * ClassName: LoginInterceptor
 * Description:
 * date: 2019/12/1 8:54
 *
 * @author : 付 劲 松
 */
@Setter
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 设置不拦截的路径：静态资源、登录、注册、忘记密码、发送验证码等等
     */
    @Autowired
    private UserService userService;
    private List<String> allowUrls;

    public void setAllowUrls(List<String> allowUrls) {
        this.allowUrls = allowUrls;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        System.out.println(uri);
        //放行：不需要拦截的
        if (!allowUrls.isEmpty()) {
            for (String allowUrl : allowUrls) {
                if (uri.contains(allowUrl)) {
                    //直接放行，不需要拦截
                    return true;
                }
            }
        }
        //登录拦截 ： 从session中取登录用户的id，判断用户状态；如果异常，返回登录页面；反之，放行
        HttpSession session = request.getSession();
        String loginUserId = (String) session.getAttribute("loginUserId");
        if (StringUtils.isBlank(loginUserId)) {
            //未登录，去登录页面
            response.sendRedirect("/login_page");
            return false;
        }
        User user = userService.get(loginUserId);
        if (user == null) {
            //账号有异常，去登录页面
            response.sendRedirect("/login_page");
            return false;
        }
        if (user.getStatus() == Constant.DATA_STATUS_FREEZE) {
            //账号冻结，去登录页面
            response.sendRedirect("/login_page");
            return false;
        }
//         执行到此，表示登录账号正常，放行
        return true;
    }
}

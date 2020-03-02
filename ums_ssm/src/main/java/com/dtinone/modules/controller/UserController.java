package com.dtinone.modules.controller;

import com.dtinone.modules.entity.Role;
import com.dtinone.modules.entity.User;
import com.dtinone.modules.service.inter.RoleService;
import com.dtinone.modules.service.inter.UserService;
import com.dtinone.utils.Constant;
import com.dtinone.utils.Page;
import com.dtinone.utils.Result;
import com.dtinone.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

/**
 * ClassName: UserController
 * Description:
 * date: 2019/12/1 8:43
 *
 * @author : 付 劲 松
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("login")
    @ResponseBody
    public Result login(String mobile, String verification, HttpSession session) {
        Integer sessionVerification = (Integer) session.getAttribute("sessionVerification");
        // 登录成功，信息存在session,失败给出提示
        Result result = userService.login(mobile, verification, sessionVerification + "");
        if (result.getStatus() == Constant.STATUS_SUCCESS) {
            // 将id 账号存到session中
            User user = (User) result.getData();
            Timestamp loginTime = new Timestamp(System.currentTimeMillis());
            user.setLoginTime(loginTime);
            boolean flag = userService.update(user);
            session.setAttribute("loginUserId", user.getId());
            session.setAttribute("loginUserName", user.getUserName());
            session.setAttribute("loginStatus", "yes");
        }
//        session.removeAttribute("sessionVerification");
        return result;
    }

    @RequestMapping("exit")
    public String exit(HttpSession session) {
        session.invalidate();
        return "redirect:/login_page";
    }

    /**
     * 用户列表
     *
     * @param model model
     * @param page  分页
     * @param user  用户
     * @return 用户列表页面
     */
    @RequestMapping({"list", ""})
    public String list(Model model, Page page, User user) {
        page = userService.find(user, page);
        List<Role> roleList = roleService.findAllValidate();
        model.addAttribute("page", page);
        model.addAttribute("user", user);
        model.addAttribute("roleList", roleList);
        return "user_list";
    }

    /**
     * 冻结用户或者解冻用户,值为yes为冻结操作，值为no为解冻操作
     *
     * @return
     */
    @RequestMapping("freeze")
    @ResponseBody
    public Result freeze(String id, String freezeFlag) {
        return userService.freeze(id, freezeFlag);
    }

    /**
     * 修改角色的信息
     * @param id     用户id
     * @param roleId 角色id
     * @return 修改结果
     */
    @RequestMapping("update_role")
    @ResponseBody
    public Result updateRole(String id, String roleId) {
        return userService.updateRole(id, roleId);
    }

    /**
     * 去表单页
     * @param user 存放用户的id，有id时是修改，没有是添加
     */
    @RequestMapping("form")
    public String form(User user, Model model) {
        if (StringUtils.isNotBlank(user.getId())) {
            user = userService.get(user.getId());
        }
        List<Role> roleList = roleService.findAllValidate();
        model.addAttribute("user", user);
        model.addAttribute("roleList", roleList);
        return "user_form";
    }

    /**
     * 保存数据：添加或者修改
     * @param user ：有id时是修改，没有id时是添加
     */
    @RequestMapping("save")
    @ResponseBody
    public Result save(User user, Model model) {
        return userService.save(user);
    }

    /**
     * 判断账号是否存在
     * @return 结果
     */
    @RequestMapping("checkUserName")
    @ResponseBody
    public Result checkUserName(String id, String userName) {
        return userService.checkUserName(id, userName);
    }

    /**
     * 判断手机号是否存在
     * @return 结果
     */
    @RequestMapping("checkMobile")
    @ResponseBody
    public Result checkMobile(String id, String mobile) {
        return userService.checkMobile(id, mobile);
    }

    /**
     * 删除数据
     * @param id id
     * @return 结果
     */
    @RequestMapping("delete")
    @ResponseBody
    public Result delete(String id) {
        return userService.delete(id);
    }
}

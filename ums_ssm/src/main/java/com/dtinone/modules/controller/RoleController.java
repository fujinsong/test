package com.dtinone.modules.controller;

import com.dtinone.modules.entity.Role;
import com.dtinone.modules.service.inter.RoleService;
import com.dtinone.utils.Page;
import com.dtinone.utils.Result;
import com.dtinone.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName: UserController
 * Description:
 * date: 2019/12/1 8:43
 * @author : 付 劲 松
 */
@Controller
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @RequestMapping({"list",""})
    public String list(Role role, Page page, Model model,String layerFlag,String userId){
        page = roleService.find(role,page);
        model.addAttribute("page",page);
        model.addAttribute("role",role);
        if("yes".equals(layerFlag)){
            model.addAttribute("userId",userId);
            return "user_role_list";
        }
        return "role_list";
    }
    /**
     * 去表单页
     * @param role 存放用户的id，有id时是修改，没有是添加
     */
    @RequestMapping("form")
    public String form(Role role,Model model){
        if (StringUtils.isNotBlank(role.getId())){
            role = roleService.get(role.getId());
        }
        model.addAttribute("role",role);
        return "role_form";
    }

    @RequestMapping("update_menu")
    @ResponseBody
    public Result updateMenu(String menuId,String roleId){
        return roleService.updateMenu(menuId,roleId);
    }
    /**
     * 保存数据：添加或者修改
     * @param role ：有id时是修改，没有id时是添加
     */
    @RequestMapping("save")
    @ResponseBody
    public Result save(Role role, Model model){
        return roleService.save(role);
    }

    /**
     * 判断账号是否存在
     * @return 结果
     */
    @RequestMapping("checkRoleName")
    @ResponseBody
    public Result checkRoleName(String id, String name){
        return roleService.checkRoleName(id,name);
    }

    /**
     * 删除数据
     * @param id 角色id
     * @return 删除结果
     */
    @RequestMapping("delete")
    @ResponseBody
    public Result delete(String id){
        return roleService.delete(id);
    }
}


package com.dtinone.modules.controller;

import com.dtinone.modules.entity.Menu;
import com.dtinone.modules.service.inter.MenuService;
import com.dtinone.utils.Page;
import com.dtinone.utils.Result;
import com.dtinone.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * ClassName: MenuController
 * Description:
 * date: 2019/12/1 8:43
 * @author : 付 劲 松
 */
@Controller
@RequestMapping("menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @RequestMapping({"list",""})
    public String list(Menu menu, Page page, Model model, String layerFlag, String roleId, HttpSession session){
        String userName = (String) session.getAttribute("loginUserName");
        page = menuService.find(menu,page);
//        List<Menu> list = menuService.findAllValidate(userName);
//        model.addAttribute("list",list);
        model.addAttribute("page",page);
        model.addAttribute("menu",menu);
        if("yes".equals(layerFlag)){
            model.addAttribute("roleId",roleId);
            return "role_menu_list";
        }
        return "menu_list";
    }
    /**
     * 去表单页
     * @param menu 存放用户的id，有id时是修改，没有是添加
     */
    @RequestMapping("form")
    public String form(Menu menu,Model model){
        if (StringUtils.isNotBlank(menu.getId())){
            menu = menuService.get(menu.getId());
        }
        model.addAttribute("menu",menu);
        return "menu_form";
    }

    /**
     * 保存数据：添加或者修改
     * @param menu ：有id时是修改，没有id时是添加
     */
    @RequestMapping("save")
    @ResponseBody
    public Result save(Menu menu, Model model,HttpSession session){
        String userName = (String) session.getAttribute("loginUserName");
        return menuService.save(menu,userName);
    }

    /**
     * 判断账号是否存在
     * @return 结果
     */
    @RequestMapping("checkMenuName")
    @ResponseBody
    public Result checkMenuName(String id, String name){
        return menuService.checkMenuName(id,name);
    }

    @RequestMapping("checkMenuUrl")
    @ResponseBody
    public Result checkMenuUrl(String id,String url){
        return menuService.checkMenuUrl(id,url);
    }
    /**
     * 删除数据
     * @param id 角色id
     * @return 删除结果
     */
    @RequestMapping("delete")
    @ResponseBody
    public Result delete(String id){
        return menuService.delete(id);
    }
}


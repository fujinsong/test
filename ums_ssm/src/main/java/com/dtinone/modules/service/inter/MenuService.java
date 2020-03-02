package com.dtinone.modules.service.inter;

import com.dtinone.modules.entity.Menu;
import com.dtinone.utils.Page;
import com.dtinone.utils.Result;

/**
 * ClassName: UserService
 * Description:角色业务接口
 * date: 2019/12/1 8:58
 * @author : 付 劲 松
 */
public interface MenuService {

    /**
     * 分页查找角色
     * @param menu 角色
     * @param page 页数
     * @return 结果
     */
    Page<Menu> find(Menu menu, Page<Menu> page);

    /**
     * 查找所有的未删除的角色
     * @param userName 用户名
     * @return list
     */
//    List<Menu> findAllValidate(String userName);

    /**
     * 根据id获取数据
     * @param id 角色id
     * @return 数据
     */
    Menu get(String id);

    /**
     * 保存权限
     * @param menu 权限
     * @param userName 用户
     * @return 结果
     */
    Result save(Menu menu,String userName);

    /**
     * 判断角色名是否存在
     * @param id 角色id
     * @param name 角色名
     * @return 结果
     */
    Result checkMenuName(String id, String name);

    /**
     * 删除角色
     * @param id 角色id
     * @return 删除结果
     */
    Result delete(String id);

    /**
     * 判断url是否存在
     * @param id 菜单id
     * @param url url
     * @return 返回结果
     */
    Result checkMenuUrl(String id, String url);
}

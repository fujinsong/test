package com.dtinone.modules.service.inter;

import com.dtinone.modules.entity.Role;
import com.dtinone.utils.Page;
import com.dtinone.utils.Result;

import java.util.List;

/**
 * ClassName: UserService
 * Description:角色业务接口
 * date: 2019/12/1 8:58
 * @author : 付 劲 松
 */
public interface RoleService {


    /**
     * 分页查找角色
     * @param role 角色
     * @param page 页数
     * @return 结果
     */
    Page<Role> find(Role role, Page<Role> page);

    /**
     * 查找所有的未删除的角色
     * @return list
     */
    List<Role> findAllValidate();

    /**
     * 根据id获取数据
     * @param id 角色id
     * @return 数据
     */
    Role get(String id);

    /**
     * 保存角色
     * @param role 角色
     * @return 结果
     */
    Result save(Role role);

    /**
     * 判断角色名是否存在
     * @param id 角色id
     * @param name 角色名
     * @return 结果
     */
    Result checkRoleName(String id, String name);

    /**
     * 删除角色
     * @param id 角色id
     * @return 删除结果
     */
    Result delete(String id);

    /**
     * 更新权限
     * @param menuId 权限id
     * @param roleId 角色id
     * @return 更新结果
     */
    Result updateMenu(String menuId, String roleId);
}

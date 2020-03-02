package com.dtinone.modules.dao;

import com.dtinone.modules.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ClassName: MenuDao
 * Description:菜单持久层接口
 * date: 2019/12/1 8:43
 * @author : 付 劲 松
 */
@Mapper
public interface MenuDao {
    /**
     * 带条件的分页查询
     * @param menu 条件查询的信息
     * @param startIndex 分页的起始位置
     * @param pageSize 分页的显示量
     * @return 当前页的数据
     */
    List<Menu> find(@Param("menu") Menu menu, @Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

    /**
     * 查找所有未删除的数据
     * @param userName 用户名
     * @return list
     */
//    List<Menu> findAllValidate(String userName);

    /**
     * 统计
     * @param menu 条件查询的信息
     * @return 数据结果
     */
    int count(@Param("menu") Menu menu);

    /**
     * 根据id获取数据
     * @param id id
     * @return 查询结果
     */
    Menu get(String id);

    /**
     * 通过菜单名查找数据
     * @param name 菜单名
     * @return 查找结果
     */
    List<Menu> findValidateByName(String name);

    /**
     * 删除菜单
     * @param id 菜单id
     * @return 删除结果
     */
    boolean delete(String id);

    /**
     * 添加菜单
     * @param menu 菜单信息
     * @return 返回结果
     */
    boolean add(Menu menu);

    /**
     * 修改菜单
     * @param menu 菜单
     * @return 修改结果
     */
    boolean updatePart(Menu menu);

    /**
     * 通过url查找数据
     * @param url url
     * @return 查找结果
     */
    List<Menu> findValidateByUrl(String url);

    /**
     * 添加权限后在第三张表role_menu添加数据
     * @param id id
     * @param menuId 菜单id
     * @param roleId 角色id
     * @return 添加结果
     */
    boolean insert(@Param("id") String id, @Param("menuId") String menuId, @Param("roleId") String roleId);
}

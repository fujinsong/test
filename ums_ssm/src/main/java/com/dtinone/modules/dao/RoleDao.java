package com.dtinone.modules.dao;

import com.dtinone.modules.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ClassName: RoleDao
 * Description:角色持久层接口
 * date: 2019/12/1 8:43
 * @author : 付 劲 松
 */
@Mapper
public interface RoleDao {
    /**
     * 带条件的分页查询
     * @param role 条件查询的信息
     * @param startIndex 分页的起始位置
     * @param pageSize 分页的显示量
     * @return 当前页的数据
     */
    List<Role> find(@Param("role") Role role, @Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

    /**
     * 查找所有未删除的数据
     * @return list
     */
    List<Role> findAllValidate();

    /**
     * 统计
     * @param role 条件查询的信息
     * @return 数据结果
     */
    int count(@Param("role") Role role);

    /**
     * 根据id获取数据
     * @param id id
     * @return 查询结果
     */
    Role get(String id);

    /**
     * 通过角色名查找数据
     * @param name 角色名
     * @return 查找结果
     */
    List<Role> findValidateByName(String name);

    /**
     * 删除角色
     * @param id 角色id
     * @return 删除结果
     */
    boolean delete(String id);

    /**
     * 添加角色
     * @param role 角色信息
     * @return 返回结果
     */
    boolean add(Role role);

    /**
     * 修改角色
     * @param role 角色
     * @return 修改结果
     */
    boolean updatePart(Role role);

    /**
     * 不为空的全部修改
     * @param role role
     * @return 修改结果
     */
    boolean update(Role role);
}

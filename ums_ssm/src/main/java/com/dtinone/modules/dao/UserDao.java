package com.dtinone.modules.dao;

import com.dtinone.modules.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ClassName: UserDao
 * Description:
 * date: 2019/12/1 8:43
 * @author : 付 劲 松
 */
@Mapper
public interface UserDao {
    /**
     * 根据手机号查询
     * @param  mobile 手机号
     * @return list
     */
    List<User> findByMobile(String mobile);

    /**
     * 根据id获取数据
     * @param id id
     * @return user
     */
    User get(String id);

    /**
     * 删除数据
     * @param id id
     * @return 结果
     */
    boolean delete(String id);

    /**
     * 查询符合条件的数据条数
     * @param user user
     * @return 条数
     */
    int count(@Param("user") User user);

    /**
     * 带条件的分页查询
     * @param user 条件查询的信息
     * @param startIndex 分页的起始位置
     * @param pageSize 分页的显示量
     * @return 当前页的数据
     */
    List<User> find(@Param("user") User user, @Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

//    /**
//     * 根据用户查找信息
//     * @param user 用户
//     * @return 查找结果
//     */
//    List<User> findByUser(@Param("user") User user);

    /**
     * 添加用户方法
     * @param user 用户
     * @return 添加结果
     */
    boolean add(User user);

    /**
     * 不为空的全部修改
     * @param user user
     * @return 修改结果
     */
    boolean update(User user);

    /**
     * 根据用户名查找数据
     * @param userName 用户名
     * @return 查找结果
     */
    List<User> findValidateByUserName(String userName);

    /**
     * 根据手机号查找数据
     * @param mobile 手机号
     * @return 查找结果
     */
    List<User> findValidateByMobile(String mobile);

    /**
     * 修改用户的部分信息
     * @param user 用户
     * @return 修改结果
     */
    boolean updatePart(User user);

    /**
     * 根据用户找角色id
     * @param userName 用户
     * @return
     */
    String findRoleIdByUser(String userName);
}

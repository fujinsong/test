package com.dtinone.modules.service.inter;

import com.dtinone.modules.entity.User;
import com.dtinone.utils.Page;
import com.dtinone.utils.Result;

/**
 * ClassName: UserService
 * Description:用户业务接口
 * date: 2019/12/1 8:58
 * @author : 付 劲 松
 */
public interface UserService {
    /**
     * 登录
     * @param mobile 手机号
     * @param verification 用户输入的验证码
     * @param sessionVerification 服务端验证码
     * @return 登录结果
     */
    Result login(String mobile, String verification, String sessionVerification);

    /**
     * 根据id删除数据
     * @param id id
     * @return 结果
     */
    Result delete(String id);

    /**
     * 根据id获取数据
     * @param id id
     * @return 返回id对应的用户
     */
    User get(String id);

    /**
     * 用户查找页
     * @param user 用户
     * @param page 页面
     * @return Page<User>
     */
    Page<User> find(User user, Page<User> page);

    /**
     * 用户冻结
     * @param id id
     * @return result
     */
    Result freeze(String id,String freezeFlag);

    /**
     * 修改用户角色
     * @param id 用户id
     * @param roleId 角色id
     * @return 修改结果
     */
    Result updateRole(String id, String roleId);

    /**
     * 修改、添加数据
     * @param user id为空时添加，id不为空是修改
     * @return 修改的结果
     */
    Result save(User user);

    /**
     * 判断用户名是否存在
     * @param id 用户id
     * @param userName 用户名
     * @return 结果
     */
    Result checkUserName(String id, String userName);

    /**
     * 判断手机号是否被使用
     * @param id 用户id
     * @param mobile 手机号
     * @return 结果
     */
    Result checkMobile(String id, String mobile);

    /**
     * 更新登录时间
     * @param user 用户
     * @return 更新结果
     */
    Boolean update(User user);
}

package com.dtinone.modules.service.impl;

import com.dtinone.modules.dao.RoleDao;
import com.dtinone.modules.dao.UserDao;
import com.dtinone.modules.entity.Role;
import com.dtinone.modules.entity.User;
import com.dtinone.modules.service.inter.UserService;
import com.dtinone.utils.Constant;
import com.dtinone.utils.Page;
import com.dtinone.utils.Result;
import com.dtinone.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * ClassName: UserServiceImpl
 * Description:用户业务实现
 * date: 2019/12/1 8:59
 *
 * @author : 付 劲 松
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    /**
     * 登录
     *
     * @param mobile              手机号
     * @param verification        用户输入的验证码
     * @param sessionVerification 服务端验证码
     * @return 登录结果
     */
    @Override
    public Result login(String mobile, String verification, String sessionVerification) {
        // 校验参数
        if (!StringUtils.checkMobile(mobile)) {
            return Result.setFailure("手机号的格式不正确");
        }
        if (StringUtils.isBlank(sessionVerification)) {
            return Result.setFailure("验证码失效，请重新发送");
        }
        if (!sessionVerification.equals(verification)) {
            return Result.setFailure("请输入正确的验证码");
        }
        // 判断手机号是否注册
        List<User> userList = userDao.findByMobile(mobile);
        // 处理错误数据
        if (userList == null || userList.isEmpty()) {
            // 账号不存在
            return Result.setFailure("账号不存在");
        }
        for (int i = 1; i < userList.size(); i++) {
            User user = userList.get(i);
            delete(user.getId());
        }
        User user = userList.get(0);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        user.setLoginTime(now);
        boolean flag = userDao.update(user);
        return Result.setSuccess(user);
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 删除结果
     */
    @Override
    public Result delete(String id) {
        if (StringUtils.isBlank(id)) {
            return Result.setFailure("请选择需要删除的数据");
        }
        User user = userDao.get(id);
        if (user == null) {
            return Result.setFailure("该用户不存在，请刷新页面再试");
        }
        boolean flag = userDao.delete(id);
        if (!flag) {
            return Result.setFailure("网络开小差了，请稍后再试");
        }
        return Result.setSuccess();
    }

    /**
     * 根据id获取用户
     *
     * @param id id
     * @return 相应id对应的用户
     */
    @Override
    public User get(String id) {
        if (StringUtils.isBlank((id))) {
            return null;
        }
        return userDao.get(id);
    }

    /**
     * 分页实现
     *
     * @param user 用户
     * @param page 页面
     * @return 分页
     */
    @Override
    public Page<User> find(User user, Page<User> page) {
        // 判断page为空
        if (page == null) {
            page = new Page<>();
        }
        int pageSize = page.getPageSize();
        int pageNo = page.getPageNo();
        // 查询总页数
        int count = userDao.count(user);
        // 计算总页数
        int totalPage = (int) Math.ceil((double) count / pageSize);
        // 设置总页数
        page.setTotalPage(totalPage);
        // 查询当前页的数据
        int startIndex = (pageNo - 1) * pageSize;
        List<User> userList = userDao.find(user, startIndex, pageSize);
        page.setList(userList);
        return page;
    }

    /**
     * 用户冻结
     *
     * @param id 用户id
     * @return 结果
     */
    @Override
    public Result freeze(String id, String freezeFlag) {
        try {
            // 校验参数
            if (StringUtils.isBlank(id)) {
                return Result.setFailure("请选择你想要冻结的用户");
            }
            if (StringUtils.isBlank(freezeFlag) || (!"yes".equals(freezeFlag) && !"no".equals(freezeFlag))) {
                return Result.setFailure("请选择你的操作类型:解冻或者冻结");
            }
            // 判断用户是否存在
            User user = userDao.get(id);
            if (user == null || user.getStatus() == Constant.DATA_STATUS_DELETE) {
                return Result.setFailure("该用户不存在，请刷新页面再试");
            }
            // 冻结或者解冻
            user.setStatus(Constant.DATA_STATUS_FREEZE);
            if ("no".equals(freezeFlag)) {
                user.setStatus(Constant.DATA_STATUS_NORMAL);
            }
            boolean flag = userDao.update(user);
            if (!flag) {
                return Result.setFailure("网络开小差了，请稍后再试");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.setSuccess("冻结成功");
    }

    /**
     * 修改用户角色
     *
     * @param id     用户id
     * @param roleId 角色id
     * @return 修改结果
     */
    @Override
    public Result updateRole(String id, String roleId) {
        if (StringUtils.isBlank(id)) {
            return Result.setFailure("请选择您想操作的用户");
        }
        if (StringUtils.isBlank(roleId)) {
            return Result.setFailure("请选择您想设置的角色");
        }
        //验证角色和用户的有效性
        User user = userDao.get(id);
        if (user == null || user.getStatus() != Constant.DATA_STATUS_NORMAL) {
            return Result.setFailure("当前用户已经失效");
        }
        Role role = roleDao.get(roleId);
        if (role == null || role.getStatus() != Constant.DATA_STATUS_NORMAL) {
            return Result.setFailure("当前角色已经失效");
        }
        //修改角色
        user.setRole(role);
        boolean flag = userDao.update(user);
        if (!flag) {
            return Result.setFailure("网络开小差了，请稍后再试");
        }
        return Result.setSuccess();
    }

    /**
     * 修改、添加数据
     *
     * @param user id为空时添加，id不为空是修改
     * @return 修改的结果
     */
    @Override
    public Result save(User user) {
        if (user == null) {
            return Result.setFailure("请输入用户信息");
        }
        if (StringUtils.isNotBlank(user.getId())) {
            User obj = userDao.get(user.getId());
            if (obj == null || obj.getStatus() == Constant.DATA_STATUS_DELETE) {
                return Result.setFailure("被修改用户失效");
            }
        }
        Result result = checkUserName(user.getId(), user.getUserName());
        if (StringUtils.isBlank(user.getUserName())) {
            return Result.setFailure("请输入账号信息");
        }
        if (result.getStatus() == Constant.STATUS_SUCCESS) {
            //账号已经存在
            return Result.setFailure("账号已经被使用");
        }
        //校验真实姓名
        String realName = user.getRealName();
        if (StringUtils.isBlank(realName) || realName.length() < 2 || realName.length() > 18) {
            return Result.setFailure("请输入正确格式的真实姓名");
        }
        if (StringUtils.isBlank(user.getMobile())) {
            return Result.setFailure("请输入手机号");
        }
        result = checkMobile(user.getId(), user.getMobile());
        if (result.getStatus() == Constant.STATUS_SUCCESS) {
            //账号已经存在
            return Result.setFailure("手机号已经被使用");
        }
        //初始化默认信息：性别，注册时间，状态

        Integer sex = user.getSex();
        if (sex == null) {
            sex = Constant.SEX_DEFAULT;
        }
        user.setSex(sex);
        if (user.getRole() == null) {
            //避免sql报空指针
            user.setRole(new Role());
        }
        if (StringUtils.isBlank(user.getId())) {
            user.setStatus(Constant.DATA_STATUS_NORMAL);
            //添加
            user.setId(StringUtils.getUuid());
            Timestamp now = new Timestamp(System.currentTimeMillis());
            user.setRegisterTime(now);
            boolean flag = userDao.add(user);
            if (flag) {
                return Result.setSuccess();
            }
            return Result.setFailure("网络信号不好，请重启路由器再试");
        }
        //修改
        boolean flag = userDao.updatePart(user);
        if (flag) {
            return Result.setSuccess();
        }
        return Result.setFailure("网络信号不好，请重启路由器再试");
    }


    /**
     * 判断用户名是否存在
     *
     * @param id       用户id
     * @param userName 用户名
     * @return 结果
     */
    @Override
    public Result checkUserName(String id, String userName) {
        if (StringUtils.isBlank(userName)) {
            return Result.setFailure("账号不能为空");
        }
        List<User> userList = userDao.findValidateByUserName(userName);
        if (userList == null || userList.isEmpty()) {
            return Result.setFailure("账号不存在");
        }
        //错误数据处理
        for (int i = 1; i < userList.size(); i++) {
            User obj = userList.get(i);
            delete(obj.getId());
        }
        User source = userList.get(0);
        if (StringUtils.isBlank(id)) {
            return Result.setSuccess("账号存在");
        }
        //执行到这，表示id不为空，那么执行的是修改
        if (id.equals(source.getId())) {
            return Result.setFailure("账号没有重复");
        }
        return Result.setSuccess("账号存在");
    }

    /**
     * 判断手机号是否被使用
     *
     * @param id     用户id
     * @param mobile 手机号
     * @return 结果
     */
    @Override
    public Result checkMobile(String id, String mobile) {
        if (StringUtils.isBlank(mobile)) {
            return Result.setFailure("手机号不能为空");
        }
        List<User> userList = userDao.findValidateByMobile(mobile);
        if (userList == null || userList.isEmpty()) {
            return Result.setFailure("账号不存在");
        }
        //错误数据处理
        for (int i = 1; i < userList.size(); i++) {
            User obj = userList.get(i);
            delete(obj.getId());
        }
        User source = userList.get(0);
        if (StringUtils.isBlank(id)) {
            return Result.setSuccess("账号存在");
        }
        //执行到这，表示id不为空，那么执行的是修改
        if (id.equals(source.getId())) {
            return Result.setFailure("账号没有重复");
        }
        return Result.setSuccess("账号存在");
    }

    /**
     * 更新登录时间
     * @param user 用户
     * @return 更新结果
     */
    @Override
    public Boolean update(User user) {
        if (user == null){
            return false;
        }
        boolean flag = userDao.update(user);
        return flag;
    }
}

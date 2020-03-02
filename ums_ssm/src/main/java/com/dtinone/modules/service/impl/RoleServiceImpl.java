package com.dtinone.modules.service.impl;

import com.dtinone.modules.dao.MenuDao;
import com.dtinone.modules.dao.RoleDao;
import com.dtinone.modules.entity.Menu;
import com.dtinone.modules.entity.Role;
import com.dtinone.modules.service.inter.RoleService;
import com.dtinone.utils.Constant;
import com.dtinone.utils.Page;
import com.dtinone.utils.Result;
import com.dtinone.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * ClassName: RoleServiceImpl
 * Description:角色业务实现
 * date: 2019/12/1 8:59
 * @author : 付 劲 松
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private MenuDao menuDao;

    /**
     * 分页查找角色
     * @param role 角色
     * @param page 页数
     * @return 结果
     */
    @Override
    public Page<Role> find(Role role, Page<Role> page) {
        // 判断page是否为空
        if (page == null) {
            page = new Page<>();
        }
        int pageSize = page.getPageSize();
        int pageNo = page.getPageNo();
        // 查询出当前页的数据，总页数
        int count = roleDao.count(role);
        int totalPage = (int) Math.ceil((double) count / pageSize);
        page.setTotalPage(totalPage);
        // 查询当前页的数据
        int startIndex = (pageNo - 1) * pageSize;
        List<Role> roleList = roleDao.find(role,startIndex,pageSize);
        page.setList(roleList);
        return page;
    }

    /**
     * 查找所有的未删除的角色
     * @return list
     */
    @Override
    public List<Role> findAllValidate() {
        return roleDao.findAllValidate();
    }

    /**
     * 根据id获取数据
     * @param id 角色id
     * @return 数据
     */
    @Override
    public Role get(String id) {
        if (StringUtils.isBlank(id)){
            return null;
        }
        return roleDao.get(id);
    }

    /**
     * 保存角色
     * @param role 角色
     * @return 结果
     */
    @Override
    public Result save(Role role) {
        if (role == null){
            return Result.setFailure("请输入角色信息");
        }
        if (StringUtils.isNotBlank(role.getId())){
            Role obj = roleDao.get(role.getId());
            if (obj==null || obj.getStatus() == Constant.DATA_STATUS_DELETE){
                return Result.setFailure("被修改角色失效");
            }
        }
        Result result = checkRoleName(role.getId(),role.getName());
        if (StringUtils.isBlank(role.getName())){
            return Result.setFailure("请输入角色信息");
        }
        if (result.getStatus() == Constant.STATUS_SUCCESS){
            //账号已经存在
            return Result.setFailure("账号已经被使用");
        }
        if (StringUtils.isBlank(role.getId())){
            role.setStatus(Constant.DATA_STATUS_NORMAL);
            //添加
            role.setId(StringUtils.getUuid());
            Timestamp now = new Timestamp(System.currentTimeMillis());
            role.setCreateTime(now);
//            role.setStatus(Constant.DATA_STATUS_NORMAL);
            boolean flag = roleDao.add(role);
            if (flag){
                return Result.setSuccess();
            }
            return Result.setFailure("网络开小差了，请稍后再试");
        }
        //修改
        Timestamp updateTime = new Timestamp(System.currentTimeMillis());
        role.setUpdateTime(updateTime);
        boolean flag = roleDao.updatePart(role);
        if (flag){
            return Result.setSuccess();
        }
        return Result.setFailure("网络信号不好，请重启路由器再试");
    }

    /**
     * 判断角色名是否存在
     * @param id   角色id
     * @param name 角色名
     * @return 结果
     */
    @Override
    public Result checkRoleName(String id, String name) {
        if(StringUtils.isBlank(name)){
            return Result.setFailure("角色名不能为空");
        }
        List<Role> roleList = roleDao.findValidateByName(name);
        if(roleList == null || roleList.isEmpty()){
            return Result.setFailure("角色不存在");
        }
        // 处理错误数据
        for (int i = 1; i < roleList.size(); i++) {
            Role obj = roleList.get(i);
            delete(obj.getId());
        }
        Role source = roleList.get(0);
        if(StringUtils.isBlank(id)){
            return Result.setSuccess("角色已存在");
        }
        // id 不为空 执行修改语句
        if (id.equals(source.getId())){
            return Result.setFailure("账号没有重复");
        }
        return Result.setSuccess("角色存在");

    }

    /**
     * 删除角色
     * @param id 角色id
     * @return 删除结果
     */
    @Override
    public Result delete(String id) {
        if (StringUtils.isBlank(id)) {
            return Result.setFailure("请选择需要删除的数据");
        }
        Role role = roleDao.get(id);
        if (role == null) {
            return Result.setFailure("该角色不存在，请刷新页面再试");
        }
        boolean flag = roleDao.delete(id);
        if (!flag) {
            return Result.setFailure("网络开小差了，请稍后再试");
        }
        return Result.setSuccess();
    }

    /**
     * 更新权限
     * @param menuId 权限id
     * @param roleId 角色id
     * @return 更新结果
     */
    @Override
    public Result updateMenu(String menuId, String roleId) {
        if (StringUtils.isBlank(roleId)) {
            return Result.setFailure("请选择您想操作的角色");
        }
        if (StringUtils.isBlank(menuId)) {
            return Result.setFailure("请选择您想设置的菜单");
        }
        //验证角色和用户的有效性
        Role role = roleDao.get(roleId);
        if (role == null || role.getStatus() != Constant.DATA_STATUS_NORMAL) {
            return Result.setFailure("当前角色已经失效");
        }
        Menu menu = menuDao.get(menuId);
        if (menu == null || menu.getStatus() != Constant.DATA_STATUS_NORMAL) {
            return Result.setFailure("当前菜单已经失效");
        }
        //修改角色
        role.setMenu(menu);
        boolean flag = roleDao.update(role);
        if (!flag) {
            return Result.setFailure("网络开小差了，请稍后再试");
        }
        return Result.setSuccess();
    }

}

package com.dtinone.modules.service.impl;

import com.dtinone.modules.dao.MenuDao;
import com.dtinone.modules.dao.UserDao;
import com.dtinone.modules.entity.Menu;
import com.dtinone.modules.service.inter.MenuService;
import com.dtinone.utils.Constant;
import com.dtinone.utils.Page;
import com.dtinone.utils.Result;
import com.dtinone.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * ClassName: MenuServiceImpl
 * Description:权限业务实现
 * date: 2019/12/1 8:59
 *
 * @author : 付 劲 松
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private UserDao userDao;

    /**
     * 分页查找权限
     *
     * @param menu 权限
     * @param page 页数
     * @return 结果
     */
    @Override
    public Page<Menu> find(Menu menu, Page<Menu> page) {
        // 判断page是否为空
        if (page == null) {
            page = new Page<>();
        }
        int pageSize = page.getPageSize();
        int pageNo = page.getPageNo();
        // 查询出当前页的数据，总页数
        int count = menuDao.count(menu);
        int totalPage = (int) Math.ceil((double) count / pageSize);
        page.setTotalPage(totalPage);
        // 查询当前页的数据
        int startIndex = (pageNo - 1) * pageSize;
        List<Menu> menuList = menuDao.find(menu, startIndex, pageSize);
        page.setList(menuList);
        return page;
    }

    /**
     * 查找所有的未删除的权限
     *
     * @return list
     */
//    @Override
//    public List<Menu> findAllValidate(String userName) {
//        return menuDao.findAllValidate(userName);
//    }

    /**
     * 根据id获取数据
     *
     * @param id 权限id
     * @return 数据
     */
    @Override
    public Menu get(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        return menuDao.get(id);
    }

    /**
     * 保存权限
     *
     * @param menu 权限
     * @return 结果
     */
    @Override
    public Result save(Menu menu ,String userName) {
        if (menu == null) {
            return Result.setFailure("请输入权限信息");
        }
        if (StringUtils.isNotBlank(menu.getId())) {
            Menu obj = menuDao.get(menu.getId());
            if (obj == null || obj.getStatus() == Constant.DATA_STATUS_DELETE) {
                return Result.setFailure("被修改权限失效");
            }
        }
        Result result = checkMenuName(menu.getId(), menu.getName());
        if (StringUtils.isBlank(menu.getName())) {
            return Result.setFailure("请输入权限信息");
        }
        if (result.getStatus() == Constant.STATUS_SUCCESS) {
            //账号已经存在
            return Result.setFailure("账号已经被使用");
        }
        if (StringUtils.isBlank(menu.getId())) {
            menu.setStatus(Constant.DATA_STATUS_NORMAL);
            //添加
            String menuId = StringUtils.getUuid();
            menu.setId(menuId);
            Timestamp now = new Timestamp(System.currentTimeMillis());
            menu.setCreateTime(now);
            boolean flag = menuDao.add(menu);
            String id = StringUtils.getUuid();
            String roleId = userDao.findRoleIdByUser(userName);
            menuDao.insert(id, menuId, roleId);
            if (flag) {
                return Result.setSuccess();
            }
            return Result.setFailure("网络开小差了，请稍后再试");
        }
        //修改
        Timestamp updateTime = new Timestamp(System.currentTimeMillis());
        menu.setUpdateTime(updateTime);
        boolean flag = menuDao.updatePart(menu);
        if (flag) {
            return Result.setSuccess();
        }
        return Result.setFailure("网络信号不好，请重启路由器再试");
    }

    /**
     * 判断菜单名是否存在
     *
     * @param id   菜单id
     * @param name 权限名
     * @return 结果
     */
    @Override
    public Result checkMenuName(String id, String name) {
        if (StringUtils.isBlank(name)) {
            return Result.setFailure("权限名不能为空");
        }
        List<Menu> menuList = menuDao.findValidateByName(name);
        if (menuList == null || menuList.isEmpty()) {
            return Result.setFailure("权限不存在");
        }
        // 处理错误数据
        for (int i = 1; i < menuList.size(); i++) {
            Menu obj = menuList.get(i);
            delete(obj.getId());
        }
        Menu source = menuList.get(0);
        if (StringUtils.isBlank(id)) {
            return Result.setSuccess("权限已存在");
        }
        // id 不为空 执行修改语句
        if (id.equals(source.getId())) {
            return Result.setFailure("账号没有重复");
        }
        return Result.setSuccess("权限已存在");

    }

    /**
     * 删除权限
     *
     * @param id 权限id
     * @return 删除结果
     */
    @Override
    public Result delete(String id) {
        if (StringUtils.isBlank(id)) {
            return Result.setFailure("请选择需要删除的数据");
        }
        Menu menu = menuDao.get(id);
        if (menu == null) {
            return Result.setFailure("该权限不存在，请刷新页面再试");
        }
        boolean flag = menuDao.delete(id);
        if (!flag) {
            return Result.setFailure("网络开小差了，请稍后再试");
        }
        return Result.setSuccess();
    }

    /**
     * 判断url是否存在
     *
     * @param id  菜单id
     * @param url url
     * @return 返回结果
     */
    @Override
    public Result checkMenuUrl(String id, String url) {
        if (StringUtils.isBlank(url)) {
            return Result.setFailure("url不能为空");
        }
        List<Menu> menuList = menuDao.findValidateByUrl(url);
        if (menuList == null || menuList.isEmpty()) {
            return Result.setFailure("url不存在");
        }
        // 处理错误数据
        for (int i = 1; i < menuList.size(); i++) {
            Menu obj = menuList.get(i);
            delete(obj.getId());
        }
        Menu source = menuList.get(0);
        if (StringUtils.isBlank(id)) {
            return Result.setSuccess("url已存在");
        }
        // id 不为空 执行修改语句
        if (id.equals(source.getId())) {
            return Result.setFailure("账号没有重复");
        }
        return Result.setSuccess("url存在");
    }
}

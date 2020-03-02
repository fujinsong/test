package com.dtinone.modules.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ClassName: User
 * Description:用户类
 * date: 2019/12/1 8:43
 * @author : 付 劲 松
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class User implements Serializable {
    /**
     * UUID+时间戳+8位随机数
     */
    private String id;
    /**
     * 真实姓名，不能为空长度大于2小于20
     */
    private String realName;
    /**
     * 登录名，不能为空长度大于2小于20不能重复，唯一
     */
    private String userName;
    /**
     * 密码，不能为空，需要MD5加密长度大于6小于18
     */
    private String password;
    /**
     * 性别，0表示男 1表示女默认值为0（男）不能为空
     */
    private Integer sex;
    /**
     * 最后登录时间，显示格式为:(yyyy-MM-dd hh:mm:ss)用于记录用户最有一次登录，时间为数据库当前时间或后台系统当前时间
     */
    private Timestamp loginTime;
    /**
     * 注册时间，显示格式为:(yyyy-MM-dd hh:mm:ss)用于记录用户注册时间，时间为数据库当前时间或后台系统当前时间
     */
    private Timestamp registerTime;
    /**
     * 用户状态 ,0表示正常 1表示删除 2表示停用（冻结），默认正常
     */
    private Integer status;
    /**
     * 用户状态 整型 0表示正常 1表示删除 2表示停用（冻结）
     */
    private Role role;
    /**
     * 手机号，不能为空
     */
    private String mobile;
    /**
     * 辅助属性：辅助控制层接收客户端（视图层）参数
     */
    private String confirmPwd;

    private String startLoginTime;
    private String endLoginTime;
    private String startRegisterTime;
    private String endRegisterTime;
}

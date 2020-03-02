package com.dtinone.utils;

/**
 * ClassName: Constant
 * Description:常量工具类，控制层、业务层、持久层不能出现成员变量
 * date: 2019/12/1 9:00
 * @author : 付 劲 松
 */
public class Constant {
    private Constant() {
    }

    /**
     * 0表示成功，1表示失败
     */
    public static final int STATUS_SUCCESS = 0;
    /**
     * 0表示成功，1表示失败
     */
    public static final int STATUS_FAILURE = 1;
    /**
     * 0表示男，1表示女
     */
    public static final int SEX_MALE = 0;
    /**
     * 0表示男，1表示女
     */
    public static final int SEX_FEMALE = 1;
    /**
     * 默认性别
     */
    public static final int SEX_DEFAULT = SEX_MALE;
    /**
     * 用户操作失败最高次数限制
     */
    public static final int FAILURE_MAX_NUMBER = 5;
    /**
     * 0表示正常 1表示删除 2表示停用（冻结）
     */
    public static final int DATA_STATUS_NORMAL = 0;
    /**
     * 0表示正常 1表示删除 2表示停用（冻结）
     */
    public static final int DATA_STATUS_DELETE = 1;
    /**
     * 0表示正常 1表示删除 2表示停用（冻结）
     */
    public static final int DATA_STATUS_FREEZE = 2;
    /**
     * 数据默认状态
     */
    public static final int DATA_STATUS_DEFAULT = DATA_STATUS_NORMAL;
    /**
     * 校验登录名的正则表达式
     */
    public static final String REGEX_USERNAME = "^[a-zA-Z][a-zA-Z0-9]{1,7}";
    /**
     * 校验密码的正则表达式
     */
    public static final String REGEX_PASSWORD = "[a-zA-Z0-9]{3,8}";
    /**
     * 校验手机号的正则表达式
     */
    public static final String REGEX_MOBILE = "^1[3|4|5|7|8][0-9]\\d{4,8}$";
    /**
     * 容器默认大小
     */
    public static final int CONTAINER_DEFAULT_SIZE = 10;

    /**
     * 每页默认显示数据量10条
     */
    public static final int DEFAULT_PAGE_SIZE = 10;

    /**
     * 手机号最小长度
     */
    public static final int MOBILE_MIN_SIZE = 11;

    /**
     * 手机号最大长度
     */
    public static final int MOBILE_MAX_SIZE = 13;

}


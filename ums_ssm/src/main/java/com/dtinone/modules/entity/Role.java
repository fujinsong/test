package com.dtinone.modules.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ClassName: Role
 * Description:
 * date: 2019/12/1 8:46
 * @author : 付 劲 松
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Role implements Serializable {
    /**
     * id,UUID+时间戳+8位随机数
     */
    private String id;
    /**
     * 角色名,不能为空长度大于2小于20
     */
    private String name;
    /**
     * 状态 , 0表示正常，1表示删除，默认0
     */
    private Integer status;
    /**
     * 创建时间,显示格式为:(yyyy-MM-dd hh:mm:ss)用于记录数据创建时间
     */
    private Timestamp createTime;
    /**
     * 修改时间,显示格式为:(yyyy-MM-dd hh:mm:ss)用于记录数据创建时间
     */
    private Timestamp updateTime;

    /**
     * 菜单属性
     */
    private Menu menu;

}

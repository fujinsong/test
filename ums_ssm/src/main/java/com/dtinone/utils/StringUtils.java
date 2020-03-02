package com.dtinone.utils;

import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * ClassName: StringUtils
 * Description:字符串工具类
 * date: 2019/12/1 9:11
 * @author : 付 劲 松
 */
public class StringUtils extends StringUtil {
    /**
     * 规范上：工具类需要显示声明私有构造器，避免创建不必要的对象
     */
    private StringUtils() {
    }

    /**
     * 获取一个随机的id，由UUID+时间戳+8位随机数构成
     */
    public static String getUuid() {
        // 获取UUID
        String uuid = UUID.randomUUID().toString();
        // 获取当前系统时间
        long now = System.currentTimeMillis();
        // 获取8位随机数
        String randomNumber = String.format("%8d", new Random().nextInt(99999999));
        // 组合构成id
        String id = uuid + randomNumber + now;
        return id;
    }

    /**
     * 判断手机号
     *
     * @param mobile 手机号
     * @return 结果
     */
    public static boolean checkMobile(String mobile) {
        String reg = "^[+][8][6][1][3|4|5|7|8][0-9]\\d{4,8}$||^[1][3|4|5|7|8][0-9]\\d{4,8}$";
        if (isBlank(mobile)) {
            return false;
        }
        return Pattern.matches(reg, mobile);
    }

    /**
     * 生成指定位数的随机数
     *
     * @param length 位数
     * @return 随机数
     */
    public static int getRandomNumber(int length) {
        String str = "";
        for (int i = 0; i < length; i++) {
            str = str + 9;
        }
        int value = Integer.parseInt(str);
        String randomNumber = String.format("%" + length + "d", new Random().nextInt(value));
        return Integer.parseInt(randomNumber);
    }
}

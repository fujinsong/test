package com.dtinone.utils;

/**
 * ClassName: Result
 * Description:封装业务结果的类
 * date: 2019/12/1 9:05
 * @author : 付 劲 松
 */
public class Result<T> {
    /**
     * 结果状态：0成功，1失败
     */
    private int status;
    /**
     * 提示信息
     */
    private String msg;

    private T data;

    public Result() {
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 封装失败的方法
     *
     * @param msg 提示信息
     * @return 封装结果
     */
    public static Result setFailure(String msg) {
        Result result = new Result();
        result.setStatus(Constant.STATUS_FAILURE);
        result.setMsg(msg);
        return result;
    }

    /**
     * 用来封装系统异常的统一提示
     *
     * @return 结果
     */
    public static Result setFailure() {
        Result result = new Result();
        result.setStatus(Constant.STATUS_FAILURE);
        result.setMsg("网络故障，请重启路由器");
        return result;
    }

    /**
     * 用来封装成功的统一提示
     *
     * @return 结果
     */
    public static Result setSuccess() {
        Result result = new Result();
        result.setStatus(Constant.STATUS_SUCCESS);
        result.setMsg("操作成功");
        return result;
    }

    /**
     * 用来封装成功的统一提示
     *
     * @return 结果
     */
    public static Result setSuccess(String msg) {
        Result result = new Result();
        result.setStatus(Constant.STATUS_SUCCESS);
        result.setMsg(msg);
        return result;
    }

    /**
     * 用来封装成功的统一提示
     *
     * @return 结果
     */
    public static Result setSuccess(Object data) {
        Result result = new Result();
        result.setStatus(Constant.STATUS_SUCCESS);
        result.setMsg("操作成功");
        result.setData(data);
        return result;
    }
}


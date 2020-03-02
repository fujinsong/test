package com.dtinone.utils;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName: Page
 * Description:分页工具类
 * date: 2019/12/1 16:12
 * @author : 付 劲 松
 */
public class Page<T> implements Serializable {
    /**
     * 当前页
     */
    private int pageNo;
    /**
     * 每页显示量
     */
    private int pageSize;
    /**
     * 总页数，没有数据的时候，默认为1
     */
    private int totalPage;
    /**
     * 当前页的数据
     */
    private List<T> list;

    public Page() {
        pageNo = 1;
        pageSize = Constant.DEFAULT_PAGE_SIZE;
        totalPage = 1;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        if (pageNo < 1) {
            pageNo = 1;
        }
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize < 1) {
            pageSize = Constant.DEFAULT_PAGE_SIZE;
        }
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}

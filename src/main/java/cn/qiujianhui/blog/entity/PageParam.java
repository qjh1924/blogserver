package cn.qiujianhui.blog.entity;

import lombok.Data;

@Data
public class PageParam {
    /**
     * 当前页数
     */
    private int pageNum;
    /**
     * 每页展示数量
     */
    private int pageSize;
    /**
     * 总条数
     */
    private long total;
    /**
     * 总页数
     */
    private int pages;
}

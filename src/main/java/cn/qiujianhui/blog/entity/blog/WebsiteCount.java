package cn.qiujianhui.blog.entity.blog;

import lombok.Data;

/**
 * 网站计数
 */
@Data
public class WebsiteCount {
    /**
     * 文章数量
     */
    private String articleCnt;
    /**
     * 浏览总数
     */
    private String viewCnt;
    /**
     * 评论总数
     */
    private String commentCnt;
}

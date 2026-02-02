package cn.qiujianhui.blog.entity.blog;

import lombok.Data;

/**
 * 文章对象
 */
@Data
public class Article {
    private String id;
    private String type;
    private String typeName;
    private String title;
    private String content;
    private String introduce;
    private String addTime;
    private String viewCnt;
    private String tags;
}

package cn.qiujianhui.blog.entity.tweet;

import cn.qiujianhui.blog.entity.PageParam;
import lombok.Data;

import java.util.List;

@Data
public class Tweet extends PageParam {
    /**
     * 推文id
     */
    private String id;
    /**
     * 推文内容
     */
    private String content;
    /**
     * 发布者
     */
    private String author;
    /**
     * 图片/视频列表
     */
    private List<TweetPic> picList;
    /**
     * 评论列表
     */
    private List<TweetComment> commentList;
    /**
     * 发布时间
     */
    private String publishTime;
    /**
     * 最后修改时间
     */
    private String updateTime;
}

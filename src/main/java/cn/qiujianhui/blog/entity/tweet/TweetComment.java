package cn.qiujianhui.blog.entity.tweet;

import lombok.Data;

@Data
public class TweetComment {
    private String id;
    private String tweetId;
    private String commentContent;
    private String commentPicPath;
    private String commentAuthor;
    private String publishTime;
}
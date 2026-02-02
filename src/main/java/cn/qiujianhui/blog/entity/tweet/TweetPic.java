package cn.qiujianhui.blog.entity.tweet;

import lombok.Data;

@Data
public class TweetPic {
    private String id;
    private String tweetId;
    private String type;
    private String picPath;
    private String order;
}
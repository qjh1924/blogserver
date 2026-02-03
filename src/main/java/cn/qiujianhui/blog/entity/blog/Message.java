package cn.qiujianhui.blog.entity.blog;

import lombok.Data;

/**
 * 文章评论留言
 */
@Data
public class Message {
    private String id;
    private String message;
    private String nickname;
    private String addTime;
    private String articleId;
    private String isValid;
    private String email;
}

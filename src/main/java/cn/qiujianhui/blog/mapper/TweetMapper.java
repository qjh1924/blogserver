package cn.qiujianhui.blog.mapper;

import cn.qiujianhui.blog.entity.tweet.Tweet;
import cn.qiujianhui.blog.entity.tweet.TweetComment;
import cn.qiujianhui.blog.entity.tweet.TweetPic;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TweetMapper {

    /**
     * 分页查询推文列表
     * @param tweet
     * @return
     */
    Page<Tweet> queryTweets(Tweet tweet);

    /**
     * 根据推文id查询关联图片
     * @param tweetIds
     * @return
     */
    List<TweetPic> queryTweetPicByIds(List<String> tweetIds);

    /**
     * 根据推文id查询关联评论
     * @param tweetIds
     * @return
     */
    List<TweetComment> queryTweetCommentByIds(List<String> tweetIds);

    /**
     * 新增推文
     * @param tweet 推文内容
     * @return 成功标识
     */
    int insertNewTweet(Tweet tweet);

    /**
     * 新增推文关联图片
     * @param tweetId 推文id
     * @param tweetPicList 推文关联图片
     * @return 成功标识
     */
    int insertTweetPicList(String tweetId, List<TweetPic> tweetPicList);

}

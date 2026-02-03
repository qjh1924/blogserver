package cn.qiujianhui.blog.controller;

import cn.qiujianhui.blog.entity.Result;
import cn.qiujianhui.blog.entity.tweet.Tweet;
import cn.qiujianhui.blog.entity.tweet.TweetComment;
import cn.qiujianhui.blog.entity.tweet.TweetPic;
import cn.qiujianhui.blog.mapper.TweetMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tweet")
public class TweetController {

    @Autowired
    TweetMapper tweetMapper;

    /**
     * 分页查询推文
     * @param tweet 请求参数
     * @return Result 结果数据
     */
    @GetMapping("/queryTweets")
    Result queryTweets(Tweet tweet) {
        try {
            // 查出推文列表
            PageHelper.startPage(tweet.getPageNum(), tweet.getPageSize());
            Page<Tweet> pages = tweetMapper.queryTweets(tweet);
            if (pages != null && pages.getResult().size() > 0) {

                // 查询关联的图片/视频
                List<String> tweetIds = pages.getResult().stream().map(Tweet::getId).toList();
                List<TweetPic> tweetPicList = tweetMapper.queryTweetPicByIds(tweetIds);
                List<TweetComment> tweetCommentList = tweetMapper.queryTweetCommentByIds(tweetIds);

                Result result = Result.success(pages.getResult());
                setPageParam(result, pages);
                return result;
            } else {
                return Result.fail("查询数据为空");
            }
        } catch (Exception e) {
            return Result.fail("数据查询失败：" + e.getMessage());
        }
    }

    private void setPageParam(Result result, Page pages) {
        result.setPages(pages.getPages());
        result.setPageNum(pages.getPageNum());
        result.setTotal(pages.getTotal());
        result.setPageSize(pages.getPageSize());
    }
}

package cn.qiujianhui.blog.controller;

import cn.qiujianhui.blog.entity.PageParam;
import cn.qiujianhui.blog.entity.Result;
import cn.qiujianhui.blog.entity.tweet.Tweet;
import cn.qiujianhui.blog.entity.tweet.TweetComment;
import cn.qiujianhui.blog.entity.tweet.TweetPic;
import cn.qiujianhui.blog.mapper.TweetMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tweet")
public class TweetController {

    @Autowired
    TweetMapper tweetMapper;

    /**
     * 分页查询推文
     * @param param 分页参数
     * @return Result 结果数据
     */
    @GetMapping("/queryTweets")
    Result queryTweets(PageParam param) {
        try {
            // 查出推文列表
            PageHelper.startPage(param.getPageNum(), param.getPageSize());
            Page<Tweet> pages = tweetMapper.queryTweets(null);
            if (pages != null && !pages.getResult().isEmpty()) {

                // 查询关联的图片/视频
                List<String> tweetIds = pages.getResult().stream().map(Tweet::getId).toList();
                List<TweetPic> tweetPicList = tweetMapper.queryTweetPicByIds(tweetIds);
                List<TweetComment> tweetCommentList = tweetMapper.queryTweetCommentByIds(tweetIds);

                Map<String, List<TweetPic>> tweetPicMap = tweetPicList.stream().collect(Collectors.groupingBy(TweetPic::getTweetId));
                Map<String, List<TweetComment>> tweetCommentMap = tweetCommentList.stream().collect(Collectors.groupingBy(TweetComment::getTweetId));

                // 整理数据
                pages.getResult().forEach(item -> {
                    item.setPicList(tweetPicMap.get(item.getId()));
                    item.setCommentList(tweetCommentMap.get(item.getId()));
                });

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
        PageParam param = new PageParam();
        param.setPages(pages.getPages());
        param.setPageNum(pages.getPageNum());
        param.setTotal(pages.getTotal());
        param.setPageSize(pages.getPageSize());
        result.setPageParam(param);
    }

    @PostMapping("/addNewTweet")
    @Transactional(rollbackFor = Exception.class)
    Result addNewTweet(Tweet tweet) {
        try {
            int flag = tweetMapper.insertNewTweet(tweet);
            if ( flag > 0 ) {
                tweetMapper.insertTweetPicList(tweet.getId(), tweet.getPicList());
            }

        } catch (Exception e) {

        }
        return null;
    }

}

package cn.qiujianhui.blog.controller;

import cn.qiujianhui.blog.entity.Article;
import cn.qiujianhui.blog.entity.Message;
import cn.qiujianhui.blog.entity.TypeInfo;
import cn.qiujianhui.blog.entity.WebsiteCount;
import cn.qiujianhui.blog.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 获取网站计数
     * @return WebsiteCount 网站计数
     */
    @GetMapping("/getWebsiteCounts")
    public WebsiteCount getWebsiteCounts() {
        return articleMapper.getWebsiteCounts();
    }

    /**
     * 获取最近的三条文章
     * @param pageId 页数
     * @return List<Article> 文章列表
     */
    @GetMapping("/getLatestArticles/{pageId}")
    List<Article> getLatestArticles(@PathVariable String pageId) {
        int page = Integer.parseInt(pageId);
        return articleMapper.getLatestArticles(3*page);
    }

    /**
     * 根据类别获取文章列表
     * @param typeId 类别id
     * @return List<Article> 文章列表
     */
    @GetMapping("getListByTypeId/{typeId}")
    List<Article> getListByTypeId(@PathVariable String typeId) {
        return articleMapper.getListByTypeId(typeId);
    }

    /**
     * 根据id获取文章
     * @param id 文章id
     * @return Article 文章内容
     */
    @GetMapping("getArticleById/{id}")
    Article getArticleById(@PathVariable String id) {
        // 文章浏览数+1
        articleMapper.increaseViewCnt(id);
        return articleMapper.getArticleById(id);
    }

    /**
     *获取文章评论留言
     * @param id 文章id
     * @return
     */
    @GetMapping("getMessagesById/{id}")
    List<Message> getMessagesById(@PathVariable String id) {
        return articleMapper.getMessagesById(id);
    }

    /**
     * 获取文章类别列表
     * @return List<TypeInfo> 文章类别列表
     */
    @GetMapping("getTypeInfo")
    List<TypeInfo> getTypeInfo() {
        return articleMapper.getTypeInfo();
    }

    /**
     * 获取所有文章列表
     * @return List<Article>
     */
    @GetMapping(value = "/getArticleList")
    List<Article> getArticleList() {
        return articleMapper.getArticleList();
    }


}

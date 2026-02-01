package cn.qiujianhui.blog.mapper;

import cn.qiujianhui.blog.entity.Article;
import cn.qiujianhui.blog.entity.Message;
import cn.qiujianhui.blog.entity.TypeInfo;
import cn.qiujianhui.blog.entity.WebsiteCount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {
    /**
     * 获取网站计数
     * @return
     */
    WebsiteCount getWebsiteCounts();

    /**
     * 获取最近的三条文章
     * @param pageId 页数
     * @return List<Article> 文章列表
     */
    List<Article> getLatestArticles(int pageId);

    /**
     * 根据类别获取文章列表
     * @param typeId 类别id
     * @return List<Article> 文章列表
     */
    List<Article> getListByTypeId(String typeId);

    /**
     * 文章浏览量+1
     * @param articleId 文章id
     * @return
     */
    void increaseViewCnt(String articleId);

    /**
     * 根据id获取文章
     * @param articleId 文章id
     * @return Article 文章内容
     */
    Article getArticleById(String articleId);

    /**
     *获取文章评论留言
     * @param articleId 文章id
     * @return
     */
    List<Message> getMessagesById(String articleId);

    /**
     * 获取文章类别列表
     * @return List<TypeInfo> 文章类别列表
     */
    List<TypeInfo> getTypeInfo();

    /**
     * 获取所有文章列表
     * @return List<Article>
     */
    List<Article> getArticleList();
}

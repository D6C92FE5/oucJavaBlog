package cn.edu.ouc.j2ee.handlers;

import java.util.List;

import cn.edu.ouc.j2ee.BaseHandler;
import cn.edu.ouc.j2ee.Util;
import cn.edu.ouc.j2ee.entities.CommentEntity;
import cn.edu.ouc.j2ee.entities.PostEntity;

import javax.xml.stream.events.Comment;


public class PostHandler extends BaseHandler {
    @Override
    public Object handle() {

        // TODO: read from db
        String postId = request.params(":id");
        PostEntity Post = (PostEntity)database.createQuery("From PostEntity WHERE id =?").setString(0,postId).uniqueResult();
        List<CommentEntity> commentList = (List<CommentEntity>)database.createQuery("FROM CommentEntity WHERE  id=? order by date desc").setString(0,postId).uniqueResult();
        modal.put("post",Post);
        /*HashMap<String, Object> comment = new HashMap<>();
        comment.put("id", 10);
        comment.put("author", "lzlk");
        comment.put("body", "<p>评论~</p>");
        comment.put("date", Util.toString(new Date()));
        HashMap<String, Object> post = new HashMap<>();
        post.put("id", 1);
        post.put("title", "文章 1");
        post.put("author", "test");
        post.put("date", Util.toString(new Date()));
        post.put("body", "<p>测试</p><p>内容</p>");

        modal.put("post", post);
        modal.put("comment", comment);*/
        modal.put("comment",commentList);
        return rendered();

    }

    @Override
    public BaseHandler acceptGet(String path) {
        return super.acceptGet(path + "/:id");
    }
}

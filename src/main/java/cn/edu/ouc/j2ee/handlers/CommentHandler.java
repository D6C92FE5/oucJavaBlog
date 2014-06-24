package cn.edu.ouc.j2ee.handlers;

import cn.edu.ouc.j2ee.BaseHandler;
import cn.edu.ouc.j2ee.entities.CommentEntity;
import cn.edu.ouc.j2ee.entities.PostEntity;

import java.util.Date;


public class CommentHandler extends BaseHandler {
    @Override
    public Object handle() {

        String postId = request.params(":post-id");
        PostEntity post =(PostEntity)database.createQuery("From PostEntity WHERE id =?").setString(0,postId).uniqueResult();
        String username =  request.queryParams("username");
        String email = request.queryParams("email");
        String body = request.queryParams("body");

        CommentEntity comment  = new CommentEntity();
        comment.setAuthor(username);
        comment.setPost(post);
        comment.setEmail(email);
        comment.setBody(body);
        comment.setDate(new Date());
        database.save(comment);
        // TODO: save post to db
        int  commentId = comment.getId();

        response.redirect("/post/" + postId + "#comment-" + commentId);

        return rendered();

    }

    @Override
    public BaseHandler acceptPost(String path) {
        super.acceptPost(path + "/:post-id");
        return this;
    }
}

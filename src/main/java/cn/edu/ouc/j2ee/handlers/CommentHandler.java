package cn.edu.ouc.j2ee.handlers;

import cn.edu.ouc.j2ee.BaseHandler;
import cn.edu.ouc.j2ee.entities.CommentEntity;
import cn.edu.ouc.j2ee.entities.PostEntity;

import java.util.Date;


public class CommentHandler extends BaseHandler {
    @Override
    public Object handle() {

        String postId = request.params(":post-id");
        PostEntity post =(PostEntity)database.createQuery("From PostEntity WHERE id =?")
                .setString(0, postId).uniqueResult();

        String nickname =  request.queryParams("nickname");
        String email = request.queryParams("email");
        String body = request.queryParams("body");

        CommentEntity comment  = new CommentEntity();
        comment.setPost(post);
        comment.setDate(new Date());
        comment.setAuthor(nickname);
        comment.setEmail(email);
        comment.setBody(body);

        database.save(comment);

        return redirect("/post/" + postId + "#comment-" + comment.getId());

    }

    @Override
    public BaseHandler acceptPost(String path) {
        super.acceptPost(path + "/:post-id");
        return this;
    }
}

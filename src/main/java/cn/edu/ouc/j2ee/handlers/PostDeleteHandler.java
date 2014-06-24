package cn.edu.ouc.j2ee.handlers;

import cn.edu.ouc.j2ee.BaseHandler;
import cn.edu.ouc.j2ee.entities.PostEntity;


public class PostDeleteHandler extends BaseHandler {
    @Override
    public Object handle() {

        String postId = request.params(":id");
        PostEntity  post = (PostEntity)database.createQuery("FROM PostEntity WHERE id=?")
                .setString(0, postId).uniqueResult();
        if(!post.getAuthor().equals(currentUser))
            return null;
        if(post!=null)   database.delete(post);
        else return "文章不存在";

        // TODO: delete post from db

        response.redirect("/index");
        return null;

    }

    @Override
    public BaseHandler acceptGet(String path) {
        super.acceptGet(path);
        super.acceptGet(path + "/:id");
        return this;
    }
}

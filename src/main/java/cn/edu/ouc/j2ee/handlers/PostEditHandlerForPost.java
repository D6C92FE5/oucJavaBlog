package cn.edu.ouc.j2ee.handlers;

import java.util.Date;

import cn.edu.ouc.j2ee.BaseHandler;
import cn.edu.ouc.j2ee.entities.PostEntity;


public class PostEditHandlerForPost extends BaseHandler {
    @Override
    public Object handle() {

        String postId = request.params(":id");

        PostEntity post = (PostEntity)database.createQuery("FROM PostEntity WHERE id=?")
                .setString(0, postId).uniqueResult();
        if (post == null) {
            post = new PostEntity();
        }

        post.setDate(new Date());
        post.setPublicity(request.queryParams("publicity") != null);
        post.setTitle(request.queryParams("title"));
        post.setBody(request.queryParams("body"));
        post.setAuthor(currentUser);

        database.saveOrUpdate(post);
        return redirect("/post/" + post.getId());

    }
    @Override
    public BaseHandler acceptPost(String path) {
        super.acceptPost(path);
        super.acceptPost(path + "/:id");
        return this;
    }
}

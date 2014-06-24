package cn.edu.ouc.j2ee.handlers;

import cn.edu.ouc.j2ee.BaseHandler;
import cn.edu.ouc.j2ee.entities.PostEntity;


public class PostHandler extends BaseHandler {
    @Override
    public Object handle() {

        String postId = request.params(":id");
        PostEntity post = (PostEntity)database.createQuery("From PostEntity WHERE id =?")
                .setString(0, postId).uniqueResult();

        post.initIsMy(currentUser);

        modal.put("post", post);
        modal.put("comments", post.getComments().toArray());
        return rendered();

    }

    @Override
    public BaseHandler acceptGet(String path) {
        return super.acceptGet(path + "/:id");
    }
}

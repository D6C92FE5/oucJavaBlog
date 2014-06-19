package cn.edu.ouc.j2ee.handlers;

import cn.edu.ouc.j2ee.BaseHandler;


public class PostEditHandlerForPost extends BaseHandler {
    @Override
    public Object handle() {

        String postId = request.params(":id");

        // TODO: save post to db

        response.redirect("/post/" + postId);
        return null;

    }

    @Override
    public BaseHandler acceptPost(String path) {
        super.acceptPost(path);
        super.acceptPost(path + "/:id");
        return this;
    }
}

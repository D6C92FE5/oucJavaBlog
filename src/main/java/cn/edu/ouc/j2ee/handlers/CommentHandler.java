package cn.edu.ouc.j2ee.handlers;

import cn.edu.ouc.j2ee.BaseHandler;


public class CommentHandler extends BaseHandler {
    @Override
    public Object handle() {

        String postId = request.params(":post-id");

        // TODO: save post to db
        int commentId = 123;

        response.redirect("/post/" + postId + "#comment-" + commentId);

        return null;

    }

    @Override
    public BaseHandler acceptPost(String path) {
        super.acceptPost(path + "/:post-id");
        return this;
    }
}

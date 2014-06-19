package cn.edu.ouc.j2ee.handlers;

import cn.edu.ouc.j2ee.BaseHandler;


public class PostDeleteHandler extends BaseHandler {
    @Override
    public Object handle() {

        String postId = request.params(":id");

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

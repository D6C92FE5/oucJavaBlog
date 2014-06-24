package cn.edu.ouc.j2ee.handlers;

import java.util.Date;
import java.util.HashMap;

import cn.edu.ouc.j2ee.BaseHandler;
import cn.edu.ouc.j2ee.Util;
import cn.edu.ouc.j2ee.entities.PostEntity;
import cn.edu.ouc.j2ee.entities.UserEntity;


public class PostEditHandlerForGet extends BaseHandler {
    @Override
    public Object handle() {

        String postId = request.params(":id");
        if (postId != null) {
            // TODO: read from db
            PostEntity Post = (PostEntity)database.createQuery("From PostEntity WHERE id =?").setString(0,postId).uniqueResult();
            modal.put("post",Post);
        } else {
            modal.put("post","123");
        }
        return rendered();

    }
    @Override
    public BaseHandler acceptGet(String path) {
        super.acceptGet(path);
        super.acceptGet(path + "/:id");
        return this;
    }
}

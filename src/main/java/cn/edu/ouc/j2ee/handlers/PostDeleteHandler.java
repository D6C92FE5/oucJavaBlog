package cn.edu.ouc.j2ee.handlers;

import cn.edu.ouc.j2ee.BaseHandler;
import cn.edu.ouc.j2ee.entities.PostEntity;


public class PostDeleteHandler extends BaseHandler {
    @Override
    public Object handle() {

        String postId = request.params(":id");
        PostEntity  post = (PostEntity)database.createQuery("FROM PostEntity WHERE id=?")
                .setString(0, postId).uniqueResult();
        if(post!=null) {
            database.createQuery("delete  From PostEntity where id =?").setString(0, postId);
        }
        else return showMessage("文章不存在");

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

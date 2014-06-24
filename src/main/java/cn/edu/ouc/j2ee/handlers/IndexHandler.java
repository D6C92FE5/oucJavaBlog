package cn.edu.ouc.j2ee.handlers;


import java.util.List;

import cn.edu.ouc.j2ee.BaseHandler;
import cn.edu.ouc.j2ee.entities.PostEntity;


public class IndexHandler extends BaseHandler {
    @Override
    public Object handle() {

        List<PostEntity> posts = (List<PostEntity>)database.createQuery(
                "From PostEntity order by date desc").list();

        for (PostEntity post: posts) {
            post.initCommentCount();
            post.initIsMy(currentUser);
        }

        modal.put("posts", posts);
        return rendered();

    }
}

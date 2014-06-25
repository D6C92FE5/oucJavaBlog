package cn.edu.ouc.j2ee.handlers;


import java.util.List;

import cn.edu.ouc.j2ee.BaseHandler;
import cn.edu.ouc.j2ee.entities.PostEntity;


public class IndexHandler extends BaseHandler {
    @Override
    public Object handle() {
        List<PostEntity> posts;
        if(currentUser==null){
            posts = (List<PostEntity>)database.createQuery(
             "From PostEntity where publicity = true order by date desc").list();
        }
        else {
            String UserId = Integer.toString(currentUser.getId());
            posts = (List<PostEntity>) database.createQuery(
                    "From PostEntity where publicity = true or author.id = ? order by date desc").setString(0,UserId).list();

        }
        for (PostEntity post : posts) {
            post.initCommentCount();
            post.initIsMy(currentUser);
        }
        modal.put("posts", posts);
        return rendered();

    }
}

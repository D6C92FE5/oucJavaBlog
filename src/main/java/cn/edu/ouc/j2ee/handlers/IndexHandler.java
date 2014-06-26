package cn.edu.ouc.j2ee.handlers;


import java.util.List;

import cn.edu.ouc.j2ee.BaseHandler;
import cn.edu.ouc.j2ee.entities.PostEntity;


public class IndexHandler extends BaseHandler {
    @Override
    public Object handle() {

        List<PostEntity> posts;
        if (currentUser==null){
            posts = (List<PostEntity>)database.createQuery(
                    "From PostEntity where publicity = true order by date desc").list();
        }
        else {
            if(false)
                posts = (List<PostEntity>)database.createQuery("From PostEntity where author.id = ? order by date desc")
                        .setInteger(0,currentUser.getId()).list();
            else
                posts = (List<PostEntity>)database.createQuery(
                        "From PostEntity where publicity = true or author.id = ? order by date desc")
                         .setInteger(0, currentUser.getId()).list();
        }
        for (PostEntity post : posts) {
            post.initCommentCount();
            post.initIsMy(currentUser);
        }

        modal.put("posts", posts);
        return rendered();

    }
}

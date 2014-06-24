package cn.edu.ouc.j2ee.handlers;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import cn.edu.ouc.j2ee.BaseHandler;
import cn.edu.ouc.j2ee.entities.PostEntity;
import org.eclipse.jetty.server.Authentication;


public class IndexHandler extends BaseHandler {
    @Override
    public Object handle() {
        List<PostEntity> postList = (List<PostEntity>)database.createQuery("From PostEntity order by date desc").list();

        modal.put("post", postList);
        return rendered();
    }
}

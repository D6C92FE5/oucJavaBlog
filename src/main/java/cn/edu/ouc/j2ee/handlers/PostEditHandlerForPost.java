package cn.edu.ouc.j2ee.handlers;

import cn.edu.ouc.j2ee.BaseHandler;
import cn.edu.ouc.j2ee.entities.PostEntity;
import cn.edu.ouc.j2ee.entities.UserEntity;
import org.eclipse.jetty.server.Authentication;
import org.eclipse.jetty.server.session.JDBCSessionManager;
import org.hibernate.Session;

import javax.net.ssl.SSLSession;
import java.util.Date;


public class PostEditHandlerForPost extends BaseHandler {
    @Override
    public Object handle() {
        String postId = request.params(":id");
        if (postId != null) {
            // TODO: read from db
            PostEntity post = (PostEntity)database.createQuery("FROM PostEntity WHERE id=?").setString(0,postId).uniqueResult();
            String title  = request.queryParams("title");
            Boolean publicity=Boolean.getBoolean(request.queryParams("publicity"));
            String body = request.queryParams("body");
            post.setPublicity(publicity);
            post.setTitle(title);
            post.setBody(body);
            post.setAuthor(request.session().attribute("user"));
            post.setDate(new Date());
            database.save(post);
            response.redirect("/post/" + postId);
        } else {
            // TODO: save post to db
            String title  = request.queryParams("title");
            Boolean publicity=Boolean.getBoolean(request.queryParams("publicity"));
            String body = request.queryParams("body");
            PostEntity post = new PostEntity();
            post.setPublicity(publicity);
            post.setTitle(title);
            post.setBody(body);
            post.setAuthor(request.session().attribute("user"));
            post.setDate(new Date());
            database.save(post);
            int  PostId = post.getId();
            response.redirect("/post/" + PostId);
        }
        return rendered();
    }
    @Override
    public BaseHandler acceptPost(String path) {
        super.acceptPost(path);
        super.acceptPost(path + "/:id");
        return this;
    }
}

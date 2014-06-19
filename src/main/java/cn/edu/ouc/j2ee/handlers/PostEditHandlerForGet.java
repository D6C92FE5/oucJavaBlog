package cn.edu.ouc.j2ee.handlers;

import java.util.Date;
import java.util.HashMap;

import cn.edu.ouc.j2ee.BaseHandler;
import cn.edu.ouc.j2ee.Util;


public class PostEditHandlerForGet extends BaseHandler {
    @Override
    public Object handle() {

        String postId = request.params(":id");
        if (postId != null) {
            // TODO: read from db
            HashMap<String, Object> post = new HashMap<>();
            post.put("id", 1);
            post.put("title", "文章 1");
            post.put("author", "test");
            post.put("date", Util.toString(new Date()));
            post.put("body", "<p>测试</p><p>内容</p>");
            post.put("public", false);

            modal.put("post", post);
        } else {
            modal.put("post", true);
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

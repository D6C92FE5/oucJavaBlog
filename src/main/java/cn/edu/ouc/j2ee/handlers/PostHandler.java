package cn.edu.ouc.j2ee.handlers;

import java.util.Date;
import java.util.HashMap;

import cn.edu.ouc.j2ee.BaseHandler;
import cn.edu.ouc.j2ee.Util;


public class PostHandler extends BaseHandler {
    @Override
    public Object handle() {

        // TODO: read from db
        HashMap<String, Object> comment = new HashMap<>();
        comment.put("id", 10);
        comment.put("author", "lzlk");
        comment.put("body", "<p>评论~</p>");
        comment.put("date", Util.toString(new Date()));
        HashMap<String, Object> post = new HashMap<>();
        post.put("id", 1);
        post.put("title", "文章 1");
        post.put("author", "test");
        post.put("date", Util.toString(new Date()));
        post.put("body", "<p>测试</p><p>内容</p>");

        modal.put("post", post);
        modal.put("comment", comment);

        return rendered();

    }

    @Override
    public BaseHandler acceptGet(String path) {
        return super.acceptGet(path + "/:id");
    }
}

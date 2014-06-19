package cn.edu.ouc.j2ee.handlers;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import cn.edu.ouc.j2ee.BaseHandler;
import cn.edu.ouc.j2ee.Util;


public class IndexHandler extends BaseHandler {
    @Override
    public Object handle() {

        // TODO: read from db
        HashMap<String, Object> post = new HashMap<>();
        post.put("id", 1);
        post.put("title", "文章 1");
        post.put("author", "test");
        post.put("date", Util.toString(new Date()));
        post.put("body", "<p>测试</p><p>内容</p>");
        post.put("comment_count", 5);

        modal.put("post", Arrays.asList(post, post, post));

        return rendered();

    }
}

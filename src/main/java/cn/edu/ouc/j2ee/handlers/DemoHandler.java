package cn.edu.ouc.j2ee.handlers;

import cn.edu.ouc.j2ee.BaseHandler;


public class DemoHandler extends BaseHandler {
    @Override
    public Object handle() {
        modal.put("test", "!!!!");
        return rendered();
    }
}

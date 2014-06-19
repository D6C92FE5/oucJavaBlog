package cn.edu.ouc.j2ee.handlers;

import cn.edu.ouc.j2ee.BaseHandler;


public class LoginHandler extends BaseHandler {
    @Override
    public Object handle() {

        String username = request.queryParams("username");
        String password = request.queryParams("password");

        if (username == null) {
            return rendered();
        }

        // TODO: vertify user info

        request.session().attribute("user", username);
        response.redirect("/");

        return null;

    }
}

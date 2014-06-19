package cn.edu.ouc.j2ee.handlers;

import cn.edu.ouc.j2ee.BaseHandler;


public class SignupHandler extends BaseHandler {
    @Override
    public Object handle() {

        String username = request.queryParams("username");
        String password = request.queryParams("password");

        if (username == null) {
            return rendered();
        }

        // TODO: save user info to db

        request.session().attribute("user", username);
        response.redirect("/");

        return null;

    }
}

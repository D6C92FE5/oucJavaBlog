package cn.edu.ouc.j2ee.handlers;

import cn.edu.ouc.j2ee.BaseHandler;


public class LogoutHandler extends BaseHandler {
    @Override
    public Object handle() {

        request.session().removeAttribute("user");
        response.removeCookie("user-id");
        response.removeCookie("user-key");

        return redirect("/");

    }
}

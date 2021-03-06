package cn.edu.ouc.j2ee.handlers;

import cn.edu.ouc.j2ee.BaseHandler;
import cn.edu.ouc.j2ee.entities.UserEntity;
import org.jasypt.util.password.StrongPasswordEncryptor;


public class LoginHandler extends BaseHandler {
    @Override
    public Object handle() {

        String username = request.queryParams("username");
        String password = request.queryParams("password");

        if (username == null) {
            if (currentUser != null) {
                return redirect("/");
            }

            return rendered();
        }

        UserEntity user = (UserEntity)database.createQuery("FROM UserEntity WHERE username=?")
                .setString(0, username).uniqueResult();
        if (user == null || !new StrongPasswordEncryptor()
                .checkPassword(password, user.getPassword())) {
            return showMessage("用户名或密码错误");
        }

        login(user);
        return redirect("/");

    }
}

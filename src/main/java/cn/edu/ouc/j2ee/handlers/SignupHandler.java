package cn.edu.ouc.j2ee.handlers;

import cn.edu.ouc.j2ee.BaseHandler;
import cn.edu.ouc.j2ee.entity.UserEntity;
import org.jasypt.util.password.StrongPasswordEncryptor;


public class SignupHandler extends BaseHandler {
    @Override
    public Object handle() {

        String username = request.queryParams("username");
        String password = request.queryParams("password");
        String password2 = request.queryParams("password2");

        if (username == null) {
            return rendered();
        }

        // TODO: 检查用户名是否已存在，检查两次密码输入是否一致

        password = new StrongPasswordEncryptor().encryptPassword(password);

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(password);

        database.save(user);

        request.session().attribute("user", user);
        response.redirect("/");

        return null;

    }
}

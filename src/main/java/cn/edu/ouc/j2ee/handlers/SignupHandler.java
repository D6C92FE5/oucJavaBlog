package cn.edu.ouc.j2ee.handlers;

import cn.edu.ouc.j2ee.BaseHandler;
import cn.edu.ouc.j2ee.entities.UserEntity;
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

        UserEntity User =(UserEntity)database.createQuery("FROM UserEntity WHERE username =?").setString(0,username).uniqueResult();
        if(User!=null)
        {
            return  showMessage("用户名已存在！");
        }
        if(!password.equals(password2))
            return showMessage("请输入相同密码");
        password = new StrongPasswordEncryptor().encryptPassword(password);
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(password);

        database.save(user);

        login(user);
        return redirect("/");

    }
}

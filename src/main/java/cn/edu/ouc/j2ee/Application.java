package cn.edu.ouc.j2ee;

import cn.edu.ouc.j2ee.handlers.*;
import spark.Spark;
import spark.servlet.SparkApplication;


public class Application implements SparkApplication {

    @Override
    public void init() {
        Spark.staticFileLocation("/webapp");

        new IndexHandler().acceptGet().acceptGet("/").acceptGet("/post");
        new PostHandler().acceptGet();
        new PostEditHandlerForGet().acceptGet();
        new PostEditHandlerForPost().acceptPost();
        new CommentHandler().acceptPost();
        new LoginHandler().acceptGet().acceptPost();
        new LogoutHandler().acceptGet();
        new SignupHandler().acceptGet().acceptPost();
        new AboutHandler().acceptGet().acceptPost();

    }

}

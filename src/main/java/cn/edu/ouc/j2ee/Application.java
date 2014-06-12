package cn.edu.ouc.j2ee;

import spark.Spark;
import spark.servlet.SparkApplication;

import cn.edu.ouc.j2ee.handlers.DemoHandler;


public class Application implements SparkApplication {

    @Override
    public void init() {
        Spark.staticFileLocation("/webapp");

        new DemoHandler().acceptGet().acceptGet("/").acceptPost();


    }

}

package cn.edu.ouc.j2ee;

import spark.Spark;
import spark.route.SimpleRouteMatcher;

import java.lang.reflect.Field;
import java.util.List;


public class Main {

    public static void printRoutes() {
        try {
            Field f = Spark.class.getSuperclass().getDeclaredField("routeMatcher");
            f.setAccessible(true);
            SimpleRouteMatcher routeMatcher = (SimpleRouteMatcher) f.get(null);

            f = routeMatcher.getClass().getDeclaredField("routes");
            f.setAccessible(true);
            List<Object> routes = (List<Object>) f.get(routeMatcher);

            System.out.println("\nRoutes:");
            for (Object route : routes) {
                System.out.println(route);
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Application().init();
        printRoutes();
    }

}

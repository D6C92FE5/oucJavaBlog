package cn.edu.ouc.j2ee;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class Util {

    public static SimpleDateFormat dateFormater =
            new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss.SSS");

    public static String toString(Date date) {
        return dateFormater.format(date);
    }

    public static int tryToInt(String s, int on_invalid) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return on_invalid;
        }
    }

    public static Session getHibernateSession() {
        Configuration config = new Configuration().configure();
        return config.buildSessionFactory(new StandardServiceRegistryBuilder()
                .applySettings(config.getProperties()).build()).openSession();
    }

}

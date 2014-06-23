package cn.edu.ouc.j2ee;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import cn.edu.ouc.j2ee.entities.UserEntity;
import com.google.common.base.CaseFormat;
import org.hibernate.Session;
import spark.*;
import spark.template.mustache.MustacheTemplateEngine;


public abstract class BaseHandler implements TemplateViewRoute {

    protected String hyphenName;

    protected boolean authenticated = false;

    protected Request request;
    protected Response response;

    protected Session database;

    protected UserEntity currentUser;

    protected HashMap<String, Object> modal = new HashMap<>();

    public BaseHandler() {
        String name = this.getClass().getSimpleName();
        for (String suffix : Arrays.asList("HandlerForGet", "HandlerForPost", "Handler")) {
            if (name.endsWith(suffix)) {
                name = name.substring(0, name.length() - suffix.length());
                break;
            }
        }
        this.hyphenName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_HYPHEN, name);
    }

    protected ModelAndView rendered() {
        return rendered(modal.clone());
    }
    protected ModelAndView rendered(Object modal) {
        return new ModelAndView(modal, hyphenName + ".html");
    }

    protected Object showMessage(String message) {
        HashMap<String, Object> modal = new HashMap<>();
        modal.put("message", message);
        return rendered(modal);
    }

    public abstract Object handle();
    public ModelAndView handle(Request request, Response response) {
        this.request = request;
        this.response = response;

        database = Util.getHibernateSession();
        database.beginTransaction();

        currentUser = request.session().attribute("user");

        if (authenticated && currentUser == null) {
            response.redirect("/login");
            return null;
        }

        modal.put("user", request.session().attribute("user"));

        Object res = handle();

        database.getTransaction().commit();
        database.close();

        if (res instanceof ModelAndView) {
            return (ModelAndView) res;
        } else {
            Map<String, Object> data = new HashMap<>();
            data.put("data", res);
            return new ModelAndView(data, "literal.template");
        }
    }

    protected static TemplateEngine templateEngine = new MustacheTemplateEngine("webapp");
    public BaseHandler acceptGet() {
        return acceptGet("/" + hyphenName);
    }
    public BaseHandler acceptGet(String path) {
        Spark.get(path, this, templateEngine);
        return this;
    }
    public BaseHandler acceptPost() {
        return acceptPost("/" + hyphenName);
    }
    public BaseHandler acceptPost(String path) {
        Spark.post(path, this, templateEngine);
        return this;
    }

}

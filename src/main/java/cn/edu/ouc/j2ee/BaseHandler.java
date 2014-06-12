package cn.edu.ouc.j2ee;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.CaseFormat;
import spark.*;
import spark.template.mustache.MustacheTemplateEngine;


public abstract class BaseHandler implements TemplateViewRoute {

    protected String hyphenName;

    protected Request request;
    protected Response response;

    protected HashMap<String, Object> modal = new HashMap<>();

    public BaseHandler() {
        String name = this.getClass().getSimpleName();
        for (String suffix : Arrays.asList("GetHandler", "PostHandler", "Handler")) {
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

    public abstract Object handle();
    public ModelAndView handle(Request request, Response response) {
        this.request = request;
        this.response = response;

        Object res = handle();
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

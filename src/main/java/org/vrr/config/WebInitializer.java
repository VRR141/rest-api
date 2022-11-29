package org.vrr.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{HibernateConfig.class, WebConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}

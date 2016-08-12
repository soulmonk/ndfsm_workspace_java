package com.soulmonk.ndfsm.configuration;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * ndfsm
 * Created by SoulMonk on 11.08.2016.
 */
public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
    }

    @Override
    protected Class< ? >[] getRootConfigClasses() {
        return new Class< ? >[] { RootContextConfig.class };
    }

    @Override
    protected Class< ? >[] getServletConfigClasses() {

        return new Class< ? >[] { WebAppConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[] {
                new HiddenHttpMethodFilter(),
                characterEncodingFilter(),
                securityFilterChain()
        };
    }

    @Override
    protected String getServletName() {
        return "appServlet";
    }

    protected DelegatingFilterProxy securityFilterChain() {
        return new DelegatingFilterProxy("springSecurityFilterChain");
    }

    protected CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }
}
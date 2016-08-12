package com.soulmonk.ndfsm.configuration;

import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.theme.CookieThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;
import org.springframework.web.servlet.view.tiles2.TilesConfigurer;
import org.springframework.web.servlet.view.tiles2.TilesView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

/**
 * ndfsm
 * Created by SoulMonk on 11.08.2016.
 */
@Configuration
@EnableWebMvc
@ComponentScan(value = {
        "com.soulmonk.ndfsm.web.app.controllers",
})
@PropertySource("classpath:application.properties")
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Resource
    private Environment env;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations(
                        "/",
                        "classpath:/META-INF/web-resources"
                );
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(true)
                .useJaf(false)
                .ignoreAcceptHeader(true)
                .mediaType("html", MediaType.TEXT_HTML)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .defaultContentType(MediaType.TEXT_HTML);
    }

    @Override
    public Validator getValidator() {
        return validator();
    }

    @Bean(name = "tilesViewResolver")
    public UrlBasedViewResolver tilesViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setViewClass(TilesView.class);
        return resolver;
    }

    @Bean(name = "tilesConfigurer")
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer configurer = new TilesConfigurer();
        configurer.setDefinitions(
                "/WEB-INF/layouts/layouts.xml",
                "/WEB-INF/views/**/views.xml"
        );
        return configurer;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(themeChangeInterceptor());
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Bean
    public ThemeChangeInterceptor themeChangeInterceptor() {
        return new ThemeChangeInterceptor();
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/uncaughtException");
        registry.addViewController("/resourceNotFound");
        registry.addViewController("/dataAccessFailure");
    }

    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(
                "WEB-INF/i18n/messages",
                "WEB-INF/i18n/application",
                "WEB-INF/i18n/time",
                "WEB-INF/i18n/user",
                "WEB-INF/i18n/note",
                "WEB-INF/i18n/steam_web"
        );
        messageSource.setFallbackToSystemLocale(false);
        return messageSource;
    }

    @Bean(name = "localeResolver")
    public CookieLocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setCookieName("local");
        return localeResolver;
    }

    @Bean(name = "themeSource")
    public ResourceBundleThemeSource themeSource() {
        return new ResourceBundleThemeSource();
    }

    @Bean(name = "themeResolver")
    public CookieThemeResolver themeResolver() {
        CookieThemeResolver themeResolver = new CookieThemeResolver();
        themeResolver.setCookieName("theme");
        themeResolver.setDefaultThemeName("standard");
        return themeResolver;
    }

    @Bean(name = "validator")
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        return validator;
    }

    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        resolver.setExceptionMappings(simpleMappingExceptionResolverProperties());
        return resolver;
    }

    private Properties simpleMappingExceptionResolverProperties() {
        Properties properties = new Properties();
        properties.put(".DataAccessException", "dataAccessFailure");
        properties.put(".NoSuchRequestHandlingMethodException", "resourceNotFound");
        properties.put(".TypeMismatchException", "resourceNotFound");
        properties.put(".MissingServletRequestParameterException", "resourceNotFound");
        return properties;
    }

    @Bean(name = "multipartResolve")
    public StandardServletMultipartResolver multipartResolve() {
        return new StandardServletMultipartResolver();
    }

}

package com.soulmonk.ndfsm.configuration;

import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * ndfsm
 * Created by SoulMonk on 11.08.2016.
 */
@Configuration
@EnableWebMvc
@Import({
        DatasourceConfig.class,
        SecurityConfig.class
})
@ComponentScan(value = {
        "com.soulmonk.ndfsm.web.app.controllers",

})
@PropertySource("classpath:application.properties")
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Resource
    private Environment env;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
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

    @Bean
    public ViewResolver contentNegotiatingViewResolver(
            ContentNegotiationManager manager) {

        List< ViewResolver > resolvers = new ArrayList< ViewResolver >();

        InternalResourceViewResolver r1 = new InternalResourceViewResolver();
        r1.setPrefix("/WEB-INF/pages/");
        r1.setSuffix(".jsp");
        r1.setViewClass(JstlView.class);
        resolvers.add(r1);

        JsonViewResolver r2 = new JsonViewResolver();
        resolvers.add(r2);

        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setViewResolvers(resolvers);
        resolver.setContentNegotiationManager(manager);
        return resolver;

    }

    /**
     * View resolver for returning JSON in a view-based system. Always returns a
     * {@link MappingJacksonJsonView}.
     */
    public class JsonViewResolver implements ViewResolver {
        public View resolveViewName(String viewName, Locale locale)
                throws Exception {
            MappingJacksonJsonView view = new MappingJacksonJsonView();
            view.setPrettyPrint(true);
            return view;
        }
    }
}

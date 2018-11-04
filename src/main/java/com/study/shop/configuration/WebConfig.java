package com.study.shop.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.study.shop.web.controller")
public class WebConfig implements WebMvcConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        logger.debug("WebConfig.addResourceHandlers() invocation");
        registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
    }

//    @Bean
//    ServletContextTemplateResolver templateResolver(ServletContext servletContext) {
//        ServletContextTemplateResolver servletContextTemplateResolver = new ServletContextTemplateResolver(servletContext);
//        servletContextTemplateResolver.setPrefix("/WEB-INF/view/");
//        servletContextTemplateResolver.setSuffix(".html");
//        servletContextTemplateResolver.setCharacterEncoding("UTF-8");
//        servletContextTemplateResolver.setTemplateMode(TemplateMode.HTML);
//        return servletContextTemplateResolver;
//    }

    @Bean
    public ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver springResourceTemplateResolver = new
                SpringResourceTemplateResolver();
        springResourceTemplateResolver.setApplicationContext(applicationContext);
        springResourceTemplateResolver.setPrefix("/WEB-INF/view/");
        springResourceTemplateResolver.setTemplateMode(TemplateMode.HTML);
        springResourceTemplateResolver.setSuffix(".html");
        springResourceTemplateResolver.setCharacterEncoding("UTF-8");
        return springResourceTemplateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver(servletContext));
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    @Bean
    public ViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }

//    <bean id="templateEngine"
//    class="org.thymeleaf.spring4.SpringTemplateEngine">
//        <property name="templateResolver"ref="templateResolver"/>
//    </bean>
//
//    <bean class="org.thymeleaf.spring4.view.ThymeleafViewResolver">
//        <property name="templateEngine"ref="templateEngine"/>
//    </bean>


}

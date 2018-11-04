package com.study.shop.configuration;

import com.study.shop.web.filter.SecurityFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class HierarchicalApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Filter[] getServletFilters() {
        logger.debug("CALLED getServletFilters()");
        return new Filter[]{new SecurityFilter()};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        logger.debug("CALLED getRootConfigClasses()");
        return new Class[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        logger.debug("CALLED getServletConfigClasses()");
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        logger.debug("CALLED getServletMappings()");
        return new String[] { "/*" };
    }
}
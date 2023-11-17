package com.example.jwttutorial.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@Configuration
public class MessageConfig implements WebMvcConfigurer {
    @Bean(value = "localResolver")
    public LocaleResolver getLocaleResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setCookieDomain("myAppLocaleCookie");
        localeResolver.setDefaultLocale(new Locale("vi", "VN"));
        localeResolver.setCookieMaxAge(60 * 60);
        return localeResolver;
    }

    @Bean(value = "messageSource")
    public MessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("language");
        registry.addInterceptor(localeChangeInterceptor).addPathPatterns("/*");
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    @Bean
    public LocalValidatorFactoryBean getValidator(MessageSource messageSource) {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);
        return bean;
    }

    public String message(String key, String... value) {
        return getMessageSource().getMessage(key, value, LocaleContextHolder.getLocale());
    }
}
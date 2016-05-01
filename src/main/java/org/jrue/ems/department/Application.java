package org.jrue.ems.department;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Application Startup Class. It serves as both the runtime application entry
 * point and the central Java configuration class.
 * 
 * @author Joel F. Ruelos Jr.
 * @since 1.0
 */

@SpringBootApplication
@EnableTransactionManagement
@EnableAspectJAutoProxy
@EnableCaching(order = 2)
public class Application {

    public static void main(String[] args) {
	SpringApplication.run(Application.class, args);
    }

    @Bean
    public CacheManager cacheManager() {
	GuavaCacheManager cacheManager = new GuavaCacheManager("departments");
	return cacheManager;
    }

    @Bean
    public MessageSource messageSource() {
	ReloadableResourceBundleMessageSource messageBundle = new ReloadableResourceBundleMessageSource();
	messageBundle.setBasename("classpath:messages/messages");
	messageBundle.setDefaultEncoding("UTF-8");
	return messageBundle;
    }
}
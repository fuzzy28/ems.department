package org.jrue.hris.master;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Application Startup Class
 * 
 * @author Joel F. Ruelos Jr.
 * @since 1.0
 */

@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
public class Application {

    public static void main(String[] args) {
	SpringApplication.run(Application.class, args);
    }

    @Bean
    public CacheManager cacheManager() {
	GuavaCacheManager cacheManager = new GuavaCacheManager("departments");
	return cacheManager;
    }

}

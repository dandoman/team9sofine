package com.kink.bootstrap;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.kink.dao.KinkDao;
import com.kink.logic.KinkLogic;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.kink"})
@EnableSwagger2
public class App {
	
	@Bean
	protected ServletContextListener listener() {
		return new ServletContextListener() {

			@Override
			public void contextInitialized(ServletContextEvent sce) {
				
			}

			@Override
			public void contextDestroyed(ServletContextEvent sce) {
				
			}

		};
	}
	
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
	        return new EmbeddedServletContainerCustomizer() {
	            @Override
	            public void customize(ConfigurableEmbeddedServletContainer container) {
	                container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
	            }
	        };
	}
	
	
	@Bean
	public KinkDao createKinkDao() {
		return new KinkDao();
	}
	
	@Bean
	public KinkLogic createKinkLogic() {
		return new KinkLogic();
	}
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
	}
}

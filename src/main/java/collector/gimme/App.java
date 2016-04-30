package collector.gimme;

import javax.servlet.Filter;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@SpringBootApplication
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

	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
	}
}

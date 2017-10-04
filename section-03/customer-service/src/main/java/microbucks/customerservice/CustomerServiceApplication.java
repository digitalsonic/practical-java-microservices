package microbucks.customerservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(CustomerServiceApplication.class).web(false).run(args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}

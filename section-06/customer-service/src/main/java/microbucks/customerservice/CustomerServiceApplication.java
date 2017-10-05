package microbucks.customerservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(CustomerServiceApplication.class).web(false).run(args);
	}
}

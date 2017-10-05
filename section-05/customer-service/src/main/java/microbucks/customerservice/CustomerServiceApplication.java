package microbucks.customerservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("/META-INF/spring/applicationContext.xml")
public class CustomerServiceApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(CustomerServiceApplication.class).web(false).run(args);
	}
}

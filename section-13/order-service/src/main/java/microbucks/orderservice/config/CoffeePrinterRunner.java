package microbucks.orderservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CoffeePrinterRunner implements CommandLineRunner {
    @Autowired
    private CoffeeProperties coffeeProperties;

    @Override
    public void run(String... args) throws Exception {
        log.info("my secret: {}", coffeeProperties.getSecrets());
        coffeeProperties.getPrice().forEach((name, price) -> log.info("{} -> {}", name, price));
    }
}

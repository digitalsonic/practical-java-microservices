package microbucks.orderservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Data
@ConfigurationProperties("coffee")
@RefreshScope
public class CoffeeProperties {
    private String secrets;
    private Map<String, String> price = new HashMap<>();
}

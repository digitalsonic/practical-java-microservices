package microbucks.oauthservice;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    public void configure(AuthorizationServerSecurityConfigurer securityConfigurer)
            throws Exception {
        securityConfigurer.checkTokenAccess("isAuthenticated()");
    }

    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
        builder.withClient("customer").secret("customer")
                .scopes("vip")
                .authorizedGrantTypes("client_credentials");
        builder.withClient("order").secret("order")
                .scopes("vip")
                .authorizedGrantTypes("client_credentials");
    }
}

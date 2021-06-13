package proposta.configs.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
                authorizeRequests
                        .antMatchers(HttpMethod.POST, "/api/proposal/**").hasAuthority("SCOPE_propostas-scope:write")
                        .antMatchers(HttpMethod.GET, "/api/proposal/**").hasAuthority("SCOPE_propostas-scope:read")
                        .antMatchers(HttpMethod.POST, "/api/biometry/**").hasAuthority("SCOPE_propostas-scope:write")
                        .antMatchers(HttpMethod.PUT, "/api/card/**").hasAuthority("SCOPE_propostas-scope:write")
                        .antMatchers(HttpMethod.POST, "/api/card/**").hasAuthority("SCOPE_propostas-scope:write")
                        .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                        .anyRequest().authenticated()
        ).csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }

}

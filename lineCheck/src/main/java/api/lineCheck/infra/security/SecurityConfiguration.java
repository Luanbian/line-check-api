package api.lineCheck.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "api/account").permitAll()
                        .requestMatchers(HttpMethod.POST, "api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/checkpoint/driver").hasAnyRole("DRIVER", "MANAGER")
                        .requestMatchers(HttpMethod.GET, "api/checkpoint/manager").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "api/checkpoint/driver").hasAnyRole("DRIVER", "MANAGER")
                        .requestMatchers(HttpMethod.POST, "api/checkpoint/line").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "api/checkpoint/line").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "api/logistic").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "api/manufacture").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "api/service").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "api/vehicle").hasRole("MANAGER")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}

package com.bankIndia.bankindia_secure.config;

import com.bankIndia.bankindia_secure.exceptionHandling.CustomAuthenticationEntryPoint;
import com.bankIndia.bankindia_secure.filter.CSrfFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.csrf.CsrfTokenRequestHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Collections;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();


        http.cors(cors -> cors.configurationSource(
                new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                }
        ));

//        http.sessionManagement(smc -> smc.invalidSessionUrl("/error").maximumSessions(1)
//                .maxSessionsPreventsLogin(true)
//
//        );

        http.sessionManagement(smc -> smc.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));
        http.securityContext(sc -> sc.requireExplicitSave(false));

        http.csrf(csrf ->
                csrf.ignoringRequestMatchers("/contact")
                        .csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)

                        .csrfTokenRepository( CookieCsrfTokenRepository.withHttpOnlyFalse()));
        http.addFilterAfter(new CSrfFilter() , BasicAuthenticationFilter.class);

     //   http.sessionManagement(smc -> smc.sessionFixation().none()); -> this will lead to session fixation attack
        //by default spring security uses sesionfixation.changeSessionId to fix session fixation attack

        http.authorizeHttpRequests((requests) -> {
            requests.requestMatchers("/myCards").hasAuthority("VIEWCARDS");
            requests.requestMatchers("/myAccount").hasAuthority("VIEWACCOUNT");
requests.requestMatchers(  "/user").authenticated();
requests.requestMatchers("/notices"  ,"/error" ,"/register" ,"/contact").permitAll();
        });

        http.formLogin(flc -> flc.defaultSuccessUrl("/getAccount"));
        http.httpBasic(hbc -> hbc.authenticationEntryPoint(new CustomAuthenticationEntryPoint())); //when we throw exception using this inside http basic then
        //it will throw exception  only during  login using basic authentication
        //http.exception( // we can throw this exception globally)

        return (SecurityFilterChain)http.build();
    }





    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }

}

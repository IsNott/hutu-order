package org.nott.security.configuration;

import org.nott.security.filter.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @author Nott
 * @date 2024-6-6
 */
@EnableWebSecurity
@Configuration
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Resource
    private JwtTokenFilter jwtAuthenticationTokenFilter;

    @Value("${spring.security.load:true}")
    private boolean load;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/doc.html",
                "/webjars/**",
                "/v2/api-docs",
                "/v2/api-docs-ext",
                "/v3/api-docs",
                "/v3/api-docs/**",
                "/swagger-resources/**",
                "/swagger-ui.html",
                "/swagger-ui/**",
                "/favicon.ico"
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().cacheControl();

        if (load) {
            http.csrf().disable()
                    .exceptionHandling()
                    .authenticationEntryPoint(
                            (request, response, authException) ->
                                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
                    )
                    .and()
                    .authorizeRequests()
                    .antMatchers(
                            JwtTokenFilter.PERMIT_ALL_PATTERNS
                    )
                    .permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .httpBasic().disable();

            http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        } else {
            http.csrf().disable()
                    .authorizeRequests()
                    .anyRequest().permitAll()
                    .and()
                    .httpBasic().disable();
        }
    }
}
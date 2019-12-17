package org.shanzhaozhen.dynamicadmin.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyUserDetailsService myUserDetailsService;

    private final MyAuthenticationEntryPoint myAuthenticationEntryPoint;

    private final MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter;

    private final MyJwtAuthenticationFilter myJwtAuthenticationFilter;

    private final MyFilterSecurityInterceptor myFilterSecurityInterceptor;

    public MySecurityConfig(MyUserDetailsService myUserDetailsService, MyAuthenticationEntryPoint myAuthenticationEntryPoint, MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter, MyJwtAuthenticationFilter myJwtAuthenticationFilter, MyFilterSecurityInterceptor myFilterSecurityInterceptor) {
        this.myUserDetailsService = myUserDetailsService;
        this.myAuthenticationEntryPoint = myAuthenticationEntryPoint;
        this.myUsernamePasswordAuthenticationFilter = myUsernamePasswordAuthenticationFilter;
        this.myJwtAuthenticationFilter = myJwtAuthenticationFilter;
        this.myFilterSecurityInterceptor = myFilterSecurityInterceptor;
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 配置允许跨域
     * @return
     */
    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "HEAD", "OPTION"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.addExposedHeader("Authorization");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * 定义认证规则
     * anyRequest().authenticated(): 其他地址的访问均需验证权限
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http
            .cors()
                .and()
            .csrf()
                .disable()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
            .authorizeRequests()
//                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/", "/login", "/register/**", "/swagger-resources/**", "/v2/api-docs/**", "/swagger-ui.html", "/druid/**", "/webjars/**", "/upload", "/test", "/files/**", "/error").permitAll()
                .anyRequest().authenticated()
                .and()
            .headers()
                .frameOptions().disable()       //允许iframe
                .and()
            .formLogin()
                .disable()
            .logout()
                .disable()
//            .exceptionHandling()
//                .authenticationEntryPoint(myAuthenticationEntryPoint)
//                .and()
            .addFilterBefore(myJwtAuthenticationFilter, BasicAuthenticationFilter.class)
            .addFilterBefore(myUsernamePasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .addFilterAfter(myFilterSecurityInterceptor, FilterSecurityInterceptor.class)
        ;

    }

    /**
     * 定义授权规则
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());

    }


    @Override
    public void configure(WebSecurity web) throws Exception {
    }

}

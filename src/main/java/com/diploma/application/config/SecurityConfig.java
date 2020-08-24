package com.diploma.application.config;

import com.diploma.application.filters.JwtRequestFilter;
import com.diploma.application.security.RestAuthenticationEntryPoint;
import com.diploma.application.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import com.diploma.application.security.oauth2.OAuth2AuthenticationFailureHandler;
import com.diploma.application.security.oauth2.OAuth2AuthenticationSuccessHandler;
import com.diploma.application.service.UserService;
import com.diploma.application.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private JwtRequestFilter jwtRequestFilter;

    private UserService userService;

    private AppProperties appProperties;

    public SecurityConfig(JwtRequestFilter jwtRequestFilter, UserService userService, AppProperties appProperties) {
        this.jwtRequestFilter = jwtRequestFilter;
        this.userService = userService;
        this.appProperties = appProperties;

        System.out.println(appProperties.getAuth().getTokenSecret());
        System.out.println(appProperties.getAuth().getTokenExpirationMsec());
        System.out.println(appProperties.getOauth2().getAuthorizedRedirectUris());


    }

    //    @Autowired
//    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @Autowired
    private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf()
                .disable()
                .formLogin()
                .disable()
                .httpBasic()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .and()
                .authorizeRequests()
                .antMatchers("/",
                        "/error",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js")
                .permitAll()
                .antMatchers("/auth/**", "/oauth2/**","login/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2Login()
                .authorizationEndpoint()
                .baseUri("/oauth2/authorize")
                .authorizationRequestRepository(cookieAuthorizationRequestRepository())
                .and()
                .redirectionEndpoint()
                .baseUri("/oauth2/callback/*")
                .and()
                .userInfoEndpoint()
                .userService(userService)
                .and()
                .successHandler(oAuth2AuthenticationSuccessHandler)
                .failureHandler(oAuth2AuthenticationFailureHandler)
        ;

        // Add our custom Token based authentication filter
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }


//    @Override
//    protected void configure(HttpSecurity http) throws Exception
//    {
//        http.csrf().disable()
//            .authorizeRequests()
//                .antMatchers("/auth","/","login.html","/login").permitAll()
//                .anyRequest().authenticated()
//    //                .and().oauth2Login()
////            .and()
////                .oauth2ResourceServer()
////                    .jwt()
////                .and().oauth2Login()
////                .loginPage("/auth/custom-login")
////                .authorizationEndpoint()
////                .baseUri("/oauth2/authorize")
////                .authorizationRequestRepository(customAuthorizationRequestRepository())
////                //and()
////               // .successHandler(customAuthenticationSuccessHandler)
////                .and()
////                .defaultSuccessUrl("/loginSuccess");
//        ;
//        http.sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//              //  .and()
//              //  .oauth2Login();
//    }

//    @Bean
//    public AuthorizationRequestRepository<OAuth2AuthorizationRequest> customAuthorizationRequestRepository() {
//        return new HttpSessionOAuth2AuthorizationRequestRepository();
//    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
        .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
//package ch.skunky.springdemo;
//
//import ch.skunky.springdemo.security.JwtAuthenticationFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.annotation.Resource;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Resource(name = "userService")
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    private JwtAuthenticationEntryPoint unauthorizedHandler;
//
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Autowired
//    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(encoder());
//    }
//
//    @Bean
//    public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
//        return new JwtAuthenticationFilter();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable().
//                authorizeRequests()
//                .antMatchers("/token/*").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http
//                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
//    }
//
//    @Bean
//    public BCryptPasswordEncoder encoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//}
//
//
//
//
////
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
////import org.springframework.security.core.userdetails.User;
////import org.springframework.security.core.userdetails.UserDetails;
////import org.springframework.security.core.userdetails.UserDetailsService;
////import org.springframework.security.provisioning.InMemoryUserDetailsManager;
////
/////**
//// * https://www.devglan.com/spring-security/angular-jwt-authentication
//// * https://www.baeldung.com/securing-a-restful-web-service-with-spring-security
//// * https://www.baeldung.com/spring-security-oauth-jwt
//// */
////@Configuration
////@EnableWebSecurity
////public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http
////                .authorizeRequests()
////                .antMatchers("/", "/home").permitAll()
////                .anyRequest().authenticated()
////                .and()
////                .formLogin()
////                .loginPage("/account/login")
////                .permitAll()
////                .and()
////                .logout()
////                .permitAll();
////    }
////
////    @Bean
////    @Override
////    public UserDetailsService userDetailsService() {
////        UserDetails user =
////                User.withDefaultPasswordEncoder()
////                        .username("user")
////                        .password("password")
////                        .roles("USER")
////                        .build();
////
////        return new InMemoryUserDetailsManager(user);
////    }
////}
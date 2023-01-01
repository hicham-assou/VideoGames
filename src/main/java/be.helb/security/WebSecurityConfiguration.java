package be.helb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
 private BCryptPasswordEncoder bCryptPasswordEncoder;
 private UserDetailsService userDetailsService;

 private static final String[] AUTH_WHITELIST = {
         "/h2-console/**",
         "/api/**",
         "/home",
         "/swagger-resources/**",
         "/configuration/ui",
         "/configuration/security",
         "/swagger-ui/**",
         "/v2/**",
         "/v3/**",
         "/login/**",
         "/signup/**",
         "/webjars/**"
 };



 public WebSecurityConfiguration(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
  this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  this.userDetailsService = userDetailsService;

 }


 protected void configure(HttpSecurity httpSecurity) throws Exception {
  httpSecurity.cors().and().csrf().disable().authorizeRequests()
          .antMatchers(AUTH_WHITELIST).permitAll()
          .antMatchers(HttpMethod.POST, "/signup").permitAll()
          .antMatchers(HttpMethod.GET, "/signup").permitAll()
          .anyRequest().authenticated()
          .and().addFilter(new AuthenticationFilter(authenticationManager()))
          .addFilter(new AuthorizationFilter(authenticationManager()))
          .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  //this will allow frames with same origin which is much more safe
  httpSecurity.headers().frameOptions().sameOrigin();
 }

 public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
  authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
 }


 @Bean
 CorsConfigurationSource corsConfigurationSource() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
    return source;
 }




}
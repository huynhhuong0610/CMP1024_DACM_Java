package com.example.DAMH.Security;


import com.example.DAMH.Service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Value("25")
    private int mailPort;

    @Value("sandbox.smtp.mailtrap.io")
    private String host;

    @Value("b956ff761b064b")
    private String username;

    @Value("f0148153421f95")
    private String password;

    @Bean
    protected BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }

    @Bean
    protected DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(mailPort);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        return mailSender;
    }



    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("filter");
        http.authorizeHttpRequests(requests -> requests
                .antMatchers("/css/**").permitAll()
                        .antMatchers("/js/**").permitAll()
                        .antMatchers("/fonts/**").permitAll()
                        .antMatchers("/register").permitAll()
                        .antMatchers("/forgot_password").permitAll()
                        .antMatchers("/reset_password").permitAll()
                        .antMatchers("/change_password").permitAll()
                        .antMatchers("/products/")
                        .hasAnyAuthority("ADMIN")
                        .antMatchers("/products/add").hasAnyAuthority("ADMIN")
                        .antMatchers("products/edit/**")
                        .hasAnyAuthority("ADMIN")
                        .antMatchers("/products/delete/**")
                        .hasAnyAuthority("ADMIN")

                .antMatchers("/sanphams")
                .hasAnyAuthority("USER","ADMIN")
               /* .antMatchers("/products/add").hasAnyAuthority("ADMIN")
                .antMatchers("products/edit/**")
                .hasAnyAuthority("ADMIN")
                .antMatchers("/products/delete/**")
                .hasAnyAuthority("ADMIN")*/

                        .antMatchers("/contacts")
                        .hasAnyAuthority("USER","ADMIN")

                        .antMatchers("/blogs")
                        .hasAnyAuthority("USER","ADMIN")

                        .antMatchers("/products")
                        .hasAnyAuthority("ADMIN")

                        .antMatchers("/categories")
                        .hasAnyAuthority("ADMIN")

                        .antMatchers("/users")
                        .hasAnyAuthority("ADMIN")

                        .antMatchers("/roles")
                        .hasAnyAuthority("ADMIN")

                        .antMatchers("/suppliers")
                        .hasAnyAuthority("ADMIN")
                        .anyRequest()

                .authenticated())
                .formLogin(login -> login.loginPage("/login").permitAll())
                .logout(logout -> logout.permitAll())
                .exceptionHandling(hangling -> hangling.accessDeniedPage("/403"));
        return http.build();
    }
}

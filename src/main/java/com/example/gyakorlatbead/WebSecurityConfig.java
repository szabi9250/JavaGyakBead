package com.example.gyakorlatbead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*http
                .authorizeHttpRequests(auth -> auth
                        //statikus erőforrások
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/sass/**", "/webfonts/**").anonymous()

                        //nyilvános oldalak
                        .requestMatchers ("/resources/**", "/", "/regisztral", "/regisztral_feldolgoz", "/kinalat", "/ar", "/kapcsolat", "/eredmeny").anonymous()
                        //jelszó teszt
                        .requestMatchers("/","/jelszoteszt").anonymous()
                        //hitelesítéshez között
                        .requestMatchers("/resources/**", "/", "/home").authenticated()
                        //admin
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/home")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .permitAll()
                );*/
  http
          .csrf(csrf -> csrf.disable())
          .authorizeHttpRequests(auth -> auth
                  .anyRequest().permitAll());
        return http.build();


    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
            throws Exception {
        return configuration.getAuthenticationManager();
    }
}

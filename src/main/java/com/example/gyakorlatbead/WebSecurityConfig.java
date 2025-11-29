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
        http
                .authorizeHttpRequests(auth -> auth
                        //statikus erőforrások
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/sass/**", "/webfonts/**").permitAll()
                        //nyilvános oldalak, bárki hozzájuk férhet
                        .requestMatchers("/resources/**", "/", "/regisztral", "/regisztral_feldolgoz", "/kinalat", "/ar", "/kapcsolat", "/eredmeny", "/diagram", "/crud", "/index", "/uj/**", "/modosit/**", "/torles/**", "/ment", "/kapcsolatk").permitAll()
                        //Loginhoz kötött
                        .requestMatchers("/uzenetek", "/home").authenticated()
                        //csak az admin láthatja, alap felhasználónév: admin@gmail.com, jelszó: jelszo1
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        //Loginhoz ne férjen hozzá már bejelentkezett felhasználó, csak anonymous, alap felhasználónév: user@gmail.com, jelszó: jelszo2
                        .requestMatchers("/login").anonymous()
                        //Restful elérhető mindenkinek
                        .requestMatchers("/restful/**").permitAll()
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/home")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .permitAll()
                );
        return http.build();
    }
  /*http
          .csrf(csrf -> csrf.disable())
          .authorizeHttpRequests(auth -> auth
                  .anyRequest().permitAll());
                          return http.build();
   */

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
            throws Exception {
        return configuration.getAuthenticationManager();
    }
}

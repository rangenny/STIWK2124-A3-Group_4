package com.stiw2124.assignment1_group4.config;

import com.stiw2124.assignment1_group4.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 1. Enable CORS configuration defined below
            .cors(Customizer.withDefaults())
            // 2. Disable CSRF protection since this is a stateless API development server
            .csrf(csrf -> csrf.disable())
            // 3. Define authorization rules based on your security criteria
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll() // Permits authentication routes
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Add this right below your auth permitAll
                .requestMatchers(HttpMethod.GET, "/api/books/**", "/api/**").permitAll() // Public read access
                .requestMatchers(HttpMethod.POST, "/api/books/**", "/api/**").authenticated() // Secure writes
                .requestMatchers(HttpMethod.PUT, "/api/books/**", "/api/**").authenticated()  // Secure updates
                .requestMatchers(HttpMethod.DELETE, "/api/books/**", "/api/**").authenticated() // Secure deletes
                .anyRequest().authenticated()
            )
            // 4. Implement Basic Authentication for the secure write endpoints
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    // Used to hash passwords and to verify them on login
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            // 1. Find the user in your MySQL database
            com.stiw2124.assignment1_group4.model.User appUser = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

            // 2. Pass the database credentials to Spring Security
            return User.builder()
                    .username(appUser.getUsername())
                    .password(appUser.getPassword())
                    .roles("USER") // Spring Security requires a default role
                    .build();
        };
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200")); // Allows your Angular frontend
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
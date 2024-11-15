package com.keepcoding.purchasing_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final AuthenticationProvider authenticationProvider;
	
	public SecurityConfig(
			AuthenticationProvider authenticationProvider
	    ) {
	        this.authenticationProvider = authenticationProvider;
	    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
            		
            		.requestMatchers("/public/**", "/css/**", "/js/**", "/images/**", "/fonts/**", "/main/**", "/scss/**").permitAll()          
            		.requestMatchers("/adm_menu/**").hasRole("ADMIN")
            		.requestMatchers("/user_menu/**").hasAnyRole("USER", "ADMIN")            		
            		.anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")         // Ruta de la página de inicio de sesión personalizada
                .loginProcessingUrl("/login") // Ruta a la que envía el formulario para procesar el login
                .defaultSuccessUrl("/default") 
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout") // Ruta después del cierre de sesión
                .permitAll()
            )        
        	.csrf(AbstractHttpConfigurer::disable); // Deshabilitar CSRF para simplicidad, habilitar según tus necesidades 
        
        	http.authenticationProvider(authenticationProvider);

        return http.build();
    }
    
    @Bean 
    public PasswordEncoder passwordEncoder() { 
    	return new BCryptPasswordEncoder(); 
    	
    }
}

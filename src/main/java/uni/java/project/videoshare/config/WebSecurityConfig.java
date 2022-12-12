package uni.java.project.videoshare.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {	
		http.headers().frameOptions().disable();
		http.csrf().disable();
		http.cors();
		return http.build();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setMaxAge((long) 3600);
		configuration.addAllowedHeader("Content-Type");
		configuration.addAllowedHeader("Access-Control-Allow-Origin");
		configuration.addAllowedHeader("Access-Control-Allow-Headers");
		configuration.addAllowedHeader("Authorization");
		configuration.addAllowedHeader("requestId");
		configuration.addAllowedHeader("Correlation-Id");
		configuration.addAllowedHeader("X-Requested-With");
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
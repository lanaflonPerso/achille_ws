//package achille.config;
//
//import java.util.Arrays;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//	
////	@Override
////	protected void configure(HttpSecurity http) throws Exception {
////		//On configure les webServices qui nécessitent une authentification
////		http.cors().configurationSource(corsConfigurationSource());
////	};
////
////	
////	   @Bean
////	   CorsConfigurationSource corsConfigurationSource() {
////		   System.out.println("toto_sec");
////		    CorsConfiguration configuration = new CorsConfiguration();
////		    configuration.setAllowedOrigins(Arrays.asList("*"));
////		    //configuration.setAllowCredentials(true);
////		    //configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Headers","Access-Control-Allow-Origin","Access-Control-Request-Method", "Access-Control-Request-Headers","Origin","Cache-Control", "Content-Type", "Authorization"));
////		    configuration.setAllowedMethods(Arrays.asList("DELETE", "GET", "POST", "PATCH", "PUT"));
////		    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////		    source.registerCorsConfiguration("/**", configuration);
////		    return source;
////		}
//
//}

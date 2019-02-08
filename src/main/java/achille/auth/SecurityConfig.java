package achille.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import achille.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserService userDetailsService;
	@Autowired
	AuthenticationProvider provider;

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//On configure le service
		auth.userDetailsService(userDetailsService); }

	@Bean
	public AuthenticationProvider getProvider() {
		//On ocnfigure le provider
		// On peut passer des utilisateurs en dur, ou bien un AuthProvider qui va se connecter à une base de données
		AppAuthProvider provider = new AppAuthProvider();
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
	
	//On peut ajouter un bean password encoder

	protected void configure(HttpSecurity http) throws Exception {

		//On configure les webServices qui nécessitent une authentification
		http.csrf()
		.disable()
		.authenticationProvider(provider)
		.authorizeRequests()
		//.antMatchers("/consultants").permitAll()
		.antMatchers(HttpMethod.OPTIONS, "**").permitAll()
		.antMatchers("/consultants").hasAuthority("ADMIN")
		.antMatchers("/consultant/auth").hasAuthority("CONSULTANT")
		.antMatchers(HttpMethod.POST,"/consultant/*").hasAuthority("ADMIN")
		.antMatchers(HttpMethod.GET,"/consultant/*").hasAuthority("ADMIN")
		.antMatchers(HttpMethod.POST,"/fiche").hasAnyAuthority("CONSULTANT","ADMIN")
		//.antMatchers(HttpMethod.POST,"/consultant").hasAuthority("CONSULTANT")
		.anyRequest().permitAll()
		.and()
		.httpBasic();
		//login mot de passe
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

}

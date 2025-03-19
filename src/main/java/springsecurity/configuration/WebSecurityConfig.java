package springsecurity.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import springsecurity.jwt.JwtAuthenticationEntryPoint;
import springsecurity.jwt.JwtRequestFilter;
import springsecurity.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	private final UserService userService;
	private final JwtRequestFilter jwtRequestFilter;
	private final PasswordEncoder passwordEncoder;
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    public WebSecurityConfig(UserService userService, JwtRequestFilter jwtRequestFilter, PasswordEncoder passwordEncoder, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.userService = userService;
        this.jwtRequestFilter = jwtRequestFilter;
        this.passwordEncoder = passwordEncoder;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

	/*
	* configure AuthenticationManager so that it knows from where to load
	* user for matching credentials
	* Use BCryptPasswordEncoder
	*/
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userService);
		authProvider.setPasswordEncoder(passwordEncoder);
		return authProvider;
	}

	/* Using at AuthenticationController */
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
				.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeHttpRequests((requests) -> requests
						.requestMatchers("/","/auth/login", "/swagger-ui/**",
								"/v3/api-docs/**",
								"/swagger-ui.html", "/auth/**").permitAll()
						.requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
						.requestMatchers("/welcome/**").authenticated()
						.anyRequest().authenticated());

		http.authenticationProvider(authenticationProvider());
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	/*
	* addFilterBefore it performance on filter first
	* get token, go through to validate
	*/
}
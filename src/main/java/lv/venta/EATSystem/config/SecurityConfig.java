package lv.venta.EATSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lv.venta.EATSystem.enums.SecurityRole;
import lv.venta.EATSystem.repos.IMyAuthorityRepo;
import lv.venta.EATSystem.repos.IMyUserRepo;
import lv.venta.EATSystem.services.impl.MyUserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final MyUserDetailsServiceImpl userDetailsService;
	//private final JwtAuthFilter jwtAuthFilter;
	private final IMyUserRepo userRepo;
	private final IMyAuthorityRepo authorityRepo;

	public SecurityConfig(MyUserDetailsServiceImpl userDetailsService, IMyUserRepo userRepo, IMyAuthorityRepo authorityRepo){
		this.userDetailsService = userDetailsService;
		this.userRepo = userRepo;
		this.authorityRepo = authorityRepo;
	}


	public MyUserDetailsServiceImpl getDetailsService() {
	return new MyUserDetailsServiceImpl(userRepo, authorityRepo);
	}

	public SecurityFilterChain configurePermissionToEndpoints(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
		return http
		.cors(AbstractHttpConfigurer::disable)
		.csrf(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests(auth->
		auth.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
		.requestMatchers("/admin/**").hasAuthority(SecurityRole.ADMIN.toString())
		.requestMatchers("/**/admin/**").hasAuthority(SecurityRole.ADMIN.toString())
		.requestMatchers("/departmentmanager/**").hasAnyAuthority(SecurityRole.ADMIN.toString(), SecurityRole.DEPARTMENT_MANAGER.toString())
		.requestMatchers("/**/departmentmanager/**").hasAnyAuthority(SecurityRole.ADMIN.toString(), SecurityRole.ADMIN.toString())
		.requestMatchers("/projectmanager/**").hasAnyAuthority(SecurityRole.ADMIN.toString(), SecurityRole.PROJECT_MANAGER.toString())
		.requestMatchers("/**/projectmanager/**").hasAnyAuthority(SecurityRole.ADMIN.toString(), SecurityRole.PROJECT_MANAGER.toString())
		.anyRequest().permitAll()
		)
		.authenticationManager(authenticationManager)
		.userDetailsService(userDetailsService)
		.addFilterBefore(null, UsernamePasswordAuthenticationFilter.class)
		.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
	return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
	return config.getAuthenticationManager();
	}

}

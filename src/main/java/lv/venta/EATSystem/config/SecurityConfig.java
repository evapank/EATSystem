package lv.venta.EATSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
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
	private final IMyUserRepo userRepo;
	private final IMyAuthorityRepo authorityRepo;

	public SecurityConfig(MyUserDetailsServiceImpl userDetailsService, IMyUserRepo userRepo, IMyAuthorityRepo authorityRepo){
		this.userDetailsService = userDetailsService;
		this.userRepo = userRepo;
		this.authorityRepo = authorityRepo;
		//this.jwToken = jwtToken;
	}


	public MyUserDetailsServiceImpl getDetailsService() {
	return new MyUserDetailsServiceImpl(userRepo, authorityRepo);
	}

	@Bean
	public SecurityFilterChain configurePermissionToEndpoints(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
		//return http
		//.cors(AbstractHttpConfigurer::disable)
		//.csrf(AbstractHttpConfigurer::disable)
		http.authorizeHttpRequests(auth->
		auth.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
		.requestMatchers("/h2-console/**").permitAll()
		.requestMatchers("/auth/login").permitAll()
		.requestMatchers("/auth/signup").permitAll()
		.requestMatchers("/auth/user/employee/**").permitAll()//.hasAuthority(SecurityRole.EMPLOYEE.toString())
		.requestMatchers("/auth/user/employee/projects/**").hasAuthority(SecurityRole.EMPLOYEE.toString())
		.requestMatchers("/auth/user/employee/orders/**").hasAuthority(SecurityRole.EMPLOYEE.toString())
		.requestMatchers("/auth/user/employee/newStatus/**").permitAll()//.hasAuthority(SecurityRole.EMPLOYEE.toString())
		.requestMatchers("/dashboard").permitAll()
		.requestMatchers("/department/**").hasAnyAuthority(SecurityRole.ADMIN.toString(), SecurityRole.DEPARTMENT_MANAGER.toString())
		.requestMatchers("/departmentmanager/**").hasAuthority(SecurityRole.ADMIN.toString())
		.requestMatchers("/projectmanager/**").hasAuthority(SecurityRole.ADMIN.toString())
		.requestMatchers("/employee/**").hasAuthority(SecurityRole.ADMIN.toString())
		.requestMatchers("/employeeorderstatus/**").hasAuthority(SecurityRole.ADMIN.toString())
		.requestMatchers("/employeestatus/**").hasAuthority(SecurityRole.ADMIN.toString())
		.requestMatchers("/order/**").hasAuthority(SecurityRole.ADMIN.toString())
		.requestMatchers("/project/**").hasAnyAuthority(SecurityRole.ADMIN.toString(), SecurityRole.PROJECT_MANAGER.toString())
		)
		.authenticationManager(authenticationManager)
		.userDetailsService(userDetailsService)
		.addFilterBefore(new JwtTokenValidator(), UsernamePasswordAuthenticationFilter.class)
		//.addFilterBefore(null, UsernamePasswordAuthenticationFilter.class)
		.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		//http.csrf(auth->auth.ignoringRequestMatchers("/h2-console/**"));
		http.csrf(csrf -> csrf.disable());
		http.cors(cors -> {}); //	http.cors(auth->auth.disable());
		http.headers(frame->frame.frameOptions(option->option.disable()));
		return http.build();
		//.build();

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

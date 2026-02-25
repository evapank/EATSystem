package lv.venta.EATSystem.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lv.venta.EATSystem.config.JwtProvider;
import lv.venta.EATSystem.enums.SecurityRole;
import lv.venta.EATSystem.models.MyAuthority;
import lv.venta.EATSystem.models.MyUser;
import lv.venta.EATSystem.repos.IMyUserRepo;
import lv.venta.EATSystem.response.AuthResponse;
import lv.venta.EATSystem.services.IMyUserService;
import lv.venta.EATSystem.services.impl.MyUserDetailsServiceImpl;
import lv.venta.EATSystem.services.impl.MyUserServiceImpl;

@RestController
@RequestMapping("/auth")
public class MyUserController {
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired 
	private MyUserDetailsServiceImpl customUserDetails;
	
	@Autowired
	private IMyUserService myUserService;
	
	@PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody MyUser user) throws Exception  {
        String email = user.getEmployee().getEmail();
        String password = user.getPassword();
        String name = user.getEmployee().getName();
        String surname = user.getEmployee().getSurname();
        MyAuthority role =  user.getAuthority();

        MyUser isEmailExist = myUserService.findUserByEmail(email);
        if (isEmailExist != null) {
            throw new Exception("Email Is Already Used With Another Account");

        }
        MyUser createdUser = new MyUser();
        createdUser.getEmployee().setEmail(email);
        createdUser.getEmployee().setName(name);
        createdUser.getEmployee().setSurname(surname);
        createdUser.setAuthority(role);
        createdUser.setPassword(passwordEncoder.encode(password));
        
        myUserService.saveUser(createdUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(email,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = JwtProvider.generateToken(authentication);


        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Register Success");
        authResponse.setStatus(true);
        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);

    }





    @PostMapping("/login")
    public ResponseEntity<AuthResponse> signin(@RequestBody MyUser loginRequest) {
        String username = loginRequest.getEmployee().getEmail();
        String password = loginRequest.getPassword();

        System.out.println(username+"-------"+password);

        Authentication authentication = authenticate(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = JwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();

        authResponse.setMessage("Login success");
        authResponse.setJwt(token);
        authResponse.setStatus(true);

        return new ResponseEntity<>(authResponse,HttpStatus.OK);
    }


    private Authentication authenticate(String username, String password) {

        System.out.println(username+"---++----"+password);

        UserDetails userDetails = customUserDetails.loadUserByUsername(username);

        System.out.println("Sig in in user details"+ userDetails);

        if(userDetails == null) {
            System.out.println("Sign in details - null" + userDetails);

            throw new BadCredentialsException("Invalid username and password");
        }  else if(!passwordEncoder.matches(password,userDetails.getPassword())) {
            System.out.println("Sign in userDetails - password mismatch"+userDetails);

            throw new BadCredentialsException("Invalid password");

        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

    }


}

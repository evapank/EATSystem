package lv.venta.EATSystem.services.impl;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import lv.venta.EATSystem.config.MyUserDetails;
import lv.venta.EATSystem.models.MyAuthority;
import lv.venta.EATSystem.models.MyUser;
import lv.venta.EATSystem.repos.IMyAuthorityRepo;
import lv.venta.EATSystem.repos.IMyUserRepo;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService, UserDetailsManager{
	
	private IMyUserRepo userRepo;
	private IMyAuthorityRepo authorityRepo;
	
	public MyUserDetailsServiceImpl(IMyUserRepo userRepo, IMyAuthorityRepo authorityRepo) {
		this.userRepo = userRepo;
		this.authorityRepo = authorityRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MyUser result = userRepo.findByUsername(username);
		
		if(result==null) {
			throw new UsernameNotFoundException(username + "not found");
		}
		return new MyUserDetails(result);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void createUser(UserDetails user) {
		if(!userExists(user.getUsername())) {
			MyUser result = new MyUser();
			result.setUsername(user.getUsername());
			result.setPassword(user.getPassword());
			ArrayList<MyAuthority> authorities = (ArrayList<MyAuthority>) user.getAuthorities();
			for(MyAuthority a: authorities) {
				result.setAuthority(a);
				authorityRepo.save(a);
			}
			userRepo.save(result);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateUser(UserDetails user) {
		if(userExists(user.getUsername())) {
			MyUser result = new MyUser();
			result.setUsername(user.getUsername());
			result.setPassword(user.getPassword());
			ArrayList<MyAuthority> authorities = (ArrayList<MyAuthority>) user.getAuthorities();
			for(MyAuthority a: authorities) {
				result.setAuthority(a);
				authorityRepo.save(a);
			}
			userRepo.save(result);
		}
	}

	@Override
	public void deleteUser(String username) {
		if(userExists(username)) {
			MyUser user = userRepo.findByUsername(username);
			userRepo.delete(user);
		}
		
	}

	@Override
	public void changePassword(String oldPassword, String newPassword) {
			oldPassword = newPassword;
		
	}

	@Override
	public boolean userExists(String username) {
		if(userRepo.existsByUsername(username)) {
			return true;
		}
		return false;
	}

}

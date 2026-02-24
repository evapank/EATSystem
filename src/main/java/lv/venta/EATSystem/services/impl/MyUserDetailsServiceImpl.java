package lv.venta.EATSystem.services.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import lv.venta.EATSystem.config.MyUserDetails;
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

	@Override
	public void createUser(UserDetails user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(UserDetails user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changePassword(String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean userExists(String username) {
		// TODO Auto-generated method stub
		return false;
	}

}

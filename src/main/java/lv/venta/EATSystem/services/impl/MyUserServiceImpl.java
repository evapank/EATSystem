package lv.venta.EATSystem.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lv.venta.EATSystem.models.MyUser;
import lv.venta.EATSystem.repos.IMyAuthorityRepo;
import lv.venta.EATSystem.repos.IMyUserRepo;
import lv.venta.EATSystem.services.IMyUserService;

@Service
public class MyUserServiceImpl implements IMyUserService{

	@Autowired
	IMyUserRepo userRepo;
	
	@Autowired
	IMyAuthorityRepo authorityRepo;
	
	@Override
	public ArrayList<MyUser> getAllUsers() {
		ArrayList<MyUser> result = (ArrayList<MyUser>) userRepo.findAll();
		return result;
	}

	@Override
	public MyUser findUserProfileByJwt(String jwt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyUser findUserByEmail(String email) {
		MyUser result = userRepo.findByEmployeeEmail(email);
		return result;
	}

	@Override
	public MyUser findUserById(int userId) {
		MyUser result = null;
		if (userRepo.existsByIdMyUser(userId)) {
			result = userRepo.findByIdMyUser(userId);
		}
		return result;
	}

	@Override
	public void saveUser(MyUser user) {
		if(!userRepo.existsByUsername(user.getUsername())) {
			userRepo.save(user);
		}
		
	}
	
}


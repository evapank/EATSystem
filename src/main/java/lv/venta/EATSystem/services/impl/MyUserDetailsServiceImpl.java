package lv.venta.EATSystem.services.impl;

import org.springframework.stereotype.Service;

import lv.venta.EATSystem.repos.IMyAuthorityRepo;
import lv.venta.EATSystem.repos.IMyUserRepo;

@Service
public class MyUserDetailsServiceImpl {
	
	IMyUserRepo userRepo;
	
	IMyAuthorityRepo authorityRepo;
	
	public MyUserDetailsServiceImpl(IMyUserRepo userRepo, IMyAuthorityRepo authorityRepo) {
		this.userRepo = userRepo;
		this.authorityRepo = authorityRepo;
	}

}

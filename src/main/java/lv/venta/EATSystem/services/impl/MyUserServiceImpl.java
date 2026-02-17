package lv.venta.EATSystem.services.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import lv.venta.EATSystem.models.MyUser;
import lv.venta.EATSystem.services.IMyUserService;

@Service
public class MyUserServiceImpl implements IMyUserService{

	@Override
	public ArrayList<MyUser> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyUser findUserProfileByJwt(String jwt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyUser findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyUser findUserById(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}

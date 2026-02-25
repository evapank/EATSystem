package lv.venta.EATSystem.services;

import java.util.ArrayList;

import lv.venta.EATSystem.models.MyUser;

public interface IMyUserService {
	
	public ArrayList<MyUser> getAllUsers();
    
    public MyUser findUserProfileByJwt(String jwt);
    
    public MyUser findUserByEmail(String email);
    
    public MyUser findUserById(int userId);
    
    public void saveUser(MyUser user);

}

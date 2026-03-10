package lv.venta.EATSystem.services;

import java.util.ArrayList;

import lv.venta.EATSystem.models.MyUser;
import lv.venta.EATSystem.models.Order;
import lv.venta.EATSystem.models.Project;

public interface IMyUserService {
	
	public ArrayList<MyUser> getAllUsers();
    
    public MyUser findUserProfileByJwt(String jwt);
    
    public MyUser findUserByEmail(String email);
    
    public MyUser findUserById(int userId);
    
    public void saveUser(MyUser user);
    
    public ArrayList<Project> getAllEmployeeProjectsById (int userId);
    
    public ArrayList<Order> getAllEmployeeCurrentOrdersById (int userId);

}

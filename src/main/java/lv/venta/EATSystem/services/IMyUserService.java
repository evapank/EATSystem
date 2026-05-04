package lv.venta.EATSystem.services;

import java.util.ArrayList;

import lv.venta.EATSystem.models.EmployeeStatus;
import lv.venta.EATSystem.models.MyAuthority;
import lv.venta.EATSystem.models.MyUser;
import lv.venta.EATSystem.models.Order;
import lv.venta.EATSystem.models.Project;

public interface IMyUserService {
	
	public abstract ArrayList<MyUser> getAllUsers();
    
    public abstract MyUser findUserByEmail(String email);
    
    public abstract MyUser findUserById(int userId);
    
    public abstract void saveUser(MyUser user);
    
    public abstract ArrayList<Project> getAllEmployeeProjectsById (int userId);
    
    public abstract ArrayList<Order> getAllEmployeeCurrentOrdersById (int userId);
    
    public abstract EmployeeStatus insertNewEmployeeStatusbyEmployee(int userId, EmployeeStatus empSt);
    
    public abstract MyAuthority getAuthorityForUser(String username);
    
    public abstract MyUser getUserByUsername(String username);

}

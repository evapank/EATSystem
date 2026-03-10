package lv.venta.EATSystem.services;

import java.util.ArrayList;

import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.models.EmployeeOrderStatus;
import lv.venta.EATSystem.models.EmployeeStatus;
import lv.venta.EATSystem.models.MyUser;
import lv.venta.EATSystem.models.Order;
import lv.venta.EATSystem.models.Project;

public interface IMyUserService {
	
	public abstract ArrayList<MyUser> getAllUsers();
    
    public abstract MyUser findUserProfileByJwt(String jwt);
    
    public abstract MyUser findUserByEmail(String email);
    
    public abstract MyUser findUserById(int userId);
    
    public abstract void saveUser(MyUser user);
    
    public abstract ArrayList<Project> getAllEmployeeProjectsById (int userId);
    
    public abstract ArrayList<Order> getAllEmployeeCurrentOrdersById (int userId);
    
    public abstract EmployeeStatus insertNewEmployeeStatusbyEmployee(int userId, EmployeeStatus empSt);

}

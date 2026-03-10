package lv.venta.EATSystem.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.models.EmployeeOrderStatus;
import lv.venta.EATSystem.models.EmployeeStatus;
import lv.venta.EATSystem.models.MyUser;
import lv.venta.EATSystem.models.Order;
import lv.venta.EATSystem.models.Project;
import lv.venta.EATSystem.repos.IEmployeeStatusRepo;
import lv.venta.EATSystem.repos.IMyAuthorityRepo;
import lv.venta.EATSystem.repos.IMyUserRepo;
import lv.venta.EATSystem.repos.IOrderRepo;
import lv.venta.EATSystem.repos.IProjectRepo;
import lv.venta.EATSystem.services.IMyUserService;

@Service
public class MyUserServiceImpl implements IMyUserService{

	@Autowired
	IMyUserRepo userRepo;
	
	@Autowired
	IMyAuthorityRepo authorityRepo;
	
	@Autowired
	IProjectRepo projectRepo;
	
	@Autowired
	IOrderRepo orderRepo;
	
	@Autowired
	IEmployeeStatusRepo empStRepo;
	
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

	@Override
	public ArrayList<Project> getAllEmployeeProjectsById(int userId) {
		ArrayList<Project> projects = new ArrayList<>();
		if (userRepo.existsByIdMyUser(userId)) {
			Employee employee = userRepo.findByIdMyUser(userId).getEmployee();
			projects = projectRepo.findByEmployeesIdEmployee(employee.getIdEmployee());
		}
		return projects;
	}

	@Override
	public ArrayList<Order> getAllEmployeeCurrentOrdersById(int userId) {
		ArrayList<Order> orders = new ArrayList<>();
		LocalDateTime current = LocalDateTime.now();
		if(userRepo.existsByIdMyUser(userId)) {
			Employee employee = userRepo.findByIdMyUser(userId).getEmployee();
			ArrayList<Order> temp = orderRepo.findAllByEmployeeOrderStatusEmployee(employee);
			for (Order o: temp) {
				if(o.getDateTimeEnd().isAfter(current)) {
					orders.add(o);
				}
			}
		}
		return orders;
	}

	@Override
	public EmployeeStatus insertNewEmployeeStatusbyEmployee(int userId, EmployeeStatus empSt) {
		EmployeeStatus result = new EmployeeStatus();
		Employee employee = userRepo.findByIdMyUser(userId).getEmployee();
		if(!empStRepo.existsById(empSt.getIdEmployeeStatus())) {
			result.setEmployee(employee);
			result.setGeneralStatus(empSt.getGeneralStatus());
			result.setDateTimeStart(empSt.getDateTimeStart());
			result.setDateTimeEnd(empSt.getDateTimeEnd());
			empStRepo.save(result);
		}
		return result;
	}
	
}


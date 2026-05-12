package lv.venta.EATSystem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lv.venta.EATSystem.enums.GeneralStatus;
import lv.venta.EATSystem.enums.OrderStatus;
import lv.venta.EATSystem.enums.SecurityRole;
import lv.venta.EATSystem.models.Department;
import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.models.EmployeeOrderStatus;
import lv.venta.EATSystem.models.EmployeeStatus;
import lv.venta.EATSystem.models.Meeting;
import lv.venta.EATSystem.models.MyAuthority;
import lv.venta.EATSystem.models.MyUser;
import lv.venta.EATSystem.models.Order;
import lv.venta.EATSystem.models.Project;
import lv.venta.EATSystem.repos.IDepartmentRepo;
import lv.venta.EATSystem.repos.IEmployeeOrderStatusRepo;
import lv.venta.EATSystem.repos.IEmployeeRepo;
import lv.venta.EATSystem.repos.IEmployeeStatusRepo;
import lv.venta.EATSystem.repos.IMeetingRepo;
import lv.venta.EATSystem.repos.IMyAuthorityRepo;
import lv.venta.EATSystem.repos.IMyUserRepo;
import lv.venta.EATSystem.repos.IOrderRepo;
import lv.venta.EATSystem.repos.IProjectRepo;
import lv.venta.EATSystem.services.IEmailService;


@SpringBootApplication
public class EatSystem1Application {
	
	public static void main(String[] args) {
		SpringApplication.run(EatSystem1Application.class, args);
	}

	@Bean
	public CommandLineRunner runner(IDepartmentRepo departmentRepo,
			IEmployeeOrderStatusRepo employeeOrderStatusRepo, IEmployeeRepo employeeRepo,
			IEmployeeStatusRepo employeeStatusRepo, IOrderRepo orderRepo, IProjectRepo projectRepo,
			IMyUserRepo userRepo, IMyAuthorityRepo authorityRepo, IMeetingRepo meetingRepo) {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				
				Department dep1 = new Department("Accounting");
				Department dep2 = new Department("Software engineering");
				departmentRepo.save(dep1);
				departmentRepo.save(dep2);
				
				Employee emp1 = new Employee ("John", "Smith", "Software engineer", dep2, "jsmith@gmail.com", true);
				Employee emp2 = new Employee ("Anne", "Lee", "Accounting", dep1, "annelee123@gmail.com", false);
				employeeRepo.save(emp1);
				employeeRepo.save(emp2);
				
				dep1.addEmployee(emp2);
				dep2.addEmployee(emp1);
				departmentRepo.save(dep1);
				departmentRepo.save(dep2);
				
				MyAuthority EMPLOYEE = new MyAuthority(SecurityRole.EMPLOYEE);
				MyAuthority ADMIN = new MyAuthority(SecurityRole.ADMIN);
				authorityRepo.save(EMPLOYEE);
				authorityRepo.save(ADMIN);
				
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				
				MyUser u1 = new MyUser("admin", encoder.encode("admin"), ADMIN, emp1);
				MyUser u2 = new MyUser("employee", encoder.encode("employee"), EMPLOYEE, emp2);
				userRepo.save(u1);
				userRepo.save(u2);
				authorityRepo.save(ADMIN);
				authorityRepo.save(EMPLOYEE);
				
				
				EmployeeStatus empSt1 = new EmployeeStatus(emp1, GeneralStatus.Online,
										LocalDateTime.of(LocalDate.of(2025, 11, 4), LocalTime.of(13, 0)),
										LocalDateTime.of(LocalDate.of(2025, 11, 4), LocalTime.of(16, 30)));
				EmployeeStatus empSt2 = new EmployeeStatus(emp1, GeneralStatus.InPerson,
						LocalDateTime.of(LocalDate.of(2025, 11, 5), LocalTime.of(8, 0)),
						LocalDateTime.of(LocalDate.of(2025, 11, 5), LocalTime.of(17, 0)));
				employeeStatusRepo.save(empSt1);
				employeeStatusRepo.save(empSt2);
				
				Order ord1 = new Order(100023, null, LocalDate.of(2025, 10, 28),
						LocalDateTime.of(LocalDate.of(2025, 11, 3), LocalTime.of(8, 0)),
						LocalDateTime.of(LocalDate.of(2025, 11, 7), LocalTime.of(17, 0)),
						OrderStatus.Vacation, null);
				Order ord2 = new Order(200532, null, LocalDate.of(2025, 10, 20),
						LocalDateTime.of(LocalDate.of(2025, 1, 12), LocalTime.of(9, 0)),
						LocalDateTime.of(LocalDate.of(2025, 5, 12), LocalTime.of(12, 0)),
						OrderStatus.BusinessTrip, null);
				orderRepo.save(ord1);
				orderRepo.save(ord2);
				
				EmployeeOrderStatus empOrdSt1 = new EmployeeOrderStatus(emp1, GeneralStatus.Online);
				empOrdSt1.addOrder(ord1);
				employeeOrderStatusRepo.save(empOrdSt1);
				
				ord1.setEmployeeOrderStatus(empOrdSt1);
				orderRepo.save(ord1);
				
				Project proj1 = new Project(10001, "Mobile app development",LocalDate.of(2025, 1, 9),
						LocalDate.of(2026, 1, 9), emp1);
				Project proj2 = new Project(20006, "Social media website development",LocalDate.of(2026, 2, 9),
						LocalDate.of(2028, 1, 2), emp1);
				projectRepo.save(proj1);
				projectRepo.save(proj2);
				
				ord2.setProject(proj2);
				orderRepo.save(ord2);
				
				proj1.addEmployee(emp1);
				proj2.addEmployee(emp1);
				proj2.addEmployee(emp2);
				projectRepo.save(proj1);
				projectRepo.save(proj2);
				
				emp1.addProjects(proj1);
				emp1.addProjects(proj2);
				emp2.addProjects(proj2);
				employeeRepo.save(emp1);
				employeeRepo.save(emp2);
				
				Meeting meet1 = new Meeting(LocalDateTime.of(2026, Month.APRIL, 29, 11, 30, 00),
											LocalDateTime.of(2026, Month.APRIL, 29, 12, 00, 00), GeneralStatus.InPerson);
				meetingRepo.save(meet1);
				
				meet1.addEmployee(emp2);
				meetingRepo.save(meet1);
				employeeRepo.save(emp2);
				
				//emailService.sendEmail("s21pankeva@venta.lv", LocalDateTime.of( 2026, Month.APRIL, 24, 14, 30, 00),
				//		 LocalDateTime.of( 2026, Month.APRIL, 24, 15, 30, 00));

			}

		};
	}
}

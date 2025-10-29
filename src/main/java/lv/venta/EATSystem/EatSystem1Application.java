package lv.venta.EATSystem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lv.venta.EATSystem.enums.GeneralStatus;
import lv.venta.EATSystem.enums.OrderStatus;
import lv.venta.EATSystem.models.Day;
import lv.venta.EATSystem.models.Department;
import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.models.EmployeeOrderStatus;
import lv.venta.EATSystem.models.EmployeeStatus;
import lv.venta.EATSystem.models.Order;
import lv.venta.EATSystem.models.Project;
import lv.venta.EATSystem.repos.IDayRepo;
import lv.venta.EATSystem.repos.IDepartmentRepo;
import lv.venta.EATSystem.repos.IEmployeeOrderStatusRepo;
import lv.venta.EATSystem.repos.IEmployeeRepo;
import lv.venta.EATSystem.repos.IEmployeeStatusRepo;
import lv.venta.EATSystem.repos.IOrderRepo;
import lv.venta.EATSystem.repos.IProjectRepo;


@SpringBootApplication
public class EatSystem1Application {
	
	public static void main(String[] args) {
		SpringApplication.run(EatSystem1Application.class, args);
	}

	@Bean
	public CommandLineRunner runner(IDayRepo dayRepo, IDepartmentRepo departmentRepo,
			IEmployeeOrderStatusRepo employeeOrderStatusRepo, IEmployeeRepo employeeRepo,
			IEmployeeStatusRepo employeeStatusRepo, IOrderRepo orderRepo, IProjectRepo projectRepo) {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				
				Department dep1 = new Department("Accounting", null);
				Department dep2 = new Department("Software engineering", null);
				departmentRepo.save(dep1);
				departmentRepo.save(dep2);
				
				Employee emp1 = new Employee ("John", "Smith", "Software engineer", dep2, "jsmith@gmail.com");
				Employee emp2 = new Employee ("Anne", "Lee", "Accounting", dep1, "annelee123@gmail.com");
				employeeRepo.save(emp1);
				employeeRepo.save(emp2);
				
				dep1.setManager(emp2);
				departmentRepo.save(dep1);
				
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
				
				Day day1 = new Day(empSt1, LocalDate.of(2025, 11, 3));
				Day day2 = new Day(empSt2, LocalDate.of(2025, 11, 4));
				dayRepo.save(day1);
				dayRepo.save(day2);
				
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

			}

		};
	}
}

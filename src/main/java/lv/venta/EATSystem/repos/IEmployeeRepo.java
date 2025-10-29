package lv.venta.EATSystem.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.EATSystem.models.Employee;

public interface IEmployeeRepo extends CrudRepository<Employee, Integer>{

}

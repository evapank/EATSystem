package lv.venta.EATSystem.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.EATSystem.models.EmployeeStatus;

public interface IEmployeeStatusRepo extends CrudRepository<EmployeeStatus, Integer>{

}

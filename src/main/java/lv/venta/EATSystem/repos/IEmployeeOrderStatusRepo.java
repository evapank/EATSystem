package lv.venta.EATSystem.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.EATSystem.models.EmployeeOrderStatus;

public interface IEmployeeOrderStatusRepo extends CrudRepository<EmployeeOrderStatus, Integer>{

}

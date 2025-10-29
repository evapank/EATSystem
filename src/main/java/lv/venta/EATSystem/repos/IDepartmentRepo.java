package lv.venta.EATSystem.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.EATSystem.models.Department;

public interface IDepartmentRepo extends CrudRepository<Department, Integer>{

}

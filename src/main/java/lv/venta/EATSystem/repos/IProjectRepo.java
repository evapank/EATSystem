package lv.venta.EATSystem.repos;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.venta.EATSystem.models.Project;

public interface IProjectRepo extends CrudRepository<Project, Integer>{

	Project findByIdProject(int id);

	void deleteByIdProject(int id);

	ArrayList<Project> findByEmployeesIdEmployee(int id);

}

package lv.venta.EATSystem.repos;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import lv.venta.EATSystem.models.MyUser;

@Repository
public interface IMyUserRepo extends CrudRepository<MyUser,String>{

	MyUser findByUsername(String username);

	MyUser findByEmployeeEmail(String email);

	MyUser findByIdMyUser(int userId);

	boolean existsByUsername(String username);

	boolean existsByIdMyUser(int userId);

}

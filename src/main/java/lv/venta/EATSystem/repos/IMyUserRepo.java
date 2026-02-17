package lv.venta.EATSystem.repos;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import lv.venta.EATSystem.models.MyUser;

@Repository
public interface IMyUserRepo extends MongoRepository<MyUser,String>{

	MyUser findByUsername(String username);

	MyUser findByEmployeeEmail(String email);

}

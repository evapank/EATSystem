package lv.venta.EATSystem.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.EATSystem.models.Order;

public interface IOrderRepo extends CrudRepository<Order, Integer>{

}

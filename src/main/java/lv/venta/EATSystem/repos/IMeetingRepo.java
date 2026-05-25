package lv.venta.EATSystem.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.EATSystem.models.Meeting;

public interface IMeetingRepo extends CrudRepository<Meeting, Integer>{

	Meeting findByIdMeeting(int id);

}

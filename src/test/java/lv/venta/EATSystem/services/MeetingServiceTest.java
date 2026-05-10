package lv.venta.EATSystem.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lv.venta.EATSystem.models.Meeting;
import lv.venta.EATSystem.repos.IMeetingRepo;

@SpringBootTest(properties = "spring.profiles.active=test")
class MeetingServiceTest {

	@Autowired
	IMeetingService meetingService;
	
	@Autowired
	IMeetingRepo meetingRepo;
	
	@Test
	void testSelectAll() {
		Meeting meeting = new Meeting();
		meetingRepo.save(meeting);
		
		assertFalse(meetingService.selectAllMeetings().isEmpty());
	}

}

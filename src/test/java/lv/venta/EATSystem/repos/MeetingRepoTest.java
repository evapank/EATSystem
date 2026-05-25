package lv.venta.EATSystem.repos;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lv.venta.EATSystem.enums.GeneralStatus;
import lv.venta.EATSystem.models.Meeting;

@SpringBootTest(properties = "spring.profiles.active=test")
class MeetingRepoTest {

	@Autowired
	private IMeetingRepo meetingRepo;
	
	@Test
	void test() {
		Meeting meeting = new Meeting(LocalDateTime.of(
						2026, Month.APRIL, 26, 18, 00, 00), LocalDateTime.of(
	                    2026, Month.APRIL, 26, 18, 30, 00), GeneralStatus.Online);
		meetingRepo.save(meeting);
		
		assertNotNull(meetingRepo.findById(meeting.getIdMeeting()));
	}

}

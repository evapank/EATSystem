package lv.venta.EATSystem.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lv.venta.EATSystem.enums.GeneralStatus;
import lv.venta.EATSystem.models.Meeting;
import lv.venta.EATSystem.repos.IMeetingRepo;
import lv.venta.EATSystem.services.impl.MeetingServiceImpl;

@SpringBootTest(properties = "spring.profiles.active=test")
class MeetingServiceTest {

	@InjectMocks
	MeetingServiceImpl meetingService;
	
	@Mock
	IMeetingRepo meetingRepo;
	
	@Test
	void testCreate() {
		Meeting meeting = new Meeting(LocalDateTime.of(2026, Month.JULY, 9, 14, 45), LocalDateTime.of(2026, Month.JULY, 9, 15, 20),
				GeneralStatus.Online);
		
		when(meetingRepo.save(any())).thenReturn(meeting);
		
		try {
			Meeting meetingFromService = meetingService.insertNewMeeting(meeting.getDateTimeStart(), meeting.getDateTimeEnd(), meeting.getGeneralStatus());
			assertEquals(LocalDateTime.of(2026, Month.JULY, 9, 15, 20), meetingFromService.getDateTimeEnd());
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
	}

}

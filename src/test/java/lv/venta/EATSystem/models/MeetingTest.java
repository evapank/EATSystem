package lv.venta.EATSystem.models;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import lv.venta.EATSystem.enums.GeneralStatus;

@SpringBootTest(properties = "spring.profiles.active=test")
class MeetingTest {

	private Meeting meetingRight = new Meeting(LocalDateTime.of(
            2026, Month.APRIL, 24, 14, 30, 00), LocalDateTime.of(
                   2026, Month.APRIL, 24, 15, 30, 00), GeneralStatus.InPerson);
	
	
	@Test
	void testCreate() {
		assertEquals("InPerson", meetingRight.getGeneralStatus().toString());
		assertEquals(LocalDateTime.of(2026, Month.APRIL, 24, 14, 30, 00), meetingRight.getDateTimeStart());
		assertEquals(LocalDateTime.of(2026, Month.APRIL, 24, 15, 30, 00), meetingRight.getDateTimeEnd());
	}
}

package lv.venta.EATSystem.models;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;

import lv.venta.EATSystem.enums.GeneralStatus;

class MeetingTest {

	Meeting meetingRight = new Meeting(LocalDateTime.of(
            2026, Month.APRIL, 24, 14, 30, 00), LocalDateTime.of(
                    2026, Month.APRIL, 24, 15, 30, 00), GeneralStatus.InPerson);
	@Test
	void test() {
		assertEquals("InPerson", GeneralStatus.InPerson.toString());
	}

}

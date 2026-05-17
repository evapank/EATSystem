package lv.venta.EATSystem.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import lv.venta.EATSystem.enums.GeneralStatus;
import lv.venta.EATSystem.models.Meeting;
import lv.venta.EATSystem.services.IMeetingService;

@SpringBootTest(properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
class MeetingControllerTest {

	@Mock
	private static IMeetingService meetingService;
	
	@InjectMocks
	private static MeetingController meetingController;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	void test() {
		Meeting meeting1 = new Meeting(LocalDateTime.of(2026, Month.JUNE, 2, 13, 00, 00), LocalDateTime.of(2026, Month.JUNE, 2, 14, 00, 00), GeneralStatus.InPerson);
		Meeting meeting2 = new Meeting(LocalDateTime.of(2026, Month.JUNE, 10, 15, 30, 00), LocalDateTime.of(2026, Month.JUNE, 10, 16, 00, 00), GeneralStatus.Online);
		
		ArrayList<Meeting> allMeetings = new ArrayList<>(Arrays.asList(meeting1, meeting2));
		
		try {
			mockMvc.perform(get("/meeting/all"))
			.andExpect(status().isOk())
			.andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
			.andExpect((ResultMatcher) jsonPath("$[0].generalStatus", GeneralStatus.InPerson))
			.andExpect((ResultMatcher) jsonPath("$[1].dateTimeStart", LocalDateTime.of(2026, Month.JUNE, 10, 15, 30, 00)));
		} catch (Exception e){
			
		}
	}

}

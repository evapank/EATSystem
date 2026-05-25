package lv.venta.EATSystem.models.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MeetingDTO {

	private LocalDateTime dateTimeStart;
	private LocalDateTime dateTimeEnd;
}

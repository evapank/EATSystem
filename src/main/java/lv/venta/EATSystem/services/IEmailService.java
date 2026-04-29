package lv.venta.EATSystem.services;

import java.time.LocalDateTime;

public interface IEmailService {

	public abstract void sendEmail(String email, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd);
}

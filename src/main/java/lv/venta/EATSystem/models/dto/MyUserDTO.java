package lv.venta.EATSystem.models.dto;

import lombok.Data;
import lv.venta.EATSystem.models.Employee;
import lv.venta.EATSystem.models.MyAuthority;

@Data
public class MyUserDTO {
	private String username;
	private String password;
	private MyAuthority authority;
	private Employee employee;
}

package lv.venta.EATSystem.models;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lv.venta.EATSystem.enums.SecurityRole;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table
@Entity
public class MyUser {
	
	@Id
	@Column(name = "IdUser")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private int idUser;
	
	@Column(name = "Username")
	@Size(min = 3, max = 20)
	@Pattern(regexp = "[a-zA-Z0-9]+")
	private String username;

	@Column(name = "Password")
	@Size(min = 8, max = 60)
	@Pattern(regexp = "[a-zA-Z0-9\\W]+")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	@Column(name = "Role")
	private ArrayList<SecurityRole> roles;
	
	@OneToOne
	@JoinColumn(name = "IdEmployee")
	private Employee employee;
	

	public MyUser(String username, String password, Employee employee) {
		
		this.username = username;
		this.password = password;
		this.employee = employee;
		roles.add(SecurityRole.EMPLOYEE);
	}
	
	public MyUser(String username) {
		this.username = username;
	}
	
	public void addRole(SecurityRole role) {
		roles.add(role);
	}

}

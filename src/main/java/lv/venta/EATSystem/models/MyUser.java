package lv.venta.EATSystem.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name="myUser_table")
public class MyUser {
	@Id
	@Column(name = "IdMyUser")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private int idMyUser;
	
	@Column(name = "Username")
	private String username;
	
	@Column(name = "Password")
	@Size(min=5)
	private String password;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idAuthority")
	private MyAuthority authority;
	
	public MyUser(String username, String password, MyAuthority authority) {
	this.username = username;
	this.password = password;
	this.authority = authority;
	}
}

package lv.venta.EATSystem.models;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lv.venta.EATSystem.enums.SecurityRole;


@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name="myAuthority_table")
public class MyAuthority {
	
	@Id
	@Column(name = "AuthorityId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private int authorityId;
	
	@Column(name = "Title")
	private SecurityRole title;
	
	@OneToMany(mappedBy = "authority")
	@ToString.Exclude
	@JsonIgnore
	private Collection<MyUser> users;
	
	public MyAuthority(SecurityRole title) {
		this.title = title;
	}
}


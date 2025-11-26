package lv.venta.EATSystem.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table
@Entity
public class Department {

	@Id
	@Column(name = "IdDepartment")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private int idDepartment;
	
	@Column(name = "Title")
	@Size(min = 3, max = 100)
	//@Pattern(regexp = "[A-Z]{1}[a-z]+")
	private String title;
	
	@OneToOne
	@JoinColumn(name = "IdEmployee")
	@JsonIgnore
	private Employee manager;
	
	public Department (String title, Employee manager) {
		
		this.title = title;
		this.manager = manager;
		
	}
}

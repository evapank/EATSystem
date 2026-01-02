package lv.venta.EATSystem.models;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
	
	@OneToMany(mappedBy = "department")
	@JsonIgnore
	@ToString.Exclude
	private Collection<Employee> employees = new ArrayList<Employee>();
	
	public Department (String title) {
		
		this.title = title;
		
	}
	
	public void addEmployee(Employee employee) {
		employees.add(employee);
	}
	
	
}

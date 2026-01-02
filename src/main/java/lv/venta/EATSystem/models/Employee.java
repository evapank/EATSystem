package lv.venta.EATSystem.models;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
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
public class Employee {
	
	@Id
	@Column(name = "IdEmployee")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private int idEmployee;
	
	@Column(name = "Name")
	@Size(min = 1, max = 100)
	@Pattern(regexp = "[A-Z]{1}[a-z]+")
	private String name;
	
	@Column(name = "Surname")
	@Size(min = 1, max = 300)
	@Pattern(regexp = "[A-Z]{1}[a-z]+")
	private String surname;
	
	@Column(name = "Position")
	@Size(min = 3, max = 100)
	//@Pattern(regexp = "[A-Z]{1}[a-z]+")
	private String postion;
	
	@ManyToOne
	@JoinColumn(name = "IdDepartment")
	private Department department;
	
	@Column(name = "Email")
	@Size(min = 3, max = 300)
	@Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
	private String email;
	
	@Column(name = "IsManager")
	private boolean isManager;
	
	@ManyToMany
	@JoinTable(joinColumns = @JoinColumn(name = "IdEmployee"),inverseJoinColumns = @JoinColumn(name = "IdProject"),
	name = "projects_employees")
	@ToString.Exclude
	@JsonIgnore
	private Collection<Project> projects  = new ArrayList<Project>();
	
	@OneToMany(mappedBy = "employee")
	@JsonIgnore
	@ToString.Exclude
	private Collection<EmployeeOrderStatus> orderStatuses  = new ArrayList<EmployeeOrderStatus>();
	
	@OneToMany(mappedBy = "employee")
	@JsonIgnore
	@ToString.Exclude
	private Collection<EmployeeStatus> statuses  = new ArrayList<EmployeeStatus>();
	
	public Employee (String name, String surname, String position, Department department,
			String email, boolean isManager) {
		
		this.name = name;
		this.surname = surname;
		this.postion = position;
		this.department = department;
		this.email = email;
		this.isManager = isManager;
	}
	
	public void addProjects(Project project) {
		
		projects.add(project);
		
	}
	
	public void addOrderStatus(EmployeeOrderStatus orderStatus) {
		
		orderStatuses.add(orderStatus);
		
	}
	
	public void addStatus(EmployeeStatus status) {
		
		statuses.add(status);
		
	}
	

}

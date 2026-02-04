package lv.venta.EATSystem.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
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
public class Project {
	
	@Id
	@Column(name = "IdProject")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private int idProject;
	
	@Column(name = "ProjectNumber")
	@Min(value = 0)
	private int projectNumber;
	
	@Column(name = "Title")
	@Size(min = 3, max = 300)
	//@Pattern(regexp = "[A-Z]{1}[a-z]+")
	private String title;
	
	@Column(name = "DateStart")
	private LocalDate dateStart;
	
	@Column(name = "DateEnd")
	private LocalDate dateEnd;
	
	@ManyToOne
	@JoinColumn(name = "IdEmployee")
	private Employee projectManager;
	
	@ManyToMany(mappedBy = "projects")
	@JsonIgnore
	@ToString.Exclude
	private Collection<Employee> employees = new ArrayList<Employee>();
	
	public Project(int projectNumber, String title, LocalDate dateStart, LocalDate dateEnd,
			Employee projectManager) {
		
		this.projectNumber = projectNumber;
		this.title = title;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.projectManager = projectManager;
		
	}
	
	public void addEmployee (Employee employee) {
		
		employees.add(employee);
		
	}
	
	public void removeEmployee(Employee employee) {
		employees.remove(employee);
	}

}

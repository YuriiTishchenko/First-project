package ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="name_string_specification", indexes=@Index(columnList="_name"))
public class NameStringSpecification extends AbstractEntity {
	
	@Column(name = "_name")
	private String name;
	@OneToMany(mappedBy="nameStringSpecification")
	private List<Specification> specifications = new ArrayList<>();
	
	
	public List<Specification> getSpecifications() {
		return specifications;
	}

	public void setSpecifications(List<Specification> specifications) {
		this.specifications = specifications;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}

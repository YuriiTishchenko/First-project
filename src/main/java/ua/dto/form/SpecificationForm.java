package ua.dto.form;

import ua.entity.NameStringSpecification;

public class SpecificationForm {

	private int id;

	private NameStringSpecification nameStringSpecification;

	private String value;

	public int getId() {
		return id;
	}

	public NameStringSpecification getNameStringSpecification() {
		return nameStringSpecification;
	}

	public String getValue() {
		return value;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNameStringSpecification(
			NameStringSpecification nameStringSpecification) {
		this.nameStringSpecification = nameStringSpecification;
	}

	public void setValue(String value) {
		this.value = value;
	}

}

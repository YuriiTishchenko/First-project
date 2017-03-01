package ua.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="specification", indexes=@Index(columnList="_value"))
public class Specification extends AbstractEntity {
	
	@Column(name = "_value")
	private BigDecimal value;
	@ManyToOne(fetch = FetchType.LAZY)
	private NameStringSpecification nameStringSpecification;
	
	@OneToMany(mappedBy="specification")
	private List <Item> items = new ArrayList<>();
	
	public Specification() {}
	
	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public NameStringSpecification getNameStringSpecification() {
		return nameStringSpecification;
	}

	public void setNameStringSpecification(
			NameStringSpecification nameStringSpecification) {
		this.nameStringSpecification = nameStringSpecification;
	}

	
}

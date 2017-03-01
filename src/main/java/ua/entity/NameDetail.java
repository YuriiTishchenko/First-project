package ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="name_detail", indexes=@Index(columnList="_name"))
public class NameDetail extends AbstractEntity {
	
	@Column(name = "_name")
	private String name;
	@OneToMany(mappedBy="nameDetail")
	private List <Item> items = new ArrayList<>();
	

	public NameDetail() {}
	
	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}

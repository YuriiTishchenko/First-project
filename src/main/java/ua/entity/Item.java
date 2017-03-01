package ua.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;
@Entity
@Table(name="item", indexes=@Index(columnList="price"))
public class Item extends AbstractEntity {
	
	
	@Column(name = "version", nullable = true)
	private Integer version;
	
	@Transient
	private MultipartFile file;


	public MultipartFile getFile() {
		return file;
	}



	public void setFile(MultipartFile file) {
		this.file = file;
	}

	@ManyToOne(fetch= FetchType.LAZY)	
	private TypeDetail typeDetail;
	
	@ManyToOne(fetch= FetchType.LAZY)
	private NameDetail nameDetail;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Producer producer;
	
	private BigDecimal price;
	
	@ManyToOne(fetch= FetchType.LAZY)	
	private Description description;
	
	@ManyToMany(mappedBy="items")
	private List<User> users = new ArrayList<>();

	
	public List<User> getUsers() {
		return users;
	}



	public void setUsers(List<User> users) {
		this.users = users;
	}



	public Integer getVersion() {
		return version;
	}

	
	public void setVersion(Integer version) {
		this.version = version;
	}

	@ManyToOne(fetch= FetchType.LAZY)
	private Specification specification;
	
	
	public Item() {}

	public TypeDetail getType() {
		return typeDetail;
	}

	public void setType(TypeDetail type) {
		this.typeDetail = type;
	}

	public NameDetail getNameDetail() {
		return nameDetail;
	}

	public void setNameDetail(NameDetail nameDetail) {
		this.nameDetail = nameDetail;
	}

	public Producer getProducer() {
		return producer;
	}

	public void setProducer(Producer producer) {
		this.producer = producer;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	
	public Specification getSpecification() {
		return specification;
	}

	public void setSpecification(Specification specification) {
		this.specification = specification;
	}

	public TypeDetail getTypeDetail() {
		return typeDetail;
	}

	public void setTypeDetail(TypeDetail typeDetail) {
		this.typeDetail = typeDetail;
	}



	public Description getDescription() {
		return description;
	}



	public void setDescription(Description description) {
		this.description = description;
	}

	
}

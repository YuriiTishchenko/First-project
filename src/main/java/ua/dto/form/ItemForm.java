package ua.dto.form;

import org.springframework.web.multipart.MultipartFile;

import ua.entity.Description;
import ua.entity.NameDetail;
import ua.entity.Producer;
import ua.entity.Specification;
import ua.entity.TypeDetail;

public class ItemForm {
	
	private int id;
	
	private TypeDetail typeDetail;
	
	private NameDetail nameDetail;
	
	private Producer producer;
	
	private String price;
	
	private Description description;
	
	private Specification specification;
	
	private MultipartFile file;
	
	private String version;
	
	public String getVersion() {
		return version;
	}


	public void setVersion(String version) {
		this.version = version;
	}


	public MultipartFile getFile() {
		return file;
	}


	public void setFile(MultipartFile file) {
		this.file = file;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Description getDescription() {
		return description;
	}


	public void setDescription(Description description) {
		this.description = description;
	}


	public TypeDetail getTypeDetail() {
		return typeDetail;
	}


	public void setTypeDetail(TypeDetail typeDetail) {
		this.typeDetail = typeDetail;
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


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public Specification getSpecification() {
		return specification;
	}


	public void setSpecification(Specification specification) {
		this.specification = specification;
	}
	
	
}

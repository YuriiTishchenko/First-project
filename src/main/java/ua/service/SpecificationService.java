package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.dto.filter.SpecificationFilter;
import ua.dto.form.SpecificationForm;
import ua.entity.Specification;

public interface SpecificationService {
	
	Specification findOne(int id);
	
	SpecificationForm findForm(int id) ;
	 
	List<Specification> findAll();
	
	void save(SpecificationForm specificationForm);
	void delete(int id);
	
	Page<Specification> findAll(Pageable pageable,SpecificationFilter filter);
}

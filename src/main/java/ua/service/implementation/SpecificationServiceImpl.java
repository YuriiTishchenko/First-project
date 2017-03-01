package ua.service.implementation;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.dto.filter.SpecificationFilter;
import ua.dto.form.SpecificationForm;
import ua.entity.Item;
import ua.entity.Specification;
import ua.repository.SpecificationRepository;
import ua.service.SpecificationService;
import ua.service.specification.SpecificationSpecification;
@Service
public class SpecificationServiceImpl implements SpecificationService {
	
	
	@Autowired
	private SpecificationRepository repository;

	@Override
	public ua.entity.Specification findOne(int id) {
		
		return repository.findOne(id);
	}
	
	@Override
	@Transactional(readOnly=true)
	public SpecificationForm findForm( int id) {
		ua.entity.Specification entity = repository.findOne(id);
		SpecificationForm specificationForm = new SpecificationForm();
		specificationForm.setId(entity.getId());
		specificationForm.setNameStringSpecification(entity.getNameStringSpecification());
		specificationForm.setValue(String.valueOf(entity.getValue()));
		return specificationForm;
	}

	@Override
	@Transactional(readOnly=true)
	public List<ua.entity.Specification> findAll() {
		return repository.findAll();
	}

	@Override
	public void save(SpecificationForm specificationForm) {
		ua.entity.Specification specification = new ua.entity.Specification();
		specification.setId(specificationForm.getId());
		specification.setNameStringSpecification(specificationForm.getNameStringSpecification());
		specification.setValue(new BigDecimal(specificationForm.getValue().replace(',', '.')));
		repository.save(specification);
		
	}

	@Override
	public void delete(int id) {
		Specification specification = findOne(id);
		Hibernate.initialize(specification.getItems());
		for (Item item : specification.getItems()) {
			item.setSpecification(null);
		}	
		repository.delete(id);
	}
	
	@Override
	public Page<ua.entity.Specification> findAll(Pageable pageable, SpecificationFilter filter) {
		return repository.findAll(new SpecificationSpecification(filter), pageable);
	}

	

}

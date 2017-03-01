package ua.service.implementation;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.dto.filter.BasicFilter;
import ua.entity.NameStringSpecification;
import ua.entity.Specification;
import ua.repository.NameStringSpecificationRepository;
import ua.service.NameStringSpecificationService;
import ua.service.specification.NameStringSpecificationSpecification;
@Service
public class NameStringSpecificationServiceImpl  implements NameStringSpecificationService{
	@Autowired
	private NameStringSpecificationRepository repository;

	@Override
	@Transactional(readOnly=true)
	public NameStringSpecification findOne(int id) {
		return repository.findOne(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<NameStringSpecification> findAll() {
		return repository.findAll();
	}

	@Override
	public void save(NameStringSpecification nameStringSpecification) {
		repository.save(nameStringSpecification);
	}
	@Transactional
	@Override
	public void delete(int id) {
		NameStringSpecification nameStringSpecification = findOne(id);
		Hibernate.initialize(nameStringSpecification.getSpecifications());
		for (Specification specification : nameStringSpecification.getSpecifications()) {
			specification.setNameStringSpecification(null);
		}
		repository.delete(id);
	}

	@Override
	public NameStringSpecification findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public Page<NameStringSpecification> findAll(Pageable pageable,
			BasicFilter filter) {
		return repository.findAll(new NameStringSpecificationSpecification(filter), pageable);
	}
	

}

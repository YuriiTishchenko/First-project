package ua.service.implementation;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.dto.filter.BasicFilter;
import ua.entity.Item;
import ua.entity.TypeDetail;
import ua.repository.TypeDetailRepository;
import ua.service.TypeDetailService;
import ua.service.specification.TypeDetailSpecification;
@Service
public class TypeDetailServiceImpl  implements TypeDetailService{
	@Autowired
	private TypeDetailRepository repository;

	@Override
	@Transactional(readOnly=true)
	public TypeDetail findOne(int id) {
		// TODO Auto-generated method stub
		return repository.findOne(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<TypeDetail> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public void save(TypeDetail typeDetail) {
		repository.save(typeDetail);
		
	}

	@Override
	@Transactional
	public void delete(int id) {
		TypeDetail typeDetail = findOne(id);
		Hibernate.initialize(typeDetail.getItems());
		for (Item item : typeDetail.getItems()) {
			item.setTypeDetail(null);
		}	
		repository.delete(id);
		
	}

	@Override
	public TypeDetail findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public Page<TypeDetail> findAll(Pageable pageable, BasicFilter filter) {
		
		return repository.findAll(new TypeDetailSpecification(filter), pageable);
	}
	

}

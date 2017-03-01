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
import ua.entity.NameDetail;
import ua.repository.NameDetailRepository;
import ua.service.NameDetailService;
import ua.service.specification.NameDetailSpecification;

@Service
public class NameDetailServiceImpl implements NameDetailService {
	@Autowired
	private NameDetailRepository nameDetailRepository;

	@Override
	@Transactional(readOnly=true)
	public NameDetail findOne(int id) {
		return nameDetailRepository.findOne(id);
	}


	@Override
	public void save(NameDetail nameDetail) {
		nameDetailRepository.save(nameDetail);
	}

	@Override
	@Transactional
	public void delete(int id) {
		NameDetail nameDetail = findOne(id);
		Hibernate.initialize(nameDetail.getItems());
		for (Item item : nameDetail.getItems()) {
			item.setNameDetail(null);
		}	
		nameDetailRepository.delete(id);		
	}

	@Override
	public NameDetail findByName(String name) {
		return nameDetailRepository.findByName(name);
	}

	@Override
	@Transactional(readOnly=true)
	public List<NameDetail> findAll() {
		return nameDetailRepository.findAll();
	}

	@Override
	public Page<NameDetail> findAll(Pageable pageable, BasicFilter filter) {
		return nameDetailRepository.findAll(new NameDetailSpecification(filter), pageable);
	}
	
	
}

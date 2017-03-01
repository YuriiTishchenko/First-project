package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.dto.filter.ProducerFilter;
import ua.entity.Producer;

public interface ProducerService {
	Producer findOne(int id) ;
	Producer findByName(String name); 
	List<Producer> findAll();
	
	void save(Producer producer);
	void delete(int id);
	Page<Producer> findAll(Pageable pageable,ProducerFilter filter);
}

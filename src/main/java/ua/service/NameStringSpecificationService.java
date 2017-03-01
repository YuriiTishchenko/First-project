package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.dto.filter.BasicFilter;
import ua.entity.NameStringSpecification;

public interface NameStringSpecificationService {
	NameStringSpecification findOne(int id) ;
	 NameStringSpecification findByName(String name);
		List<NameStringSpecification> findAll();
		
		void save(NameStringSpecification nameStringSpecification);
		void delete(int id);
		Page<NameStringSpecification> findAll(Pageable pageable,BasicFilter filter);
}	

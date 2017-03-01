package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.dto.filter.BasicFilter;
import ua.entity.TypeDetail;

public interface TypeDetailService {
	TypeDetail findOne(int id) ;
	TypeDetail findByName(String name); 
	List<TypeDetail> findAll();
	
	void save(TypeDetail typeDetail);
	void delete(int id);
	Page<TypeDetail> findAll(Pageable pageable,BasicFilter filter);
	}

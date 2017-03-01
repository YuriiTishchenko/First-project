package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.dto.filter.BasicFilter;
import ua.entity.NameDetail;

public interface NameDetailService {
	 NameDetail findOne(int id) ;
	 NameDetail findByName(String name);
	 List<NameDetail> findAll();	
	 Page<NameDetail> findAll(Pageable pageable,BasicFilter filter);
		
		void save(NameDetail nameDetail);
		void delete(int id);
}

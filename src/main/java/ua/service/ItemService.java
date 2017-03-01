package ua.service;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.dto.filter.ItemFilter;
import ua.dto.form.ItemForm;
import ua.entity.Item;

public interface ItemService {
	 ItemForm findOne(int id) ;
	 
		List<Item> findAll();
		
		void save(ItemForm itemForm);
		void delete(int id);
		
		Page<Item> findAll(Pageable pageable,ItemFilter filter);
		 List<Item> findByUserId(Principal principal);
		 
		 void addUsers(int id, Principal principal);
}

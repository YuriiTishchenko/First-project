package ua.service.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.dto.filter.BasicFilter;
import ua.entity.NameStringSpecification;

public class NameStringSpecificationSpecification implements Specification<NameStringSpecification> {
	
private final BasicFilter filter;
	
	public NameStringSpecificationSpecification (BasicFilter filter){
		this.filter=filter;
	}
	
	@Override
	public Predicate toPredicate(Root<NameStringSpecification> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		if(filter.getSearch().isEmpty()) return null;
		return cb.like(root.get("name"), filter.getSearch()+"%");
	}
}

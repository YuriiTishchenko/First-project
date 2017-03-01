package ua.service.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.dto.filter.ProducerFilter;
import ua.entity.Producer;

public class ProducerSpecification  implements Specification<Producer>{

	private final ProducerFilter filter;
	
	private final List<Predicate> predicates = new ArrayList<>();
	
	public ProducerSpecification (ProducerFilter filter){
		this.filter=filter;
	}
	
	private void filterBySearch(Root<Producer> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getSearch().isEmpty()){
			predicates.add(cb.like(root.get("name"), filter.getSearch()+"%"));
		}
	}
	
	private void filterByCountry(Root<Producer> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getCountryIds().isEmpty()){
			predicates.add(root.get("country").in(filter.getCountryIds()));
		}
	}
	

	private void fetch(Root<Producer> root, CriteriaQuery<?> query){
		if(query.getResultType()!=Long.class){
			root.fetch("country", JoinType.LEFT);
		}
	}
	
	@Override
	public Predicate toPredicate(Root<Producer> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		fetch(root, query);
		filterBySearch(root, query, cb);
		filterByCountry(root, query, cb);
		if(predicates.isEmpty())return null;
		Predicate[] array = new Predicate[predicates.size()];
		predicates.toArray(array);
		return cb.and(array);
	}

}

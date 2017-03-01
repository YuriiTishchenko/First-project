package ua.service.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.dto.filter.SpecificationFilter;

public class SpecificationSpecification implements Specification<ua.entity.Specification> {

	private final SpecificationFilter filter;
	
	private final List<Predicate> predicates = new ArrayList<>();
	
	public SpecificationSpecification (SpecificationFilter filter){
		this.filter=filter;
	}
	
	private void filterByName(Root<ua.entity.Specification> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getNameStringSpecificationIds().isEmpty()){
			predicates.add(root.get("nameStringSpecification").in(filter.getNameStringSpecificationIds()));
		}
	}
	
	
/*	private void filterByValue(Root<ua.entity.Specification> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(filter.getMax()!=null&&filter.getMin()!=null){
			predicates.add(cb.between(root.get("value"), filter.getMin(), filter.getMax()));
		}else if(filter.getMaxValue()!=null){
			predicates.add(cb.lessThanOrEqualTo(root.get("value"), filter.getMax()));
		}else if(filter.getMinValue()!=null){
			predicates.add(cb.greaterThanOrEqualTo(root.get("value"), filter.getMin()));
		}
	}*/

	private void filterByValue(Root<ua.entity.Specification> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(filter.getMax()!=null&&filter.getMin()!=null){
			filterByMinMaxValue(root, query, cb);
		}else if(filter.getMax()!=null){
			filterByMaxValue(root, query, cb);
		}else if(filter.getMin()!=null){
			filterByMinValue(root, query, cb);
		}
	}
	private void filterByMinValue(Root<ua.entity.Specification> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		predicates.add(cb.greaterThanOrEqualTo(root.get("value"), filter.getMin()));
	}
	
	private void filterByMaxValue(Root<ua.entity.Specification> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		predicates.add(cb.lessThanOrEqualTo(root.get("value"), filter.getMax()));
	}
	private void filterByMinMaxValue(Root<ua.entity.Specification> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		predicates.add(cb.between(root.get("value"), filter.getMin(), filter.getMax()));
	}
	private void fetch(Root<ua.entity.Specification> root, CriteriaQuery<?> query){
		if(query.getResultType()!=Long.class){
			root.fetch("nameStringSpecification", JoinType.LEFT);
		}
	}

	@Override
	public Predicate toPredicate(Root<ua.entity.Specification> root,
			CriteriaQuery<?> query, CriteriaBuilder cb) {
		fetch(root, query);
		filterByName(root, query, cb);
		filterByValue(root, query, cb);
		if(predicates.isEmpty())return null;
		Predicate[] array = new Predicate[predicates.size()];
		predicates.toArray(array);
		return cb.and(array);
	}

}

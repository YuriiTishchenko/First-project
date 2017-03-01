package ua.service.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.dto.filter.ItemFilter;
import ua.entity.Item;

public class ItemSpecification implements Specification<Item> {

	private final ItemFilter filter;
	
	private final List<Predicate> predicates = new ArrayList<>();
	
	public ItemSpecification(ItemFilter filter) {
		this.filter = filter;
	}
	
	private void filterByTypeDetail(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(!filter.getTypeDetailIds().isEmpty()){
			predicates.add(root.get("typeDetail").in(filter.getTypeDetailIds()));
		}
	}
	private void filterByProducer(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(!filter.getProducerIds().isEmpty()){
			predicates.add(root.get("producer").in(filter.getProducerIds()));
		}
	}
	private void filterByNameDetail(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(!filter.getNameDetailIds().isEmpty()){
			predicates.add(root.get("nameDetail").in(filter.getNameDetailIds()));
		}
	}
	
	
	private void filterByPrice(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(filter.getMax()!=null&&filter.getMin()!=null){
			predicates.add(cb.between(root.get("price"), filter.getMin(), filter.getMax()));
		}else if(filter.getMax()!=null){
			predicates.add(cb.lessThanOrEqualTo(root.get("price"), filter.getMax()));
		}else if(filter.getMin()!=null){
			predicates.add(cb.greaterThanOrEqualTo(root.get("price"), filter.getMin()));
		}
	}
	
	private void fetch(Root<Item> root, CriteriaQuery<?> query){
		if(query.getResultType()!=Long.class){
			root.fetch("typeDetail", JoinType.LEFT);
			root.fetch("nameDetail", JoinType.LEFT);
			root.fetch("producer", JoinType.LEFT);
			root.fetch("specification", JoinType.LEFT);
			root.fetch("description", JoinType.LEFT);
			/*Join<Item,ua.entity.Specification> join = root.join("nameStringSpecification", JoinType.LEFT);*/
		}
	}
	
	@Override
	public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		fetch(root, query);
		filterByTypeDetail(root, query, cb);
		filterByNameDetail(root, query, cb);
		filterByPrice(root, query, cb);
		filterByProducer(root, query, cb);
		
		if(predicates.isEmpty())return null;
		Predicate[] array = new Predicate[predicates.size()];
		predicates.toArray(array);
		return cb.and(array);
	}
	
}

package ua.service.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.dto.filter.ItemFilter;
import ua.entity.Item;
import ua.entity.NameDetail;
import ua.entity.Producer;
import ua.entity.TypeDetail;

public class ItemIndexSpecification implements Specification<Item> {
	
	private final ItemFilter filter;
	
	public ItemIndexSpecification (ItemFilter filter){
		this.filter=filter;
	}
	
	private final List<Predicate> predicates = new ArrayList<>();
	
	private void filterByString(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getSearch().isEmpty()){
			Join<Item, NameDetail> joinName = root.join("nameDetail");
			predicates.add(cb.like(joinName.get("name"), filter.getSearch()+"%"));
			Join<Item, TypeDetail> joinType = root.join("typeDetail");
			predicates.add(cb.like(joinType.get("name"), filter.getSearch()+"%"));
			Join<Item, Producer> joinProd = root.join("producer");
			predicates.add(cb.like(joinProd.get("name"), filter.getSearch()+"%"));
			System.out.println("filterByString");
		}
		
	}
	private void fetch(Root<Item> root, CriteriaQuery<?> query){
		if(query.getResultType()!=Long.class){
			root.fetch("typeDetail", JoinType.LEFT);
			root.fetch("nameDetail", JoinType.LEFT);
			root.fetch("producer", JoinType.LEFT);
			root.fetch("specification", JoinType.LEFT);
			root.fetch("description", JoinType.LEFT);
		}
	}
	
	@Override
	public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		fetch(root, query);
		filterByString(root, query, cb);
		if(predicates.isEmpty())return null;
		System.out.println("--------------");
		Predicate[] array = new Predicate[predicates.size()];
		predicates.toArray(array);
		return cb.or(array);
	}
}

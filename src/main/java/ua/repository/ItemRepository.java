package ua.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>,JpaSpecificationExecutor<Item> {
	
	@Query("SELECT  i FROM Item i LEFT JOIN FETCH i.nameDetail LEFT JOIN FETCH i.typeDetail LEFT JOIN FETCH i.producer LEFT JOIN FETCH i.specification  ")
	List<Item> findAll();
	@Query("SELECT  i FROM Item i LEFT JOIN FETCH i.nameDetail LEFT JOIN FETCH i.typeDetail LEFT JOIN FETCH i.producer  LEFT JOIN FETCH i.specification s LEFT JOIN FETCH s.nameStringSpecification LEFT JOIN FETCH  i.description  WHERE i.id=:id")
	Item findOne(@Param("id")int id);
	
	@Query(value="SELECT  i FROM Item i LEFT JOIN FETCH i.nameDetail LEFT JOIN FETCH i.typeDetail LEFT JOIN FETCH i.producer LEFT JOIN FETCH i.specification ",
			countQuery="SELECT count(i.id) FROM Item i")
	Page<Item> findAll(Pageable pageable);
	@Query("SELECT DISTINCT i FROM Item i LEFT JOIN FETCH i.users  WHERE i.id=:id " )
	List<Item> findByUserId(@Param("id")int id);
}

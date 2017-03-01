package ua.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.Specification;

public interface SpecificationRepository extends JpaRepository<Specification, Integer>,JpaSpecificationExecutor<Specification> {
		@Query("SELECT s FROM Specification s LEFT JOIN FETCH s.nameStringSpecification")
		List<Specification> findAll();
		@Query("SELECT s FROM Specification s LEFT JOIN FETCH s.nameStringSpecification WHERE s.id=:id")
		Specification findOne(@Param("id")int id);
		
		@Query(value="SELECT s FROM Specification s LEFT JOIN FETCH s.nameStringSpecification",
				countQuery="SELECT count(s.id) FROM Specification s")
		Page<Specification> findAll(Pageable pageable);
}

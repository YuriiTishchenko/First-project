package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.Producer;

public interface ProducerRepository  extends JpaRepository<Producer, Integer>,JpaSpecificationExecutor<Producer>{
	@Query("SELECT p FROM Producer p LEFT JOIN FETCH p.country")	
	List<Producer> findAll();
	@Query("SELECT p FROM Producer p LEFT JOIN FETCH p.country  WHERE p.id=:id")
	Producer findOne(@Param("id")int id);
	Producer findByName(String name);
	
}

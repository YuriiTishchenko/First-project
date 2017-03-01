package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.entity.NameDetail;

public interface NameDetailRepository extends JpaRepository<NameDetail, Integer>,JpaSpecificationExecutor<NameDetail> {
	
	NameDetail findByName(String name);


}

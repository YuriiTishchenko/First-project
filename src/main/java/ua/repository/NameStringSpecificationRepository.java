package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.entity.NameStringSpecification;

public interface NameStringSpecificationRepository extends JpaRepository<NameStringSpecification, Integer>,JpaSpecificationExecutor<NameStringSpecification> {
	
	NameStringSpecification findByName(String name);
}

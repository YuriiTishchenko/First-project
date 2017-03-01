package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.entity.TypeDetail;

public interface TypeDetailRepository extends JpaRepository<TypeDetail, Integer>,JpaSpecificationExecutor<TypeDetail>{
	TypeDetail findByName(String name);
}

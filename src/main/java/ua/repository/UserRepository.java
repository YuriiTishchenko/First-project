package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	@Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.items i LEFT JOIN FETCH  i.nameDetail LEFT JOIN FETCH i.typeDetail LEFT JOIN FETCH i.producer  LEFT JOIN FETCH i.specification s LEFT JOIN FETCH s.nameStringSpecification LEFT JOIN FETCH  i.description   WHERE u.username=:username" )
	User findByUsername(String username);
	@Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.items i LEFT JOIN FETCH  i.nameDetail LEFT JOIN FETCH i.typeDetail LEFT JOIN FETCH i.producer  LEFT JOIN FETCH i.specification s LEFT JOIN FETCH s.nameStringSpecification  LEFT JOIN FETCH  i.description  "
				)
		List<User> findAll();
}
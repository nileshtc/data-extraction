package com.nileshchakraborty.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.nileshchakraborty.spring.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, String> {

	Optional<Employee> findByEmail(String email);

	List<Employee> findByFirstNameAndLastName(String fname, String lname);
		
}

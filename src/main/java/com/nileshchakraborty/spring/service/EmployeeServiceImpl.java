package com.nileshchakraborty.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nileshchakraborty.spring.entity.Employee;
import com.nileshchakraborty.spring.exception.BadRequestException;
import com.nileshchakraborty.spring.exception.EmployeeNotFoundException;
import com.nileshchakraborty.spring.exception.ResourceNotFoundException;
import com.nileshchakraborty.spring.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeRepository repository;
	
	@Transactional(readOnly = true)
	public List<Employee> findAll() {
		return (List<Employee>) repository.findAll();
	}

	@Transactional(readOnly = true)
	public Employee findOne(String id) {
		return repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee with id :"+ id +" not found"));
	}

	@Transactional
	public Employee create(Employee employee) {
		Optional<Employee> emp = repository.findByEmail(employee.getEmail());
		if(emp.isPresent()) {
			throw new BadRequestException("Cannot Create Employee, already exists: "+ emp);
		}
		
		return repository.save(employee);
	}

	@Transactional
	public Employee update(String id, Employee employee) {
		Optional<Employee> emp = repository.findById(id);
		if(!emp.isPresent()) {
			throw new ResourceNotFoundException("Cannot Create Employee, doesnot exists: "+ emp);
		}
		
		return repository.save(employee);
	}

	@Transactional
	public void delete(String id) {
		Optional<Employee> emp = repository.findById(id);
		if(!emp.isPresent()) {
			throw new ResourceNotFoundException("Cannot Create Employee, doesnot exists: "+ emp);
		}
		
		repository.delete(emp.get());

	}
	
}

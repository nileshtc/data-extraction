package com.nileshchakraborty.spring.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.nileshchakraborty.spring.entity.Employee;

public interface EmployeeService {
	List<Employee> findAll();
	Employee findOne(String id);
	Employee create(Employee employee);
	Employee update(String id, Employee employee);
	void delete(String id);
	default String toInterfaceDefaultString() {
		return "This is from default method";
	}
	static String toInterfaceStaticString() {
		return "This is from static method";
	}
	static Collection<?> coll() {
		return null;
	}
}

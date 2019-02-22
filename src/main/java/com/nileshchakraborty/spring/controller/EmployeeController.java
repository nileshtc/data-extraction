package com.nileshchakraborty.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nileshchakraborty.spring.entity.Employee;
import com.nileshchakraborty.spring.service.EmployeeService;

@RestController
@RequestMapping(value = "employees")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	@RequestMapping(method = RequestMethod.GET)
	public List<Employee> findAll() {
		return employeeService.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public Employee findOne(@PathVariable("id") String id) {
		return employeeService.findOne(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Employee create(@RequestBody Employee employee) {
		//System.out.println(employee);
		return employeeService.create(employee);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	public Employee update(@PathVariable("id") String id, @RequestBody Employee employee) {
		System.out.println(id);
		System.out.println(employee);
		return employeeService.update(id, employee);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public void delete(@PathVariable("id") String id) {
		employeeService.delete(id);
	}
}

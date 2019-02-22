package com.nileshchakraborty.spring.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.nileshchakraborty.spring.entity.Employee;
import com.nileshchakraborty.spring.exception.ResourceNotFoundException;
import com.nileshchakraborty.spring.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
public class EmployeeServiceImplTest {
	@TestConfiguration
	static class EmployeeServiceImplTestConfiguration{
		@Bean
		public EmployeeService getService() {
			return new EmployeeServiceImpl();
		}
	}

	@Autowired
	private EmployeeService service;

	@MockBean
	private EmployeeRepository repository;

	private List<Employee> employees;
	@Before
	public void setup() {
		Employee emp = new Employee();
		emp.setFirstName("John");
		emp.setLastName("Smith");
		emp.setEmail("jsmith@abc.com");
		
		employees = (List<Employee>) Collections.singletonList(emp);
		
		Mockito.when(repository.findAll()).thenReturn(employees);
		
		Mockito.when(repository.findById(emp.getId())).thenReturn(Optional.of(emp));
	}	
	
	@After
	public void cleanup() {
		employees = null;
	}
	
	@Test
	public void findAll() throws Exception {
		// TODO Auto-generated method stub
		List<Employee> result = service.findAll();
		Assert.assertEquals("Employee", employees, result);
	}

	@Test
	public void findOne() throws Exception {
		// TODO Auto-generated method stub
		Employee result = service.findOne(employees.get(0).getId());
		Assert.assertEquals("Employee", employees.get(0), result);
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void findOneNotFound() throws Exception {
		// TODO Auto-generated method stub
		Employee result = service.findOne("mfasmkdsa");
		
	}


	@Test
	public void create() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Test
	public void update() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Test
	public void delete() throws Exception {
		// TODO Auto-generated method stub

	}

}

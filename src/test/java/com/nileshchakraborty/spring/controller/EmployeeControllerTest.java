package com.nileshchakraborty.spring.controller;

import org.hamcrest.Matchers;
import org.hibernate.hql.internal.ast.tree.IsNotNullLogicOperatorNode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nileshchakraborty.spring.entity.Employee;
import com.nileshchakraborty.spring.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private EmployeeRepository repository;

	@Autowired
	private ObjectMapper objMapper;

	@Before
	public void setup() {
		Employee emp = new Employee();
		emp.setId("t1");
		emp.setFirstName("John");
		emp.setLastName("Smith");
		emp.setEmail("jsmith@abc.com");
		repository.save(emp);
	}

	@After
	public void cleanup() {
		repository.deleteAll();
	}

	@Test
	public void findAll() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/employees")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].email", Matchers.is("jsmith@abc.com")));
	}

	@Test
	public void findOne() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/employees/t1")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("jsmith@abc.com")));
	}

	@Test
	public void create() throws Exception {
		Employee emp = new Employee();
		emp.setFirstName("HHAfs");
		emp.setLastName("gas");
		emp.setEmail("d@faf.com");
		mvc.perform(MockMvcRequestBuilders.post("/employees")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsBytes(emp)))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.notNullValue()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.email",Matchers.is("d@faf.com")));
	}

	@Test
	public void update() throws Exception {

	}

	@Test
	public void delete() throws Exception {

	}

}

package com.project.professor.allocation.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Department;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class DepartmentServiceTest {
	
	@Autowired
	private DepartmentService departmentService;
	
	@Test
	public void findAllTest() {
		//In Service
		List<Department> departments = departmentService.findAll(null);
		
		//Result
		System.out.println(departments);
	}
	
	@Test
	public void findByIdTest() {
		//From Controller
		Long departmentId = 2L;
		
		//In Service
		Department department = departmentService.findById(departmentId);
		
		//Result
		System.out.println(department);
	}
	
	@Test
	public void createTest() {
		//From Controller
		Department department = new Department(null, "Novo Departamento Cadastrado", null);
		
		//In Service
		department.setId(null);
		Department newDepartment = departmentService.create(department);
		
		//Result
		System.out.println(newDepartment);
	}
	
	@Test
	public void updateTest() {
		//From Controller
		Department department = new Department(null, "Novo Departamento Cadastrado", null);

		//In Service
		Department newDepartment = departmentService.update(department);
		
		//Result
		System.out.println(newDepartment);
	}
	
	@Test
	public void deleteByIdTest() {
		//From Controller
		Long departmentId = 2L;
		
		//In Service
		departmentService.deleteById(departmentId);
	}
	
	@Test
	public void deleteAllTest() {
		//In Service
		departmentService.deleteAll();
	}
}

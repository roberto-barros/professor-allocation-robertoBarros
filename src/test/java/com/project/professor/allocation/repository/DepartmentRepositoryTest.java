package com.project.professor.allocation.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Department;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")

public class DepartmentRepositoryTest {

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Test
	public void testCreate() {
		//From Controller - Arrange
		Department department = new Department();
		department.setId(5L);
		department.setName("Novo Departamento");
		
		//In Service - Act
		departmentRepository.save(department);
		
		//Result
		System.out.println(department);
	}
	
	@Test
	public void testRead1() {
		
		List<Department> departments = departmentRepository.findAll();
	
		System.out.println(departments);
	}
	
	@Test
	public void testRead2() {
		
		Long id = 5L;
		
		Optional<Department> optional = departmentRepository.findById(id);
		Department dep = optional.orElse(null);
	
		System.out.println(dep);
	}
	
	@Test
	public void testUpdate() {
		
		Department department = new Department();
		department.setId(1L);
		department.setName("Departamento de Português");
		
		if(departmentRepository.existsById(department.getId())) {
			Department newDep = departmentRepository.save(department);
			
		System.out.println(newDep);
		}
		
		/*if(departmentRepository.existsById(department.getId())) {
			Optional<Department> optional = departmentRepository.findById(department.getId());
			Department dep = optional.get();
			//realiza o set: .setId() ou .setName()
			dep.setName("Departamento Update");
			departmentRepository.save(dep);
		}*/
	}
	
	@Test
	public void testDelete1() {
		
		Long id = 5L;
		
		if(id !=null && departmentRepository.existsById(id)) {
			departmentRepository.deleteById(id);
		}
		
		
	}
	
	@Test
	public void testDelete2() {
		
		departmentRepository.deleteAllInBatch();
		
	}
}

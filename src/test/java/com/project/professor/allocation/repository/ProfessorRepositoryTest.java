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
import com.project.professor.allocation.entity.Professor;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")

public class ProfessorRepositoryTest {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Test
	void testCreate() {
		//From Controller - Arrange
		/*
		Department dep = new Department();
			dep.setId(3L);
		Professor professor = new Professor();
			professor.setId(null);
			professor.setCpf("111.111.111-17");
			professor.setName("Novo Professor");
		
			professor.setDepartment(dep);
			
		//In Service - Act	
		Professor newProfessor = professorRepository.save(professor);
		
		//Result
		System.out.println(newProfessor);
		*/
		
		//From Controller - Arrange
		Department dept = new Department(3L);
		Professor prof = new Professor();
		prof.setId(null);
		prof.setCpf("111.111.222-44");
		prof.setName("New Professor1");
		prof.setDepartment(dept);
		
		//In Service - Act
		prof.setId(null);
		Professor newProf = professorRepository.save(prof);
		Long departmentId = newProf.getDepartment().getId();
		
		Department newDepartment = departmentRepository.findById(departmentId).orElse(null);
		newProf.setDepartment(newDepartment);
		
		//Result
		System.out.println(newProf);
	}
	
	@Test
	void testRead1() {
		
		List<Professor> professors = professorRepository.findAll();
		
	}
	
	@Test
	void testRead2() {
		
		Long id = 7L;
		
		Optional<Professor> optional = professorRepository.findById(id);
		Professor prof = optional.orElse(null);
		
	}
	
	@Test
	void testUpdate() {
		
		Long id = 7L;
		
		if(professorRepository.existsById(id)) {
			Optional<Professor> optional = professorRepository.findById(id);
			Professor prof = optional.get();
			//Realiza o set: .setName() ou .setCpf ou .setDepartment()
			prof.setName("Professor Novo Nome");
			prof.setCpf("111.111.111-20");
			
			professorRepository.save(prof);
		}
	}
	
	@Test
	void testDelete1() {
		
		Long id = 7L;
		
		professorRepository.deleteById(id);
	}
	
	@Test
	void testDelete2() {
		
		professorRepository.deleteAllInBatch();
	}
}

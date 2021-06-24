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
	public void testCreate() {
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
		Department dept = new Department(3L, null, null);
		Professor prof = new Professor(null, "111.111.222-44", "New Professor1", dept, null);
		
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
	public void testRead1() {
		
		List<Professor> professors = professorRepository.findAll();
		
		System.out.println(professors);
	}
	
	@Test
	public void testRead2() {
		
		Long id = 7L;
		
		Optional<Professor> optional = professorRepository.findById(id);
		Professor prof = optional.orElse(null);
		
		System.out.println(prof);
	}
	
	@Test
	public void testUpdate() {
		
		//From Controller - Arrange
		Department dept = new Department(3L, null, null);
		Professor prof = new Professor(7L, "555.555.555-55", "Tal Pessoa", dept, null);
		
		//In Service - Act
		if(professorRepository.existsById(prof.getId())) {
			Professor newProf = professorRepository.save(prof);
			Long departmentId = newProf.getDepartment().getId();
			
			Department newDepartment = departmentRepository.findById(departmentId).orElse(null);
			newProf.setDepartment(newDepartment);
			
		//Result
		System.out.println(newProf);
		}		
		
		/*Long id = 7L;
		
		if(professorRepository.existsById(id)) {
			Optional<Professor> optional = professorRepository.findById(id);
			Professor prof = optional.get();
			//Realiza o set: .setName() ou .setCpf ou .setDepartment()
			prof.setName("Professor Novo Nome");
			prof.setCpf("111.111.111-20");
			
			professorRepository.save(prof);
		}*/
		
	}
	
	@Test
	public void testDelete1() {
		
		Long id = 7L;
		
		if(id != null && professorRepository.existsById(id)) {
			professorRepository.deleteById(id);
		}
		
	}
	
	@Test
	public void testDelete2() {
		
		professorRepository.deleteAllInBatch();
	}
}

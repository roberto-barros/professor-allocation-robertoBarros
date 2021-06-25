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
import com.project.professor.allocation.entity.Professor;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class ProfessorServiceTest {

	@Autowired
	private ProfessorService professorService;
	@Autowired 
	DepartmentService departmentService;
	
	@Test
	public void findAllTest() {
		//In Service
		List<Professor> professors = professorService.findAll();
		
		// Result
		System.out.println(professors);
	}
	@Test
	public void findByIdTest() {
		//From Controller
		Long professorId = 3L;
		
		//In Service
		Professor professors = professorService.findById(professorId);
		
		//Result
		System.out.println(professors);
	}
	@Test
	public void createTest() {
		//From Controller
		Department department = new Department(4L, null, null);
		Professor professor = new Professor(null, "555.555.555-55", "New Professor 2.0", department, null);
		
		//In Service
		professor.setId(null);
		Professor newProfessor = professorService.create(professor);
		
		//Result
		System.out.println(newProfessor);
	}
	
	@Test
	public void updateTest() {
		//From Controller
		Department department = new Department(2L, "Mamute", null);
		Professor professor = new Professor(7L, "888.888.888-88", "Rita", department, null);
		
		//In Service
		Professor newProfessor = professorService.update(professor);
		
		//Result
		System.out.println(newProfessor);
		}
	
	@Test
	public void deleteByIdTest() {
		//From Controller
		Long professorId = 13L;
		
		//In Service
		professorService.deleteById(professorId);
	}
	
	@Test
	public void deleteAllTest() {
		//In Service
		professorService.deleteAll();
	}
}

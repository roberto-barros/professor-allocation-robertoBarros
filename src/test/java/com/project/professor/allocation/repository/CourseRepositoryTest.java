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

import com.project.professor.allocation.entity.Course;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class CourseRepositoryTest {

	@Autowired
	private CourseRepository courseRepository;
	
	@Test
	public void testCreate() {
		
		//From Controller - Arrange
		Course course = new Course();
		course.setId(5L);
		course.setName("Novo Curso");
		
		//In Service - Act
		courseRepository.save(course);
		
		//Result
		System.out.println(course);
	}
	
	@Test
	public void testRead1() {
		
		List<Course> courses = courseRepository.findAll();
		
		System.out.println(courses);
	}
	
	@Test
	public void testRead2() {
		
		//From Controller - Arrange
		
		Long id = 5L;
		
		//In Service - Act
		Optional<Course> optional = courseRepository.findById(id);
		Course c = optional.orElse(null);
		
		//Result
		System.out.println(c);
		
	}
	
	@Test
	public void testUpdate() {
		
		//From Controller
		Course course = new Course();
		course.setId(1L);
		course.setName("Português");
		
		//In Service - Act
		if(courseRepository.existsById(course.getId())) {
			Course newCourse = courseRepository.save(course);
			
		//Result
			System.out.println(newCourse);
		}
		
		/*Long id = 5L;
		
		if(courseRepository.existsById(id)) {
			Optional<Course> optional = courseRepository.findById(id);
			Course c = optional.get();
			//Realiza o set: .setName()
			c.setName("Novo Curso Update");
			courseRepository.save(c);
		}*/
	}
	
	@Test
	public void testDelete1() {
		
		Long id = 5L;
		
		if(id != null && courseRepository.existsById(id)) {
			courseRepository.deleteById(id);			
		}

	}
	
	@Test
	public void testDelete2() {
		
		courseRepository.deleteAllInBatch();
	}
}

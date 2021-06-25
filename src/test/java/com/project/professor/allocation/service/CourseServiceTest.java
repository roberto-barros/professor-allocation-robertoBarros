package com.project.professor.allocation.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Course;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class CourseServiceTest {
	
	@Autowired
	private CourseService courseService;
	
	@Test
	public void findByIdTest() {
		//From Controller
		Long courseId = 3L;
		
		//In Service
		Course course = courseService.findById(courseId);
		
		//Result
		System.out.println(course);
	}
	
	@Test
	public void findAllTest() {
		//In Service
		List<Course> courses = courseService.findAll();
		
		//Result
		System.out.println(courses);
	}
	
	@Test
	public void createTest() {
		//From Controller
		Course course = new Course(null, "Novo Curso", null);
		
		//In Service
		Course newCourse = courseService.create(course);
		
		//Result
		System.out.println(newCourse);
	}
	
	@Test
	public void updateTest() {
		//From Controller
		Course course = new Course(null, "Novo Curso", null);
		
		//In Service
		Course newCourse = courseService.update(course);
		
		//Result
		System.out.println(newCourse);
	}
	
	@Test
	public void deleteByIdTest() {
		//From Controller
		Long courseId = 3L;
		
		//In Service
		courseService.deleteById(courseId);
	}
	
	@Test
	public void deleteAllTest() {
		//In Service
		courseService.deleteAll();
	}
}

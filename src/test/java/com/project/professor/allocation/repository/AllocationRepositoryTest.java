package com.project.professor.allocation.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Professor;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")

public class AllocationRepositoryTest {

	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

	@Autowired
	private AllocationRepository allocationRepository;

	@Autowired
	private ProfessorRepository professorRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Test
	public void testCreate() throws ParseException {

		// From Controller - Arrange
		Professor prof = new Professor();
		prof.setId(1L);

		Course course = new Course();
		course.setId(1L);

		Allocation allocation = new Allocation();
		allocation.setId(8L);
		allocation.setProfessor(prof);
		allocation.setCourse(course);
		allocation.setDayOfWeek(DayOfWeek.FRIDAY);
		allocation.setStart(sdf.parse("10:00"));
		allocation.setEnd(sdf.parse("11:00"));

		// In Service - Act
		Allocation newAllocation = allocationRepository.save(allocation);
		Long professorId = newAllocation.getProfessor().getId();
		Long CourseId = newAllocation.getCourse().getId();

		Professor newProfessor = professorRepository.findById(professorId).orElse(null);
		newAllocation.setProfessor(newProfessor);
		Course newCourse = courseRepository.findById(CourseId).orElse(null);
		newAllocation.setCourse(newCourse);

		// Result
		System.out.println(newAllocation);
	}

	@Test
	public void testRead1() {

		List<Allocation> allocation = allocationRepository.findAll();

		System.out.println(allocation);
	}
	
	@Test
	public void testRead2() {

		//From Controller - Arrange
		Long id = 2L;

		//In Service - Act
		Optional<Allocation> optional = allocationRepository.findById(id);
		Allocation allocation = optional.orElse(null);
		
		System.out.println(allocation);
	
	}

	@Test
	public void testUpdate() throws ParseException {

		Professor prof = new Professor();
		prof.setId(1L);

		Course course = new Course();
		course.setId(1L);

		Allocation allocation = new Allocation();
		allocation.setId(8L);
		allocation.setProfessor(prof);
		allocation.setCourse(course);
		allocation.setDayOfWeek(DayOfWeek.FRIDAY);
		allocation.setStart(sdf.parse("10:00"));
		allocation.setEnd(sdf.parse("11:00"));

		// In Service - Act
		if (allocationRepository.existsById(allocation.getId())) {
			Allocation newAllocation = allocationRepository.save(allocation);
			Long professorId = newAllocation.getProfessor().getId();
			Long CourseId = newAllocation.getCourse().getId();

			Professor newProfessor = professorRepository.findById(professorId).orElse(null);
			newAllocation.setProfessor(newProfessor);
			Course newCourse = courseRepository.findById(CourseId).orElse(null);
			newAllocation.setCourse(newCourse);

			// Result
			System.out.println(newAllocation);
		}

	}

	@Test
	public void testDelete1() {
		
		Long id = 1L;
		
		if(id != null && allocationRepository.existsById(id)) {
			allocationRepository.deleteById(id);
		}
	}
	
	@Test
	public void testDelete2() {
		
		allocationRepository.deleteAll();
	}
}

package com.project.professor.allocation.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Professor;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationServiceTest {
	
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	
	@Autowired
	private AllocationService allocationService;

	@Test
	public void findByIdTest() {
		//From Controller
		Long allocationId = 2L;
		
		//In Service
		Allocation allocation = allocationService.findById(allocationId);
		
		//Result
		System.out.println(allocation);
	}
	
	@Test
	public void findAll() {
		//In Service
		List<Allocation> allocations = allocationService.findAll();
		
		//Result
		System.out.println(allocations);
	}
	
	@Test
	public void createTest() throws ParseException {
		//From Controller
		Professor professor = new Professor(1L, null, null, null, null);
		Course course = new Course(1L, null, null);
		
		Allocation allocation = new Allocation();
		allocation.setId(null);
		allocation.setProfessor(professor);
		allocation.setCourse(course);
		allocation.setDayOfWeek(DayOfWeek.TUESDAY);
		allocation.setStart(sdf.parse("10:00"));
		allocation.setEnd(sdf.parse("11:00"));
		
		//In Service
		Allocation newAllocation = allocationService.create(allocation);
		
		//Result
		System.out.println(newAllocation);
	}
	
	@Test
	public void updateTest() throws ParseException {
		//From Controller
		Professor professor = new Professor(1L, null, null, null, null);
		Course course = new Course(1L, null, null);
		
		Allocation allocation = new Allocation();
		allocation.setId(null);
		allocation.setProfessor(professor);
		allocation.setCourse(course);
		allocation.setDayOfWeek(DayOfWeek.TUESDAY);
		allocation.setStart(sdf.parse("10:00"));
		allocation.setEnd(sdf.parse("11:00"));
		
		//In Service
		Allocation newAllocation = allocationService.update(allocation);
		
		//Result
		System.out.println(newAllocation);
	}
	
	@Test
	public void deleteByIdTest() {
		//From Controller
		Long allocationId = 1L;
		
		//In Service
		allocationService.deleteById(allocationId);
	}
	
	@Test
	public void deleteAllTest() {
		//In Service
		allocationService.deleteAll();
	}
	
}

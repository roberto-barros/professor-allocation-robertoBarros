package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.repository.CourseRepository;

@Service
public class CourseService {
	
	private final CourseRepository courseRepository;

	public CourseService(CourseRepository courseRepository) {
		super();
		this.courseRepository = courseRepository;
	}
	
	public List<Course> findAll(String partName) {
		if(partName == null) {
			return courseRepository.findAll();		
		}else {
			return courseRepository.findByNameContainingIgnoreCase(partName);
		}
	}
	
	public Course findById(Long id) {
		Course course = courseRepository.findById(id).orElse(null);
		return course;
	}
	
	public Course create(Course course) {
		course.setId(null);
		return saveInteral(course);
	}
	public Course update(Course course) {
		if(courseRepository.existsById(course.getId())){
			return saveInteral(course);
		}else {
			return(null);
		}
	}
	
	private Course saveInteral(Course course) {
		Course newCourse = courseRepository.save(course);
		return newCourse;
	} 
	
	public void deleteById(Long courseId){
		if(courseRepository.existsById(courseId)) {
			courseRepository.deleteById(courseId);
		}
	}
	
	public void deleteAll() {
		courseRepository.deleteAllInBatch();
	}
}

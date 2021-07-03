package com.project.professor.allocation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.service.CourseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "COURSE REGISTER")
@RestController
@RequestMapping(path = "/course", produces = MediaType.APPLICATION_JSON_VALUE)
public class CourseController {

	private final CourseService courseService;

	public CourseController(CourseService courseService) {
		super();
		this.courseService = courseService;
	}

	@ApiResponse(code = 200, message = "OK")
	@GetMapping
	public ResponseEntity<List<Course>> findAll(@RequestParam(name = "partName", required = false) String name) {
		List<Course> courses = courseService.findAll(name);
		return new ResponseEntity<>(courses, HttpStatus.OK);
	}

	@ApiResponses({@ApiResponse(code = 404, message = "NOT FOUND"), @ApiResponse(code = 200, message = "OK")})
	@GetMapping(path = "/{courseId}")
	public ResponseEntity<Course> findById(@PathVariable(name = "courseId") Long id) {
		Course course = courseService.findById(id);
		if (course == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(course, HttpStatus.OK);
		}
	}

	@ApiResponses({@ApiResponse(code = 201, message = "CREATED"), @ApiResponse(code = 400, message = "BAD REQUEST")})
	@PostMapping
	public ResponseEntity<Course> create(@RequestBody Course course) {
		try {
			Course newCourse = courseService.create(course);
			return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiResponses({@ApiResponse(code = 404, message = "NOT FOUND"), @ApiResponse(code = 400, message = "BAD REQUEST"), @ApiResponse(code = 200, message = "OK")})
	@PutMapping(path = "/{courseId}")
	public ResponseEntity<Course> update(@PathVariable(name = "courseId") Long id, @RequestBody Course course) {
		course.setId(id);
		try {
			Course newCourse = courseService.update(course);
			if (newCourse == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(newCourse, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiResponse(code = 204, message = "DELETED")
	@DeleteMapping(path = "/{courseId}")
	public ResponseEntity<Void> deleteById(@PathVariable(name = "courseId") Long id) {
		courseService.deleteById(id);
		return new ResponseEntity<> (HttpStatus.NO_CONTENT);
	}
	
	@ApiResponse(code = 204, message = "ALL DELETED")
	@DeleteMapping
	public ResponseEntity<Void> deleteAll()	{
		courseService.deleteAll();
		return new ResponseEntity<> (HttpStatus.NO_CONTENT);
	}
}

package com.project.professor.allocation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.service.DepartmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "DEPARTMENT REGISTER")
@RestController
@RequestMapping(path = "/departments", produces = MediaType.APPLICATION_JSON_VALUE)
public class DepartmentController {

	private final DepartmentService departmentService;

	public DepartmentController(DepartmentService departmentService) {
		super();
		this.departmentService = departmentService;
	}
	
	@ApiResponse(code = 200, message = "OK")
	@GetMapping
	public ResponseEntity<List<Department>> findAll(@RequestParam(name = "partName", required = false) String name) {
		List<Department> departments = departmentService.findAll(name);
		return new ResponseEntity<>(departments, HttpStatus.OK);
	}

	@ApiResponses({
		@ApiResponse(code = 404, message = "NOT FOUND"), 
		@ApiResponse(code = 200, message = "OK"),
	})
	@GetMapping(path = "/{departmentId}")
	public ResponseEntity<Department> findById(@PathVariable(name = "departmentId") Long id) {
		Department department = departmentService.findById(id);
		if (department == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(department, HttpStatus.OK);
		}
	}

	@ApiResponses({
		@ApiResponse(code = 201, message = "CREATED"), 
		@ApiResponse(code = 400, message = "BAD REQUEST")
	})
	@PostMapping
	public ResponseEntity<Department> create(@RequestBody Department department) {
		try {
			Department newDepartment = departmentService.create(department);
			return new ResponseEntity<>(newDepartment, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiResponses({
		@ApiResponse(code = 404, message = "NOT FOUND"), 
		@ApiResponse(code = 200, message = "OK"), 
		@ApiResponse(code = 400, message = "BAD REQUEST")
	})
	@PostMapping(path = "/{departmentId}")
	public ResponseEntity<Department> update(@PathVariable(name = "departmentId") Long id, @RequestBody Department department) {
		department.setId(id);
		try {
			Department newDepartment = departmentService.update(department);
			if (newDepartment == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(newDepartment, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiResponse(code = 204, message = "DELETED")
	@DeleteMapping(path = "/{departmentId}")
	public ResponseEntity<Void> deleteById(@PathVariable(name = "departmentId") Long id) {
		departmentService.deleteById(id);
		return new ResponseEntity<> (HttpStatus.NO_CONTENT);
	}
	
	@ApiResponse(code = 204, message = "ALL DELETED")
	@DeleteMapping
	public ResponseEntity<Void> deleteAll() {
		departmentService.deleteAll();
		return new ResponseEntity<> (HttpStatus.NO_CONTENT);
	}

}

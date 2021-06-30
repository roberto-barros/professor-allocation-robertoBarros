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
import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.service.AllocationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "ALLOCATION REGISTER")
@RestController
@RequestMapping(path = "/allocations", produces = MediaType.APPLICATION_JSON_VALUE)
public class AllocationController {

	private final AllocationService allocationService;

	public AllocationController(AllocationService allocationService) {
		super();
		this.allocationService = allocationService;
	}
	
	@ApiResponse(code = 200, message = "OK")
	@GetMapping
	public ResponseEntity<List<Allocation>> findAll() {
		List<Allocation> allocations = allocationService.findAll();
		return new ResponseEntity<>(allocations, HttpStatus.OK);
	}

	@ApiResponse(code = 200, message = "OK")
	@GetMapping(path = "/allocationId")
	public ResponseEntity<Allocation> findById(@PathVariable(name = "allocationId") Long id) {
		Allocation allocation = allocationService.findById(id);
		return new ResponseEntity<>(allocation, HttpStatus.OK);
	}
	@ApiResponse(code = 200, message = "OK")
	@GetMapping(path = "/professorId")
	public ResponseEntity<List<Allocation>> findByProfessorId(@PathVariable(name = "professorId") Long id) {
		List<Allocation> allocations = allocationService.findByProfessorId(id);
		return new ResponseEntity<>(allocations, HttpStatus.OK);
	}

	@ApiResponse(code = 200, message = "OK")
	@GetMapping(path = "/courseId")
	public ResponseEntity<List<Allocation>> findByCourseId(@PathVariable(name = "courseId") Long id) {
		List<Allocation> allocations = allocationService.findByCourseId(id);
		return new ResponseEntity<>(allocations, HttpStatus.OK);
	}

	@ApiResponse(code = 200, message = "OK")
	@GetMapping(path = "/professorName")
	public ResponseEntity<List<Allocation>> findByProfessorName(@PathVariable(name = "professorName") String professorName) {
		List<Allocation> allocations = allocationService.findByProfessorName(professorName);
		return new ResponseEntity<>(allocations, HttpStatus.OK);
	}

	@ApiResponse(code = 200, message = "OK")
	@GetMapping(path = "/courseName")
	public ResponseEntity<List<Allocation>> findByCourseName(@PathVariable(name = "courseName") String courseName) {
		List<Allocation> allocations = allocationService.findByCourseName(courseName);
		return new ResponseEntity<>(allocations, HttpStatus.OK);
	}

	@ApiResponses({@ApiResponse(code = 201, message = "CREATED"), @ApiResponse(code = 400, message = "BAD REQUEST")})
	@PostMapping
	public ResponseEntity<Allocation> create(@RequestBody Allocation allocation) {
		try {
			Allocation newAllocation = allocationService.create(allocation);
			return new ResponseEntity<>(newAllocation, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiResponses({@ApiResponse(code = 404, message = "NOT FOUND"), @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "BAD REQUEST")})
	@PutMapping(path = "/allocationId")
	public ResponseEntity<Allocation> update(@PathVariable(name = "allocationId") Long id, @RequestBody Allocation allocation) {
		allocation.setId(id);
		try {
			Allocation newAllocation = allocationService.update(allocation);
			if (newAllocation == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(newAllocation, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiResponse(code = 204, message = "DELETED")
	@DeleteMapping(path = "/allocationId")
	public ResponseEntity<Void> deleteById(@PathVariable(name = "allocationId") Long id) {
		allocationService.deleteById(id);
		return new ResponseEntity<> (HttpStatus.NO_CONTENT);
	}
	
	@ApiResponse(code = 204, message = "ALL DELETED")
	@DeleteMapping
	public ResponseEntity<Void> deleteAll(){
		allocationService.deleteAll();
		return new ResponseEntity<> (HttpStatus.NO_CONTENT);
	}

}

package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	private final DepartmentRepository departmentRepository;

	public DepartmentService(DepartmentRepository departmentRepository) {
		super();
		this.departmentRepository = departmentRepository;
	}
	
	public List<Department> findAll() {
		List<Department> departments = departmentRepository.findAll();
		return departments;
	}
	
	public Department findById(Long id) {
		Department department = departmentRepository.findById(id).orElse(null);
		return department;
	}
	
	public Department create(Department department) {
		department.setId(null);
		return saveInternal(department);
	}
	
	public Department update(Department department) {
		return saveInternal(department);
	}
	
	private Department saveInternal(Department department) {
		Department newDepartment = departmentRepository.save(department);
		return newDepartment;
	}
	
	public void deleteById(Long departmentId) {
		if(departmentRepository.existsById(departmentId)) {
			departmentRepository.deleteById(departmentId);
		}
	}
	
	public void deleteAll() {
		departmentRepository.deleteAllInBatch();
	}
}

package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.ProfessorRepository;

@Service
public class ProfessorService {

	private final ProfessorRepository professorRepository;
	private final DepartmentService departmentService;

	public ProfessorService(ProfessorRepository professorRepository, DepartmentService departmentService) {
		super();
		this.professorRepository = professorRepository;
		this.departmentService = departmentService;
	}
	
	public List<Professor> findAll(){
		List<Professor> professors = professorRepository.findAll();
		return professors;
	}
	
	public Professor findById(Long id) {
		Professor professor = professorRepository.findById(id).orElse(null);
		return professor;
	}
	
	public Professor create(Professor professor) {
		professor.setId(null);
		return saveInternal(professor);
	}
	
	public Professor update(Professor professor) {
		if(professorRepository.existsById(professor.getId())) {
			return saveInternal(professor);
		}else {
			return null;
		}
	}
	
	private Professor saveInternal(Professor professor) {
		Professor newProfessor = professorRepository.save(professor);
		
		Long departmentId = newProfessor.getDepartment().getId();
		Department newDepartment = departmentService.findById(departmentId);
		newProfessor.setDepartment(newDepartment);
		
		return newProfessor;
	}
	
	public void deleteById(Long professorId) {
		if(professorRepository.existsById(professorId)) {
			departmentService.deleteById(professorId);
		}
	}
	
	public void deleteAll() {
		departmentService.deleteAll();
	}
}

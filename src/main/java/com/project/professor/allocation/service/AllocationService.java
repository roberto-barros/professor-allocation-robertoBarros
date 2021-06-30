package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.AllocationRepository;

@Service
public class AllocationService {
	
	private final AllocationRepository allocationRepository;
	private final ProfessorService professorService;
	private final CourseService courseService;

	public AllocationService(AllocationRepository allocationRepository, ProfessorService professorService, CourseService courseService) {
		super();
		this.allocationRepository = allocationRepository;
		this.professorService = professorService;
		this.courseService = courseService;
	}
	
	public List<Allocation> findAll() {
		return allocationRepository.findAll();
	}
	
	public Allocation findById(Long id) {
		return allocationRepository.findById(id).orElse(null);
	}
	
	public List<Allocation> findByProfessorId(Long professorId) {
		return allocationRepository.findByProfessorId(professorId);
	}
	
	public List<Allocation> findByCourseId(Long courseId){
		return allocationRepository.findByCourseId(courseId);
	}
	
	public List<Allocation> findByProfessorName(String professorName){
		return allocationRepository.findByProfessorNameContainingIgnoreCase(professorName);
	}
	
	public List<Allocation> findByCourseName(String courseName){
		return allocationRepository.findByCourseNameContainingIgnoreCase(courseName);
	}
	
	public Allocation create(Allocation allocation) {
		allocation.setId(null);
		return saveInternal(allocation);
	}
	
	public Allocation update(Allocation allocation) {
		if(allocationRepository.existsById(allocation.getId())){
			return saveInternal(allocation);
		}else {
			return null;
		}
	}
	
	public void deleteById(Long allocationId) {
		if(allocationId != null && allocationRepository.existsById(allocationId)) {
			allocationRepository.deleteById(allocationId);
		}
	}
	
	public void deleteAll() {
		allocationRepository.deleteAllInBatch();
	}
	
	private Allocation saveInternal(Allocation allocation) {
		if(!hasCollision(allocation)) {
		Allocation newAllocation = allocationRepository.save(allocation);
		
		Long professorId = newAllocation.getProfessor().getId();
		Professor professor = professorService.findById(professorId);
		
		Long courseId = newAllocation.getCourse().getId();
		Course course = courseService.findById(courseId);
		
		newAllocation.setProfessor(professor);
		newAllocation.setCourse(course);
		
		return newAllocation;
		}else {
			throw new AllocationCollisionException(allocation);
		}
	}
	
	boolean hasCollision(Allocation newAllocation) {
		boolean hasCollision = false;

		List<Allocation> currentAllocations = allocationRepository.findByProfessorId(newAllocation.getProfessor().getId());

		for (Allocation currentAllocation : currentAllocations) {
			hasCollision = hasCollision(currentAllocation, newAllocation);
			if (hasCollision) {
				break;
			}
		}

		return hasCollision;
	}

	private boolean hasCollision(Allocation currentAllocation, Allocation newAllocation) {
		return !currentAllocation.getId().equals(newAllocation.getId())
				&& currentAllocation.getDayOfWeek() == newAllocation.getDayOfWeek()
				&& currentAllocation.getStart().compareTo(newAllocation.getEnd()) < 0
				&& newAllocation.getStart().compareTo(currentAllocation.getEnd()) < 0;
	}
}

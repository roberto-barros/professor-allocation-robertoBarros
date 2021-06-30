package com.project.professor.allocation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.professor.allocation.entity.Allocation;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation, Long> {
	
	//Custom Queries
	
	List<Allocation> findByProfessorId(Long id);
	
	List<Allocation> findByCourseId(Long id);
	
	List<Allocation> findByProfessorNameContainingIgnoreCase(String professorName);
	
	List<Allocation> findByCourseNameContainingIgnoreCase(String courseName);

}

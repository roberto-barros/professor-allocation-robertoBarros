package com.project.professor.allocation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Professor;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation, Long> {
	
	//Custom Queries
	
	List<Allocation> findByFkIdLike(Professor id);
	
	List<Allocation> findByFkIdLike(Course id);
	
	//é possível fazer um find por parte do nome do professor em allocation?
}

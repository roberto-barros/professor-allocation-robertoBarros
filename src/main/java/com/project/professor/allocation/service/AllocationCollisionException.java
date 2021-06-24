package com.project.professor.allocation.service;

import com.project.professor.allocation.entity.Allocation;

public class AllocationCollisionException extends RuntimeException {
	
	private static final long serialVersionUID = -8143291971256058638L;
	private static final String MESSAGE_BASE = "Time collision to allocation: %s";

    public AllocationCollisionException(Allocation allocation) {
        super(String.format(MESSAGE_BASE, allocation));
    }
}

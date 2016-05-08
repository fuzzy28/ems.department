package org.jrue.ems.department.exception;

public class DepartmentIdNotConsistentException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public DepartmentIdNotConsistentException() {
    }

    public DepartmentIdNotConsistentException(String message) {
	super(message);
    }

}

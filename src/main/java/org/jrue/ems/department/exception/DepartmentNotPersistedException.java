package org.jrue.ems.department.exception;

public class DepartmentNotPersistedException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public DepartmentNotPersistedException() {
    }

    public DepartmentNotPersistedException(String message) {
	super(message);
    }

}

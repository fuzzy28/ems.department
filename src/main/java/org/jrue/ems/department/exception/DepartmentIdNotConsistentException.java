package org.jrue.ems.department.exception;

/**
 * The DepartmentIdNotConsistentException class is a custom class use if ID in
 * URL path and request payload does not match.
 * 
 * @author Joel F. Ruelos Jr.
 * @since 1.0
 * 
 */

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

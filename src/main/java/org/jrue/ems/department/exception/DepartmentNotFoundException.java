package org.jrue.ems.department.exception;

/**
 * The DepartmentNotFoundException class is a custom class use if ${domainName}
 * resource cannot be found in the database.
 * 
 * @author Joel F. Ruelos Jr.
 * @since 1.0
 * 
 */

public class DepartmentNotFoundException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public DepartmentNotFoundException() {
    }

    public DepartmentNotFoundException(String message) {
	super(message);
    }

}

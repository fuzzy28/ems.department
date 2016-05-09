package org.jrue.ems.department.exception;

/**
 * The DepartmentNotPersistedException class is a custom class use if
 * ${domainName} was not persisted or saved in the database.
 * 
 * @author Joel F. Ruelos Jr.
 * @since 1.0
 * 
 */

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

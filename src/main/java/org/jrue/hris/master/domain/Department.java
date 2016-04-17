package org.jrue.hris.master.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Department Resource 
 * @author Joel F. Ruelos Jr.
 * @since 1.0
 */

@Entity
public class Department {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String departmentCode;

    @Column
    private String departmentName;

    @Column
    private boolean active;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getDepartmentCode() {
	return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
	this.departmentCode = departmentCode;
    }

    public String getDepartmentName() {
	return departmentName;
    }

    public void setDepartmentName(String departmentName) {
	this.departmentName = departmentName;
    }

    public boolean isActive() {
	return active;
    }

    public void setActive(boolean active) {
	this.active = active;
    }

}
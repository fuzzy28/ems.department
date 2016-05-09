package org.jrue.ems.department.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Department class is the most important entity of this service.
 * 
 * @author Joel F. Ruelos Jr.
 * @since 1.0
 */

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "departmentcode")
    @NotNull(message = "err.departmentcode.notnull")
    @Size(min = 1, max = 10, message = "err.departmentcode.size")
    private String departmentCode;

    @Column(name = "departmentname")
    @NotNull(message = "err.departmentname.notnull")
    @Size(min = 1, max = 100, message = "err.departmentname.size")
    private String departmentName;

    @Column
    private boolean active = true;

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
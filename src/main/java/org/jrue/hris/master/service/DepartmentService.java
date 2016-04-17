package org.jrue.hris.master.service;

import java.util.Collection;

import org.jrue.hris.master.domain.Department;

/**
 * Department Service Interface
 * @author Joel F. Ruelos Jr.
 * @since 1.0
 */

public interface DepartmentService {

    Collection<Department> findAll();

    Department findOne(Long id);

    Department save(Department persist);

    Department update(Department update);

    void delete(Long id);
}
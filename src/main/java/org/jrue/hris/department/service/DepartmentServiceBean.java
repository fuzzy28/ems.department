package org.jrue.hris.department.service;

import java.util.Collection;

import org.jrue.hris.department.domain.Department;
import org.jrue.hris.department.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of Department Service
 * 
 * @author Joel F. Ruelos Jr.
 * @since 1.0
 */

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DepartmentServiceBean implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Collection<Department> findAll() {
	return departmentRepository.findAll();
    }

    @Override
    @Cacheable(value = "departments", key = "#id")
    public Department findOne(Long id) {
	return departmentRepository.findOne(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @CachePut(value = "departments", key = "#result.id", condition = "#result != null")
    public Department save(Department persist) {

	Department department = null;
	if (persist != null && persist.getId() == null) {
	    department = saveOrUpdate(persist);
	}

	return department;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @CachePut(value = "departments", key = "#update.id", condition = "#result != null")
    public Department update(Department update) {

	Department department = null;
	if (update != null && update.getId() != null
		&& departmentRepository.findOne(update.getId()) != null) {
	    department = saveOrUpdate(update);
	}

	return department;
    }

    private Department saveOrUpdate(Department department) {
	return departmentRepository.save(department);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @CacheEvict(value = "departments", key = "#id")
    public void delete(Long id) {
	departmentRepository.delete(id);
    }

    @Override
    public long countAll() {
	return departmentRepository.count();
    }
}
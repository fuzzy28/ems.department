package org.jrue.hris.master.repository;

import org.jrue.hris.master.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Department Repository which acts as Data Access Object 
 * @author Joel F. Ruelos Jr.
 * @since 1.0
 */

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}

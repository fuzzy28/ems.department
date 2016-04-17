package org.jrue.hris.master;

import javax.transaction.Transactional;

import org.jrue.hris.master.domain.Department;
import org.jrue.hris.master.service.DepartmentService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;

@Transactional
public class DepartmentServiceTest extends AbstractTest {

    @Autowired
    DepartmentService departmentService;

    @Test
    public void whenPersistingWithoutIdThenShouldPersist() {
	// assert saving
	Department sdd = new Department();
	sdd.setDepartmentCode("SDD");
	sdd.setDepartmentName("SOFTWARE DEVELOPMENT DEPARTMENT");
	sdd.setActive(true);

	Department sddPersisted = departmentService.save(sdd);
	assertNotNull("failure - department was not persisted", sddPersisted);
    }

    @Test
    public void whenPesistingWithIdThenShouldNotPersist() {
	// assert saving with Id
	Department sdd = new Department();
	sdd.setId(1L);
	sdd.setDepartmentCode("SDD");
	sdd.setDepartmentName("SOFTWARE DEVELOPMENT DEPARTMENT");
	sdd.setActive(true);

	Department sddPersisted = departmentService.save(sdd);
	assertNull("failure - saving department with ID was persisted", sddPersisted);
    }

    @Test
    public void whenSuccessfullyPersistedThenShouldRetrieve() {
	// assert saving
	Department sdd = new Department();
	sdd.setDepartmentCode("SDD");
	sdd.setDepartmentName("SOFTWARE DEVELOPMENT DEPARTMENT");
	sdd.setActive(true);

	Department sddPersisted = departmentService.save(sdd);
	assertNotNull("failure - department was not persisted", sddPersisted);

	Department fep = new Department();
	fep.setDepartmentCode("FEP");
	fep.setDepartmentName("FRONT END PROCESSING DEPARTMENT");
	fep.setActive(true);
	assertNotNull("failure - department was not persisted",
		departmentService.save(fep));

	// assert find all
	int total = departmentService.findAll().size();
	assertEquals("failure - total size does not match", 2, total);

	// assert find single
	Department actual = departmentService.findOne(sddPersisted.getId());
	assertEquals("failure - department code not match", sdd.getDepartmentCode(),
		actual.getDepartmentCode());
	assertEquals("failure - department name not match", sdd.getDepartmentName(),
		actual.getDepartmentName());
	assertEquals("failure - department active not match", sdd.isActive(),
		actual.isActive());
    }

    @Test
    public void whenUpdateWithValidIdThenShouldSucces() {
	// assert saving
	Department sdd = new Department();
	sdd.setDepartmentCode("SDD");
	sdd.setDepartmentName("SOFTWARE DEVELOPMENT DEPARTMENT");
	sdd.setActive(true);

	Department sddPersisted = departmentService.save(sdd);
	assertNotNull("failure - department was not persisted", sddPersisted);

	sddPersisted.setDepartmentCode("SDD UPDATED");
	sddPersisted.setDepartmentName("SOFTWARE DEVELOPMENT DEPARTMENT TEST");
	sddPersisted.setActive(false);

	Department modified;
	assertNotNull("failure - department was not updated",
		modified = departmentService.update(sddPersisted));
	assertEquals("failure - department code not match",
		sddPersisted.getDepartmentCode(), modified.getDepartmentCode());
	assertEquals("failure - department name not match",
		sddPersisted.getDepartmentName(), modified.getDepartmentName());
	assertEquals("failure - department active not match", sddPersisted.isActive(),
		modified.isActive());
    }

    @Test
    public void whenUpdatingWithInvalidIdShouldFail() {
	// assert saving
	Department sdd = new Department();
	sdd.setDepartmentCode("SDD");
	sdd.setDepartmentName("SOFTWARE DEVELOPMENT DEPARTMENT");
	sdd.setActive(true);

	assertNull("failure - entity without ID should not be updated",
		departmentService.update(sdd));

	sdd.setId(Long.MAX_VALUE);

	assertNull("failure - entity with no existing ID should not be updated",
		departmentService.update(sdd));
    }

    @Test
    public void whenDeletingThenShouldNoLongerExists() {
	// assert saving
	Department sdd = new Department();
	sdd.setDepartmentCode("SDD");
	sdd.setDepartmentName("SOFTWARE DEVELOPMENT DEPARTMENT");
	sdd.setActive(true);

	Department sddPersisted = departmentService.save(sdd);
	departmentService.delete(sddPersisted.getId());
	assertNull("failure - resource has not been deleted",
		departmentService.findOne(sddPersisted.getId()));
    }
}
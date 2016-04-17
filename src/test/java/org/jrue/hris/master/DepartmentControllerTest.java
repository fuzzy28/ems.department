package org.jrue.hris.master;

import java.util.ArrayList;
import java.util.Collection;
import javax.transaction.Transactional;

import org.jrue.hris.master.controller.DepartmentController;
import org.jrue.hris.master.domain.Department;
import org.jrue.hris.master.service.DepartmentService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@Transactional
public class DepartmentControllerTest extends AbstractControllerTest {

    private final String BASE_URI = "/departments";

    @Mock
    protected DepartmentService departmentService;

    @InjectMocks
    protected DepartmentController departmentController;

    @Before
    public void setup() {
	MockitoAnnotations.initMocks(this);

	setup(departmentController);
    }

    protected Collection<Department> getAllDepartments() {
	Collection<Department> deps = new ArrayList<>();
	Department sdd = new Department();
	sdd.setId(1L);
	sdd.setDepartmentCode("SDD");
	sdd.setDepartmentName("SOFTWARE DEVELOPMENT DEPARTMENT");
	sdd.setActive(true);
	deps.add(sdd);

	Department fep = new Department();
	fep.setId(2L);
	fep.setDepartmentCode("FEP");
	fep.setDepartmentName("FRONT END PROCESSING DEPARTMENT");
	fep.setActive(true);
	deps.add(fep);
	return deps;
    }

    protected Department getSingleDepartment(Long id) {
	for (Department dep : getAllDepartments()) {
	    if (dep.getId() == id)
		return dep;
	}
	return null;
    }

    @Test
    public void whenFetchingAllThenShouldListAvailableDepartments() throws Exception {
	when(departmentService.findAll()).thenReturn(getAllDepartments());

	MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URI)
		.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

	assertSuccessStatusAndHasContent(response.getResponse());

	verify(departmentService, times(1)).findAll();
    }

    @Test
    public void whenFindingExistingRecordThenShouldFetchSuccessfully() throws Exception {
	Long id = 1L;
	when(departmentService.findOne(any(Long.class)))
		.thenReturn(getSingleDepartment(id));

	MvcResult response = mockMvc
		.perform(MockMvcRequestBuilders.get(BASE_URI + "/" + id)).andReturn();

	assertSuccessStatusAndHasContent(response.getResponse());

	verify(departmentService, times(1)).findOne(id);
    }

    @Test
    public void whenFindingNonExistingRecordThenShouldReturnNotFound() throws Exception {
	Long id = Long.MAX_VALUE;
	when(departmentService.findOne(any(Long.class)))
		.thenReturn(getSingleDepartment(id));

	MvcResult response = mockMvc
		.perform(MockMvcRequestBuilders.get(BASE_URI + "/" + id)).andReturn();

	assertNotFoundStatusAndHasNoContent(response.getResponse());

	verify(departmentService, times(1)).findOne(id);
    }

    @Test
    public void whenSavingWithoutIdThenEntityShouldBeSaved() throws Exception {
	Department department = getSingleDepartment(1L);

	when(departmentService.save(any(Department.class))).thenReturn(department);

	MvcResult response = postRequest(super.objectToJson(department));

	assertEquals("failure - status not 201", HttpStatus.CREATED.value(),
		response.getResponse().getStatus());

	assertTrue("failure - content empty",
		response.getResponse().getContentAsString().trim().length() > 0);

	Department persistedHrd = super.jsonToObject(
		response.getResponse().getContentAsString(), Department.class);

	assertNotNull("failure - persisted hrd returns null", persistedHrd);

	verify(departmentService, times(1)).save(any(Department.class));

	assertContentEquals(department, persistedHrd);

    }

    @Test
    public void whenSavingNotSuccessfullThenShouldReturnInternalServerError()
	    throws Exception {
	Department department = getSingleDepartment(1L);

	when(departmentService.save(any(Department.class))).thenReturn(null);

	MvcResult response = postRequest(super.objectToJson(department));

	assertEquals("failure - status not 500", HttpStatus.INTERNAL_SERVER_ERROR.value(),
		response.getResponse().getStatus());

	assertTrue("failure - content not empty",
		response.getResponse().getContentAsString().trim().length() == 0);

    }

    private MvcResult postRequest(String content) throws Exception {
	return mockMvc.perform(MockMvcRequestBuilders.post(BASE_URI).content(content)
		.accept(MediaType.APPLICATION_JSON_VALUE)
		.contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
    }

    @Test
    public void whenUpdatingNonExistingResourceThenShouldReturnNotFound()
	    throws Exception {
	Department department = getSingleDepartment(1L);

	when(departmentService.update(any(Department.class))).thenReturn(null);

	MvcResult response = putRequest(super.objectToJson(department), Long.MAX_VALUE);

	assertNotFoundStatusAndHasNoContent(response.getResponse());
    }

    @Test
    public void whenUpdatingExistingResourceThenShouldReturnUpdatedEntity()
	    throws JsonProcessingException, Exception {
	Department department = getSingleDepartment(1L);

	department.setDepartmentCode("SDD UP");
	department.setDepartmentName("SOFTWARE DEVELOPMENT DEPARTMENT TEST");
	department.setActive(false);

	when(departmentService.update(any(Department.class))).thenReturn(department);

	MvcResult response = putRequest(super.objectToJson(department),
		department.getId());

	verify(departmentService, times(1)).update(any(Department.class));

	Department updatedEntity = super.jsonToObject(
		response.getResponse().getContentAsString(), Department.class);

	assertContentEquals(department, updatedEntity);
    }

    @Test
    public void whenDeletingNonExistingResourceThenShouldReturnNotFound()
	    throws Exception {

	when(departmentService.findOne(any(Long.class))).thenReturn(null);

	MvcResult response = mockMvc
		.perform(MockMvcRequestBuilders
			.delete(BASE_URI + "/" + any(Long.class).longValue()))
		.andReturn();

	assertNotFoundStatusAndHasNoContent(response.getResponse());

    }

    @Test
    public void whenDeletingExistingResourceThenShouldReturnOk() throws Exception {
	Department department = getSingleDepartment(1L);

	when(departmentService.findOne(any(Long.class))).thenReturn(department);
	
	MvcResult response = mockMvc.perform(
		MockMvcRequestBuilders.delete(BASE_URI + "/" + department.getId()))
		.andReturn();

	assertEquals("failure - status not NO_CONTENT", HttpStatus.NO_CONTENT.value(),
		response.getResponse().getStatus());

	assertTrue("failure - response has content",
		response.getResponse().getContentAsString().trim().length() == 0);
	
	verify(departmentService,times(1)).findOne(any(Long.class));
	
	verify(departmentService,times(1)).delete(any(Long.class));
	
	
    }

    private MvcResult putRequest(String content, Long id) throws Exception {
	return mockMvc
		.perform(MockMvcRequestBuilders.put(BASE_URI + "/" + id)
			.accept(MediaType.APPLICATION_JSON_VALUE)
			.contentType(MediaType.APPLICATION_JSON_VALUE).content(content))
		.andReturn();
    }

    private void assertSuccessStatusAndHasContent(MockHttpServletResponse response)
	    throws Exception {
	assertEquals("failure - status not 200", HttpStatus.OK.value(),
		response.getStatus());
	assertTrue("failure - content empty",
		response.getContentAsString().trim().length() > 0);
	logger.info("content is > " + response.getContentAsString());
    }

    private void assertNotFoundStatusAndHasNoContent(MockHttpServletResponse response)
	    throws Exception {
	assertEquals("failure - has fetched non existing department",
		HttpStatus.NOT_FOUND.value(), response.getStatus());
	assertTrue("failure - 404 page content is not empty",
		response.getContentAsString().trim().length() == 0);
	logger.info("content is > " + response.getContentAsString());
    }

    private void assertContentEquals(Department expected, Department actual) {
	assertEquals("failure - department code not equal", expected.getDepartmentCode(),
		actual.getDepartmentCode());
	assertEquals("failure - department name not equal", expected.getDepartmentName(),
		actual.getDepartmentName());
	assertEquals("failure - department status not equal", expected.isActive(),
		actual.isActive());
    }
}

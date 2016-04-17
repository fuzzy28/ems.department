package org.jrue.hris.master;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Abstract Test class for Unit testing Controllers.
 * @author Joel F. Ruelos Jr.
 * @since 1.0
 */

@WebAppConfiguration
public class AbstractControllerTest {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    protected void setup() {
	mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected void setup(Object... controller) {
	mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    
    protected String objectToJson(Object object) throws JsonProcessingException {
	return new ObjectMapper().writeValueAsString(object);
    }
    
    protected <T> T jsonToObject(String json,Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
	return new ObjectMapper().readValue(json, clazz);
    }
}

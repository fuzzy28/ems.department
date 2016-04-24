package org.jrue.hris.department;

import org.jrue.hris.department.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Abstract Test which initialize the Spring boot application
 * @author Joel F. Ruelos Jr.
 * @since 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public abstract class AbstractTest {

    @Test
    public void contextLoads() {
    }

}

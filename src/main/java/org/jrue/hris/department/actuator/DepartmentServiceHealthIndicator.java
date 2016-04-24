package org.jrue.hris.department.actuator;

import org.jrue.hris.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class DepartmentServiceHealthIndicator implements HealthIndicator {

    @Autowired
    private DepartmentService departmentService;

    @Value("${health.indicator.message}")
    private String indicatorMessage;

    @Override
    public Health health() {

	long size = departmentService.countAll();
	boolean hasDepartment = size > 0;
	return (hasDepartment ? Health.up() : Health.down())
		.withDetail("info",
			String.format(indicatorMessage, hasDepartment ? size : "no"))
		.build();

    }

}
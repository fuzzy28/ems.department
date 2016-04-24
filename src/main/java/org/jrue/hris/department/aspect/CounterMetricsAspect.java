package org.jrue.hris.department.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class CounterMetricsAspect {

    @Autowired
    private CounterService counterService;

    @Before("execution(* org.jrue.hris.department.service.DepartmentService.*(..))")
    public void increment(JoinPoint joinPoint) throws Throwable {
	counterService.increment(joinPoint.getSignature().toShortString());
    }
}
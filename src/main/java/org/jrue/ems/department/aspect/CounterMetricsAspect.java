package org.jrue.ems.department.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * The CounterMetricsAspect class provides additional information to /metrics
 * actuator end point on how many times a service has been invoked.
 * 
 * @author Joel F. Ruelos Jr.
 * @since 1.0
 */

@Aspect
@Component
@Order(1)
public class CounterMetricsAspect {

    @Autowired
    private CounterService counterService;

    @Before("execution(* org.jrue.ems.department.service.*Service.*(..))")
    public void increment(JoinPoint joinPoint) throws Throwable {
	counterService.increment(joinPoint.getSignature().toShortString());
    }
}
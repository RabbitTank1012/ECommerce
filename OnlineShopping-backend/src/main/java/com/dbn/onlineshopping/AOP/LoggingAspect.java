package com.dbn.onlineshopping.AOP;

import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class LoggingAspect {
    private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
   /* @Before("com.dbn.onlineshopping.AOP.PointCuts.inControllerLayer()")
    public void logStartTime(){
        logger.info("From LoggingAspect.logStartTime in controller: " + System.currentTimeMillis()); // advice
    }

    @After("com.dbn.onlineshopping.AOP.PointCuts.inService()")
    public void logEndTime(JoinPoint joinPoint){
        logger.info("From LoggingAspect.logEndTime in service: " + System.currentTimeMillis()  + ": " + joinPoint.getSignature());
    }

    @Around("com.dbn.onlineshopping.AOP.PointCuts.inDAOLayer()")
    public UserResult logStartAndEndTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        // before
        logger.info("From LoggingAspect.logStartAndEndTime: " + proceedingJoinPoint.getSignature());
        logger.info("Start time: " + System.currentTimeMillis());

        //Invoke the actual object
        UserResult userResult = (UserResult) proceedingJoinPoint.proceed();

        // after
        logger.info("End time: " + System.currentTimeMillis());
        return userResult;
    }*/
}
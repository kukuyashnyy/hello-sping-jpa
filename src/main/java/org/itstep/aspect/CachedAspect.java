package org.itstep.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.itstep.controller.domain.entity.Task;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
public class CachedAspect {

    Map<String, Object> map = new ConcurrentHashMap<>();

    @Pointcut("@annotation(Cached)")
    public void cachePointCut() {
    }

    @Around("cachePointCut()")
    public Object around(ProceedingJoinPoint jp) throws Throwable {

        Object retVal;
        String key = jp.getClass().getName() + " " + jp.getSignature().getName() + " " + Arrays.toString(jp.getArgs());

        if (map.containsKey(key)) {
            System.out.println("From cache");
            return map.get(key);
        } else {
            System.out.println("From db");
            retVal = jp.proceed();
            map.put(key, retVal);
        }

        return retVal;
    }
}

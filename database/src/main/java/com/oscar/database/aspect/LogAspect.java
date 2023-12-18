package com.oscar.database.aspect;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class LogAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.oscar.database.controller.*.*(..))")
    public void logs() {
    }

    @Before("logs()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String fullClassName = joinPoint.getSignature().getDeclaringTypeName();
        String controllerName = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
        String methodName = joinPoint.getSignature().getName();
        String requestURL = request.getRequestURL().toString();
        String apiName = requestURL.substring(requestURL.lastIndexOf("restfulapi/"));
        RequestLog requestLog = new RequestLog(
                apiName,
                request.getRemoteAddr(),
                controllerName + "." + methodName,
                joinPoint.getArgs());
        logger.info("Request --- {}", requestLog.toString());
    }

    @AfterReturning(returning = "result", pointcut = "logs()")
    public void doAfterReturning(ResponseEntity result) {
        logger.info("Returning --- {}", result.getBody());
    }

    @AllArgsConstructor
    @NoArgsConstructor
    private class RequestLog {
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}

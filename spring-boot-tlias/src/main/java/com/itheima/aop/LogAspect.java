package com.itheima.aop;

import com.alibaba.fastjson.JSONObject;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LogAspect {

//    @Autowired
//    private HttpServletRequest request;

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.itheima.anno.Log)")
    public Object recordLog(ProceedingJoinPoint pjp) throws Throwable {

//        String token = request.getHeader("token");
//        Claims claims = JwtUtils.parseJWT(token);
//        Integer operateUser = (Integer) claims.get("id");
        Integer operateUser = 1;

        // operateTime
        LocalDateTime operateTime = LocalDateTime.now();


        // class
        String className = pjp.getTarget().getClass().getName();

        // mehtod
        String methodName = pjp.getSignature().getName();

        // arg
        Object[] args = pjp.getArgs();
        String methodParams = Arrays.toString(args);



        Long begin = System.currentTimeMillis();
        Object res = pjp.proceed();
        String returnValue = JSONObject.toJSONString(res);

        Long end = System.currentTimeMillis();
        Long costTime = end - begin;

        //记录操作日志
        OperateLog operateLog = new OperateLog(null, operateUser, operateTime, className, methodName, methodParams, returnValue, costTime);
        operateLogMapper.insert(operateLog);

        log.info("AOP记录操作日志: {}", operateLog);

        return res;
    }
}

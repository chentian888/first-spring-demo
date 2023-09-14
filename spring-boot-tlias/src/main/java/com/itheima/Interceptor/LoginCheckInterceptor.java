package com.itheima.Interceptor;

import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("HandlerInterceptor preHandle");
        String url = request.getRequestURI();
        if (url.contains("/login")) {
            return HandlerInterceptor.super.preHandle(request, response, handler);
        } else {
            String token = request.getHeader("token");

//            token不存在
            if (token == null || token == "") {
                Result responseResult = Result.error("NOT_LOGIN");
                String json = JSONObject.toJSONString(responseResult);
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(json);
                return false;
            }

//            token解析失败
            try {
                JwtUtils.parseJWT(token);
            } catch (Exception e) {
                Result responseResult = Result.error("NOT_LOGIN");
                String json = JSONObject.toJSONString(responseResult);
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(json);
                return false;
            }
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("HandlerInterceptor postHandle");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("HandlerInterceptor afterCompletion");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}

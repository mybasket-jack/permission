package com.jack006.common;

import com.jack006.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 *  Http请求前后监听
 *
 * @Author jack
 * @Since 1.0 2020/1/26 14:25
 */
@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter {

    private static final String START_TIME = "requestStartTime";
    // 请求实现之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI().toString();
        Map<String, String[]> parameterMap = request.getParameterMap();
        long start = System.currentTimeMillis();
        request.setAttribute(START_TIME,start);
        log.info("request start, url:{}, params:{}",url, JsonMapper.obj2String(parameterMap));
        return true;
    }

    // 请求结束后进行（正常处理请求后）
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        String url = request.getRequestURI();
//        long start = (Long) request.getAttribute(START_TIME);
//        long end = System.currentTimeMillis();
//        log.info("request finished, url:{}, cost:{}",url, end - start );
    }

    // 请求结束后进行处理（任何情况都执行）
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String url = request.getRequestURI();
        long start = (Long) request.getAttribute(START_TIME);
        long end = System.currentTimeMillis();
        log.info("request completed, url:{}, cost:{}",url, end - start );    }
}

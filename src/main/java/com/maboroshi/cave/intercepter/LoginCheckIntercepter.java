package com.maboroshi.cave.intercepter;


import com.maboroshi.cave.pojo.Result;
import com.maboroshi.cave.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginCheckIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //拦截器取到请求先进行判断，如果是OPTIONS请求，则放行
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            System.out.println("OPTIONS请求，放行");
            return true;
        }

        String url = request.getRequestURI().toString();
        log.info("请求的信息：{}", url);

        if (url.contains("login")){
            log.info("登录操作，放行-------");
            return true;
        }

        String jwt = request.getHeader("Authorization");
        if (jwt != null && jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7);
        }

        if (!StringUtils.hasLength(jwt)){
            log.info(jwt);
            log.info("请求头为空，返回未登录状态");
            Result error = Result.error("NOT_LOGIN");
            String noLogin = com.alibaba.fastjson.JSONObject.toJSONString(error);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 设置响应状态为 401
            response.getWriter().write(noLogin);
            return false;
        }

        try {
            JWTUtils.ParseJWT(jwt);
        }catch (Exception e){
            e.printStackTrace();
            log.info("解析立牌失败，返回未登录状态");
            Result error = Result.error("NOT_LOGIN");
            String noLogin = com.alibaba.fastjson.JSONObject.toJSONString(error);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 设置响应状态为 401
            response.getWriter().write(noLogin);
            return false;
        }

        log.info("令牌合法，放行");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

}

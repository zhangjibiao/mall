package cn.stu.mall.portal.web.interceptor;

import cn.stu.mall.common.base.annotations.TokenCheck;
import cn.stu.mall.common.utils.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("\n---------拦截器---------");
        String token = request.getHeader("token");
        System.out.println(token);
        if (StringUtils.isBlank(token)){
            throw new LoginException("未登录！");
        }

        HandlerMethod methodhandle = (HandlerMethod) handler;
        Method method = methodhandle.getMethod();
        if(method.isAnnotationPresent(TokenCheck.class)){
            TokenCheck annotation = method.getAnnotation(TokenCheck.class);
            if(annotation.required()){
                try{
                    JwtUtil.parseToken(token);
                    return true;
                }catch (Exception e){
                    e.printStackTrace();
                    throw new LoginException("token错误，未登录");
                }
            }
        }

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

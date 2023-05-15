package com.spring.mvc.interceptor;

import com.spring.mvc.util.LoginUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// 로그인 이후 비회원 관련 페이지 진입 차단
// preHandle : 컨트롤러 진입 전 핸들링 한다.
// afterHandler : 컨트롤러
@Configuration
@Slf4j
public class AfterLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();

        if (LoginUtil.isLogin(session)) {
            response.sendRedirect("/");
            return false;
        }

        return true;
    }
}

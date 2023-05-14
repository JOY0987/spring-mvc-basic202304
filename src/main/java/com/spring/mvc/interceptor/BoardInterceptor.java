package com.spring.mvc.interceptor;

import com.spring.mvc.util.LoginUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 인터셉터 : 하위 컨트롤러에 요청이 들어가기 전, 후에
//          공통으로 검사할 일들을 정의해놓는 클래스
// 반드시 핸들러인터셉터를 구현해주어야 한다.
// 게시판 관련 인가 처리
@Configuration
@Slf4j
public class BoardInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {

        // 로그인을 했는지 확인할 것
        // 로그인을 안했으면 로그인 페이지로 강제 리다이렉션 할 것
        if (!LoginUtil.isLogin(request.getSession())) {
            log.info("this request( {} ) denied!!", request.getRequestURI());
            response.sendRedirect("/members/sign-in"); // 이거 없으면 하얀 화면으로 뜬다...
            return false;
        }

        log.info("board interceptor pass!!");
        return true; // 요청을 들여보내줌. false 면 요청을 내보낸다.
    }
}

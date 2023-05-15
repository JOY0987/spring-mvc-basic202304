package com.spring.mvc.interceptor;

import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.repository.BoardMapper;
import com.spring.mvc.util.LoginUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.spring.mvc.util.LoginUtil.getCurrentLoginMemberAccount;
import static com.spring.mvc.util.LoginUtil.isAdmin;

// 인터셉터 : 하위 컨트롤러에 요청이 들어가기 전, 후에
//          공통으로 검사할 일들을 정의해놓는 클래스
// 반드시 핸들러인터셉터를 구현해주어야 한다.
// 게시판 관련 인가 처리
@Configuration
@Slf4j
@RequiredArgsConstructor
public class BoardInterceptor implements HandlerInterceptor {

    private final BoardMapper boardMapper;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {

        // 로그인을 했는지 확인할 것
        // 로그인을 안했으면 로그인 페이지로 강제 리다이렉션 할 것
        HttpSession session = request.getSession();

        if (!LoginUtil.isLogin(request.getSession())) {
            log.info("this request( {} ) denied!!", request.getRequestURI());
            response.sendRedirect("/members/sign-in"); // 이거 없으면 하얀 화면으로 뜬다...
            return false;
        }

        // 삭제 요청을 한다면 자기가 쓴 글 또는 관리자인지 체크

        /*
            1. 우선 로그인 계정과 삭제하려는 게시물의 계정명이 일치해야 함
            2. 로그인 계정은 세션에서 구할 수 있음
            3. 삭제하려는 게시물의 계정은 어떻게 구함?
                -> 삭제 요청에는 글번호 정보만 있다.
                -> 글번호를 DB에 조회해서 계정명을 얻어낸다.
         */
        // 삭제 요청인지 확인
        String uri = request.getRequestURI();
        if (uri.contains("delete")) {

            // 쿼리 파라미터 읽기
            int bno = Integer.parseInt(request.getParameter("bno"));

            // 게시물 정보 읽기
            Board board = boardMapper.findOne(bno);

            String targetAccount = board.getAccount();

            // 관리자가 맞으면 isMine 검사 없이 통과
            if (isAdmin(session)) return true; 
            
            // 관리자가 아니면 내가 쓴 게시물인지 검사
            if (!isMine(session, targetAccount)) {
                response.sendRedirect("/access-deny");
                return false;
            }
        }

        log.info("board interceptor pass!!");
        return true; // 요청을 들여보내줌. false 면 요청을 내보낸다.
    }

    private boolean isMine(HttpSession session, String targetAccount) {
        return targetAccount.equals(getCurrentLoginMemberAccount(session));
    }
}

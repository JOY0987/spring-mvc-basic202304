package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.dto.request.LoginRequestDTO;
import com.spring.mvc.chap05.dto.request.SignUpRequestDTO;
import com.spring.mvc.chap05.service.LoginResult;
import com.spring.mvc.chap05.service.MemberService;
import com.spring.mvc.util.upload.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.spring.mvc.chap05.service.LoginResult.*;
import static com.spring.mvc.util.LoginUtil.isAutoLogin;
import static com.spring.mvc.util.LoginUtil.isLogin;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    @Value("${file.upload.root-path}")
    private String rootPath;

    private final MemberService memberService;

    // 회원 가입 요청
    // 회원 가입 양식 요청
    @GetMapping("/sign-up")
    public String signUp() {
        log.info("/member/sign-up GET - forwarding to jsp");
        return "members/sign-up";
    }

    // 회원 가입 처리 요청
    @PostMapping("/sign-up")
    public String signUp(SignUpRequestDTO dto) {
        log.info("/members/sign-up POST ! - {}", dto);
        log.info("프로필 사진 이름: {}", dto.getProfileImage().getOriginalFilename());

        MultipartFile profileImage = dto.getProfileImage();
        log.info("프로필사진 이름: {}", profileImage.getOriginalFilename());

        String savePath = null;

        if (!profileImage.isEmpty()) {
            // 실제 로컬 스토리지에 파일을 업로드하는 로직
            savePath = FileUtil.uploadFile(dto.getProfileImage(), rootPath);
        }

        boolean flag = memberService.join(dto, savePath);

        // 회원가입 후 로그인창으로 이동
        return "redirect:/members/sign-in";
    }

    // 아이디, 이메일 중복검사
    // 비동기 요청 처리
    @GetMapping("/check")
    @ResponseBody // 이것만 Restful 처리
    public ResponseEntity<?> check(String type, String keyword) {
        log.info("/members/check?type={}&keyword={} ASYNC GET!", type, keyword);

        boolean flag = memberService.checkSignUpValue(type, keyword);
        return ResponseEntity.ok().body(flag);
    }

    // 로그인 양식 요청
    @GetMapping("/sign-in")
    public String signIn() {
        log.info("/member/sign-in GET - forwarding to jsp");
        return "members/sign-in";
    }

    // 로그인 검증 요청
    @PostMapping("sign-in")
     public String signIn(LoginRequestDTO dto
                          // 리다이렉션 시 2번째 응답에 데이터를 보내기 위함! model 에 담으면 2번째 응답은 모른다.
                        , RedirectAttributes ra
                          // 응답할 때 정보를 실어 보내기 위함!
                        , HttpServletResponse response
                        , HttpServletRequest request
    ) {

        log.info("/member/sign-in POST ! - {}", dto);

        LoginResult result = memberService.authenticate(dto, request.getSession(), response);

        // 로그인 성공시
        if (result == SUCCESS) {

           /* 
           // 1. 쿠키 방식으로 로그인 유지
           // 쿠키 만들기 (로그인 성공 정보 기억)
            // javax servlet 으로
            Cookie loginCookie = new Cookie("login", "홍길동");
            // 쿠키 셋팅
            loginCookie.setPath("/"); // 쿠키 유효범위 설정 (보통 전체 다 들고다니게 함)
            loginCookie.setMaxAge(60 * 60 * 24); // 쿠키 수명 설정 (하루짜리)
            // 쿠키를 응답시에 실어서 클라이언트에게 전송
            response.addCookie(loginCookie);
            */
            
            // 2. 세션 방식으로 로그인 유지
            // 서버에서 세션에 로그인 정보를 저장
            memberService
                    .maintainLoginState(request.getSession(), dto.getAccount());

//            ra.addFlashAttribute("flag", true);
            return "redirect:/"; // 홈으로
        }

        // 첫번째 요청의 데이터 (명시적 요청)
        // addFlashAttribute : 1회용으로 쓰고 버릴 데이터에 사용
        ra.addFlashAttribute("msg", result);

        // 로그인 실패시
        return "redirect:/members/sign-in"; // 요청 URL
    }

    // 로그아웃 요청 처리
    @GetMapping("/sign-out")
    public String signOut(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        HttpSession session = request.getSession();

        // 로그인 중인지 확인
        if (isLogin(session)) {

            // 자동 로그인 상태라면 해제한다.
            if (isAutoLogin(request)) {
                memberService.autoLoginClear(request, response);
            }

            // 세션에서 login 정보를 제거
            session.removeAttribute("login");
//        HttpSession session = request.getSession(); // 브라우저에 저장된 세션 가져오기

            // 세션을 아예 초기화 (세션만료 시간)
            session.invalidate();
            return "redirect:/";
        }

        return "redirect:/members/sign-in";
    }
}

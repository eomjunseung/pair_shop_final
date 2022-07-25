package fianl.shop.api;

import fianl.shop.Result;
import fianl.shop.SessionConst;
import fianl.shop.domain.member.MemberService;
import fianl.shop.domain.member.login.LoginService;
import fianl.shop.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class LoginAPIController {

    private final MemberService memberService;
    private final LoginService loginService;

    @PostMapping("/login")
    public Result loginV4(@Valid @RequestBody LoginForm form,
                          HttpServletRequest request) throws Exception{

        Member loginMember = loginService.login(form.getName(), form.getPassword());

        if (loginMember == null) {
            return new Result<>("존재하지 않는 회원입니다.");
        }

        //로그인 성공 처리
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성
        HttpSession session = request.getSession();

        //세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);


        return new Result<>("로그인성공");
    }

    @PostMapping("/logout")
    public Result logoutV3(HttpServletRequest request) {
        //세션을 삭제한다.
        HttpSession session = request.getSession(false); //자동 생성 없이 있으면 받아와라
        if (session != null) { //있네?
            session.invalidate(); //없애
        }
        return new Result<>("로그아웃 성공");
    }
}

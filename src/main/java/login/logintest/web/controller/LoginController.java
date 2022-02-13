package login.logintest.web.controller;

import login.logintest.domain.model.Member;
import login.logintest.domain.repository.MemberRepository;
import login.logintest.domain.service.LoginService;
import login.logintest.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;
    private final LoginService loginService;

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
        String memberId = sessionManager.getSession(request);

        if(memberId == null) {
            return "login";
        }

        Optional<Member> findMemberOptional = memberRepository.findByMemberId(memberId);
        Member member = findMemberOptional.orElse(null);

        if(member == null) {
            return "login";
        }

        model.addAttribute("member", member);
        return "home";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Member member, HttpServletResponse response) {
        Member loginMember = loginService.login(member.getMemberId(), member.getPassword());

        if(loginMember == null) {
            return "redirect:/";
        }

        sessionManager.createSession(loginMember.getMemberId(), response);
        return "redirect:/";
    }

    @PostMapping("logout")
    public String logout(HttpServletRequest request) {
        //세션store에서 세션 remove
        sessionManager.sessionExpire(request);
        return "redirect:/";
    }
}

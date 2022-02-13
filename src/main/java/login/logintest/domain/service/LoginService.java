package login.logintest.domain.service;

import login.logintest.domain.repository.MemberRepository;
import login.logintest.domain.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(String memberId, String password) {
        return memberRepository.findByMemberId(memberId)
                .filter(member -> member.getPassword().equals(password))
                .orElse(null);
    }
}

package login.logintest;

import login.logintest.domain.model.Member;
import login.logintest.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class MemberInit {

    private final MemberRepository memberRepository;

    @PostConstruct
    public void memberInit() {
        Member member = new Member("hong-gildong", "1234");
        memberRepository.save(member);
    }
}

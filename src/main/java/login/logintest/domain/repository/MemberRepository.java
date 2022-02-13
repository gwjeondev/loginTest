package login.logintest.domain.repository;

import login.logintest.domain.model.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemberRepository {

    private static Map<Long, Member> store = new ConcurrentHashMap<>();
    private static long idSequence = 0;

    public void save(Member member) {
        store.put(++idSequence, member);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<Member> findByMemberId(String memberId) {
        return findAll().stream()
                .filter(member -> member.getMemberId().equals(memberId))
                .findFirst();
    }

    public void clear() {
        store.clear();
    }

}

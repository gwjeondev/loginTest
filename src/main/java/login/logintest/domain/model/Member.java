package login.logintest.domain.model;

import lombok.Data;

@Data
public class Member {
    private String memberId;
    private String password;

    public Member(String memberId, String password) {
        this.memberId = memberId;
        this.password = password;
    }
}

package com.example.security_demo.member;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Member {

    private Integer memberId;

    private String username;

    private String password;

    public Member(Integer memberId, String username, String password) {
        this.memberId = memberId;
        this.username = username;
        this.password = password;
    }

}

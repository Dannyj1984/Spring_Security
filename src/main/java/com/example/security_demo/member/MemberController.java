package com.example.security_demo.member;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/members")
public class MemberController {


    //A list of new members
    private static final List<Member> MEMBERS = Arrays.asList(
            new Member(1, "dannyjebb", "P4ssword"),
            new Member(2, "katyjebb", "P4ssword"),
            new Member(3, "emilyjebb", "P4ssword")
    );

    //path the view member details after the original path in requestmapping
    @GetMapping(path = "{memberId}")
    public Member getMember(@PathVariable("memberId") Integer memberId){
        return MEMBERS.stream().filter(member -> memberId.equals(member.getMemberId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Member " + memberId   + " does not exist" ));
    }
}

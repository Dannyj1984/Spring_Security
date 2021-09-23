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
            new Member("dannyjebb", "Danny", "Jebb", "dannyjebb@gmail.com", "USER", 4.0, 0,4.0,0,0,"P4ssword"),
            new Member("mikedobson", "Mike", "Dobson", "mikedobson@gmail.com", "ADMIN", 18.7, 0,18.7,0,0,"P4ssword"),
            new Member("dancross", "Dan", "Cross", "dancross@gmail.com", "EVENTADMIN", 8.2, 0,8.2,0,0,"P4ssword"),
            new Member("leeoconnell", "Lee", "Oconnell", "leeoconnell@gmail.com", "HADNICAPADMIN", 14.2, 0,14.2,0,0,"P4ssword")
    );

    @GetMapping
    public List<Member> getMembers() {
        return MEMBERS;
    }

    //path the view member details after the original path in requestmapping
    @GetMapping(path = "{memberId}")
    public Member getMember(@PathVariable("memberId") Integer memberId){
        return MEMBERS.stream().filter(member -> memberId.equals(member.getMemberId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Member " + memberId   + " does not exist" ));
    }
}

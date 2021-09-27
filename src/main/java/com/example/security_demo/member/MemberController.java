package com.example.security_demo.member;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/members")
public class MemberController {

private MemberRepository memberRepository;

    //A list of new members
    private static final List<Member> MEMBERS = Arrays.asList(
            new Member("dannyjebb", "Danny", "Jebb", "dannyjebb@gmail.com", "USER",4.0, 0,4.0, 0, "07777777777", "1013500000",0,"P4ssword"),
            new Member("mikedobson", "Mike", "Dobson", "mikedobson@gmail.com", "ADMIN", 18.7, 0,18.7,0, "07777777777", "1013500000",0,"P4ssword"),
            new Member("dancross", "Dan", "Cross", "dancross@gmail.com", "EVENTADMIN", 8.2, 0,8.2,0, "07777777777", "1013500000",0,"P4ssword"),
            new Member("leeoconnell", "Lee", "O'connell", "leeoconnell@gmail.com", "HANDICAPADMIN", 14.2, 0,14.2,0,"07777777777", "1013500000", 0,"P4ssword")
    );


    @GetMapping
    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    //path the view member details after the original path in requestmapping
    @GetMapping(path = "{memberId}")
    ResponseEntity<?> getMember(@PathVariable("memberId") Long memberId){
        Optional<Member> member = memberRepository.findById(memberId);
        return member.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

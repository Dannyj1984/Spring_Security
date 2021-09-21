package com.example.security_demo.member;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/members")
public class MemberManagementController {

    //A list of new members
    private static final List<Member> MEMBERS = Arrays.asList(
            new Member(1, "dannyjebb", "P4ssword"),
            new Member(2, "katyjebb", "P4ssword"),
            new Member(3, "emilyjebb", "P4ssword")
    );

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')") //pre authorise takes a string of hasRole, hasAnyRole etc
    public List<Member> getAllMembers() {
        System.out.println("getAllMembers");
        return MEMBERS;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('member:write')")
    public void registerNewMember(@RequestBody Member member) {
        System.out.println("registerNewMember");
        System.out.println(member);
        //TODO logic to register new member
    }

    @DeleteMapping(path = "{memberId}")
    @PreAuthorize("hasAuthority('member:write')")
    public void deleteMember(@PathVariable("memberId") Integer memberId) {
        System.out.println("deleteMember");
        System.out.println(memberId);
        //TODO logic to delete member
    }

    @PutMapping(path = "{memberId}")
    @PreAuthorize("hasAuthority('member:write')")
    public void updateMember(@PathVariable("memberId") Integer memberId, @RequestBody Member member) {
        System.out.println("updateMember");
        System.out.println(String.format("%s %s", memberId, member));
    }
}

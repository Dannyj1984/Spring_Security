package com.example.security_demo.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/members")
public class MemberManagementController {

    //A list of new members
    private static final List<Member> MEMBERS = Arrays.asList(
            new Member("dannyjebb", "Danny", "Jebb", "dannyjebb@gmail.com", "USER",4.0, 0,4.0, 0, "07777777777", "1013500000",0,"P4ssword"),
            new Member("mikedobson", "Mike", "Dobson", "mikedobson@gmail.com", "ADMIN", 18.7, 0,18.7,0, "07777777777", "1013500000",0,"P4ssword"),
            new Member("dancross", "Dan", "Cross", "dancross@gmail.com", "EVENTADMIN", 8.2, 0,8.2,0, "07777777777", "1013500000",0,"P4ssword"),
            new Member("leeoconnell", "Lee", "O'connell", "leeoconnell@gmail.com", "HANDICAPADMIN", 14.2, 0,14.2,0,"07777777777", "1013500000", 0,"P4ssword")
    );

    @Autowired
    MemberRepository memberRepository;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HANDICAPADMIN')") //pre authorise takes a string of hasRole, hasAnyRole etc
    public List<Member> getAllMembers() {
        System.out.println("getAllMembers");
        return MEMBERS;
    }

    @GetMapping(path = "{memberId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_HANDICAPADMIN')")
    public Member getMember(@PathVariable("memberId") Integer memberId){
        return MEMBERS.stream().filter(member -> memberId.equals(member.getMemberId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Member " + memberId   + " does not exist" ));
    }

    @PutMapping(path = "edit/{memberId}")
    @PreAuthorize("hasAuthority('member:write')")
    public void updateMember(@PathVariable("memberId") Integer memberId, @RequestBody Member member) {
        System.out.println("updateMember");
        System.out.println(String.format("%s %s", memberId, member));
    }

    @PostMapping("/process_register")
    @PreAuthorize("HasAuthority('member:write')")
    public String processRegister(Member user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        memberRepository.save(user);

        return "register_success";
    }

    @DeleteMapping(path = "{memberId}")
    @PreAuthorize("hasAuthority('member:write')")
    public void deleteMember(@PathVariable("memberId") Integer memberId) {
        System.out.println("deleteMember");
        System.out.println(memberId);
        //TODO logic to delete member
    }


}

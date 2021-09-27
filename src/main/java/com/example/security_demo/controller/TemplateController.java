package com.example.security_demo.controller;

import com.example.security_demo.member.Member;
import com.example.security_demo.member.MemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class TemplateController {

    MemberRepository memberRepository;

    @GetMapping("login")
    public String getLoginView() {
        return "login"; // same name as the html file in templates
    }

    @GetMapping("courses")
    public String getCourses() {
        return "courses"; // same name as the html file in templates
    }



}

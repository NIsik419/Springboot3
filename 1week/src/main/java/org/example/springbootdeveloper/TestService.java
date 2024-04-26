package org.example.springbootdeveloper;

import org.example.springbootdeveloper.MemberRepository;
import org.example.springbootdeveloper.Testcontroller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TestService{ //비즈니스 계층
    @Autowired
    MemberRepository memberRepository;

    public List<Member> getAllMembers()
    {
        return memberRepository.findAll();
    }


}
package org.example.springbootdeveloper.service;
import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.example.springbootdeveloper.domain.User;

@RequiredArgsConstructor
@Service
//스프링 시큐리티에서 사용자 정보가져오는 인터페이스
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    //사용자 email로 사용자의 정보를 가져오는 메서드
    @Override
    public User loadUserByUsername(String email)
    {
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new IllegalArgumentException((email)));
    }

}

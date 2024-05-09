package org.example.springbootdeveloper.domain;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.*;

@Table(name="users")
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Entity
public class User implements UserDetails {
    //UserDetails를 상속받아 인증객체로 사용
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id",updatable=false)
    private Long id;

    @Column(name="email",nullable = false,unique = true)
    private String email;

    @Column(name="password")
    private String password;
    @Builder
    public User(String email,String password,String auth)

    {
        this.email=email;
        this.password=password;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("user"));
    }
    @Override
    public String getUsername(){
        return email;
    }
    @Override
    public String getPassword(){
        return password;
    }
    @Override
    public boolean isAccountNonExpired(){
        //만료확인 로진, true 만료x
        return true;
    }
    @Override
    public boolean isAccountNonLocked(){
        //계정 잠금 확인 로직
        return true;// true->잠금 x

    }
    @Override
    public boolean isCredentialsNonExpired(){
        //패스워드 만료확인
        return true;
    }
    @Override
    public boolean isEnabled(){
        //계정 사용가능 여부 확인
        return true;
    }
}

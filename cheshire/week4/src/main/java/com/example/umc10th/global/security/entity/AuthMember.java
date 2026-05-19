package com.example.umc10th.global.security.entity;

import com.example.umc10th.domain.member.entity.Member;
import jakarta.annotation.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AuthMember implements UserDetails {
    private final Member member;

    public AuthMember(Member member) {
        this.member = member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of();
    }

    @Override
    public @Nullable String getPassword(){
        return member.getPassword();
    }

    @Override
    public String getUsername(){
        return member.getEmail();
    }
}

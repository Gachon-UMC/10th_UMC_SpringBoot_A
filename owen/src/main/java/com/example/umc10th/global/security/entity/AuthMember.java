package com.example.umc10th.global.security.entity;

import com.example.umc10th.domain.member.entity.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Spring Security 가 인증된 사용자를 표현할 때 쓰는 UserDetails 구현체.
 * SecurityContextHolder.getContext().getAuthentication().getPrincipal() 결과가 이 객체.
 */
@Getter
@RequiredArgsConstructor
public class AuthMember implements UserDetails {

    private final Member member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 권한 미사용 (필요 시 ROLE_USER, ROLE_ADMIN 등 추가)
        return List.of();
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        // 폼 로그인 식별자로 email 사용
        return member.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() { return member.getDeletedAt() == null; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return member.getDeletedAt() == null; }
}

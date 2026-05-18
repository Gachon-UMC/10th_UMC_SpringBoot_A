package com.umc10th.umc10th_kamang.global.security;

import com.umc10th.umc10th_kamang.domain.user.entity.User;
import com.umc10th.umc10th_kamang.domain.user.enums.UserStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class AuthMember implements UserDetails {

    // Spring Security 인증 객체 안에서 프로젝트 User 엔티티 함께 보관
    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 별도 권한 테이블이 없으므로 모든 로그인 사용자를 일반 사용자 권한으로 처리
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 탈퇴/비활성 사용자는 로그인할 수 없도록 회원 상태 반영
        return user.getStatus() == UserStatus.ACTIVE;
    }
}

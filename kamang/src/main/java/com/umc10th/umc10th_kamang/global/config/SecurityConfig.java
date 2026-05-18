package com.umc10th.umc10th_kamang.global.config;

import com.umc10th.umc10th_kamang.global.security.CustomAccessDeniedHandler;
import com.umc10th.umc10th_kamang.global.security.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    // 회원가입/로그인/Swagger는 로그인 없이 접근 가능한 Public API로 둠
    private static final String[] PUBLIC_URIS = {
            "/api/auth/**",
            "/login",
            "/logout",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/v3/api-docs/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // API 서버 형태로 사용하므로 CSRF 보호 비활성화
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(PUBLIC_URIS).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        // Spring Security 기본 username 파라미터 대신 미션 요구사항의 email 사용
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/swagger-ui/index.html", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        // 필터 단계 인증/인가 실패도 기존 ApiResponse 형태로 반환
                        .authenticationEntryPoint(customAuthenticationEntryPoint)
                        .accessDeniedHandler(customAccessDeniedHandler)
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

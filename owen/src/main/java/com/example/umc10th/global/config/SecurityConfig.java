package com.example.umc10th.global.config;

import com.example.umc10th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc10th.global.security.CustomAccessDenied;
import com.example.umc10th.global.security.CustomEntryPoint;
import com.example.umc10th.global.security.SecurityResponseWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    // 인증 없이 접근 가능한 Public 경로
    private static final String[] ALLOW_URIS = {
            // Swagger
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            // 8주차 미션: 회원가입 API만 Public API
            "/auth/signup",
            // Spring Security 폼 로그인 화면
            "/login"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // REST API 환경 — CSRF 토큰 불필요
                .csrf(AbstractHttpConfigurer::disable)

                // URL 별 접근 제어
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(ALLOW_URIS).permitAll()
                        .anyRequest().authenticated()
                )

                // 폼 로그인 (GET /login 으로 기본 페이지, POST /login 으로 인증)
                .formLogin(form -> form
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/swagger-ui/index.html", true)
                        .failureHandler((request, response, exception) ->
                                SecurityResponseWriter.write(response, GeneralErrorCode.UNAUTHORIZED))
                        .permitAll()
                )

                // 로그아웃
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )

                // 인증/인가 실패 시 응답 통일
                .exceptionHandling(ex -> ex
                        .accessDeniedHandler(customAccessDenied())
                        .authenticationEntryPoint(customEntryPoint())
                );

        return http.build();
    }

    // 비밀번호 솔트 처리 (BCrypt)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomAccessDenied customAccessDenied() {
        return new CustomAccessDenied();
    }

    @Bean
    public CustomEntryPoint customEntryPoint() {
        return new CustomEntryPoint();
    }
}

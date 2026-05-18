package com.umc10th.umc10th_kamang.global.security;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    // Security 기본 로그인 페이지가 비활성화되는 환경에서도 폼 로그인 테스트 가능하도록 직접 제공
    @ResponseBody
    @GetMapping(value = "/login", produces = MediaType.TEXT_HTML_VALUE)
    public String login() {
        return """
                <!DOCTYPE html>
                <html lang="ko">
                <head>
                    <meta charset="UTF-8">
                    <title>로그인</title>
                </head>
                <body>
                    <h1>로그인</h1>
                    <form method="post" action="/login">
                        <div>
                            <label for="email">이메일</label>
                            <input type="text" id="email" name="email">
                        </div>
                        <div>
                            <label for="password">비밀번호</label>
                            <input type="password" id="password" name="password">
                        </div>
                        <button type="submit">로그인</button>
                    </form>
                </body>
                </html>
                """;
    }
}

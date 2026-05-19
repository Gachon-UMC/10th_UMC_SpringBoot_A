package com.example.umc10th.global.security;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginPageController {

    @ResponseBody
    @GetMapping(value = "/login", produces = MediaType.TEXT_HTML_VALUE)
    public String loginPage() {
        return """
                <!DOCTYPE html>
                <html lang="ko">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Login</title>
                    <style>
                        body {
                            margin: 0;
                            min-height: 100vh;
                            display: flex;
                            align-items: center;
                            justify-content: center;
                            font-family: Arial, sans-serif;
                            background: #f5f6f8;
                        }
                        form {
                            width: 320px;
                            padding: 28px;
                            border: 1px solid #d9dde3;
                            background: #ffffff;
                        }
                        h1 {
                            margin: 0 0 24px;
                            font-size: 24px;
                        }
                        label {
                            display: block;
                            margin-bottom: 8px;
                            font-size: 14px;
                            font-weight: 600;
                        }
                        input {
                            width: 100%;
                            box-sizing: border-box;
                            margin-bottom: 16px;
                            padding: 10px;
                            border: 1px solid #c7ccd4;
                            font-size: 14px;
                        }
                        button {
                            width: 100%;
                            padding: 11px;
                            border: 0;
                            background: #24292f;
                            color: #ffffff;
                            font-size: 15px;
                            cursor: pointer;
                        }
                    </style>
                </head>
                <body>
                    <form method="post" action="/login">
                        <h1>Login</h1>
                        <label for="email">Email</label>
                        <input id="email" name="email" type="email" autocomplete="username" required>
                        <label for="password">Password</label>
                        <input id="password" name="password" type="password" autocomplete="current-password" required>
                        <button type="submit">Sign in</button>
                    </form>
                </body>
                </html>
                """;
    }
}

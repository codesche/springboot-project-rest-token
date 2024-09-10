package com.kosta.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosta.config.JwtProvider;
import com.kosta.domain.LoginResponse;
import com.kosta.entity.User;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TokenUtils {

    private final JwtProvider jwtProvider;

    // 토큰 생성
    public Map<String, String> generateToken(User user) {
        String accessToken = jwtProvider.generateAccessToken(user);

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("accessToken", accessToken);
        return tokenMap;
    }

    // JSON 응답 전송
    public void writeResponse(HttpServletResponse response, LoginResponse loginResponse) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(loginResponse);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
    }

}

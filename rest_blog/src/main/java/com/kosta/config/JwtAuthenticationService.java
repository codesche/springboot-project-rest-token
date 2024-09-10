package com.kosta.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosta.domain.LoginResponse;
import com.kosta.entity.User;
import com.kosta.util.TokenUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtAuthenticationService {

    private final TokenUtils tokenUtils;

    void successAuthentication(HttpServletResponse response, Authentication authResult) throws IOException {
        User user = (User) authResult.getPrincipal();
        Map<String, String> tokenMap = tokenUtils.generateToken(user);
        String accessToken = tokenMap.get("accessToken");                   // 토큰 가져오기

        LoginResponse loginResponse = LoginResponse.builder()
                .accessToken(accessToken)
                .build();
        tokenUtils.writeResponse(response, loginResponse);
    }

}

package org.scoula.security.util;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.scoula.security.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class, SecurityConfig.class})
@Log4j2
class JwtProcessorTest {

    @Autowired
    JwtProcessor jwtProcessor;

    @Test
    void generateToken() {

        // 테스트에사용할 username
        String username = "user0";
        String role = "ROLE_ADMIN";

        String token = jwtProcessor.generateToken(username, role);

        // eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMCIsImlhdCI6MTc1MDc0NzQ3NywiZXhwIjoxNzUwNzQ3Nzc3fQ.cm2dHIUln8iMNXgWsSRwB8X39MyIvOmrHExML78RuLc
        log.info("생성된 토큰 : {}", token);
    }

    @Test
    void validateToken_Valid() {
        String username = "testuser";
        String role = "ROLE_ADMIN";

        // 토큰 생성
        String token = jwtProcessor.generateToken(username, role);

        log.info("생성된 토큰 : {}", token);

        // 토큰 검증
        boolean isValid = jwtProcessor.validateToken(token);

        log.info("검증 결과(true/false) : {}", isValid);

        assertTrue(isValid, "새로 생성된 토큰 유효함 확인");
    }

    @Test
    void validateToken_Expire() {
        // 만료된 토큰
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMCIsImlhdCI6MTc1MDc0NzQ3NywiZXhwIjoxNzUwNzQ3Nzc3fQ.cm2dHIUln8iMNXgWsSRwB8X39MyIvOmrHExML78RuLc";

        // 토큰 검증
        boolean isValid = jwtProcessor.validateToken(token);

        log.info("검증 결과(true/false) : {}", isValid);

        assertFalse(isValid, "만료된 토큰은 무효해야함.");
    }

    @Test
    void validateToken_Invalid() {
        // 이상한 토큰 (잘못된 형식)
        String token = "invalid.jwt.token";

        // 토큰 검증
        boolean isValid = jwtProcessor.validateToken(token);

        log.info("검증 결과(true/false) : {}", isValid);

        assertFalse(isValid, "잘못된 형식의 토큰은 무효해야함.");
    }
}
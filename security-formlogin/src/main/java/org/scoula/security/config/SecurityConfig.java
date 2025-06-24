package org.scoula.security.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Log4j2
@ComponentScan(basePackages = {"org.scoula.security"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    // 문자셋 필터
    public CharacterEncodingFilter encodingFilter() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        return encodingFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(encodingFilter(), CsrfFilter.class);

        // 경로별 접근 권한 설정
        http.authorizeRequests()
            .antMatchers("/security/all").permitAll()
            .antMatchers("/security/admin").access("hasRole('ROLE_ADMIN')")
            .antMatchers("/security/member").access("hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')");

        http.formLogin()   // form 기반 로그인 활성화
            .loginPage("/security/login")   // 사용자가 보게 될 로그인 페이지 (우리가 만든거)
            .loginProcessingUrl("/security/login")  // 로그인 폼에서 제출되는 URL(POST)
            .defaultSuccessUrl("/");

        http.logout()
            .logoutUrl("/security/logout")
            .invalidateHttpSession(true)
            .deleteCookies("JSESSION-ID")
            .logoutSuccessUrl("/security/logout");
    }

    /*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        log.info("configure .........................................");
        auth.inMemoryAuthentication()
            .withUser("admin")
//            .password("{noop}1234")     // security는 기본적으로 비밀번호 암호화 필수
            .password("$2a$10$EsIMfxbJ6NuvwX7MDj4WqOYFzLU9U/lddCyn0nic5dFo3VfJYrXYC")
            // -> {noop} 으로 예외 시키기
            .roles("ADMIN", "MEMBER"); // ROLE_ADMIN

        auth.inMemoryAuthentication()
            .withUser("member")
            .password("$2a$10$EsIMfxbJ6NuvwX7MDj4WqOYFzLU9U/lddCyn0nic5dFo3VfJYrXYC")
            .roles("MEMBER"); // ROLE_MEMBER

    }
     */

    // 직접 만든 userDetailsService를 이용해 진증을 진행하도록 설정
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());
    }
}

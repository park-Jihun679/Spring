package org.scoula.security.account.domain;

import java.util.Collection;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Getter
@Setter
public class CustomUser extends User {

    private MemberVO member;    // 실질적 사용자 데이터

    // 권한 가져오기
    public CustomUser(String username, String password,
        Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public CustomUser(MemberVO vo) {
        super(vo.getUsername(), vo.getPassword(), vo.getAuthList());
        this.member = vo;
    }
}

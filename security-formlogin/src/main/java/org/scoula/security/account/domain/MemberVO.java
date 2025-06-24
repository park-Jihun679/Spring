package org.scoula.security.account.domain;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {

    private String username;
    private String password;
    private String email;
    private Date regDate;
    private Date updateDate;

    private List<AuthVO> authList;
}

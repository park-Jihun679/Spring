package org.scoula.domain;

import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardVO {

    private int no;
    private String title;
    private String content;
    private String writer;
    private Date regDate;
    private Date updateDate;
}

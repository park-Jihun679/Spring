package org.scoula.config;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
class RootConfigTest {

    // 주입 3가지 --> 필드 위에 주입!
    @Autowired
    private DataSource dataSource;

    @Test
    void dataSource() throws SQLException {
        try(Connection con = dataSource.getConnection()) {
            log.info("dbcp로 부터 연결 하나 가지고 왔음");
            log.info(con);
        }
    }
}
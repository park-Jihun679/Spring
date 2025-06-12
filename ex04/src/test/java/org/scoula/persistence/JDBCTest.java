package org.scoula.persistence;

import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Log4j2
public class JDBCTest {

    // 여기에 여러 설정 잘 되었는지 함수 만들어서 테스트 할 예정
    // 테스트 단위마다 함수 하나 만들어주면 됨.!
    // 함수 단위마다 테스트 해 준다고 해서 단위 테스트
    // JUnit 라이브러리 사용 예정

    // 모든 단위테스트에서 사용할 드라이버를 설정하는 부분이 필요

    // 아래 모든 함수 호출 전에 무조건 이거 한 번 실행해줘 라고 할 때
    // @BeforeAll --> 시작할 때 한 번 실행함
    @BeforeAll
    public static void setUp() {

        // 클래스 파일을 연결 (예외처리, try-catch)
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();    // 상세한 에러 정보를 프린트 할 수 있다.
        }
    }

    @Test
    @DisplayName("JDBC 드라이버 연결이 된다.")
    public void testConnection() {
        String url = "jdbc:mysql://localhost:3306/scoula_db";
        try (Connection con = DriverManager.getConnection(url, "scoula", "1234")) {
            log.info(con);
        } catch (Exception e) {
             fail(e.getMessage()); //==> 명시적으로 fail임을 알려주기 위함. 출력+fail했음을 전달함.
            System.out.println(e.getMessage());
            // 연결이 실패하면 예외 메시지를 출력하고 테스트 실패 처리
        }
    }
}

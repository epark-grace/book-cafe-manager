package config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConfigTest {

    Logger logger = LoggerFactory.getLogger("test");

    @Test
    @DisplayName("DB 연결: Connection 객체 Null 테스트")
    void dataSource_connectionIsNotNull() {
        DatabaseConfig config = new DatabaseConfig();
        DataSource dataSource = config.dataSource();
        try(Connection connection = dataSource.getConnection()){
            assertNotNull(connection);
        } catch (SQLException e) {
            logger.error("DB 연결 실패: ", e);
        }
    }
}
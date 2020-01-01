package enotes.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcConnectionManagerTest {

    @Autowired
    private ConnectionManager connectionManager;

    @Test
    public void mustReturnValidConnection() throws SQLException {
        Connection connection = connectionManager.getConnection();
        assertNotNull("Returned connection must be not null.",connection);
        assertTrue("Returned connection must be valid.", connection.isValid(0));
    }
}

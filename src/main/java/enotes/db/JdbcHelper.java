package enotes.db;

import lombok.extern.log4j.Log4j2;

import java.sql.ResultSet;
import java.sql.SQLException;

@Log4j2
public class JdbcHelper {

    public static void closeResultSet(ResultSet set) {
        try {
            if (set == null || set.isClosed()) {
                return;
            }

            set.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}

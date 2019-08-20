package com.enotes.db;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

public class ConnectionPoolImplTest {

    private static final String URL = "jdbc:h2:mem:myDb;DB_CLOSE_DELAY=-1";

    @Test
    public void shouldCreateConnectionPoolWithConnectionsList() throws SQLException
    {
        ConnectionPool pool = ConnectionPoolImpl.create(URL, "", "");
        Assert.assertEquals(10, pool.getSize());
    }
}

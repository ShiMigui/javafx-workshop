package model.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public final class Database {
    private static Connection con;

    private Database() {
    }

    public static Connection getConnection() throws SQLException {
	if (con == null) {
	    Properties props = loadProps();
	    con = DriverManager.getConnection(props.getProperty("url"), props);
	}
	return con;
    }

    private static Properties loadProps() {
	try (FileInputStream fs = new FileInputStream("db.props")) {
	    Properties props = new Properties();
	    props.load(fs);
	    return props;
	} catch (FileNotFoundException e) {
	    throw new DatabaseException("Database properties file not found", e.getMessage());
	} catch (IOException e) {
	    throw new DatabaseException("Unexpected error", e.getMessage());
	}
    }

    public static void closeConnection() {
	try {
	    con.close();
	} catch (SQLException e) {
	    throw new DatabaseException("Cannot close connection", e.getMessage());
	}
    }

    public static void close(Statement st, ResultSet rs) {
	try {
	    if (rs != null && !rs.isClosed())
		rs.close();
	    if (st != null && !st.isClosed())
		st.close();
	} catch (SQLException e) {
	    throw new DatabaseException("Cannot close", e.getMessage());
	}
    }
}

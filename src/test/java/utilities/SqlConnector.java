package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class SqlConnector {
    private static final Logger LOGGER = LogManager.getLogger(SqlConnector.class);
    static String dBName = ReadConfigFiles.getPropertyValues("DbName");
    private static final String DbUrl = String.format("jdbc:postgresql://localhost:5432/%s", dBName);
    private static final String User = ReadConfigFiles.getPropertyValues("DBUser");
    private static final String Password = ReadConfigFiles.getPropertyValues("DbPassword");

    /***
     * Connect to the PostgreSQL database
     * @return a Connection object
     */

    private static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DbUrl, User, Password);
            LOGGER.info("Connected to the PostgreSQL server successfully");
        }catch (SQLException e){
            LOGGER.error("SQL Connection Exception: " + e.getMessage());
        }
        return conn;
    }

    public static ResultSet readData(String SQL) {
        ResultSet rs = null;
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);
        }catch (SQLException e) {
            LOGGER.error("SQL ResultSet Exception: " + e.getMessage());
        }
        return rs;
    }
}

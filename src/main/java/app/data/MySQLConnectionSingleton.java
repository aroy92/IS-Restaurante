package app.data;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnectionSingleton {
  private static Connection conn;
  private static final String DB_USER = "root";
  private static final String DB_PASSWD = "";

  private MySQLConnectionSingleton() {
  }

  public static Connection getInstance() {
    if (conn == null) {
      try {
        Properties prop = new Properties();
        InputStream is = ClassLoader.getSystemResourceAsStream("config.properties");
        if (is == null) {
          System.out.println("config.properties file not found");
          return null;
        }
        prop.load(is);
        final String connectionString = "jdbc:mysql://" + prop.getProperty("db.url") + ":" + prop.getProperty("db.port")
            + "/" + prop.getProperty("db.database") + "?user=" + DB_USER + "&password=" + DB_PASSWD;
        conn = DriverManager.getConnection(connectionString);
        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        return conn;
      } catch (IOException ex) {
        System.out.println("Cannot open config.properties file: " + ex.getMessage());
        return null;
      } catch (SQLException ex) {
        System.out.println("SQLException: " + ex.getMessage());
        System.out.println("SQLState: " + ex.getSQLState());
        System.out.println("VendorError: " + ex.getErrorCode());
        return null;
      }
    }
    return conn;
  }

}

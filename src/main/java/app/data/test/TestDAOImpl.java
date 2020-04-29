package app.data.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.data.MySQLConnectionSingleton;

public class TestDAOImpl implements TestDAO {

  private Connection conn;

  public TestDAOImpl() {
    conn = MySQLConnectionSingleton.getInstance();
  }

  @Override
  public List<TestTransfer> findTests() {
    try {
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM tests ORDER BY date DESC");
      List<TestTransfer> list = new ArrayList<>();
      TestTransfer test;
      while (rs.next()) {
        test = new TestTransfer();
        test.setId(rs.getLong("id"));
        test.setName(rs.getString("name"));
        test.setDate(rs.getTimestamp("date"));
        list.add(test);
        test = null;
      }
      return list;
    } catch (SQLException ex) {
      return null;
    }
  }

  @Override
  public boolean deleteTest(long id) {
    try {
      conn.createStatement().executeUpdate("DELETE FROM tests WHERE id = " + id);
      return true;
    } catch (SQLException ex) {
      return false;
    }
  }

}

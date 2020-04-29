package app.controller;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import app.data.MySQLConnectionSingleton;
import app.sa.ApplicationServiceFactory;
import app.sa.test.TestASImpl;
import app.view.View;

public class Controller {

  private TestASImpl testAS;

  private JFrame frame;

  public Controller() {
    if (MySQLConnectionSingleton.getInstance() == null) {
      initError();
    } else {
      testAS = ApplicationServiceFactory.getTestInstance();
      initWiew();
    }
  }

  private void initError() {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        JOptionPane.showMessageDialog(null, "Error al conectarse a la base de datos", "Error",
            JOptionPane.ERROR_MESSAGE);
        System.exit(1);
      }
    });
  }

  private void initWiew() {
    final Controller c = this;
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        frame = new View("Prueba", c);
        frame.setVisible(true);
      }
    });
  }

  public void closeApp() {
    Connection conn = MySQLConnectionSingleton.getInstance();
    if (conn != null) {
      try {
        conn.close();
      } catch (SQLException ex) {
      }
    }
  }

  public void showError(String msg) {
    JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
  }

  /*
   * TEST METHODS
   */

  public void deleteTest(long id) {
    testAS.deleteTest(id);
  }

}

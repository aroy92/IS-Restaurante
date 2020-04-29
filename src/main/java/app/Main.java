package app;

import app.controller.Controller;

public class Main {

  public static void main(String[] args) {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
      new Controller();
    } catch (Exception ex) {

    }

  }

}

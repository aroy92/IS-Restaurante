package app.data.test;

import java.util.Date;

public class TestTransfer {

  private long id;
  private String name;
  private Date date;

  public TestTransfer() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(java.sql.Timestamp ts) {
    Date d = new Date(ts.getTime());
    this.date = d;
  }

}

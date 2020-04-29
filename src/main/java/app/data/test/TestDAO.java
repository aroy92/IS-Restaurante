package app.data.test;

import java.util.List;

public interface TestDAO {
  public List<TestTransfer> findTests();

  public boolean deleteTest(long id);
}

package app.sa.test;

import java.util.List;

import app.data.test.TestTransfer;

public interface TestAS {
  public List<TestTransfer> getTests();

  public void deleteTest(long id);
}

package app.as.test;

import java.util.List;

import app.data.test.TestDAOImpl;
import app.data.test.TestTransfer;
import app.observer.Observable;

public class TestASImpl extends Observable implements TestAS {

  private TestDAOImpl testDAO;

  public TestASImpl() {
    testDAO = new TestDAOImpl();
  }

  @Override
  public List<TestTransfer> getTests() {
    return testDAO.findTests();
  }

  @Override
  public void deleteTest(long id) {
    if (testDAO.deleteTest(id)) {
      notifyObservers();
    }
  }

}

package app.sa;

import app.sa.test.TestASImpl;

public class ApplicationServiceFactory {

  private static TestASImpl testAS;

  private ApplicationServiceFactory() {
  }

  public static TestASImpl getTestInstance() {
    if (testAS == null)
      testAS = new TestASImpl();
    return testAS;
  }

}

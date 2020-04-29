package app.observer;

import java.util.LinkedList;
import java.util.List;

public abstract class Observable {

  private List<Observer> observers;

  public Observable() {
    observers = new LinkedList<Observer>();
  }

  public void addObserver(Observer o) {
    observers.add(o);
  }

  public void removeObserver(Observer o) {
    observers.remove(o);
  }

  public void notifyObservers() {
    for (Observer observer : observers) {
      observer.update();
    }
  }
}

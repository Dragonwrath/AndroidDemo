package com.example.concurrent.basic.tutorial;

public class TransferMoneyTest {
  private static final Object tieLock = new Object();

  public void transferMoney(final Account fromAccount,
                            final Account toAccount,
                            final DollarAmount amount) {
    class Helper {
      public void transfer(){

      }
    }

    int fromHash = System.identityHashCode(fromAccount);
    int toHash = System.identityHashCode(toAccount);
    if (fromHash > toHash) {
      synchronized (fromAccount){
        synchronized (toAccount) {
          new Helper().transfer();
        }
      }
    } else if (toHash > fromHash) {
      synchronized (toAccount) {
        synchronized (fromAccount){
          new Helper().transfer();
        }
      }
    } else {
      synchronized (tieLock) {
        synchronized (fromAccount) {
          synchronized (toAccount) {
            new Helper().transfer();
          }
        }
      }
    }
  }

  class Account implements Comparable<Account>{

    int getBalance() {
      return -1;
    }

    @Override
    public int compareTo(Account o) {
      return 0;
    }
  }

  class DollarAmount {
  }
}

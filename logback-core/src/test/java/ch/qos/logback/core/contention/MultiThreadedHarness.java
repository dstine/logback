/**
 * Logback: the generic, reliable, fast and flexible logging framework.
 * 
 * Copyright (C) 2000-2009, QOS.ch
 * 
 * This library is free software, you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation.
 */
package ch.qos.logback.core.contention;


/**
 * Useful scaffolding/harness to start and stop multiple threads.
 * 
 * @author Joern Huxhorn
 * @author Ralph Goers
 * @author Ceki Gulcu
 */
public class MultiThreadedHarness {

  RunnableWithCounterAndDone[] runnableArray;
  Thread[] threadArray;
  final long overallDurationInMillis;

  public MultiThreadedHarness(long overallDurationInMillis) {
    this.overallDurationInMillis = overallDurationInMillis;
  }

  public void printEnvironmentInfo(String msg) {
    System.out.println("=== " + msg + " ===");
    System.out.println("java.runtime.version = "
        + System.getProperty("java.runtime.version"));
    System.out.println("java.vendor          = "
        + System.getProperty("java.vendor"));
    System.out.println("java.version         = "
        + System.getProperty("java.version"));
    System.out.println("os.name              = "
        + System.getProperty("os.name"));
    System.out.println("os.version           = "
        + System.getProperty("os.version"));
  }

  public void execute(RunnableWithCounterAndDone[] runnableArray)
      throws InterruptedException {
    this.runnableArray = runnableArray;
    Thread[] threadArray = new Thread[runnableArray.length];

    for (int i = 0; i < runnableArray.length; i++) {
      threadArray[i] = new Thread(runnableArray[i]);
    }
    for (Thread t : threadArray) {
      t.start();
    }
    // let the threads run for a while
    Thread.sleep(overallDurationInMillis);

    for (RunnableWithCounterAndDone r : runnableArray) {
      r.setDone(true);
    }
    for (Thread t : threadArray) {
      t.join();
    }
  }
}

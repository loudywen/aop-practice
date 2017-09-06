package com.devon.demo.springaoppractice;

import org.springframework.stereotype.Component;
@Component
public class Service {

  @LogExecutionTime(name ="diwen")
  public void serve() throws InterruptedException {
    Thread.sleep(2000);
  }
}

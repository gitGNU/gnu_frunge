package org.nognu.frunge;


public class Parser {


  public static void main(String ... arg) {
      System.out.format("Hello word!%n");
      
      System.out.format("Arguments:%n");
      for(int i=0; i<arg.length; i++) {
        System.out.format("arg[%d]=%s%n", i, arg[i]);
      }
      
  }
}
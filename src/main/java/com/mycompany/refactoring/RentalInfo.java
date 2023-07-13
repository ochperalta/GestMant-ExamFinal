/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.refactoring;

import java.util.HashMap;

/**
 *
 * @author Zaleski
 */
public class RentalInfo {
    public String statement(Customer customer) {
    HashMap<String, Movie> movies = new HashMap();
    movies.put("F001", new Movie("You've Got Mail", Constans.REGULAR_STRING));
    movies.put("F002", new Movie("Matrix", Constans.REGULAR_STRING));
    movies.put("F003", new Movie("Cars", "childrens"));
    movies.put("F004", new Movie("Fast & Furious X", "new"));

    double totalAmount = 0;
    int frequentEnterPoints = 0;
    StringBuilder bld = new StringBuilder();
    bld.append("Rental Record for ").append(customer.getName()).append("\n");
    String result;
    
    for (MovieRental r : customer.getRentals()) {
      double thisAmount;
      
      // determine amount for each movie
      thisAmount = calculateAmount(movies, r);

      //add frequent bonus points
      frequentEnterPoints++;
      // add bonus for a two day new release rental
      if ("new".equals(movies.get(r.getMovieId()).getCode()) && r.getDays() > 2) frequentEnterPoints++;

      //print figures for this rental
      bld.append("\t").append(movies.get(r.getMovieId()).getTitle()).append("\t").append(thisAmount).append("\n");
      totalAmount = totalAmount + thisAmount;
    }
    // add footer lines
    bld.append("Amount owed is ").append(totalAmount).append("\n");
    bld.append("You earned ").append(frequentEnterPoints).append(" frequent points\n");
    result = bld.toString();

    return result;
    }
    
    // función: determine amount
    private double calculateAmount(HashMap<String, Movie> movies,MovieRental r){
        double thisAmount = 0;
        if (movies.get(r.getMovieId()).getCode().equals(Constans.REGULAR_STRING)) {
        thisAmount = 2;
        if (r.getDays() > 2) {
          thisAmount = ((r.getDays() - 2) * 1.5) + thisAmount;
        }
      }
      if (movies.get(r.getMovieId()).getCode().equals("new")) {
        thisAmount = r.getDays() * 3;
      }
      if (movies.get(r.getMovieId()).getCode().equals("childrens")) {
        thisAmount = 1.5;
        if (r.getDays() > 3) {
          thisAmount = ((r.getDays() - 3) * 1.5) + thisAmount;
        }
      }
        return thisAmount;
    }
}

package com.sics.params;

/**
 * @author liangjc
 * @version 2022/08/05
 */
public class VoteName {
  private String name;
  private double score;

  public String getName() {
    return name;
  }

  public VoteName(String name) {
    this.name = name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getScore() {
    return score;
  }

  public void setScore(double score) {
    this.score = score;
  }
}

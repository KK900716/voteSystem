package com.sics.params;

/**
 * @author liangjc
 * @version 2022/08/05
 */
public class VoteParam {
  private String name;
  private double score;

  public String getName() {
    return name;
  }

  public VoteParam(String name, double score) {
    this.name = name;
    this.score = score;
  }

  public VoteParam() {
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "VoteParam{" +
            "name='" + name + '\'' +
            ", score=" + score +
            '}';
  }

  public double getScore() {
    return score;
  }

  public void setScore(double score) {
    this.score = score;
  }
}

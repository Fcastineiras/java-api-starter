package com.project.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Entity {

  public Entity(
      Integer someSmallId,
      Long someBigId,
      BigDecimal somePrice,
      SomeEnum someEnum,
      LocalDateTime someDate) {
    this.someSmallId = someSmallId;
    this.someBigId = someBigId;
    this.somePrice = somePrice;
    this.someEnum = someEnum;
    this.someDate = someDate;
  }

  private Integer someSmallId;
  private Long someBigId;
  private BigDecimal somePrice;
  private SomeEnum someEnum;
  private LocalDateTime someDate;

  public Integer getSomeSmallId() {
    return someSmallId;
  }

  public Long getSomeBigId() {
    return someBigId;
  }

  public BigDecimal getSomePrice() {
    return somePrice;
  }

  public SomeEnum getSomeEnum() {
    return someEnum;
  }

  public LocalDateTime getSomeDate() {
    return someDate;
  }
}

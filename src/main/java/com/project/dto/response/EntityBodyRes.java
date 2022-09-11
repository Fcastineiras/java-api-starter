package com.project.dto.response;

import static com.project.utils.Constants.DATE_TIME_PATTERN;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.project.model.Entity;
import com.project.model.SomeEnum;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EntityBodyRes {

  public EntityBodyRes() {}

  public EntityBodyRes(Entity entity) {
    this.someSmallId = entity.getSomeSmallId();
    this.someBigId = entity.getSomeBigId();
    this.somePrice = entity.getSomePrice();
    this.someEnum = entity.getSomeEnum();
    this.someDate = entity.getSomeDate();
  }

  private Integer someSmallId;
  private Long someBigId;
  private BigDecimal somePrice;
  private SomeEnum someEnum;

  @JsonFormat(pattern = DATE_TIME_PATTERN)
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

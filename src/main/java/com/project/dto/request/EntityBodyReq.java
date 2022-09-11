package com.project.dto.request;

import static com.project.utils.Constants.DATE_TIME_PATTERN;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.project.model.SomeEnum;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EntityBodyReq {

  public EntityBodyReq() {
    // do nothing
  }

  public EntityBodyReq(
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
